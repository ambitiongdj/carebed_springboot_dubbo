package com.carebed.manage.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.carebed.common.constant.Constants;
import com.carebed.exception.BusinessException;
import com.carebed.manage.entity.SysDictData;
import com.carebed.manage.entity.SysDictType;
import com.carebed.manage.service.ISysDictTypeService;
import com.carebed.util.DictUtils;
import com.carebed.utils.CacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.carebed.common.constant.UserConstants;
import com.carebed.common.core.domain.Ztree;
import com.carebed.common.core.page.TableDataInfo;
import com.carebed.common.core.text.Convert;
import com.carebed.common.utils.StringUtils;
import com.carebed.manage.mapper.SysDictDataMapper;
import com.carebed.manage.mapper.SysDictTypeMapper;


/**
 * 字典 业务层处理
 * 
 * @author ruoyi
 */
@Service
public class SysDictTypeServiceImpl implements ISysDictTypeService
{
    @Autowired
    private SysDictTypeMapper dictTypeMapper;

    @Autowired
    private SysDictDataMapper dictDataMapper;

    @Autowired
    private DictUtils dictUtils;

    @Autowired
    private CacheUtils cacheUtils;

    /**
     * 项目启动时，初始化字典到缓存
     */
    @Override
    public void initSysDictType()
    {
        List<SysDictType> dictTypeList = dictTypeMapper.selectDictTypeAll();
        for (SysDictType dictType : dictTypeList)
        {
            List<SysDictData> dictDatas = dictDataMapper.selectDictDataByType(dictType.getDictType());
            dictUtils.setDictCache(dictType.getDictType(), dictDatas);
        }
    }

