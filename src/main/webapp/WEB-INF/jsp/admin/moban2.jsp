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

<title>商家编辑</title>

<!-- Bootstrap core CSS -->
<link href="../static/css/bootstrap.css" rel="stylesheet">
<link href="../static/css/main.css" rel="stylesheet">
<link href="../static/css/fileupload.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<div class="dhx_edit_form">
		<form method="post" action="saveMerchant.do" class="form-horizontal" id="submitForm">
			<div class="alert alert-danger hidden lpy_alert" role="alert" id="error">${errMsg}</div>
			<input type="hidden" name="strMerchantid" value="${merchant.strMerchantid}" />
			<c:if test='${merchant.strMerchantid==null||merchant.strMerchantid==""}'>
				<!-- 新增商家 -->
				<div class="form-group">
					<label class="col-sm-2 control-label" for="inputStrUsername"><i class="red">*</i>用户名</label>
					<div class="col-sm-4">
						<input class="form-control inputNeedCheck" data-rule-required="true" maxlength="20" type="text" id="inputStrUsername"
							data-id="${merchant.strMerchantid}" data-href="userNameIsExists.do" name="strUsername" value="${merchant.strUsername}" placeholder="请输入用户名" />
					</div>
					<div class="col-sm-2 errorInfo" data-result="false">
						<span>该用户名已存在</span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" for="inputStrPassword"><i class="red">*</i>密码</label>
					<div class="col-sm-4">
						<input class="form-control" data-rule-required="true" maxlength="20" type="password" id="inputStrPassword" name="strPassword" value="${merchant.strPassword}" placeholder="请输入密码" />
					</div>
				</div>
			</c:if>
			<div class="form-group">
				<label class="col-sm-2 control-label" for="inputStrMerchantname"><i class="red">*</i>商家名称</label>
				<div class="col-sm-4">
					<input class="form-control" data-rule-required="true" maxlength="50" type="text" id="inputStrMerchantname" name="strMerchantname" value="${merchant.strMerchantname}" placeholder="请输入商家名称" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label" for="selectIIsshow"><i class="red">*</i>是否在列表中显示</label>
				<div class="col-sm-2">
					<select id="selectIIsshow" name="iIsshow" class="form-control">
						<option <c:if test='${merchant.iIsshow==1}'>selected</c:if> value="1">展示</option>
						<option <c:if test='${merchant.iIsshow==-1}'>selected</c:if> value="-1">不展示</option>
  					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label" for="inputtSrAddress"><i class="red">*</i>商家地址</label>
				<div class="col-sm-4">
					<input class="form-control" data-rule-required="true" maxlength="100" type="text" id="inputtSrAddress" name="strAddress" value="${merchant.strAddress}" placeholder="请输入商家地址" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label" for="inputStrContactperson"><i class="red  ">*</i>联系人</label>
				<div class="col-sm-4">
					<input class="form-control" data-rule-required="true" maxlength="50" type="text" id="inputStrContactperson" name="strContactperson" value="${merchant.strContactperson}" placeholder="请输入联系人" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label" for="inputStrPhone"><i class="red">*</i>联系电话</label>
				<div class="col-sm-4">
					<input class="form-control" data-rule-required="true" maxlength="50" type="text" id="inputStrPhone" name="strPhone" value="${merchant.strPhone}" placeholder="请输入联系电话" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label" for="inputFile"><i class="red">*</i>商家LOGO图片</label>
				<div class="col-sm-8 contentBox">
					<div class="uploader blue -lpy-upload-content" data-index="0">
					    <div class="-lpy-upload-img">
				     		<img style="width: 300px;height: 160px;" src="<c:choose><c:when test='${merchant.strImagepath==null||merchant.strImagepath==""}'>https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3176667768,1161431363&fm=116&gp=0.jpg</c:when><c:otherwise>${imgRootPath}${merchant.strImagepath}</c:otherwise></c:choose>" id="img0">
					    </div>
						<input type="hidden" name="strImagepath" id="filepath0" value="${merchant.strImagepath}"/>
						<div class="-lpy-div">
						    <input type="text" class="filename" readonly="readonly" value="${merchant.strImagepath}"/>
							<input type="button" class="button -lpy-file-upload-btn" value="浏览..."/>
						    <input data-url="../admin/merchantImageUpload.do" type="file" data-index="0" name="file" id="file0"/>	
						    <div class="cb"></div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label" for="inputFile"><i class="red">*</i>资质与荣誉图片</label>
				<div class="col-sm-8 contentBox">
					<div>	
						<input type="button" class="btn btn-primary" id="btn_add" value="增加" />
					</div>
					<c:choose>
						<c:when test='${merchant.strQualificationhonor!=null&&merchant.strQualificationhonor!=""}'>
							<c:forEach var="data" items="${merchant.qualificationhonorList}" varStatus="status">
								<div class="uploader blue -lpy-upload-content" data-index="${status.index+1}">
								    <div class="-lpy-upload-img">
							     		<img style="width: 300px;height: 160px;" src="${imgRootPath}${data}" id="img${status.index+1}">
								    </div>
									<input type="hidden" name="filepath" id="filepath${status.index+1}" value="${data}"/>
									<div class="-lpy-div">
									    <input type="text" class="filename" readonly="readonly"/>
										<input type="button" class="button -lpy-file-upload-btn" value="浏览..."/>
									    <input data-url="../admin/merchantQuahonorUpload.do" type="file" data-index="${status.index+1}" name="file" id="file${status.index+1}"/>	
									    <div class="cb"></div>
									</div>
									<div class="-lpy-div">
										<input type="text" class="-lpy-cloth-type-input" value="${data}"/><input type="button" data-index="${status.index+1}" class="btn btn-danger btn_delete_image" value="删除" />
									 <div class="cb"></div>
									</div>
								</div>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<div class="uploader blue -lpy-upload-content" data-index="1">
							    <div class="-lpy-upload-img">
						     		<img style="width: 300px;height: 160px;" src="https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3176667768,1161431363&fm=116&gp=0.jpg" id="img1">
							    </div>
								<input type="hidden" name="filepath" id="filepath1"/>
								<div class="-lpy-div">
								    <input type="text" class="filename" readonly="readonly"/>
									<input type="button" class="button -lpy-file-upload-btn" value="浏览..."/>
								    <input data-url="../admin/merchantQuahonorUpload.do" type="file" data-index="1" name="file" id="file1"/>	
								    <div class="cb"></div>
								</div>
								<div class="-lpy-div">
									<input type="text" class="-lpy-cloth-type-input" /><input type="button" data-index="1" class="btn btn-danger btn_delete_image" value="删除" />
								 <div class="cb"></div>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label" for="inputStrIntroduce"><i class="red">*</i>商家介绍</label>
				<div class="col-sm-6">
					<textarea class="form-control" data-rule-required="true" rows="10" id="inputStrIntroduce" name="strIntroduce" >${merchant.strIntroduce}</textarea>
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
	<script src="../static/js/tool/ajaxfileupload.js"></script>
	<script src="../static/js/page/common/validate.js"></script>
	<script src="../static/js/page/common/error.js"></script>
	<script src="../static/js/page/common/common.js"></script>
	<script src="../static/js/page/common/fileupload.js"></script>
	<script type="text/javascript">
		var imgRootPath = '${imgRootPath}';
		$(function() {
			//表单验证
			$("#submitForm").validate({
				submitHandler: function(form) {
					//验证通过后 的js代码写在这里
					form.submit();
				}
			});
		});
	</script>
</body>
</html>
