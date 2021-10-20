package com.lc.shoppingmall.client.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.lc.shoppingcommon.constant.RedisPrefixConstant;
import com.lc.shoppingcommon.constant.ResultMessageConstant;
import com.lc.shoppingcommon.interfaces.OrderFeignService;
import com.lc.shoppingcommon.interfaces.SeckillFeignService;
import com.lc.shoppingcommon.pojo.SeckillOrderEntity;
import com.lc.shoppingcommon.pojo.UserEntity;
import com.lc.shoppingcommon.request.result.SrvResult;
import com.lc.shoppingmall.client.service.SeckillService;
import com.lc.shoppingmall.rabbitmq.MQReceiver;
import com.lc.shoppingmall.rabbitmq.MQSender;
import com.lc.shoppingmall.rabbitmq.SeckillMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.util.*;
import java.util.List;


/**
 * @author 刘晨
 * @description 秒杀Controller
 * @create 2021/5/14 0014
 * @since 1.0.0
 */
@RestController
@RequestMapping("/seckill/client")
@RefreshScope
@Slf4j
public class SeckillClientController {

    @Value("${test.a}")
    private String aa;

    @Autowired
    private SeckillFeignService seckillFeignService;
    @Autowired
    private OrderFeignService orderFeignService;
    @Autowired
    private MQSender mqSender;
    @Autowired
    private SeckillService seckillService;


    private Map<Long, Boolean> seckillOverMap = new HashMap<>();

    private RedisTemplate redisTemplate;

    /**
     * 秒杀系统为了避免链接暴露，导致其他人利用脚本进行卡点请求
     * 对秒杀链接进行动态生成（也就是 url动态化）
     */
    @PostMapping("/getPath")
    public SrvResult<String> getSeckillPath(@RequestParam("goodsId") Long goodsId, @RequestBody UserEntity user) {
        System.out.println(aa);

        if (user == null || goodsId < 0) {
            return SrvResult.creat(false, ResultMessageConstant.WRONG_PARAM);
        }
        String path = seckillFeignService.getSeckillPath(goodsId, user);
        return SrvResult.creat(true, path,"success");
    }

    /**
     * 生成图片验证码
     * @param response
     * @return
     */
    @PostMapping("/getSeckillVerifyCode")
    @ResponseBody
    public SrvResult<String> getSeckillVerifyCode(HttpServletResponse response) {
        try {
            //验证码图片
            BufferedImage image = seckillService.creatSeckillVerifyCode();
            //将验证码图片输出
            OutputStream outputStream = response.getOutputStream();
            ImageIO.write(image, "JPEG", outputStream);
            outputStream.flush();
            outputStream.close();
            return SrvResult.creat(true, null);
        } catch (Exception e) {
            log.error("生成验证码失败", e);
            return SrvResult.creat(false, "秒杀失败");
        }
    }

    /**
     * 秒杀
     * @PathVariable 注解可以将url中{XXX}的占位符映射到方法的入参中
     */
    @PostMapping("/{path}/doSeckill")
    public SrvResult<String> doSeckill(@PathVariable("path") String path, @RequestParam("goodsId") Long goodsId, UserEntity user){
        if (user == null || StringUtils.isEmpty(path) || goodsId < 0) {
            return SrvResult.creat(false, ResultMessageConstant.WRONG_PARAM);
        }
        //1.验证path是否正确
        Boolean checkUrl = seckillFeignService.checkUrl(user, goodsId, path);
        if (!checkUrl) {
            return SrvResult.creat(false, "seckill path is wrong");
        }
        //2.秒杀商品是否结束
        //内存标记，减少redis访问。查看库存，是否商品已被秒杀完毕,true表示无库存，秒杀完毕
        if (seckillOverMap.get(goodsId)) {
            return SrvResult.creat(false, "seckill is over");
        }
        //3.从秒杀订单表查看是否已经秒杀过
        List<SeckillOrderEntity> order = orderFeignService.getOrderByNameAndGoods(user.getId(), Arrays.asList(goodsId)).getData();
        if (!CollectionUtils.isEmpty(order)) {
            return SrvResult.creat(false, "Can't repeat seckill");
        }
        //可以秒杀，redis中秒杀商品数量减一，减一后商品数量小于0，说明秒杀结束，大于等于0则可以秒杀
        Long stock = redisTemplate.opsForValue().decrement(RedisPrefixConstant.SECKILL_GOODS_STOCK + goodsId);
        if (stock < 0) {
            seckillOverMap.put(goodsId, true);
            return SrvResult.creat(false, "seckill is over");
        }
        //消息队列发送用户及商品信息，进行数据库操作
        SeckillMessage seckillMessage = new SeckillMessage();
        seckillMessage.setUser(user);
        seckillMessage.setGoodsId(goodsId);
        mqSender.sendSeckillMessage(seckillMessage);
        return SrvResult.creat(true, "success");
    }

}
