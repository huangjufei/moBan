package cn.jiaxiaoAdmin.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.jiaxiaoAdmin.model.Page;
import cn.jiaxiaoAdmin.model.School;
import cn.jiaxiaoAdmin.service.SchoolService;
import cn.jiaxiaoAdmin.util.FileTools;
import cn.jiaxiaoAdmin.util.Image;
import cn.jiaxiaoAdmin.util.StringTools;

@RequestMapping("/school")
@Controller
public class AdminSchoolController {

	@Resource
	private SchoolService schoolService;

	/**
	 * 
	 * @描述：得到一个驾校list,带分页功能
	 * @作者:黄举飞
	 * @部门：伏守科技项目开发部 @日期： 2016年6月20日 下午7:23:11 @版本： V1.0
	 * @param page
	 * @return
	 */
	@RequestMapping("getViewSchoolList")
	public ModelAndView getViewSchoolList(Page<School> page, HttpServletRequest request, HttpServletResponse response,
			String searchSchoolName) {

		ModelAndView mav = new ModelAndView("admin/viewSchoolList");

		boolean flag = true;

		if (flag) {

			try {
				// 得到一个分页的驾校list
				Page<School> pages = schoolService.getViewSchoolList(page, searchSchoolName);
				mav.addObject("pages", pages);
				return mav;

			} catch (Exception e) {
				e.printStackTrace();
				// 跳转到失败页面,打印错误
				return mav;
			}

		} else {
			mav.setViewName("redirect:/admin/login.do");
		}
		return mav;

	}

