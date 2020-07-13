package com.carebed.business.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import java.util.Map;
import com.carebed.business.entity.TCot;
import com.carebed.business.entity.TCotVo;
import com.carebed.business.entity.TCotBindingVO;


/**
 * 陪护床Mapper接口
 * 
 * @author wjl
 * @date 2020-06-17
 */
public interface TCotMapper 
{
    /**
     * 查询陪护床
     * 
     * @param id 陪护床ID
     * @return 陪护床
     */
    public TCot selectTCotById(Long id);

    /**
     * 查询陪护床列表
     * 
     * @param tCot 陪护床
     * @return 陪护床集合
     */
    public List<TCotVo> selectTCotList(TCotVo tCotVo);

    /**
     * 新增陪护床
     * 
     * @param tCot 陪护床
     * @return 结果
     */
    public int insertTCot(TCot tCot);

    /**
     * 修改陪护床
     * 
     * @param tCot 陪护床
     * @return 结果
     */
    public int updateTCot(TCot tCot);

    /**
     * 删除陪护床
     * 
     * @param id 陪护床ID
     * @return 结果
     */
    public int deleteTCotById(Long id);

    /**
     * 批量删除陪护床
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTCotByIds(String[] ids);
    
    /**
     * 查询陪护床
     * 
     * @param cotNo 陪护床号
     * @return 陪护床
     */
    public TCot selectTCotByCotNo(String cotNo);
    
    /**
     * @Description: 根据干系组id解绑和床位的关系
     * @Author: GDJ
     * @Date: 2020/06/17
     * @param toStrArray:
     * @return: int
     **/
    public int batchUnboundBySGIds(String[] toStrArray);

    /**
     * 根据cotNo查询陪护床
     *
     * @param tCot 陪护床
     * @return 陪护床
     */
    public TCot findTCotByCotNo(TCot tCot);

    /**
     * @Description: 查找绑定干系组的陪护床数据
     * @Author: GDJ
     * @Date: 2020/06/18
     * @param cot:
     * @return: java.util.List<com.carebed.business.entity.TCotBindingVO>
     **/
    public List<TCotBindingVO> findBindingSGCotList(TCot cot);

    /**
     * @Description: 查找未绑定干系组的陪护床数据
     * @Author: GDJ
     * @Date: 2020/06/18
     * @param cot:
     * @return: java.util.List<com.carebed.business.entity.TCotBindingVO>
     **/
    public List<TCotBindingVO> findUnboundSGOfCotList(TCot cot);

    /**
     * @Description:查看下陪护床是否已经绑定了干系组
     * @Author: GDJ
     * @Date: 2020/06/18
     * @param toStrArray:
     * @return: int
     **/
    public int findBindingSGByCotIds(String[] toStrArray);

    /**
     * @Description: 批量绑定干系组
     * @Author: GDJ
     * @Date: 2020/06/18
     * @param params:
     * @return: int
     **/
    public int updateBatchSGOfCotRelationship(Map<String, Object> params);

    /**
     * @Description: 解除陪护床绑定干系组
     * @Author: GDJ
     * @Date: 2020/06/18
     * @param cotId:
     * @return: int
     **/
    public int cancleCotBindingSGByCotId(Long cotId);

    /**
     * @Description: 根据条件解除干系组和陪护床的关系
     * @Author: GDJ
     * @Date: 2020/06/18
     * @param params:
     * @return: int
     **/
    public int cancleCotBindingSGByCondition(Map<String, Object> params);
    /**
     * 批量插入
     *
     * @param list
     * @return int
     */
    public int insertBatchTCot(@Param("readCotList") List<TCot> readCotList);
    
}
