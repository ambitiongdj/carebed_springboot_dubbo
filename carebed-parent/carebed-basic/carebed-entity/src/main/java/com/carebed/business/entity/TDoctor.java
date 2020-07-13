package com.carebed.business.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.carebed.common.annotation.Excel;
import com.carebed.common.core.domain.BaseEntity;

/**
 * 医生对象 t_doctor
 * 
 * @author wjl
 * @date 2020-06-17
 */
public class TDoctor extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 医生姓名 */
    @Excel(name = "医生姓名")
    private String doctorName;

    /** 电话 */
    @Excel(name = "电话")
    private String phone;

    /** 医院名称 */
    @Excel(name = "医院名称")
    private String hospitalName;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String cardNo;

    /** 科室 */
    @Excel(name = "科室")
    private String department;

    /** 专业 */
    @Excel(name = "专业")
    private String major;

    /** 职称 */
    @Excel(name = "职称")
    private String title;

    /** 医生状态:0:失效:1:有效; */
    @Excel(name = "医生状态:0:失效:1:有效;")
    private String status;

    /**导出选中id**/
    private String checkBoxIds;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setDoctorName(String doctorName) 
    {
        this.doctorName = doctorName;
    }

    public String getDoctorName() 
    {
        return doctorName;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setHospitalName(String hospitalName) 
    {
        this.hospitalName = hospitalName;
    }

    public String getHospitalName() 
    {
        return hospitalName;
    }
    public void setCardNo(String cardNo) 
    {
        this.cardNo = cardNo;
    }

    public String getCardNo() 
    {
        return cardNo;
    }
    public void setDepartment(String department) 
    {
        this.department = department;
    }

    public String getDepartment() 
    {
        return department;
    }
    public void setMajor(String major) 
    {
        this.major = major;
    }

    public String getMajor() 
    {
        return major;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public String getCheckBoxIds() {
        return checkBoxIds;
    }

    public void setCheckBoxIds(String checkBoxIds) {
        this.checkBoxIds = checkBoxIds;
    }

    @Override
    public String toString() {
        return "TDoctor{" +
                "id=" + id +
                ", doctorName='" + doctorName + '\'' +
                ", phone='" + phone + '\'' +
                ", hospitalName='" + hospitalName + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", department='" + department + '\'' +
                ", major='" + major + '\'' +
                ", title='" + title + '\'' +
                ", status='" + status + '\'' +
                ", checkBoxIds='" + checkBoxIds + '\'' +
                '}';
    }
}
