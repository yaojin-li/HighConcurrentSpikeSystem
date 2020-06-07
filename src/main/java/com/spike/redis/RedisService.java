package com.spike.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
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

    @Autowired
    RedisConfig redisConfig;

    public <T> T get(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            T t = stringToBean(jedis);
            return t;
        } finally {
            returnToPool(jedis);
        }
    }

    private <T> T stringToBean(String string) {

        return null;
    }


    private void returnToPool(Jedis jedis) {
        if (null != jedis) {
            jedis.close();
        }
    }


    @Bean
    public JedisPool JedisPoolFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait() * 1000);
        jedisPoolConfig.setMaxIdle(redisConfig.getPoolMaxIdle());
        jedisPoolConfig.setMinIdle(redisConfig.getPoolMinIdle());

        JedisPool jedisPool = new JedisPool(jedisPoolConfig, redisConfig.getHost(), redisConfig.getPort(),
                redisConfig.getTimeout(), redisConfig.getPassword(), redisConfig.getDatabase());
        return jedisPool;
    }


}
