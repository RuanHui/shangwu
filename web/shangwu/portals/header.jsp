<%--
  Created by IntelliJ IDEA.
  User: aRunn
  Date: 2018/12/26
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<!DOCTYPE html>--%>
<nav class="navbar navbar-default">
            <div class="navbar-header navbar-left">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <h1><a class="navbar-brand" href="/"><span><i>K</i>arate</span></a></h1>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse navbar-right" id="bs-example-navbar-collapse-1">
                <nav class="menu menu--juliet">
                    <ul class="nav navbar-nav">
                        <li id="index" class="menu__item"><a href="/" class="menu__link">首页</a></li>
                        <li id="service" class="menu__item"><a href="<%=basePath%>shangwu/portals/services.jsp" class="menu__link">服务</a></li>
                        <li id="gallery" class="menu__item"><a href="<%=basePath%>shangwu/portals/gallery.jsp" class="menu__link">图库</a></li>
                        <li  id="honor" class="dropdown menu__item">
                            <a href="#" class="dropdown-toggle menu__link" data-toggle="dropdown">所获荣誉<b class="caret"></b></a>
                            <ul class="dropdown-menu agile_short_dropdown">
                                <li><a href="<%=basePath%>shangwu/portals/studentHonor.jsp">学生荣誉</a></li>
                                <li><a href="<%=basePath%>shangwu/portals/teacherHonor.jsp">教师荣誉</a></li>
                            </ul>
                        </li>
                        <li  id="contactUs" class="menu__item"><a href="<%=basePath%>shangwu/portals/contactUs.jsp" class="menu__link">联系我们</a></li>
                    </ul>
                    <div class="agileinfo_social_icons">
                        <ul class="agileits_social_list">
                            <%--用户头像--%>
                            <li><a href="#" class="w3_agile_facebook"><i class="fa fa-meh-o" aria-hidden="true"></i></a></li>
                        </ul>
                    </div>
                </nav>
            </div>
        </nav>
