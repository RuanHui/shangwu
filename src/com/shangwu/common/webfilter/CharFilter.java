package com.shangwu.common.webfilter;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 *@author ruanhui
 *@date 2018/12/12
 *@description 
 */
public class CharFilter implements Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String requestStr = getRequestString(request);
 	
		System.out.println("requestStr： ======================== " + requestStr);
		System.out.println("完整的地址是====" + request.getRequestURL().toString());
		System.out.println("提交的方式是========" + request.getMethod());
		 
		// add by wangsk 过滤请求特殊字符，扫描跨站式漏洞
		Map parameters = request.getParameterMap();
		if (parameters != null && parameters.size() > 0) {
			for (Iterator iter = parameters.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String[] values = (String[]) parameters.get(key);
				for (int i = 0; i < values.length; i++) {
//					values[i] = guolv(values[i]);
//					values[i] = guolv2(values[i]);
//					System.out.println(values[i]);
				}
			}
		}
		filterChain.doFilter(servletRequest, servletResponse) ;
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	/**
	 * 过滤框架或者链接注入的特殊字符
	  * @author  xiangyong 
	  * @date 创建时间：2018-5-21 上午10:53:11 
	  * @parameter  
	  * @since  
	  * @return
	 */
	public static String guolv(String a) {
		a = a.replaceAll("%22", "");
		a = a.replaceAll("%27", "");
		a = a.replaceAll("%3E", "");
		a = a.replaceAll("%3e", "");
		a = a.replaceAll("%3C", "");
		a = a.replaceAll("%3c", "");
		a = a.replaceAll("<", "");
		a = a.replaceAll(">", "");
		a = a.replaceAll("\"", "");
		a = a.replaceAll("'", "");
		a = a.replaceAll("\\+", "");
		a = a.replaceAll("\\(", "");
		a = a.replaceAll("\\)", "");
		a = a.replaceAll(" and ", "");
		a = a.replaceAll(" or ", "");
		a = a.replaceAll(" 1=1 ", "");
		a = a.replaceAll("|", "");
		a = a.replaceAll("&", "");
		a = a.replaceAll(";", "");
		a = a.replaceAll("$", "");
		a = a.replaceAll("%", "");
		a = a.replaceAll("@", "");
		a = a.replaceAll("\\'", "");
		a = a.replaceAll("\\", "");
		a = a.replaceAll("\r", "");
		a = a.replaceAll("\n", "");
		a = a.replaceAll("\\\"", "");
		a = a.replaceAll(",", "");
		a = a.replaceAll("'", "");
		a = a.replaceAll("[\\t\\n\\r]", "") ;
		return a;
	}
	
	private String getRequestString(HttpServletRequest req) {
		String requestPath = req.getServletPath().toString();
		String queryString = req.getQueryString();
		if (queryString != null)
			return requestPath + "?" + queryString;
		else
			return requestPath;
	}

	public String guolv2(String a) {
		if (StringUtils.isNotEmpty(a)) {
			if (a.contains("%22") || a.contains("%3E") || a.contains("%3e")
					|| a.contains("%3C") || a.contains("%3c")
					|| a.contains("<") || a.contains(">") || a.contains("\"") || a.contains("\\")
					|| a.contains("'") || a.contains("+") || a.contains("\\\\") ||  
					a.contains(" and ") || a.contains(" or ")
					|| a.contains("1=1") || a.contains("(") || a.contains(")")) {
				return "";
			}
		}
		return a;
	}

}
