package com.carebed.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CorsFilter implements Filter {

	@Override
	public void init(FilterConfig paramFilterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest _request = (HttpServletRequest)request;
		HttpServletResponse _response = (HttpServletResponse)response;
		_response.setHeader("Access-Control-Allow-Origin", _request.getHeader("Origin")); //解决跨域访问报错   
		_response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");   
		_response.setHeader("Access-Control-Max-Age", "3600"); //设置过期时间   
		_response.setHeader("Access-Control-Allow-Headers", "user-client,authorization");   
		_response.setHeader("Access-Control-Allow-Credentials", "true");//是否支持cookie跨域
		_response.setHeader("XDomainRequestAllowed","1");
		_response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // 支持HTTP 1.1. 
		if("OPTIONS".equals(_request.getMethod())){
			_response.setStatus(org.apache.commons.httpclient.HttpStatus.SC_NO_CONTENT);
		}
		
		filterChain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
