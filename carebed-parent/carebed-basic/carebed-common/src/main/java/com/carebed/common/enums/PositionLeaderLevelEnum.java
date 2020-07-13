package com.carebed.common.enums;

public enum PositionLeaderLevelEnum implements CodeEnum{


    /**
        00	集团运营
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
    POSITION_LEADER_CODE_LEVEL_1("1","01"),
    POSITION_LEADER_CODE_LEVEL_2("2","02"),
    POSITION_LEADER_CODE_LEVEL_3("3","03"),
    POSITION_LEADER_CODE_LEVEL_4("4","04"),
    POSITION_LEADER_CODE_LEVEL_5("5","05"),
    POSITION_LEADER_CODE_LEVEL_6("6","06"),
    ;

    private String code;

    private String message;

    PositionLeaderLevelEnum(String code, String message) {
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
