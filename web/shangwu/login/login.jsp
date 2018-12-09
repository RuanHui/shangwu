<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>用户登录</title>
    <%--加载jquery插件--%>
    <script src="<%=basePath%>resource/jquery/jquery-3.3.1.min.js"></script>
    <!-- 引入 login.js，既可以使用其中提供的 initGeetest 初始化函数 -->
    <script src="js/login.js"></script>
    <%--引入bootstrap--%>
    <script src="<%=basePath%>resource/bootstrap/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resource/bootstrap/css/bootstrap.min.css"/>
</head>
<body>
<form>
        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
            <div class="col-sm-10">
                <input type="email" class="form-control" id="inputEmail3" placeholder="Email">
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="inputPassword3" placeholder="Password">
            </div>
        </div>
    <div class="form-group">
        <label for="inputPassword3" class="col-sm-2 control-label">完成验证：</label>
        <div id="captcha" class="col-sm-10">
            <p id="wait" class="show">正在加载验证码......</p>
        </div>
    </div>
    <p id="notice" style="display: none">请先完成验证</p>
    <button type="submit" id="submit" class="btn btn-default">提交</button>
</form>
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
                            alert('登录成功');
                        } else if (data.status === 'fail') {
                            alert('登录失败');
                        }
                    }
                })
            }
            e.preventDefault();
        });
        // 将验证码加到id为captcha的元素里，同时会有三个input的值用于表单提交
        captchaObj.appendTo("#captcha");
        captchaObj.onReady(function () {
            // $("#wait").hide();
            $("#wait").remove();
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