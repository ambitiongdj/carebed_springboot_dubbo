package com.carebed.business.service;

import java.util.List;
import com.carebed.business.entity.TStakeholderGroupDoctor;

/**
 * 干系组和医生关联Service接口
 * 
 * @author wjl
 * @date 2020-06-17
 */
public interface ITStakeholderGroupDoctorService 
{
    /**
     * 查询干系组和医生关联
     * 
     * @param id 干系组和医生关联ID
     * @return 干系组和医生关联
     */
    public TStakeholderGroupDoctor selectTStakeholderGroupDoctorById(Long id);

    /**
     * 查询干系组和医生关联列表
     * 
     * @param tStakeholderGroupDoctor 干系组和医生关联
     * @return 干系组和医生关联集合
     */
    public List<TStakeholderGroupDoctor> selectTStakeholderGroupDoctorList(TStakeholderGroupDoctor tStakeholderGroupDoctor);

    /**
     * 新增干系组和医生关联
     * 
     * @param tStakeholderGroupDoctor 干系组和医生关联
     * @return 结果
     */
    public int insertTStakeholderGroupDoctor(TStakeholderGroupDoctor tStakeholderGroupDoctor);

    /**
     * 修改干系组和医生关联
     * 
     * @param tStakeholderGroupDoctor 干系组和医生关联
     * @return 结果
     */
    public int updateTStakeholderGroupDoctor(TStakeholderGroupDoctor tStakeholderGroupDoctor);

    /**
     * 批量删除干系组和医生关联
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTStakeholderGroupDoctorByIds(String ids);

    /**
     * 删除干系组和医生关联信息
     * 
     * @param id 干系组和医生关联ID
     * @return 结果
     */
    public int deleteTStakeholderGroupDoctorById(Long id);

    /**
     * @Description: 插入医生和干系组的关系
     * @Author: GDJ
     * @Date: 2020/06/18
     * @param sgId:
     * @param doctorIds:
     * @return: int
     **/
    public int insertTStakeholderGroupDoctors(Long sgId, String doctorIds);

    /**
     * @Description: 根据实体解除干系组和医生关系
     * @Author: GDJ
     * @Date: 2020/06/18
     * @param tStakeholderGroupDoctor:
     * @return: int
     **/
    public int deleteTStakeholderGroup(TStakeholderGroupDoctor tStakeholderGroupDoctor);

    /**
     * @Description: 根据条件批量解除干系组和医生的关系
     * @Author: GDJ
     * @Date: 2020/06/18
     * @param sgId:
     * @param doctorIds:
     * @return: int
     **/
    public int deleteTStakeholderGroupBatch(Long sgId, String doctorIds);
}
