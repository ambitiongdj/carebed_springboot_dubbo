package com.carebed.business.service;

import java.util.List;
import java.util.Map;

import com.carebed.business.entity.TDoctor;
import com.carebed.business.entity.TDoctorBindingVO;
import com.carebed.business.entity.TDoctorVo;
import com.carebed.common.core.page.TableDataInfo;

/**
 * 医生Service接口
 * 
 * @author wjl
 * @date 2020-06-17
 */
public interface ITDoctorService 
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
     * 分页查询医生列表
     * 
     * @param tDoctor 医生
     * @return 医生集合
     */
    public TableDataInfo selectTDoctorPageList(TDoctorVo tDoctor,Map<String, Object> map);

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
     * 批量删除医生
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTDoctorByIds(String ids);

    /**
     * 删除医生信息
     * 
     * @param id 医生ID
     * @return 结果
     */
    public int deleteTDoctorById(Long id);
    /**
     * 根据条件查询医生信息是否唯一
     * 
     * @param tDoctor
     * @return 结果
     */
    public String checkDoctorUnique(TDoctor tDoctor);
    /**
     * @Description: 当前干系组绑定的医生
     * @Author: GDJ
     * @Date: 2020/06/18
     * @param doctor:
     * @param map:
     * @return: com.carebed.common.core.page.TableDataInfo
     **/
    public TableDataInfo findBindingSGOfDoctorPage(TDoctor doctor, Map<String, Object> map);

    /**
     * @Description: 没有绑定干系组的医生
     * @Author: GDJ
     * @Date: 2020/06/18
     * @param doctor:
     * @param map:
     * @return: com.carebed.common.core.page.TableDataInfo
     **/
    public TableDataInfo findUnboundSGOfDoctorPage(TDoctor doctor, Map<String, Object> map);

    /**
     * @Description: 查找绑定的干系组的医生
     * @Author: GDJ
     * @Date: 2020/06/18
     * @param doctor:
     * @return: java.util.List<com.carebed.business.entity.TDoctorBoundVO>
     **/
    public List<TDoctorBindingVO> findBindingSGOfDoctorList(TDoctor doctor);
}
