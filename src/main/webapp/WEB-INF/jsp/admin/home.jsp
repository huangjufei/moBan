<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="LIUPENGYAN">
<link rel="icon" href="../../favicon.ico">

<title>主页</title>

<!-- Bootstrap core CSS -->
<link href="../static/css/bootstrap.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="../static/css/main.css" rel="stylesheet">


<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target=".navbar-collapse">
					<span class="sr-only">515学车</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="javascript:void(0);">515学车后台管理系统</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="javascript:void(0);">欢迎您，${admin.strUserName}</a>
					</li>
					<li><a href="exit.do">退出</a>
					</li>
					<li><a href="modifyPwdIndex.do" target="J_iframe">修改密码</a>
					</li>
				</ul>
			</div>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
				
					<li class="lpy-sidebar-first"><a href="javascript:void(0)">订单管理</a></li>
			
					<ul class="nav nav-sidebar lpy-sidebar-second">
						<li class="active"><a href="javascript:void(0)" data-url="../order/selectOrder.do">订单信息</a></li>
					</ul>
					
					
					
					
					<li class="lpy-sidebar-first"><a href="javascript:void(0)">驾校管理</a></li>
			
					<ul class="nav nav-sidebar lpy-sidebar-second">
						<li class="active"><a href="javascript:void(0)" data-url="../school/getViewSchoolList.do">驾校信息</a></li>
					</ul>
					
					
					<li class="lpy-sidebar-first"><a href="javascript:void(0)">场地管理</a></li>
					
					<ul class="nav nav-sidebar lpy-sidebar-second">
						<li class="active"><a href="javascript:void(0)" data-url="../area/getViewAreaList.do">场地信息</a></li>
					</ul>

					
					<li class="lpy-sidebar-first"><a href="javascript:void(0)">教练管理</a></li>
					
					<ul class="nav nav-sidebar lpy-sidebar-second">

						<li class="active"><a href="javascript:void(0)" data-url="../driver/getViewDriverList.do">教练信息</a></li>

					</ul>
					
				</ul>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<iframe id="J_iframe" name="J_iframe" class="iframe"> </iframe>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript
================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="../static/js/tool/jquery.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="../static/js/tool/bootstrap.min.js"></script>
	<script src="../static/js/page/common/main.js"></script>

</body>
</html>
