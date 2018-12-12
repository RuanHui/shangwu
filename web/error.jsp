<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>系统错误</title>

<style>
* {
	margin: 0px;
	padding: 0px;
}

#all {
	margin-right: auto;
	margin-left: auto;
	width: 640px;
	margin-top: 100px;
}

#main {
	padding-top: 10px;
	padding-right: 14px;
	padding-bottom: 10px;
	padding-left: 14px;
	border: 1px solid #CCCCCC;
}

#main h1 {
	padding-bottom: 10px;
}

#main_i {
	background-color: #F5F5F5;
	padding-top: 18px;
	padding-right: 40px;
	padding-bottom: 18px;
	padding-left: 40px;
	font-family: Tahoma
}

#main_i h1 {
	font-size: 24px;
	font-weight: bolder;
	margin-bottom: 5px;
	text-align: center;
}

#main_i h2 {
	font-size: 14px;
	font-weight: bolder;
	margin-bottom: 10px;
	text-align: center;
}

#main_i h3 {
	font-size: 13px;
	font-weight: lighter;
	text-align: right;
	color: #483D8B
}

#main_i h3 a {
	color: #556B2F;
	text-decoration: underline;
}

#main_i h3 a:visited {
	color: #2F4F4F;
}

#main_i h3 a:hover {
	color: #5F9EA0;
	text-decoration: none;
}
</style>
<script>
	function showDetail() {
		var ed = document.getElementById("detail_error_msg");
		if (ed.style.display == "none")
			ed.style.display = "block";
		else
			ed.style.display = "none";
	}
</script>
</head>

<body>
	<%
		String requestUri = (String) request
				.getAttribute("javax.servlet.error.request_uri");
		Exception excepiton = (Exception) request
				.getAttribute("javax.servlet.error.exception");

		String friendlyErrMsg = "";
		if (excepiton != null) {
			friendlyErrMsg = excepiton.getMessage();
		}
	%>

	<div id="all">
		<div id="main">

			<div id="main_i">

				<h3 style="text-align: center">
					<font color="red" size="3px">系统出现异常，请稍后重试！</font><br />
					<img src="<%=request.getContextPath()%>/error/images/position.gif" /><a
						href="javascript:void(0)" onclick="parent.window.close();">关闭窗口</a>&nbsp;&nbsp;
					<br/>如有疑问请与技术人员联系！
				</h3>	
			</div>
			<div id="detail_error_msg" style="display:none">
				<pre>
					<%
						//注释在页面上显示错误信息，修复应用程序未容错漏洞   ruanhui  20181112
                        //本地测试时，可以打开该注释，定位问题。上线时请注意：一定要注释该输出的异常信息
						out.println("错误来源:" + requestUri);
						exception.printStackTrace(new java.io.PrintWriter(out));
					%>
				</pre>
			</div>
		</div>


	</div>
</body>
</html>