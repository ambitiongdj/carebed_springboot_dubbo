package com.carebed.manage.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.carebed.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.carebed.common.annotation.DataScope;
import com.carebed.common.constant.UserConstants;
import com.carebed.common.core.page.TableDataInfo;
import com.carebed.common.core.text.Convert;
import com.carebed.common.utils.StringUtils;
import com.carebed.manage.entity.SysPost;
import com.carebed.manage.entity.SysRole;
import com.carebed.manage.entity.SysUser;
import com.carebed.manage.entity.SysUserPost;
import com.carebed.manage.entity.SysUserRole;
import com.carebed.manage.entity.SysUserVo;
import com.carebed.manage.mapper.SysConfigMapper;
import com.carebed.manage.mapper.SysPostMapper;
import com.carebed.manage.mapper.SysRoleMapper;
import com.carebed.manage.mapper.SysUserMapper;
import com.carebed.manage.mapper.SysUserPostMapper;
import com.carebed.manage.mapper.SysUserRoleMapper;
import com.carebed.manage.service.ISysUserService;

/**
 * 用户 业务层处理
 * 
 * @author iysk
 */
@Service
public class SysUserServiceImpl implements ISysUserService {
	@Autowired
	private SysUserMapper userMapper;

	@Autowired
	private SysRoleMapper roleMapper;

	@Autowired
	private SysPostMapper postMapper;

	@Autowired
	private SysUserPostMapper userPostMapper;

	@Autowired
	private SysUserRoleMapper userRoleMapper;

	@Autowired
	private SysConfigMapper sysConfigMapper;
 

	/**
	 * 根据条件分页查询已分配用户角色列表
	 *
	 * @param user
	 *            用户信息
	 * @return 用户信息集合信息
	 */
	@DataScope(deptAlias = "d", userAlias = "u")
	public List<SysUser> selectAllocatedList(SysUser user) {
		return userMapper.selectAllocatedList(user);
	}

	/**
	 * 根据条件分页查询未分配用户角色列表
	 *
	 * @param user
	 *            用户信息
	 * @return 用户信息集合信息
	 */
	@DataScope(deptAlias = "d", userAlias = "u")
	public List<SysUser> selectUnallocatedList(SysUser user) {
		return userMapper.selectUnallocatedList(user);
	}
	 
	/**
	 * 通过用户ID查询用户
	 *
	 * @param userId
	 *            用户ID
	 * @return 用户对象信息
	 */
	@Override
	public SysUser selectUserById(Long userId) {
		return userMapper.selectUserById(userId);
	}

	/**
	 * 通过用户ID查询用户和角色关联
	 *
	 * @param userId
	 *            用户ID
	 * @return 用户和角色关联列表
	 */
	public List<SysUserRole> selectUserRoleByUserId(Long userId) {
		return userRoleMapper.selectUserRoleByUserId(userId);
	}

	/**
	 * 通过用户ID删除用户
	 *
	 * @param userId
	 *            用户ID
	 * @return 结果
	 */
	@Override
	public int deleteUserById(Long userId) {
		// 删除用户与角色关联
		userRoleMapper.deleteUserRoleByUserId(userId);
		// 删除用户与岗位表
		userPostMapper.deleteUserPostByUserId(userId);
		return userMapper.deleteUserById(userId);
	}

	/**
	 * 批量删除用户信息
	 *
	 * @param ids
	 *            需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteUserByIds(String ids) throws BusinessException {
		Long[] userIds = Convert.toLongArray(ids);
		for (Long userId : userIds) {
			checkUserAllowed(new SysUser(userId));
		}
		
		return userMapper.deleteUserByIds(userIds);
	}

	/**
	 * 新增保存用户信息
	 *
	 * @param user
	 *            用户信息
	 * @return 结果
	 */
	@Override
	@Transactional
	public int insertUser(SysUser user) {
		// 新增用户信息
		int rows = userMapper.insertUser(user);

		// 根据岗位获取角色ids
		SysPost syspost = postMapper.checkPostCodeUnique(user.getPostCode());
		// 新增用户岗位关联
		Long[] postIds = new Long[1];
		postIds[0] = syspost.getPostId();
		user.setPostIds(postIds);
		insertUserPost(user);
		if (StringUtils.isNotBlank(syspost.getRoleId())) {
			String[] roleids = syspost.getRoleId().split(",");
			Long[] roleIds = new Long[roleids.length];
			for (int i = 0; i < roleids.length; i++) {
				roleIds[i] = Long.parseLong(roleids[i]);
			}
			user.setRoleIds(roleIds);
			// 新增用户与角色管理
			insertUserRole(user.getUserId(), user.getRoleIds());
		}
		
		return rows;
	}

