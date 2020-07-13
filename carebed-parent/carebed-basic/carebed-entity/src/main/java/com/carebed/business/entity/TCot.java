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
public class TCot extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 陪护床号 */
    @Excel(name = "陪护床号")
    private String cotNo;

    /** 会员姓名(使用者姓名,微信昵称) */
    @Excel(name = "会员姓名(使用者姓名,微信昵称)")
    private String memberName;

    /** 干系组Id */
    @Excel(name = "干系组Id")
    private Long stakeholderGroupId;

    /** 状态:00:维护中;01:闲置中:02:使用中;03:保修中; */
    @Excel(name = "状态:00:维护中;01:闲置中:02:使用中;03:保修中;")
    private String status;
    /** 删除状态:0:无效;1:有效 */
    private String delSta;

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
    public void setCotNo(String cotNo) 
    {
        this.cotNo = cotNo;
    }

    public String getCotNo() 
    {
        return cotNo;
    }
    public void setMemberName(String memberName) 
    {
        this.memberName = memberName;
    }

    public String getMemberName() 
    {
        return memberName;
    }
    public void setStakeholderGroupId(Long stakeholderGroupId) 
    {
        this.stakeholderGroupId = stakeholderGroupId;
    }

    public Long getStakeholderGroupId() 
    {
        return stakeholderGroupId;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public String getDelSta() {
		return delSta;
	}

	public void setDelSta(String delSta) {
		this.delSta = delSta;
	}

    public String getCheckBoxIds() {
        return checkBoxIds;
    }

    public void setCheckBoxIds(String checkBoxIds) {
        this.checkBoxIds = checkBoxIds;
    }

    @Override
    public String toString() {
        return "TCot{" +
                "id=" + id +
                ", cotNo='" + cotNo + '\'' +
                ", memberName='" + memberName + '\'' +
                ", stakeholderGroupId=" + stakeholderGroupId +
                ", status='" + status + '\'' +
                ", delSta='" + delSta + '\'' +
                ", checkBoxIds='" + checkBoxIds + '\'' +
                '}';
    }
}
