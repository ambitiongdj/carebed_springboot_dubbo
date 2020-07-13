package com.carebed.common.enums;

public enum CustomerEnum implements CodeEnum {

    CERTIFICATE_TYPE1("1", "身份证"),
    CERTIFICATE_TYPE2("2", "护照"),
    CERTIFICATE_TYPE3("3", "军官证"),
    CERTIFICATE_TYPE4("4", "港澳台回乡证或台胞证"),
    CERTIFICATE_TYPE5("5", "统一社会信用代码"),
    ;

    private String code;

    private String message;

    CustomerEnum(String code, String message) {
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
