package com.carebed.business.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.carebed.business.entity.TOrder;
import com.carebed.business.entity.TOrderVo;
import com.carebed.business.mapper.TOrderMapper;
import com.carebed.business.service.ITOrderService;
import com.carebed.common.core.text.Convert;
import com.carebed.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 订单Service业务层处理
 * 
 * @author wjl
 * @date 2020-06-17
 */
@Service
public class TOrderServiceImpl implements ITOrderService 
{
    @Autowired
    private TOrderMapper tOrderMapper;

    /**
     * 查询订单
     * 
     * @param id 订单ID
     * @return 订单
     */
    @Override
    public TOrder selectTOrderById(Long id)
    {
        return tOrderMapper.selectTOrderById(id);
    }

    /**
     * 查询订单列表
     * 
     * @param tOrder 订单
     * @return 订单
     */
    @Override
    public List<TOrder> selectTOrderList(TOrder tOrder)
    {
        return tOrderMapper.selectTOrderList(tOrder);
    }

    @Override
    public List<TOrderVo> findTOrderList(TOrderVo tOrder) {
        return tOrderMapper.findTOrderList(tOrder);
    }

    /**
     * 新增订单
     * 
     * @param tOrder 订单
     * @return 结果
     */
    @Override
    public int insertTOrder(TOrder tOrder)
    {
        tOrder.setCreateTime(DateUtils.getNowDate());
        return tOrderMapper.insertTOrder(tOrder);
    }

    /**
     * 修改订单
     * 
     * @param tOrder 订单
     * @return 结果
     */
    @Override
    public int updateTOrder(TOrder tOrder)
    {
        tOrder.setUpdateTime(DateUtils.getNowDate());
        return tOrderMapper.updateTOrder(tOrder);
    }

    /**
     * 删除订单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTOrderByIds(String ids)
    {
        return tOrderMapper.deleteTOrderByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除订单信息
     * 
     * @param id 订单ID
     * @return 结果
     */
    @Override
    public int deleteTOrderById(Long id)
    {
        return tOrderMapper.deleteTOrderById(id);
    }
}
