package com.carebed.business.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.carebed.business.entity.TWechatUser;
import com.carebed.business.mapper.TWechatUserMapper;
import com.carebed.business.service.ITWechatUserService;
import com.carebed.common.core.text.Convert;
import com.carebed.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 微信用户Service业务层处理
 * 
 * @author wjl
 * @date 2020-06-17
 */
@Service
public class TWechatUserServiceImpl implements ITWechatUserService 
{
    @Autowired
    private TWechatUserMapper tWechatUserMapper;

    /**
     * 查询微信用户
     * 
     * @param id 微信用户ID
     * @return 微信用户
     */
    @Override
    public TWechatUser selectTWechatUserById(Long id)
    {
        return tWechatUserMapper.selectTWechatUserById(id);
    }

    /**
     * 查询微信用户列表
     * 
     * @param tWechatUser 微信用户
     * @return 微信用户
     */
    @Override
    public List<TWechatUser> selectTWechatUserList(TWechatUser tWechatUser)
    {
        return tWechatUserMapper.selectTWechatUserList(tWechatUser);
    }

    /**
     * 新增微信用户
     * 
     * @param tWechatUser 微信用户
     * @return 结果
     */
    @Override
    public int insertTWechatUser(TWechatUser tWechatUser)
    {
        tWechatUser.setCreateTime(DateUtils.getNowDate());
        return tWechatUserMapper.insertTWechatUser(tWechatUser);
    }

    /**
     * 修改微信用户
     * 
     * @param tWechatUser 微信用户
     * @return 结果
     */
    @Override
    public int updateTWechatUser(TWechatUser tWechatUser)
    {
        tWechatUser.setUpdateTime(DateUtils.getNowDate());
        return tWechatUserMapper.updateTWechatUser(tWechatUser);
    }

    /**
     * 删除微信用户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTWechatUserByIds(String ids)
    {
        return tWechatUserMapper.deleteTWechatUserByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除微信用户信息
     * 
     * @param id 微信用户ID
     * @return 结果
     */
    @Override
    public int deleteTWechatUserById(Long id)
    {
        return tWechatUserMapper.deleteTWechatUserById(id);
    }
}
