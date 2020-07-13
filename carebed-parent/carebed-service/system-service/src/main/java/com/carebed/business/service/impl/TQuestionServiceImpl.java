package com.carebed.business.service.impl;

import java.util.List;
import com.carebed.common.core.text.Convert;
import com.carebed.common.utils.DateUtils;
import com.carebed.business.entity.TQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.carebed.business.mapper.TQuestionMapper;
import com.carebed.business.service.ITQuestionService;

/**
 * 问题Service业务层处理
 * 
 * @author wjl
 * @date 2020-06-17
 */
@Service
public class TQuestionServiceImpl implements ITQuestionService 
{
    @Autowired
    private TQuestionMapper tQuestionMapper;

    /**
     * 查询问题
     * 
     * @param id 问题ID
     * @return 问题
     */
    @Override
    public TQuestion selectTQuestionById(Long id)
    {
        return tQuestionMapper.selectTQuestionById(id);
    }

    /**
     * 查询问题列表
     * 
     * @param tQuestion 问题
     * @return 问题
     */
    @Override
    public List<TQuestion> selectTQuestionList(TQuestion tQuestion)
    {
        return tQuestionMapper.selectTQuestionList(tQuestion);
    }

    /**
     * 新增问题
     * 
     * @param tQuestion 问题
     * @return 结果
     */
    @Override
    public int insertTQuestion(TQuestion tQuestion)
    {
        tQuestion.setCreateTime(DateUtils.getNowDate());
        return tQuestionMapper.insertTQuestion(tQuestion);
    }

    /**
     * 修改问题
     * 
     * @param tQuestion 问题
     * @return 结果
     */
    @Override
    public int updateTQuestion(TQuestion tQuestion)
    {
        return tQuestionMapper.updateTQuestion(tQuestion);
    }

    /**
     * 删除问题对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTQuestionByIds(String ids)
    {
        return tQuestionMapper.deleteTQuestionByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除问题信息
     * 
     * @param id 问题ID
     * @return 结果
     */
    @Override
    public int deleteTQuestionById(Long id)
    {
        return tQuestionMapper.deleteTQuestionById(id);
    }
}
