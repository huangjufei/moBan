//回显后台数据验证返回的错误
$(document).ready(function() {
	 var error = $("#error");
	 var errorInfo = error.text();
	 if(errorInfo != "") {
		 //有错误信息,需要显示
		 error.removeClass("hidden");
		 error.fadeOut(3000,function(){
		    error.show();
		    error.addClass("hidden");
		 });
	 }
});