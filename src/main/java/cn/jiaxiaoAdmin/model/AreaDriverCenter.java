package cn.jiaxiaoAdmin.model;


/**
 * 
 * @描述：场地和教练的中间表
 * @作者:黄举飞
 * @部门：伏守科技项目开发部
 * @日期： 2016年6月16日 下午4:16:07
 * @版本： V1.0 
 * @return
 */
public class AreaDriverCenter {
	

	 
	private String area_driver_id;
	private String areaId;
	private String driverId;
	
	public String getArea_driver_id() {
		return area_driver_id;
	}
	public void setArea_driver_id(String area_driver_id) {
		this.area_driver_id = area_driver_id;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	
	


}
