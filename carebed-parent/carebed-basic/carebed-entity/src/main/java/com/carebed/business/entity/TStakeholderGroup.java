package com.carebed.business.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.carebed.common.annotation.Excel;
import com.carebed.common.core.domain.BaseEntity;

/**
 * 干系组对象 t_stakeholder_group
 * 
 * @author wjl
 * @date 2020-06-17
 */
public class TStakeholderGroup extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 组名称 */
    @Excel(name = "组名称")
    private String groupName;

    /** 状态:0:无效;1:有效 */
    @Excel(name = "状态:0:无效;1:有效")
    private String status;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "TStakeholderGroup{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", status='" + status + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
