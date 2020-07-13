package com.carebed.common.enums;

public enum MemberCardStatusEnum implements CodeEnum {

    STATUS0("0", "注销"),
    STATUS1("1", "正常"),
    STATUS2("2", "注销申请")
    ;

    private String code;

    private String message;

    MemberCardStatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return message;
    }
}
