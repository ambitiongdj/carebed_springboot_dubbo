package com.carebed.util;

import com.carebed.common.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TokenUtil
 * @Description 生成token工具
 * @Author GDJ
 * @Date 2020/06/03
 * @Version 1.0
 */
public class TokenUtil {

    /**
     * @Description: 生成token
     * @Author: GDJ
     * @Date: 2020/06/02
     * @param memberCardNo:
     * @param memberName:
     * @param memberMobile:
     * @return: java.lang.String
     **/
    public static String generateTokenToUser(Long memberId,String memberCardNo, String memberName, String memberMobile) throws Exception {
        Map<String, Object> jwtParams = new HashMap<String, Object>();
        jwtParams.put("memberId", StringUtils.convert(memberId));
        jwtParams.put("memberCardNo", StringUtils.convert(memberCardNo));
        jwtParams.put("memberCardNo", StringUtils.convert(memberCardNo));
        jwtParams.put("memberName", StringUtils.convert(memberName));
        jwtParams.put("memberMobile", StringUtils.convert(memberMobile));

        return JWTUtil.generateToken(jwtParams, 7 * 24 * 60 * 60 * 1000);
    }

}
