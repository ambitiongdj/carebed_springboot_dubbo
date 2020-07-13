package com.carebed.web.shiro.service;

import com.carebed.exception.user.UserPasswordNotMatchException;
import com.carebed.manage.entity.SysUser;
import com.carebed.web.manager.AsyncManager;
import com.carebed.web.manager.factory.AsyncFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Component;

import com.carebed.common.constant.Constants;
import com.carebed.common.utils.MessageUtils;

/**
 * 登录密码方法
 * 
 * @author iysk
 */
@Component
public class SysPasswordService
{
    public void validate(SysUser user, String password)
    {
        String loginName = user.getLoginName();

        if (!matches(user, password))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginName, Constants.LOGIN_FAIL,
                    MessageUtils.message("user.password.not.match")));
            throw new UserPasswordNotMatchException();
        }
    }

    public boolean matches(SysUser user, String newPassword)
    {
        return user.getPassword().equals(encryptPassword(user.getLoginName(), newPassword, user.getSalt()));
    }

    public String encryptPassword(String username, String password, String salt)
    {
        return new Md5Hash(username + password + salt).toHex().toString();
    }
}
