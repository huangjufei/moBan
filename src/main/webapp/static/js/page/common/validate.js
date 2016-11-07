
//配置错误提示的节点，默认为label，这里配置成 span （errorElement:'span'）
$.validator.setDefaults({
	errorElement:'span'
});
//配置通用的默认提示语
$.extend($.validator.messages, {
	required: "必填",
    equalTo: "请再次输入相同的值"
});
//正整数（含0）
jQuery.validator.addMethod("nnInt", function (value, element) {
	var posInt = /^\d+$/;
	return this.optional(element) || (posInt.test(value));
}, "请输入大于等于0的正整数");
//正整数
jQuery.validator.addMethod("posInt", function (value, element) {
	var posInt = /^[0-9]*[1-9][0-9]*$/;
	return this.optional(element) || (posInt.test(value));
}, "请输入大于0的正整数");
//手机验证规则  
jQuery.validator.addMethod("mobile", function (value, element) {
    var mobile = /^1[3|4|5|7|8]\d{9}$/;
	return this.optional(element) || (mobile.test(value));
}, "手机号码格式不正确");
//折扣验证规则
jQuery.validator.addMethod("dis", function (value, element) {
    var patten = /^0\.[1-9]\d*$/;
	return this.optional(element) || (patten.test(value));
}, "只能是0-1之间的小数（不含边界）");
//提成验证规则
jQuery.validator.addMethod("rp", function (value, element) {
    var patten = /^0\.[0-9]\d*$/;
	return this.optional(element) || (patten.test(value));
}, "只能是0-1之间的小数（含0.0）");
//奖品权重验证规则
jQuery.validator.addMethod("aw", function (value, element) {
    var patten = /(^[1-9][0-9]$)|(^100)|(^[1-9]$)$/ ;
	return this.optional(element) || (patten.test(value));
}, "只能是1-100之间的小数（含边界）");

//区号验证规则  
jQuery.validator.addMethod("ac", function (value, element) {
    var ac = /^0\d{2,3}$/;
    return this.optional(element) || (ac.test(value));
}, "城市编码如：010或0371");

//邮箱 
jQuery.validator.addMethod("mail", function (value, element) {
	var mail = /^[a-z0-9._%-]+@([a-z0-9-]+\.)+[a-z]{2,4}$/;
	return this.optional(element) || (mail.test(value));
}, "邮箱格式不对");

//电话验证规则
jQuery.validator.addMethod("phone", function (value, element) {
    var phone = /^0\d{2,3}-\d{7,8}$/;
    return this.optional(element) || (phone.test(value));
}, "电话格式如：0371-68787027");

//价格验证规则
jQuery.validator.addMethod("double", function (value, element) {
    var double = /^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
    return this.optional(element) || (double.test(value));
}, "只能是大于0的数字");

//非负数字，包括小数
jQuery.validator.addMethod("posDouble", function (value, element) {
    var posDouble = /^\d+(\.\d+)?$/;
    return this.optional(element) || (posDouble.test(value));
}, "只能是大于等于0的数字");


//无区号电话验证规则  
jQuery.validator.addMethod("noactel", function (value, element) {
    var noactel = /^\d{7,8}$/;
    return this.optional(element) || (noactel.test(value));
}, "电话格式如：68787027");

//邮箱或手机验证规则  
jQuery.validator.addMethod("mm", function (value, element) {
    var mm = /^[a-z0-9._%-]+@([a-z0-9-]+\.)+[a-z]{2,4}$|^1[3|4|5|7|8]\d{9}$/;
	return this.optional(element) || (mm.test(value));
}, "格式不对");

//电话或手机验证规则  
jQuery.validator.addMethod("tm", function (value, element) {
    var tm=/(^1[3|4|5|7|8]\d{9}$)|(^\d{3,4}-\d{7,8}$)|(^\d{7,8}$)|(^\d{3,4}-\d{7,8}-\d{1,4}$)|(^\d{7,8}-\d{1,4}$)/;
    return this.optional(element) || (tm.test(value));
}, "电话或手机格式不对");

//传真
jQuery.validator.addMethod("fax",function(value,element){
    var fax = /^(\d{3,4})?[-]?\d{7,8}$/;
    return this.optional(element) || (fax.test(value));
},"传真格式如：0371-68787027");

//身份证
jQuery.validator.addMethod("idCard", function (value, element) {
    var isIDCard1=/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;//(15位)
    var isIDCard2=/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;//(18位)
    return this.optional(element) || (isIDCard1.test(value)) || (isIDCard2.test(value));
}, "格式不对");

//URL地址
jQuery.validator.addMethod("url", function (value, element) {
	var url = /(((^https?:(?:\/\/)?)(?:[-;:&=\+\$,\w]+@)?[A-Za-z0-9.-]+|(?:www.|[-;:&=\+\$,\w]+@)[A-Za-z0-9.-]+)((?:\/[\+~%\/.\w-_]*)?\??(?:[-\+=&;%@.\w_]*)#?(?:[\w]*))?)$/g;
    return this.optional(element) || (url.test(value));
}, "请输入正确的HTTP地址");

//16进制颜色值
jQuery.validator.addMethod("color", function (value, element) {
	var url = /^([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$/;
    return this.optional(element) || (url.test(value));
}, "请输入正确的16进制颜色值");
