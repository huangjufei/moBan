
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

<script type="text/javascript"  src="../static/js/tool/jquery.js"></script>
<script type="text/javascript">


$(document).ready(function(){
	$('#subb').click(function(){
		
		
		var age = $('#age').val();
		var beginTime = $('#beginTime').val()
		var pr = $('#pr').val();
		
		
		if(age == null || age == ""){
			$("#sage").text("教练的年龄不能为空");
			return false;
		}
		if(beginTime == null || beginTime == ""){
			$("#sbeginTime").text("教龄不能为空");
			return false;
		}
		if(pr == null || pr == ""){
			$("#spr").text("教练的学员通过率不能为空");
			return false;
		
		}
		
	
		
		  $("#form1").submit();

	
		
	})
})


//判输入的值必须是一个数组类型   		
function checkNumber(input){
	
    var re = /^[0-9]+.?[0-9]$/;   //判断字符串是否为数字 
    var number = document.getElementById(input).value;  
     if (!re.test(number)){
        alert("请输入正确的数字!");
        document.getElementById(input).value = "";
        return false;
     }
}  		











/*	
$(document).ready(function(){
	$("#agea").on("keyup",function(){
		var agea=$(this).val();
		var re = /^[0-9]+.?[0-9]*$/;
		if(agea==""||agea==null || agea==undefined){
	    	 return false;
	     }
		else if (!re.test(agea))
	    {
	        alert("请输入数字");
	        return false;
	     }
	})
})
	
	
*/	
	

	
</script>


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

				
	<form name="form1" id="form1" action="addDriver.do" method="post" enctype="multipart/form-data" class="form-horizontal" >
	

		<div class="form-group">
			<label class="col-sm-2 control-label" for="selectIIsshow"><i class="red">*</i>教练属于那个场地</label>
			<div class="col-sm-4">
				<select id="selectIIsshow" name="areaId" class="form-control" style="width: 80">	
					<option value="0" >请选择教练属于那个场地</option>							
		  			<c:forEach items="${areas}" var="a">		
						<option <c:if test='${areaIds == a.areaId}'>selected</c:if> value="${a.areaId}">${a.areaName}</option>
					</c:forEach>
 					</select>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-2 control-label"><i class="red">*</i>教练姓名</label>
			
			<div class="col-sm-4">
				<input class="form-control inputNeedCheck"  maxlength="20" type="text" name="driverName" value="${driver.driverName}" placeholder="教练的真实姓名"/>
			</div>
		</div>
	
	
	
	
		<div class="form-group">
			<label class="col-sm-2 control-label"><i class="red">*</i>教练年龄</label>
			
			<div class="col-sm-4">
				<input class="form-control inputNeedCheck"  maxlength="20" type="text" name="driverAge" value="${driver.driverAge}" id="age" onchange="checkNumber('age')" placeholder="教练年龄" />
				<span id="sage" style="color:red;"></span>
			</div>
		</div>
	
		<div class="form-group">
			<label class="col-sm-2 control-label"><i class="red">*</i>教练的教授年数</label>
			
			<div class="col-sm-4">
				<input class="form-control inputNeedCheck"  maxlength="20" type="text" name="beginAge" value="${driver.beginAge}" id="beginTime" placeholder="教练的教授年数" />
				<span id="sbeginTime" style="color:red;"></span>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-2 control-label"><i class="red">*</i>教练性别</label>
			
			<div class="col-sm-4">

				<input type="radio" name="driverSex" value="0" checked='<c:if test="${driver.driverSex ==0}">女</c:if>' > 女
				<input type="radio" name="driverSex" value="1" checked='<c:if test="${driver.driverSex ==1}">男</c:if>' > 男
		
			
			</div>
		</div>
	
	
		<div class="form-group">
			<label class="col-sm-2 control-label"><i class="red">*</i>学员通过率</label>
			
			<div class="col-sm-4">
				<input class="form-control inputNeedCheck"  maxlength="20" type="text" name="passRate" value="${driver.passRate}" id="pr" onchange="checkNumber('pr')" placeholder="学员通过率" />
				<span id="spr" style="color:red;"></span>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label"><i class="red">*</i>可以教授的范围</label>
			
			<div class="col-sm-4">					
					<select name="subject"  class="form-control">			
					<option  <c:if test="${'0' == driver.subject}">selected</c:if> value="0">请选择教练教授的范围</option>
					<option  <c:if test="${'1' == driver.subject}">selected</c:if> value="1">C1</option>
					<option  <c:if test="${'2' == driver.subject}">selected</c:if> value="2">C2</option>
					<option  <c:if test="${'3' == driver.subject}">selected</c:if> value="3">C1和C2</option>										
				</select>
						
			</div>
		</div>
	
		<div class="form-group">
			<label class="col-sm-2 control-label" for="selectIIsshow"><i class="red">*</i>教练等级</label>
			<div class="col-sm-4">	
				<select name="driverGrade"  class="form-control">			
					<option  <c:if test="${'0' == driver.driverGrade}">selected</c:if> value="0">请选择教练的等级</option>
					<option  <c:if test="${'1' == driver.driverGrade}">selected</c:if> value="1">一星</option>
					<option  <c:if test="${'2' == driver.driverGrade}">selected</c:if> value="2">二星</option>
					<option  <c:if test="${'3' == driver.driverGrade}">selected</c:if> value="3">三星</option>
					<option  <c:if test="${'4' == driver.driverGrade}">selected</c:if> value="4">四星</option>
					<option  <c:if test="${'5' == driver.driverGrade}">selected</c:if> value="5">五星</option>					
				</select>	
		
			</div>
		</div>
		

		
		<div class="form-group">
			<label class="col-sm-2 control-label"><i class="red">*</i>上传教练个人风采图片(可选多张)</label>
				<input type="file"  name="files" multiple="multiple"  >
		</div>
		
	
	
	
		<div class="form-group">
			<label class="col-sm-2 control-label"><i class="red">*</i>教练简介</label>
			<div class="col-sm-6">
				<textarea class="form-control" data-rule-required="true" rows="10"  name="driverInfo" placeholder="教练简介,小于500字" >${driver.driverInfo}</textarea>
			</div>
		</div>
	
	
		<div class="form-group">
			 <div class="col-sm-offset-2 col-sm-6">
				 <input type="button" id="subb" value="保 存" class="btn btn-primary">
			</div>
		</div>
			
		 
	</form>




</body>


</html>















