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

<title>订单信息</title>

<link href="../static/css/bootstrap.css" rel="stylesheet">
<link href="../static/css/jquery.pagination.css" rel="stylesheet">
<link href="../static/css/main.css" rel="stylesheet">



<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<!--  %@ include file="../common/parameter.jsp"%>-->
	<c:choose>
		<c:when test='${totalNums!="0"}'>
			<div class="container-fluid" style="margin-bottom: 10px;">
				<form class="form-inline" action="selectOrder.do" method="post" id="queryForm">
					<div class="row">
					  	<div class="col-md-2 form-group">
					  		<label for="selectIState">订单状态</label>
					  		<select id="selectIState" name="orderStatus" class="form-control">
					  			<option value="0">查询全部</option>
					  			<option value="-1" <c:if test='${queryOrder.orderStatus=="-1"}'>selected</c:if>>下单</option>
					  			<option value="1" <c:if test='${queryOrder.orderStatus=="1"}'>selected</c:if>>待支付</option>
					  			<option value="2" <c:if test='${queryOrder.orderStatus=="2"}'>selected</c:if>>支付完成</option>
					  		</select>
					  	</div>
					  	<div class="col-md-2 form-group">
					  		<label for="selectIPayway">支付方式</label>
					  		<select id="selectIPayway" name="payWay" class="form-control">
					  			<option value="0">查询全部</option>
					  			<option value="1" <c:if test='${queryOrder.payWay=="1"}'>selected</c:if>>微信</option>
					  			<option value="2" <c:if test='${queryOrder.payWay=="2"}'>selected</c:if>>支付宝</option>
					  			<option value="3" <c:if test='${queryOrder.payWay=="3"}'>selected</c:if>>网关</option>
					  		</select>
					  	</div>
					  	<div class="col-md-2 form-group">
						  	<input type="hidden" id="page" name="page" value="${page.page}"/>
						  	<input type="hidden" id="pagesize" name="pagesize" value="${page.pagesize}"/>
						  	<input type="hidden" id="totalNums" name="totalNums" value="${totalNums}"/>					  
						  	<input type="submit" class="form-control btn btn-primary" value="查询">
					  	</div>
					</div>
				</form>
			</div>
			<table class="table table-bordered table-striped table-hover">
				<thead>
					<tr>
						<th width="15%">订单号</th>
						<th width="8%">班级类型</th>
						<th width="10%">价格类型</th>
						<th width="5%">用户名</th>
						<th width="10%">电话号码</th>	
						<th width="6%">金额</th>
						<th width="22%">选择的场地</th>
						<th width="8%">支付时间</th>
						<th width="8%">支付方式</th>
						<th width="8%">支付状态</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="data" items="${orderList}">
						<tr>
							<td>${data.orderId}</td>
							<td>${data.classType}</td>
							<td>${data.className}</td>
							<td>${data.userName}</td>
							<td>${data.phone}</td>
							<td>${data.totalPrice}</td>
							<td>${data.areaInfo}</td>
							<td>${data.payTime}</td>
							<td>
								<c:choose>
									<c:when test='${data.payWay==1}'>
										微信
									</c:when>
									<c:when test='${data.payWay==2}'>
										支付宝
									</c:when>
									<c:when test='${data.payWay==3}'>
										网关
									</c:when>
									<c:otherwise>
										
									</c:otherwise>
								</c:choose>
							</td>
							<td>
							    <c:if test='${data.orderStatus==-1}'>
									下单
								</c:if>
								<c:if test='${data.orderStatus==1}'>
									待支付
								</c:if>
								<c:if test='${data.orderStatus==2}'>
									支付完成
								</c:if>
							</td>							
                        	</tr>
					</c:forEach>
				</tbody>
			</table>
			<div id="page4" class="m-pagination"></div>
			</c:when>
			<c:otherwise>
			<div class="no_data">暂无数据</div>
		</c:otherwise>
	</c:choose>
	
	<!-- Bootstrap core JavaScript
================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="../static/js/tool/jquery.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="../static/js/tool/bootstrap.min.js"></script>
	<script src="../static/js/tool/jquery.pagination-1.2.1.js"></script>
	<script src="../static/js/page/common/common.js"></script>
	<script src="../static/js/page/common/page.js"></script>
</body>
</html>
