package com.carebed.web.controller.business;

import java.util.List;
import java.util.Map;

import com.carebed.common.constant.Constants;
import com.carebed.manage.entity.SysUser;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.carebed.business.entity.*;
import com.carebed.business.service.ITCotService;
import com.carebed.business.service.ITDoctorService;
import com.carebed.business.service.ITStakeholderGroupDoctorService;
import com.carebed.business.service.ITStakeholderGroupService;
import com.carebed.common.annotation.Log;
import com.carebed.common.core.controller.BaseController;
import com.carebed.common.core.domain.AjaxResult;
import com.carebed.common.core.page.TableDataInfo;
import com.carebed.common.enums.BusinessType;
import com.carebed.common.utils.StringUtils;
import com.carebed.exception.BusinessException;
import com.carebed.web.shiro.utils.ShiroUtils;
import com.carebed.web.utils.ExcelUtil;

/**
 * 干系组Controller
 * 
 * @author wjl
 * @date 2020-06-17
 */
@Controller
@RequestMapping("/business/stakeholderGroup")
public class TStakeholderGroupController extends BaseController
{
    private String prefix = "business/stakeholderGroup";

    @Reference
    private ITStakeholderGroupService tStakeholderGroupService;

    @Reference
    private ITStakeholderGroupDoctorService stakeholderGroupDoctorService;

    @Reference
    private ITDoctorService doctorService;

    @Reference
    private ITCotService cotService;


    @RequiresPermissions("business:stakeholderGroup:view")
    @GetMapping()
    public String stakeholderGroup()
    {
        return prefix + "/stakeholderGroup";
    }

