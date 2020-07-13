package com.carebed.manage.entity;

import com.carebed.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 部门 sys_deptvo
 * 
 * @author iysk
 */
public class SysDeptVo  extends BaseEntity
{
    private static final long serialVersionUID = 1L;
    private Long deptId;
     /** 父部门ID */
     private Long parentId;

     /** 部门名称 */
     private String deptName;

     /** 显示顺序 */
     private String orderNum;

     /** 删除标志（0代表存在 2代表删除） */
     private String delFlag;

     /** 父部门名称 */
     private String parentName;
     /**机构编码*/
     private String deptCode;
     /**部门成立时间*/
     private Date businessTime;
     /**城市*/
     private String city;
     
     /**部门创建时间*/
     private Date createTime;
     /**部门负责人*/
     private String userName;
     
     /**岗位职级*/
     private String dictLabel;
     
     /**岗位职级码*/
     private String levelCode;

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public Date getBusinessTime() {
		return businessTime;
	}

	public void setBusinessTime(Date businessTime) {
		this.businessTime = businessTime;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDictLabel() {
		return dictLabel;
	}

	public void setDictLabel(String dictLabel) {
		this.dictLabel = dictLabel;
	}

	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}
     
}
