<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>系统错误</title>
</head>
<link rel="shortcut icon" href="/resource/images/icon.png" type="image/x-icon"/>
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
	<div style="margin:0 auto; width:500px; height:306px;">
		<input type="image" src="/resource/images/500.jpg" style="padding-top: 150px;"/>
	</div>
	<div id="all">
		<div id="main">
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