package com.shangwu.login.controller;

import com.shangwu.login.domain.UserInfo;
import com.shangwu.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author ruanhui
 * @date 2018/12/12
 * 登录控制类
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService service;

    @RequestMapping("login.json")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response,ModelAndView mv,HttpSession session) {
        //把用户名和加密后的密码拿到数据库校验
        UserInfo user = service.check(request, response);
        if (user != null) {
            //登录成功
            // 登录成功，将user对象设置到HttpSession作用范围域
            session.setAttribute("user", user);
            // 转发到main请求
            mv.setView(new RedirectView("/shangwu/enroll/enroll.jsp"));
        }else {
            session.setAttribute("message", "用户名或密码错误，请重试！");
            // 登录失败，设置失败提示信息，并跳转到登录页面
            mv.setView(new RedirectView("/shangwu/login/login.jsp"));
        }
        return mv;
    }
}
