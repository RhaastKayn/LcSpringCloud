package com.lc.service;

import com.lc.redis.RedisService;
import com.lc.shoppingcommon.constant.RedisPrefixConstant;
import com.lc.shoppingcommon.pojo.UserEntity;
import com.lc.shoppingcommon.request.result.SrvResult;
import com.lc.shoppingcommon.util.MD5Utils;
import com.lc.shoppingcommon.util.UUIDUtil;
//import com.lc.redis.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author 刘晨
 * @description 秒杀Service
 * @create 2021/5/19 0019
 * @since 1.0.0
 */
@Service
@Transactional
public class SeckillService {
    @Autowired
    private RedisService redisService;

    /**
     * 获取秒杀地址
     * @param goodsId
     * @param user
     * @return
     */
    public String getSeckillPath(Long goodsId, UserEntity user) {
        if(user == null || goodsId <=0) {
            return null;
        }
        String str = MD5Utils.md5(UUIDUtil.uuid()+"123456");
        //将秒杀地址放入redis中，key为sp:userNickName_goodsId,过期时间为5分钟,value为str
        redisService.setRedis(RedisPrefixConstant.SECKILL_PATH + user.getNickName() + "_"+ goodsId, 300, str);
        return str;
    }

    /**
     * 验证秒杀地址是否正确
     * @param user
     * @param goodsId
     * @param path
     * @return
     */
    public boolean checkUrl(UserEntity user, Long goodsId, String path) {
        if(user == null || goodsId <=0 || StringUtils.isEmpty(path)) {
            return false;
        }
        //从redis获取地址，并进行对比
        String redisPath = redisService.getRedis(RedisPrefixConstant.SECKILL_PATH + user.getNickName() + "_"+ goodsId);
        return path.equals(redisPath);
    }
}
