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
import cn.jiaxiaoAdmin.model.Page;
import cn.jiaxiaoAdmin.model.School;
import cn.jiaxiaoAdmin.model.SchoolAreaCenter;
import cn.jiaxiaoAdmin.service.AreaService;
import cn.jiaxiaoAdmin.util.FileTools;
import cn.jiaxiaoAdmin.util.Image;
import cn.jiaxiaoAdmin.util.StringTools;


@RequestMapping("/area")
@Controller
public class AdminAreaController {

	@Resource
	private AreaService areaService;



	/**

我开
	 * @描述：得到一个场地的list,带分页功能
	 * @作者:黄举飞1231231231
	 * @部门：伏守科技项目开发部
	 * @日期： 2016年6月20日  下午7:23:11
	 * @版本： V1.0 
	 * @param page
	 * @return
	 */
	@RequestMapping("getViewAreaList")
	public ModelAndView getViewAreaList(Page<Area> page, HttpServletRequest request, HttpServletResponse response,String searchAreaName) {

		
		ModelAndView mav = new ModelAndView("admin/viewAreaList");

		boolean flag = true;

		if (flag) {

			try {
				//得到一个分页的场地的list
				Page<Area> pages = areaService.getViewAreaList(page,searchAreaName);
				mav.addObject("pages", pages);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			mav.setViewName("redirect:/admin/login.do");
		}

		return mav;
	}

	/**
	 * 
	 * @描述：转发去场地添加页面
	 * @作者:黄举飞
	 * @部门：伏守科技项目开发部
	 * @日期： 2016年6月21日  下午8:47:44
	 * @版本： V1.0 
	 * @return
	 */
	@RequestMapping("addAreaPage")
	public ModelAndView addAreaPage(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("admin/addArea");
		boolean flag = true;

		if (flag) {
			try {

				mav.addObject("area", new Area());
				schools = areaService.getSchoolList();

				if (schools == null || schools.size() == 0) {
					mav.addObject("error", "场地必须关联一个驾校,请先建立驾校!");
					return mav;
				}

				mav.addObject("schools", schools);

			} catch (Exception e) {
				e.printStackTrace();

			}
		} else {
			mav.setViewName("redirect:/admin/login.do");
		}

		return mav;
	}

	/**
	 * 
	 * @描述：后台保存场地信息
	 * @作者:黄举飞
	 * @部门：伏守科技项目开发部
	 * @日期： 2016年6月14日  下午5:46:59
	 * @版本： V1.0 
	 * @param school
	 * @param files
	 * @return
	 * @throws Exception
	 */

	@RequestMapping("addArea")
	public ModelAndView addArea(Area area, String schoolId, @RequestParam("files") MultipartFile[] files,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView("admin/addArea");

		boolean flag = true;

		if (flag) {

			mav.addObject("schools", schools);
			mav.addObject("schoolId", schoolId);

			if (StringTools.isEmpty(schoolId) || "0".equals(schoolId)) {
				return mav.addObject("error", "请选择场地属于那个驾校");
			}

			if (StringTools.isEmpty(area.getAreaName())) {
				return mav.addObject("error", "场地名字不能为空");
			} else {
				if (area.getAreaName().length() > 20) {
					return mav.addObject("error", "场地名字不能长于20");
				}
			}

			if (StringTools.isEmpty(area.getAreaLng())) {
				return mav.addObject("error", "场地的经度不能为空");
			} else {
				if (area.getAreaLng().length() > 10) {
					return mav.addObject("error", "场地的经度不能10");
				}
			}

			if (StringTools.isEmpty(area.getAreaLat())) {
				return mav.addObject("error", "场地的纬度不能为空");
			} else {
				if (area.getAreaLat().length() > 10) {
					return mav.addObject("error", "场地的纬度不能长于10");
				}
			}

			if (StringTools.isEmpty(area.getAreaAddress())) {
				return mav.addObject("error", "场地地址不能为空");
			} else {
				if (area.getAreaAddress().length() > 50) {
					return mav.addObject("error", "场地地址不能长于50");
				}
			}

			if (StringTools.isEmpty(area.getAreaNumber())) {
				return mav.addObject("error", "场地的编号不能为空");
			} else {
				if (area.getAreaNumber().length() > 20) {
					return mav.addObject("error", "场地的编号不能为空不能长于20");
				}
			}

			if (StringTools.isEmpty(area.getClassmark()) || "0".equals(area.getClassmark())) {
				return mav.addObject("error", "场地有拥有那些活动的标记不能为空");
			}

			if (StringTools.isEmpty(area.getAreaInfo())) {
				return mav.addObject("error", "场地的简介不能为空");
			} else {
				if (area.getAreaInfo().length() > 1000) {
					return mav.addObject("error", "场地简介不能大于1000字");
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
						return mav.addObject("error", "驾校的展示图片不能为空");
					} else {

						if (!FileTools.validateSuffix(files[i])) {
							return mav.addObject("error", "驾校的展示图片格式只能是JPG或PNG");
						}

						Image image;
						try {
							image = FileTools.readImageInfo(files[i]);
						} catch (Exception e) {
							mav.addObject("error", "展示图片种可能有图片损坏,请换图片.");
							return mav;

						}

						if (!FileTools.validateSize(image)) {
							return mav.addObject("error", "驾校的展示图片片的大小不能超过" + FileTools.IMAGESIZE / 1000 + "KB");
						}

					}

				}

			} else {
				return mav.addObject("error", "files为空");
			}

			try {
				//保存场地
				int ii = areaService.addArea(area, schoolId, files);

				if (ii == -1) {
					return mav.addObject("error", "没有查到对应的驾校");
				}

				if (ii == -3) {
					return mav.addObject("error", "场地名字已经存在");
				}

				if (ii == 1) {
					//成功返回
					return mav.addObject("ok", "恭喜你,添加成功!");
				} else {
					return mav.addObject("error", "保存场地未知错误");
				}

			} catch (Exception e) {
				e.printStackTrace();
				return mav.addObject("error", "保存驾校系统错误");
			}
		} else {
			mav.setViewName("redirect:/admin/login.do");
		}

		return mav;

	}

