package cn.jiaxiaoAdmin.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @描述：驾校
 * @作者:黄举飞
 * @部门：伏守科技项目开发部 @日期： 2016年4月26日 上午10:29:15 @版本： V1.0
 * @return
 */
public class School implements Serializable {

	/**
		CREATE TABLE tb_school (
		  schoolId                       varchar(50) NOT NULL, -- id
		  schoolLogo                     varchar(255) NOT NULL,-- 商家商标图片地址
		  schoolName                     varchar(50) NOT NULL, -- 驾校名称(索引schoolName_index)
		  contactPerson                  varchar(100) NOT NULL, -- 联系人
		  contactPhone                   varchar(100) NOT NULL, -- 联系电话
		  schoolInfo                     text, -- 驾校简介
		  schoolAddress                  varchar(255) NOT NULL, -- 地址
		  schoolImage                    varchar(255) NOT NULL, -- 驾校图片
		  schoolVideo                    varchar(255) NOT NULL, -- 驾校视频
		  schoolGrade                    int NOT NULL, -- 驾校等级
		  schoolLat                      varchar(255) NOT NULL, -- 驾校纬度(索引schoolLat_index)
		  schoolLng                      varchar(255) NOT NULL, -- 驾校经度 (索引schoolLng_index)
		  driverInfo                     varchar(255) NOT NULL, -- 驾校教练信息
		  insertTimeStamp                timestamp(255) NOT NULL, -- 插入时间戳
		  PRIMARY KEY (schoolId)
		) ENGINE=INNODB DEFAULT CHARSET=utf8;
	 */

	private String schoolId;
	private String schoolLogo;
	private String schoolName;
	private String contactPerson;
	private String contactPhone;
	private String schoolInfo;
	private String schoolAddress;
	private String schoolImage;
	private String schoolVideo;
	private int schoolGrade;
	private String schoolLat;
	private String schoolLng;
	private String driverInfo;
	private String insertTimeStamp;


	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolLogo() {
		return schoolLogo;
	}

	public void setSchoolLogo(String schoolLogo) {
		this.schoolLogo = schoolLogo;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getSchoolInfo() {
		return schoolInfo;
	}

	public void setSchoolInfo(String schoolInfo) {
		this.schoolInfo = schoolInfo;
	}

	public String getSchoolAddress() {
		return schoolAddress;
	}

	public void setSchoolAddress(String schoolAddress) {
		this.schoolAddress = schoolAddress;
	}

	public String getSchoolImage() {
		return schoolImage;
	}

	public void setSchoolImage(String schoolImage) {
		this.schoolImage = schoolImage;
	}

	public String getSchoolVideo() {
		return schoolVideo;
	}

	public void setSchoolVideo(String schoolVideo) {
		this.schoolVideo = schoolVideo;
	}

	public int getSchoolGrade() {
		return schoolGrade;
	}

	public void setSchoolGrade(int schoolGrade) {
		this.schoolGrade = schoolGrade;
	}

	public String getSchoolLat() {
		return schoolLat;
	}

	public void setSchoolLat(String schoolLat) {
		this.schoolLat = schoolLat;
	}

	public String getSchoolLng() {
		return schoolLng;
	}

	public void setSchoolLng(String schoolLng) {
		this.schoolLng = schoolLng;
	}

	public String getDriverInfo() {
		return driverInfo;
	}

	public void setDriverInfo(String driverInfo) {
		this.driverInfo = driverInfo;
	}

	public String getInsertTimeStamp() {
		return insertTimeStamp;
	}

	public void setInsertTimeStamp(String insertTimeStamp) {
		this.insertTimeStamp = insertTimeStamp;
	}

	public School() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "School [schoolId=" + schoolId + ", schoolLogo=" + schoolLogo + ", schoolName=" + schoolName
				+ ", contactPerson=" + contactPerson + ", contactPhone=" + contactPhone + ", schoolInfo=" + schoolInfo
				+ ", schoolAddress=" + schoolAddress + ", schoolImage=" + schoolImage + ", schoolVideo=" + schoolVideo
				+ ", schoolGrade=" + schoolGrade + ", schoolLat=" + schoolLat + ", schoolLng=" + schoolLng
				+ ", driverInfo=" + driverInfo + ", insertTimeStamp=" + insertTimeStamp + "]";
	}


}
