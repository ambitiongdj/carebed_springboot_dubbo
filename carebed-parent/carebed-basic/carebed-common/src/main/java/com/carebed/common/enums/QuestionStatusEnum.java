package com.carebed.common.enums;

public enum QuestionStatusEnum implements CodeEnum {

    STATUS0("00", "待处理"),
    STATUS1("01", "已处理"),
    ;

    private String code;

    private String message;

    QuestionStatusEnum(String code, String message) {
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
