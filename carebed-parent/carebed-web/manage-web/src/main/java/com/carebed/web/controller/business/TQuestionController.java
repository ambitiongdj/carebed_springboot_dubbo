package com.carebed.web.controller.business;

import com.alibaba.dubbo.config.annotation.Reference;
import com.carebed.business.entity.TCot;
import com.carebed.business.entity.TQuestion;
import com.carebed.business.service.ITCotService;
import com.carebed.business.service.ITQuestionService;
import com.carebed.common.annotation.Log;
import com.carebed.common.config.Global;
import com.carebed.common.core.controller.BaseController;
import com.carebed.common.core.domain.AjaxResult;
import com.carebed.common.core.page.TableDataInfo;
import com.carebed.common.enums.BusinessType;
import com.carebed.common.enums.QuestionStatusEnum;
import com.carebed.common.utils.StringUtils;
import com.carebed.web.utils.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 问题Controller
 * 
 * @author wjl
 * @date 2020-06-17
 */
@Controller
@RequestMapping("/business/question")
public class TQuestionController extends BaseController
{
    private String prefix = "business/question";

    @Reference
    private ITQuestionService tQuestionService;

    @Reference
    private ITCotService tCotService;

    @RequiresPermissions("business:question:view")
    @GetMapping()
    public String question()
    {
        return prefix + "/question";
    }

    /**
     * 查询问题列表
     */
    @RequiresPermissions("business:question:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TQuestion tQuestion)
    {
        startPage();
        List<TQuestion> list = tQuestionService.selectTQuestionList(tQuestion);
        return getDataTable(list);
    }

    /**
     * 查询待处理问题列表
     */
    @GetMapping("/index")
    public String index(ModelMap mmap)
    {
        TQuestion tQuestion=new TQuestion();
        tQuestion.setStatus(QuestionStatusEnum.STATUS0.getCode());
        List<TQuestion> list = tQuestionService.selectTQuestionList(tQuestion);
        if(StringUtils.isNotEmpty(list) && list.size()>0){
            mmap.put("questionStatus",QuestionStatusEnum.STATUS0.getCode());
            return prefix + "/pending";
        }else{
            mmap.put("version", Global.getVersion());
            return "main";
        }
    }

    /**
     * 导出问题列表
     */
    @RequiresPermissions("business:question:export")
    @Log(title = "问题", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TQuestion tQuestion)
    {
        List<TQuestion> list = tQuestionService.selectTQuestionList(tQuestion);
        ExcelUtil<TQuestion> util = new ExcelUtil<TQuestion>(TQuestion.class);
        return util.exportExcel(list, "question");
    }

    /**
     * 新增问题
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存问题
     */
    @RequiresPermissions("business:question:add")
    @Log(title = "问题", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TQuestion tQuestion)
    {
        return toAjax(tQuestionService.insertTQuestion(tQuestion));
    }

    /**
     * 修改问题
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TQuestion tQuestion = tQuestionService.selectTQuestionById(id);
        TCot tCot = new TCot();
        tCot.setCotNo(tQuestion.getCotNo());
//        tCot.setDelSta("1");
        tCot = tCotService.findTCotByCotNo(tCot);
        if (StringUtils.isNotNull(tCot)){
            mmap.put("tCot", tCot);
        }
        mmap.put("tQuestion", tQuestion);
        return prefix + "/edit";
    }

    /**
     * 修改保存问题
     */
    @RequiresPermissions("business:question:edit")
    @Log(title = "问题", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult editSave(TQuestion tQuestion)
    {
        TCot tCot = new TCot();
        tCot.setId(tQuestion.getCotId());
        tCot.setCotNo(tQuestion.getCotNo());
        tCot.setStatus(tQuestion.getCotStatus());
        tCotService.updateTCot(tCot);
        return toAjax(tQuestionService.updateTQuestion(tQuestion));
    }

    /**
     * 删除问题
     */
    @RequiresPermissions("business:question:remove")
    @Log(title = "问题", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tQuestionService.deleteTQuestionByIds(ids));
    }
}