    /**
     * 根据条件查询字典类型
     * 
     * @param dictType 字典类型信息
     * @return 字典类型集合信息
     */
    @Override
    public List<SysDictType> selectDictTypeList(SysDictType dictType)
    {
        return dictTypeMapper.selectDictTypeList(dictType);
    }
    /**
     * 根据条件分页查询字典类型
     * 
     * @param dictType 字典类型信息
     * @return 字典类型集合信息
     */
    @Override
    public TableDataInfo selectDictTypePageList(SysDictType dictType, Map<String, Object> map) {
		if (map.get("orderBy") != null && StringUtils.isNotBlank(map.get("orderBy").toString())) {
			PageHelper.orderBy(map.get("orderBy").toString());
		}
		PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()),
				Integer.parseInt(map.get("pageSize").toString()));
		List<SysDictType> list = dictTypeMapper.selectDictTypeList(dictType);
		PageInfo<SysDictType> pageInfo = new PageInfo<SysDictType>(list);
		TableDataInfo rspData = new TableDataInfo();
		rspData.setCode(0);
		rspData.setRows(pageInfo.getList());
		rspData.setTotal(pageInfo.getTotal());
		return rspData;
    }
    /**
     * 根据所有字典类型
     * 
     * @return 字典类型集合信息
     */
    @Override
    public List<SysDictType> selectDictTypeAll()
    {
        return dictTypeMapper.selectDictTypeAll();
    }

    /**
     * 根据字典类型查询字典数据
     * 
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    @Override
    public List<SysDictData> selectDictDataByType(String dictType)
    {
        List<SysDictData> dictDatas = dictUtils.getDictCache(dictType);
        if (StringUtils.isNotNull(dictDatas))
        {
            return dictDatas;
        }
        dictDatas = dictDataMapper.selectDictDataByType(dictType);
        if (StringUtils.isNotNull(dictDatas))
        {
            dictUtils.setDictCache(dictType, dictDatas);
            return dictDatas;
        }
        return null;
    }

    /**
     * 根据字典类型ID查询信息
     * 
     * @param dictId 字典类型ID
     * @return 字典类型
     */
    @Override
    public SysDictType selectDictTypeById(Long dictId)
    {
        return dictTypeMapper.selectDictTypeById(dictId);
    }

    /**
     * 根据字典类型查询信息
     * 
     * @param dictType 字典类型
     * @return 字典类型
     */
    public SysDictType selectDictTypeByType(String dictType)
    {
        return dictTypeMapper.selectDictTypeByType(dictType);
    }

    /**
     * 批量删除字典类型
     * 
     * @param ids 需要删除的数据
     * @return 结果
     */
    @Override
    public int deleteDictTypeByIds(String ids)
    {
        Long[] dictIds = Convert.toLongArray(ids);
        List<SysDictType> dictTypeList = new ArrayList<>();
        for (Long dictId : dictIds)
        {
            SysDictType dictType = selectDictTypeById(dictId);
            dictTypeList.add(dictType);
            if (dictDataMapper.countDictDataByType(dictType.getDictType()) > 0)
            {
                throw new BusinessException(String.format("%1$s已分配,不能删除", dictType.getDictName()));
            }
        }
        int count = dictTypeMapper.deleteDictTypeByIds(dictIds);
        if (count > 0)
        {
            //dictUtils.clearDictCache();
            for (SysDictType sysDictType : dictTypeList)
            {
                //清除数据字典对应缓存
                cacheUtils.remove(Constants.SYS_DICT_CACHE,Constants.SYS_DICT_KEY+sysDictType.getDictType());
            }
        }
        return count;
    }

    /**
     * 清空缓存数据
     */
    public void clearCache()
    {
        dictUtils.clearDictCache();
        initSysDictType();
    }

    /**
     * 新增保存字典类型信息
     * 
     * @param dictType 字典类型信息
     * @return 结果
     */
    @Override
    public int insertDictType(SysDictType dictType)
    {
        int row = dictTypeMapper.insertDictType(dictType);
        if (row > 0)
        {
            //dictUtils.clearDictCache();
        }
        return row;
    }

    /**
     * 修改保存字典类型信息
     * 
     * @param dictType 字典类型信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateDictType(SysDictType dictType)
    {
        SysDictType oldDict = dictTypeMapper.selectDictTypeById(dictType.getDictId());
        dictDataMapper.updateDictDataType(oldDict.getDictType(), dictType.getDictType());
        int row = dictTypeMapper.updateDictType(dictType);

        if (row > 0)
        {
            //dictUtils.clearDictCache();
            //删除老数据字典缓存
            cacheUtils.remove(Constants.SYS_DICT_CACHE,Constants.SYS_DICT_KEY+oldDict.getDictType());
            //放入缓存新数据数据
            List<SysDictData> dictDatas = dictDataMapper.selectDictDataByType(dictType.getDictType());
            dictUtils.setDictCache(dictType.getDictType(), dictDatas);
        }
        return row;
    }

    /**
     * 校验字典类型称是否唯一
     * 
     * @param dict 字典类型
     * @return 结果
     */
    @Override
    public String checkDictTypeUnique(SysDictType dict)
    {
        Long dictId = StringUtils.isNull(dict.getDictId()) ? -1L : dict.getDictId();
        SysDictType dictType = dictTypeMapper.checkDictTypeUnique(dict.getDictType());
        if (StringUtils.isNotNull(dictType) && dictType.getDictId().longValue() != dictId.longValue())
        {
            return UserConstants.DICT_TYPE_NOT_UNIQUE;
        }
        return UserConstants.DICT_TYPE_UNIQUE;
    }

    /**
     * 查询字典类型树
     * 
     * @param dictType 字典类型
     * @return 所有字典类型
     */
    public List<Ztree> selectDictTree(SysDictType dictType)
    {
        List<Ztree> ztrees = new ArrayList<Ztree>();
        List<SysDictType> dictList = dictTypeMapper.selectDictTypeList(dictType);
        for (SysDictType dict : dictList)
        {
            if (UserConstants.DICT_NORMAL.equals(dict.getStatus()))
            {
                Ztree ztree = new Ztree();
                ztree.setId(dict.getDictId());
                ztree.setName(transDictName(dict));
                ztree.setTitle(dict.getDictType());
                ztrees.add(ztree);
            }
        }
        return ztrees;
    }

    public String transDictName(SysDictType dictType)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("(" + dictType.getDictName() + ")");
        sb.append("&nbsp;&nbsp;&nbsp;" + dictType.getDictType());
        return sb.toString();
    }
}
