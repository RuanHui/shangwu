<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%--<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>--%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>用户登录</title>
    <%@ include file="/resource/resource.jsp"%>
    <!-- 引入 login.js，既可以使用其中提供的 initGeetest 初始化函数 -->
    <script src="js/login.js?v=<%=new Date()%>"></script>
    <link rel="stylesheet" href="css/login.css?v=<%=new Date()%>">
</head>
<body>
<div class="col-md-6 col-md-4" id="loginDiv">
    <form class="form-horizontal">
        <div class="form-group">
            <label for="inputEmail3" class="col-sm-5 control-label">用户名：</label>
            <div class="col-sm-7">
                <input type="email" class="form-control" id="inputEmail3" placeholder="请输入用户名">
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword3" class="col-sm-5 control-label">密码：</label>
            <div class="col-sm-7">
                <input type="password" class="form-control" id="inputPassword3" placeholder="请输入密码">
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword3" class="col-sm-5 control-label">完成验证：</label>
            <div id="captcha" class="col-sm-7">
                <p id="wait" style="display: block">正在加载验证码......</p>
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword3" class="col-sm-5 control-label"></label>
            <div id="noticeDiv" class="col-sm-7">
                <p id="notice" style="display: none;color: red;">请先完成验证</p>
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword3" class="col-sm-5 control-label"></label>
            <button type="submit" id="submit" class="btn btn-default">提交</button>
        </div>
    </form>
</div>

</body>
<script type="text/javascript">
    var handler2 = function (captchaObj) {
        $("#submit").click(function (e) {
            var result = captchaObj.getValidate();
            if (!result) {
                $("#notice").show();
                setTimeout(function () {
                    $("#notice").hide();
                }, 2000);
            } else {
                $.ajax({
                    url: '<%=basePath%>gt/ajax-validate2',
                    type: 'POST',
                    dataType: 'json',
                    data: {
                        username: $('#username2').val(),
                        password: $('#password2').val(),
                        geetest_challenge: result.geetest_challenge,
                        geetest_validate: result.geetest_validate,
                        geetest_seccode: result.geetest_seccode
                    },
                    success: function (data) {
                        if (data.status === 'success') {
                            // alert('登录成功');
                        } else if (data.status === 'fail') {
                            // alert('登录失败');
                        }
                    }
                })
            }
            e.preventDefault();
        });
        // 将验证码加到id为captcha的元素里，同时会有三个input的值用于表单提交
        captchaObj.appendTo("#captcha");
        captchaObj.onReady(function () {
            $("#wait").hide();
        });
        // 更多接口参考：http://www.geetest.com/install/sections/idx-client-sdk.html
    };
    $.ajax({
        url:  "<%=basePath%>gt/register2?t=" + (new Date()).getTime(), // 加随机数防止缓存
        type: "get",
        dataType: "json",
        success: function (data) {
            // 调用 initGeetest 初始化参数
            // 参数1：配置参数
            // 参数2：回调，回调的第一个参数验证码对象，之后可以使用它调用相应的接口
            initGeetest({
                gt: data.gt,
                challenge: data.challenge,
                new_captcha: data.new_captcha, // 用于宕机时表示是新验证码的宕机
                offline: !data.success, // 表示用户后台检测极验服务器是否宕机，一般不需要关注
                product: "popup", // 产品形式，包括：float，popup
                width: "100%"
                // 更多配置参数请参见：http://www.geetest.com/install/sections/idx-client-sdk.html#config
            }, handler2);
        }
    });
</script>
</html>