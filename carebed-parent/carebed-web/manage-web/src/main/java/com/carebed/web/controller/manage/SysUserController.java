package com.carebed.web.controller.manage;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.carebed.web.utils.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.carebed.common.annotation.Log;
import com.carebed.common.constant.UserConstants;
import com.carebed.common.core.controller.BaseController;
import com.carebed.common.core.domain.AjaxResult;
import com.carebed.common.core.page.TableDataInfo;
import com.carebed.common.enums.BusinessType;
import com.carebed.manage.entity.SysUser;
import com.carebed.manage.entity.SysUserVo;
import com.carebed.manage.service.ISysConfigService;
import com.carebed.manage.service.ISysPostService;
import com.carebed.manage.service.ISysRoleService;
import com.carebed.manage.service.ISysUserService;
import com.carebed.web.shiro.service.SysPasswordService;
import com.carebed.web.shiro.utils.ShiroUtils;

/**
 * 用户信息
 * 
 * @author iysk
 */
@Controller
@RequestMapping("/system/user")
public class SysUserController extends BaseController {
	private String prefix = "system/user";

	@Reference
	private ISysUserService userService;

	@Reference
	private ISysRoleService roleService;

	@Reference
	private ISysPostService postService;

	@Autowired
	private SysPasswordService passwordService;

	@Reference
	private ISysConfigService configService;

	@RequiresPermissions("system:user:view")
	@GetMapping()
	public String user(ModelMap mmap) {
		mmap.put("roles", roleService.selectRoleAll());
		mmap.put("posts", postService.selectPostAll());
		return prefix + "/user";
	}

	@RequiresPermissions("system:user:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SysUserVo user) {
		String postCode=ShiroUtils.getSysUser().getPostCode();
		//获取只看自己的岗位
		if(!postCode.equals("01")){
			user.setCheckBoxIds(ShiroUtils.getSysUser().getUserId()+"");
		}
		Map<String, Object> map = startMapPage();
		return userService.selectUserVoPageList(user, map);
	}

	@Log(title = "用户管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("system:user:export")
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(SysUserVo user) {
		//获取只看自己的岗位
		String postCode=ShiroUtils.getSysUser().getPostCode();
		if(!postCode.equals("01")){
			user.setCheckBoxIds(ShiroUtils.getSysUser().getUserId()+"");
		}		
		List<SysUserVo> list = userService.selectUserVoList(user);
		ExcelUtil<SysUserVo> util = new ExcelUtil<SysUserVo>(SysUserVo.class);
		return util.exportExcel(list, "用户数据");
	}

	@RequiresPermissions("system:user:view")
	@GetMapping("/importTemplate")
	@ResponseBody
	public AjaxResult importTemplate() {
		ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
		return util.importTemplateExcel("用户数据");
	}

	/**
	 * 新增用户
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap) {
		mmap.put("roles", roleService.selectRoleAll());
		mmap.put("posts", postService.selectPostAll());
		return prefix + "/add";
	}

	/**
	 * 新增保存用户
	 */
	@RequiresPermissions("system:user:add")
	@Log(title = "用户管理", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(@Validated SysUser user) {
		if (UserConstants.USER_PHONE_NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
			return error("新增用户'" + user.getUserName() + "'失败，手机号码已存在");
		}
		if (UserConstants.USER_USERCARDCODE_NOT_UNIQUE.equals(userService.checkUserCardCodeUnique(user))) {
			return error("新增用户'" + user.getUserName() + "'失败，身份证号已存在");
		}
		// 获取用户默认秘密
		String password = configService.selectConfigByKey("sys.user.initPassword");
		user.setPassword(password);
		// 获取用户的性别0男1女
		String sex = "0";
		if (user.getUserCardCode().length() == 18) {
			if (Integer.parseInt(user.getUserCardCode().substring(16).substring(0, 1)) % 2 == 0) {
				sex = "1";
			} else {
				sex = "0";
			}
		} else if (user.getUserCardCode().length() == 15) {
			String usex = user.getUserCardCode().substring(14, 15);// 用户的性别
			if (Integer.parseInt(usex) % 2 == 0) {
				sex = "1";
			} else {
				sex = "0";
			}
		}
		user.setSex(sex);
		user.setSalt(ShiroUtils.randomSalt());
		user.setLoginName(user.getPhonenumber());
		user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
		user.setCreateBy(ShiroUtils.getUserId() + "");
		return toAjax(userService.insertUser(user));
	}

	/**
	 * 修改用户
	 */
	@GetMapping("/edit/{userId}")
	public String edit(@PathVariable("userId") Long userId, ModelMap mmap) {
		SysUser user=userService.selectUserById(userId);
		mmap.put("user",user );
		mmap.put("roles", roleService.selectRolesByUserId(userId));
		mmap.put("posts", postService.selectPostsByUserId(userId));
		return prefix + "/edit";
	}

	/**
	 * 修改保存用户
	 */
	@RequiresPermissions("system:user:edit")
	@Log(title = "用户管理", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(@Validated SysUser user) {
		SysUser userold = userService.selectUserById(user.getUserId());
		if (!userold.getPhonenumber().equals(user.getPhonenumber())
				&& UserConstants.USER_PHONE_NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
			return error("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
		}
		if (!userold.getUserCardCode().equals(user.getUserCardCode())
				&& UserConstants.USER_USERCARDCODE_NOT_UNIQUE.equals(userService.checkUserCardCodeUnique(user))) {
			return error("修改用户'" + user.getUserName() + "'失败，身份证号已存在");
		}
		user.setUpdateBy(ShiroUtils.getUserId() + "");
		user.setUpdateTime(new Date());
		return toAjax(userService.updateUser(userold, user));
	}

	@RequiresPermissions("system:user:resetPwd")
	@Log(title = "重置密码", businessType = BusinessType.UPDATE)
	@GetMapping("/resetPwd/{userId}")
	public String resetPwd(@PathVariable("userId") Long userId, ModelMap mmap) {
		mmap.put("user", userService.selectUserById(userId));
		return prefix + "/resetPwd";
	}

	@RequiresPermissions("system:user:resetPwd")
	@Log(title = "重置密码", businessType = BusinessType.UPDATE)
	@PostMapping("/resetPwd")
	@ResponseBody
	public AjaxResult resetPwdSave(SysUser user) {
		user.setSalt(ShiroUtils.randomSalt());
		user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
		if (userService.resetUserPwd(user) > 0) {
			if (ShiroUtils.getUserId() == user.getUserId()) {
				ShiroUtils.setSysUser(userService.selectUserById(user.getUserId()));
			}
			return success();
		}
		return error();
	}

	@RequiresPermissions("system:user:remove")
	@Log(title = "用户管理", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		try {
			return toAjax(userService.deleteUserByIds(ids));
		} catch (Exception e) {
			return error(e.getMessage());
		}
	}

	/**
	 * 校验手机号码
	 */
	@PostMapping("/checkPhoneUnique")
	@ResponseBody
	public String checkPhoneUnique(SysUser user) {
		return userService.checkPhoneUnique(user);
	}

	/**
	 * 校验身份证号
	 */
	@PostMapping("/checkUserCardCodeUnique")
	@ResponseBody
	public String checkUserCardCodeUnique(SysUser user) {
		return userService.checkUserCardCodeUnique(user);
	}
}