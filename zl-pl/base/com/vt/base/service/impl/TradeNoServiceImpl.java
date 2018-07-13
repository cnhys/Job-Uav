package com.vt.base.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.vt.base.service.ITradeNoService;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 交易流水号服务实现
 * Created by zhangtao on 16/1/9.
 */
@Component
public class TradeNoServiceImpl implements ITradeNoService {
    /**
     * 交易批次号KEY
     */
    public static final String TX_NO_KEY = "GatewayTxNoKey";
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
     * 生成交易批次号
     *
     * @return
     */
    public Integer generateTxNo() {
        Jedis jedis = getConnection();
        if (jedis != null) {
            Long txNo = jedis.incr(TX_NO_KEY);
            releaseConnection(jedis);
            if (txNo != null) {
                return txNo.intValue();
            }
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
    @Override
    public String generateFlowNumber(String prefix, int length) {
        Jedis jedis = getConnection();
        if (jedis != null) {
            Long txNo = jedis.incr("FLOWNUMBER:" + prefix);
            releaseConnection(jedis);
            if (txNo != null) {
                return makeFlowNumber(prefix, length, txNo);
            }
        }
        return null;
    }
    /**
     * 生成流水号
     * @param prefix
     * @param length
     * @param txNo
     * @return
     */
    private static String makeFlowNumber(String prefix, int length, Long txNo) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append("0");
        }
        sb.append(txNo);
        String data = sb.substring(sb.length() - length - 1, sb.length());
        return prefix + data;
    }
}
