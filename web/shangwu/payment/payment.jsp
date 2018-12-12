<%--
  Created by IntelliJ IDEA.
  User: aRunn
  Date: 2018/12/8
  Time: 12:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%--<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生缴费窗口</title>
    <%@ include file="/resource/resource.jsp"%>
    <script src="js/payment.js?v=<%=new Date()%>"></script>
</head>
<body>
<form class="navbar-form navbar-left" role="search">
    <div class="form-group">
        <input type="text" class="form-control" placeholder="Search">
    </div>
    <button type="submit" class="btn btn-default">Submit</button>
</form>
</body>
</html>