	/**
	 * 
	 * @描述：转发去驾校添加页面
	 * @作者:黄举飞
	 * @部门：伏守科技项目开发部 @日期： 2016年6月21日 下午8:47:44 @版本： V1.0
	 * @return
	 */
	@RequestMapping("addSchoolPage")
	public ModelAndView addSchoolPage(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("admin/addSchool");
		boolean flag = true;
		if (flag) {
			try {
				mav.addObject("school", new School());
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
	 * @描述：后台保存驾校信息
	 * @作者:黄举飞
	 * @部门：伏守科技项目开发部 @日期： 2016年6月14日 下午5:46:59 @版本： V1.0
	 * @param school
	 * @param files
	 * @return
	 * @throws Exception
	 */

	@RequestMapping("addSchool")
	public ModelAndView addSchool(School school, @RequestParam("files") MultipartFile[] files,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView("admin/addSchool");

		boolean flag = true;

		if (flag) {
			// 从对象中取出字段,进行常规判断
			if (StringTools.isEmpty(school.getSchoolName())) {
				return mav.addObject("error", "驾校名字不能为空");
			} else {
				if (school.getSchoolName().length() > 20) {
					return mav.addObject("error", "驾校简介不能大于20字");
				}
			}

			if (StringTools.isEmpty(school.getSchoolLng())) {
				return mav.addObject("error", "驾校的经度不能为空");
			} else {
				if (school.getSchoolLng().length() > 10) {
					return mav.addObject("error", "驾校经度不能大于10字");
				}
			}

			if (StringTools.isEmpty(school.getSchoolLat())) {
				return mav.addObject("error", "驾校的纬度不能为空");
			} else {
				if (school.getSchoolLat().length() > 10) {
					return mav.addObject("error", "驾校的纬度不能大于10字");
				}
			}

			if (StringTools.isEmpty(school.getContactPerson())) {
				return mav.addObject("error", "驾校联系人不能空");
			} else {
				if (school.getContactPerson().length() > 15) {
					return mav.addObject("error", "驾校联系人不能大于15字");
				}
			}

			if (StringTools.isEmpty(school.getSchoolAddress())) {
				return mav.addObject("error", "驾校地址不能为空");
			} else {
				if (school.getSchoolAddress().length() > 50) {
					return mav.addObject("error", "驾校地址不能大于50字");
				}
			}

			if ("0".equals(Integer.toString(school.getSchoolGrade()))) {
				return mav.addObject("error", "请选择驾校的的等级");
			}

			if (StringTools.isEmpty(school.getDriverInfo())) {
				return mav.addObject("error", "所属驾校的教练信息简介");
			} else {
				if (school.getDriverInfo().length() > 200) {
					return mav.addObject("error", "教练信息简介不能大于200个字");
				}
			}

			if (StringTools.isEmpty(school.getSchoolInfo())) {
				return mav.addObject("error", "驾校的简介不能为空");
			} else {
				if (school.getSchoolInfo().length() > 1000) {
					return mav.addObject("error", "驾校简介不能大于1000字");
				}
			}

			if (files != null) {

				int len = files.length;

				for (int i = 0; i < len; i++) {

					// 上传logo
					if (i == 0) {

						if (files[0].isEmpty()) {
							return mav.addObject("error", "Logo为空");

						} else {

							if (!FileTools.validateSuffix(files[0])) {
								return mav.addObject("error", "Logo图片格式只能是JPG或PNG");
							}

							Image image;
							try {
								image = FileTools.readImageInfo(files[0]);
							} catch (Exception e) {
								mav.addObject("error", "Logo图片可能损坏,请换图片.");
								return mav;

							}

							if (!FileTools.validateSize(image)) {
								return mav.addObject("error", "Logo图片的大小不能超过" + FileTools.LOGOSIZE / 1000 + "KB");
							}

						}
					}

					// 上传图片
					if (i != 0 && i != len - 1) {

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

					// 上传视频
					if (i == len - 1) {

						if (files[len - 1].isEmpty()) {
							return mav.addObject("error", "视屏不能为空");

						} else {

							if (!FileTools.validateSuffix(files[len - 1])) {
								return mav.addObject("error", "驾校的视屏格式只能是什么格式???????????");
							}

							Image image;
							try {
								image = FileTools.readImageInfo(files[len - 1]);
							} catch (Exception e) {
								mav.addObject("error", "视频可能有图片损坏,请换图片.");
								return mav;

							}

							if (!FileTools.validateSize(image)) {
								return mav.addObject("error", "驾校的视屏大小不能大于" + FileTools.VIDEOSIZE / 1000 + "KB");
							}

						}

					}

				}

				// 检查多选图片上传的数量
				if (!files[0].isEmpty() && !files[len - 1].isEmpty()) {

					if (!FileTools.validateImageNumber(len - 2)) {
						return mav.addObject("error", "多选图片的上传数量不能大于" + FileTools.IMAGENUMBER + "张");
					}
				}

			} else {

				return mav.addObject("error", "files为空");
			}

			try {
				// 保存驾校
				int ii = schoolService.addSchool(school, files);

				if (ii == -1) {
					return mav.addObject("error", "保存驾校数据失败");
				}

				if (ii == -3) {
					return mav.addObject("error", "驾校名字已经存在");
				}

				if (ii == 1) {
					// 成功返回
					return mav.addObject("ok", "恭喜你,添加成功!");
				} else {
					return mav.addObject("error", "保存驾校未知错误");
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
	 * @描述：根据驾校id删除驾校的一条记录
	 * @作者:黄举飞
	 * @部门：伏守科技项目开发部 @日期： 2016年6月23日 上午9:51:28 @版本： V1.0
	 * @param schoolId
	 * @return
	 */
	@RequestMapping("deleteSchool")
	public ModelAndView deleteSchool(String schoolId, HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("forward:/school/getViewSchoolList.do");

		boolean flag = true;

		if (flag) {

			if (StringTools.isEmpty(schoolId)) {
				mav.addObject("error", "删除的驾校的id不能为空");
			}

			try {

				int i = schoolService.deleteSchool(schoolId);
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

	/**
	 * 
	 * @描述：根据驾校id,查询驾校对象,然后去修改页面
	 * @作者:黄举飞
	 * @部门：伏守科技项目开发部 @日期： 2016年6月23日 上午9:54:37 @版本： V1.0
	 * @param schoolId
	 * @return
	 */
	@RequestMapping("updateSchoolPage")
	public ModelAndView updateSchoolPage(String schoolId, HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("admin/updateSchool");

		boolean flag = true;

		if (flag) {

			if (StringTools.isEmpty(schoolId)) {
				mav.addObject("error", "的驾校的id不能为空");
			}

			try {

				School school = schoolService.selectSchoolById(schoolId);
				if (school == null) {
					return mav.addObject("error", "数据库种没有查到该驾校");
				}

				mav.addObject("school", school);
				return mav;
			} catch (Exception e) {
				e.printStackTrace();
				mav.setViewName("redirect:/school/getViewSchoolList.do");
				return mav;
			}

		} else {
			mav.setViewName("redirect:/admin/login.do");
		}
		return mav;

	}

	/**
	 * 
	 * @描述：修改驾校信息后,点击提高,保存修改后的信息. @作者:黄举飞
	 * @部门：伏守科技项目开发部 @日期： 2016年6月23日 上午9:54:37 @版本： V1.0
	 * @param schoolId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("updateSchool")
	public ModelAndView updateSchool(School newSchool, @RequestParam("files") MultipartFile[] files,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView("admin/updateSchool");

		boolean flag = true;

		if (flag) {
			if (StringTools.isEmpty(newSchool.getSchoolId())) {
				mav.addObject("error", "删除的驾校的id不能为空");
			}
			// 从对象中取出字段,进行常规判断
			if (StringTools.isEmpty(newSchool.getSchoolName())) {
				return mav.addObject("error", "驾校名字不能为空");
			}
			if (StringTools.isEmpty(newSchool.getSchoolLng())) {
				return mav.addObject("error", "驾校的经度不能为空");
			}

			if (StringTools.isEmpty(newSchool.getSchoolLat())) {
				return mav.addObject("error", "驾校的纬度不能为空");
			}

			if (StringTools.isEmpty(newSchool.getContactPerson())) {
				return mav.addObject("error", "驾校联系人不能空");
			}

			if (StringTools.isEmpty(newSchool.getSchoolAddress())) {
				return mav.addObject("error", "驾校地址不能为空");
			}

			if ("0".equals(Integer.toString(newSchool.getSchoolGrade()))) {
				return mav.addObject("error", "请选择驾校的的等级");
			}
			if (StringTools.isEmpty(newSchool.getDriverInfo())) {
				return mav.addObject("error", "所属驾校的教练信息简介");
			}

			if (StringTools.isEmpty(newSchool.getSchoolInfo())) {
				return mav.addObject("error", "驾校的简介不能为空");
			}

			/*
			 * 这里注释的是修改logo ,展示图片 和视频 的,现在暂时不用这个
			 * 
			 * if (files != null) {
			 * 
			 * int len = files.length;
			 * 
			 * for (int i = 0; i < len; i++) {
			 * 
			 * //上传logo if (i == 0) {
			 * 
			 * if (files[0].isEmpty()) { return mav.addObject("error",
			 * "Logo为空"); } else {
			 * 
			 * if (!FileTools.validateSuffix(files[0])) { return
			 * mav.addObject("error", "Logo图片格式只能是JPG或PNG"); }
			 * 
			 * Image image; try { image = FileTools.readImageInfo(files[0]); }
			 * catch (Exception e) { mav.addObject("error",
			 * "展示图片种可能有图片损坏,请换图片."); return mav;
			 * 
			 * }
			 * 
			 * if (!FileTools.validateSize(image)) { return
			 * mav.addObject("error", "Logo图片的大小不能超过" + FileTools.LOGOSIZE /
			 * 1000 + "KB"); }
			 * 
			 * } }
			 * 
			 * //上传图片 if (i != 0 && i != len - 1) {
			 * 
			 * if (files[i].isEmpty()) { return mav.addObject("error",
			 * "驾校的展示图片不能为空"); } else {
			 * 
			 * if (!FileTools.validateSuffix(files[i])) { return
			 * mav.addObject("error", "驾校的展示图片格式只能是JPG或PNG"); }
			 * 
			 * Image image; try { image = FileTools.readImageInfo(files[i]); }
			 * catch (Exception e) { mav.addObject("error",
			 * "展示图片种可能有图片损坏,请换图片."); return mav;
			 * 
			 * }
			 * 
			 * if (!FileTools.validateSize(image)) { return
			 * mav.addObject("error", "驾校的展示图片片的大小不能超过" + FileTools.IMAGESIZE /
			 * 1000 + "KB"); }
			 * 
			 * }
			 * 
			 * }
			 * 
			 * //上传视频 if (i == len - 1) {
			 * 
			 * if (files[len - 1].isEmpty()) { return mav.addObject("error",
			 * "视屏不能为空"); } else {
			 * 
			 * if (!FileTools.validateSuffix(files[len - 1])) { return
			 * mav.addObject("error", "驾校的视屏格式只能是什么格式???????????"); }
			 * 
			 * Image image;
			 * 
			 * try { image = FileTools.readImageInfo(files[len - 1]); } catch
			 * (Exception e) { mav.addObject("error", "展示图片种可能有图片损坏,请换图片.");
			 * return mav;
			 * 
			 * }
			 * 
			 * if (!FileTools.validateSize(image)) { return
			 * mav.addObject("error", "驾校的视屏大小不能大于" + FileTools.VIDEOSIZE / 1000
			 * + "KB"); }
			 * 
			 * }
			 * 
			 * }
			 * 
			 * }
			 * 
			 * //检查多选图片上传的数量 if (!files[0].isEmpty() && !files[len -
			 * 1].isEmpty()) {
			 * 
			 * if (!FileTools.validateImageNumber(len - 2)) { return
			 * mav.addObject("error", "多选图片的上传数量不能大于" + FileTools.IMAGENUMBER +
			 * "张"); } }
			 * 
			 * } else {
			 * 
			 * return mav.addObject("error", "files为空"); }
			 * 
			 */

			boolean tag = true;

			if (files != null) {

				int len = files.length;

				System.out.println(len);
				// 检查多选图片上传的数量
				if (len > FileTools.IMAGENUMBER) {
					return mav.addObject("error", "多选图片的上传数量不能大于" + FileTools.IMAGENUMBER + "张");
				}

				for (int i = 0; i < len; i++) {

					if (files[i].isEmpty()) {
						// return mav.addObject("error", "驾校的展示图片不能为空");

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

				int i = schoolService.updateSchool(newSchool, files, tag);

				if (i == 1) {
					mav.addObject("ok", "恭喜你修改成功!");
					mav.setViewName("forward:/school/getViewSchoolList.do");
					return mav;
				}

				if (i == -1) {
					mav.addObject("error", "上传时出错");
					mav.setViewName("forward:/school/getViewSchoolList.do");
					return mav;
				}
				if (i == -2) {
					mav.addObject("error", "没有查到该驾校");
					mav.setViewName("forward:/school/getViewSchoolList.do");
					return mav;
				}

				if (i == -3) {
					mav.addObject("error", "修改驾校信息出错");
					mav.setViewName("forward:/school/getViewSchoolList.do");
					return mav;

				} else {
					mav.addObject("error", "修改驾校信息系统错误");
					mav.setViewName("forward:/school/getViewSchoolList.do");
					return mav;
				}

			} catch (Exception e) {
				e.printStackTrace();
				mav.setViewName("forward:/school/getViewSchoolList.do");
				return mav;
			}

		} else {
			mav.setViewName("redirect:/admin/login.do");
		}
		return mav;

	}

	@RequestMapping("moban")
	public ModelAndView mobanTest() {
		ModelAndView mav = new ModelAndView("admin/moban2");
		return mav;
	}

}
