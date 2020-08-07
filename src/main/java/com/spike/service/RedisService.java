package com.spike.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.spike.common.KeyPrefix;
import com.spike.redis.RedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.ObjectUtils;
import org.thymeleaf.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Description: --------------------------------------
 * @ClassName: RedisService.java
 * @Date: 2020/6/7 17:07
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Component
public class RedisService {

    @Autowired
    JedisPool jedisPool;

    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            // 唯一key
            String realKey = prefix.getPrefix() + key;
            String value = jedis.get(realKey);
            T t = stringToBean(value, clazz);
            return t;
        } finally {
            returnToPool(jedis);
        }
    }

    public <T> boolean set(KeyPrefix prefix, String key, T value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String valueObj = beanToString(value);
            if (StringUtils.isEmpty(valueObj)) {
                return false;
            }
            // 唯一key
            String realKey = prefix.getPrefix() + key;
            int seconds = prefix.expireSeconds();
            if (seconds <= 0) {
                jedis.set(realKey, valueObj);
            } else {
                jedis.setex(realKey, seconds, valueObj);
            }
            return true;
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 字符串转对象（不支持list对象）
     */
    private <T> T stringToBean(String string, Class<?> clazz) {
        if (StringUtils.isEmpty(string)) {
            return null;
        }
        if (int.class == clazz || Integer.class == clazz) {
            return (T) Integer.valueOf(string);
        } else if (String.class == clazz) {
            return (T) string;
        } else if (long.class == clazz || Long.class == clazz) {
            return (T) Long.valueOf(string);
        } else {
            return (T) JSON.toJavaObject(JSON.parseObject(string), clazz);
        }
    }

    /**
     * 对象转字符串
     */
    private <T> String beanToString(T value) {
        // 参数校验
        if (null == value) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if (int.class == clazz || Integer.class == clazz) {
            return "" + value;
        } else if (String.class == clazz) {
            return (String) value;
        } else if (long.class == clazz || Long.class == clazz) {
            return "" + value;
        } else {
            return JSON.toJSONString(value);
        }
    }


    private void returnToPool(Jedis jedis) {
        if (null != jedis) {
            jedis.close();
        }
    }

    /**
     * 判断 Key 是否存在
     */
    public <T> boolean exists(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //
            String realKey = prefix.getPrefix() + key;
            return jedis.exists(realKey);
        } finally {
            returnToPool(jedis);
        }
    }


    /**
     * 增加 value 值
     */
    public <T> Long incr(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //
            String realKey = prefix.getPrefix() + key;
            return jedis.incr(realKey);
        } finally {
            returnToPool(jedis);
        }
    }


    /**
     * 减少 value 值
     */
    public <T> Long decr(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //
            String realKey = prefix.getPrefix() + key;
            return jedis.decr(realKey);
        } finally {
            returnToPool(jedis);
        }
    }


}