	/**
	 * 
	 * @描述：删除一个场地的信息
	 * @作者:黄举飞
	 * @部门：伏守科技项目开发部
	 * @日期： 2016年6月24日  下午2:25:15
	 * @版本： V1.0 
	 * @param AreaId
	 * @return
	 */
	@RequestMapping("deleteArea")
	public ModelAndView deleteArea(String areaId, HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("forward:/area/getViewAreaList.do");

		boolean flag = true;

		if (flag) {

			if (StringTools.isEmpty(areaId)) {
				mav.addObject("error", "删除的场地的id不能为空");
			}

			try {

				int i = areaService.deleteArea(areaId);
				if (i == 1) {
					mav.addObject("ok", "删除成功!");
				} else {
					mav.addObject("error", "删除失败");
				}
				return mav;

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			mav.setViewName("redirect:/admin/login.do");
		}

		return mav;
	}

	List<School> schools;
	SchoolAreaCenter sac;

	/**
	 * 
	 * @描述：根据驾校id,查询驾校对象,然后去修改页面
	 * @作者:黄举飞
	 * @部门：伏守科技项目开发部
	 * @日期： 2016年6月23日  上午9:54:37
	 * @版本： V1.0 
	 * @param schoolId
	 * @return
	 */
	@RequestMapping("updateAreaPage")
	public ModelAndView updateAreaPage(String areaId, HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("admin/updateArea");

		boolean flag = true;

		if (flag) {

			if (StringTools.isEmpty(areaId)) {
				mav.addObject("error", "场地的的id不能为空");
				mav.setViewName("forward:/area/getViewAreaList.do");
				return mav;
			}

			try {
				Map<String, Object> map = new HashMap<>();

				map = areaService.selectAreaById(areaId);

				Area area = (Area) map.get("area");
				sac = (SchoolAreaCenter) map.get("schoolAndArea");
				schools = (List<School>) map.get("schools");

				if (area == null) {
					return mav.addObject("error", "数据库中没有查到该场地");
				}

				if (sac == null) {
					mav.addObject("error", "可能原来关联的驾校已经不存在了,目前场地没关联任何驾校");
				}

				if (schools == null || schools.size() == 0) {
					return mav.addObject("error", "请先添加驾校信息!");
				}

				mav.addObject("area", area);
				mav.addObject("sac", sac);
				mav.addObject("schools", schools);

				return mav;
			} catch (Exception e) {
				e.printStackTrace();
				mav.setViewName("redirect:/Area/getViewAreaList.do");
			}

		} else {
			mav.setViewName("redirect:/admin/login.do");

		}
		return mav;

	}

	/**
	 * 
	 * @描述：修改驾校信息后,点击提高,保存修改后的信息.
	 * @作者:黄举飞
	 * @部门：伏守科技项目开发部
	 * @日期： 2016年6月23日  上午9:54:37
	 * @版本： V1.0 
	 * @param AreaId
	 * @return
	 * @throws Exception 
	 */

	@RequestMapping("updateArea")
	public ModelAndView updateArea(Area newArea, String schoolId, @RequestParam("files") MultipartFile[] files,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView("admin/updateArea");

		boolean flag = true;

		if (flag) {

			if (StringTools.isEmpty(schoolId)) {
				return mav.addObject("error", "请选择场地属于那个驾校");
			} else {
				if (schoolId.length() > 50) {
					return mav.addObject("error", "驾校的id长度不能大于50");
				}
			}

			//重新设置属于那个驾校id
			sac.setSchoolId(schoolId);
			mav.addObject("sac", sac);
			mav.addObject("schools", schools);

			if (StringTools.isEmpty(newArea.getAreaLng())) {
				return mav.addObject("error", "场地的经度不能为空");
			} else {
				if (newArea.getAreaLng().length() > 10) {
					return mav.addObject("error", "场地的经度不能10");
				}
			}

			if (StringTools.isEmpty(newArea.getAreaLat())) {
				return mav.addObject("error", "场地的纬度不能为空");
			} else {
				if (newArea.getAreaLat().length() > 10) {
					return mav.addObject("error", "场地的纬度不能长于10");
				}
			}

			if (StringTools.isEmpty(newArea.getAreaAddress())) {
				return mav.addObject("error", "场地地址不能为空");
			} else {
				if (newArea.getAreaAddress().length() > 50) {
					return mav.addObject("error", "场地地址不能长于50");
				}
			}

			if (StringTools.isEmpty(newArea.getAreaNumber())) {
				return mav.addObject("error", "场地的编号不能为空");
			} else {
				if (newArea.getAreaNumber().length() > 20) {
					return mav.addObject("error", "场地的编号不能为空不能长于20");
				}
			}

			if (StringTools.isEmpty(newArea.getClassmark()) || "0".equals(newArea.getClassmark())) {
				return mav.addObject("error", "场地有拥有那些活动的标记不能为空");
			}

			if (StringTools.isEmpty(newArea.getAreaInfo())) {
				return mav.addObject("error", "场地的简介不能为空");
			} else {
				if (newArea.getAreaInfo().length() > 1000) {
					return mav.addObject("error", "场地简介不能大于1000字");
				}
			}

			boolean tag = true;

			if (files != null) {

				int len = files.length;

				System.out.println(len);
				//检查多选图片上传的数量
				if (len > FileTools.IMAGENUMBER) {
					return mav.addObject("error", "多选图片的上传数量不能大于" + FileTools.IMAGENUMBER + "张");
				}

				for (int i = 0; i < len; i++) {

					if (files[i].isEmpty()) {
						//return mav.addObject("error", "驾校的展示图片不能为空");	

						tag = false;

					} else {

						if (!FileTools.validateSuffix(files[i])) {
							return mav.addObject("error", "驾校的展示图片格式只能是JPG或PNG");
						}

						Image image;
						try {
							image = FileTools.readImageInfo(files[i]);
						} catch (Exception e) {
							mav.addObject("error", "展示图片种可能有图片损坏,请换图片.");
							return mav;

						}

						if (!FileTools.validateSize(image)) {
							return mav.addObject("error", "驾校的展示图片片的大小不能超过" + FileTools.IMAGESIZE / 1000 + "KB");
						}

					}

				}

			} else {
				return mav.addObject("error", "files为空");
			}

			try {

				int i = areaService.updateArea(newArea, files, sac, tag);

				if (i == 1) {
					mav.addObject("ok", "恭喜你修改成功!");
					mav.setViewName("forward:/area/getViewAreaList.do");
					return mav;
				}

				if (i == -1) {
					mav.addObject("error", "上传时出错");

					return mav;
				}
				if (i == -2) {
					mav.addObject("error", "没有查到该场地");
					return mav;
				}

				if (i == -3) {
					mav.addObject("error", "修改驾校信息出错");
					return mav;
				}

				if (i == -5) {
					mav.addObject("error", "根据驾校的id得到驾校名字时出错");
					return mav;

				} else {
					mav.addObject("error", "修改驾校信息系统错误");
					return mav;
				}

			} catch (Exception e) {
				e.printStackTrace();
				mav.setViewName("forward:/area/getViewAreaList.do");
				return mav;
			}

		} else {
			mav.setViewName("redirect:/admin/login.do");
		}

		return mav;
	}

}
