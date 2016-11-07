package cn.jiaxiaoAdmin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.jiaxiaoAdmin.model.Area;
import cn.jiaxiaoAdmin.model.AreaDriverCenter;
import cn.jiaxiaoAdmin.model.Driver;
import cn.jiaxiaoAdmin.model.Page;
import cn.jiaxiaoAdmin.model.School;
import cn.jiaxiaoAdmin.model.SchoolAreaCenter;


@Repository
public interface AreaDAO {
	
	public void insertArea(Area area) throws Exception;

	public int selectCountByName(String areaName) throws Exception;

	public void updateAreaLatAndLng(Area area) throws Exception;

	public List<Area> getAreaIsList()throws Exception;

	public Area getAreaById(String areaId)throws Exception;


	
	//根据教练id的集合得到教练对象的集合
	public List<Driver> getDriverByAreaId(List<String> driverIds)throws Exception;

	public List<Area> getAreaIsListNotNull(String lpymark)throws Exception;

	
	public List<Area> getAreaListByName(Map maps)throws Exception;
	
	
	
	
	
	
	
	
	
	
	
	
	//后台,得到带分页的场地list
	public Page<Area> getViewAreaList(Page<Area> page)throws Exception;
	
	//后台,查看场地的总条数
	public int getCountArea()throws Exception;

	//后台,分页查询场地信息
	public List<Area> getViewAreaList(@Param("startNuber") int startNuber, @Param("pageSize") int pageSize,@Param("searchAreaName")String searchAreaName)throws Exception;

	public List<School> getSchoolList()throws Exception;

	//保存驾校信息
	public void addArea(Area area)throws Exception;
	
	//根据驾校id查询驾校信息
	public School selectSchoolById(String schoolId)throws Exception;
	
	//维护中间表
	public void insertSchoolAndArea(SchoolAreaCenter sac)throws Exception;
	
	//根据id删除一个场地
	public void deleteArea(String areaId)throws Exception;


	
	//后台,根据id得到一个场地对象
	public Area selectAreaById(String areaId)throws Exception;

	//根据场地id,查询驾校和场地中间表
	public SchoolAreaCenter selectSchoolAndArea(String areaId)throws Exception;

	//后台,修改场地操作
	public void updateArea(Area oldArea)throws Exception;

	//后台修改场地操作
	public void updateSchoolAndArea(SchoolAreaCenter sac)throws Exception;

	//根据驾校的id查询驾校的名称
	public String selectSchoolIsNameById(String schoolId)throws Exception;
	
	//后台,修改场地不包含图片修改
	public void updateAreaNoImage(Area oldArea)throws Exception;

	
	//根据场地id查询中间表
	public List<AreaDriverCenter> selectAreaDriverCenterByAreaId(String areaId);

	
	//删除驾校和场地的中间表
	public void deleteAreaDriverCenterById(String area_driver_id);
	
}
