<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="LIUPENGYAN">
<link rel="icon" href="../../favicon.ico">

<title>管理员修改密码</title>

<!-- Bootstrap core CSS -->
<link href="../static/css/bootstrap.css" rel="stylesheet">
<link href="../static/css/main.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<div class="dhx_edit_form">
		<form method="post" action="modifyPwd.do" class="form-horizontal" id="submitForm">
			<div class="alert alert-danger hidden lpy_alert" role="alert" id="error">${errMsg}</div>
			<div class="form-group">
				<label class="col-sm-2 control-label" for="inputStrOldPassword"><i class="red">*</i>原密码</label>
				<div class="col-sm-4">
					<input class="form-control" data-rule-required="true" maxlength="20" type="password" id="inputStrOldPassword" name="strOldPassword" placeholder="请输入原密码" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label" for="inputStrNewPassword"><i class="red">*</i>新密码</label>
				<div class="col-sm-4">
					<input class="form-control" data-rule-required="true" maxlength="20" type="password" id="inputStrNewPassword" name="strNewPassword" placeholder="请输入新密码" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label" for="inputStrConfirmPassword"><i class="red">*</i>确认新密码</label>
				<div class="col-sm-4">
					<input class="form-control" data-rule-required="true" maxlength="20" type="password" id="inputStrConfirmPassword" name="strConfirmPassword" placeholder="请再次输入新密码" />
				</div>
			</div>
			<div class="form-group">
				 <div class="col-sm-offset-2 col-sm-6">
				 	<input type="submit" class="btn btn-primary" value="保 存" />
  				 </div>
			</div>
		</form>
	</div>

	<!-- Bootstrap core JavaScript
================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="../static/js/tool/jquery.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="../static/js/tool/bootstrap.min.js"></script>
	<script src="../static/js/tool/jquery.validate.js"></script>
	<script src="../static/js/page/common/error.js"></script>
	<script src="../static/js/page/common/validate.js"></script>
	<script type="text/javascript">
		$(function() {
			function showError(msg) {
				
				$("#error").text(msg);
				$("#error").removeClass("hidden");
				$("#error").fadeOut(3000,function(){
					$("#error").show();
					$("#error").addClass("hidden");
				});
			}
			//表单验证
			$("#submitForm").validate({
				submitHandler: function(form) {
					//验证通过后 的js代码写在这里
					var oldPwd = $("#inputStrOldPassword").val().trim();
					var newPwd = $("#inputStrNewPassword").val().trim();
					var confirmNewPwd = $("#inputStrConfirmPassword").val().trim();
					if(oldPwd==newPwd) {
						//新密码和原密码相同
						showError("新密码不能和原密码相同");
						return;
					}
					if(newPwd!=confirmNewPwd) {
						//新密码和确认密码不相同
						showError("两次输入的新密码不一致");
						return;
					}
					form.submit();
				}
			});
		});
	</script>
</body>
</html>
