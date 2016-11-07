package cn.jiaxiaoAdmin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.jiaxiaoAdmin.model.Area;
import cn.jiaxiaoAdmin.model.Driver;
import cn.jiaxiaoAdmin.model.Page;
import cn.jiaxiaoAdmin.service.DriverService;
import cn.jiaxiaoAdmin.util.FileTools;
import cn.jiaxiaoAdmin.util.Image;
import cn.jiaxiaoAdmin.util.StringTools;

@RequestMapping("/driver")
@Controller
public class AdminDriverController {

	@Resource
	private DriverService driverService;



	/**
	 * 
	 * @描述：得到教练的集合
	 * @作者:黄举飞
	 * @部门：伏守科技项目开发部
	 * @日期： 2016年6月26日  下午8:28:00
	 * @版本： V1.0 
	 * @return
	 */

	@RequestMapping("getViewDriverList")
	public ModelAndView getDriverList(Page<Driver> page, HttpServletRequest request, HttpServletResponse response,String searchDriverName) {
		ModelAndView mav = new ModelAndView("admin/viewDriverList");

		boolean flag = true;
		if (flag) {
			try {

				Page<Driver> pages = driverService.getDriverList(page,searchDriverName);
				mav.addObject("pages", pages);
				return mav;

			} catch (Exception e) {
				e.printStackTrace();
				return mav;
			}
		} else {
			mav.setViewName("redirect:/admin/login.do");
		}
		return mav;

	}

	/**
	 * 
	 * @描述：删除一个教练信息
	 * @作者:黄举飞
	 * @部门：伏守科技项目开发部
	 * @日期： 2016年6月24日  下午2:25:15
	 * @版本： V1.0 
	 * @param AreaId
	 * @return
	 */
	@RequestMapping("deleteDriver")
	public ModelAndView deleteDriver(String driverId, HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("forward:/driver/getViewDriverList.do");

		boolean flag = true;
		if (flag) {
			if (StringTools.isEmpty(driverId)) {
				mav.addObject("error", "删除的场地的id不能为空");
			}

			try {

				int i = driverService.deleteDriver(driverId);
				if (i == 1) {
					mav.addObject("ok", "删除成功!");
				} else {
					mav.addObject("error", "删除失败");
				}
				return mav;

			} catch (Exception e) {
				e.printStackTrace();
				//去错误页面
				return mav;
			}

		} else {
			mav.setViewName("redirect:/admin/login.do");
		}
		return mav;

	}

	List<Area> areas;

	/**
	 * 
	 * @描述：转发去添加教练页面
	 * @作者:黄举飞
	 * @部门：伏守科技项目开发部
	 * @日期： 2016年6月21日  下午8:47:44
	 * @版本： V1.0 
	 * @return
	 */
	@RequestMapping("addDriverPage")
	public ModelAndView addDriverPage(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("admin/addDriver");

		boolean flag = true;
		
		if (flag) {
			try {
				areas = driverService.getAreaList();

				if (areas == null || areas.size() == 0) {
					mav.addObject("error", "教练必须关联一个场地,请先创建场地信息!");
					return mav;
				}

				//mav.addObject("driver", new Driver());

				mav.addObject("areas", areas);

				return mav;
			} catch (Exception e) {
				e.printStackTrace();
				return mav;
			}
		} else {
			mav.setViewName("redirect:/admin/login.do");
		}
		return mav;

	}

