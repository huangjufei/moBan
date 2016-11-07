var totalNums = parseInt($("#totalNums").val());
var page = parseInt($("#page").val());
var pagesize = parseInt($("#pagesize").val());
$("#page4").page({
    total: totalNums,
    firstBtnText: '首页',
    lastBtnText: '尾页',
    prevBtnText: '上一页',
    nextBtnText: '下一页',
    showInfo: true,
    showJump: true,
    currentPage:page,
    pageSize:pagesize,
    jumpBtnText:'跳转',
    showPageSizes: true,
    infoFormat: '共{total}条'
}).on("pageClicked", function (event, data) {
	$("#page").val(data);
	$("#queryForm").submit();
}).on('jumpClicked', function (event, data) {
	$("#page").val(data);
	$("#queryForm").submit();
}).on('pageSizeChanged', function (event, data) {
	$("#pagesize").val(data);
	$("#queryForm").submit();
});