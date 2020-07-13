package com.carebed.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularUtil {

    /**
     * 金额验证:判断小数点后2位的数字的正则表达式
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("^(\\d+)(.\\d{0,2})?$"); // 判断小数点后2位的数字的正则表达式
        Matcher match = pattern.matcher(str);
        return match.matches();
    }

    /**
     * @param mobile: 手机号
     * @Description: 验证手机号格式
     * @Author: GDJ
     * @Date: 2020/06/02
     * @return: boolean
     **/
    public static boolean isMobile(String mobile) {
        Pattern pattern = Pattern.compile("^1[34578]\\d{9}$");
        Matcher match = pattern.matcher(mobile);
        return match.matches();
    }

    /**
     * @param str:
     * @Description: 验证字符串是非负整数
     * @Author: GDJ
     * @Date: 2020/06/05
     * @return: boolean
     **/
    public static boolean isPositiveInteger(String str) {
        Pattern pattern = Pattern.compile("^[1-9]\\d*|0$");
        return pattern.matcher(str).matches();
    }

    /**
     * @Description: 验证字符串是否是日期格式
     * @Author: GDJ
     * @Date: 2020/06/05
     * @param str:
     * @return: boolean
     **/
    public static boolean isValidDate(String str) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = (Date) formatter.parse(str);
            return str.equals(formatter.format(date));
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(isNumber("11"));
        System.out.println(isNumber("0.11"));
        System.out.println(isNumber("0.1"));
        System.out.println(isNumber("0.01"));
        System.out.println(isNumber("11.0"));
        System.out.println(isNumber("11.00"));
        System.out.println(isNumber("11.01"));
        System.out.println(isNumber("11.11"));
        System.out.println(isNumber("11.11"));
        System.out.println(isNumber("01.11"));
        System.out.println("---------------------------------");
        System.out.println(isNumber("01.110"));
        System.out.println(isNumber("01.111"));
        System.out.println(isNumber("01.000"));
        System.out.println(isNumber("01.000"));
        System.out.println(isNumber("01.000"));
        System.out.println(isNumber("01.000"));
        System.out.println(isNumber("01.000"));
        System.out.println(isNumber("-1.00"));
        System.out.println(isNumber("-0"));


        /*System.out.println(isMobile("15503553255"));
        System.out.println(isMobile("13503553255"));
        System.out.println(isMobile("14503553255"));
        System.out.println(isMobile("16503553255"));
        System.out.println(isMobile("17503553255"));
        System.out.println(isMobile("18503553255"));
        System.out.println(isMobile("19503553255"));
        System.out.println("---------------------------------------");
        System.out.println(isMobile("11503553255"));
        System.out.println(isMobile("12503553255"));*/


        /*System.out.println(isPositiveInteger("1.0"));
        System.out.println(isPositiveInteger("1.01"));
        System.out.println(isPositiveInteger("1.021"));
        System.out.println(isPositiveInteger("-1.0"));
        System.out.println(isPositiveInteger("-1.01"));
        System.out.println(isPositiveInteger("-1"));
        System.out.println(isPositiveInteger("-122"));
        System.out.println(isPositiveInteger("-0"));
        System.out.println(isPositiveInteger("0"));
        System.out.println(isPositiveInteger("01"));
        System.out.println("---------------------------------------");
        System.out.println(isPositiveInteger("11"));
        System.out.println(isPositiveInteger("1"));*/

/*
        System.out.println(isValidDate("2020-11-22 11:56:22"));
*/
    }
}
