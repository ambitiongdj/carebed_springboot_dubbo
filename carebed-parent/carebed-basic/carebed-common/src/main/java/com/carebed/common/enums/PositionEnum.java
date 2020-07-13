package com.carebed.common.enums;

public enum PositionEnum implements CodeEnum{

     /*
    * 00	集团运营
    01	运营中心经理
    02	事业部经理
    03	大区经理
    04	分公司经理
    05	营业部经理
    06	团队经理
    07	客户经理
    08	分公司综合内勤
    09	大区综合内勤
    10	事业部综合内勤
    11	集团财务
    12	集团人力
    * */

    POSITION_CODE_00("00","集团运营"),
    POSITION_CODE_01("01","运营中心经理"),
    POSITION_CODE_02("02","事业部经理"),
    POSITION_CODE_03("03","大区经理"),
    POSITION_CODE_04("04","分公司经理"),
    POSITION_CODE_05("05","营业部经理"),
    POSITION_CODE_06("06","团队经理"),
    POSITION_CODE_07("07","客户经理"),
    POSITION_CODE_08("08","分公司综合内勤"),
    POSITION_CODE_09("09","大区综合内勤"),
    POSITION_CODE_10("10","事业部综合内勤"),
    POSITION_CODE_11("11","集团财务"),
    POSITION_CODE_12("12","集团人力"),
    ;

    private String code;

    private String message;

    PositionEnum(String code, String message) {
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
