$(".dhx_order").click(function() {
	var orderStatus = $("#orderStatus").val();
	if(orderStatus == "DESC") {
		$("#orderStatus").val("ASC")
	} else if(orderStatus == "ASC"){
		$("#orderStatus").val("DESC")
	}
	$("#queryForm").submit();
});


/**
 * 判断某个输入框的值是否在数据库中已经存在
*/
function checkInputIsRepeat(tag) {
	var id = $(tag).attr("data-id");
	var inputValue = $(tag).val();
	var operationHref = $(tag).attr("data-href");
	$.ajax({
		  type: 'POST',
		  async:'FALSE',
		  url: operationHref,
		  timeout:"100000",
		  data: {"id":id,"inputValue":inputValue},
		  dataType: 'JSON',
		  success: function(result) {
			  var status=result.state;
			  if(status==0) {
				  var data=result.data;
				  if(data=="true") {
					  //表示输入的值在数据库中存在
					  $(tag).parent().next().show();
					  $(tag).parent().next().attr("data-result",data);
				  } else {
					  $(tag).parent().next().hide();
					  $(tag).parent().next().attr("data-result",data);
				  }
			  } else {
				  var errMsg = result.errMsg;
				  alert(errMsg);
			  }
		  },
		  error: function(xmlHttpRequest, textStatus, errorThrown){
 	    		if(textStatus=="timeout"){
 	    			alert("哦哦，服务器太远，链接超时！");	
 	    		}else if(textStatus=="parsererror"){
 	    			alert("哦哦，数据解析出错！");	
 	    		}else{
 	    			alert("哦哦，系统被你们玩儿死了！");	
 	    		}
 	    	}
	});
}
//需要验证某些输入的值是否重复的时候
$(".inputNeedCheck").blur(function() {
	checkInputIsRepeat(this);
});
//提交，验证有些需要验证某些输入的值是否重复
$("#btn_submit").click(function() {
	var flag = true;
	$(".errorInfo").each(function() {
		var checkResult = $(this).attr("data-result");
		if(checkResult=="true") {
			//有一个严重没有通过
			flag = false;
			return false;
		}
	});
	if(flag) {
		$("#submit").click();
	}
});
//点击删除按钮
$(".btn_delete").click(function() {
	deleteOperation(this);
});

//上/下架产品
function banOperation(tag) {
	var operationId = $(tag).attr("data-id");
	if(operationId==null||operationId==undefined||operationId=='') {
		//operationId为空，不能操作
		alert("请选择正确的操作记录");
		return;
	}
	var status = $(tag).attr("data-status");
	var remark = "";
	if(status==-1) {
		remark = "确定上架该任务？";
	}
	if(status==1) {
		remark = "确定下架该任务？";
	}
	var flag = confirm(remark);
	if(!flag) {
		return;
	}
	
	var operationHref = $(tag).attr("data-href");
	$.ajax({
		  type: 'POST',
		  async:'FALSE',
		  url: operationHref,
		  timeout:"100000",
		  data: {"recordId":operationId,"iIsban":status},
		  dataType: 'JSON',
		  success: function(result) {
			  var status=result.state;
			  if(status==0) {
				  alert("操作成功");
				  window.location.reload();
			  } else {
				  var errMsg=result.errMsg;
				  if(errMsg=="noLogin") {
					  //超级管理员
					  window.document.location.href = zhuanquanquanRootPath + "admin/login.do";
				  } else {
					  alert(errMsg);
				  }
			  }
		  },
		  error: function(xmlHttpRequest, textStatus, errorThrown){
   	    		if(textStatus=="timeout"){
   	    			alert("哦哦，服务器太远，链接超时！");	
   	    		}else if(textStatus=="parsererror"){
   	    			alert("哦哦，数据解析出错！");	
   	    		}else{
   	    			alert("哦哦，系统被你们玩儿死了！");	
   	    		}
   	    	}
	});
}

//上/下架点击
$(".btn_ban").click(function() {
	banOperation(this);
});


