package com.carebed.business.entity;

import com.carebed.common.annotation.Excel;
import com.carebed.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 分润对象 t_profit_sharing
 * 
 * @author wjl
 * @date 2020-06-17
 */
public class TProfitSharing extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String orderNo;

    /** 陪护床号 */
    @Excel(name = "陪护床号")
    private String cotNo;

    /** 医生id */
    @Excel(name = "医生id")
    private Long doctorId;

    /** 租用金额 */
    @Excel(name = "租用金额")
    private Long rentalAmt;

    /** 分润比例 */
    @Excel(name = "分润比例")
    private Double shareProportion;

    /** 分润金额 */
    @Excel(name = "分润金额")
    private Double shareAmt;

    /** 订单完成时间 */
    @Excel(name = "订单完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date leaseEndTime;

    /** 结算状态:00未结算;01已结算 */
    @Excel(name = "结算状态:00未结算;01已结算")
    private String settleStatus;

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
    public void setDoctorId(Long doctorId) 
    {
        this.doctorId = doctorId;
    }

    public Long getDoctorId() 
    {
        return doctorId;
    }
    public void setRentalAmt(Long rentalAmt) 
    {
        this.rentalAmt = rentalAmt;
    }

    public Long getRentalAmt() 
    {
        return rentalAmt;
    }
    public void setShareProportion(Double shareProportion) 
    {
        this.shareProportion = shareProportion;
    }

    public Double getShareProportion() 
    {
        return shareProportion;
    }
    public void setShareAmt(Double shareAmt) 
    {
        this.shareAmt = shareAmt;
    }

    public Double getShareAmt() 
    {
        return shareAmt;
    }
    public void setLeaseEndTime(Date leaseEndTime) 
    {
        this.leaseEndTime = leaseEndTime;
    }

    public Date getLeaseEndTime() 
    {
        return leaseEndTime;
    }
    public void setSettleStatus(String settleStatus) 
    {
        this.settleStatus = settleStatus;
    }

    public String getSettleStatus() 
    {
        return settleStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderNo", getOrderNo())
            .append("cotNo", getCotNo())
            .append("doctorId", getDoctorId())
            .append("rentalAmt", getRentalAmt())
            .append("shareProportion", getShareProportion())
            .append("shareAmt", getShareAmt())
            .append("leaseEndTime", getLeaseEndTime())
            .append("settleStatus", getSettleStatus())
            .toString();
    }
}
