package com.miaosha.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Description:
 * redis 读取配置文件。与 RedisService 文件合到一起会出现循环依赖异常。
 * JedisPool 这个 bean 的创建依赖 RedisService，而在 RedisService 中又注入了 JedisPool，导致循环依赖。
 * 把 JedisPool 这个 bean 单独构建文件 RedisPoolFactory 用于实例化。
 * --------------------------------------
 * @ClassName: RedisPoolFactory.java
 * @Date: 2020/8/7 17:20
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Service
public class RedisPoolFactory {
    @Autowired
    RedisConfig redisConfig;

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
