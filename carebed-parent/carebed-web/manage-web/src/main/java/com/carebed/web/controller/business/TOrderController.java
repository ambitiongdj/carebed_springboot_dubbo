package com.carebed.web.controller.business;


import com.alibaba.dubbo.config.annotation.Reference;
import com.carebed.business.entity.TCot;
import com.carebed.business.entity.TOrder;
import com.carebed.business.entity.TOrderVo;
import com.carebed.business.service.ITCotService;
import com.carebed.business.service.ITOrderService;
import com.carebed.common.annotation.Log;
import com.carebed.common.core.controller.BaseController;
import com.carebed.common.core.domain.AjaxResult;
import com.carebed.common.core.page.TableDataInfo;
import com.carebed.common.enums.BusinessType;
import com.carebed.common.utils.StringUtils;
import com.carebed.web.utils.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单Controller
 * 
 * @author wjl
 * @date 2020-06-17
 */
@Controller
@RequestMapping("/business/order")
public class TOrderController extends BaseController
{
    private String prefix = "business/order";

    @Reference
    private ITOrderService tOrderService;

    @Reference
    private ITCotService tCotService;

    @RequiresPermissions("business:order:view")
    @GetMapping()
    public String order()
    {
        return prefix + "/order";
    }

    /**
     * 查询订单列表
     */
    @RequiresPermissions("business:order:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TOrder tOrder)
    {
        startPage();
        List<TOrder> list = tOrderService.selectTOrderList(tOrder);
        return getDataTable(list);
    }

    /**
     * 导出订单列表
     */
    @RequiresPermissions("business:order:export")
    @Log(title = "订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TOrderVo tOrder)
    {
        List<TOrderVo> list = tOrderService.findTOrderList(tOrder);
        ExcelUtil<TOrderVo> util = new ExcelUtil<TOrderVo>(TOrderVo.class);
        return util.exportExcel(list, "订单信息");
    }

    /**
     * 新增订单
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存订单
     */
    @RequiresPermissions("business:order:add")
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TOrder tOrder)
    {
        return toAjax(tOrderService.insertTOrder(tOrder));
    }

    /**
     * 修改订单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TOrder tOrder = tOrderService.selectTOrderById(id);
        TCot tCot = new TCot();
        tCot.setCotNo(tOrder.getCotNo());
//        tCot.setDelSta("1");
        tCot = tCotService.findTCotByCotNo(tCot);
        if (StringUtils.isNotNull(tCot)){
            mmap.put("tCot", tCot);
        }
        mmap.put("tOrder", tOrder);
        return prefix + "/edit";
    }

    /**
     * 修改保存订单
     */
    @RequiresPermissions("business:order:edit")
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TOrder tOrder)
    {
        return toAjax(tOrderService.updateTOrder(tOrder));
    }

    /**
     * 删除订单
     */
    @RequiresPermissions("business:order:remove")
    @Log(title = "订单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tOrderService.deleteTOrderByIds(ids));
    }
}
