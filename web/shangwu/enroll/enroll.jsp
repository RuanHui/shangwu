<%--
  Created by IntelliJ IDEA.
  User: aRunn
  Date: 2018/12/8
  Time: 12:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix= "form" uri= "http://www.springframework.org/tags/form" %>
<%--<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生缴费窗口</title>
    <%@ include file="/resource/resource.jsp"%>
    <script src="js/enroll.js?v=<%=new Date()%>"></script>
    <link rel="shortcut icon" href="/resource/images/icon.png" type="image/x-icon"/>
    <link rel="stylesheet" href="css/enroll.css?v=<%=new Date()%>">
</head>
<body>
<div class="col-md-6 col-md-4" id="loginDiv">
    <form class="form-horizontal">
        <div class="form-group">
            <label for="name" class="col-sm-5 control-label">姓名：</label>
            <div class="col-sm-7">
                <input type="text" class="form-control" id="name" name="name" placeholder="姓名">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-5 control-label">性别：</label>
            <div class="col-sm-7">
                <label class="radio-inline">
                    <input type="radio" name="sex" value="1" checked="checked"> 男
                </label>
                <label class="radio-inline">
                    <input type="radio" name="sex" value="2"> 女
                </label>
            </div>
        </div>
        <div class="form-group">
            <label for="age" class="col-sm-5 control-label">年龄：</label>
            <div class="col-sm-7">
                <input type="text" class="form-control" id="age" name="age" placeholder="年龄">
            </div>
        </div>
        <div class="form-group">
            <label for="parentPhone" class="col-sm-5 control-label">父母电话：</label>
            <div class="col-sm-7">
                <input type="text" class="form-control" id="parentPhone" name="parentPhone" placeholder="父母电话">
            </div>
        </div>
        <div class="form-group">
            <label for="address" class="col-sm-5 control-label">家庭住址：</label>
            <div class="col-sm-7">
                <input type="text" class="form-control" id="address" name="address" placeholder="家庭住址">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-5 control-label"></label>
            <button type="submit" id="submit" class="btn btn-default" onclick="doSubmit()">提交</button>
        </div>
    </form>
</div>
</body>
</html>
