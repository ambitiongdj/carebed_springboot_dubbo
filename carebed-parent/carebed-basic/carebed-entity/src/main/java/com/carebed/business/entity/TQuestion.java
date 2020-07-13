package com.carebed.business.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.carebed.common.annotation.Excel;
import com.carebed.common.core.domain.BaseEntity;

/**
 * 问题对象 t_question
 * 
 * @author wjl
 * @date 2020-06-17
 */
public class TQuestion extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 陪护床号 */
    @Excel(name = "陪护床号")
    private String cotNo;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String phone;

    /** 问题意见 */
    @Excel(name = "问题意见")
    private String questionIdea;

    /** 处理状态:00待处理;01:已处理 */
    @Excel(name = "处理状态:00待处理;01:已处理")
    private String status;

    /** 处理方案 */
    @Excel(name = "处理方案")
    private String handlePlan;

    /** 陪护床id*/
    private Long cotId;
    /** 陪护床状态 */
    private String cotStatus;

    public Long getCotId() {
        return cotId;
    }

    public void setCotId(Long cotId) {
        this.cotId = cotId;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCotNo(String cotNo) 
    {
        this.cotNo = cotNo;
    }

    public String getCotNo() 
    {
        return cotNo;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setQuestionIdea(String questionIdea) 
    {
        this.questionIdea = questionIdea;
    }

    public String getQuestionIdea() 
    {
        return questionIdea;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setHandlePlan(String handlePlan) 
    {
        this.handlePlan = handlePlan;
    }

    public String getHandlePlan() 
    {
        return handlePlan;
    }

    public String getCotStatus() {
        return cotStatus;
    }

    public void setCotStatus(String cotStatus) {
        this.cotStatus = cotStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("cotNo", getCotNo())
            .append("address", getAddress())
            .append("phone", getPhone())
            .append("questionIdea", getQuestionIdea())
            .append("status", getStatus())
            .append("handlePlan", getHandlePlan())
            .append("createTime", getCreateTime())
            .append("cotId", getCotId())
            .append("cotStatus", getCotStatus())
            .toString();
    }
}
