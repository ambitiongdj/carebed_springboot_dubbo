package com.carebed.business.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.carebed.common.annotation.Excel;
import com.carebed.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 干系组和医生关联对象 t_stakeholder_group_doctor
 * 
 * @author wjl
 * @date 2020-06-17
 */
public class TStakeholderGroupDoctor extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 医生id */
    @Excel(name = "医生id")
    private Long doctorId;

    /** 干系组id */
    @Excel(name = "干系组id")
    private Long stakeholderGroupId;

    /** 分润比例(%) */
    @Excel(name = "分润比例(%)")
    private Double shareProportion;

    private Date createTime;

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

    public Long getStakeholderGroupId() {
        return stakeholderGroupId;
    }

    public void setStakeholderGroupId(Long stakeholderGroupId) {
        this.stakeholderGroupId = stakeholderGroupId;
    }

    public Double getShareProportion() {
        return shareProportion;
    }

    public void setShareProportion(Double shareProportion) {
        this.shareProportion = shareProportion;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "TStakeholderGroupDoctor{" +
                "id=" + id +
                ", doctorId=" + doctorId +
                ", stakeholderGroupId=" + stakeholderGroupId +
                ", shareProportion=" + shareProportion +
                ", createTime=" + createTime +
                '}';
    }
}
