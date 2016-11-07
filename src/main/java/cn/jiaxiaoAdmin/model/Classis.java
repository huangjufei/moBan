package cn.jiaxiaoAdmin.model;

import java.io.Serializable;

/**
 * 
 * @描述：选择具体班级类
 * @作者:黄举飞
 * @部门：伏守科技项目开发部
 * @日期： 2016年4月26日 上午10:50:45
 * @版本： V1.0 
 * @return
 */
public class Classis implements Serializable {

	/**
		CREATE TABLE tb_class(
		 classId                    varchar(50) NOT NULL, -- 数据库主键
		 classType                  varchar(100) NOT NULL, -- c1或者c2等 (索引classType_index)      
		 className                  varchar(100) NOT NULL, -- 班级名称 学生班,普通班,白领班(索引className_index)
		 logoImg                    varchar(100) NOT NULL, -- logo图片路径
		 pxfwf                      decimal(10,2) default 0.00, -- 配需服务费
		 djf                        decimal(10,2) default 0.00, -- 代缴费
		 dateScope                  varchar(100) NOT NULL, -- 可以预约的时间范围(dateScope_index)
		 service                    varchar(255) NOT NULL, -- 特色服务,使用,号分割
		 loginIf					varchar(255) NOT NULL, -- 注册条件,使用,号分割
		 desc						varchar(15), -- 班级描述
		 PRIMARY KEY (classId)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	 */
	
	private String classId;
	private String classType;
	private String className;
	private String logoImg;
	private double pxfwf;
	private double djf;
	private String dateScope;
	private String service;
	private String loginIf;
	private String classDesc;
	private String mark;
	
	

	
	public String getMark() {
		return mark;
	}



	public void setMark(String mark) {
		this.mark = mark;
	}



	public String getClassId() {
		return classId;
	}



	public void setClassId(String classId) {
		this.classId = classId;
	}



	public String getClassType() {
		return classType;
	}



	public void setClassType(String classType) {
		this.classType = classType;
	}



	public String getClassName() {
		return className;
	}



	public void setClassName(String className) {
		this.className = className;
	}



	public String getLogoImg() {
		return logoImg;
	}



	public void setLogoImg(String logoImg) {
		this.logoImg = logoImg;
	}



	public double getPxfwf() {
		return pxfwf;
	}



	public void setPxfwf(double pxfwf) {
		this.pxfwf = pxfwf;
	}



	public double getDjf() {
		return djf;
	}



	public void setDjf(double djf) {
		this.djf = djf;
	}



	public String getDateScope() {
		return dateScope;
	}



	public void setDateScope(String dateScope) {
		this.dateScope = dateScope;
	}



	public String getService() {
		return service;
	}



	public void setService(String service) {
		this.service = service;
	}



	public String getLoginIf() {
		return loginIf;
	}



	public void setLoginIf(String loginIf) {
		this.loginIf = loginIf;
	}



	public Classis() {
		super();
		// TODO Auto-generated constructor stub
	}



	public String getClassDesc() {
		return classDesc;
	}



	public void setClassDesc(String classDesc) {
		this.classDesc = classDesc;
	}





	


	
	


}
