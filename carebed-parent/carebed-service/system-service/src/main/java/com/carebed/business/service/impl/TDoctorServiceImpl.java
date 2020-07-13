package com.carebed.business.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.carebed.business.entity.TDoctor;
import com.carebed.business.entity.TDoctorBindingVO;
import com.carebed.business.entity.TDoctorVo;
import com.carebed.business.mapper.TDoctorMapper;
import com.carebed.business.service.ITDoctorService;
import com.carebed.common.core.page.TableDataInfo;
import com.carebed.common.core.text.Convert;
import com.carebed.common.utils.DateUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 医生Service业务层处理
 * 
 * @author wjl
 * @date 2020-06-17
 */
@Service
public class TDoctorServiceImpl implements ITDoctorService 
{
    @Autowired
    private TDoctorMapper tDoctorMapper;

    /**
     * 查询医生
     * 
     * @param id 医生ID
     * @return 医生
     */
    @Override
    public TDoctor selectTDoctorById(Long id)
    {
        return tDoctorMapper.selectTDoctorById(id);
    }

    /**
     * 查询医生列表
     * 
     * @return 医生
     */
    @Override
    public List<TDoctorVo> selectTDoctorList(TDoctorVo tDoctorVo)
    {
        return tDoctorMapper.selectTDoctorList(tDoctorVo);
    }
    
    
    /**
     * 分页查询医生列表
     * 
     * @return 医生
     */
    @Override
    public TableDataInfo selectTDoctorPageList(TDoctorVo tDoctorVo,Map<String, Object> map)
    {
    	
    	if (map.get("orderBy") != null
				&& StringUtils.isNotBlank(map.get("orderBy").toString())) {
			PageHelper.orderBy(map.get("orderBy").toString());
		}
		PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()),
				Integer.parseInt(map.get("pageSize").toString()));
		List<TDoctorVo> list = tDoctorMapper.selectTDoctorList(tDoctorVo);
		PageInfo<TDoctorVo> pageInfo = new PageInfo<TDoctorVo>(list);
		TableDataInfo rspData = new TableDataInfo();
		rspData.setCode(0);
		rspData.setRows(pageInfo.getList());
		rspData.setTotal(pageInfo.getTotal());
		return rspData;
    }


    /**
     * 新增医生
     * 
     * @param tDoctor 医生
     * @return 结果
     */
    @Override
    public int insertTDoctor(TDoctor tDoctor)
    {
        tDoctor.setCreateTime(DateUtils.getNowDate());
        return tDoctorMapper.insertTDoctor(tDoctor);
    }

    /**
     * 修改医生
     * 
     * @param tDoctor 医生
     * @return 结果
     */
    @Override
    public int updateTDoctor(TDoctor tDoctor)
    {
        tDoctor.setUpdateTime(DateUtils.getNowDate());
        return tDoctorMapper.updateTDoctor(tDoctor);
    }

    /**
     * 删除医生对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTDoctorByIds(String ids)
    {
        return tDoctorMapper.deleteTDoctorByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除医生信息
     * 
     * @param id 医生ID
     * @return 结果
     */
    @Override
    public int deleteTDoctorById(Long id)
    {
        return tDoctorMapper.deleteTDoctorById(id);
    }

	@Override
	public String checkDoctorUnique(TDoctor tDoctor) {
		int count=tDoctorMapper.checkDoctorUnique(tDoctor);
		if(count>0){
			return "1";
		}else{
			return "0";
		}
	}
	@Override
    public TableDataInfo findBindingSGOfDoctorPage(TDoctor doctor, Map<String, Object> map) {
        PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()),Integer.parseInt(map.get("pageSize").toString()));
        List<TDoctorBindingVO> list = tDoctorMapper.findBindingSGOfDoctorList(doctor);
        PageInfo<TDoctorBindingVO> pageInfo = new PageInfo<TDoctorBindingVO>(list);
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(0);
        rspData.setRows(pageInfo.getList());
        rspData.setTotal(pageInfo.getTotal());
        return rspData;
    }

    @Override
    public TableDataInfo findUnboundSGOfDoctorPage(TDoctor doctor, Map<String, Object> map) {
        PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()),Integer.parseInt(map.get("pageSize").toString()));
        List<TDoctorBindingVO> list = tDoctorMapper.findUnboundSGOfDoctorList(doctor);
        PageInfo<TDoctorBindingVO> pageInfo = new PageInfo<TDoctorBindingVO>(list);
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(0);
        rspData.setRows(pageInfo.getList());
        rspData.setTotal(pageInfo.getTotal());
        return rspData;
    }

    @Override
    public List<TDoctorBindingVO> findBindingSGOfDoctorList(TDoctor doctor) {
        return tDoctorMapper.findBindingSGOfDoctorList(doctor);
    }
}
