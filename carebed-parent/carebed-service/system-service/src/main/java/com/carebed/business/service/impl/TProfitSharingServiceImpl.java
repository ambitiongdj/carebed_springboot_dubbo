package com.carebed.business.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.carebed.business.entity.TProfitSharing;
import com.carebed.business.entity.TProfitSharingCensusVo;
import com.carebed.business.entity.TProfitSharingVo;
import com.carebed.business.mapper.TProfitSharingMapper;
import com.carebed.business.service.ITProfitSharingService;
import com.carebed.common.core.page.TableDataInfo;
import com.carebed.common.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 分润Service业务层处理
 * 
 * @author wjl
 * @date 2020-06-17
 */
@Service
public class TProfitSharingServiceImpl implements ITProfitSharingService 
{
    @Autowired
    private TProfitSharingMapper tProfitSharingMapper;

    /**
     * 查询分润
     * 
     * @param id 分润ID
     * @return 分润
     */
    @Override
    public TProfitSharing selectTProfitSharingById(Long id)
    {
        return tProfitSharingMapper.selectTProfitSharingById(id);
    }

    /**
     * 查询分润列表
     * 
     * @param tProfitSharing 分润
     * @return 分润
     */
    @Override
    public List<TProfitSharingVo> selectTProfitSharingList(TProfitSharingVo tProfitSharingVo)
    {
        return tProfitSharingMapper.selectTProfitSharingList(tProfitSharingVo);
    }
    /**
     * 分页查询分润列表
     * 
     * @param tProfitSharing 分润
     * @return 分润
     */
    @Override
	public TableDataInfo selectTProfitSharingPageList(TProfitSharingVo tProfitSharingVo,
			Map<String, Object> map) {

		if (map.get("orderBy") != null
				&& StringUtils.isNotBlank(map.get("orderBy").toString())) {
			PageHelper.orderBy(map.get("orderBy").toString());
		}
		PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()),
				Integer.parseInt(map.get("pageSize").toString()));
		List<TProfitSharingVo> list = tProfitSharingMapper.selectTProfitSharingList(tProfitSharingVo);;
		PageInfo<TProfitSharingVo> pageInfo = new PageInfo<TProfitSharingVo>(list);
		TableDataInfo rspData = new TableDataInfo();
		rspData.setCode(0);
		rspData.setRows(pageInfo.getList());
		rspData.setTotal(pageInfo.getTotal());
		return rspData;
	}
    /**
     * 金额统计
     * 
     * @param tProfitSharing 分润
     * @return 分润
     */
	@Override
	public Map<String, Object> getMoneySum(TProfitSharingVo tProfitSharingVo) {
		// TODO Auto-generated method stub
		return tProfitSharingMapper.getMoneySum(tProfitSharingVo);
	}
	
	/**
     * 查询分润列表
     * 
     * @param tProfitSharing 分润
     * @return 分润
     */
    @Override
    public List<TProfitSharingCensusVo> selectTProfitSharingCensusList(TProfitSharingCensusVo tProfitSharingVo)
    {
        return tProfitSharingMapper.selectTProfitSharingCensusList(tProfitSharingVo);
    }
    /**
     * 分页查询分润列表
     * 
     * @param tProfitSharing 分润
     * @return 分润
     */
    @Override
	public TableDataInfo selectTProfitSharingCensusPageList(TProfitSharingCensusVo tProfitSharingVo,
			Map<String, Object> map) {

		if (map.get("orderBy") != null
				&& StringUtils.isNotBlank(map.get("orderBy").toString())) {
			PageHelper.orderBy(map.get("orderBy").toString());
		}
		PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()),
				Integer.parseInt(map.get("pageSize").toString()));
		List<TProfitSharingCensusVo> list = tProfitSharingMapper.selectTProfitSharingCensusList(tProfitSharingVo);;
		PageInfo<TProfitSharingCensusVo> pageInfo = new PageInfo<TProfitSharingCensusVo>(list);
		TableDataInfo rspData = new TableDataInfo();
		rspData.setCode(0);
		rspData.setRows(pageInfo.getList());
		rspData.setTotal(pageInfo.getTotal());
		return rspData;
	}
    
}
