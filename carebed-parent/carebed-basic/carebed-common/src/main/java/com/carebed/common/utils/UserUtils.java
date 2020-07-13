package com.carebed.common.utils;

import com.carebed.common.constant.UserConstants;


/**
 * 用户代码转换工具类
 *
 * @author yml
 */
public class UserUtils {
    /**
     * 根据岗位代码，计算出机构代码长度
     * @param deptLevel
     * @return
     */
    public static int getDeptDCodeLen(String deptLevel) {
        int parentDeptCodeLen = 2;
        if ("1".equals(deptLevel)) {        //运营岗位
            parentDeptCodeLen = 2;
        } else if ("2".equals(deptLevel)) {  //事业部岗位
            parentDeptCodeLen = 4;
        } else if ("3".equals(deptLevel)) {  //大区岗位
            parentDeptCodeLen = 6;
        } else if ("4".equals(deptLevel)) {  //分公司岗位
            parentDeptCodeLen = 8;
        } else if ("5".equals(deptLevel)) {  //营业部岗位
            parentDeptCodeLen = 10;
        } else if ("6".equals(deptLevel)) {  //团队岗位
            parentDeptCodeLen = 12;
        }
        return parentDeptCodeLen;
    }
    /**
     * 根据岗位代码，计算出机构代码长度
     * @param positionCode
     * @return
     */
    public static int getUserDeptCodeLen(String positionCode) {
        int parentDeptCodeLen = 2;
        if ("01".equals(positionCode)) {        //运营岗位
            parentDeptCodeLen = 2;
        } else if ("02".equals(positionCode) || "10".equals(positionCode)) {  //事业部岗位
            parentDeptCodeLen = 4;
        } else if ("03".equals(positionCode) || "09".equals(positionCode)) {  //大区岗位
            parentDeptCodeLen = 6;
        } else if ("04".equals(positionCode) || "08".equals(positionCode)) {  //分公司岗位
            parentDeptCodeLen = 8;
        } else if ("05".equals(positionCode)) {  //营业部岗位
            parentDeptCodeLen = 10;
        } else if ("06".equals(positionCode)) {  //团队岗位
            parentDeptCodeLen = 12;
        } else if ("07".equals(positionCode)) {  //客户经理岗位
            parentDeptCodeLen = 12;
        }else if ("00".equals(positionCode) || "11".equals(positionCode) || "12".equals(positionCode)) {  //集团岗位
            parentDeptCodeLen = 0;
        }
        return parentDeptCodeLen;
    }
    /**
     * 返回职位编号
     * @param position
     * @return
     */
    public static String getPositionCode(String position){
        String dictCode = null;
        if(position.contains("运营中心负责人")){
            dictCode = UserConstants.POSITION_Code_YYJL;
        }else if(position.contains("事业部经理")){
            dictCode = UserConstants.POSITION_Code_SYBJL;
        }else if(position.contains("大区经理")){
            dictCode = UserConstants.POSITION_Code_DQJL;
        }else if(position.contains("分公司经理")){
            dictCode = UserConstants.POSITION_Code_FGSJL;
        }else if(position.contains("营业部经理")){
            dictCode = UserConstants.POSITION_Code_YYBJL;
        }else if(position.contains("团队经理")){
            dictCode = UserConstants.POSITION_Code_TDJL;
        }else if(position.contains("客户经理")){
            dictCode = UserConstants.POSITION_Code_KHJL;
        }else if(position.contains("分公司综合内勤")){
            dictCode = UserConstants.POSITION_Code_FGSNQ;
        }else if(position.contains("大区综合内勤")){
            dictCode = UserConstants.POSITION_Code_DQNQ;
        }else if(position.contains("事业部综合内勤")){
            dictCode = UserConstants.POSITION_Code_SYBNQ;
        }else if(position.contains("集团运营")){
            dictCode = UserConstants.POSITION_Code_JTTUANYUNYING;
        }else if(position.contains("集团财务")){
            dictCode = UserConstants.POSITION_Code_JTTUANCAIWU;
        }else if(position.contains("集团人力")){
            dictCode = UserConstants.POSITION_Code_JTTUANRENLI;
        }
        return dictCode;
    }
    /** 返回岗位职级
     * @param postLevel
     * @return
     */
    public static String getPostLevelCode(String postLevel){
        String dictCode = null;
        if(postLevel.contains("筹备")){
            dictCode = UserConstants.POSTLEVEL_Code_CB;
        }else if(postLevel.contains("一级")){
            dictCode = UserConstants.POSTLEVEL_Code_01;
        }else if(postLevel.contains("二级")){
            dictCode = UserConstants.POSTLEVEL_Code_02;
        }else if(postLevel.contains("三级")){
            dictCode = UserConstants.POSTLEVEL_Code_03;
        }else if(postLevel.contains("四级")){
            dictCode = UserConstants.POSTLEVEL_Code_04;
        }else if(postLevel.contains("五级")){
            dictCode = UserConstants.POSTLEVEL_Code_05;
        }else if(postLevel.contains("高级")){
            dictCode = UserConstants.POSTLEVEL_Code_06;
        }else if(postLevel.contains("副总裁")){
            dictCode = UserConstants.POSTLEVEL_Code_07;
        }
        return dictCode;
    }

