<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>515学车管理后台</title>
<!-- Bootstrap -->
<link href="../static/css/bootstrap.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="../static/css/lpy_login.css" rel="stylesheet">
</head>

<body class="body_background">
	<!-- container开始 -->
	<div class="container">
		<h1 class="text-center">515学车管理后台</h1>
		<form class="form-signin" action="" method="post">
			<div class="alert alert-danger hidden lpy_alert" role="alert"
				id="error"></div>
			<input type="text" class="form-control" placeholder="用户名" autofocus
				id="username">
				 <input type="password" class="form-control"
				placeholder="密码" id="password">
			<button class="btn btn-lg btn-primary btn-block" type="button"
				id="login_bt">登录</button>
		</form>
	</div>
	<!-- container结束 -->
	
   	<script src="../static/js/tool/jquery.js"></script>
	<script src="../static/js/tool/bootstrap.min.js"></script>
	<script src="../static/js/page/common/login.js"></script>
</body>
</html>
