package com.carebed.constants;

public class Constants {

    public static final String AUTHORIZATION = "authorization"; //存放Authorization的header字段

    /* 发送手机号验证码-redis中key的前缀 */
    public static final String MSG_SEND_CODE_PREFIX = "MSG:CODE:";

    /* 发送手机号验证码次数-redis中key的前缀 */
    public static final String MSG_SEND_CODE_NUM_PREFIX = "MSG:CODE:NUM:";

    /*短信验证码有效时间*/
    public static final long MSG_CODE_EFFECTIVE_TIME = 180;

    /* 发送短信验证码次数限制 */
    public static final int MSG_SEND_CODE_NUM_LIMIT = 10;

    /*验证码长度*/
    public static final int CODE_LENGTH = 6;

    /* 会员：1正常 0注销*/
    public static final String member_status_disale = "0";
    public static final String member_status_enable = "1";

    public static final int DEFAULT_PAGE_NO = 1;
    public static final int DEFAULT_PAGE_SIZE = 10;
}
