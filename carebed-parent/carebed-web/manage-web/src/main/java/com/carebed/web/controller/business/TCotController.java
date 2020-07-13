package com.carebed.web.controller.business;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.config.annotation.Reference;
import com.carebed.business.entity.TCot;
import com.carebed.business.entity.TCotVo;
import com.carebed.business.service.ITCotService;
import com.carebed.common.annotation.Log;
import com.carebed.common.constant.Constants;
import com.carebed.common.core.controller.BaseController;
import com.carebed.common.core.domain.AjaxResult;
import com.carebed.common.core.page.TableDataInfo;
import com.carebed.common.enums.BusinessType;
import com.carebed.common.utils.ImportExcelUtil;
import com.carebed.common.utils.file.FileUtils;
import com.carebed.manage.entity.SysUser;
import com.carebed.web.shiro.utils.ShiroUtils;
import com.carebed.web.utils.ExcelUtil;
 

/**
 * 陪护床Controller
 * 
 * @author wjl
 * @date 2020-06-17
 */
@Controller
@RequestMapping("/business/cot")
public class TCotController extends BaseController
{
	 private static final Logger log = LoggerFactory.getLogger(TCotController.class);
    private String prefix = "business/cot";

    @Reference
    private ITCotService tCotService;

    @RequiresPermissions("business:cot:view")
    @GetMapping()
    public String cot()
    {
        return prefix + "/cot";
    }

    /**
     * 查询陪护床列表
     */
    @RequiresPermissions("business:cot:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TCotVo tCot)
    {
    	Map<String, Object> map = startMapPage();
	
        return tCotService.selectTCotPageList(tCot,map);
    }

    /**
     * 导出陪护床列表
     */
    @RequiresPermissions("business:cot:export")
    @Log(title = "陪护床", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TCotVo tCot)
    {
        List<TCotVo> list = tCotService.selectTCotList(tCot);
        ExcelUtil<TCotVo> util = new ExcelUtil<TCotVo>(TCotVo.class);
        return util.exportExcel(list, "cot");
    }

    /**
     * 新增陪护床
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存陪护床
     */
    @RequiresPermissions("business:cot:add")
    @Log(title = "陪护床", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TCot tCot)
    {
    	//根据陪护床号判断是否已经存在
    	if(tCotService.selectTCotByCotNo(tCot.getCotNo())!=null){
    		return error("陪护床号已存在，请确认");
    	}
    	tCot.setCreateBy(ShiroUtils.getUserId() + "");
        return toAjax(tCotService.insertTCot(tCot));
    }

    /**
     * 修改陪护床
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TCot tCot = tCotService.selectTCotById(id);
        mmap.put("tCot", tCot);
        return prefix + "/edit";
    }

    /**
     * 修改保存陪护床
     */
    @RequiresPermissions("business:cot:edit")
    @Log(title = "陪护床", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TCot tCot)
    {
        return toAjax(tCotService.updateTCot(tCot));
    }

    /**
     * 删除陪护床
     */
    @RequiresPermissions("business:cot:remove")
    @Log(title = "陪护床", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tCotService.deleteTCotByIds(ids));
    }
    
    @RequiresPermissions("business:cot:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file) {
        SysUser loginUser = ShiroUtils.getSysUser();
        List<Map<String,Object>> exceDataList = new ArrayList<>();
        try {
            exceDataList = ImportExcelUtil.readExcel(file, Constants.COT_CELL_VALUES);
        } catch (IOException e) {
            e.printStackTrace();
            return AjaxResult.error(e.getMessage());
        }
        return tCotService.importCotRecord(loginUser,exceDataList);
    }
    /**
     * 下载会员卡模版
     *
     * @param response
     * @param request
     */
    @GetMapping("/downloadTemplate")
    public void fileDownload(HttpServletResponse response, HttpServletRequest request) {
        InputStream input = null;
        try {
            ClassPathResource classPathResource = new ClassPathResource("/templateFile/cot_template.xlsx");
            input = classPathResource.getInputStream();
            String realFileName = System.currentTimeMillis() + "陪护床模版.xlsx";
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, realFileName));
            FileUtils.writeBytes(input, response.getOutputStream());
        } catch (Exception e) {
            log.error("下载文件失败", e);
        }finally {
            if(input != null){
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
