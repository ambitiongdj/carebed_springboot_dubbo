package com.carebed.business.service;

import java.util.List;
import com.carebed.business.entity.TWechatUser;

/**
 * 微信用户Service接口
 * 
 * @author wjl
 * @date 2020-06-17
 */
public interface ITWechatUserService 
{
    /**
     * 查询微信用户
     * 
     * @param id 微信用户ID
     * @return 微信用户
     */
    public TWechatUser selectTWechatUserById(Long id);

    /**
     * 查询微信用户列表
     * 
     * @param tWechatUser 微信用户
     * @return 微信用户集合
     */
    public List<TWechatUser> selectTWechatUserList(TWechatUser tWechatUser);

    /**
     * 新增微信用户
     * 
     * @param tWechatUser 微信用户
     * @return 结果
     */
    public int insertTWechatUser(TWechatUser tWechatUser);

    /**
     * 修改微信用户
     * 
     * @param tWechatUser 微信用户
     * @return 结果
     */
    public int updateTWechatUser(TWechatUser tWechatUser);

    /**
     * 批量删除微信用户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTWechatUserByIds(String ids);

    /**
     * 删除微信用户信息
     * 
     * @param id 微信用户ID
     * @return 结果
     */
    public int deleteTWechatUserById(Long id);
}
