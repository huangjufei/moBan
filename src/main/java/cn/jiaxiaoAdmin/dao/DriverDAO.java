package cn.jiaxiaoAdmin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.jiaxiaoAdmin.model.Area;
import cn.jiaxiaoAdmin.model.AreaDriverCenter;
import cn.jiaxiaoAdmin.model.Driver;


@Repository
public interface DriverDAO  {
	
	public void insertDriver(Driver driver) throws Exception;

	public List<Driver> getDriverIsList() throws Exception;

	public Driver getDriverById(String driverId) throws Exception;

	public List<Driver> selectLikeByName(Map<String, Object> resultVO)throws Exception;


	public void updateGrade(@Param("driverId")String driverId, @Param("currentGrade") double currentGrade)throws Exception;


	//得到教练的总条数
	public int getTotalNumber()throws Exception;
	
	//分页得到教练的list
	public List<Driver> getDriverList(@Param("start") int start, @Param("pageSize")int pageSize,@Param("searchDriverName") String searchDriverName)throws Exception;

	//根据教练的id删除一条教练的记录
	public void deleteDriver(String driverId)throws Exception;

	public List<Area> getAreaList()throws Exception;
	

	
	//查询根据总条数
	public int selectDriverCount(Map<String, Object> map) throws Exception;
	//根据不同条件查询订单
	public List<Driver> selectDriver(Map<String, Object> map) throws Exception;
	//保存场地和教练之间的中间表
	public void insertAreaAndDriver(AreaDriverCenter adc)throws Exception;
	//后台,根据教练id得到一个教练对象
	public Driver selectDriverById(String driverId)throws Exception;
	//后台,根据教练id得到场地id
	public String selectAreaDriverCenter(String driverId)throws Exception;
	
	//得到场地的一个list,只包含id和名字
	public List<Area> selectAreaList()throws Exception;

	//后台,根据场地id查找场地对象
	public int selectAreaCount(String areaId)throws Exception;

	//后台,修改教练操作,
	public void updateDriver(Driver oldDriver)throws Exception;
	
	//后台,修改教练操作,不含个人风采
	public void updateDriverNoImage(Driver oldDriver)throws Exception;
	
	//根据教练id,查出场地和教练的中间表对象
	public AreaDriverCenter selectAreaDriverCenterByDriverId(String driverId)throws Exception;
	
	//保存场地和教练的中间表
	public void updateAreaAndDriver(AreaDriverCenter adc)throws Exception;
	
	
	
	
	
	
	
	
	
	
	
	
}
