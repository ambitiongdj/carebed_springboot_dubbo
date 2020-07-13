package com.carebed.business.mapper;

import java.util.List;

import com.carebed.business.entity.TDoctor;
import com.carebed.business.entity.TDoctorVo;
import com.carebed.business.entity.TDoctorBindingVO;

/**
 * 医生Mapper接口
 * 
 * @author wjl
 * @date 2020-06-17
 */
public interface TDoctorMapper 
{
    /**
     * 查询医生
     * 
     * @param id 医生ID
     * @return 医生
     */
    public TDoctor selectTDoctorById(Long id);

    /**
     * 查询医生列表
     * 
     * @param tDoctor 医生
     * @return 医生集合
     */
    public List<TDoctorVo> selectTDoctorList(TDoctorVo tDoctorVo);
 

    /**
     * 新增医生
     * 
     * @param tDoctor 医生
     * @return 结果
     */
    public int insertTDoctor(TDoctor tDoctor);

    /**
     * 修改医生
     * 
     * @param tDoctor 医生
     * @return 结果
     */
    public int updateTDoctor(TDoctor tDoctor);

    /**
     * 删除医生
     * 
     * @param id 医生ID
     * @return 结果
     */
    public int deleteTDoctorById(Long id);

    /**
     * 批量删除医生
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTDoctorByIds(String[] ids);
    
    /**
     * 查询医生
     * 
     * @param tDoctor
     * @return 医生
     */
    public int checkDoctorUnique(TDoctor tDoctor);
    /**
     * @Description: 干系组绑定列表
     * @Author: GDJ
     * @Date: 2020/06/18
     * @param doctor:
     * @return: java.util.List<TDoctorBoundVO>
     **/
    public List<TDoctorBindingVO> findBindingSGOfDoctorList(TDoctor doctor);

    /**
     * @Description: 没有绑定干系组的医生
     * @Author: GDJ
     * @Date: 2020/06/18
     * @param doctor:
     * @return: java.util.List<com.carebed.business.entity.TDoctorBoundVO>
     **/
    public List<TDoctorBindingVO> findUnboundSGOfDoctorList(TDoctor doctor);
}
