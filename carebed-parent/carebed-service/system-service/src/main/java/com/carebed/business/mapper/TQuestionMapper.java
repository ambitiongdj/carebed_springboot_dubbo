package com.carebed.business.mapper;

import java.util.List;
import com.carebed.business.entity.TQuestion;

/**
 * 问题Mapper接口
 * 
 * @author wjl
 * @date 2020-06-17
 */
public interface TQuestionMapper 
{
    /**
     * 查询问题
     * 
     * @param id 问题ID
     * @return 问题
     */
    public TQuestion selectTQuestionById(Long id);

    /**
     * 查询问题列表
     * 
     * @param tQuestion 问题
     * @return 问题集合
     */
    public List<TQuestion> selectTQuestionList(TQuestion tQuestion);

    /**
     * 新增问题
     * 
     * @param tQuestion 问题
     * @return 结果
     */
    public int insertTQuestion(TQuestion tQuestion);

    /**
     * 修改问题
     * 
     * @param tQuestion 问题
     * @return 结果
     */
    public int updateTQuestion(TQuestion tQuestion);

    /**
     * 删除问题
     * 
     * @param id 问题ID
     * @return 结果
     */
    public int deleteTQuestionById(Long id);

    /**
     * 批量删除问题
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTQuestionByIds(String[] ids);
}
