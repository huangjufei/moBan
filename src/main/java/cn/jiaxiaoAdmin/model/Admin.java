package cn.jiaxiaoAdmin.model;

import java.io.Serializable;


/**
 * 
 * @描述：后台管理员用户实体
 * @作者： 丁洪星
 * @文件名：Admin.java 
 * @包名：cn.zhuanquanquan.model 
 * @项目名：zhuanquanquan
 * @部门：伏守科技项目开发部
 * @日期： 2015年11月4日 下午4:04:26 
 * @版本： V1.0
 */
public class Admin implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 6766511558888113914L;
	/**
	 * 数据库主键
	 */
	private String strAdminId;
	/**
	 * 用户名
	 */
	private String strUserName;
	/**
	 * 密码
	 */
	private String strUserPassword;

	
	
	
	
	
	
	
	
	
	public String getStrAdminId() {
		return strAdminId;
	}
	public void setStrAdminId(String strAdminId) {
		this.strAdminId = strAdminId;
	}
	public String getStrUserName() {
		return strUserName;
	}
	public void setStrUserName(String strUserName) {
		this.strUserName = strUserName;
	}
	public String getStrUserPassword() {
		return strUserPassword;
	}
	public void setStrUserPassword(String strUserPassword) {
		this.strUserPassword = strUserPassword;
	}

   
}
