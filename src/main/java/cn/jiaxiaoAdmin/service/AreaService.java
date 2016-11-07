package cn.jiaxiaoAdmin.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import cn.jiaxiaoAdmin.model.Area;
import cn.jiaxiaoAdmin.model.Page;
import cn.jiaxiaoAdmin.model.School;
import cn.jiaxiaoAdmin.model.SchoolAreaCenter;


@Repository
public interface AreaService {
	
	//添加场地(没有事务,可能要删除)
	public void insertArea(Area area) throws Exception;
	///根据场地名字统计有几条数据
	public int selectCountByName(String areaName) throws Exception;
	//根据场地名字修改 经纬度
	public void updateAreaLatAndLng(Area area) throws Exception;
	//无条件得到一个集合的场地的List
	public List<Area> getAreaIsList() throws Exception;	
	//根据一个场地的Id 得到部门场地信息
	public Area getAreaById(String areaId) throws Exception;	
	//有事务的保存场地信息
	public void addArea(Area area) throws Exception;

	//根据传进来的mark 值,查询后返回筛选后的场地list
	public List<Area> getAreaIsListNotNull(String mark)throws Exception;
	
	//根据场地名字模糊查询场地的list
	public List<Area> getAreaListByName(Map map)throws Exception;
	
	
	
	
	
	
	
	
	//后台,带分页的一个场地的List
	public Page<Area> getViewAreaList(Page<Area> page, String searchAreaName)throws Exception;
	//后台,得到一个驾校的list
	public List<School> getSchoolList()throws Exception;
	//后台,保存学校信息
	public int addArea(Area area, String schoolId, MultipartFile[] files)throws Exception;
	//后台,根据id删除一个场地的id
	public int deleteArea(String areaId)throws Exception;
	
	//后台,根据场地id得到场地对象
	public Map<String, Object> selectAreaById(String areaId)throws Exception;
	
	//后台,修改场地操作
	
	public int updateArea(Area newArea, MultipartFile[] files, SchoolAreaCenter sac,boolean tag);	

}
