package cn.jiaxiaoAdmin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import cn.jiaxiaoAdmin.dao.DriverDAO;
import cn.jiaxiaoAdmin.model.Area;
import cn.jiaxiaoAdmin.model.AreaDriverCenter;
import cn.jiaxiaoAdmin.model.Driver;
import cn.jiaxiaoAdmin.model.Page;
import cn.jiaxiaoAdmin.service.DriverService;
import cn.jiaxiaoAdmin.util.FileTools;
import cn.jiaxiaoAdmin.util.StringTools;


@Transactional
@Service
public class DriverServiceImpl implements DriverService {
	@Resource
	private DriverDAO driverDAO;

	@Override
	public void insertDriver(Driver driver) throws Exception {

		driverDAO.insertDriver(driver);

	}

	@Override
	public List<Driver> getDriverIsList() throws Exception {
		return driverDAO.getDriverIsList();
	}

	@Override
	public Driver getDriverById(String driverId) throws Exception {

		return driverDAO.getDriverById(driverId);
	}

	@Override
	public List<Driver> selectLikeByName(Map<String, Object> resultVO) throws Exception {

		return driverDAO.selectLikeByName(resultVO);
	}

/*	@Override
	public List<Driver> getDriverList(int start, int pageSize) throws Exception {
		return driverDAO.getDriverList(start, pageSize,searchDriverName);
	}*/

	@Override
	public int getTotalNumber() throws Exception {
		return driverDAO.getTotalNumber();
	}

