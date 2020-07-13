package com.carebed.web.controller.manage;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.carebed.common.constant.Constants;
import com.carebed.common.core.controller.BaseController;
import com.carebed.common.core.domain.AjaxResult;
import com.carebed.common.core.page.TableDataInfo;
import com.carebed.common.core.text.Convert;
import com.carebed.manage.entity.SysDictData;
import com.carebed.manage.service.ISysConfigService;
import com.carebed.manage.service.ISysDictDataService;
import com.carebed.manage.service.ISysDictTypeService;
import com.carebed.manage.service.ISysPostService;
import com.carebed.utils.CacheUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 缓存信息操作处理
 * 
 * @author iysk
 */
@Controller
@RequestMapping("/system/cache")
public class SysCacheController extends BaseController
{
    private String prefix = "system/cache";

    @Reference
    private ISysDictTypeService sysDictTypeService;

    @Reference
    private ISysDictDataService sysDictDataService;

    @Reference
    private ISysPostService sysPostService;

    @Reference
    private ISysConfigService sysConfigService;

    @Autowired
    private CacheUtils cacheUtils;
    
    @RequiresPermissions("system:cache:view")
    @GetMapping()
    public String operlog()
    {
        return prefix + "/cache";
    }

    @RequiresPermissions("system:cache:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysDictData dictData)
    {
        Map<String,Object> map=startMapPage();
        TableDataInfo rspData = new TableDataInfo();
        SysDictData sysDictData = null;
        List<SysDictData> sysDictDataList = sysDictDataService.selectDictDataByType("redis_cache_info");
        if(sysDictDataList != null && sysDictDataList.size()>0) {
            for (int i = sysDictDataList.size() - 1; i >= 0; i--) {
                sysDictData = sysDictDataList.get(i);
                if (Constants.SYS_DICT_CACHE.equals(sysDictData.getDictValue())) {
                    Map<Object, Object> dictCacheMap = cacheUtils.getCache(Constants.SYS_DICT_CACHE);
                    if (dictCacheMap == null || dictCacheMap.size() < 1) {
                        sysDictDataList.remove(sysDictData);
                    }
                } else if (Constants.SYS_POST_CACHE.equals(sysDictData.getDictValue())) {
                    Map<Object, Object> postCacheMap = cacheUtils.getCache(Constants.SYS_POST_CACHE);
                    Map<Object, Object> postLevelCacheMap = cacheUtils.getCache(Constants.SYS_POSTLEVEL_CACHE);
                    if ((postCacheMap == null || postCacheMap.size() < 1) && (postLevelCacheMap == null || postLevelCacheMap.size() < 1)) {
                        sysDictDataList.remove(sysDictData);
                    }
                } else if (Constants.SYS_CONFIG_CACHE.equals(sysDictData.getDictValue())) {
                    Map<Object, Object> configCacheMap = cacheUtils.getCache(Constants.SYS_CONFIG_CACHE);
                    if (configCacheMap == null || configCacheMap.size() < 1) {
                        sysDictDataList.remove(sysDictData);
                    }
                }
            }
        }
        PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()), Integer.parseInt(map.get("pageSize").toString()));
        PageInfo<SysDictData> pageInfo = new PageInfo<SysDictData>(sysDictDataList);
        rspData.setCode(0);
        rspData.setRows(pageInfo.getList());
        rspData.setTotal(pageInfo.getTotal());
        return rspData;
    }

    @RequiresPermissions("system:cache:refresh")
    @PostMapping("/refresh")
    @ResponseBody
    public AjaxResult refresh(String codes)
    {
        try
        {
            String[] cacheCodes = Convert.toStrArray(codes);
            for (String cacheCode : cacheCodes)
            {
                //数据字典数据刷新
                if(cacheCode.equals(Constants.SYS_DICT_CACHE)){
                    cacheUtils.removeAll(Constants.SYS_DICT_CACHE);
                    sysDictTypeService.initSysDictType();
                //岗位及职级数据刷新
                }else if(cacheCode.equals(Constants.SYS_POST_CACHE)){
                    cacheUtils.removeAll(Constants.SYS_POST_CACHE);
                    cacheUtils.removeAll(Constants.SYS_POSTLEVEL_CACHE);
                //参数配置数据刷新
                }else if(cacheCode.equals(Constants.SYS_CONFIG_CACHE)){
                    cacheUtils.removeAll(Constants.SYS_CONFIG_CACHE);
                    sysConfigService.initSysConfig();
                }
            }
            return toAjax(1);
        }
        catch (Exception e)
        {
            return error(e.getMessage());
        }
    }
    @RequiresPermissions("system:cache:refresh")
    @GetMapping("/refreshAll")
    @ResponseBody
    public AjaxResult refreshAll()
    {
        try
        {
            //数据字典数据刷新
            cacheUtils.removeAll(Constants.SYS_DICT_CACHE);
            sysDictTypeService.initSysDictType();
            //岗位及职级数据刷新
            cacheUtils.removeAll(Constants.SYS_POST_CACHE);
            cacheUtils.removeAll(Constants.SYS_POSTLEVEL_CACHE);
            //参数配置数据刷新
            cacheUtils.removeAll(Constants.SYS_CONFIG_CACHE);
            sysConfigService.initSysConfig();
            return toAjax(1);
        }
        catch (Exception e)
        {
            return error(e.getMessage());
        }
    }
}
