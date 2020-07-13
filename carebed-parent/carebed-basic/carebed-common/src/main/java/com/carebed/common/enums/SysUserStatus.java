package com.carebed.common.enums;

public enum SysUserStatus implements CodeEnum{

    REGULAR_EMPLOYEE("0", "正式员工"),
    EMPLOYEE_TURNOVER("1", "离职员工"),
    PROBATION_EMPLOYEE("2", "试用员工");

    private final String code;
    private final String info;

    SysUserStatus(String code, String info)
    {
        this.code = code;
        this.info = info;
    }

    @Override
    public String getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }
}
