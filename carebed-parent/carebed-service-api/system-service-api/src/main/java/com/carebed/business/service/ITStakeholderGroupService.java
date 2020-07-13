package com.carebed.business.service;

import java.util.List;
import java.util.Map;

import com.carebed.business.entity.TStakeholderGroup;
import com.carebed.business.entity.TStakeholderGroupVO;
import com.carebed.common.core.page.TableDataInfo;

/**
 * 干系组Service接口
 * 
 * @author wjl
 * @date 2020-06-17
 */
public interface ITStakeholderGroupService 
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
     * @Description: 干系组列表分页
     * @Author: GDJ
     * @Date: 2020/06/17
     * @param tStakeholderGroup:
     * @param map:
     * @return: com.carebed.common.core.page.TableDataInfo
     **/
    public TableDataInfo selectTStakeholderGroupListPage(TStakeholderGroup tStakeholderGroup, Map<String, Object> map);

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
     * 批量删除干系组
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTStakeholderGroupByIds(String ids,Long operatorId);

    /**
     * 删除干系组信息
     * 
     * @param id 干系组ID
     * @return 结果
     */
    public int deleteTStakeholderGroupById(Long id);

    /**
     * @Description: 校验组名称是否重复
     * @Author: GDJ
     * @Date: 2020/06/19
     * @param stakeholderGroup:
     * @return: java.lang.String
     **/
    public String checkGroupNameUnique(TStakeholderGroup stakeholderGroup);
}
