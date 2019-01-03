<%--
  Created by IntelliJ IDEA.
  User: aRunn
  Date: 2018/12/26
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="footer">
    <div class="container">
        <div class="col-md-4 agile_footer_grid">
            <h3>关于我们</h3>
            <p>尚武道场</p>
        </div>
        <div class="col-md-4 agile_footer_grid">

        </div>
        <div class="col-md-4 agile_footer_grid">
            <h3>联系信息</h3>
            <ul class="w3_address">
                <li><i class="glyphicon glyphicon-map-marker" aria-hidden="true"></i>武汉市黄陂区百盛街与锦秀道交叉口东北150米</li>
                <li><i class="glyphicon glyphicon-envelope" aria-hidden="true"></i><a href="mailto:info@example.com">info@example.com</a></li>
                <li><i class="glyphicon glyphicon-earphone" aria-hidden="true"></i>027 61000798</li>
            </ul>
        </div>
        <div class="clearfix"> </div>
        <div class="w3_agileits_footer_grids">

        </div>
        <div class="agileinfo_copyright">
            <p>Powered By aRunner</p>
        </div>
    </div>
</div>
