package com.carebed.business.service;

import java.util.List;
import java.util.Map;

import com.carebed.business.entity.TProfitSharing;
import com.carebed.business.entity.TProfitSharingCensusVo;
import com.carebed.business.entity.TProfitSharingVo;
import com.carebed.common.core.page.TableDataInfo;

/**
 * 分润Service接口
 * 
 * @author wjl
 * @date 2020-06-17
 */
public interface ITProfitSharingService 
{
    /**
     * 查询分润
     * 
     * @param id 分润ID
     * @return 分润
     */
    public TProfitSharing selectTProfitSharingById(Long id);

    /**
     * 查询分润列表
     * 
     * @param tProfitSharing 分润
     * @return 分润集合
     */
    public List<TProfitSharingVo> selectTProfitSharingList(TProfitSharingVo tProfitSharingVo);

    /**
     * 分页查询润列表
     * 
     * @param tProfitSharing 分润
      * @return 分润集合
     */
    public TableDataInfo selectTProfitSharingPageList(TProfitSharingVo tProfitSharingVo,
			Map<String, Object> map);
    
    /**
     * 金额统计
     * 
     * @param tProfitSharingVo 
     * @return 分润
     */
    public Map<String, Object> getMoneySum(TProfitSharingVo tProfitSharingVo);
    
    /**
     * 查询分润统计列表
     * 
     * @param tProfitSharing 分润
     * @return 分润集合
     */
    public List<TProfitSharingCensusVo> selectTProfitSharingCensusList(TProfitSharingCensusVo tProfitSharingVo);

    /**
     * 分页查询润统计列表
     * 
     * @param tProfitSharing 分润
      * @return 分润集合
     */
    public TableDataInfo selectTProfitSharingCensusPageList(TProfitSharingCensusVo tProfitSharingVo,
			Map<String, Object> map);
    
    
}
