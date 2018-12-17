<%--
  Created by IntelliJ IDEA.
  User: aRunn
  Date: 2018/12/15
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>尚武道场</title>
    <%@ include file="/resource/resource.jsp"%>
    <link rel="shortcut icon" href="/resource/images/icon.png" type="image/x-icon"/>
    <script src="js/students.js"></script>
</head>
<body>
<div class="panel-body" style="padding-bottom:0px;">
    <%--<div class="panel panel-default">
        <div class="panel-heading">查询条件</div>
        <div class="panel-body">
            <form id="formSearch" class="form-horizontal">
                <div class="form-group" style="margin-top:15px">
                    <label class="control-label col-sm-1" for="name">姓名</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="name">
                    </div>
                    <div class="col-sm-4" style="text-align:left;">
                        <button type="button" style="margin-left:50px" id="btn_query" onclick="doSearch()" class="btn btn-primary">查询</button>
                    </div>
                </div>
            </form>
        </div>
    </div>--%>

    <div id="toolbar" class="btn-group">
        <button id="btn_back" type="button" class="btn btn-default" onclick="doBackEnroll()">
            <span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span>返回报名系统
        </button>

        <button id="btn_edit" type="button" class="btn btn-default" onclick="openEditModal()">
            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
        </button>
        <button id="btn_delete" type="button" class="btn btn-default" onclick="openDeleteModal()">
            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
        </button>
    </div>
    <div style="height: 75%">
        <table id="studentsTable"></table>
    </div>
</div>
<!-- 修改模态框（Modal） -->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    修改记录
                </h4>
            </div>
            <div class="modal-body">
                <form id="enrollForm" class="form-horizontal" onsubmit="return false;">
                    <div class="form-group">
                        <label for="name" class="col-sm-3 control-label">姓名：</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="id" style="display: none" name="id">
                            <input type="text" class="form-control" id="name" name="name" placeholder="姓名">
                            <div id="nameErrorDiv" style="display: none" class="alert alert-danger">姓名不能为空！</div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">性别：</label>
                        <div class="col-sm-7">
                            <label class="radio-inline">
                                <input type="radio" name="sex" value="1"> 男
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
                            <select id="courseSelect" onchange="selectOnchang()" class="form-control">
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
                    <%--<div class="form-group">
                        <label for="paymentMode" class="col-sm-3 control-label">支付方式：</label>
                        <div class="col-sm-7">
                            <select id="paymentMode" class="form-control">
                                <option value="1">支付宝支付</option>
                                <option value="2">微信支付</option>
                                <option value="3">现金支付</option>
                                <option value="4">刷卡支付</option>
                            </select>
                        </div>
                    </div>--%>
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
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" onclick="doEdit()" class="btn btn-primary">
                    提交更改
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<!-- 删除模态框（Modal） -->
<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">
                    删除记录
                </h4>
            </div>
            <div class="modal-body" id="deleteModalContent">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消
                </button>
                <button type="button" onclick="doDelete()" class="btn btn-primary">
                    确认删除
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

</body>
</html>
