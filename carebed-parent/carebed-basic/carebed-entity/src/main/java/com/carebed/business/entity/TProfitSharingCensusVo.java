package com.carebed.business.entity;

import java.util.Date;

import com.carebed.common.annotation.Excel;
import com.carebed.common.core.domain.BaseEntity;

/**
 * 分润对象 t_profit_sharing
 * 
 * @author wjl
 * @date 2020-06-17
 */
public class TProfitSharingCensusVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;
 

    /** 医生id */
    private Long doctorId;
    
    /** 医生姓名*/
    @Excel(name = "医生姓名")
    private String doctorName;
    
    /** 联系方式*/
    @Excel(name = "联系方式")
    private String phone;
    /** 医院名称*/
    @Excel(name = "医院名称")
    private String hospitalName;
    /** 科室及名称*/
    @Excel(name = "科室及职称")
    private String departmentTitle;

    /** 租用金额 */
    @Excel(name = "租用金额")
    private Long rentalAmt;


    /** 分润金额 */
    @Excel(name = "分润金额")
    private Double shareAmt;


    
    /** 开始时间 */
    private Date startTime;
    
    /**结束时间 */
    private Date endTime;
    
    private String checkBoxIds;
 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
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

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getDepartmentTitle() {
		return departmentTitle;
	}

	public void setDepartmentTitle(String departmentTitle) {
		this.departmentTitle = departmentTitle;
	}

	public Long getRentalAmt() {
		return rentalAmt;
	}

	public void setRentalAmt(Long rentalAmt) {
		this.rentalAmt = rentalAmt;
	}

	 

	public Double getShareAmt() {
		return shareAmt;
	}

	public void setShareAmt(Double shareAmt) {
		this.shareAmt = shareAmt;
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

	@Override
	public String toString() {
		return "TProfitSharingCensusVo [id=" + id + ", doctorId=" + doctorId
				+ ", doctorName=" + doctorName + ", phone=" + phone
				+ ", hospitalName=" + hospitalName + ", departmentTitle="
				+ departmentTitle + ", rentalAmt=" + rentalAmt
				 + ", shareAmt="
				+ shareAmt + ", startTime=" + startTime + ", endTime="
				+ endTime + ", checkBoxIds=" + checkBoxIds + "]";
	}

	 
}
