package com.carebed.web.controller.business;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.carebed.business.entity.TWechatUser;
import com.carebed.business.service.ITWechatUserService;
import com.carebed.common.annotation.Log;
import com.carebed.common.core.controller.BaseController;
import com.carebed.common.core.domain.AjaxResult;
import com.carebed.common.core.page.TableDataInfo;
import com.carebed.common.enums.BusinessType;
import com.carebed.web.utils.ExcelUtil;

/**
 * 微信用户Controller
 * 
 * @author wjl
 * @date 2020-06-17
 */
@Controller
@RequestMapping("/business/wechatUser")
public class TWechatUserController extends BaseController
{
    private String prefix = "business/wechatUser";

    @Reference
    private ITWechatUserService tWechatUserService;

    @RequiresPermissions("business:wechatUser:view")
    @GetMapping()
    public String wechatUser()
    {
        return prefix + "/wechatUser";
    }

    /**
     * 查询微信用户列表
     */
    @RequiresPermissions("business:wechatUser:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TWechatUser tWechatUser)
    {
        startPage();
        List<TWechatUser> list = tWechatUserService.selectTWechatUserList(tWechatUser);
        return getDataTable(list);
    }

    /**
     * 导出微信用户列表
     */
    @RequiresPermissions("business:wechatUser:export")
    @Log(title = "微信用户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TWechatUser tWechatUser)
    {
        List<TWechatUser> list = tWechatUserService.selectTWechatUserList(tWechatUser);
        ExcelUtil<TWechatUser> util = new ExcelUtil<TWechatUser>(TWechatUser.class);
        return util.exportExcel(list, "wechatUser");
    }

    /**
     * 新增微信用户
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存微信用户
     */
    @RequiresPermissions("business:wechatUser:add")
    @Log(title = "微信用户", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TWechatUser tWechatUser)
    {
        return toAjax(tWechatUserService.insertTWechatUser(tWechatUser));
    }

    /**
     * 修改微信用户
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TWechatUser tWechatUser = tWechatUserService.selectTWechatUserById(id);
        mmap.put("tWechatUser", tWechatUser);
        return prefix + "/edit";
    }

    /**
     * 修改保存微信用户
     */
    @RequiresPermissions("business:wechatUser:edit")
    @Log(title = "微信用户", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TWechatUser tWechatUser)
    {
        return toAjax(tWechatUserService.updateTWechatUser(tWechatUser));
    }

    /**
     * 删除微信用户
     */
    @RequiresPermissions("business:wechatUser:remove")
    @Log(title = "微信用户", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tWechatUserService.deleteTWechatUserByIds(ids));
    }
}
