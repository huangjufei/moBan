$(function(){
	
	var _topWin = window;
	while (_topWin != _topWin.parent.window)
	{ 
		_topWin = _topWin.parent.window;
	}
	if (window != _topWin){
		_topWin.document.location.href = 'login.do'
	}
	
	$.ajaxSetup({ cache: false }); 
    $(document).keydown(function(event){
        var curKey = event.which;
        if(curKey==13){
            $("#login_bt").click();
        }
        
    });
    
    //登录按钮点击事件
	$("#login_bt").on("click",function(){
		
	    var username=$("#username").val();
	    var password=$("#password").val();
	    
	    if(username==null||""==username){
	        show_errorinfo("您必须输入用户名！");
	        return;
	    }
	    
	    if(password==null||""==password){
	        show_errorinfo("您必须输入密码！");
	        return;
	    }
	    login(username,password);
	});


	/**
	 * 执行登陆函数
	 * @param username 用户输入的用户名
	 * @param password 用户输入的密码
	 */
	function login(username,password) {
		
		//请在这里添加ajax登陆请求处理函数
		$.ajax({
	    	url:"loginCheck.do",
	    	type:"post",
	     	timeout:"100000",
	    	dataType:"json",
	    	data:{"strUserName":username,"strUserPassword":password},
	    	success:function(data){
	    		var status=data.state;
	    		if(status==0){
	                var hrefurl="homePage.do";
	                window.location.href=hrefurl;
	    		}
	    		else{
	    			var errMsg=data.errMsg;
	    			show_errorinfo(errMsg);	
	    		}
	    	},
	    	error:function(xmlHttpRequest, textStatus, errorThrown){
	    		if(textStatus=="timeout"){
	    			show_errorinfo("哦哦，服务器太远，链接超时！");	
	    		}else if(textStatus=="parsererror"){
	    			show_errorinfo("哦哦，数据解析出错！");	
	    		}else{
	    			show_errorinfo("哦哦，系统被你们玩儿死了！");	
   	    		}
    		}
		});
	}
	
	/**
	 * 弹出显示错误信息
	 * @param errorinfo 错误信息内容
	 */
	function show_errorinfo (errorinfo){
	    var error=$("#error");
		error.text(errorinfo);
		error.removeClass("hidden");
		error. fadeOut(3000,function(){
		    error.show();
		    error.addClass("hidden");
	    });
	}
	
	
});