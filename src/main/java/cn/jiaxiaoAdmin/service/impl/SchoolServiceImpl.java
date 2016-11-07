package cn.jiaxiaoAdmin.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import cn.jiaxiaoAdmin.dao.SchoolDAO;
import cn.jiaxiaoAdmin.model.Page;
import cn.jiaxiaoAdmin.model.School;
import cn.jiaxiaoAdmin.model.SchoolAreaCenter;
import cn.jiaxiaoAdmin.service.SchoolService;
import cn.jiaxiaoAdmin.util.FileTools;
import cn.jiaxiaoAdmin.util.StringTools;


@Transactional
@Service
public class SchoolServiceImpl implements SchoolService {
	@Resource
	private SchoolDAO schoolDAO;

	public void insertSchool(School school) throws Exception {
		schoolDAO.insertSchool(school);
	}

	@Override
	public void updateSchoolLatAndLng(School school) throws Exception {
		schoolDAO.updateSchoolLatAndLng(school);
	}

	@Override
	public List<School> getSchoolList() throws Exception {

		return schoolDAO.getSchoolList();
	}

	@Override
	public List<School> querySchoolList() throws Exception {
		return schoolDAO.querySchoolList();
	}

	@Override
	public School getSchooById(String schoolId) throws Exception {
		return schoolDAO.getSchooById(schoolId);
	}

	/**
	 * 
	 * @描述：添加学校信息,包含图片
	 * @作者:黄举飞
	 * @部门：伏守科技项目开发部
	 * @日期： 2016年6月21日  下午8:42:25
	 * @版本： V1.0 
	 * @param school
	 * @param files
	 * @return
	 * @throws Exception
	 */

