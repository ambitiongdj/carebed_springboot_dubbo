package com.carebed.business.service;

import com.carebed.business.entity.TOrder;
import com.carebed.business.entity.TOrderVo;

import java.util.List;

/**
 * 订单Service接口
 * 
 * @author wjl
 * @date 2020-06-17
 */
public interface ITOrderService 
{
    /**
     * 查询订单
     * 
     * @param id 订单ID
     * @return 订单
     */
    public TOrder selectTOrderById(Long id);

    /**
     * 查询订单列表
     * 
     * @param tOrder 订单
     * @return 订单集合
     */
    public List<TOrder> selectTOrderList(TOrder tOrder);

    public List<TOrderVo> findTOrderList(TOrderVo tOrder);

    /**
     * 新增订单
     * 
     * @param tOrder 订单
     * @return 结果
     */
    public int insertTOrder(TOrder tOrder);

    /**
     * 修改订单
     * 
     * @param tOrder 订单
     * @return 结果
     */
    public int updateTOrder(TOrder tOrder);

    /**
     * 批量删除订单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTOrderByIds(String ids);

    /**
     * 删除订单信息
     * 
     * @param id 订单ID
     * @return 结果
     */
    public int deleteTOrderById(Long id);
}
