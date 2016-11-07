package cn.jiaxiaoAdmin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.jiaxiaoAdmin.model.Admin;
import cn.jiaxiaoAdmin.model.ResultVO;
import cn.jiaxiaoAdmin.service.AdminService;
import cn.jiaxiaoAdmin.service.impl.AdminServiceImpl;
import cn.jiaxiaoAdmin.util.JsonTool;
import cn.jiaxiaoAdmin.util.Md5EncoderTool;
import cn.jiaxiaoAdmin.util.StaticConstants;
import cn.jiaxiaoAdmin.util.ValidationTool;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminServiceImpl;

	/**
	 * 
	 * @描述：跳转到后台管理界面登录页面(超级管理员) @作者:丁洪星
	 * @部门：伏守科技项目开发部 @日期： 2015年11月4日 下午3:38:10 @版本： V1.0
	 * @return view
	 */
	@RequestMapping("login")
	public ModelAndView login() {
		
		System.out.println("jinlaile ----------------------");

		ModelAndView view = new ModelAndView();
		view.setViewName("admin/login");
		return view;

	}

	/**
	 * 
	 * @描述：用户退出登录
	 * @作者:丁洪星
	 * @部门：伏守科技项目开发部 @日期： 2015年11月4日 下午4:26:25 @版本： V1.0
	 * @return
	 */
	@RequestMapping("exit")
	public ModelAndView exit(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView view = new ModelAndView();

		request.getSession().removeAttribute("admin");

		view.setViewName("redirect:/admin/login.do");
		return view;

	}

	/**
	 * 登录流程
	 */
	@ResponseBody
	@RequestMapping("loginCheck")
	public String loginCheck(HttpServletRequest request, HttpServletResponse response, String strUserName,
			String strUserPassword) {

		ResultVO<String> resultVO = new ResultVO<String>();

		if (ValidationTool.isEmptyString(strUserName)) {
			// 用户名为空,登录失败
			resultVO.setState(StaticConstants.AJAX_STATE_FALSE);
			resultVO.setErrMsg("用户名不能为空哟！");
		} else if (ValidationTool.isEmptyString(strUserPassword)) {
			// 密码为空,登录失败
			resultVO.setState(StaticConstants.AJAX_STATE_FALSE);
			resultVO.setErrMsg("密码不能为空哟！");
		} else {
			// 用户名密码都不为空，进行验证用户名和密码是否匹配
			try {
				Admin admin = adminServiceImpl.queryAdminByName(strUserName);

				if (ValidationTool.isNull(admin)) {
					// 根据用户名查询出的管理员用户对象为空,登录失败
					resultVO.setState(StaticConstants.AJAX_STATE_FALSE);
					resultVO.setErrMsg("该用户名不存在哟！");
				} else {
					String strUserRealPassword = admin.getStrUserPassword(); // 用户的实际密码
					String md5StrUserPassword = Md5EncoderTool.createMd5(Md5EncoderTool.createMd5(strUserPassword)); // 经过MD5加密两次后的密码
					if (md5StrUserPassword.equals(strUserRealPassword)) {
						// 用户实际的密码和输入的密码匹配成功,登录成功
						resultVO.setState(StaticConstants.AJAX_STATE_TRUE);

						// 这里的Session 是自定义的的对象.作用就是方便存取值,封装的思想
						request.getSession().setAttribute("admin", admin);

					} else {
						// 用户实际的密码和输入的密码匹配失败,登录失败
						resultVO.setState(StaticConstants.AJAX_STATE_FALSE);
						resultVO.setErrMsg("密码错了哟！");
					}

				}
			} catch (Exception e) {
				resultVO.setState(StaticConstants.AJAX_STATE_FALSE);
				resultVO.setErrMsg("哦哦，服务器出错了！");
				e.printStackTrace();
			}
		}

		String json = JsonTool.toJson(resultVO);
		return json;

	}

	/**
	 * 
	 * @描述： 登陆成功跳转到主页
	 * 
	 * @作者:丁洪星
	 * @部门：伏守科技项目开发部 @日期： 2015年11月4日 下午5:33:45 @版本： V1.0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("homePage")
	public ModelAndView homePage(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView view = new ModelAndView();

		boolean flag = true;
		// System.out.println("是否登录:" + flag);

		if (flag) {
			// 查询出该登陆用户所拥有的权限

			Admin admin = (Admin) request.getSession().getAttribute("admin");

			try {
				view.addObject("admin", admin);
				view.setViewName("admin/home");
			} catch (Exception e) {
				view.setViewName("error/500");
				e.printStackTrace();
			}
		} else {
			// 没有登录，不能进入主页,跳到登录界面
			view.setViewName("redirect:/admin/login.do");
		}

		return view;

	}

	/**
	 * 
	 * @描述： 修改密码页面
	 * 
	 * @作者:丁洪星
	 * @部门：伏守科技项目开发部 @日期： 2015年11月4日 下午5:33:45 @版本： V1.0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("modifyPwdIndex")
	public ModelAndView modifyPwdIndex(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView view = new ModelAndView();
		boolean flag = true;
		System.out.println("是否登录:" + flag);
		if (flag) {
			// 查询出该登陆用户所拥有的权限
			view.setViewName("admin/modifyPwdIndex");
		} else {
			// 没有登录，不能进入主页,跳到登录界面
			view.setViewName("redirect:/admin/login.do");
		}
		return view;

	}

	/**
	 * 
	 * @描述： 修改密码
	 * 
	 * @作者:丁洪星
	 * @部门：伏守科技项目开发部 @日期： 2015年11月4日 下午5:05:17 @版本： V1.0
	 * @param request
	 * @param response
	 * @param strOldPassword
	 * @param strNewPassword
	 * @param strConfirmPassword
	 * @return
	 */
	@RequestMapping("modifyPwd")
	public ModelAndView modifyPwd(HttpServletRequest request, HttpServletResponse response, String strOldPassword,
			String strNewPassword, String strConfirmPassword) {

		ModelAndView view = new ModelAndView();
		boolean flag = true;

		if (flag) {
			// 已经登录

			if (ValidationTool.isEmptyString(strOldPassword)) {
				// 原密码为空
				view.setViewName("admin/modifyPwdIndex");
				view.addObject("errMsg", "原密码不能为空");
				return view;
			}

			if (ValidationTool.isEmptyString(strNewPassword)) {
				// 新密码为空
				view.setViewName("admin/modifyPwdIndex");
				view.addObject("errMsg", "新密码不能为空");
				return view;
			}

			if (ValidationTool.isEmptyString(strConfirmPassword)) {
				// 确认新密码为空
				view.setViewName("admin/modifyPwdIndex");
				view.addObject("errMsg", "确认新密码不能为空");
				return view;
			}

			if (strOldPassword.equals(strNewPassword)) {
				// 原密码和新密码相同
				view.setViewName("admin/modifyPwdIndex");
				view.addObject("errMsg", "新密码不能和原密码相同");
				return view;
			}

			if (!strConfirmPassword.equals(strNewPassword)) {
				// 新密码相和确认密码不相同
				view.setViewName("admin/modifyPwdIndex");
				view.addObject("errMsg", "两次输入的新密码不一致");
				return view;
			}

			try {

				Admin admin = (Admin) request.getSession().getAttribute("admin");

				if (!admin.getStrUserPassword()
						.equals(Md5EncoderTool.createMd5(Md5EncoderTool.createMd5(strOldPassword.trim())))) {
					// Md5EncoderTool.createMd5(Md5EncoderTool.createMd5(strOldPassword.trim()))
					// 原密码不匹配
					view.setViewName("admin/modifyPwdIndex");
					view.addObject("errMsg", "原密码错误");
					return view;
				}

				admin.setStrUserPassword(Md5EncoderTool.createMd5(Md5EncoderTool.createMd5(strNewPassword.trim())));

				// 修改密码
				adminServiceImpl.updateAdminPwd(admin);
				view.setViewName("admin/modifyPwdIndex");
				view.addObject("errMsg", "修改成功");

			} catch (Exception e) {
				view.setViewName("error/500");
				e.printStackTrace();
			}

		} else {
			// 表示该用户未登录
			view.setViewName("redirect:/admin/login.do");
		}
		return view;

	}

}
