package com.carebed.manage.mapper;

import com.carebed.manage.entity.SysDept;
import com.carebed.manage.entity.SysDeptVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 部门管理 数据层
 * 
 * @author ruoyi
 */
public interface SysDeptMapper
{
    /**
     * 查询部门人数
     * 
     * @param dept 部门信息
     * @return 结果
     */
    public int selectDeptCount(SysDept dept);
    
    /**
     * 查询部门Vo
     * 
     * @param dept 部门信息
     * @return 结果
     */
    public List<SysDeptVo> selectDeptVoList(SysDeptVo dept);
    
    

    /**
     * 查询部门是否存在用户
     * 
     * @param deptId 部门ID
     * @return 结果
     */
    public int checkDeptExistUser(Long deptId);

    /**
     * 查询部门管理数据
     * 
     * @param dept 部门信息
     * @return 部门信息集合
     */
    public List<SysDept> selectDeptList(SysDept dept);

    /**
     * 删除部门管理信息
     * 
     * @param deptId 部门ID
     * @return 结果
     */
    public int deleteDeptById(Long deptId);

    /**
     * 新增部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    public int insertDept(SysDept dept);

    /**
     * 修改部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    public int updateDept(SysDept dept);

    /**
     * 修改子元素关系
     * 
     * @param depts 子元素
     * @return 结果
     */
    public int updateDeptChildren(@Param("depts") List<SysDept> depts);

    /**
     * 根据部门ID查询信息
     * 
     * @param deptId 部门ID
     * @return 部门信息
     */
    public SysDept selectDeptById(Long deptId);

    /**
     * 根据机构编码查询信息
     * @param deptCode
     * @return
     */
    public SysDept selectDeptByDeptCode(String deptCode);

    /**
     * 校验部门名称是否唯一
     * 
     * @param deptName 部门名称
     * @param parentId 父部门ID
     * @return 结果
     */
    public SysDept checkDeptNameUnique(@Param("deptName") String deptName, @Param("parentId") Long parentId);

    /**
     * 根据角色ID查询部门
     *
     * @param roleId 角色ID
     * @return 部门列表
     */
    public List<String> selectRoleDeptTree(Long roleId);

    /**
     * 修改所在部门的父级部门状态
     * 
     * @param dept 部门
     */
    public void updateDeptStatus(SysDept dept);

    /**
     * 根据ID查询所有子部门
     * @param deptId 部门ID
     * @return 部门列表
     */
    public List<SysDept> selectChildrenDeptById(Long deptId);

    /**
     * 根据父ID查询部门编号
     * @param dept
     * @return Map
     * @Author wzhiq
     * Create Date:2020年4月3日
     */
    public Map<String,Object> getDeptCountByParentId(SysDept dept);

    /**
     * 根据条件查询部门数量
     * @param map
     * @return
     */
    public int getDeptCountByCondition(Map<String, Object> map);

    public SysDept findDeptByDeptCode(String deptCode);

    List<SysDept> getOrderTreeData(Map<String, Object> param);
    /**
     * 查询所有部门及负责人数据
     *
     * @return 部门及负责人集合
     */
    public List<SysDept> getDeptAndManagerList();

    /**
     * 验证部门编号的唯一性
     * @return
     */
    public int checkDeptCodeUnique(SysDept dept);
}
