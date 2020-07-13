package com.carebed.web.controller.manage;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.carebed.common.annotation.Log;
import com.carebed.common.constant.UserConstants;
import com.carebed.common.core.controller.BaseController;
import com.carebed.common.core.domain.AjaxResult;
import com.carebed.common.core.domain.Ztree;
import com.carebed.common.core.page.TableDataInfo;
import com.carebed.common.enums.BusinessType;
import com.carebed.common.enums.PositionEnum;
import com.carebed.common.enums.PositionLeaderLevelEnum;
import com.carebed.common.utils.EnumUtil;
import com.carebed.common.utils.StringUtils;
import com.carebed.exception.BusinessException;
import com.carebed.manage.entity.SysDept;
import com.carebed.manage.entity.SysDeptVo;
import com.carebed.manage.entity.SysRole;
import com.carebed.manage.entity.SysUser;
import com.carebed.manage.service.ISysDeptService;
import com.carebed.manage.service.ISysUserService;
import com.carebed.web.shiro.utils.ShiroUtils;

/**
 * 部门信息
 * 
 * @author iysk
 */
@Controller
@RequestMapping("/system/dept")
public class SysDeptController extends BaseController
{
    private String prefix = "system/dept";

    @Reference
    private ISysDeptService deptService;

    @Reference
    private ISysUserService userService;

    @RequiresPermissions("system:dept:view")
    @GetMapping()
    public String dept()
    {
    	
        return prefix + "/dept";
    }

    @RequiresPermissions("system:dept:list")
    @PostMapping("/list")
    @ResponseBody
    public List<SysDeptVo> list(SysDeptVo dept)
    {
    	Long deptId = ShiroUtils.getSysUser().getDeptId();
    	dept.setDeptId(deptId);
        List<SysDeptVo> deptList = deptService.selectDeptVoList(dept);
        return deptList;
    }

    /**
     * 新增部门
     */
    @GetMapping("/add/{deptCode}/{type}")
    public String add(@PathVariable("deptCode") String deptCode,@PathVariable("type") String type, ModelMap mmap)
    {
        if(StringUtils.isNotNull(type) && "all".equals(type)){
            mmap.put("dept", deptService.selectDeptByDeptCode(ShiroUtils.getSysUser().getDept().getDeptCode()));
        }else{
            mmap.put("dept", deptService.selectDeptByDeptCode(deptCode));
        }
        mmap.put("addType",type);
        return prefix + "/add";
    }

