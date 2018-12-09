<%--
  Created by IntelliJ IDEA.
  User: aRunn
  Date: 2018/12/8
  Time: 12:38
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生缴费窗口</title>
    <%--加载jquery插件--%>
    <script src="<%=basePath%>resource/jquery/jquery-3.3.1.min.js"></script>
    <script src="<%=basePath%>payment/js/payment.js"></script>
    <%--引入bootstrap--%>
    <script src="<%=basePath%>resource/bootstrap/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resource/bootstrap/css/bootstrap.min.css"/>
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
