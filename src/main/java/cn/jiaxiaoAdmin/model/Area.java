package cn.jiaxiaoAdmin.model;

import java.io.Serializable;

/**
 * 
 * @描述：场地类
 * @作者:黄举飞
 * @部门：伏守科技项目开发部 @日期： 2016年4月29日 下午12:40:59 @版本： V1.0
 * @return
 */
public class Area implements Serializable {

	/**
	 * CREATE TABLE tb_area (
		  areaId                         varchar(50) NOT NULL, -- id
		  areaName                       varchar(50) NOT NULL,  -- 场地名称(索引areaName_index)
		  areaAddress                    varchar(50) NOT NULL, -- 场地位置
		  areaLat                        varchar(255) NOT NULL, -- 场地纬度(索引areaLat_index)
		  areaLng                        varchar(255) NOT NULL, -- 场地经度(索引areaLng_index)
		  areaInfo                       text, -- 场地简介  
		  areaImage                      varchar(255) NOT NULL, -- 照片  
		  insertTimeStamp                varchar(255) NOT NULL,-- 插入时间戳
		  PRIMARY KEY (areaId)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8;

	 * 
	 */

	private String areaId;
	private String areaName;
	private String areaAddress;
	private String areaLat;
	private String areaLng;
	private String areaInfo;
	private String areaImage;
	private String insertTimeStamp;
	private String classmark;
	
	
	

	public String getClassmark() {
		return classmark;
	}

	public void setClassmark(String classmark) {
		this.classmark = classmark;
	}
	private String areaNumber;
	

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaAddress() {
		return areaAddress;
	}

	public void setAreaAddress(String areaAddress) {
		this.areaAddress = areaAddress;
	}

	public String getAreaLat() {
		return areaLat;
	}

	public void setAreaLat(String areaLat) {
		this.areaLat = areaLat;
	}

	public String getAreaLng() {
		return areaLng;
	}

	public void setAreaLng(String areaLng) {
		this.areaLng = areaLng;
	}

	public String getAreaInfo() {
		return areaInfo;
	}

	public void setAreaInfo(String areaInfo) {
		this.areaInfo = areaInfo;
	}

	public String getAreaImage() {
		return areaImage;
	}

	public void setAreaImage(String areaImage) {
		this.areaImage = areaImage;
	}

	public String getInsertTimeStamp() {
		return insertTimeStamp;
	}

	public void setInsertTimeStamp(String insertTimeStamp) {
		this.insertTimeStamp = insertTimeStamp;
	}
	
	
	

	public String getAreaNumber() {
		return areaNumber;
	}

	public void setAreaNumber(String areaNumber) {
		this.areaNumber = areaNumber;
	}

	public Area() {
		super();
		// TODO Auto-generated constructor stub
	}

}
