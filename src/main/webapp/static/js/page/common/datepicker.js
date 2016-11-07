/**
 *日历选择插件JS
 */
var curDate = new Date(); 
$('#datetimepicker').datetimepicker({
	language:'zh-CN',
	startDate:curDate,
	showMeridian:true,
	autoclose:true,
	pickDate:true,
	minView:2,
	pickTime:false,
	totayBtn:true
});
$('#datetimepicker1').datetimepicker({
	language:'zh-CN',
	startDate:curDate,
	showMeridian:true,
	autoclose:true,
	pickDate:true,
	minView:2,
	pickTime:false,
	totayBtn:true
});

$("#startTime").datetimepicker({
	language:'zh-CN',
	showMeridian:true,
	autoclose:true,
	pickDate:true,
	minView:2,
	pickTime:false,
	totayBtn:true
}).on('changeDate', function(ev){
		var startTime = $("#"+$("#startTime").attr("data-link-field")).val();
		$("#endTime").datetimepicker('setStartDate',startTime);
		$("#startTime").datetimepicker('hide');
});

$("#endTime").datetimepicker({
	language:'zh-CN',
	showMeridian:true,
	autoclose:true,
	pickDate:true,
	minView:2,
	pickTime:false,
	totayBtn:true
}).on('changeDate', function(ev){
		var endTime = $("#"+$(this).attr("data-link-field")).val();
		$("#startTime").datetimepicker('setEndDate',endTime);
		$(this).datetimepicker('hide');
});
