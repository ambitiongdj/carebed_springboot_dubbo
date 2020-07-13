package com.carebed.business.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.carebed.common.annotation.Excel;
import com.carebed.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 订单对象 t_order
 * 
 * @author wjl
 * @date 2020-06-17
 */
public class TOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String orderNo;

    /** 陪护床号 */
    @Excel(name = "陪护床号")
    private String cotNo;

    /** 微信用户关联表中的id */
    @Excel(name = "微信用户关联表中的id")
    private Long wechatId;

    /** 租用开始时间 */
    @Excel(name = "租用开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date leaseStartTime;

    /** 租用结束时间 */
    @Excel(name = "租用结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date leaseEndTime;

    /** 租用耗时(小时) */
    @Excel(name = "租用耗时(小时)")
    private Long rentalTime;

    /** 租用押金（元） */
    @Excel(name = "租用押金", readConverterExp = "元=")
    private Long rentalDeposit;

    /** 租用金额(元) */
    @Excel(name = "租用金额(元)")
    private Long rentalAmt;

    /** 退回金额(元) */
    @Excel(name = "退回金额(元)")
    private Long returnAmt;

    /** 00:租用中;01:完成; */
    @Excel(name = "00:租用中;01:完成;")
    private String status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOrderNo(String orderNo) 
    {
        this.orderNo = orderNo;
    }

    public String getOrderNo() 
    {
        return orderNo;
    }
    public void setCotNo(String cotNo) 
    {
        this.cotNo = cotNo;
    }

    public String getCotNo() 
    {
        return cotNo;
    }
    public void setWechatId(Long wechatId) 
    {
        this.wechatId = wechatId;
    }

    public Long getWechatId() 
    {
        return wechatId;
    }
    public void setLeaseStartTime(Date leaseStartTime) 
    {
        this.leaseStartTime = leaseStartTime;
    }

    public Date getLeaseStartTime() 
    {
        return leaseStartTime;
    }
    public void setLeaseEndTime(Date leaseEndTime) 
    {
        this.leaseEndTime = leaseEndTime;
    }

    public Date getLeaseEndTime() 
    {
        return leaseEndTime;
    }
    public void setRentalTime(Long rentalTime) 
    {
        this.rentalTime = rentalTime;
    }

    public Long getRentalTime() 
    {
        return rentalTime;
    }
    public void setRentalDeposit(Long rentalDeposit) 
    {
        this.rentalDeposit = rentalDeposit;
    }

    public Long getRentalDeposit() 
    {
        return rentalDeposit;
    }
    public void setRentalAmt(Long rentalAmt) 
    {
        this.rentalAmt = rentalAmt;
    }

    public Long getRentalAmt() 
    {
        return rentalAmt;
    }
    public void setReturnAmt(Long returnAmt) 
    {
        this.returnAmt = returnAmt;
    }

    public Long getReturnAmt() 
    {
        return returnAmt;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderNo", getOrderNo())
            .append("cotNo", getCotNo())
            .append("wechatId", getWechatId())
            .append("leaseStartTime", getLeaseStartTime())
            .append("leaseEndTime", getLeaseEndTime())
            .append("rentalTime", getRentalTime())
            .append("rentalDeposit", getRentalDeposit())
            .append("rentalAmt", getRentalAmt())
            .append("returnAmt", getReturnAmt())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
