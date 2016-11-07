<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
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


	
	<form  action="updateArea.do" method="post" enctype="multipart/form-data" class="form-horizontal" >

		<input type="hidden" name="areaId" value="${area.areaId}">
		<input type="hidden" name="areaName" value="${area.areaName}">


			<div class="form-group">
				<label class="col-sm-2 control-label" for="selectIIsshow"><i class="red">*</i>场地属于那个驾校</label>
				<div class="col-sm-4">
					<select id="selectIIsshow" name="schoolId" class="form-control" style="width: 80">					
			  			<c:forEach items="${schools}" var="s">	
			  			
							<option  <c:if test='${sac.schoolId == s.schoolId}'>selected</c:if>  value="${s.schoolId}">${s.schoolName} </option>
						</c:forEach>
  					</select>
				</div>
			</div>
			
	

			<div class="form-group">
				<label class="col-sm-2 control-label"><i class="red">*</i>场地名称</label>
				
				<div class="col-sm-4">
					<input class="form-control inputNeedCheck"  maxlength="20" type="text" name="areaName" value="${area.areaName}" disabled="disabled"/>
				</div>
			</div>


			<div class="form-group">
				<label class="col-sm-2 control-label"><i class="red">*</i>场地经度</label>
				
				<div class="col-sm-4">
					<input class="form-control inputNeedCheck"  maxlength="20" type="text" name="areaLng" value="${area.areaLng}" placeholder="场地经度" />
				</div>
			</div>
		
			<div class="form-group">
				<label class="col-sm-2 control-label"><i class="red">*</i>场地纬度</label>
				
				<div class="col-sm-4">
					<input class="form-control inputNeedCheck"  maxlength="20" type="text" name="areaLat" value="${area.areaLat}" placeholder="场地纬度" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label"><i class="red">*</i>场地地址</label>
				
				<div class="col-sm-4">
					<input class="form-control inputNeedCheck"  maxlength="20" type="text" name="areaAddress" value="${area.areaAddress}" placeholder="场地地址" />
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label"><i class="red">*</i>场地编号</label>
				
				<div class="col-sm-4">
					<input class="form-control inputNeedCheck"  maxlength="20" type="text" name="areaNumber" value="${area.areaNumber}" placeholder="场地编号" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label"><i class="red">*</i>活动标记</label>
				
				
				
				<div class="col-sm-4">
					<select name="classmark"  class="form-control">			
						<option  <c:if test="${'0' == area.classmark}">selected</c:if> value="0">请选择活动</option>
						<option  <c:if test="${'1' == area.classmark}">selected</c:if> value="1">惊爆价</option>
						<option  <c:if test="${'2' == area.classmark}">selected</c:if> value="2">活动价</option>
						<option  <c:if test="${'3' == area.classmark}">selected</c:if> value="3">门店价</option>										
						<option  <c:if test="${'1,2' == area.classmark}">selected</c:if> value="1,2">惊爆价,活动价</option>										
						<option  <c:if test="${'1,3' == area.classmark}">selected</c:if> value="1,3">惊爆价,门店价</option>																	
						<option  <c:if test="${'2,3' == area.classmark}">selected</c:if> value="2,3">活动价,门店价</option>
						<option  <c:if test="${'1,2,3' == area.classmark}">selected</c:if> value="1,2,3">惊爆价,活动价,门店价</option>																				
					</select>
					
				</div>
		
			</div>
			
			
			<div class="form-group">
				<label class="col-sm-2 control-label" for="inputFile"><i class="red">*</i>上传场地图片(可选多张)</label>
					<input type="text" name="fielInfo" disabled="disabled" maxlength="30" placeholder="图片无需修改,可以不选"/> 
					<input type="file"  name="files" multiple="multiple"  onchange="document.getElementById('textfield').value=this.value" >
			</div>
			
	
	
		

			
			
			
			
			

			<div class="form-group">
				<label class="col-sm-2 control-label" for="inputStrIntroduce"><i class="red">*</i>场地简介</label>
				<div class="col-sm-6">
					<textarea class="form-control" data-rule-required="true" rows="10" id="inputStrIntroduce" name="areaInfo" >${area.areaInfo}</textarea>
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








