package cn.jiaxiaoAdmin.service;

import java.util.List;
import java.util.Map;

import cn.jiaxiaoAdmin.model.Admin;

/**
 * 
 * @描述：Admin(管理员）Service接口层
 * @作者： 丁洪星
 * @文件名：AdminService.java 
 * @包名：cn.zhuanquanquan.service 
 * @项目名：zhuanquanquan
 * @部门：伏守科技项目开发部
 * @日期： 2015年11月4日 下午4:12:00 
 * @版本： V1.0
 */
public interface AdminService {
 
	/**
     * 
     * @描述：根据用户名查询管理员对象
     * @作者:丁洪星 
     * @部门：伏守科技项目开发部
     * @日期： 2015年11月4日 下午4:12:58 
     * @版本： V1.0 
     * @return 
     * @throws Exception
     */
	public Admin queryAdminByName(String strUserName) throws Exception;
	
	/**
	 * 
	 * @描述：新增一个管理员对象
	 * @作者:丁洪星 
	 * @部门：伏守科技项目开发部
	 * @日期： 2015年11月4日 下午5:15:28 
	 * @版本： V1.0 
	 * @param admin 新增的管理员对象
	 */
	public void insertAdmin(Admin admin) throws Exception;
	
	/**
	 * 
	 * @描述：删除一个管理员对象
	 * @作者:丁洪星 
	 * @部门：伏守科技项目开发部
	 * @日期： 2015年11月4日 下午5:21:35 
	 * @版本： V1.0 
	 * @param strAdminId 被删除的管理员ID
	 */
	public void deleteAdmin(String strAdminId) throws Exception;
	
	/**
	 * 
	 * @描述：判断用户名是否在数据库中存在
	 * @作者:丁洪星 
	 * @部门：伏守科技项目开发部
	 * @日期： 2015年11月4日 下午5:29:11 
	 * @版本： V1.0 
	 * @param admin 需要被判断的数据对象
	 * @return
	 */
	public String userNameIsExists(Admin admin) throws Exception;
	
	/**
	 * 
	 * @描述：查询管理员用户总数量
	 * @作者:丁洪星 
	 * @部门：伏守科技项目开发部
	 * @日期： 2015年11月20日 上午9:56:45 
	 * @版本： V1.0 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int selectAdminCount(Map<String, Object> map) throws Exception;

	/**
	 * 
	 * @描述：查询管理员用户列表
	 * @作者:丁洪星 
	 * @部门：伏守科技项目开发部
	 * @日期： 2015年11月20日 上午9:56:52 
	 * @版本： V1.0 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Admin> selectAdmin(Map<String, Object> map) throws Exception;
	
	/**
	 * 
	 * @描述：修改管理员用户密码
	 * @作者:丁洪星 
	 * @部门：伏守科技项目开发部
	 * @日期： 2015年11月20日 上午10:10:01 
	 * @版本： V1.0 
	 * @param admin
	 */
	public void updateAdminPwd(Admin admin) throws Exception;
    
}