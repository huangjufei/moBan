package cn.jiaxiaoAdmin.model;

import java.util.ArrayList;

/**
 * 
 * @描述：AJAX请求通用返回类(list类)
 * @作者： 丁洪星
 * @文件名：ResultTVO.java 
 * @包名：cn.zhuanquanquan.vo 
 * @项目名：zhuanquanquan
 * @部门：伏守科技项目开发部
 * @日期： 2015年11月4日 下午3:53:32 
 * @版本： V1.0
 */
public class ResultTVO<T> {

	/**
	 * 状态码，0：表示请求成功，-1表示请求失败
	 */
	private int state;
	/**
	 * 错误信息
	 */
	private String errMsg;
	/**
	 * 返回数据
	 */
	private ArrayList<T> data;
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public ArrayList<T> getData() {
		return data;
	}
	public void setData(ArrayList<T> data) {
		this.data = data;
	}

	
}
