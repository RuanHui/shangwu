package com.shangwu.common.webfilter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *@author ruanhui
 *@date 2018/12/12
 *@description
 */
public class LocalXssFilter implements Filter {

	
	private String filterChar;
	  private String replaceChar;
	  private String splitChar;
	  FilterConfig filterConfig = null;
	  
	public void init(FilterConfig filterConfig) throws ServletException {
		
		this.filterChar=filterConfig.getInitParameter("FilterChar");
	    this.replaceChar=filterConfig.getInitParameter("ReplaceChar");
	    this.splitChar=filterConfig.getInitParameter("SplitChar");
	    this.filterConfig = filterConfig;
	}

	public void doFilter(ServletRequest req, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;

	    HttpSession session = request.getSession();
	    chain.doFilter(new LocalXssHttpServletRequestWrapper(request,filterChar,replaceChar,splitChar), response);
	}

	public void destroy() {
		this.filterConfig = null;
	}

}
