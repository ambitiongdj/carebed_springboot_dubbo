package com.carebed.web.controller.business;

import java.util.List;

import com.carebed.common.utils.RegularUtil;
import com.carebed.common.utils.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.carebed.business.entity.TStakeholderGroupDoctor;
import com.carebed.business.service.ITStakeholderGroupDoctorService;
import com.carebed.common.annotation.Log;
import com.carebed.common.core.controller.BaseController;
import com.carebed.common.core.domain.AjaxResult;
import com.carebed.common.core.page.TableDataInfo;
import com.carebed.common.enums.BusinessType;
import com.carebed.web.utils.ExcelUtil;

/**
 * 干系组和医生关联Controller
 * 
 * @author wjl
 * @date 2020-06-17
 */
@Controller
@RequestMapping("/business/stakeholderGroupDoctor")
public class TStakeholderGroupDoctorController extends BaseController
{
    private String prefix = "business/stakeholderGroupDoctor";

    @Reference
    private ITStakeholderGroupDoctorService tStakeholderGroupDoctorService;

    @RequiresPermissions("business:stakeholderGroupDoctor:view")
    @GetMapping()
    public String stakeholderGroupDoctor()
    {
        return prefix + "/stakeholderGroupDoctor";
    }

    /**
     * 查询干系组和医生关联列表
     */
    @RequiresPermissions("business:stakeholderGroupDoctor:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TStakeholderGroupDoctor tStakeholderGroupDoctor)
    {
        startPage();
        List<TStakeholderGroupDoctor> list = tStakeholderGroupDoctorService.selectTStakeholderGroupDoctorList(tStakeholderGroupDoctor);
        return getDataTable(list);
    }

    /**
     * 导出干系组和医生关联列表
     */
    @RequiresPermissions("business:stakeholderGroupDoctor:export")
    @Log(title = "干系组和医生关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TStakeholderGroupDoctor tStakeholderGroupDoctor)
    {
        List<TStakeholderGroupDoctor> list = tStakeholderGroupDoctorService.selectTStakeholderGroupDoctorList(tStakeholderGroupDoctor);
        ExcelUtil<TStakeholderGroupDoctor> util = new ExcelUtil<TStakeholderGroupDoctor>(TStakeholderGroupDoctor.class);
        return util.exportExcel(list, "stakeholderGroupDoctor");
    }

    /**
     * 新增干系组和医生关联
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存干系组和医生关联
     */
    @RequiresPermissions("business:stakeholderGroupDoctor:add")
    @Log(title = "干系组和医生关联", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TStakeholderGroupDoctor tStakeholderGroupDoctor)
    {
        return toAjax(tStakeholderGroupDoctorService.insertTStakeholderGroupDoctor(tStakeholderGroupDoctor));
    }

    /**
     * 修改干系组和医生关联
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TStakeholderGroupDoctor tStakeholderGroupDoctor = tStakeholderGroupDoctorService.selectTStakeholderGroupDoctorById(id);
        mmap.put("tStakeholderGroupDoctor", tStakeholderGroupDoctor);
        return prefix + "/edit";
    }

    /**
     * 修改保存干系组和医生关联
     */
    @RequiresPermissions("business:stakeholderGroupDoctor:edit")
    @Log(title = "干系组和医生关联", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TStakeholderGroupDoctor tStakeholderGroupDoctor)
    {
        return toAjax(tStakeholderGroupDoctorService.updateTStakeholderGroupDoctor(tStakeholderGroupDoctor));
    }

    /**
     * 删除干系组和医生关联
     */
    @RequiresPermissions("business:stakeholderGroupDoctor:remove")
    @Log(title = "干系组和医生关联", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tStakeholderGroupDoctorService.deleteTStakeholderGroupDoctorByIds(ids));
    }

    @Log(title = "干系组医生分润比例", businessType = BusinessType.UPDATE)
    @PostMapping("/editShareProportion")
    @ResponseBody
    public AjaxResult editShareProportion(TStakeholderGroupDoctor stakeholderGroupDoctor){
        if(StringUtils.isNull(stakeholderGroupDoctor.getId()) || StringUtils.isNull(stakeholderGroupDoctor.getShareProportion())){
            return AjaxResult.error("提交参数错误");
        }
        if(!RegularUtil.isNumber(stakeholderGroupDoctor.getShareProportion().toString())){
            return AjaxResult.error("分润比例填写的格式不正确");
        }
        tStakeholderGroupDoctorService.updateTStakeholderGroupDoctor(stakeholderGroupDoctor);
        return AjaxResult.success();
    }
}
