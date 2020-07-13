package com.carebed.enums;

import com.carebed.common.enums.CodeEnum;

public enum ResultEnum implements CodeEnum {

    SUCCESS("0000", "成功"),
    
    /**操作失败*/
	OPERATE_ERROR("9001","操作失败"),
	OPERATE_ERROR_EXCEPTION("9002","操作失败，发生异常"),
	OPERATE_ERROR_TIMEOUT("9003","操作失败，请求超时"),
	OPERATE_ERROR_NODATA("9004","操作失败，未找到数据"),
	OPERATE_ERROR_TOKEN("9005","token未找到或已失效~"),
	OPERATE_ERROR_PERMISSION("9006","操作失败，登录账号无权限"),
	OPERATE_LOGIN_ERROR_TO_PHONE("9007","您的手机号已变更，请重新登录"),
    FAIL("9999", "失败"),
    
    /**参数错误*/
	PARAMETER_LOST("2000","参数丢失,数据不完整"),
	PARAMETER_NULL("2001","参数不能为空"),
	PARAMETER_DATATYPE_NOT_MATCH("2002","参数类型不匹配,数据格式不正确"),
    
    SYSTEM_EXCEPTION("500","系统异常"),
    PARARM_ERROR("1001", "提交参数不正确"),
    ;

	private String code;

    private String message;

    ResultEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
    
    public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
    
}
