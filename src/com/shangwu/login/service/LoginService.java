package com.shangwu.login.service;

import com.shangwu.common.utils.HashUtil;
import com.shangwu.login.domain.UserInfo;
import com.shangwu.login.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ruanhui
 * @date 2018/12/13
 */
@Service
public class LoginService {

    @Autowired
    private LoginMapper mapper;

    private final String SALT = "cjchnws";    //用于给密码机密的盐

    //登录校验
    public UserInfo check(HttpServletRequest request, HttpServletResponse response) {
       try {
           //获取用户名和密码、
           String username = request.getParameter("username");
           String password = request.getParameter("password");
           //给密码加密
           String sha1 = HashUtil.hash(password, "sha1");
           //加静态盐
           sha1 = sha1 + SALT;
           //给加盐后的字符串再加密
           String encodePwd = HashUtil.hash(sha1, "sha1");
           //查询数据库
           UserInfo user = mapper.check(username,encodePwd);
           return user;
       }catch (Exception e) {
           e.printStackTrace();
       }
        return null;
    }
}
