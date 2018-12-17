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
    <script src="js/LodopFuncs.js?v=<%=new Date()%>"></script>
    <link rel="shortcut icon" href="/resource/images/icon.png" type="image/x-icon"/>
    <link rel="stylesheet" href="css/enroll.css?v=<%=new Date()%>">
</head>
<body>
<div class="col-md-6 col-sm-3" id="loginDiv">
    <form id="enrollForm" class="form-horizontal" onsubmit="return false;">
        <div class="form-group">
            <label for="name" class="col-sm-3 control-label">姓名：</label>
            <div class="col-sm-7">
                <input type="text" class="form-control" id="name" name="name" placeholder="姓名">
                <div id="nameErrorDiv" style="display: none" class="alert alert-danger">姓名不能为空！</div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">性别：</label>
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
            <label for="age" class="col-sm-3 control-label">年龄：</label>
            <div class="col-sm-7">
                <input type="text" class="form-control" id="age" name="age" placeholder="年龄">
                <div id="ageErrorDiv" style="display: none" class="alert alert-danger">年龄不合法！</div>
            </div>
        </div>
        <div class="form-group">
            <label for="age" class="col-sm-3 control-label">课程：</label>
            <div class="col-sm-7">
                <select id="courceSelect" onchange="selectOnchang()" class="form-control">
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="age" class="col-sm-3 control-label">缴费金额：</label>
            <div class="col-sm-7">
                <input type="text" class="form-control" id="paymentAmount" name="paymentAmount" placeholder="缴费金额">
                <div id="paymentAmountErrorDiv" style="display: none" class="alert alert-danger">金额输入不合法！</div>
            </div>
        </div>
        <div class="form-group">
            <label for="paymentMode" class="col-sm-3 control-label">支付方式：</label>
            <div class="col-sm-7">
                <select id="paymentMode" class="form-control">
                    <option value="1">支付宝支付</option>
                    <option value="2">微信支付</option>
                    <option value="3">现金支付</option>
                    <option value="4">刷卡支付</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="parentPhone" class="col-sm-3 control-label">父母电话：</label>
            <div class="col-sm-7">
                <input type="text" class="form-control" id="parentPhone" name="parentPhone" placeholder="父母电话">
                <div id="phoneErrorDiv" style="display: none" class="alert alert-danger">手机号码不合法！</div>
            </div>
        </div>
        <div class="form-group">
            <label for="address" class="col-sm-3 control-label">家庭住址：</label>
            <div class="col-md-7">
                <div id="distpicker" class="form-group">
                    <div class="col-sm-4">
                        <select class="form-control" id="province"></select>
                    </div>
                    <div class="col-md-4">
                        <select class="form-control" id="city"></select>
                    </div>
                    <div class="col-md-4">
                        <select class="form-control" id="district"></select>
                    </div>
                </div>
                <input type="text" class="form-control" id="address" name="address" placeholder="详细地址">
                <div id="addressErrorDiv" style="display: none" class="alert alert-danger">地址信息不完整！</div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label"></label>
            <button id="submit" <%--data-toggle="popover"--%> data-content="添加成功！" class="btn btn-default btn-lg" onclick="doSubmit()">提交</button>
            <div id="submitErrorDiv" style="display: none" class="alert alert-danger">遇到错误，请稍后重试！</div>
            <button type="button" style="margin-left: 18%" id="btn_query" onclick="doStudentsList()" class="btn btn-primary">查看学生列表</button>
            <button type="button" id="testPrint" onclick="doTestPrint()" class="btn btn-primary">测试打印</button>
        </div>
    </form>
</div>
</body>
</html>
