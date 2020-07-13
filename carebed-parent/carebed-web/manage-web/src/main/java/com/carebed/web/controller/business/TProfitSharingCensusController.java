package com.carebed.web.controller.business;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.carebed.business.entity.TProfitSharingCensusVo;
import com.carebed.business.entity.TProfitSharingVo;
import com.carebed.business.service.ITProfitSharingService;
import com.carebed.common.annotation.Log;
import com.carebed.common.core.controller.BaseController;
import com.carebed.common.core.domain.AjaxResult;
import com.carebed.common.core.page.TableDataInfo;
import com.carebed.common.enums.BusinessType;
import com.carebed.web.utils.ExcelUtil;


/**
 * 分润Controller
 * 
 * @author wjl
 * @date 2020-06-17
 */
@Controller
@RequestMapping("/business/profitSharingCensus")
public class TProfitSharingCensusController extends BaseController
{
    private String prefix = "business/profitSharing";

    @Reference
    private ITProfitSharingService tProfitSharingService;

    @RequiresPermissions("business:profitSharingCensus:view")
    @GetMapping()
    public String profitSharingCensus()
    {
        return prefix + "/profitSharingCensus";
    }

    /**
     * 查询分润列表
     */
    @RequiresPermissions("business:profitSharingCensus:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TProfitSharingCensusVo tProfitSharingCensusVo)
    {
    	Map<String, Object> map = startMapPage();
    	
        return tProfitSharingService.selectTProfitSharingCensusPageList(tProfitSharingCensusVo,map);
    }

    /**
     * 导出分润列表
     */
    @RequiresPermissions("business:profitSharing:export")
    @Log(title = "分润", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TProfitSharingCensusVo tProfitSharingVo)
    {
        List<TProfitSharingCensusVo> list = tProfitSharingService.selectTProfitSharingCensusList(tProfitSharingVo);
        ExcelUtil<TProfitSharingCensusVo> util = new ExcelUtil<TProfitSharingCensusVo>(TProfitSharingCensusVo.class);
        return util.exportExcel(list, "profitSharingCensus");
    }

    /**
     * 金额统计
     * @param tProfitSharingVo
     * @return
     */
    @PostMapping("/getMoneySum")
    @ResponseBody
    public Map<String, Object> getMoneySum(TProfitSharingVo tProfitSharingVo){
       
        Map<String, Object> moneySum = tProfitSharingService.getMoneySum(tProfitSharingVo);
        return AjaxResult.success(moneySum);
    }
}
