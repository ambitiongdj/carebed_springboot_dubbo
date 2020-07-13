package com.carebed.business.entity;

import com.carebed.common.annotation.Excel;
import com.carebed.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 陪护床对象 t_cot
 * 
 * @author wjl
 * @date 2020-06-17
 */
public class TCotBindingVO extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 陪护床表主键id */
    private Long cotId;

    /** 干系组Id */
    private Long stakeholderGroupId;

    /** 干洗组组名 */
    @Excel(name = "组名称")
    private String groupName;

    /** 陪护床号 */
    @Excel(name = "陪护床号")
    private String cotNo;

    /** 会员姓名(使用者姓名,微信昵称) */
    private String memberName;

    /** 状态:00:维护中;01:闲置中:02:使用中;03:保修中; */
    private String cotStatus;

    @Excel(name = "状态:")
    private String cotStatusName;

    /** 删除状态:0:无效;1:有效 */
    private String delSta;

    public Long getCotId() {
        return cotId;
    }

    public void setCotId(Long cotId) {
        this.cotId = cotId;
    }

    public Long getStakeholderGroupId() {
        return stakeholderGroupId;
    }

    public void setStakeholderGroupId(Long stakeholderGroupId) {
        this.stakeholderGroupId = stakeholderGroupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCotNo() {
        return cotNo;
    }

    public void setCotNo(String cotNo) {
        this.cotNo = cotNo;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getCotStatus() {
        return cotStatus;
    }

    public void setCotStatus(String cotStatus) {
        this.cotStatus = cotStatus;
    }

    public String getCotStatusName() {
        return cotStatusName;
    }

    public void setCotStatusName(String cotStatusName) {
        this.cotStatusName = cotStatusName;
    }

    public String getDelSta() {
        return delSta;
    }

    public void setDelSta(String delSta) {
        this.delSta = delSta;
    }

    @Override
    public String toString() {
        return "TCotBindingVO{" +
                "cotId=" + cotId +
                ", stakeholderGroupId=" + stakeholderGroupId +
                ", groupName='" + groupName + '\'' +
                ", cotNo='" + cotNo + '\'' +
                ", memberName='" + memberName + '\'' +
                ", cotStatus='" + cotStatus + '\'' +
                ", cotStatusName='" + cotStatusName + '\'' +
                ", delSta='" + delSta + '\'' +
                '}';
    }
}
