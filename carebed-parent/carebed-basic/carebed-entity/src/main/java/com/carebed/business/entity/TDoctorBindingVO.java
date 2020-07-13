package com.carebed.business.entity;

import com.carebed.common.annotation.Excel;
import com.carebed.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 医生对象 t_doctor
 * 
 * @author wjl
 * @date 2020-06-17
 */
public class TDoctorBindingVO extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 干系组和医生关系表id */
    private Long stakeholderGroupDoctorId;

    /** 医生id */
    private Long doctorId;

    /** 干洗组组名 */
    @Excel(name = "组名称")
    private String groupName;

    /** 医生姓名 */
    @Excel(name = "医生姓名")
    private String doctorName;

    /** 电话 */
    @Excel(name = "联系方式")
    private String phone;

    /** 医院名称 */
    @Excel(name = "医院名称")
    private String hospitalName;

    @Excel(name = "科室及职称")
    private String departmentAndTitle;

    @Excel(name = "分润比例(%)")
    private Double shareProportion;

    @Excel(name = "绑定时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date boundTime;

    /** 身份证号 */
    private String cardNo;

    /** 科室 */
    private String department;

    /** 专业 */
    private String major;

    /** 职称 */
    private String title;

    /** 医生状态:0:失效:1:有效; */
    private String status;

    public Long getStakeholderGroupDoctorId() {
        return stakeholderGroupDoctorId;
    }

    public void setStakeholderGroupDoctorId(Long stakeholderGroupDoctorId) {
        this.stakeholderGroupDoctorId = stakeholderGroupDoctorId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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

    public String getDepartmentAndTitle() {
        return departmentAndTitle;
    }

    public void setDepartmentAndTitle(String departmentAndTitle) {
        this.departmentAndTitle = departmentAndTitle;
    }

    public Double getShareProportion() {
        return shareProportion;
    }

    public void setShareProportion(Double shareProportion) {
        this.shareProportion = shareProportion;
    }

    public Date getBoundTime() {
        return boundTime;
    }

    public void setBoundTime(Date boundTime) {
        this.boundTime = boundTime;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TDoctorBindingVO{" +
                "stakeholderGroupDoctorId=" + stakeholderGroupDoctorId +
                ", doctorId=" + doctorId +
                ", groupName='" + groupName + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", phone='" + phone + '\'' +
                ", hospitalName='" + hospitalName + '\'' +
                ", departmentAndTitle='" + departmentAndTitle + '\'' +
                ", shareProportion=" + shareProportion +
                ", boundTime=" + boundTime +
                ", cardNo='" + cardNo + '\'' +
                ", department='" + department + '\'' +
                ", major='" + major + '\'' +
                ", title='" + title + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