	/**
	 * 
	 * @描述：根据教练的id删除教练的一条
	 * @作者:黄举飞
	 * @部门：伏守科技项目开发部
	 * @日期： 2016年6月26日  下午10:12:30
	 * @版本： V1.0 
	 * @param driverId
	 * @return
	 * @throws Exception
	 */
	@Override
	public Page<Driver> getDriverList(Page<Driver> page,String searchDriverName) throws Exception {

		try {

			int total = driverDAO.getTotalNumber();

			int pageSize = page.getPageSize();

			//计算一些前段需要的数据
			int totalPage = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;//总页数

			//判断下一页不会超出界限
			if (totalPage < page.getPageNum()) {
				page.setPageNum(totalPage);
			}

			page.setTotalPage(totalPage);

			//默认是开始页是第一页,
			int start = (page.getPageNum() - 1) * pageSize;

			//如果起始位置小于0,就等于0,mysql 从0索引开始
			if (start < 0) {
				start = 0;
			}

			List<Driver> drivers = driverDAO.getDriverList(start, pageSize,searchDriverName);

			page.setT(drivers);//放入对象
			page.setTotalNumber(total);//总条数

			return page;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 
	 * @描述：根据教练的id删除教练的一条
	 * @作者:黄举飞
	 * @部门：伏守科技项目开发部
	 * @日期： 2016年6月26日  下午10:12:30
	 * @版本： V1.0 
	 * @param driverId
	 * @return
	 * @throws Exception
	 */
	@Override
	public int deleteDriver(String driverId) throws Exception {

		try {
			driverDAO.deleteDriver(driverId);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -5;
		}

	}

	/**
	 * 
	 * @描述：去教练添加页面前先得到一个场地的集合
	 * @作者:黄举飞
	 * @部门：伏守科技项目开发部
	 * @日期： 2016年6月26日  下午10:12:30
	 * @版本： V1.0 
	 * @param driverId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Area> getAreaList() throws Exception {

		try {
			return driverDAO.getAreaList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @描述：保存教练信息
	 * @作者:黄举飞
	 * @部门：伏守科技项目开发部
	 * @日期： 2016年6月26日  下午10:12:30
	 * @版本： V1.0 
	 * @param driverId
	 * @return
	 * @throws Exception
	 */
	@Transactional(rollbackFor = RuntimeException.class)
	@Override
	public int addDriver(Driver driver, String areaId, MultipartFile[] files) throws Exception {

		try {

			if (StringTools.isEmpty(areaId)) {
				return -2;//场地id为空
			}

			//上传图片
			if (files != null) {

				List<String> list = new ArrayList<>();
				int len = files.length;
				for (int i = 0; i < len; i++) {

					if (files[i].isEmpty()) {
						return -1;
					} else {

						list.add(FileTools.upload(files[i]));
						driver.setEachPersonImage(list.toString().substring(1, list.toString().length() - 1));
					}
				}

			} else {
				return -5;
			}

			//保存教练表
			driver.setDriverId(StringTools.getUUID());//主键
			driverDAO.insertDriver(driver);

			//维护 场地和教练的中间表	
			AreaDriverCenter adc = new AreaDriverCenter();
			adc.setArea_driver_id(StringTools.getUUID());
			adc.setAreaId(areaId);
			adc.setDriverId(driver.getDriverId());
			//保存场地和教练的中间表
			driverDAO.insertAreaAndDriver(adc);

			return 1;

		} catch (Exception e) {
			e.printStackTrace();
			return -5;
		}

	}

	/**
	 * 
	 * @描述：得到教练的总记录数
	 * @作者:黄举飞
	 * @部门：伏守科技项目开发部
	 * @日期： 2016年6月26日  下午10:12:30
	 * @版本： V1.0 
	 * @param driverId
	 * @return
	 * @throws Exception
	 */
	public Integer selectDriverCount(Map<String, Object> map) throws Exception {
		try {
			return driverDAO.selectDriverCount(map);

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 
	 */
	public List<Driver> selectDriver(Map<String, Object> map) throws Exception {
		try {
			return driverDAO.selectDriver(map);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	/**
	 * 
	 * @描述：修改操作前的准备数据操作
	 * @作者:黄举飞
	 * @部门：伏守科技项目开发部
	 * @日期： 2016年6月27日  下午7:12:54
	 * @版本： V1.0 
	 * @param driverId
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> selectDriverById(String driverId) throws Exception {

		HashMap<String, Object> map = new HashMap<>();

		try {
			//得到教练对象
			Driver driver = driverDAO.selectDriverById(driverId);
			//得到场地表id
			String areaId = driverDAO.selectAreaDriverCenter(driverId);
			//得到场地全部list,只要id和名字
			List<Area> areas = driverDAO.selectAreaList();

			map.put("driver", driver);
			map.put("areaId", areaId);
			map.put("areas", areas);

			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return map;
		}
	}

	/**
	 * 
	 * @描述：保存教练的修改后的数据并且维护中间表
	 * @作者:黄举飞
	 * @部门：伏守科技项目开发部
	 * @日期： 2016年6月27日  下午7:12:54
	 * @版本： V1.0 
	 * @param driverId
	 * @return
	 * @throws Exception
	 */
	@Transactional(rollbackFor = RuntimeException.class)
	@Override
	public int updateDriver(Driver newDriver, MultipartFile[] files, String areaId, boolean tag) throws Exception {

		try {

			if (!StringTools.isEmpty(areaId)) {
				//根据id查到对应的场地
				int count = driverDAO.selectAreaCount(areaId);
				if (count < 1) {
					return -1;
				}
			} else {
				return -1;
			}

			//根据教练id得到原有教练的对象
			Driver oldDriver = driverDAO.selectDriverById(newDriver.getDriverId());

			if (oldDriver == null) {
				return -2;
			}

			oldDriver.setBeginAge(newDriver.getBeginAge());//教龄
			oldDriver.setDriverAge(newDriver.getDriverAge());//年龄
			oldDriver.setDriverGrade(newDriver.getDriverGrade());//星级
			oldDriver.setDriverInfo(newDriver.getDriverInfo());//简介
			oldDriver.setDriverName(newDriver.getDriverName());//名字
			oldDriver.setDriverSex(newDriver.getDriverSex());//性别
			oldDriver.setPassRate(newDriver.getPassRate());//通过率
			oldDriver.setSubject(newDriver.getSubject());//能教授的科目

			boolean imageTag = false;

			if (!tag) {
				//上传图片
				if (files != null) {

					List<String> list = new ArrayList<>();
					int len = files.length;
					for (int i = 0; i < len; i++) {

						if (files[i].isEmpty()) {
							return -1;
						} else {

							list.add(FileTools.upload(files[i]));
							newDriver.setEachPersonImage(list.toString().substring(1, list.toString().length() - 1));
						}
					}

					oldDriver.setEachPersonImage(newDriver.getEachPersonImage());//教练的个人风采图片
					imageTag = true;

				} else {
					return -5;
				}
			}

			//修改教练表

			if (imageTag) {
				driverDAO.updateDriver(oldDriver);//有图片
			} else {
				driverDAO.updateDriverNoImage(oldDriver);//没图片
			}

			//--------------------------------------------------------------------------
			//下面是修改中间表	
			//根据教练id,查出场地和教练的中间表对象
			AreaDriverCenter adc = driverDAO.selectAreaDriverCenterByDriverId(newDriver.getDriverId());
			adc.setAreaId(areaId);
			driverDAO.updateAreaAndDriver(adc);

			return 1;

		} catch (Exception e) {
			e.printStackTrace();
			return -5;
		}

	}

}
