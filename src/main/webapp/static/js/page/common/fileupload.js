function changeImgIndex(_imagebox,index) {
	var imgObjects = _imagebox.find(".uploader");
	$(imgObjects).each(function() {
		var dataIndex = $(this).attr("data-index");
		if(dataIndex>index) {
			var newDataIndex = dataIndex-1;
			$(this).attr("data-index",newDataIndex)
			$(this).find("img").attr("id","img" + newDataIndex);
			$(this).find("input[type=file]").attr("id","file" + newDataIndex);
			$(this).find("input[type=file]").attr("data-index", newDataIndex);
			$(this).find("input[name='filepath']").attr("id","filepath" + newDataIndex);
			$(this).find("input[type=button]").attr("data-index", newDataIndex);
		}
	});
}
$(function(){
	$("body").on("click",".-lpy-file-upload-btn",function(){
		$(this).next().click();
	});
	//给上传文件框绑定事件
	$(".contentBox").on("change","input[type=file]",function(){ 
		$(this).parents(".uploader").find(".filename").val($(this).val());
		var index = $(this).attr("data-index");
		var uploadUrl = $(this).attr("data-url");
		ajaxFileUpload(index,uploadUrl);
	});
	$("input[type=file]").each(function(){
	if($(this).val()==""){$(this).parents(".uploader").find(".filename").val("请选择图片...");}
	});
	//增加按钮点击事件
	$("#btn_add").click(function() {
		var index = $(this).parent().parent().find(".uploader").size()+1;
		var html = '<div class="uploader blue -lpy-upload-content" data-index="'+index+'">'+
		    			'<div class="-lpy-upload-img">'+
 						'<img style="width: 300px;height: 160px;" src="https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3176667768,1161431363&fm=116&gp=0.jpg" id="img'+index+'">'+
					    '</div>'+
						'<input type="hidden" name="filepath" id="filepath'+index+'"/>'+
						'<div class="-lpy-div">'+
					    '<input type="text" class="filename" readonly="readonly"/>'+
						'<input type="button" class="button -lpy-file-upload-btn" value="浏览..."/>'+
					    '<input type="file" data-url="../admin/merchantQuahonorUpload.do" data-index="'+index+'" name="file" id="file'+index+'"/>'+
					    '<div class="cb"></div>'+
						'</div>'+
						'<div class="-lpy-div">'+
							'<input type="text" class="-lpy-cloth-type-input" /><input type="button" data-index="'+index+'" class="btn btn-danger btn_delete_image" value="删除" />'+
		 				'<div class="cb"></div>'+
						'</div>'+
					'</div>';
		$(this).parent().parent().append(html);
	});
	//给删除按钮绑定点击事件
	$(".contentBox").on("click",".btn_delete_image",function(){ 
		var index = $(this).attr("data-index");
		if(index==1) {
			alert("最后一张，不能在删除了");
			return;
		}
		changeImgIndex($(this).parent().parent().parent(),index);
		$(this).parent().parent().remove();
	});
});
//保存按钮点击事件
$("#btn_submit").click(function() {
	var jsonString = '[';
	$(".uploader").each(function() {
		var name = $(this).find(".-lpy-cloth-type-input").val();
		var imgSrc = $(this).find("input[name='filepath']").val();
		var templetImgDetailId = $(this).find("input[name='templetImgDetailId']").val();
		jsonString += '{"templetImgDetailId":"'+templetImgDetailId+'","name":"'+name+'","imgSrc":"'+imgSrc+'"},'
	});
	jsonString = jsonString.substring(0,jsonString.length-1);
	jsonString += ']';
	console.log(jsonString);
	$("#J_jsonString").val(jsonString);
	$("#submitForm").submit();
});
//ajax上传图片
function ajaxFileUpload(index,uploadUrl)
{
    $.ajaxFileUpload
    (
        {
        	type:'post',
            url:uploadUrl,//用于文件上传的服务器端请求地址
            secureuri:false,//一般设置为false
            fileElementId:'file' + index,//文件上传空间的id属性  <input type="file" id="file" name="file" />
            dataType: 'text',//返回值类型 一般设置为json   
            success: function (data)  //服务器成功响应处理函数
            {
            	var returnData = eval("(" + data + ")");
            	var state = returnData.state;
            	if(state==0) {
            		var filePath = returnData.data;
            		$("#img" + index).attr("src",imgRootPath + filePath);
            		$("#filepath" + index).val(filePath);
            	} else {
            		var errMsg = returnData.errMsg;
            		alert(errMsg);
            	}
            },
            error: function (data, status, e)//服务器响应失败处理函数
            {
                alert(e);
                console.log(data, status, e)
            }
        }
    )
}