package com.carebed.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.carebed.entity.RequestAgent;
import com.carebed.enums.ResultEnum;
import com.carebed.annotation.ClearAnnotation;
import com.carebed.common.core.domain.AjaxResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class RequestInterceptor extends HandlerInterceptorAdapter {

	private List<String> terminals = Arrays.asList("CAREBED");

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		if (handler instanceof HandlerMethod
				&& !clearedByAnnotation(((HandlerMethod) handler).getMethod())) {
			String client = request.getHeader("user-client");

			if (StringUtils.isNoneBlank(client)) {

				RequestAgent ra = new RequestAgent();
				String[] split = client.split("#");

				if (split.length == 3) {
					ra.setTerminal(split[0]);
					ra.setVersion(split[1]);
					ra.setDevice(split[2]);
				} else if (split.length == 2) {
					ra.setTerminal(split[0]);
					ra.setVersion(split[1]);
					ra.setDevice("");
				}

				if (terminals.contains(ra.getTerminal())) {
					return true;
				}

			}

			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8");
			response.setStatus(HttpServletResponse.SC_OK);
			PrintWriter out = null;
			try {
				out = response.getWriter();
				out.print(JSONObject.toJSON(AjaxResult.error(ResultEnum.PARAMETER_LOST.getMessage())));
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (out != null) {
					out.close();
				}
			}

			return false;

		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
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

}
