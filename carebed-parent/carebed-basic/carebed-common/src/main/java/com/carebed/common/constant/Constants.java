package com.carebed.common.constant;

/**
 * 通用常量信息
 * 
 * @author ruoyi
 */
public class Constants
{
    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * 通用成功标识
     */
    public static final String SUCCESS = "0";

    /**
     * 通用失败标识
     */
    public static final String FAIL = "1";

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";
    
    /**
     * 注册
     */
    public static final String REGISTER = "Register";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";

    /**
     * 参数管理 cache name
     */
    public static final String SYS_CONFIG_CACHE = "carebed-config";

    /**
     * 参数管理 cache key
     */
    public static final String SYS_CONFIG_KEY = "carebed_config:";

    /**
     * 部门管理 cache name
     */
    public static final String SYS_DEPT_CACHE = "carebed-dept";
    /**
     * 部门管理 cache key
     */
    public static final String SYS_DEPT_KEY = "carebed-dept:";

    /**
     * 字典管理 cache name
     */
    public static final String SYS_DICT_CACHE = "carebed-dict";

    /**
     * 字典管理 cache key
     */
    public static final String SYS_DICT_KEY = "carebed_dict:";

    /**
     * 资源映射路径 前缀
     */
    public static final String RESOURCE_PREFIX = "/profile";

    /**
     * 岗位管理 cache name
     */
    public static final String SYS_POST_CACHE = "carebed-post";

    /**
     * 岗位管理 cache key
     */
    public static final String SYS_POST_KEY = "carebed-post:";

    /**
     * 岗位职级管理 cache name
     */
    public static final String SYS_POSTLEVEL_CACHE = "carebed-postlevel";

    /**
     * 岗位管理 cache key
     */
    public static final String SYS_POSTLEVEL_KEY = "carebed-postlevel:";


    /**
     * 干系组表 状态:0:无效;1:有效
     **/
    public static final String STAKEHOLDER_GROUP_STATUS0 = "0";
    public static final String STAKEHOLDER_GROUP_STATUS1 = "1";

    /**
     * 干系组表 组名称:0:不重复;1:重复
     **/
    public static final String STAKEHOLDER_GROUP_NAME_UNIQUE = "0";
    public static final String STAKEHOLDER_GROUP_NAME_NOT_UNIQUE = "1";

    /**
     * 绑定干系组表 状态:0:未绑定;1:已绑定
     **/
    public static final String BOUND_SG_STATUS0 = "0";
    public static final String BOUND_SG_STATUS1 = "1";
    
    /**
     * 会员卡导入模板表头
     */
    public static final String[] COT_CELL_VALUES = { "cotNo", "status",  "remark"};
}
