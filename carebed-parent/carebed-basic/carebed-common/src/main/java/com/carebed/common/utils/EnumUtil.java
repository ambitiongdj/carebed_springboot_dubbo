package com.carebed.common.utils;

import com.carebed.common.enums.CodeEnum;

/**
 * Created by gdj
 */
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(String code, Class<T> enumClass) {
        for (T each: enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
