package com.shangwu.common.webfilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *@author ruanhui
 *@date 2018/12/12
 *@description
 */
public class CsrfFilter implements Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)throws IOException, ServletException {
		//获取需要过滤的地址系统参数	通过再这里配置过滤的地址  --ruanhui  20180828
		String[] filters = {"http://106.13.9.33:8080","http://localhost:8080","http://www.arunner.cn:80"};

		List<String> filtersList = Arrays.asList(filters);
		List<String> listWithHttp = new ArrayList();	//带http的list   --》 http://135.10.58.62
		for(String fil : filtersList) {
			listWithHttp.add(fil.substring(0,fil.lastIndexOf(":")));
		}

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String requestStr = getRequestString(request);
		// 解决缺少跨帧脚本编制防御
		System.out.println("requestStr： =====csrf=================== " + requestStr);
		System.out.println("完整的地址是==csrf==" + request.getRequestURL().toString());
		System.out.println("提交的方式是====csrf====" + request.getMethod());
	 
		// 获取请求是从哪里来的  解决 CSRF跨站请求伪造
        String referer = request.getHeader("referer");  
        System.out.println("referer===csrf=====" + referer);
        // 如果是直接输入的地址，或者不是从本网站访问的重定向到本网站的首页   
//        !referer.startsWith("http://localhost")  !referer.startsWith("http://135.10.59.62")
        if(referer==null){
        	referer="http://localhost";
//        	referer="http://135.10.58.62";
        }
        System.out.println("referer2===csrf=====" + referer);
//        if (referer != null && !referer.startsWith("http://135.10.58.62") && !referer.startsWith("http://135.10.59.64")) {
		for(String ref : listWithHttp) {
			if (referer != null && referer.startsWith(ref)) {
				filterChain.doFilter(servletRequest, servletResponse) ;
				return ;
			}
		}
//    	if (referer != null && !referer.startsWith("http://localhost") ) {
		System.out.println("======referer，已拦截======");
		response.sendRedirect(request.getContextPath() + "/login1.jsp");
		// 然后return，不要输出后面的内容了
		return;
//        }
 
//		filterChain.doFilter(servletRequest, servletResponse) ;
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}
 

	private String getRequestString(HttpServletRequest req) {
		String requestPath = req.getServletPath().toString();
		String queryString = req.getQueryString();
		if (queryString != null)
			return requestPath + "?" + queryString;
		else
			return requestPath;
	}
 
}