	/**
	 * 注册用户信息
	 * 
	 * @param user
	 *            用户信息
	 * @return 结果
	 */
	public boolean registerUser(SysUser user) {
		user.setUserType(UserConstants.REGISTER_USER_TYPE);
		return userMapper.insertUser(user) > 0;
	}

	/**
	 * 修改保存用户信息
	 * 
	 * @param user
	 *            用户信息
	 * @return 结果
	 */
	@Override
	@Transactional
	public int updateUser(SysUser oldUser, SysUser user) {
		Long userId = user.getUserId();
		// 删除用户与角色关联
		userRoleMapper.deleteUserRoleByUserId(userId);
		// 删除用户与岗位关联
		userPostMapper.deleteUserPostByUserId(userId);
		// 根据岗位获取角色ids
		SysPost syspost = postMapper.selectPostByCode(user.getPostCode());
		// 新增用户岗位关联
		Long[] postIds = new Long[1];
		postIds[0] = syspost.getPostId();
		user.setPostIds(postIds);
		insertUserPost(user);
		if (StringUtils.isNotBlank(syspost.getRoleId())) {
			String[] roleids = syspost.getRoleId().split(",");
			Long[] roleIds = new Long[roleids.length];
			for (int i = 0; i < roleids.length; i++) {
				roleIds[i] = Long.parseLong(roleids[i]);
			}
			user.setRoleIds(roleIds);
			// 新增用户与角色管理
			insertUserRole(user.getUserId(), user.getRoleIds());
		}
		return userMapper.updateUser(user);
	}

	/**
	 * 修改用户个人详细信息
	 * 
	 * @param user
	 *            用户信息
	 * @return 结果
	 */
	@Override
	public int updateUserInfo(SysUser user) {
		return userMapper.updateUser(user);
	}

	/**
	 * 用户授权角色
	 * 
	 * @param userId
	 *            用户ID
	 * @param roleIds
	 *            角色组
	 */
	public void insertUserAuth(Long userId, Long[] roleIds) {
		userRoleMapper.deleteUserRoleByUserId(userId);
		insertUserRole(userId, roleIds);
	}

	/**
	 * 修改用户密码
	 * 
	 * @param user
	 *            用户信息
	 * @return 结果
	 */
	@Override
	public int resetUserPwd(SysUser user) {
		return updateUserInfo(user);
	}
	/**
	 * 校验用户是否允许操作
	 * 
	 * @param user
	 *            用户信息
	 */
	public void checkUserAllowed(SysUser user) {
		if (StringUtils.isNotNull(user.getUserId()) && user.isAdmin()) {
			throw new BusinessException("不允许操作超级管理员用户");
		}
	}

	/**
	 * 新增用户角色信息
	 * 
	 */
	public void insertUserRole(Long userId, Long[] roleIds) {
		if (StringUtils.isNotNull(roleIds)) {
			// 新增用户与角色管理
			List<SysUserRole> list = new ArrayList<SysUserRole>();
			for (Long roleId : roleIds) {
				SysUserRole ur = new SysUserRole();
				ur.setUserId(userId);
				ur.setRoleId(roleId);
				list.add(ur);
			}
			if (list.size() > 0) {
				userRoleMapper.batchUserRole(list);
			}
		}
	}

	/**
	 * 新增用户岗位信息
	 * 
	 * @param user
	 *            用户对象
	 */
	public void insertUserPost(SysUser user) {
		Long[] posts = user.getPostIds();
		if (StringUtils.isNotNull(posts)) {
			// 新增用户与岗位管理
			List<SysUserPost> list = new ArrayList<SysUserPost>();
			for (Long postId : posts) {
				SysUserPost up = new SysUserPost();
				up.setUserId(user.getUserId());
				up.setPostId(postId);
				list.add(up);
			}
			if (list.size() > 0) {
				userPostMapper.batchUserPost(list);
			}
		}
	}

 
	/**
	 * 校验手机号码是否唯一
	 *
	 * @param user
	 *            用户信息
	 * @return
	 */
	@Override
	public String checkPhoneUnique(SysUser user) {
		Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user
				.getUserId();
		SysUser info = userMapper.checkPhoneUnique(user.getPhonenumber());
		if (StringUtils.isNotNull(info)
				&& info.getUserId().longValue() != userId.longValue()) {
			return UserConstants.USER_PHONE_NOT_UNIQUE;
		}
		return UserConstants.USER_PHONE_UNIQUE;
	}

