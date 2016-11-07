package cn.jiaxiaoAdmin.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import cn.jiaxiaoAdmin.dao.AreaDAO;
import cn.jiaxiaoAdmin.model.Area;
import cn.jiaxiaoAdmin.model.AreaDriverCenter;
import cn.jiaxiaoAdmin.model.Page;
import cn.jiaxiaoAdmin.model.School;
import cn.jiaxiaoAdmin.model.SchoolAreaCenter;
import cn.jiaxiaoAdmin.service.AreaService;
import cn.jiaxiaoAdmin.util.FileTool;
import cn.jiaxiaoAdmin.util.StringTools;


@Transactional
@Service
public class AreaServiceImpl implements AreaService {

	@Resource
	private AreaDAO areaDAO;

	public void insertArea(Area area) throws Exception {
		areaDAO.insertArea(area);
	}

	@Override
	public int selectCountByName(String areaName) throws Exception {

		return areaDAO.selectCountByName(areaName);
	}

	@Override
	public void updateAreaLatAndLng(Area area) throws Exception {
		areaDAO.updateAreaLatAndLng(area);

	}

	@Override
	public Area getAreaById(String areaId) throws Exception {
		return areaDAO.getAreaById(areaId);
	}

	//保存场地信息
	@Transactional(rollbackFor = RuntimeException.class)
	@Override
	public void addArea(Area area) throws Exception {

		int i = 0;
		//根据学校的名字,查询数据库有这名字对应的记录没有.有返回数字大于0 ,没有返回0
		i = areaDAO.selectCountByName((area.getAreaName()).trim());

		if (i > 0) {//数据库中查到有这场地名字 , 就执行修改操作
			//areaDAO.updateAreaLatAndLng(area);				
		} else { //数据库中没有这个场地,就执行添加				
						//areaDAO.insertArea(area);				 
		}

	}

	@Override
	public List<Area> getAreaIsList() throws Exception {
		return areaDAO.getAreaIsList();
	}

	@Override
	public List<Area> getAreaIsListNotNull(String mark) throws Exception {

		return areaDAO.getAreaIsListNotNull(mark);
	}

	@Override
	public List<Area> getAreaListByName(Map maps) throws Exception {

		return areaDAO.getAreaListByName(maps);
	}

	
	
	
	
	
	
	
	
	
	
