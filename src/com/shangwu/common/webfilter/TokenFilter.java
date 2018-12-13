package com.shangwu.common.webfilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

/**
 *@author ruanhui
 *@date 2018/12/12
 *@description 
 */
public class TokenFilter implements Filter {



	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
                         FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;

		/**
		 * 首先判断 session 中有没有 csrftoken，如果没有，则认为是第一次访问，session 是新建立的， 这时生成一个新的
		 * token，放于 session 之中，并继续执行请求。如果 session 中已经有 csrftoken，
		 * 则说明用户已经与服务器之间建立了一个活跃的 session，这时要看这个请求中有没有同时附带这个 token
		 */
		HttpSession s = request.getSession();
		// 从 session 中得到 csrftoken 属性
		String sToken = (String) s.getAttribute("csrftoken");
		if (sToken == null) {
			// 产生新的 token 放入 session 中
			sToken = generateToken();
			s.setAttribute("csrftoken", sToken);
			//System.out.println("1 sToken=" + sToken);

			chain.doFilter(request, response);
		} else {
			// 从 HTTP 头中取得 csrftoken
			String xhrToken = request.getHeader("csrftoken");
			// 从请求参数中取得 csrftoken
			String pToken = request.getParameter("csrftoken");
			if (sToken != null && xhrToken != null && sToken.equals(xhrToken)) {
				//System.out.println("2 sToken=" + sToken);
//				s.removeAttribute("csrftoken");
				chain.doFilter(arg0, arg1);
			} else if (sToken != null && pToken != null
					&& sToken.equals(pToken)) {
				//System.out.println("3 sToken=" + sToken);
//				s.removeAttribute("csrftoken");
				chain.doFilter(arg0, arg1);
			} else {
				if(request.getServletPath().equals("/login.jsp")){
					chain.doFilter(request, response);
				}else{
					System.out.println("4 error");
					request.getRequestDispatcher("/error/error.jsp").forward(request, response);
				}
			}
		}
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

	private String generateToken() {
		return UUID.randomUUID().toString();
	}
}
