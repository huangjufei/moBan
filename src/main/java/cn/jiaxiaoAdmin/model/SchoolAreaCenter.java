package cn.jiaxiaoAdmin.model;

/**
 * 
 * @描述：学校和场地的中间表
 * @作者:黄举飞
 * @部门：伏守科技项目开发部
 * @日期： 2016年4月29日 下午1:21:42
 * @版本： V1.0 
 * @return
 */
public class SchoolAreaCenter {
	
	
	
	private String school_area_id;
	private String schoolId;
	private String areaId;
	private String schoolName;
	
	public String getSchool_area_id() {
		return school_area_id;
	}
	public void setSchool_area_id(String school_area_id) {
		this.school_area_id = school_area_id;
	}
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	
	
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	
	
	public SchoolAreaCenter() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	

}