	/**
	 * 
	 * @描述：后台,分页显示驾校的list
	 * @作者:黄举飞
	 * @部门：伏守科技项目开发部
	 * @日期： 2016年6月28日  下午5:40:39
	 * @版本： V1.0 
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@Transactional(rollbackFor = RuntimeException.class)
	@Override
	public Page<Area> getViewAreaList(Page<Area> page,String searchAreaName) throws Exception {

		try {

			int total = areaDAO.getCountArea();
			//System.out.println("total="+total);

			int pageSize = page.getPageSize();

			//计算一些前段需要的数据

			int totalPage = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;//总页数

			//判断下一页不会超出界限
			if (totalPage < page.getPageNum()) {
				page.setPageNum(totalPage);
			}

			page.setTotalPage(totalPage);

			//默认是开始页是第一页,
			int startNuber = (page.getPageNum() - 1) * pageSize;

			//如果起始位置小鱼0,就等于0,mysql 从0索引开始
			if (startNuber < 0) {
				startNuber = 0;
			}

			List<Area> areas = areaDAO.getViewAreaList(startNuber, pageSize,searchAreaName);

			page.setT(areas);//放入对象
			page.setTotalNumber(total);//总条数

			return page;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	
	
	/**
	 * 
	 * @描述：后台得到一个学校的list只有部分字段
	 * @作者:黄举飞
	 * @部门：伏守科技项目开发部
	 * @日期： 2016年6月28日  下午5:46:09
	 * @版本： V1.0 
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<School> getSchoolList() throws Exception {
		
		try {	
			return areaDAO.getSchoolList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	
	
	
	/**
	 * 
	 * @描述：保存场地
	 * @作者:黄举飞
	 * @部门：伏守科技项目开发部
	 * @日期： 2016年6月28日  下午5:39:42
	 * @版本： V1.0 
	 * @param area
	 * @param schoolId
	 * @param files
	 * @return
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = RuntimeException.class)
	@Override
	public int addArea(Area area, String schoolId, MultipartFile[] files) throws Exception {

		try {

			int count = areaDAO.selectCountByName(area.getAreaName().trim());
			if (count > 0) {
				return -3;//场地的名字重复了
			}

			//上传图片
			if (files != null) {

				List<String> list = new ArrayList<>();
				int len = files.length;
				for (int i = 0; i < len; i++) {

					if (files[i].isEmpty()) {
						return -1;
					} else {

						list.add(new FileTool().fileUpload(files[i]));
						area.setAreaImage(list.toString().substring(1, list.toString().length() - 1));
					}
				}

			} else {
				return -5;
			}

			String areaId = StringTools.getUUID();

			area.setAreaId(areaId);//主键
			//设置创建时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			area.setInsertTimeStamp(sdf.format(new Date()));

			SchoolAreaCenter sac = new SchoolAreaCenter();
			//保存场地
			areaDAO.addArea(area);

			//维护中间表
			School school = areaDAO.selectSchoolById(schoolId.trim());
			if (school == null) {
				return -1;
			}

			sac.setSchool_area_id(StringTools.getUUID());
			sac.setSchoolId(schoolId);
			sac.setAreaId(areaId);
			sac.setSchoolName(school.getSchoolName());
		
			//保存学校和场地的中间表
			areaDAO.insertSchoolAndArea(sac);

			return 1;

		} catch (Exception e) {
			e.printStackTrace();
			return -5;
		}

	}

	
	
	
	/**
	 * 
	 * @描述：删除一条场地
	 * @作者:黄举飞
	 * @部门：伏守科技项目开发部
	 * @日期： 2016年6月28日  下午5:41:15
	 * @版本： V1.0 
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	@Transactional(rollbackFor = RuntimeException.class)
	@Override
	public int deleteArea(String areaId) throws Exception {

		try {
			//删除中间表
			List<AreaDriverCenter> adc = areaDAO.selectAreaDriverCenterByAreaId(areaId);
			if(adc != null && adc.size() != 0){
			
				for (AreaDriverCenter areaDriverCenter : adc) {
					//删除中间表
					areaDAO.deleteAreaDriverCenterById(areaDriverCenter.getArea_driver_id());
					
				}
			}
			

			//删除场地
			areaDAO.deleteArea(areaId);

			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -5;
		}
	}

	
	
	
	/**
	 * 
	 * @描述：修改操作前准备的回显数据
	 * @作者:黄举飞
	 * @部门：伏守科技项目开发部
	 * @日期： 2016年6月28日  下午5:41:40
	 * @版本： V1.0 
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	@Transactional(rollbackFor = RuntimeException.class)
	@Override
	public Map<String, Object> selectAreaById(String areaId) throws Exception {

		Map<String, Object> map = new HashMap<>();

		try {

			//根据id得到一个场地的对象
			map.put("area", areaDAO.selectAreaById(areaId));

			//查出相关联的学校id和名字
			SchoolAreaCenter sac = areaDAO.selectSchoolAndArea(areaId);
			map.put("schoolAndArea", sac);

			//得到全部学校的id,和名字
			List<School> schools = areaDAO.getSchoolList();
			map.put("schools", schools);

			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return map;
		}

	}

	
	
	
	
	
	
	
	
	/**
	 * 
	 * @描述：修改场地信息
	 * @作者:黄举飞
	 * @部门：伏守科技项目开发部
	 * @日期： 2016年6月28日  下午5:43:03
	 * @版本： V1.0 
	 * @param newArea
	 * @param files
	 * @param sac
	 * @param tag
	 * @return
	 */
	@Transactional(rollbackFor = RuntimeException.class)
	@Override
	public int updateArea(Area newArea, MultipartFile[] files, SchoolAreaCenter sac, boolean tag) {

		try {

			Area oldArea = areaDAO.selectAreaById(newArea.getAreaId());
			if (oldArea == null) {
				return -2;
			}

			//根据驾校id得到驾校的名字
			String schoolName = areaDAO.selectSchoolIsNameById(sac.getSchoolId());
			if (StringTools.isEmpty(schoolName)) {
				return -5;
			}

			sac.setSchoolName(schoolName);

			if (tag) {
				//上传图片
				if (files != null) {

					List<String> list = new ArrayList<>();
					int len = files.length;
					for (int i = 0; i < len; i++) {

						if (files[i].isEmpty()) {
							return -1;
						} else {

							list.add(new FileTool().fileUpload(files[i]));
							newArea.setAreaImage(list.toString().substring(1, list.toString().length() - 1));
						}
					}

					oldArea.setAreaImage(newArea.getAreaImage());

				} else {
					return -5;
				}

			}
			oldArea.setAreaAddress(newArea.getAreaAddress());
			oldArea.setAreaInfo(newArea.getAreaInfo());
			oldArea.setAreaLat(newArea.getAreaLat());
			oldArea.setAreaLng(newArea.getAreaLng());
			oldArea.setAreaNumber(newArea.getAreaNumber());
			oldArea.setClassmark(newArea.getClassmark());

			//修改场地信息

			if (tag) {
				areaDAO.updateArea(oldArea);

			} else {
				areaDAO.updateAreaNoImage(oldArea);
			}
			//修改中间表
			areaDAO.updateSchoolAndArea(sac);

			return 1;//修改成功

		} catch (Exception e) {
			e.printStackTrace();
			return -5;
		}
	}

}
