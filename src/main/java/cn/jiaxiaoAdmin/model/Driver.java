package cn.jiaxiaoAdmin.model;

import java.io.Serializable;

/**
 * 
 * @描述：教练类
 * @作者:黄举飞
 * @部门：伏守科技项目开发部
 * @日期： 2016年4月29日 下午12:41:19
 * @版本： V1.0 
 * @return
 */
public class Driver implements Serializable{

	/**
		CREATE TABLE tb_driver (
		  driverId                       varchar(50) NOT NULL, -- id
		  driverName                     varchar(50) NOT NULL, -- 姓名(索引driverName_index)
		  driverAge                      int default 0, -- 年龄
		  beginAge                       varchar(100) NOT NULL, -- 入教时间
		  driverSex                      int NOT NULL, -- 性别
		  subject                        varchar(50) NOT NULL, -- 假如 选择c1 的科目2和科目3,页面传进来c12,c13值
		  driverGrade                    int default 3,  -- 教练等级(一共有5颗星)默认3星.
		  idImage                        varchar(255), -- 身份证正面图片 
		  backIdmage                     varchar(255), -- 身份证背面图片 
		  driverImage                    varchar(255), -- 教练证图片
		  permitImage                    varchar(255), -- 行驶证图片
		  eachPersonImage                varchar(255), -- 个人风采图片
		  passRate                       int default 80, -- 默认80% -- 学员通过率
		  driverInfo					 varchar(255), --简介
		  PRIMARY KEY (driverId)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8;


	 */

	private String driverId;
	private String driverName;
	private int driverAge;
	private String beginAge;
	private int driverSex;
	private String subject;
	private double driverGrade;
	private String idImage;
	private String backIdmage;
	private String driverImage;
	private String permitImage;
	private String eachPersonImage;
	private int passRate;
	private String driverInfo;
	
	
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public int getDriverAge() {
		return driverAge;
	}
	public void setDriverAge(int driverAge) {
		this.driverAge = driverAge;
	}
	public String getBeginAge() {
		return beginAge;
	}
	public void setBeginAge(String beginAge) {
		this.beginAge = beginAge;
	}
	public int getDriverSex() {
		return driverSex;
	}
	public void setDriverSex(int driverSex) {
		this.driverSex = driverSex;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}

	
	public double getDriverGrade() {
		return driverGrade;
	}
	public void setDriverGrade(double driverGrade) {
		this.driverGrade = driverGrade;
	}
	
	public String getIdImage() {
		return idImage;
	}
	public void setIdImage(String idImage) {
		this.idImage = idImage;
	}
	public String getBackIdmage() {
		return backIdmage;
	}
	public void setBackIdmage(String backIdmage) {
		this.backIdmage = backIdmage;
	}
	public String getDriverImage() {
		return driverImage;
	}
	public void setDriverImage(String driverImage) {
		this.driverImage = driverImage;
	}
	public String getPermitImage() {
		return permitImage;
	}
	public void setPermitImage(String permitImage) {
		this.permitImage = permitImage;
	}
	public String getEachPersonImage() {
		return eachPersonImage;
	}
	public void setEachPersonImage(String eachPersonImage) {
		this.eachPersonImage = eachPersonImage;
	}
	public int getPassRate() {
		return passRate;
	}
	public void setPassRate(int passRate) {
		this.passRate = passRate;
	}
	public String getDriverInfo() {
		return driverInfo;
	}
	public void setDriverInfo(String driverInfo) {
		this.driverInfo = driverInfo;
	}



	

}
