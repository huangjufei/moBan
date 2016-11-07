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


<title>驾校信息</title>

<link href="../static/css/bootstrap.css" rel="stylesheet">
<link href="../static/css/jquery.pagination.css" rel="stylesheet">
<link href="../static/css/main.css" rel="stylesheet">




<script type="text/javascript"  src="../static/js/tool/jquery.js"></script>
<script type="text/javascript">
	//删除操作:根据询问返回值来确定是否需要发送 超链接
	$(function(){
		$(".delete").click(function(){
			var content = $(this).parent().parent().find("td:eq(2)").text();
			var flag = false;
			flag = confirm("你确定需要删除名字为  "+ content + "  的驾校?");
			return flag;
		})
	});
</script>




</head>

<body>

	<c:if test="${!empty error}">
		<font color="red">${error}</font>
	</c:if>	
	<c:if test="${!empty ok}">
		<font color="green">${ok}</font>
	</c:if>			
	<c:if test="${empty requestScope.pages.t}">
		数据库没有驾校信息
	</c:if>
	

		
		<form action="getViewSchoolList.do" method="post">
			<div class="col-sm-2">
				<input class="form-control inputNeedCheck"  maxlength="20" type="text" name="searchSchoolName"  placeholder="请输入驾校的名字"/>
			</div>
				<input type="submit" class="btn btn-primary" value="搜索" />　　　
				<a href="addSchoolPage.do"  class="btn btn-primary">添加驾校</a>
		</form>
		
		

			<table class="table table-bordered table-striped table-hover">
				<thead>
					<tr>
						<th width="6%">序号</th>
						<th width="8%">驾校Id</th>
						<th width="16%">驾校名称</th>
						<th width="8%">驾校联系人</th>
						<th width="10%">联系电话</th>
						<th width="6%">驾校等级</th>
						<th width="8%">驾校经度</th>
						<th width="8%">驾校纬度</th>
						<th width="8%">修改</th>
						<th width="8%">删除</th>

					</tr>				
				</thead>
	
	
	
	
		<tbody>
		
		<c:if test="${!empty pages.t}">
		
	
				
			<c:forEach items="${pages.t}" var ="s" varStatus="sta">
				<tr>		
					<td>${sta.index+1}</td>					
					<td>${s.schoolId}</td>
					<td>${s.schoolName}</td>
					<td>${s.contactPerson}</td>
					<td>${s.contactPhone}</td>
					<td>${s.schoolGrade }</td>
					<td>${s.schoolLng}</td>	
					<td>${s.schoolLat}</td>		
					<td><a href="updateSchoolPage.do?schoolId=${s.schoolId}">修改</a></td>		
					<td><a href="deleteSchool.do?schoolId=${s.schoolId}" class="delete" >删除</a></td>
				</tr>
			</c:forEach>
					
			<tr>
							
				<td  colspan="10">
				
					第${pages.pageNum}页&nbsp;
								
					<c:if test="${pages.pageNum  != 1 }">		
						<a href="getViewSchoolList.do?pageNum=1">首页</a>&nbsp;
						<a href="getViewSchoolList.do?pageNum=${pages.pageNum-1}">上一页</a>&nbsp;	
							
					</c:if>
					
					<c:if test="${pages.pageNum != pags.totalPage}">		
						<a href="getViewSchoolList.do?pageNum=${pages.pageNum+1}">下一页</a>&nbsp;
						<a href="getViewSchoolList.do?pageNum=${pages.totalPage}">末页</a>&nbsp;			
					</c:if>
			
					一共有${pages.totalNumber}条记录&nbsp;&nbsp;
			
				</td>
			</tr>	
	
		</c:if>
		

		
		</tbody>	
	</table>
			

	

</body>
</html>
