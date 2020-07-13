package com.carebed.util;

import com.carebed.common.constant.Constants;
import com.carebed.common.utils.StringUtils;
import com.carebed.manage.entity.SysDictData;
import com.carebed.utils.CacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 字典工具类
 * 
 * @author ruoyi
 */
@Component
public class DictUtils
{

    @Autowired
    private CacheUtils cacheUtils;


    /**
     * 设置字典缓存
     * 
     * @param key 参数键
     * @param dictDatas 字典数据列表
     */
    public void setDictCache(String key, List<SysDictData> dictDatas)
    {
        cacheUtils.put(getCacheName(), getCacheKey(key), dictDatas);
    }

    /**
     * 获取字典缓存
     * 
     * @param key 参数键
     * @return dictDatas 字典数据列表
     */
    public List<SysDictData> getDictCache(String key)
    {
        Object cacheObj = cacheUtils.get(getCacheName(), getCacheKey(key));
        if (StringUtils.isNotNull(cacheObj))
        {
            List<SysDictData> DictDatas = StringUtils.cast(cacheObj);
            return DictDatas;
        }
        return null;
    }
    
    /**
     * 获取字典缓存
     * 
     * @param key 参数键
     * @param value 参数键
     * @return String 字典数据值
     */
    public String getDictCacheByKey(String key,String value)
    {
        Object cacheObj = cacheUtils.get(getCacheName(), getCacheKey(key));
        if (StringUtils.isNotNull(cacheObj))
        {
            List<SysDictData> DictDatas = StringUtils.cast(cacheObj);
            for(SysDictData dictData: DictDatas){
            	if(dictData.getDictValue().equals(value)){
            		return dictData.getDictLabel();
            	}
            }
        }
        return "";
    }

    /**
     * 清空字典缓存
     */
    public void clearDictCache()
    {
        cacheUtils.removeAll(getCacheName());
    }

    /**
     * 获取cache name
     * 
     * @return 缓存名
     */
    public static String getCacheName()
    {
        return Constants.SYS_DICT_CACHE;
    }

    /**
     * 设置cache key
     * 
     * @param configKey 参数键
     * @return 缓存键key
     */
    public static String getCacheKey(String configKey)
    {
        return Constants.SYS_DICT_KEY + configKey;
    }
}
