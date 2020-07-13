package com.carebed.business.mapper;

import java.util.List;
import java.util.Map;

import com.carebed.business.entity.TProfitSharing;
import com.carebed.business.entity.TProfitSharingCensusVo;
import com.carebed.business.entity.TProfitSharingVo;

/**
 * 分润Mapper接口
 * 
 * @author wjl
 * @date 2020-06-17
 */
public interface TProfitSharingMapper 
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
     * 分润金额统计
     * 
     * @param tProfitSharing 分润
     * @return 分润集合
     */
    public Map<String, Object> getMoneySum(TProfitSharingVo tProfitSharingVo);
    
    /**
     * 查询分润统计列表
     * 
     * @param tProfitSharing 分润
     * @return 分润集合
     */
    public List<TProfitSharingCensusVo> selectTProfitSharingCensusList(TProfitSharingCensusVo tProfitSharingVo);

}
