package cn.jiaxiaoAdmin.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import cn.jiaxiaoAdmin.model.Area;
import cn.jiaxiaoAdmin.model.Driver;
import cn.jiaxiaoAdmin.model.Page;


@Repository
public interface DriverService {
	
	//向数据库保存一条教练的信息
	public void insertDriver(Driver driver) throws Exception;
	//无条件,查询一个教练的List集合
	public List<Driver> getDriverIsList() throws Exception;
	//根据教练id,得到一个教练的对象
	public Driver getDriverById(String driverId) throws Exception;
	//根据名字模糊查询教练
	public List<Driver> selectLikeByName(Map<String, Object> resultVO) throws Exception;
	
/*	//分页显示教练的list集合
	public List<Driver> getDriverList(int start, int pageSize) throws Exception;*/
	//得到教练的总条数
	public int getTotalNumber() throws Exception;
	
	//带分页的教练的List
	public Page<Driver> getDriverList(Page<Driver> page,String searchDriverName)throws Exception ;
	
	//后台,删除一条教练信息
	public int deleteDriver(String driverId)throws Exception;
	
	//后台,得到一个场地的集合
	public List<Area> getAreaList()throws Exception;
	
	//后台,保存教练操作
	public int addDriver(Driver driver, String areaId, MultipartFile[] files) throws Exception;	


    //查询根据总条数
	public Integer selectDriverCount(Map<String, Object> map) throws Exception;
	//根据不同条件查询订单
	public List<Driver> selectDriver(Map<String, Object> map) throws Exception;
	
	//通过教练id 去得到教练对象和场地id和名字
	public Map<String, Object> selectDriverById(String driverId)throws Exception;
	
	//后台,修改后保存教练
	public int updateDriver(Driver newDriver, MultipartFile[] files, String areaId, boolean tag)throws Exception;

}