	/**
	 * 
	 * @描述：后台保存教练
	 * @作者:黄举飞
	 * @部门：伏守科技项目开发部
	 * @日期： 2016年6月14日  下午5:46:59
	 * @版本： V1.0 
	 * @param derver :教练对象
	 * @param areaId :场地id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("addDriver")
	public ModelAndView addDriver(Driver driver, String areaId, @RequestParam("files") MultipartFile[] files,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView("admin/addDriver");

		boolean flag = true;
		if (flag) {
			mav.addObject("areas", areas);
			mav.addObject("areaIds", areaId);

			if (StringTools.isEmpty(areaId) || "0".equals(areaId)) {
				return mav.addObject("error", "请选择教练属于那个场地");
			} else {
				if (areaId.length() > 50) {
					return mav.addObject("error", "场地id长度不能大于50");
				}
			}

			if (StringTools.isEmpty(driver.getDriverName())) {
				return mav.addObject("error", "教练名字不能为空");
			} else {
				if (driver.getDriverName().length() > 20) {
					return mav.addObject("error", "教练名字不能长于20");
				}
			}

			//判断年龄
			if (0 == driver.getDriverAge()) {
				return mav.addObject("error", "教练的年龄不能为空");
			} else {

				if (StringTools.validateAge(driver.getDriverAge() + "")) {
					if (driver.getDriverAge() <= 18) {
						return mav.addObject("error", "年龄需要大于18岁!");
					}

				} else {
					return mav.addObject("error", "教练的年龄值大于18小于100");
				}

			}

			//判断驾龄
			if (StringTools.isEmpty(driver.getBeginAge())) {
				return mav.addObject("error", "教练的驾龄不能为空");
			} else {

				if (!StringTools.validateAge(driver.getBeginAge())) {
					return mav.addObject("error", "教练的驾龄,请输入合法的数字");
				}
			}

			//判断性别
			if (StringTools.isEmpty(driver.getDriverSex() + "")) {
				return mav.addObject("error", "性别不能为空");
			} else {

				if (driver.getDriverSex() != 1 && driver.getDriverSex() != 0) {
					return mav.addObject("error", "性别只能输入1或0(1表示男)");
				}
			}

			if ("0".equals(driver.getSubject())) {
				return mav.addObject("error", "请选择,教练可以教授的科目");
			}

			if (0.0 == driver.getDriverGrade()) {
				return mav.addObject("error", "请选择教练的等级");
			}

			//判断学员通过率
			if (StringTools.isEmpty(driver.getPassRate() + "")) {
				return mav.addObject("error", "学员通过率不能为空");
			} else {

				if (!StringTools.validateAge(driver.getPassRate() + "")) {
					return mav.addObject("error", "通过率请输入两位数的数值");
				}
			}

			//简介
			if (StringTools.isEmpty(driver.getDriverInfo())) {
				return mav.addObject("error", "教练的简介不能为空");
			} else {
				if (driver.getDriverInfo().length() > 255) {
					return mav.addObject("error", "教练简介不能大于255字");
				}
			}

			if (files != null) {

				int len = files.length;

				//检查多选图片上传的数量
				if (len > FileTools.IMAGENUMBER) {
					return mav.addObject("error", "多选图片的上传数量不能大于" + FileTools.IMAGENUMBER + "张");
				}

				for (int i = 0; i < len; i++) {

					if (files[i].isEmpty()) {
						return mav.addObject("error", "教练的展示图片不能为空");
					} else {

						if (!FileTools.validateSuffix(files[i])) {
							return mav.addObject("error", "教练的展示图片格式只能是JPG或PNG");
						}

						Image image;
						try {
							image = FileTools.readImageInfo(files[i]);
						} catch (Exception e) {
							mav.addObject("error", "展示图片种可能有图片损坏,请换图片.");
							return mav;

						}

						if (!FileTools.validateSize(image)) {
							return mav.addObject("error", "教练的展示图片片的大小不能超过" + FileTools.IMAGESIZE / 1000 + "KB");
						}

					}

				}

			} else {
				return mav.addObject("error", "files为空");
			}

			try {
				//保存教练
				int ii = driverService.addDriver(driver, areaId, files);

				if (ii == -2) {
					return mav.addObject("error", "场地id不能为空");
				}
				if (ii == -1) {
					return mav.addObject("error", "上传的图片为空!");
				}
				if (ii == 1) {
					//成功返回
					return mav.addObject("ok", "恭喜你,添加成功!");
				} else {
					return mav.addObject("error", "保存教练未知错误");
				}

			} catch (Exception e) {
				e.printStackTrace();
				return mav.addObject("error", "保存教练系统错误");
			}

		} else {
			mav.setViewName("redirect:/admin/login.do");
		}

		return mav;
	}

	/**
	 * 
	 * @描述：根据教练id,查询教练对象,然后去修改页面
	 * @作者:黄举飞
	 * @部门：伏守科技项目开发部
	 * @日期： 2016年6月23日  上午9:54:37
	 * @版本： V1.0 
	 * @param schoolId
	 * @return
	 */
	@RequestMapping("updateDriverPage")
	public ModelAndView updateDriverPage(String driverId, HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("admin/updateDriver");
		boolean flag = true;

		if (flag) {
			if (StringTools.isEmpty(driverId)) {
				mav.addObject("error", "场地的的id不能为空");
				mav.setViewName("forward:/driver/getViewDriverList.do");
				return mav;
			}

			try {
				Map<String, Object> map = new HashMap<>();

				//通过教练id 去得到教练对象和场地id和名字
				map = driverService.selectDriverById(driverId);

				//获取教练
				Driver driver = (Driver) map.get("driver");
				if (driver == null) {
					return mav.addObject("error", "数据库种没有查到该教练");
				}

				//获取场地id
				String areaIds = (String) map.get("areaId");
				if (StringTools.isEmpty(areaIds)) {
					mav.addObject("error", "当前教练没有关联任何场地,可能原来的场地不存在了");
				}

				areas = (List<Area>) map.get("areas");
				if (areas == null || areas.size() == 0) {
					return mav.addObject("error", "教练需要关联在场地下,请先创建场地信息");
				}

				mav.addObject("driver", driver);
				mav.addObject("areaIds", areaIds);
				mav.addObject("areas", areas);

				return mav;

			} catch (Exception e) {
				e.printStackTrace();
				mav.setViewName("redirect:/driver/getViewDriverList.do");
				return mav;
			}

		} else {
			mav.setViewName("redirect:/admin/login.do");
		}

		return mav;

	}

