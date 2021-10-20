package com.lc.shoppingmall.client.service;

import com.lc.redis.RedisService;
import com.lc.shoppingcommon.interfaces.SeckillFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author 刘晨
 * @description
 * @create 2021/10/12 0012
 * @since 1.0.0
 */
@Service
public class SeckillService {
    @Autowired
    private SeckillFeignService seckillFeignService;

    public BufferedImage creatSeckillVerifyCode() {
        int width = 80;
        int height = 32;
        //创建图片 BufferedImage是一个带缓冲区的图像类，是Image的子类。使用BufferedImage生成的图片在内存中有一个缓冲区，利用缓冲区操作图片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = image.getGraphics();
        //设置图片背景颜色
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, width, height);
        //设置边框
        graphics.setColor(Color.red);
        graphics.drawRect(0, 0, width-1, height-1);
        //生成验证码
        String verifyCode = generateVerifyCode();
        //设置图片颜色，字体。并把验证码放入图片中
        graphics.setColor(Color.green);
        //字体，传null会默认用Default字体，可以自己传字体名称
        graphics.setFont(new Font(null, Font.BOLD, 24));
        graphics.drawString(verifyCode, 8, 24);
        //销毁图形资源
        graphics.dispose();
        //计算验证码的答案，并存到redis中
        Integer res = calc(verifyCode);
        seckillFeignService.setVerifyCodeInRedis(res);
        //输出图片
        return image;
    }

    /**
     * 计算字符串验证码的答案
     * 因为 String a = "3 * 2 + 1"不能用java计算，要使用js的eval函数进行计算。
     * 所以使用ScriptEngineManager类
     * @param exp
     * @return
     */
    private static Integer calc(String exp) {
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("JavaScript");
            Integer catch1 = (Integer)engine.eval(exp);
            return catch1.intValue();
        }catch(Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static char[] ops = new char[] {'+', '-', '*'};
    /**
     * + - *
     * 生成10以内的加减乘字符串
     * */
    private String generateVerifyCode() {
        Random rdm = new Random();
        int num1 = rdm.nextInt(10);
        int num2 = rdm.nextInt(10);
        int num3 = rdm.nextInt(10);
        char op1 = ops[rdm.nextInt(3)];
        char op2 = ops[rdm.nextInt(3)];
        String exp = ""+ num1 + op1 + num2 + op2 + num3;
        return exp;
    }
}
