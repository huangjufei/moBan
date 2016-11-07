<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>添加驾校</title>

<!-- Bootstrap core CSS -->
<link href="../static/css/bootstrap.css" rel="stylesheet">
<link href="../static/css/main.css" rel="stylesheet">
<link href="../static/css/fileupload.css" rel="stylesheet">

</head>
<body>
<br/>
	<c:if test="${!empty error}">
		<font color="red">${error}</font>
	</c:if>
	<c:if test="${!empty ok}">
		<font color="green">${ok}</font>
	</c:if>
	<br>


	<form  action="addSchool.do" method="post" enctype="multipart/form-data" class="form-horizontal" >


		<div class="form-group">
			<label class="col-sm-2 control-label"><i class="red">*</i>驾校名称</label>
			
			<div class="col-sm-4">
				<input class="form-control inputNeedCheck"  maxlength="20" type="text" name="schoolName" value="${school.schoolName}" placeholder="驾校名称需保证唯一"/>
			</div>
		</div>


		<div class="form-group">
			<label class="col-sm-2 control-label"><i class="red">*</i>驾校经度</label>
			
			<div class="col-sm-4">
				<input class="form-control inputNeedCheck"  maxlength="20" type="text" name="schoolLng" value="${school.schoolLng}" placeholder="驾校经度" />
			</div>
		</div>
	
		<div class="form-group">
			<label class="col-sm-2 control-label"><i class="red">*</i>驾校纬度</label>
			
			<div class="col-sm-4">
				<input class="form-control inputNeedCheck"  maxlength="20" type="text" name="schoolLat" value="${school.schoolLat}" placeholder="驾校纬度" />
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-2 control-label"><i class="red">*</i>联 系 人</label>
			
			<div class="col-sm-4">
				<input class="form-control inputNeedCheck"  maxlength="20" type="text" name="contactPerson" value="${school.contactPerson}" placeholder="联系人" />
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-2 control-label"><i class="red">*</i>联系电话</label>
			
			<div class="col-sm-4">
				<input class="form-control inputNeedCheck"  maxlength="20" type="text" name="contactPhone" value="${school.contactPhone}" placeholder="联系人电话" />
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-2 control-label"><i class="red">*</i>驾校地址</label>
			
			<div class="col-sm-4">
				<input class="form-control inputNeedCheck"  maxlength="20" type="text" name="schoolAddress" value="${school.schoolAddress}" placeholder="驾校地址" />
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-2 control-label"><i class="red">*</i>教练简介</label>
			
			<div class="col-sm-4">
				<input class="form-control inputNeedCheck"  maxlength="20" type="text" name="driverInfo" value="${school.driverInfo}" placeholder="驾校教练简介" />
			</div>
		</div>
		
	
		<div class="form-group">
			<label class="col-sm-2 control-label" for="selectIIsshow"><i class="red">*</i>学校等级</label>
			<div class="col-sm-4">	
	
				<select name="schoolGrade"  class="form-control">
				
					<option  <c:if test="${'0' == school.schoolGrade}">selected</c:if> value="0">请选择学校的等级</option>
					<option  <c:if test="${'2' == school.schoolGrade}">selected</c:if> value="1">一星</option>
					<option  <c:if test="${'2' == school.schoolGrade}">selected</c:if> value="2">二星</option>
					<option  <c:if test="${'3' == school.schoolGrade}">selected</c:if> value="3">三星</option>
					<option  <c:if test="${'4' == school.schoolGrade}">selected</c:if> value="4">四星</option>
					<option  <c:if test="${'5' == school.schoolGrade}">selected</c:if> value="5">五星</option>		
			
				</select>	
			</div>
		</div>



		<div class="form-group">
			<label class="col-sm-2 control-label" for="inputFile"><i class="red">*</i>上传驾校Logo</label>
				<input type="file"  name="files" >
		</div>
		
		<div class="form-group">
			<label class="col-sm-2 control-label" for="inputFile"><i class="red">*</i>上传驾校图片(可选多张)</label>
				<input type="file"  name="files" multiple="multiple"  >
		</div>
		
		<div class="form-group">
			<label class="col-sm-2 control-label" for="inputFile"><i class="red">*</i>上传驾校视频</label>
				<input type="file"  name="files"  >
		</div>



		<div class="form-group">
			<label class="col-sm-2 control-label" for="inputStrIntroduce"><i class="red">*</i>驾校简介</label>
			<div class="col-sm-6">
				<textarea class="form-control" data-rule-required="true" rows="10" name="schoolInfo" placeholder="驾校简介,小于1000字" >${school.schoolInfo}</textarea>
			
			</div>
		</div>


		<div class="form-group">
			 <div class="col-sm-offset-2 col-sm-6">
			 	<input type="submit" class="btn btn-primary" value="保 存" />
 			</div>
		</div>
			
	</form>





</body>
</html>