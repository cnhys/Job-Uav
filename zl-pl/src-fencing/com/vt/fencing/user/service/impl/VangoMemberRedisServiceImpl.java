package com.vt.fencing.user.service.impl;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.vt.fencing.IGatewayServiceDef;
import com.vt.fencing.user.service.IVangoMemberRedisService;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
/**
 * 会员Redis服务实现
 * Created by john on 16/11/18.
 */
@Service(IGatewayServiceDef.MEMBER_REDIS_SERVICE)
public class VangoMemberRedisServiceImpl  implements IVangoMemberRedisService{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7429287528302122066L;

	   /**
     * redis url
     */
    @Value("${redis.host}")
    private String redisUrl;
    /**
     * redis port
     */
    @Value("${redis.port}")
    private int redisPort;
    /**
     * redis资源池
     */
    private JedisPool jedisPool;
    
    /**
     * 初始化
     */
    @PostConstruct
    public void init() {
    	JedisPoolConfig config = new JedisPoolConfig();  
    	//控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；  
    	//如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。  
    	config.setMaxTotal(500); 
    	//控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。  
    	config.setMaxIdle(5);  
    	//表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；  
    	config.setMaxWaitMillis(1000 * 100);  
    	//在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；  
    	config.setTestOnBorrow(true); 

        jedisPool = new JedisPool(config, redisUrl, redisPort , 10000, "VANgo1234");
    }

    /**
     * <h1>设置用户ID所对应的密钥</h1>
     * <p>使用的结构为:key = "usersecret:${userId}", value = "${secret}"</p>
     *
     * @param userId
     * @param secret
     */
    public void setUserSecret(Integer userId, String secret) {
        if (userId == null || userId <= 0 || StringUtils.isEmpty(secret)) {
            return;
        }
        Jedis jedis = getConnection();
        if (jedis != null) {
            String key = "usersecret:" + userId;
            jedis.set(key, secret);
            releaseConnection(jedis);
        }
    }

    /**
     * <h1>根据用户ID获取对应的secret</h1>
     *
     * @param userId
     * @return
     */
    public String getUserSecret(Integer userId) {
        if (userId == null || userId <= 0) {
            return null;
        }
        Jedis jedis = getConnection();
        if (jedis != null) {
            String key = "usersecret:" + userId;
            String secret = jedis.get(key);
            releaseConnection(jedis);
            return secret;
        }
        return null;
    }
    
    /**
     * 获取Redis链接
     * @return
     */
    private Jedis getConnection() {
        if (jedisPool != null) {
            return jedisPool.getResource();
        }
        return null;
    }

    /**
     * 释放Redis链接
     * @param jedis
     */
    private void releaseConnection(Jedis jedis) {
        if (jedisPool != null && jedis != null) {
            jedisPool.returnResource(jedis);
        }
    }

}