	@Transactional(rollbackFor = RuntimeException.class)
	@Override
	public int addSchool(School school, MultipartFile[] files) throws Exception {

		try {

			// 根据学校的名字,查询数据库有这名字对应的记录
			int count = schoolDAO.selectCountByName((school.getSchoolName().trim()));

			if (count >= 1) {
				return -3;//学校的名字已经存在
			} else {

				school.setSchoolId(StringTools.getUUID());//设置主键
				//设置创建时间
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				school.setInsertTimeStamp(sdf.format(new Date()));

				if (files != null) {

					List<String> list = new ArrayList<>();

					int len = files.length;

					//System.out.println("一共需要上传" + len + "文件");

					for (int i = 0; i < len; i++) {

						//上传logo
						if (i == 0) {

							if (files[0].isEmpty()) {
								return -1;
							} else {
								//这里相当于直接吧名字设置到了这对象的Logo字段上
								school.setSchoolLogo(FileTools.upload(files[0]));

							}

						}

						//上传图片	
						if (i != 0 && i != len - 1) {

							if (files[i].isEmpty()) {
								return -1;
							} else {

								list.add(FileTools.upload(files[i]));
								school.setSchoolImage(list.toString().substring(1, list.toString().length() - 1));
							}
						}

						//上传视频			
						if (i == len - 1) {

							if (files[len - 1].isEmpty()) {
								return -1;
							} else {

								String s = FileTools.upload(files[len - 1]);
								school.setSchoolVideo(s);
							}
						}

					}

				}

				//System.out.println(school);
				schoolDAO.insertSchool(school);

				return 1;

			}

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}

	/**
	 * 
	 * @描述：后台,分页显示驾校的list
	 * @作者:黄举飞
	 * @部门：伏守科技项目开发部
	 * @日期： 2016年6月28日  下午5:55:50
	 * @版本： V1.0 
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@Override
	public Page<School> getViewSchoolList(Page<School> page,String searchSchoolName) throws Exception {

		try {
			int total = schoolDAO.getCountSchool();
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

			List<School> schools = schoolDAO.getViewSchoolList(startNuber, pageSize,searchSchoolName);

			page.setT(schools);//放入对象
			page.setTotalNumber(total);//总条数

			return page;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 
	 * @描述：删除一条驾校数据
	 * @作者:黄举飞
	 * @部门：伏守科技项目开发部
	 * @日期： 2016年6月28日  下午5:56:11
	 * @版本： V1.0 
	 * @param schoolId
	 * @return
	 * @throws Exception
	 */
	@Transactional(rollbackFor = RuntimeException.class)
	@Override
	public int deleteSchool(String schoolId) throws Exception {

		try {

			List<SchoolAreaCenter> sac = schoolDAO.selectSchoolAndAreaBySchoolId(schoolId);

			if (sac != null && sac.size() > 0) {
				for (SchoolAreaCenter schoolAreaCenter : sac) {
					//删除中间表
					schoolDAO.deleteSchoolAndAreaById(schoolAreaCenter.getSchool_area_id());

				}
			}

			//删除驾校表
			schoolDAO.deleteSchool(schoolId);

			//删除展示图片		
			School school = schoolDAO.getSchooById(schoolId);

			if (school != null) {
				String strings = school.getSchoolImage();
				FileTools.deleteImage(strings);
			}

			return 1;

		} catch (Exception e) {
			e.printStackTrace();
			return -5;
		}

	}

	@Override
	public School selectSchoolById(String schoolId) throws Exception {
		return schoolDAO.selectSchoolById(schoolId);
	}

	/**
	 * 
	 * @描述：修改学校信息
	 * @作者:黄举飞
	 * @部门：伏守科技项目开发部
	 * @日期： 2016年6月28日  下午5:56:26
	 * @版本： V1.0 
	 * @param newSchool
	 * @param files
	 * @return
	 */
	@Transactional(rollbackFor = RuntimeException.class)
	@Override
	public int updateSchool(School newSchool, MultipartFile[] files, boolean tag) {

		try {

			/*		
			 *  这里注释的是修改logo ,展示图片 和视频 的,现在暂时不用这个
			 * if (files != null) {
			
						List<String> list = new ArrayList<>();
			
						int len = files.length;
			
						for (int i = 0; i < len; i++) {
			
							//上传logo
							if (i == 0) {
			
								if (files[0].isEmpty()) {
									return -1;
								} else {
									//这里相当于直接吧名字设置到了这对象的Logo字段上
									newSchool.setSchoolLogo(FileTools.upload(files[0]));
								}
			
							}
			
							//上传图片	
							if (i != 0 && i != len - 1) {
			
								if (files[i].isEmpty()) {
									return -1;
								} else {
									list.add(FileTools.upload(files[i]));
									newSchool.setSchoolImage(list.toString().substring(1, list.toString().length() - 1));
								}
							}
			
							//上传视频			
							if (i == len - 1) {
			
								if (files[len - 1].isEmpty()) {
									return -1;
								} else {
									newSchool.setSchoolVideo(FileTools.upload(files[len - 1]));
								}
							}
			
						}
			
					}*/

			//得到原有的学校的信息
			School oldSchool = schoolDAO.selectSchoolById(newSchool.getSchoolId());

			if (oldSchool == null) {
				return -2;
			}

			if (tag) {
				//上传图片
				if (files != null) {

					List<String> list = new ArrayList<>();
					int len = files.length;
					for (int i = 0; i < len; i++) {

						if (files[i].isEmpty()) {
							return -1;
						} else {

							list.add(FileTools.upload(files[i]));
							newSchool.setSchoolImage(list.toString().substring(1, list.toString().length() - 1));
						}
					}

					oldSchool.setSchoolImage(newSchool.getSchoolImage());

				} else {
					return -5;
				}

			}

			oldSchool.setContactPerson(newSchool.getContactPerson());//设置联系人
			oldSchool.setContactPhone(newSchool.getContactPhone());//设置联系电话号码
			oldSchool.setDriverInfo(newSchool.getDriverInfo());//设置教练简介
			oldSchool.setSchoolAddress(newSchool.getSchoolAddress());//设置学校的地址
			oldSchool.setSchoolGrade(newSchool.getSchoolGrade());//设置学校的等级
			oldSchool.setSchoolLng(newSchool.getSchoolLng());//设置学校的经度
			oldSchool.setSchoolLat(newSchool.getSchoolLat());//设置学校的纬度

			//-----------------------------------------下面几个赋值和上面注释是一体的-----------------
			//oldSchool.setSchoolLogo(newSchool.getSchoolLogo());//设置学校的Logo
			//oldSchool.setSchoolImage(newSchool.getSchoolImage());//设置学校的展示图片
			//oldSchool.setSchoolVideo(newSchool.getSchoolVideo());//设置学校的视频
			//根据老学校的id,修改学校的信息
			//schoolDAO.updateSchool(oldSchool);
			//----------------------------------------------------------------------------------

			if (tag) {
				//修改图片
				schoolDAO.updateSchoolImage(oldSchool);

			} else {
				//不修改图片
				schoolDAO.updateSchoolNoFiles(oldSchool);
			}

			return 1;

		} catch (Exception e) {
			e.printStackTrace();
			return -5;
		}

	}



}
