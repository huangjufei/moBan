package cn.jiaxiaoAdmin.service.impl;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jiaxiaoAdmin.dao.AdminDAO;
import cn.jiaxiaoAdmin.model.Admin;
import cn.jiaxiaoAdmin.service.AdminService;
 
/**
 * 
 * @描述：Admin(管理员)数据服务层
 * @作者： 丁洪星
 * @文件名：AdminServiceImpl.java 
 * @包名：cn.zhuanquanquan.service.impl 
 * @项目名：zhuanquanquan
 * @部门：伏守科技项目开发部
 * @日期： 2015年11月4日 下午4:08:24 
 * @版本： V1.0
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {
	
	@Resource
	private AdminDAO adminDAO;

	//根据用户名查询管理员对象
	public Admin queryAdminByName(String strUserName) throws Exception {
		
		return adminDAO.queryAdminByName(strUserName);
		
	}

	//新增一个管理员对象
	public void insertAdmin(Admin admin) throws Exception {
		
		adminDAO.insertAdmin(admin);
		
	}

	//删除一个管理员对象
	public void deleteAdmin(String strAdminId) throws Exception {
		
		adminDAO.deleteAdmin(strAdminId);
		
	}

	//判断用户名是否在数据库中存在
	public String userNameIsExists(Admin admin) throws Exception {
		return adminDAO.userNameIsExists(admin);
	}

	//查询管理员用户总数量
	public int selectAdminCount(Map<String, Object> map) throws Exception {
		return adminDAO.selectAdminCount(map);
	}

	//查询管理员用户列表
	public List<Admin> selectAdmin(Map<String, Object> map) throws Exception {
		return adminDAO.selectAdmin(map);
	}

	//重置管理员密码
	public void updateAdminPwd(Admin admin) throws Exception {
		adminDAO.updateAdminPwd(admin);
	}
 
 
}