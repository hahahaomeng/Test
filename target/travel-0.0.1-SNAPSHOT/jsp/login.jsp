<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>验证码</title>
		<script language="javascript">
			function myReload() {
				document.getElementById("CreateCheckCode").src = document
					.getElementById("CreateCheckCode").src
					+ "?nocache=" + new Date().getTime();
			}
		</script>
	</head>

	<body>
	<center>
		<form action="create_check.json" method="post">
			<input name="checkCode" type="text" id="checkCode" title="验证码区分大小写"
				size="8" ,maxlength="4" />
			<img src="create_create.json" id="CreateCheckCode" align="middle"/>
			<a href="" onclick="myReload();return false;">&nbsp;看不清</a>
			<input type="submit" value="提交" />
		</form>
	</center>
	</body>
</html>