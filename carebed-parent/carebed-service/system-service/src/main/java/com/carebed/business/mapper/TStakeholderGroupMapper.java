package com.carebed.business.mapper;

import java.util.List;
import java.util.Map;

import com.carebed.business.entity.TStakeholderGroup;
import com.carebed.business.entity.TStakeholderGroupVO;

/**
 * 干系组Mapper接口
 * 
 * @author wjl
 * @date 2020-06-17
 */
public interface TStakeholderGroupMapper 
{
    /**
     * 查询干系组
     * 
     * @param id 干系组ID
     * @return 干系组
     */
    public TStakeholderGroup selectTStakeholderGroupById(Long id);

    /**
     * 查询干系组列表
     * 
     * @param tStakeholderGroup 干系组
     * @return 干系组集合
     */
    public List<TStakeholderGroupVO> selectTStakeholderGroupList(TStakeholderGroup tStakeholderGroup);

    /**
     * 新增干系组
     * 
     * @param tStakeholderGroup 干系组
     * @return 结果
     */
    public int insertTStakeholderGroup(TStakeholderGroup tStakeholderGroup);

    /**
     * 修改干系组
     * 
     * @param tStakeholderGroup 干系组
     * @return 结果
     */
    public int updateTStakeholderGroup(TStakeholderGroup tStakeholderGroup);

    /**
     * 删除干系组
     * 
     * @param id 干系组ID
     * @return 结果
     */
    public int deleteTStakeholderGroupById(Long id);

    /**
     * 批量删除干系组
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTStakeholderGroupByIds(String[] ids);

    /**
     * @Description: 根据干系组主键id，批量更新状态
     * @Author: GDJ
     * @Date: 2020/06/17
     * @param params:
     * @return: int
     **/
    public int updateBatchStatus(Map<String,Object> params);

    public int checkGroupNameUnique(TStakeholderGroup stakeholderGroup);
}
