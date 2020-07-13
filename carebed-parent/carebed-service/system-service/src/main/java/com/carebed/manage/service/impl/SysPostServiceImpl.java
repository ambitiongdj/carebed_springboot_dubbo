package com.carebed.manage.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.carebed.common.constant.Constants;
import com.carebed.exception.BusinessException;
import com.carebed.manage.entity.SysPost;
import com.carebed.manage.entity.SysRole;
import com.carebed.manage.entity.SysUser;
import com.carebed.manage.entity.SysUserRole;
import com.carebed.manage.mapper.*;
import com.carebed.manage.service.ISysPostService;
import com.carebed.utils.CacheUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.carebed.common.constant.UserConstants;
import com.carebed.common.core.page.TableDataInfo;
import com.carebed.common.core.text.Convert;
import com.carebed.common.utils.StringUtils;

/**
 * 岗位信息 服务层处理
 * 
 * @author iysk
 */
@Service
public class SysPostServiceImpl implements ISysPostService
{
    @Autowired
    private SysPostMapper postMapper;

    @Autowired
    private SysUserPostMapper userPostMapper;

    @Autowired
    private SysRoleMapper roleMapper;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private SysUserRoleMapper userRoleMapper;
    @Autowired
    private SysDictDataMapper sysDictDataMapper;
    @Autowired
    private CacheUtils cacheUtils;

