package com.carebed.web.controller.business;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.carebed.business.entity.TDoctor;
import com.carebed.business.entity.TDoctorVo;
import com.carebed.business.service.ITDoctorService;
import com.carebed.common.annotation.Log;
import com.carebed.common.core.controller.BaseController;
import com.carebed.common.core.domain.AjaxResult;
import com.carebed.common.core.page.TableDataInfo;
import com.carebed.common.enums.BusinessType;
import com.carebed.web.utils.ExcelUtil;

/**
 * 医生Controller
 * 
 * @author wjl
 * @date 2020-06-17
 */
@Controller
@RequestMapping("/business/doctor")
public class TDoctorController extends BaseController
{
    private String prefix = "business/doctor";

    @Reference
    private ITDoctorService tDoctorService;

    @RequiresPermissions("business:doctor:view")
    @GetMapping()
    public String doctor()
    {
        return prefix + "/doctor";
    }

    /**
     * 查询医生列表
     */
    @RequiresPermissions("business:doctor:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TDoctorVo tDoctorVo)
    {
    	Map<String, Object> map = startMapPage();
    	
        return tDoctorService.selectTDoctorPageList(tDoctorVo,map);
    }

    /**
     * 导出医生列表
     */
    @RequiresPermissions("business:doctor:export")
    @Log(title = "医生", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TDoctorVo tDoctorVo)
    {
        List<TDoctorVo> list = tDoctorService.selectTDoctorList(tDoctorVo);
        ExcelUtil<TDoctorVo> util = new ExcelUtil<TDoctorVo>(TDoctorVo.class);
        return util.exportExcel(list, "doctor");
    }

    /**
     * 新增医生
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存医生
     */
    @RequiresPermissions("business:doctor:add")
    @Log(title = "医生", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TDoctor tDoctor)
    {
    	//根据陪护床号判断是否已经存在
    	TDoctor tDoctorcheck=new TDoctor();
    	tDoctorcheck.setStatus("1");
    	tDoctorcheck.setPhone(tDoctor.getPhone());
    	if("1".equals(tDoctorService.checkDoctorUnique(tDoctorcheck))){
    		return error("手机号已存在，请确认");
    	}
    	tDoctorcheck=new TDoctor();
    	tDoctorcheck.setStatus("1");
    	tDoctorcheck.setCardNo(tDoctor.getCardNo());
    	if("1".equals(tDoctorService.checkDoctorUnique(tDoctorcheck))){
    		return error("身份证号已存在，请确认");
    	}
    	//根据身份证号查询是否存在失效的该医生
    	TDoctorVo tDoctorVo=new TDoctorVo();
    	tDoctorVo.setStatus("0");
    	tDoctorVo.setCardNo(tDoctor.getCardNo());
    	List<TDoctorVo> dlist=tDoctorService.selectTDoctorList(tDoctorVo);
    	int count=0;
    	if(dlist!=null&&dlist.size()>0){
    		tDoctor.setId(dlist.get(0).getId());
    		tDoctor.setStatus("1");
    		count=tDoctorService.updateTDoctor(tDoctor);
    	}else{
    		count=tDoctorService.insertTDoctor(tDoctor);
    	}
        return toAjax(count);
    }

    /**
     * 修改医生
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TDoctor tDoctor = tDoctorService.selectTDoctorById(id);
        mmap.put("tDoctor", tDoctor);
        return prefix + "/edit";
    }

    /**
     * 修改保存医生
     */
    @RequiresPermissions("business:doctor:edit")
    @Log(title = "医生", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TDoctor tDoctor)
    {
    	//根据陪护床号判断是否已经存在
    	TDoctor tDoctorcheck=new TDoctor();
    	tDoctorcheck.setStatus("1");
    	tDoctorcheck.setId(tDoctor.getId());
    	tDoctorcheck.setPhone(tDoctor.getPhone());
    	if("1".equals(tDoctorService.checkDoctorUnique(tDoctorcheck))){
    		return error("手机号已存在，请确认");
    	}
    	tDoctorcheck=new TDoctor();
    	tDoctorcheck.setStatus("1");
    	tDoctorcheck.setId(tDoctor.getId());
    	tDoctorcheck.setCardNo(tDoctor.getCardNo());
    	if("1".equals(tDoctorService.checkDoctorUnique(tDoctorcheck))){
    		return error("身份证号已存在，请确认");
    	}
        return toAjax(tDoctorService.updateTDoctor(tDoctor));
    }

    /**
     * 删除医生
     */
    @RequiresPermissions("business:doctor:remove")
    @Log(title = "医生", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tDoctorService.deleteTDoctorByIds(ids));
    }
    /**
     * 修改保存医生
     */
    @PostMapping("/checkDoctorUnique")
    @ResponseBody
    public String checkDoctorUnique(TDoctor tDoctor)
    {
        return tDoctorService.checkDoctorUnique(tDoctor);
    }
    
    
}
