package com.carebed.manage.service.impl;

import java.util.List;
import java.util.Map;

import com.carebed.common.constant.Constants;
import com.carebed.manage.entity.SysDictData;
import com.carebed.manage.service.ISysDictDataService;
import com.carebed.util.DictUtils;
import com.carebed.utils.CacheUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.carebed.common.core.page.TableDataInfo;
import com.carebed.common.core.text.Convert;
import com.carebed.common.utils.StringUtils;
import com.carebed.manage.mapper.SysDictDataMapper;


/**
 * 字典 业务层处理
 * 
 * @author ruoyi
 */
@Service
public class SysDictDataServiceImpl implements ISysDictDataService
{
    @Autowired
    private SysDictDataMapper dictDataMapper;

    @Autowired
    private DictUtils dictUtils;

    @Autowired
    private CacheUtils cacheUtils;

    /**
     * 根据条件查询字典数据
     * 
     * @param dictData 字典数据信息
     * @return 字典数据集合信息
     */
    @Override
    public List<SysDictData> selectDictDataList(SysDictData dictData)
    {
        return dictDataMapper.selectDictDataList(dictData);
    }
    /**
     * 根据条件分页查询字典数据
     * 
     * @param dictData 字典数据信息
     * @return 字典数据集合信息
     */
    @Override
    public TableDataInfo selectDictDataPageList(SysDictData dictData,Map<String,Object> map)
    {
    	if (map.get("orderBy") != null && StringUtils.isNotBlank(map.get("orderBy").toString())) {
			PageHelper.orderBy(map.get("orderBy").toString());
		}
		PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()),
				Integer.parseInt(map.get("pageSize").toString()));
		List<SysDictData> list = dictDataMapper.selectDictDataList(dictData);
    	PageInfo<SysDictData> pageInfo = new PageInfo<SysDictData>(list);
		TableDataInfo rspData = new TableDataInfo();
		rspData.setCode(0);
		rspData.setRows(pageInfo.getList());
		rspData.setTotal(pageInfo.getTotal());
		return rspData;
    }
    /**
     * 根据字典类型和字典键值查询字典数据信息
     * 
     * @param dictType 字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    @Override
    public String selectDictLabel(String dictType, String dictValue)
    {
        return dictDataMapper.selectDictLabel(dictType, dictValue);
    }

    /**
     * 根据字典数据ID查询信息
     * 
     * @param dictCode 字典数据ID
     * @return 字典数据
     */
    @Override
    public SysDictData selectDictDataById(Long dictCode)
    {
        return dictDataMapper.selectDictDataById(dictCode);
    }

    /**
     * 批量删除字典数据
     * 
     * @param ids 需要删除的数据
     * @return 结果
     */
    @Override
    public int deleteDictDataByIds(String ids)
    {
        String dictCode = Convert.toStrArray(ids)[0];
        SysDictData sysDictData = dictDataMapper.selectDictDataById(Long.parseLong(dictCode));
        int row = dictDataMapper.deleteDictDataByIds(Convert.toStrArray(ids));
        if (row > 0)
        {
            //dictUtils.clearDictCache();
            flushDictCache(sysDictData.getDictType());
        }
        return row;
    }

    /**
     * 新增保存字典数据信息
     * 
     * @param dictData 字典数据信息
     * @return 结果
     */
    @Override
    public int insertDictData(SysDictData dictData)
    {
        int row = dictDataMapper.insertDictData(dictData);
        if (row > 0)
        {
            //dictUtils.clearDictCache();
            flushDictCache(dictData.getDictType());
        }
        return row;
    }

    /**
     * 修改保存字典数据信息
     * 
     * @param dictData 字典数据信息
     * @return 结果
     */
    @Override
    public int updateDictData(SysDictData dictData)
    {
        int row = dictDataMapper.updateDictData(dictData);
        if (row > 0)
        {
            //dictUtils.clearDictCache();
            flushDictCache(dictData.getDictType());
        }
        return row;
    }

    /**
     * 更新缓存信息
     * @param dictType
     */
    public void flushDictCache(String dictType){
        //清除缓存数据
        cacheUtils.remove(Constants.SYS_DICT_CACHE,Constants.SYS_DICT_KEY+dictType);
        //放入缓存新数据数据
        List<SysDictData> dictDatas = dictDataMapper.selectDictDataByType(dictType);
        dictUtils.setDictCache(dictType, dictDatas);
    }

    /**
     * 根据类型查询字典数据信息
     *
     * @param dictType 字典数据信息
     * @return 结果
     */
    @Override
    public List<SysDictData> selectDictDataByType(String dictType)
    {
        return  dictDataMapper.selectDictDataByType(dictType);
    }
}
