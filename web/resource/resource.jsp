<%--
  Created by IntelliJ IDEA.
  User: aRunn
  Date: 2018/12/9
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--加载jquery插件--%>
<script src="<%=basePath%>resource/jquery/jquery-3.3.1.min.js"></script>
<%--引入bootstrap--%>
<script src="<%=basePath%>/resource/bootstrap/js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css" href="<%=basePath%>resource/bootstrap/css/bootstrap.min.css"/>

<%--引入distpicker省市区三级联动插件--%>
<script src="<%=basePath%>/resource/distpicker/distpicker.data.js"></script>
<script src="<%=basePath%>/resource/distpicker/distpicker.js"></script>
