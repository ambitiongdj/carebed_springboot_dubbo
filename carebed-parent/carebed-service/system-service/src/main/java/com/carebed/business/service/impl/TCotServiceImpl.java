package com.carebed.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.carebed.business.entity.TCotBindingVO;
import com.carebed.exception.BusinessException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.carebed.business.entity.TCot;
import com.carebed.business.entity.TCotVo;
import com.carebed.business.mapper.TCotMapper;
import com.carebed.business.service.ITCotService;
import com.carebed.common.constant.Constants;
import com.carebed.common.core.domain.AjaxResult;
import com.carebed.common.core.page.TableDataInfo;
import com.carebed.common.core.text.Convert;
import com.carebed.common.utils.DateUtils;
import com.carebed.common.utils.StringUtils;
import com.carebed.manage.entity.SysUser;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 陪护床Service业务层处理
 * 
 * @author wjl
 * @date 2020-06-17
 */
@Service
public class TCotServiceImpl implements ITCotService 
{
	private static final Logger log = LoggerFactory.getLogger(TCotServiceImpl.class);
    @Autowired
    private TCotMapper tCotMapper;

    /**
     * 查询陪护床
     * 
     * @param id 陪护床ID
     * @return 陪护床
     */
    @Override
    public TCot selectTCotById(Long id)
    {
        return tCotMapper.selectTCotById(id);
    }