    /**
     * 新增保存部门
     */
    @Log(title = "部门管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:dept:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysDept dept)
    {

        SysDept parentDept = deptService.selectDeptById(dept.getParentId());
        if(StringUtils.isNull(parentDept)){
            return error("添加部门失败，没有选中上级部门无法添加");
        }
        if(parentDept.getParentId().intValue() != 0){
            String newDpCode = StringUtils.trim(dept.getDeptCode());
            String parentDpCode = parentDept.getDeptCode();
            int parentLength = parentDpCode.length();
            int newLength = newDpCode.length();
            if(newLength <= parentLength){
                return error("添加部门失败，机构编码不得少于" + parentLength + "位");
            }
            if(!newDpCode.startsWith(parentDpCode)){
                return error("添加部门失败，机构编码必须保持前" + parentLength + "位与上级部门机构编码一致");
            }
        }

        if (UserConstants.DEPT_NAME_NOT_UNIQUE.equals(deptService.checkDeptNameUnique(dept)))
        {
            return error("添加部门失败，部门名称已存在");
        }
        if(UserConstants.DEPT_CODE_NOT_UNIQUE.equals(deptService.checkDeptCodeUnique(dept))){
            return error("添加部门失败，机构编码已存在");
        }
        Date date = new Date();
        dept.setCreateBy(ShiroUtils.getUserId()+"");
        dept.setBusinessTime(date);
        dept.setCreateTime(date);
        return toAjax(deptService.insertDept(dept));
    }

    /**
     * 修改
     */
    @GetMapping("/edit/{deptId}")
    public String edit(@PathVariable("deptId") Long deptId, ModelMap mmap)
    {
        SysDept dept = deptService.selectDeptById(deptId);
        if (StringUtils.isNull(dept) || "0".equals(String.valueOf(dept.getParentId())))
        {
            dept.setParentName("无");
        }
        mmap.put("dept", dept);
        return prefix + "/edit";
    }

    /**
     * 保存
     */
    @Log(title = "部门管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:dept:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysDept dept)
    {
        if (UserConstants.DEPT_NAME_NOT_UNIQUE.equals(deptService.checkDeptNameUnique(dept)))
        {
            return error("修改部门'" + dept.getDeptName() + "'失败，部门名称已存在");
        }
        else if (dept.getParentId().equals(dept.getDeptId()))
        {
            return error("修改部门'" + dept.getDeptName() + "'失败，上级部门不能是自己");
        }
        dept.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(deptService.updateDept(dept));
    }

    /**
     * 删除
     */
    @Log(title = "部门管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:dept:remove")
    @GetMapping("/remove/{deptId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("deptId") Long deptId)
    {
        if (deptService.selectDeptCount(deptId) > 0)
        {
            return AjaxResult.warn("存在下级部门,不允许删除");
        }
        if (deptService.checkDeptExistUser(deptId))
        {
            return AjaxResult.warn("部门存在用户,不允许删除");
        }
        return toAjax(deptService.deleteDeptById(deptId));
    }

    /**
     * 校验部门名称
     */
    @PostMapping("/checkDeptNameUnique")
    @ResponseBody
    public String checkDeptNameUnique(SysDept dept)
    {
        return deptService.checkDeptNameUnique(dept);
    }

    /**
     * 选择部门树
     */
    @GetMapping("/selectDeptTree/{deptId}")
    public String selectDeptTree(@PathVariable("deptId") Long deptId, ModelMap mmap)
    {
        mmap.put("dept", deptService.selectDeptById(deptId));
        return prefix + "/tree";
    }

    /**
     * 加载部门列表树
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
    	Long deptId = ShiroUtils.getSysUser().getDeptId();
    	SysDept sysDept=new SysDept();
    	sysDept.setDeptId(deptId);
        List<Ztree> ztrees = deptService.selectDeptTree(sysDept);
        return ztrees;
    }

    /**
     * 加载角色部门（数据权限）列表树
     */
    @GetMapping("/roleDeptTreeData")
    @ResponseBody
    public List<Ztree> deptTreeData(SysRole role)
    {
        List<Ztree> ztrees = deptService.roleDeptTreeData(role);
        return ztrees;
    }

    /**
     * 选择部门树
     */
    @GetMapping("/getDeptTreeToOrder/{deptId}/{type}")
    public String getDeptTreeToOrder(@PathVariable("deptId") Long deptId,@PathVariable("type") String type, ModelMap mmap)
    {
        mmap.put("dept", deptService.selectDeptById(deptId));
        mmap.put("type",type);
        return prefix + "/order_tree";
    }

    /**
     * 订单加载部门列表树
     */
    @GetMapping("/orderTreeData/{type}")
    @ResponseBody
    public List<Ztree> orderTreeData(@PathVariable("type") String type)
    {
        SysDept dept = ShiroUtils.getSysUser().getDept();
        Map<String,Object> param = new HashMap<>();
        if(!StringUtils.equals("0",String.valueOf(dept.getParentId()))){
            param.put("deptCode",dept.getDeptCode());
        }

        if(StringUtils.equals("add",type)){
            param.put("delFlag","0");
        }
        List<Ztree> ztrees = deptService.getOrderTreeData(param);
        return ztrees;
    }

    /**
     * 检测录入的机构编码是否已经存在
     * @return
     */
    @RequestMapping("/checkDeptCodeUnique")
    @ResponseBody
    public String checkDeptCodeUnique(SysDept dept){
        return deptService.checkDeptCodeUnique(dept);
    }


    /**
     * 分配用户
     */
    @RequiresPermissions("system:dept:add")
    @GetMapping("/changeUser/{deptCode}")
    public String authUser(@PathVariable("deptCode") String deptCode, ModelMap mmap)
    {
        mmap.put("dept", deptService.selectDeptByDeptCode(deptCode));
        return prefix + "/changeUser";
    }

    /**
     * 查询当前部门下已经存在的用户
     */
    @PostMapping("/changeUser/existUserList")
    @ResponseBody
    public TableDataInfo existUserList(SysUser user)
    {
        Map<String,Object> map=startMapPage();
        Map<String, Object> params = user.getParams();
        if(StringUtils.isNull(params.get("deptCode"))){
            throw new BusinessException("机构编码异常，请联系管理员");
        }
        params.put("deptId",deptService.findDeptByDeptCode(params.get("deptCode").toString()).getDeptId());
        return null;
    }


    /**
     * 选择用户
     */
    @GetMapping("/changeUser/selectUser/{deptCode}")
    public String selectUser(@PathVariable("deptCode") String deptCode, ModelMap mmap)
    {
        mmap.put("deptCode", deptCode);
        return prefix + "/selectUser";
    }

    /**
     * 查询当前部门下可以迁移的用户列表
     * 查找可以迁移的人，排除本部门下员工并且是可以迁移到此部门下的对应的岗位的员工
     */
    @RequiresPermissions("system:dept:moveuser")
    @PostMapping("/changeUser/canChangeUserList")
    @ResponseBody
    public TableDataInfo canChangeUserList(SysUser user)
    {
        Map<String, Object> lookParams = new HashMap<>();
        Map<String,Object> map=startMapPage();
        Map<String, Object> params = user.getParams();
        Object deptCodeObj = params.get("deptCode");
        if(StringUtils.isNull(deptCodeObj) || "00".equals(deptCodeObj)){
            throw new BusinessException("机构编码异常，请联系管理员");
        }
        String deptCode = (String) deptCodeObj;
        lookParams.put("deptCode",deptCode);
        int deptLevel = deptCode.length()/2;
        PositionLeaderLevelEnum leaderCode = EnumUtil.getByCode(String.valueOf(deptLevel), PositionLeaderLevelEnum.class);
        if(StringUtils.isNull(leaderCode)){
            throw new BusinessException("机构编码异常，请联系管理员");
        }

        String leaderPostCode = leaderCode.getMessage();
        lookParams.put("postCode", leaderPostCode);
        //查看迁移部门是否有负责人
        SysUser deptLeaderByDeptCode =null;
        List<String> canChangeUserPostCodeList = new ArrayList<>();
        /**
         * 运营中心：有的岗位是运营中心经理、客户经理(编号长度2)；
         * 事业部：有的岗位是事业部经理、事业部综合内勤、客户经理(编号长度4)；
         * 大区:有的岗位是大区经理、大区综合内勤、客户经理(编号长度6)；
         * 分公司：有的岗位是分公司经理、分公司综合内勤、客户经理(编号长度8)；
         * 营业部：有的岗位是营业部经理、客户经理(编号长度10)；
         * 团队：有的岗位是团队经理、客户经理(编号长度12)；
         */
        canChangeUserPostCodeList.add(PositionEnum.POSITION_CODE_07.getCode());//客户经理可以除艾伊斯克下的任意一级
        if(StringUtils.isNull(deptLeaderByDeptCode)){//说明迁移的部门没有负责人
            canChangeUserPostCodeList.add(leaderPostCode);
        }

        if(deptLevel == 2){//事业部
            canChangeUserPostCodeList.add(PositionEnum.POSITION_CODE_10.getCode());
        }else if(deptLevel == 3){//大区
            canChangeUserPostCodeList.add(PositionEnum.POSITION_CODE_09.getCode());
        }else if(deptLevel == 4){//分公司
            canChangeUserPostCodeList.add(PositionEnum.POSITION_CODE_08.getCode());
        }

        params.put("canChangeUserPostCodeList",canChangeUserPostCodeList);
        params.put("deptId",deptService.findDeptByDeptCode(deptCode).getDeptId());
        user.setParams(params);
        return null;
    }

    /**
     * 批量选择迁移用户
     */
    @RequiresPermissions("system:dept:moveuser")
    @Log(title = "迁移用户", businessType = BusinessType.UPDATE)
    @PostMapping("/changeUser/belongDept")
    @ResponseBody
    public AjaxResult needChangeUser(@RequestParam(value = "deptCode") String deptCode, @RequestParam(value = "userIds") String userIds)
    {
        if(StringUtils.isNull(deptCode) || "00".equals(deptCode)){
            throw new BusinessException("机构编码异常，请联系管理员");
        }
        SysDept dept = deptService.selectDeptByDeptCode(deptCode);
        if(StringUtils.isNull(dept)){
            throw new BusinessException("要迁移的机构不存在");
        }
        if(StringUtils.isNull(userIds)){
            throw new BusinessException("选中迁移人员数据异常");
        }
        SysUser sysUser = new SysUser();
        sysUser.setUpdateBy(ShiroUtils.getUserId() + "");
        sysUser.setDeptId(dept.getDeptId());
        Map<String, Object> params = sysUser.getParams();
        params.put("userIds",userIds);
        params.put("deptCode",deptCode);
        return null;
    }
}
