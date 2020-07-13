package com.carebed.web.shiro.service;
import java.util.List;

import com.alibaba.dubbo.config.annotation.Reference;
import com.carebed.manage.entity.SysDictData;
import com.carebed.manage.service.ISysDictDataService;
import com.carebed.manage.service.ISysDictTypeService;
import org.springframework.stereotype.Service;

/**
 * RuoYi首创 html调用 thymeleaf 实现字典读取
 * 
 * @author iysk
 */
@Service("dict")
public class DictService
{
    @Reference
    private ISysDictTypeService dictTypeService;

    @Reference
    private ISysDictDataService dictDataService;

    /**
     * 根据字典类型查询字典数据信息
     * 
     * @param dictType 字典类型
     * @return 参数键值
     */
    public List<SysDictData> getType(String dictType)
    {
        return dictTypeService.selectDictDataByType(dictType);
    }

    /**
     * 根据字典类型和字典键值查询字典数据信息
     * 
     * @param dictType 字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    public String getLabel(String dictType, String dictValue)
    {
        return dictDataService.selectDictLabel(dictType, dictValue);
    }
}
