package com.carebed.business.service;

import java.util.List;
import java.util.Map;

import com.carebed.business.entity.TCot;
import com.carebed.business.entity.TCotBindingVO;
import com.carebed.business.entity.TCotVo;
import com.carebed.common.core.domain.AjaxResult;
import com.carebed.common.core.page.TableDataInfo;
import com.carebed.manage.entity.SysUser;

/**
 * 陪护床Service接口
 * 
 * @author wjl
 * @date 2020-06-17
 */
public interface ITCotService 
{
    /**
     * 查询陪护床
     * 
     * @param id 陪护床ID
     * @return 陪护床
     */
    public TCot selectTCotById(Long id);
    /**
     * 分页查询陪护床
     * 
     * @return 陪护床
     */
    public TableDataInfo selectTCotPageList(TCotVo tCotVo,
			Map<String, Object> map);

    /**
     * 查询陪护床列表
     * 
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
     * 批量删除陪护床
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTCotByIds(String ids);

    /**
     * 删除陪护床信息
     * 
     * @param id 陪护床ID
     * @return 结果
     */
    public int deleteTCotById(Long id);
    
    /**
     * 根据陪护床号查询陪护床信息
     * 
     * @param cotNo 陪护床号
     * @return 陪护床
     */
    public TCot selectTCotByCotNo(String cotNo);
    
    /**
     * 根据cotNo查询陪护床
     *
     * @param tCot 陪护床
     * @return 陪护床
     */
    public TCot findTCotByCotNo(TCot tCot);

    /**
     * @Description: 查找绑定干系组的陪护床
     * @Author: GDJ
     * @Date: 2020/06/18
     * @param cot:
     * @param map:
     * @return: com.carebed.common.core.page.TableDataInfo
     **/
    public TableDataInfo findBindingSGCotPage(TCot cot, Map<String, Object> map);

    /**
     * @Description: 查找未绑定干系组的陪护床
     * @Author: GDJ
     * @Date: 2020/06/18
     * @param cot:
     * @param map:
     * @return: com.carebed.common.core.page.TableDataInfo
     **/
    public TableDataInfo findUnboundSGOfCotPage(TCot cot, Map<String, Object> map);

    /**
     * @Description: 批量更新干系组和床位的关系
     * @Author: GDJ
     * @Date: 2020/06/18
     * @param sgId:
     * @param cotIds:
     * @return: int
     **/
    public int updateBatchSGOfCotRelationship(Long sgId, String cotIds);

    /**
     * @Description: 解除陪护床绑定干系组
     * @Author: GDJ
     * @Date: 2020/06/18
     * @param cotId:
     * @return: int
     **/
    public int cancleCotBindingSGByCotId(Long cotId);

    /**
     * @Description: 批量解除陪护床绑定干系组
     * @Author: GDJ
     * @Date: 2020/06/18
     * @param sgId:
     * @param cotIds:
     * @return: int
     **/
    public int cancleCotBindingSGByCondition(Long sgId, String cotIds);
    /**
     * 导入
     *
     * @param exceDataList
     * @return AjaxResult
     */
    public AjaxResult importCotRecord(SysUser loginUser, List<Map<String,Object>> exceDataList);

    /**
     * @Description: 查找干系组绑定的陪护床
     * @Author: GDJ
     * @Date: 2020/06/19
     * @param cot:
     * @return: java.util.List<com.carebed.business.entity.TCotBindingVO>
     **/
    public List<TCotBindingVO> findBindingSGCotList(TCot cot);
}
