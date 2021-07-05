package com.lc.redis;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import redis.clients.jedis.*;

import java.util.concurrent.TimeUnit;

/**
 * @author 刘晨
 * @description
 * @create 2021/5/19 0019
 * @since 1.0.0
 */
@Component
@Slf4j
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取指定redisKey的值（String）
     *
     * @param key
     * @return
     */
    public String getRedis(String key) {
        Object resultObj = redisTemplate.opsForValue().get(key);
        String res = String.valueOf(resultObj);
        return res;
    }

    /**
     * set redis
     *
     * @param key           redisKey
     * @param expireSeconds 过期秒数
     * @param value         redisValue
     * @param <T>
     * @return boolean 是否成功
     */
    public <T> boolean setRedis(String key, Integer expireSeconds, T value) {
        try {
            String valueStr = beanToString(value);
            if (StringUtils.isEmpty(key) || StringUtils.isEmpty(valueStr)) {
                log.warn("redis key or value is empty,key is :{} , value is :{}", key, valueStr);
                return false;
            }
            redisTemplate.opsForValue().set(key, valueStr);
            redisTemplate.expire(key, expireSeconds, TimeUnit.SECONDS);
            return true;
        } finally {
            //释放链接
            RedisConnectionUtils.unbindConnection(redisTemplate.getConnectionFactory());
        }
    }

    /**
     * 将String转换为指定类
     *
     * @param str
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T stringToBean(String str, Class<T> clazz) {
        if (str == null || str.length() <= 0 || clazz == null) {
            return null;
        }
        if (clazz == int.class || clazz == Integer.class) {
            return (T) Integer.valueOf(str);
        } else if (clazz == String.class) {
            return (T) str;
        } else if (clazz == long.class || clazz == Long.class) {
            return (T) Long.valueOf(str);
        } else {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }

    /**
     * bean to string
     *
     * @param value
     * @param <T>
     * @return
     */
    public static <T> String beanToString(T value) {
        if (value == null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if (clazz == int.class || clazz == Integer.class) {
            return "" + value;
        } else if (clazz == String.class) {
            return (String) value;
        } else if (clazz == long.class || clazz == Long.class) {
            return "" + value;
        } else {
            return JSON.toJSONString(value);
        }
    }
}
