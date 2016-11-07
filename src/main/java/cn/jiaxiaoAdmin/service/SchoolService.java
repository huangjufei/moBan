package cn.jiaxiaoAdmin.service;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import cn.jiaxiaoAdmin.model.Page;
import cn.jiaxiaoAdmin.model.School;

@Repository
public interface SchoolService {
	
	//录入驾校
	public void insertSchool(School school) throws Exception;
	
	//根据名字查看 数据库中是否存在这学校
	//public int selectCountByName(String schoolName) throws Exception;

	//根据学校名字,修改学校的经纬度.
	public void updateSchoolLatAndLng(School school) throws Exception;

	//录入驾校数据,有事务的保存
	public int addSchool(School school, MultipartFile[] files)throws Exception;

	//后台,得到学校的信息的一个List
	public List<School> getSchoolList()throws Exception;

	//得到学校的List,部分信息
	public List<School> querySchoolList() throws Exception;

	// 根据id得到一个驾校的详细信息
	public School getSchooById(String schoolId)throws Exception;
	
	//得到一个学校list,带分页功能
	public Page<School> getViewSchoolList(Page<School> page,String searchSchoolName)throws Exception;

	//后台,根据id删除一条学校的数据
	public int deleteSchool(String schoolId)throws Exception;

	//后台,根据学校id查询学校信息
	public School selectSchoolById(String schoolId)throws Exception;
	
	//后台,修改学校信息
	public int updateSchool(School newSchool, MultipartFile[] files,boolean tag);



	
}
