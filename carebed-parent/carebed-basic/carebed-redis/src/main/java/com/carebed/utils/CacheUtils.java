package com.carebed.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Cache工具类
 * 
 * @author ruoyi
 */
@Component
public class CacheUtils
{
    private static Logger logger = LoggerFactory.getLogger(CacheUtils.class);

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    private static final String SYS_CACHE = "sys-cache";

    /**
     * 获取SYS_CACHE缓存
     * 
     * @param key
     * @return
     */
    public Object get(String key)
    {
        return redisTemplate.opsForHash().get(SYS_CACHE, key);
    }

    /**
     * 获取SYS_CACHE缓存
     * 
     * @param key
     * @param defaultValue
     * @return
     */
    public Object get(String key, Object defaultValue)
    {
        Object value = get(key);
        return value != null ? value : defaultValue;
    }

    /**
     * 写入SYS_CACHE缓存
     * 
     * @param key
     * @return
     */
    public void put(String key, Object value)
    {
        put(SYS_CACHE, key, value);
    }

    /**
     * 从SYS_CACHE缓存中移除
     * 
     * @param key
     * @return
     */
    public void remove(String key)
    {
        remove(SYS_CACHE, key);
    }

    /**
     * 获取缓存
     * 
     * @param cacheName
     * @param key
     * @return
     */
    public Object get(String cacheName, String key)
    {
        return getCache(cacheName).get(getKey(key));
    }

    /**
     * 获取缓存
     * 
     * @param cacheName
     * @param key
     * @param defaultValue
     * @return
     */
    public Object get(String cacheName, String key, Object defaultValue)
    {
        Object value = get(cacheName, getKey(key));
        return value != null ? value : defaultValue;
    }

    /**
     * 写入缓存
     * 
     * @param cacheName
     * @param key
     * @param value
     */
    public void put(String cacheName, String key, Object value)
    {
        redisTemplate.opsForHash().put(cacheName,key,value);
    }

    /**
     * 从缓存中移除
     * 
     * @param cacheName
     * @param key
     */
    public void remove(String cacheName, String key)
    {
        redisTemplate.opsForHash().delete(cacheName,key);
    }

    /**
     * 从缓存中移除所有
     * 
     * @param cacheName
     */
    public void removeAll(String cacheName)
    {
        Set keys = redisTemplate.opsForHash().keys(cacheName);
        for (Iterator<String> it = keys.iterator(); it.hasNext();)
        {
            redisTemplate.opsForHash().delete(cacheName,it.next());
        }
        logger.info("清理缓存： {} => {}", cacheName, keys);
    }

    /**
     * 从缓存中移除指定key
     * 
     * @param keys
     */
    public void removeByKeys(Set<String> keys)
    {
        removeByKeys(SYS_CACHE, keys);
    }

    /**
     * 从缓存中移除指定key
     * 
     * @param cacheName
     * @param keys
     */
    public void removeByKeys(String cacheName, Set<String> keys)
    {
        for (Iterator<String> it = keys.iterator(); it.hasNext();)
        {
            remove(it.next());
        }
        logger.info("清理缓存： {} => {}", cacheName, keys);
    }

    /**
     * 获取缓存键名
     * 
     * @param key
     * @return
     */
    private static String getKey(String key)
    {
        return key;
    }

    /**
     * 获得一个Cache，没有则显示日志。
     * 
     * @param cacheName
     * @return
     */
    public Map<Object, Object> getCache(String cacheName)
    {
        Map<Object, Object> entries = redisTemplate.opsForHash().entries(cacheName);

        if (entries == null)
        {
            throw new RuntimeException("当前系统中没有定义“" + cacheName + "”这个缓存。");
        }
        return entries;
    }

}
