package com.carebed.business.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.carebed.business.entity.TStakeholderGroup;
import com.carebed.business.entity.TStakeholderGroupDoctor;
import com.carebed.business.mapper.TStakeholderGroupDoctorMapper;
import com.carebed.business.mapper.TStakeholderGroupMapper;
import com.carebed.business.service.ITStakeholderGroupDoctorService;
import com.carebed.common.core.text.Convert;
import com.carebed.common.utils.StringUtils;
import com.carebed.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 干系组和医生关联Service业务层处理
 * 
 * @author wjl
 * @date 2020-06-17
 */
@Service
public class TStakeholderGroupDoctorServiceImpl implements ITStakeholderGroupDoctorService 
{
    @Autowired
    private TStakeholderGroupDoctorMapper tStakeholderGroupDoctorMapper;

    @Autowired
    private TStakeholderGroupMapper stakeholderGroupMapper;

    /**
     * 查询干系组和医生关联
     * 
     * @param id 干系组和医生关联ID
     * @return 干系组和医生关联
     */
    @Override
    public TStakeholderGroupDoctor selectTStakeholderGroupDoctorById(Long id)
    {
        return tStakeholderGroupDoctorMapper.selectTStakeholderGroupDoctorById(id);
    }

    /**
     * 查询干系组和医生关联列表
     * 
     * @param tStakeholderGroupDoctor 干系组和医生关联
     * @return 干系组和医生关联
     */
    @Override
    public List<TStakeholderGroupDoctor> selectTStakeholderGroupDoctorList(TStakeholderGroupDoctor tStakeholderGroupDoctor)
    {
        return tStakeholderGroupDoctorMapper.selectTStakeholderGroupDoctorList(tStakeholderGroupDoctor);
    }

    /**
     * 新增干系组和医生关联
     * 
     * @param tStakeholderGroupDoctor 干系组和医生关联
     * @return 结果
     */
    @Override
    public int insertTStakeholderGroupDoctor(TStakeholderGroupDoctor tStakeholderGroupDoctor)
    {
        return tStakeholderGroupDoctorMapper.insertTStakeholderGroupDoctor(tStakeholderGroupDoctor);
    }

    /**
     * 修改干系组和医生关联
     * 
     * @param tStakeholderGroupDoctor 干系组和医生关联
     * @return 结果
     */
    @Override
    public int updateTStakeholderGroupDoctor(TStakeholderGroupDoctor tStakeholderGroupDoctor)
    {
        return tStakeholderGroupDoctorMapper.updateTStakeholderGroupDoctor(tStakeholderGroupDoctor);
    }

    /**
     * 删除干系组和医生关联对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTStakeholderGroupDoctorByIds(String ids)
    {
        return tStakeholderGroupDoctorMapper.deleteTStakeholderGroupDoctorByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除干系组和医生关联信息
     * 
     * @param id 干系组和医生关联ID
     * @return 结果
     */
    @Override
    public int deleteTStakeholderGroupDoctorById(Long id)
    {
        return tStakeholderGroupDoctorMapper.deleteTStakeholderGroupDoctorById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertTStakeholderGroupDoctors(Long sgId, String doctorIds) {
        String[] doctorIdArray = Convert.toStrArray(doctorIds);
        //1.查询干系组是否存在
        TStakeholderGroup tStakeholderGroup = stakeholderGroupMapper.selectTStakeholderGroupById(sgId);
        if(StringUtils.isNull(tStakeholderGroup)){
            throw new BusinessException("该干系组已不存在请核实后在添加");
        }
        //1.查询关系表中选中绑定的医生是否已经绑定了干系组
        int existNum = tStakeholderGroupDoctorMapper.findSGDByDoctorIds(doctorIdArray);
        if(existNum > 0){
            throw new BusinessException("选中医生中存在已经绑定干系组");
        }
        //2.批量插入关系
        Map<String,Object> params = new HashMap<>();
        params.put("doctorIdArray",doctorIdArray);
        TStakeholderGroupDoctor stakeholderGroupDoctor = new TStakeholderGroupDoctor();
        stakeholderGroupDoctor.setCreateTime(new Date());
        stakeholderGroupDoctor.setStakeholderGroupId(sgId);
        stakeholderGroupDoctor.setParams(params);
        tStakeholderGroupDoctorMapper.insertTStakeholderGroupDoctorBatch(stakeholderGroupDoctor);
        return 1;
    }

    @Override
    public int deleteTStakeholderGroup(TStakeholderGroupDoctor tStakeholderGroupDoctor) {
        return tStakeholderGroupDoctorMapper.deleteTStakeholderGroup(tStakeholderGroupDoctor);
    }

    @Override
    public int deleteTStakeholderGroupBatch(Long sgId, String doctorIds) {
        Map<String,Object> params = new HashMap<>();
        params.put("stakeholderGroupId",sgId);
        params.put("doctorIds",doctorIds);
        tStakeholderGroupDoctorMapper.deleteTStakeholderGroupBatch(params);
        return 1;
    }
}
