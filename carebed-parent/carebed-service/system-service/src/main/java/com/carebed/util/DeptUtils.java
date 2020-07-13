package com.carebed.util;

import com.carebed.common.constant.Constants;
import com.carebed.common.utils.StringUtils;
import com.carebed.manage.entity.SysDept;
import com.carebed.utils.CacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 部门工具类
 *
 * @author ruoyi
 */
@Component
public class DeptUtils
{

    @Autowired
    private CacheUtils cacheUtils;

    /**
     * 设置部门缓存
     *
     * @param key 参数键
     * @param sysDept 部门数据列表
     */
    public void setDeptCache(String key, SysDept sysDept)
    {
        cacheUtils.put(getCacheName(), getCacheKey(key), sysDept);
    }

    /**
     * 获取部门缓存
     *
     * @param key 参数键
     * @return dictDatas 部门数据列表
     */
    public SysDept getDeptCache(String key)
    {
        Object cacheObj = cacheUtils.get(getCacheName(), getCacheKey(key));
        if (StringUtils.isNotNull(cacheObj))
        {
            SysDept sysDept = StringUtils.cast(cacheObj);
            return sysDept;
        }
        return null;
    }

    /**
     * 清空部门缓存
     */
    public void clearDeptCache()
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
        return Constants.SYS_DEPT_CACHE;
    }

    /**
     * 设置cache key
     *
     * @param configKey 参数键
     * @return 缓存键key
     */
    public static String getCacheKey(String configKey)
    {
        return Constants.SYS_DEPT_KEY + configKey;
    }
}