    /**
     * 查询岗位信息集合
     * 
     * @param post 岗位信息
     * @return 岗位信息集合
     */
    @Override
    public List<SysPost> selectPostList(SysPost post)
    {
        return postMapper.selectPostList(post);
    }
    /**
     * 查询岗位信息集合分页
     * 
     * @param post 岗位信息
     * @return 岗位信息集合
     */
    @Override
    public TableDataInfo selectPostPageList(SysPost post,Map<String,Object> map)
    {
    	 if(map.get("orderBy")!=null&&StringUtils.isNotBlank(map.get("orderBy").toString())) {
    		 PageHelper.orderBy(map.get("orderBy").toString());
    	 }
    	 PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()), Integer.parseInt(map.get("pageSize").toString()));
    	 List<SysPost> list=postMapper.selectPostList(post);
    	 PageInfo<SysPost> pageInfo = new PageInfo<SysPost>(list);
    	 TableDataInfo rspData = new TableDataInfo();
         rspData.setCode(0);
         rspData.setRows(pageInfo.getList());
         rspData.setTotal(pageInfo.getTotal());
        return rspData;
    }

    /**
     * 查询所有岗位
     * 
     * @return 岗位列表
     */
    @Override
    public List<SysPost> selectPostAll()
    {
        return postMapper.selectPostAll();
    }

    /**
     * 根据用户ID查询岗位
     * 
     * @param userId 用户ID
     * @return 岗位列表
     */
    @Override
    public List<SysPost> selectPostsByUserId(Long userId)
    {
        List<SysPost> userPosts = postMapper.selectPostsByUserId(userId);
        List<SysPost> posts = postMapper.selectPostAll();
        for (SysPost post : posts)
        {
            for (SysPost userRole : userPosts)
            {
                if (post.getPostId().longValue() == userRole.getPostId().longValue())
                {
                    post.setFlag(true);
                    break;
                }
            }
        }
        return posts;
    }
    /**
     	* 根据岗位ID查询角色
     * 
     * @param postId 用户ID
     * @return 岗位列表
     */
    @Override
    public List<SysRole> selectRoleById(Long postId)
    {
    	SysPost post=postMapper.selectPostById(postId);
    	String roleIds=post.getRoleId();
    	List<SysRole> roles = roleMapper.selectRoleList(new SysRole());
    	if(StringUtils.isNotBlank(roleIds)) {
    		String []roleStr=roleIds.split(",");
    		Long[] roleLong=new Long[roleStr.length];
    		for(int i=0;i<roleStr.length;i++) {
    			roleLong[i]=Long.parseLong(roleStr[i]);
    		}
    		for (SysRole role : roles)
            {
                for (Long userRoleId : roleLong)
                {
                    if (role.getRoleId().longValue() == userRoleId)
                    {
                        role.setFlag(true);
                        break;
                    }
                }
            }
    	}
        
        return roles;
         
    }

    /**
     * 通过岗位ID查询岗位信息
     * 
     * @param postId 岗位ID
     * @return 角色对象信息
     */
    @Override
    public SysPost selectPostById(Long postId)
    {
        return postMapper.selectPostById(postId);
    }

    /**
     * 批量删除岗位信息
     * 
     * @param ids 需要删除的数据ID
     * @throws Exception
     */
    @Override
    public int deletePostByIds(String ids) throws BusinessException
    {
        Long[] postIds = Convert.toLongArray(ids);
        for (Long postId : postIds)
        {
            SysPost post = selectPostById(postId);
            if (countUserPostById(postId) > 0)
            {
                throw new BusinessException(String.format("%1$s已分配,不能删除", post.getPostName()));
            }
            cacheUtils.remove(getCacheName(),getCacheKey(post.getPostCode()));
            cacheUtils.remove(getLevelCacheName(),getLevelCacheKey(post.getPostCode()));
        }
        return postMapper.deletePostByIds(postIds);
    }

    /**
     * 新增保存岗位信息
     * 
     * @param post 岗位信息
     * @return 结果
     */
    @Override
    public int insertPost(SysPost post)
    {
    	//新增岗位
    	int row=postMapper.insertPost(post);
        return row;
    }

    /**
     * 修改保存岗位信息
     * 
     * @param post 岗位信息
     * @return 结果
     */
    @Override
    public int updatePost(SysPost post)
    {
    	SysPost oldpost=postMapper.selectPostById(post.getPostId());
    	if(!oldpost.getRoleId().equals(post.getRoleId())) {
    		//根据岗位查找到所属的用户
        	SysUser sysuser=new SysUser();
        	sysuser.setPostCode(post.getPostCode());
        	 String roleIds = post.getRoleId();
        	 List<SysUserRole> userRoleList=new  ArrayList<SysUserRole>();
             if (StringUtils.isNotBlank(roleIds)){
            	 String[]roleIdStr=roleIds.split(",");
            	 for(String roleIdst:roleIdStr) {
            		Long roleId=Long.parseLong(roleIdst);
            	  	List<SysUser> userList=userMapper.selectUserList(sysuser);
                	//填充角色关系对象
            		Long[]userIds=null;
                	if(userList!=null&&userList.size()>0) {
                		userIds=new Long[userList.size()];
                    	for(int i=0;i<userList.size();i++) {
                    		SysUserRole userrole=new SysUserRole();
                    		userIds[i]=userList.get(i).getUserId();
                    		userrole.setRoleId(roleId);
                    		userrole.setUserId(userIds[i]);
                    		userRoleList.add(userrole);
                    	}
                    	//批量删除角色跟用户的关系
                    	userRoleMapper.deleteUserRoleInfos(null, userIds);
                	}
            	 }
             }
             //批量新增角色跟用户的关系
        	if(userRoleList!=null&&userRoleList.size()>0) {
        		userRoleMapper.batchUserRole(userRoleList);
        	}
    	}
        return postMapper.updatePost(post);
    }

    /**
     * 通过岗位ID查询岗位使用数量
     * 
     * @param postId 岗位ID
     * @return 结果
     */
    @Override
    public int countUserPostById(Long postId)
    {
        return userPostMapper.countUserPostById(postId);
    }

    /**
     * 校验岗位名称是否唯一
     * 
     * @param post 岗位信息
     * @return 结果
     */
    @Override
    public String checkPostNameUnique(SysPost post)
    {
        Long postId = StringUtils.isNull(post.getPostId()) ? -1L : post.getPostId();
        SysPost info = postMapper.checkPostNameUnique(post.getPostName());
        if (StringUtils.isNotNull(info) && info.getPostId().longValue() != postId.longValue())
        {
            return UserConstants.POST_NAME_NOT_UNIQUE;
        }
        return UserConstants.POST_NAME_UNIQUE;
    }

    /**
     * 校验岗位编码是否唯一
     * 
     * @param post 岗位信息
     * @return 结果
     */
    @Override
    public String checkPostCodeUnique(SysPost post)
    {
        Long postId = StringUtils.isNull(post.getPostId()) ? -1L : post.getPostId();
        SysPost info = postMapper.checkPostCodeUnique(post.getPostCode());
        if (StringUtils.isNotNull(info) && info.getPostId().longValue() != postId.longValue())
        {
            return UserConstants.POST_CODE_NOT_UNIQUE;
        }
        return UserConstants.POST_CODE_UNIQUE;
    }

    /**
     * 获取cache name
     *
     * @return 缓存名
     */
    private String getCacheName()
    {
        return Constants.SYS_POST_CACHE;
    }

    /**
     * 设置cache key
     *
     * @param configKey 参数键
     * @return 缓存键key
     */
    private String getCacheKey(String configKey)
    {
        return Constants.SYS_POST_KEY + configKey;
    }

    /**
     * 获取cache name
     *
     * @return 缓存名
     */
    private String getLevelCacheName()
    {
        return Constants.SYS_POSTLEVEL_CACHE;
    }

    /**
     * 设置cache key
     *
     * @param configKey 参数键
     * @return 缓存键key
     */
    private String getLevelCacheKey(String configKey)
    {
        return Constants.SYS_POSTLEVEL_KEY + configKey;
    }
}