	/**
	 * 校验身份证号是否唯一
	 *
	 * @param user
	 *            用户信息
	 * @return
	 */
	@Override
	public String checkUserCardCodeUnique(SysUser user) {
		Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user
				.getUserId();
		SysUser info = userMapper.checkUserCardCodeUnique(user
				.getUserCardCode());
		if (StringUtils.isNotNull(info)
				&& info.getUserId().longValue() != userId.longValue()) {
			return UserConstants.USER_USERCARDCODE_NOT_UNIQUE;
		}
		return UserConstants.USER_USERCARDCODE_UNIQUE;
	}

	 
  
	@Override
	public List<SysUserVo> selectUserVoList(SysUserVo user) {
		return userMapper.selectUserVoList(user);
	}

	@Override
	public TableDataInfo selectUserVoPageList(SysUserVo user,
			Map<String, Object> map) {

		if (map.get("orderBy") != null
				&& StringUtils.isNotBlank(map.get("orderBy").toString())) {
			PageHelper.orderBy(map.get("orderBy").toString());
		}
		PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()),
				Integer.parseInt(map.get("pageSize").toString()));
		List<SysUserVo> list = userMapper.selectUserVoList(user);
		PageInfo<SysUserVo> pageInfo = new PageInfo<SysUserVo>(list);
		TableDataInfo rspData = new TableDataInfo();
		rspData.setCode(0);
		rspData.setRows(pageInfo.getList());
		rspData.setTotal(pageInfo.getTotal());
		return rspData;
	}
	

	/**
	 * 通过手机号码查询用户
	 *
	 * @param phoneNumber
	 *            手机号码
	 * @return 用户对象信息
	 */
	@Override
	public SysUser selectUserByPhoneNumber(String phoneNumber) {
		return userMapper.selectUserByPhoneNumber(phoneNumber);
	}


	/**
	 * 查询用户所属角色组
	 * 
	 * @param userId
	 *            用户ID
	 * @return 结果
	 */
	@Override
	public String selectUserRoleGroup(Long userId) {
		List<SysRole> list = roleMapper.selectRolesByUserId(userId);
		StringBuffer idsStr = new StringBuffer();
		for (SysRole role : list) {
			idsStr.append(role.getRoleName()).append(",");
		}
		if (StringUtils.isNotEmpty(idsStr.toString())) {
			return idsStr.substring(0, idsStr.length() - 1);
		}
		return idsStr.toString();
	}


	/**
	 * 查询用户所属岗位组
	 * 
	 * @param userId
	 *            用户ID
	 * @return 结果
	 */
	@Override
	public String selectUserPostGroup(Long userId) {
		List<SysPost> list = postMapper.selectPostsByUserId(userId);
		StringBuffer idsStr = new StringBuffer();
		for (SysPost post : list) {
			idsStr.append(post.getPostName()).append(",");
		}
		if (StringUtils.isNotEmpty(idsStr.toString())) {
			return idsStr.substring(0, idsStr.length() - 1);
		}
		return idsStr.toString();
	}

	/**
	 * 通过用户名查询用户
	 *
	 * @param userName
	 *            用户名
	 * @return 用户对象信息
	 */
	@Override
	public SysUser selectUserByLoginName(String userName) {
		return userMapper.selectUserByLoginName(userName);
	}

	/**
	 * 校验登录名称是否唯一
	 * 
	 * @param loginName
	 *            用户名
	 * @return
	 */
	@Override
	public String checkLoginNameUnique(String loginName) {
		int count = userMapper.checkLoginNameUnique(loginName);
		if (count > 0) {
			return UserConstants.USER_NAME_NOT_UNIQUE;
		}
		return UserConstants.USER_NAME_UNIQUE;
	}

	 
  
	
}
