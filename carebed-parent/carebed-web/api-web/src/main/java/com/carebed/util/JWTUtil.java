package com.carebed.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.inject.internal.Maps;
import com.carebed.common.utils.StringUtils;

public class JWTUtil {

	public static String generateToken(Map<String, Object> params,long expires) throws IllegalArgumentException, UnsupportedEncodingException {		
		
		Algorithm algorithm = Algorithm.HMAC256("secret");
		Map<String, Object> map = Maps.newHashMap();
		map.put("alg", "HS256");
		map.put("typ", "JWT");
		
		Builder builder = JWT.create().withHeader(map);
		
		Iterator<Entry<String, Object>> entries = params.entrySet().iterator();
		while (entries.hasNext()) { 
			Entry<String, Object> entry = entries.next();
			String key = entry.getKey();
			Object value = entry.getValue();
			Class<?> valueType = value.getClass();
			
			if(BigDecimal.class.isAssignableFrom(valueType)){
				builder.withClaim(key, Double.parseDouble(StringUtils.convert(value)));
			}else if(Integer.class.isAssignableFrom(valueType) || int.class.isAssignableFrom(valueType)){
				builder.withClaim(key, Integer.parseInt(StringUtils.convert(value)));
			}else if(String.class.isAssignableFrom(valueType)){
				builder.withClaim(key, StringUtils.convert(value));
			}else if(Long.class.isAssignableFrom(valueType) || long.class.isAssignableFrom(valueType)){
				builder.withClaim(key, Long.valueOf(StringUtils.convert(value)));
			}
		}

		return builder.withExpiresAt(new Date(System.currentTimeMillis() + expires)).sign(algorithm);
	}
	
}
