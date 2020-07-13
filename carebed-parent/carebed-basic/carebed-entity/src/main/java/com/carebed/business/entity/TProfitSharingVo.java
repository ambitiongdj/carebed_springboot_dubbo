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
public class TProfitSharingVo extends BaseEntity
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
    private Long doctorId;
    
    /** 医生姓名*/
    @Excel(name = "医生姓名")
    private String doctorName;
    
    /** 联系方式*/
    @Excel(name = "联系方式")
    private String phone;
    /** 医院名称*/
    private String hospitalName;
    /** 科室及名称*/
    @Excel(name = "科室及职称")
    private String departmentTitle;

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
    @Excel(name = "订单完成时间", width = 30, dateFormat = "yyyy-MM-dd hh:mm:ss")
    private Date leaseEndTime;

    /** 结算状态:00未结算;01已结算 */
    private String settleStatus;
    
    /** 开始时间 */
    private Date startTime;
    
    /**结束时间 */
    private Date endTime;
    
    private String checkBoxIds;

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

    public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDepartmentTitle() {
		return departmentTitle;
	}

	public void setDepartmentTitle(String departmentTitle) {
		this.departmentTitle = departmentTitle;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getCheckBoxIds() {
		return checkBoxIds;
	}

	public void setCheckBoxIds(String checkBoxIds) {
		this.checkBoxIds = checkBoxIds;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
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
