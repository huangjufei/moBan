package cn.jiaxiaoAdmin.util;


/**
 * 
 * @描述：静态常量类
 * @作者： 丁洪星
 * @文件名：StaticConstants.java 
 * @包名：cn.util 
 * @项目名：zhuanquanquan
 * @部门：伏守科技项目开发部
 * @日期： 2015年11月4日 下午2:30:20 
 * @版本： V1.0
 */
public class StaticConstants {
	/**
	 * 手机端分页每页显示条数
	 */
	public static final int PAGE_SIZE = 20;
	
	/**
	 * 转圈子web项目根路径
	 */
	public static final String WEB_ROOT_URL = "http://app.zhuanquanzi.com:8080/zhaunquanziweb";
	/**
	 * 转圈子接口项目根路径
	 */
	public static final String ROOT_URL = "http://app.zhuanquanzi.com:8080/zhuanquanquan/";
	/**
	 * 热门任务分享地址
	 */
	public static final String TASK_SHARE_URL = WEB_ROOT_URL + "page/shareTaskDetail.do";
	/**
	 * 去支付
	 */
	public static final String PAY_URL = WEB_ROOT_URL + "order/payIndex.do";

	
	/**
	 * 图片上传路径
	 */
	//public static final String FILE_UPLOAD_URL = "c:\\pic\\";
	
	
	/**
	 * 图片根路径
	 */
	public static final String IMAGE_ROOT_PATH = "http://www.515xueche.com:88/picpath/";
	
	/**
	 * 图片上传路径
	 */
	public static final String FILE_UPLOAD_URL = "/mnt/picpath/";
	
	/**
	 * 客户端访问频率
	 */
	public static final int COUNT = 20;
	/**
	 * AJAX请求成功状态码
	 */
	public static final int AJAX_STATE_TRUE = 0;
	/**
	 * AJAX请求失败状态码
	 */
	public static final int AJAX_STATE_FALSE = -1;
	/**
	 * TOKEN失效状态码
	 */
	public static final int TOKEN_FAILURE = -2;
	/**
	 * SESSION失效状态码
	 */
	public static final int SESSION_FAILURE = -3;
	/**
	 * 任务领完返回错误的状态
	 */
	public static final int TASK_RECEIVED_END = -4;
	/**
	 * 表示该任务领取时候需要收货地址，需要去添加收货地址
	 */
	public static final int TASK_RECEIVED_NEED_ADDRESS = -5;
	/**
	 * 表示该任务过期
	 */
	public static final int TASK_ISNOT_VALIDATE = -6;
	/**
	 * SESSIONID作为COOKIEID的名字
	 */
	public static final String COOKIENAME = "jiaxiaoadmin";
	/**
	 * SESSION失效时间30分钟
	 */
	public static final int SESSIONTIME = 30;
	/**
	 * COOKIEDOMAIN
	 */
	//public static final String DOMAIN = "127.0.0.1";
	/**
	 * 随手赚任务分享间隔，2分钟
	 */
	public static final int SHARE_INTERVAL = 2;
	
}