	/**
	 * 
	 * @描述：修改教练信息后,点击提交,保存修改后的信息.
	 * @作者:黄举飞
	 * @部门：伏守科技项目开发部
	 * @日期： 2016年6月23日  上午9:54:37
	 * @版本： V1.0 
	 * @param AreaId : 场地id
	 * @return
	 * @throws Exception 
	*/

	@RequestMapping("updateDriver")
	public ModelAndView updateDriver(Driver newDriver, String areaId, @RequestParam("files") MultipartFile[] files,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView("admin/updateDriver");

		boolean flag = true;

		if (flag) {
			mav.addObject("areas", areas);
			mav.addObject("areaIds", areaId);

			if (StringTools.isEmpty(areaId) || "0".equals(areaId)) {
				return mav.addObject("error", "请选择教练属于那个场地");
			} else {
				if (areaId.length() > 50) {
					return mav.addObject("error", "场地id长度不能大于50");
				}
			}

			if (StringTools.isEmpty(newDriver.getDriverName())) {
				return mav.addObject("error", "教练名字不能为空");
			} else {
				if (newDriver.getDriverName().length() > 20) {
					return mav.addObject("error", "教练名字不能长于20");
				}
			}

			//判断年龄
			if (0 == newDriver.getDriverAge()) {
				return mav.addObject("error", "教练的年龄不能为空");
			} else {

				if (StringTools.validateAge(newDriver.getDriverAge() + "")) {
					if (newDriver.getDriverAge() <= 18) {
						return mav.addObject("error", "年龄需要大于18岁!");
					}

				} else {
					return mav.addObject("error", "教练的年龄值大于18小于100");
				}

			}

			//判断驾龄
			if (StringTools.isEmpty(newDriver.getBeginAge())) {
				return mav.addObject("error", "教练的驾龄不能为空");
			} else {

				if (!StringTools.validateAge(newDriver.getBeginAge())) {
					return mav.addObject("error", "教练的驾龄,请输入合法的数字");
				}
			}

			//判断性别
			if (StringTools.isEmpty(newDriver.getDriverSex() + "")) {
				return mav.addObject("error", "性别不能为空");
			} else {

				if (newDriver.getDriverSex() != 1 && newDriver.getDriverSex() != 0) {
					return mav.addObject("error", "性别只能输入1或0(1表示男)");
				}
			}

			if ("0".equals(newDriver.getSubject())) {
				return mav.addObject("error", "请选择,教练可以教授的科目");
			}

			if (0.0 == newDriver.getDriverGrade()) {
				return mav.addObject("error", "请选择教练的等级");
			}

			//判断学员通过率
			if (StringTools.isEmpty(newDriver.getPassRate() + "")) {
				return mav.addObject("error", "学员通过率不能为空");
			} else {

				if (!StringTools.validateAge(newDriver.getPassRate() + "")) {
					return mav.addObject("error", "通过率请输入两位数的数值");
				}
			}

			//简介
			if (StringTools.isEmpty(newDriver.getDriverInfo())) {
				return mav.addObject("error", "教练的简介不能为空");
			} else {
				if (newDriver.getDriverInfo().length() > 255) {
					return mav.addObject("error", "教练简介不能大于255字");
				}
			}

			boolean tag = false;

			if (files != null) {

				int len = files.length;

				//检查多选图片上传的数量
				if (len > FileTools.IMAGENUMBER) {
					return mav.addObject("error", "多选图片的上传数量不能大于" + FileTools.IMAGENUMBER + "张");
				}

				for (int i = 0; i < len; i++) {

					if (files[i].isEmpty()) {
						//表示不上传
						tag = true;
					} else {

						if (!FileTools.validateSuffix(files[i])) {
							return mav.addObject("error", "教练的展示图片格式只能是JPG或PNG");
						}

						Image image;
						try {
							image = FileTools.readImageInfo(files[i]);
						} catch (Exception e) {
							mav.addObject("error", "展示图片种可能有图片损坏,请换图片.");
							return mav;

						}

						if (!FileTools.validateSize(image)) {
							return mav.addObject("error", "教练的展示图片片的大小不能超过" + FileTools.IMAGESIZE / 1000 + "KB");
						}

					}

				}

			} else {
				return mav.addObject("error", "files为空");
			}

			try {

				//参数:教练对象,图片,场地id,标记
				int i = driverService.updateDriver(newDriver, files, areaId, tag);

				if (i == -1) {
					mav.addObject("error", "根据场地id没有查到对应的场地!");
				}

				if (i == -2) {
					mav.addObject("error", "根据教练id没有查到对应的教练!");
				}

				if (i == 1) {
					mav.addObject("ok", "恭喜你修改成功!");
					mav.setViewName("forward:/driver/getViewDriverList.do");
				}

				return mav;

			} catch (Exception e) {
				e.printStackTrace();
				mav.setViewName("forward:/driver/getViewDriverList.do");
				return mav;
			}
		} else {
			mav.setViewName("redirect:/admin/login.do");
		}

		return mav;

	}

}
