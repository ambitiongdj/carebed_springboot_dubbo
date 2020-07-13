package com.carebed.business.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.carebed.business.entity.TStakeholderGroup;
import com.carebed.business.entity.TStakeholderGroupVO;
import com.carebed.business.mapper.TCotMapper;
import com.carebed.business.mapper.TStakeholderGroupDoctorMapper;
import com.carebed.business.mapper.TStakeholderGroupMapper;
import com.carebed.business.service.ITStakeholderGroupService;
import com.carebed.common.constant.Constants;
import com.carebed.common.core.page.TableDataInfo;
import com.carebed.common.core.text.Convert;
import com.carebed.common.utils.DateUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 干系组Service业务层处理
 * 
 * @author wjl
 * @date 2020-06-17
 */
@Service
public class TStakeholderGroupServiceImpl implements ITStakeholderGroupService 
{
    @Autowired
    private TStakeholderGroupMapper tStakeholderGroupMapper;

    @Autowired
    private TStakeholderGroupDoctorMapper stakeholderGroupDoctorMapper;

    @Autowired
    private TCotMapper cotMapper;

    /**
     * 查询干系组
     * 
     * @param id 干系组ID
     * @return 干系组
     */
    @Override
    public TStakeholderGroup selectTStakeholderGroupById(Long id)
    {
        return tStakeholderGroupMapper.selectTStakeholderGroupById(id);
    }

    /**
     * 查询干系组列表
     * 
     * @param tStakeholderGroup 干系组
     * @return 干系组
     */
    @Override
    public List<TStakeholderGroupVO> selectTStakeholderGroupList(TStakeholderGroup tStakeholderGroup)
    {
        return tStakeholderGroupMapper.selectTStakeholderGroupList(tStakeholderGroup);
    }

    @Override
    public TableDataInfo selectTStakeholderGroupListPage(TStakeholderGroup tStakeholderGroup, Map<String, Object> map) {
        PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()),Integer.parseInt(map.get("pageSize").toString()));
        List<TStakeholderGroupVO> list = tStakeholderGroupMapper.selectTStakeholderGroupList(tStakeholderGroup);
        PageInfo<TStakeholderGroupVO> pageInfo = new PageInfo<TStakeholderGroupVO>(list);
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(0);
        rspData.setRows(pageInfo.getList());
        rspData.setTotal(pageInfo.getTotal());
        return rspData;
    }

    /**
     * 新增干系组
     * 
     * @param tStakeholderGroup 干系组
     * @return 结果
     */
    @Override
    public int insertTStakeholderGroup(TStakeholderGroup tStakeholderGroup)
    {
        Date nowDate = DateUtils.getNowDate();
        tStakeholderGroup.setCreateTime(nowDate);
        tStakeholderGroup.setUpdateTime(nowDate);
        tStakeholderGroup.setStatus(Constants.STAKEHOLDER_GROUP_STATUS1);
        return tStakeholderGroupMapper.insertTStakeholderGroup(tStakeholderGroup);
    }

    /**
     * 修改干系组
     * 
     * @param tStakeholderGroup 干系组
     * @return 结果
     */
    @Override
    public int updateTStakeholderGroup(TStakeholderGroup tStakeholderGroup)
    {
        tStakeholderGroup.setUpdateTime(DateUtils.getNowDate());
        return tStakeholderGroupMapper.updateTStakeholderGroup(tStakeholderGroup);
    }

    /**
     * 删除干系组对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteTStakeholderGroupByIds(String ids,Long operatorId)
    {
        Map<String,Object> params = new HashMap<>();
        params.put("ids",Convert.toStrArray(ids));
        params.put("updateBy",operatorId + "");
        params.put("updateTime",new Date());
        params.put("status",Constants.STAKEHOLDER_GROUP_STATUS0);
        //1.逻辑删除干系组记录
        tStakeholderGroupMapper.updateBatchStatus(params);
        //2.清空该干系组和医生的关系表
        stakeholderGroupDoctorMapper.deleteSGDBySGIds(Convert.toStrArray(ids));
        //3.清空该干系组关联的床位
        cotMapper.batchUnboundBySGIds(Convert.toStrArray(ids));
        return 1;
    }

    /**
     * 删除干系组信息
     * 
     * @param id 干系组ID
     * @return 结果
     */
    @Override
    public int deleteTStakeholderGroupById(Long id)
    {
        return tStakeholderGroupMapper.deleteTStakeholderGroupById(id);
    }

    @Override
    public String checkGroupNameUnique(TStakeholderGroup stakeholderGroup) {
        int existNum = tStakeholderGroupMapper.checkGroupNameUnique(stakeholderGroup);
        if(existNum>0){
            return Constants.STAKEHOLDER_GROUP_NAME_NOT_UNIQUE;
        }
        return Constants.STAKEHOLDER_GROUP_NAME_UNIQUE;
    }


}
