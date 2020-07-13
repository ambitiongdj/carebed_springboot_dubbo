package com.carebed.business.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.carebed.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 陪护床对象 t_cot
 * 
 * @author wjl
 * @date 2020-06-17
 */
public class TCotVo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 陪护床号 */
    @Excel(name = "陪护床号")
    private String cotNo;

    /** 会员姓名(使用者姓名,微信昵称) */
    @Excel(name = "会员姓名")
    private String memberName;

    /** 干系组Id */
    private Long stakeholderGroupId;
    
    /** 干系组名称 */
    @Excel(name = " 干系人组名")
    private String groupName;


    /** 状态:00:维护中;01:闲置中:02:使用中;03:保修中; */
    @Excel(name = "状态", readConverterExp = "00=维护中,01=闲置中,02=使用中,03=保修中")
    private String status;
    /** 删除状态:0:无效;1有效 */
    private String delSta;
    
    private String num;
    /**导出选中id**/
    private String checkBoxIds;
    
    /** 搜索值 */
    private String searchValue;

    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** 备注 */
    private String remark;

    /** 请求参数 */
    private Map<String, Object> params;


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

    public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getDelSta() {
		return delSta;
	}

	public void setDelSta(String delSta) {
		this.delSta = delSta;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public String getCheckBoxIds() {
		return checkBoxIds;
	}

	public void setCheckBoxIds(String checkBoxIds) {
		this.checkBoxIds = checkBoxIds;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("cotNo", getCotNo())
            .append("memberName", getMemberName())
            .append("stakeholderGroupId", getStakeholderGroupId())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .toString();
    }
}