    /**
     * 返回职位名称
     * @param postCode
     * @return
     */
    public static String getPositionName(String postCode){
        String dictName = null;
        if(UserConstants.POSITION_Code_YYJL.equals(postCode)){
            dictName ="运营中心负责人";
        }else if(UserConstants.POSITION_Code_SYBJL.equals(postCode)){
            dictName = "事业部经理";
        }else if(UserConstants.POSITION_Code_DQJL.equals(postCode)){
            dictName = "大区经理";
        }else if(UserConstants.POSITION_Code_FGSJL.equals(postCode)){
            dictName = "分公司经理";
        }else if(UserConstants.POSITION_Code_YYBJL.equals(postCode)){
            dictName = "营业部经理";
        }else if(UserConstants.POSITION_Code_TDJL.equals(postCode)){
            dictName = "团队经理";
        }else if(UserConstants.POSITION_Code_KHJL.equals(postCode)){
            dictName = "客户经理";
        }else if(UserConstants.POSITION_Code_FGSNQ.equals(postCode)){
            dictName = "分公司综合内勤";
        }else if(UserConstants.POSITION_Code_DQNQ.equals(postCode)){
            dictName = "大区综合内勤";
        }else if(UserConstants.POSITION_Code_SYBNQ.equals(postCode)){
            dictName = "事业部综合内勤";
        }else if(UserConstants.POSITION_Code_JTTUANYUNYING.equals(postCode)){
            dictName = "集团运营";
        }else if(UserConstants.POSITION_Code_JTTUANCAIWU.equals(postCode)){
            dictName = "集团财务";
        }else if(UserConstants.POSITION_Code_JTTUANRENLI.equals(postCode)){
            dictName = "集团人力";
        }
        return dictName;
    }
    /** 返回岗位职级名称
     * @param levelCode
     * @return
     */
    public static String getPostLevelName(String levelCode){
        String dictName = null;
        if(UserConstants.POSTLEVEL_Code_CB.equals(levelCode)){
            dictName = "筹备";
        }else if(UserConstants.POSTLEVEL_Code_01.equals(levelCode)){
            dictName = "一级";
        }else if(UserConstants.POSTLEVEL_Code_02.equals(levelCode)){
            dictName = "二级";
        }else if(UserConstants.POSTLEVEL_Code_03.equals(levelCode)){
            dictName = "三级";
        }else if(UserConstants.POSTLEVEL_Code_04.equals(levelCode)){
            dictName = "四级";
        }else if(UserConstants.POSTLEVEL_Code_05.equals(levelCode)){
            dictName = "五级";
        }else if(UserConstants.POSTLEVEL_Code_06.equals(levelCode)){
            dictName = "高级";
        }else if(UserConstants.POSTLEVEL_Code_07.equals(levelCode)){
            dictName = "副总裁";
        }
        return dictName;
    }

    /**
     * 获取员工状态代码
     * @param status
     * @return
     */
    public static String getUserStatus(String status){
        String statusCode = null;
        if ("正式".equals(status)) {
            statusCode = UserConstants.USER_STATE_ZS;
        } else if ("试用".equals(status)) {
            statusCode = UserConstants.USER_STATE_SY;
        } else {
            statusCode = UserConstants.USER_STATE_TY;
        }
        return statusCode;
    }

    /**
     * 获取员工状态名称
     * @param statusCode
     * @return
     */
    public static String getUserStatusName(String statusCode){
        String statusName = null;
        if (UserConstants.USER_STATE_ZS.equals(statusCode)) {
            statusName = "正式";
        } else if (UserConstants.USER_STATE_SY.equals(statusCode)) {
            statusName = "试用";
        } else {
            statusName = "离职";
        }
        return statusName;
    }
}
