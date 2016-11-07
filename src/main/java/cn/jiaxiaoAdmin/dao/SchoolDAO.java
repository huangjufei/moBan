package cn.jiaxiaoAdmin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.jiaxiaoAdmin.model.School;
import cn.jiaxiaoAdmin.model.SchoolAreaCenter;

@Repository
public interface SchoolDAO {
	
	public void insertSchool(School school) throws Exception;

	public int selectCountByName(String schoolName) throws Exception;

	public void updateSchoolLatAndLng(School school) throws Exception;

	public void addSchool(School school) throws Exception;

	public List<School> getSchoolList() throws Exception;

	public List<School> querySchoolList() throws Exception;

	public School getSchooById(String schoolId)throws Exception;

	
	

	//后台,得到学校的总条数
	public int getCountSchool()throws Exception;

	//后台,根据分页得到
	public List<School> getViewSchoolList(@Param("startNuber") int startNuber,@Param("pageSize") int pageSize,@Param("searchSchoolName") String searchSchoolName);

	public void deleteSchool(String schoolId)throws Exception;

	public School selectSchoolById(String schoolId)throws Exception;
	
	//后台,修改学校的信息
	public int updateSchool(School oldSchool)throws Exception;
	
	//后台,根据驾校id查询中间表,得到一个集合
	public List<SchoolAreaCenter> selectSchoolAndAreaBySchoolId(String schoolId)throws Exception;
	//后台,删除场地信息
	public void deleteArea(String areaId);
	//后台,根据中间表id删除中间表关于驾校的信息
	public void deleteSchoolAndAreaById(String school_area_id)throws Exception;

	//根据驾校id 修改驾校的信息,只包含展示图片
	public void updateSchoolImage(School school)throws Exception;
	
	//不修改所有的上传文件
	public void updateSchoolNoFiles(School oldSchool)throws Exception;

	
	
}