    /**
     * 查询陪护床列表
     * 
     * @return 陪护床
     */
    @Override
    public List<TCotVo> selectTCotList(TCotVo tCotVo)
    {
        return tCotMapper.selectTCotList(tCotVo);
    }
    /**
     * 分页查询陪护床列表
     * 
     * @return 陪护床
     */
    @Override
	public TableDataInfo selectTCotPageList(TCotVo tCotVo,
			Map<String, Object> map) {

		if (map.get("orderBy") != null
				&& StringUtils.isNotBlank(map.get("orderBy").toString())) {
			PageHelper.orderBy(map.get("orderBy").toString());
		}
		PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()),
				Integer.parseInt(map.get("pageSize").toString()));
		List<TCotVo> list = tCotMapper.selectTCotList(tCotVo);;
		PageInfo<TCotVo> pageInfo = new PageInfo<TCotVo>(list);
		TableDataInfo rspData = new TableDataInfo();
		rspData.setCode(0);
		rspData.setRows(pageInfo.getList());
		rspData.setTotal(pageInfo.getTotal());
		return rspData;
	}

    /**
     * 新增陪护床
     * 
     * @param tCot 陪护床
     * @return 结果
     */
    @Override
    public int insertTCot(TCot tCot)
    {
        tCot.setCreateTime(DateUtils.getNowDate());
        return tCotMapper.insertTCot(tCot);
    }

    /**
     * 修改陪护床
     * 
     * @param tCot 陪护床
     * @return 结果
     */
    @Override
    public int updateTCot(TCot tCot)
    {
        return tCotMapper.updateTCot(tCot);
    }

    /**
     * 删除陪护床对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTCotByIds(String ids)
    {
        return tCotMapper.deleteTCotByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除陪护床信息
     * 
     * @param id 陪护床ID
     * @return 结果
     */
    @Override
    public int deleteTCotById(Long id)
    {
        return tCotMapper.deleteTCotById(id);
    }
    
    /**
     * 根据陪护床号查询陪护床信息
     * 
     * @param cotNo 陪护床号
     * @return 陪护床
     */
    @Override
    public TCot selectTCotByCotNo(String cotNo)
    {
        return tCotMapper.selectTCotByCotNo(cotNo);
    }

    @Override
    public TCot findTCotByCotNo(TCot tCot) {
        return tCotMapper.findTCotByCotNo(tCot);
    }

    @Override
    public TableDataInfo findBindingSGCotPage(TCot cot, Map<String, Object> map) {
        PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()),
                Integer.parseInt(map.get("pageSize").toString()));
        List<TCotBindingVO> list = tCotMapper.findBindingSGCotList(cot);;
        PageInfo<TCotBindingVO> pageInfo = new PageInfo<TCotBindingVO>(list);
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(0);
        rspData.setRows(pageInfo.getList());
        rspData.setTotal(pageInfo.getTotal());
        return rspData;
    }

    @Override
    public TableDataInfo findUnboundSGOfCotPage(TCot cot, Map<String, Object> map) {
        PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()),
                Integer.parseInt(map.get("pageSize").toString()));
        List<TCotBindingVO> list = tCotMapper.findUnboundSGOfCotList(cot);;
        PageInfo<TCotBindingVO> pageInfo = new PageInfo<TCotBindingVO>(list);
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(0);
        rspData.setRows(pageInfo.getList());
        rspData.setTotal(pageInfo.getTotal());
        return rspData;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateBatchSGOfCotRelationship(Long sgId, String cotIds) {
        //1.查看下陪护床是否已经绑定了干系组
        int cotBindingSGNum = tCotMapper.findBindingSGByCotIds(Convert.toStrArray(cotIds));
        if(cotBindingSGNum > 0){
            throw new BusinessException("选中记录中存在已经绑定了干系组的，请重新选择");
        }
        //2.陪护床绑定干系组
        Map<String,Object> params = new HashMap<>();
        params.put("stakeholderGroupId",sgId);
        params.put("cotIdArray",Convert.toStrArray(cotIds));
        tCotMapper.updateBatchSGOfCotRelationship(params);
        return 1;
    }

    @Override
    public int cancleCotBindingSGByCotId(Long cotId) {
        return tCotMapper.cancleCotBindingSGByCotId(cotId);
    }

    @Override
    public int cancleCotBindingSGByCondition(Long sgId, String cotIds) {
        Map<String,Object> params = new HashMap<>();
        params.put("stakeholderGroupId",sgId);
        params.put("cotIdArray",Convert.toStrArray(cotIds));
        return tCotMapper.cancleCotBindingSGByCondition(params);
    }
    
    @Override
    public AjaxResult importCotRecord(SysUser loginUser, List<Map<String,Object>> exceDataList) {
        List<TCot> readCotList = new ArrayList<TCot>();
        Date now = new Date();
        List<String> errorInfoList = new ArrayList<String>();//存放错误的单元格信息
        Map<String,Integer> uqCotNoCheck = new HashMap<>();//存放每个单元格陪护床号信息，key:陪护床号，value:行号
        if(StringUtils.isNotNull(exceDataList) && CollectionUtils.isNotEmpty(exceDataList)){
            log.info("开始解析excel中数据共计条数为【" + exceDataList.size() + "条】");
            for (int j=1;j<exceDataList.size();j++) {
                Map<String,Object> excelMapObj = exceDataList.get(j);
                int cellIsNullNum = 0;
                for(int i=0;i<Constants.COT_CELL_VALUES.length;i++) {
                    Object o = excelMapObj.get(Constants.COT_CELL_VALUES[i]);
                    if(o== null || "".equals(o.toString().trim())) {
                        cellIsNullNum = cellIsNullNum + 1;
                    }
                }
                if(cellIsNullNum == Constants.COT_CELL_VALUES.length) {
                    continue;//因为此行数据所有的单元格都没有内容，直接不解析此行
                }

                TCot tcot = new TCot();//要插入的实体
                int readExcelCurrentLine = j + 1;//读取excel的当前行号
                String cotNo = this.returnStrTrimObj(excelMapObj.get(Constants.COT_CELL_VALUES[0]));//第一个单元格陪护床号
                if(StringUtils.isNotEmpty(cotNo)){
                    if(cotNo.length() <= 32){
                        Integer lineN = uqCotNoCheck.get(cotNo);
                        if(StringUtils.isNotNull(lineN)){
                            //会员卡号重复
                            errorInfoList.add("第" + lineN + "行和第" + readExcelCurrentLine + "行陪护床号重复!");
                        }else{
                        	 TCot tCotUniqueResult =tCotMapper.selectTCotByCotNo(cotNo);
                            if(StringUtils.isNotNull(tCotUniqueResult)){
                                //陪护床号重复
                                errorInfoList.add("第" + readExcelCurrentLine + "行陪护床号系统中已存在!");
                            }else{
                            	tcot.setCotNo(cotNo);
                            }
                        }
                        //放入集合中，提供给其他数据校验
                        uqCotNoCheck.put(cotNo,readExcelCurrentLine);
                    }else{
                        errorInfoList.add("第" + readExcelCurrentLine + "行陪护床号最多能输入" + 32 + "个字符!");
                    }
                }else{
                    errorInfoList.add("第" + readExcelCurrentLine + "行陪护床号不能为空!");
                }
                String status = this.returnStrTrimObj(excelMapObj.get(Constants.COT_CELL_VALUES[1]));//第三个单元格会员卡金额
                if(StringUtils.isNotEmpty(status)){
                     if("维修中".equals(status)){
                    	 tcot.setStatus("00");
                     }else if("闲置中".equals(status)){
                    	 tcot.setStatus("01");
                     }else if("使用中".equals(status)){
                    	 tcot.setStatus("02");
                     }else if("保修中".equals(status)){
                    	 tcot.setStatus("03");
                     }else if("已报废".equals(status)){
                    	 tcot.setStatus("04");
                     }else{
                    	 errorInfoList.add("第" + readExcelCurrentLine + "行状态不正确!");
                     }
                }else{
                    errorInfoList.add("第" + readExcelCurrentLine + "行状态不能为空!");
                }

                String remark = this.returnStrTrimObj(excelMapObj.get(Constants.COT_CELL_VALUES[2]));//第四个单元格备注
                tcot.setRemark(remark);
                tcot.setCreateBy(loginUser.getUserId()+"");
                tcot.setCreateTime(now);
                readCotList.add(tcot);
            }
            if(CollectionUtils.isNotEmpty(errorInfoList)){
                return AjaxResult.error("导入失败,错误信息如下：",errorInfoList);
            }

            if(CollectionUtils.isEmpty(readCotList)){
                return AjaxResult.error("导入的excel无数据!");
            }
            int successNum = tCotMapper.insertBatchTCot(readCotList);//批量插入
            log.info("校验成功的数据要插入的数据{" + successNum + "}条");
            log.info("校验成功的数据=" + readCotList.toString());
        }else{
            return AjaxResult.error("导入的excel无数据!");
        }
        return AjaxResult.success("导入成功" + readCotList.size() + "条!");
    }

    @Override
    public List<TCotBindingVO> findBindingSGCotList(TCot cot) {
        return tCotMapper.findBindingSGCotList(cot);
    }

    /**
     * @Title: returnStrTrimObj
     * @Description: 传入String类型的值，去空返回
     * @param obj
     * @return
     * @date 2019年7月1日
     */
    private String returnStrTrimObj(Object obj) {
        String returnVal = "";
        if(obj != null) {
            returnVal = obj.toString().trim();
        }
        return returnVal;
    }
}
