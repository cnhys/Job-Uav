package com.vt.base.util;

/**
 * <p> Title: 缓存工具类 </p>
 * <p> Description: </p>
 *
 * @Author:Devin Bai
 * @Time:Jun 17, 20155:10:45 PM
 * @Version:1.00
 * @Record <pre>
 * Version 	Author	Time		describe
 * ----------------------------------------
 * 1.00		Devin	Jun 17, 2015	release
 * ----------------------------------------
 * </pre>
 */
public class CacheUtil {

    /**
     * 添加缓存
     *
     * @param key   缓存Key
     * @param value 缓存值
     */
    public static void put(String cacheName, Object key, Object value) {

    }

    /**
     * 获取缓存
     *
     * @param key 缓存Key
     * @return 缓存值
     */
    public static Object get(String cacheName, Object key) {
        return null;
    }

    /**
     * 移除缓存
     *
     * @param key 缓存Key
     */
    public static void remove(String cacheName, Object key) {
    }

    /**
     * 初始化缓存
     *
     * @param cacheName 缓存名称
     */
    public synchronized static void initCache(String cacheName) {

    }
}
