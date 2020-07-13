package com.carebed.common.constant;

/**
 * 用户常量信息
 * 
 * @author ruoyi
 */
public class UserConstants
{
    /**
     * 平台内系统用户的唯一标志
     */
    public static final String SYS_USER = "SYS_USER";

    /** 正常状态 */
    public static final String NORMAL = "0";

    /** 异常状态 */
    public static final String EXCEPTION = "1";

    /** 用户封禁状态 */
    public static final String USER_BLOCKED = "1";

    /** 角色封禁状态 */
    public static final String ROLE_BLOCKED = "1";

    /** 部门正常状态 */
    public static final String DEPT_NORMAL = "0";

    /** 字典正常状态 */
    public static final String DICT_NORMAL = "0";

    /** 是否为系统默认（是） */
    public static final String YES = "Y";

    /**
     * 用户名长度限制
     */
    public static final int USERNAME_MIN_LENGTH = 2;
    public static final int USERNAME_MAX_LENGTH = 20;

    /** 登录名称是否唯一的返回结果码 */
    public final static String USER_NAME_UNIQUE = "0";
    public final static String USER_NAME_NOT_UNIQUE = "1";

    /** 手机号码是否唯一的返回结果 */
    public final static String USER_PHONE_UNIQUE = "0";
    public final static String USER_PHONE_NOT_UNIQUE = "1";

    /** e-mail 是否唯一的返回结果 */
    public final static String USER_EMAIL_UNIQUE = "0";
    public final static String USER_EMAIL_NOT_UNIQUE = "1";
    
    /** 身份证号 是否唯一的返回结果 */
    public final static String USER_USERCARDCODE_UNIQUE = "0";
    public final static String USER_USERCARDCODE_NOT_UNIQUE = "1";

    /** 部门名称是否唯一的返回结果码 */
    public final static String DEPT_NAME_UNIQUE = "0";
    public final static String DEPT_NAME_NOT_UNIQUE = "1";

    /** 机构编码是否唯一的返回结果码 */
    public final static String DEPT_CODE_UNIQUE = "0";
    public final static String DEPT_CODE_NOT_UNIQUE = "1";

    /** 角色名称是否唯一的返回结果码 */
    public final static String ROLE_NAME_UNIQUE = "0";
    public final static String ROLE_NAME_NOT_UNIQUE = "1";

    /** 岗位名称是否唯一的返回结果码 */
    public final static String POST_NAME_UNIQUE = "0";
    public final static String POST_NAME_NOT_UNIQUE = "1";

    /** 角色权限是否唯一的返回结果码 */
    public final static String ROLE_KEY_UNIQUE = "0";
    public final static String ROLE_KEY_NOT_UNIQUE = "1";

    /** 岗位编码是否唯一的返回结果码 */
    public final static String POST_CODE_UNIQUE = "0";
    public final static String POST_CODE_NOT_UNIQUE = "1";

    /** 菜单名称是否唯一的返回结果码 */
    public final static String MENU_NAME_UNIQUE = "0";
    public final static String MENU_NAME_NOT_UNIQUE = "1";

    /** 字典类型是否唯一的返回结果码 */
    public final static String DICT_TYPE_UNIQUE = "0";
    public final static String DICT_TYPE_NOT_UNIQUE = "1";

    /** 参数键名是否唯一的返回结果码 */
    public final static String CONFIG_KEY_UNIQUE = "0";
    public final static String CONFIG_KEY_NOT_UNIQUE = "1";

    /**
     * 密码长度限制
     */
    public static final int PASSWORD_MIN_LENGTH = 5;
    public static final int PASSWORD_MAX_LENGTH = 20;

    /**
     * 用户类型
     */
    public static final String SYSTEM_USER_TYPE = "00";
    public static final String REGISTER_USER_TYPE = "01";

    /**
     * 手机号码格式限制
     */
    public static final String MOBILE_PHONE_NUMBER_PATTERN = "^0{0,1}(13[0-9]|15[0-9]|14[0-9]|18[0-9])[0-9]{8}$";

    /**
     * 邮箱格式限制
     */
    public static final String EMAIL_PATTERN = "^((([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+(\\.([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+)*)|((\\x22)((((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(([\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x7f]|\\x21|[\\x23-\\x5b]|[\\x5d-\\x7e]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(\\\\([\\x01-\\x09\\x0b\\x0c\\x0d-\\x7f]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF]))))*(((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(\\x22)))@((([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.?";


    public static final String USER_STATE_ZS = "0";//员工用户使用状态：0:正式
    public static final String USER_STATE_SY = "2";//员工用户使用状态：2:试用
    public static final String USER_STATE_TY = "1";//员工用户使用状态：1:停用

    public static final String USER_SEX_NAN = "0";//员工性别 0是男
    public static final String USER_SEX_NV = "1";//员工性别 1是女

    public static final String POSITION_Code_JTTUANYUNYING = "00"; //集团运营
    public static final String POSITION_Code_YYJL = "01"; //运营中心负责人
    public static final String POSITION_Code_SYBJL = "02"; //事业部经理
    public static final String POSITION_Code_DQJL = "03"; //大区经理
    public static final String POSITION_Code_FGSJL = "04"; //分公司经理
    public static final String POSITION_Code_YYBJL = "05"; //营业部经理
    public static final String POSITION_Code_TDJL = "06"; //团队经理
    public static final String POSITION_Code_KHJL = "07"; //客户经理
    public static final String POSITION_Code_FGSNQ = "08"; //分公司综合内勤
    public static final String POSITION_Code_DQNQ = "09"; //大区综合内勤
    public static final String POSITION_Code_SYBNQ = "10"; //事业部综合内勤
    public static final String POSITION_Code_JTTUANCAIWU = "11"; //集团财务
    public static final String POSITION_Code_JTTUANRENLI = "12"; //集团人力

    public static final String POSTLEVEL_Code_CB = "00"; //筹备
    public static final String POSTLEVEL_Code_01 = "01"; //一级
    public static final String POSTLEVEL_Code_02 = "02"; //二级
    public static final String POSTLEVEL_Code_03 = "03"; //三级
    public static final String POSTLEVEL_Code_04 = "04"; //四级
    public static final String POSTLEVEL_Code_05 = "05"; //五级
    public static final String POSTLEVEL_Code_06 = "06"; //高级
    public static final String POSTLEVEL_Code_07 = "07"; //副总裁




}