    /**
     * 查询干系组列表
     */
    @RequiresPermissions("business:stakeholderGroup:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TStakeholderGroup tStakeholderGroup)
    {
        Map<String, Object> map = startMapPage();
        return tStakeholderGroupService.selectTStakeholderGroupListPage(tStakeholderGroup,map);
    }

    /**
     * 新增干系组
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存干系组
     */
    @RequiresPermissions("business:stakeholderGroup:add")
    @Log(title = "干系组", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TStakeholderGroup tStakeholderGroup)
    {
        Long loginUserId = ShiroUtils.getUserId();
        tStakeholderGroup.setCreateBy(loginUserId + "");
        tStakeholderGroup.setUpdateBy(loginUserId + "");
        String checkGroupNameUnique = tStakeholderGroupService.checkGroupNameUnique(tStakeholderGroup);
        if(Constants.STAKEHOLDER_GROUP_NAME_NOT_UNIQUE.equals(checkGroupNameUnique)){
            return AjaxResult.error("组名称已经存在");
        }

        return toAjax(tStakeholderGroupService.insertTStakeholderGroup(tStakeholderGroup));
    }

    /**
     * 修改干系组
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TStakeholderGroup tStakeholderGroup = tStakeholderGroupService.selectTStakeholderGroupById(id);
        mmap.put("tStakeholderGroup", tStakeholderGroup);
        return prefix + "/edit";
    }

    /**
     * 修改保存干系组
     */
    @RequiresPermissions("business:stakeholderGroup:edit")
    @Log(title = "干系组", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TStakeholderGroup tStakeholderGroup)
    {
        String checkGroupNameUnique = tStakeholderGroupService.checkGroupNameUnique(tStakeholderGroup);
        if(Constants.STAKEHOLDER_GROUP_NAME_NOT_UNIQUE.equals(checkGroupNameUnique)){
            return AjaxResult.error("组名称已经存在");
        }
        return toAjax(tStakeholderGroupService.updateTStakeholderGroup(tStakeholderGroup));
    }

    /**
     * 删除干系组
     */
    @RequiresPermissions("business:stakeholderGroup:remove")
    @Log(title = "干系组", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        Long operatorId = ShiroUtils.getUserId();
        return toAjax(tStakeholderGroupService.deleteTStakeholderGroupByIds(ids,operatorId));
    }


    //-------------------------------医生维护----------------------------------------------------
    /**
     * 医生维护页面
     */
    @RequiresPermissions("business:stakeholderGroup:bindingDoctor")
    @GetMapping("/bindingDoctor/{stakeholderGroupId}")
    public String bindingDoctor(@PathVariable("stakeholderGroupId") Long stakeholderGroupId, ModelMap mmap)
    {
        mmap.put("stakeholderGroup", tStakeholderGroupService.selectTStakeholderGroupById(stakeholderGroupId));
        return prefix + "/authDoctor";
    }

    /**
     * 查询干系组绑定的医生
     */
    @RequiresPermissions("business:stakeholderGroup:bindingDoctor")
    @PostMapping("/bindingSGDoctor")
    @ResponseBody
    public TableDataInfo bindingSGDoctor(TDoctor doctor)
    {
        Map<String, Object> params = doctor.getParams();
        if(StringUtils.isNull(params) || StringUtils.isNull(params.get("sgId"))){
            throw new BusinessException("参数异常");
        }
        Map<String, Object> map = startMapPage();
        return doctorService.findBindingSGOfDoctorPage(doctor,map);
    }

    /**
     * 选择未绑定干系组的医生
     */
    @RequiresPermissions("business:stakeholderGroup:authDoctor")
    @GetMapping("/selectDoctor/{stakeholderGroupId}")
    public String selectDoctor(@PathVariable("stakeholderGroupId") Long stakeholderGroupId, ModelMap mmap)
    {
        mmap.put("stakeholderGroup", tStakeholderGroupService.selectTStakeholderGroupById(stakeholderGroupId));
        return prefix + "/selectDoctor";
    }

    /**
     * 查询未绑定干系组的医生
     */
    @RequiresPermissions("business:stakeholderGroup:authDoctor")
    @PostMapping("/unBoundSGDoctor")
    @ResponseBody
    public TableDataInfo unBoundSGDoctor(TDoctor doctor)
    {
        Map<String, Object> params = doctor.getParams();
        if(StringUtils.isNull(params) || StringUtils.isNull(params.get("sgId"))){
            throw new BusinessException("参数异常");
        }
        Map<String, Object> map = startMapPage();
        return doctorService.findUnboundSGOfDoctorPage(doctor,map);
    }

    /**
     * 批量选择医生绑定
     */
    @RequiresPermissions("business:stakeholderGroup:authDoctor")
    @Log(title = "干系组管理-医生绑定", businessType = BusinessType.GRANT)
    @PostMapping("/needBindingDoctorAll")
    @ResponseBody
    public AjaxResult needBindingDoctorAll(Long sgId, String doctorIds)
    {
        if(StringUtils.isEmpty(doctorIds) || StringUtils.isNull(sgId)){
            throw new BusinessException("参数异常");
        }
        return toAjax(stakeholderGroupDoctorService.insertTStakeholderGroupDoctors(sgId,doctorIds));
    }

    /**
     * 解除医生绑定
     */
    @RequiresPermissions("business:stakeholderGroup:cancelAuthDoctor")
    @Log(title = "干系组管理-解除医生绑定", businessType = BusinessType.GRANT)
    @PostMapping("/cancelDoctor")
    @ResponseBody
    public AjaxResult cancelDoctor(Long sgId,Long doctorId)
    {
        if(StringUtils.isNull(sgId) || StringUtils.isNull(doctorId)){
            throw new BusinessException("参数异常");
        }
        TStakeholderGroupDoctor tStakeholderGroupDoctor = new TStakeholderGroupDoctor();
        tStakeholderGroupDoctor.setStakeholderGroupId(sgId);
        tStakeholderGroupDoctor.setDoctorId(doctorId);
        return toAjax(stakeholderGroupDoctorService.deleteTStakeholderGroup(tStakeholderGroupDoctor));
    }

    /**
     * 批量解除医生绑定
     */
    @RequiresPermissions("business:stakeholderGroup:cancelAuthDoctor")
    @Log(title = "干系组管理-批量解除医生绑定", businessType = BusinessType.GRANT)
    @PostMapping("/cancelAllDoctor")
    @ResponseBody
    public AjaxResult cancelAllDoctor(Long sgId,String doctorIds)
    {
        if(StringUtils.isNull(sgId) || StringUtils.isEmpty(doctorIds)){
            throw new BusinessException("参数异常");
        }
        return toAjax(stakeholderGroupDoctorService.deleteTStakeholderGroupBatch(sgId,doctorIds));
    }

    @Log(title = "干系组管理-医生维护", businessType = BusinessType.EXPORT)
    @RequiresPermissions("business:stakeholderGroup:exportAuthDoctor")
    @PostMapping("/exportAuthDoctor")
    @ResponseBody
    public AjaxResult exportAuthDoctor(TDoctor doctor) {
        List<TDoctorBindingVO> boundSGOfDoctorList = doctorService.findBindingSGOfDoctorList(doctor);
        ExcelUtil<TDoctorBindingVO> util = new ExcelUtil<TDoctorBindingVO>(TDoctorBindingVO.class);
        return util.exportExcel(boundSGOfDoctorList, "绑定医生");
    }

    //--------------------------陪护床维护--------------------------------------------------
    /**
     * 陪护床维护页面
     */
    @RequiresPermissions("business:stakeholderGroup:bindingCot")
    @GetMapping("/bindingCot/{stakeholderGroupId}")
    public String bindingCot(@PathVariable("stakeholderGroupId") Long stakeholderGroupId, ModelMap mmap)
    {
        mmap.put("stakeholderGroup", tStakeholderGroupService.selectTStakeholderGroupById(stakeholderGroupId));
        return prefix + "/authCot";
    }

    /**
     * 查询干系组绑定的陪护床
     */
    @RequiresPermissions("business:stakeholderGroup:bindingSGCot")
    @PostMapping("/bindingSGCot")
    @ResponseBody
    public TableDataInfo bindingSGCot(TCot cot)
    {
        Map<String, Object> params = cot.getParams();
        if(StringUtils.isNull(params) || StringUtils.isNull(params.get("sgId"))){
            throw new BusinessException("参数异常");
        }
        Map<String, Object> map = startMapPage();
        return cotService.findBindingSGCotPage(cot,map);
    }

    /**
     * 选择未绑定干系组的陪护床
     */
    @RequiresPermissions("business:stakeholderGroup:authCot")
    @GetMapping("/selectCot/{stakeholderGroupId}")
    public String selectCot(@PathVariable("stakeholderGroupId") Long stakeholderGroupId, ModelMap mmap)
    {
        mmap.put("stakeholderGroup", tStakeholderGroupService.selectTStakeholderGroupById(stakeholderGroupId));
        return prefix + "/selectCot";
    }

    /**
     * 查询未绑定干系组的陪护床
     */
    @RequiresPermissions("business:stakeholderGroup:authCot")
    @PostMapping("/unBoundSGCot")
    @ResponseBody
    public TableDataInfo unBoundSGCot(TCot cot)
    {
        Map<String, Object> params = cot.getParams();
        if(StringUtils.isNull(params) || StringUtils.isNull(params.get("sgId"))){
            throw new BusinessException("参数异常");
        }
        Map<String, Object> map = startMapPage();
        return cotService.findUnboundSGOfCotPage(cot,map);
    }

    /**
     * 批量选择陪护床绑定干系组
     */
    @RequiresPermissions("business:stakeholderGroup:authCot")
    @Log(title = "干系组管理-陪护床绑定", businessType = BusinessType.GRANT)
    @PostMapping("/needBindingCotAll")
    @ResponseBody
    public AjaxResult needBindingCotAll(Long sgId, String cotIds)
    {
        if(StringUtils.isEmpty(cotIds) || StringUtils.isNull(sgId)){
            throw new BusinessException("参数异常");
        }
        return toAjax(cotService.updateBatchSGOfCotRelationship(sgId,cotIds));
    }

    /**
     * 解除陪护床绑定
     */
    @RequiresPermissions("business:stakeholderGroup:cancelAuthCot")
    @Log(title = "干系组管理-解除陪护床绑定", businessType = BusinessType.GRANT)
    @PostMapping("/cancelCot")
    @ResponseBody
    public AjaxResult cancelCot(Long sgId,Long cotId)
    {
        if(StringUtils.isNull(sgId) || StringUtils.isNull(cotId)){
            throw new BusinessException("参数异常");
        }
        return toAjax(cotService.cancleCotBindingSGByCotId(cotId));
    }

    /**
     * 批量取消陪护床绑定
     */
    @RequiresPermissions("business:stakeholderGroup:cancelAuthCot")
    @Log(title = "干系组管理-批量解除陪护床绑定", businessType = BusinessType.GRANT)
    @PostMapping("/cancelAllCot")
    @ResponseBody
    public AjaxResult cancelAllCot(Long sgId,String cotIds)
    {
        if(StringUtils.isNull(sgId) || StringUtils.isEmpty(cotIds)){
            throw new BusinessException("参数异常");
        }
        return toAjax(cotService.cancleCotBindingSGByCondition(sgId,cotIds));
    }

    @Log(title = "干系组管理-陪护床维护-导出", businessType = BusinessType.EXPORT)
    @RequiresPermissions("business:stakeholderGroup:exportAuthCot")
    @PostMapping("/exportAuthCot")
    @ResponseBody
    public AjaxResult exportAuthCot(TCot cot) {
        List<TCotBindingVO> bindingSGCotList = cotService.findBindingSGCotList(cot);
        ExcelUtil<TCotBindingVO> util = new ExcelUtil<TCotBindingVO>(TCotBindingVO.class);
        return util.exportExcel(bindingSGCotList, "绑定陪护床");
    }

    //------------------------------------------------------------------------------------
    /**
     * 校验组名称
     */
    @PostMapping("/checkGroupNameUnique")
    @ResponseBody
    public String checkPhoneUnique(TStakeholderGroup stakeholderGroup) {
        return tStakeholderGroupService.checkGroupNameUnique(stakeholderGroup);
    }
}
