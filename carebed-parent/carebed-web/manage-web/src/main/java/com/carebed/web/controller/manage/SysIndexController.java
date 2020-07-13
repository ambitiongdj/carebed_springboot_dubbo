package com.carebed.web.controller.manage;

import java.util.List;

import com.alibaba.dubbo.config.annotation.Reference;
import com.carebed.common.config.Global;
import com.carebed.common.core.controller.BaseController;
import com.carebed.common.utils.StringUtils;
import com.carebed.manage.entity.SysMenu;
import com.carebed.manage.entity.SysUser;
import com.carebed.manage.service.ISysConfigService;
import com.carebed.manage.service.ISysMenuService;
import com.carebed.web.shiro.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 首页 业务处理
 * 
 * @author iysk
 */
@Controller
public class SysIndexController extends BaseController
{
    @Reference
    private ISysMenuService menuService;

    @Reference
    private ISysConfigService configService;

    @Value("${file.realmName}")
    private String realmName;
    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap)
    {
        // 取身份信息
        SysUser user = ShiroUtils.getSysUser();
        // 根据用户id取出菜单
        List<SysMenu> menus = menuService.selectMenusByUser(user);
        mmap.put("menus", menus);
        if(StringUtils.isNotBlank(user.getAvatar())) {
        	user.setAvatar(realmName+user.getAvatar());
        }
        mmap.put("user", user);
        mmap.put("sideTheme", configService.selectConfigByKey("sys.index.sideTheme"));
        mmap.put("skinName", configService.selectConfigByKey("sys.index.skinName"));
        mmap.put("copyrightYear", Global.getCopyrightYear());
        mmap.put("demoEnabled", Global.isDemoEnabled());
        return "index";
    }

    // 切换主题
    @GetMapping("/system/switchSkin")
    public String switchSkin(ModelMap mmap)
    {
        return "skin";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap)
    {
        mmap.put("version", Global.getVersion());
        return "main";
    }
}
