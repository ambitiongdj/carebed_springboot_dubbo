package com.carebed.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.carebed.enums.ResultEnum;
import com.carebed.annotation.ClearAnnotation;
import com.carebed.common.core.domain.AjaxResult;
import com.carebed.common.utils.StringUtils;
import com.carebed.constants.Constants;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class OnlineVerifyInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) {
		Map<String, Object> params = new HashMap<String, Object>();

		if (handler instanceof HandlerMethod && !clearedByAnnotation(((HandlerMethod) handler).getMethod())) {
			String token = request.getHeader(Constants.AUTHORIZATION);
			if(StringUtils.isNotNull(token)){

			}
			if (StringUtils.isNoneBlank(token)) {
				try {
					Algorithm algorithm = Algorithm.HMAC256("secret");
					JWTVerifier jwtVerifier = JWT.require(algorithm).build();
					DecodedJWT jwt = jwtVerifier.verify(token);
					if (!jwt.getClaim("memberId").isNull() && !jwt.getClaim("memberCardNo").isNull() && !jwt.getClaim("memberName").isNull() && !jwt.getClaim("memberMobile").isNull()) {
						request.setAttribute("memberId",jwt.getClaim("memberId").asString());
						request.setAttribute("memberCardNo",jwt.getClaim("memberCardNo").asString());
						request.setAttribute("memberName", jwt.getClaim("memberName").asString());
						request.setAttribute("memberMobile", jwt.getClaim("memberMobile").asString());
						return true;
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			handlerReturnResult(response, ResultEnum.OPERATE_ERROR_TOKEN);//无token时，返回
			return false;
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	private boolean clearedByAnnotation(Method method) {
		ClearAnnotation clear = method.getAnnotation(ClearAnnotation.class);
		if (clear != null) {
			if (Arrays.asList(clear.value()).contains(this.getClass())) {
				return true;
			}
		}
		Class<?> clazz = method.getDeclaringClass();
		clear = (ClearAnnotation) clazz.getAnnotation(ClearAnnotation.class);
		if (clear != null) {
			if (Arrays.asList(clear.value()).contains(this.getClass())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @Title: handlerReturnResult   
	 * @Description: 拦截器处理异常情况返回结果
	 * @param response
	 * @param resultEnum      
	 * @date 2019年8月22日
	 */
	private void handlerReturnResult(HttpServletResponse response, ResultEnum resultEnum) {
		AjaxResult error = AjaxResult.error(resultEnum.getMessage());
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(JSONObject.toJSONString(error));
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}
