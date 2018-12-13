package com.shangwu.login.mapper;

import com.shangwu.login.domain.UserInfo;
import org.apache.ibatis.annotations.Param;

/**
 * @author ruanhui
 * @date 2018/12/13
 */
public interface LoginMapper {

    UserInfo check(@Param("username") String username, @Param("password") String password);
}
