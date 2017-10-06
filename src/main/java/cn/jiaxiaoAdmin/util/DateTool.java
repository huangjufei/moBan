package cn.jiaxiaoAdmin.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @描述：时间日期工具类
 * @作者： 丁洪星
 * @文件名：DateTool.java 
 * @包名：cn.util 
 * @项目名：zhuanquanquan
 * @部门：伏守科技项目开发部
 * @日期： 2015年11月4日 上午10:34:35 
 * @版本： V1.0
 */
public class DateTool {
	
	/**
	 * 命令：按照天增减
	 */
	public static final int DAY = 1;
	/**
	 * 命令：按照月增减
	 */
	public static final int MONDTH = 2;
	
	/**
	 * 
	 * @描述：得到当前系统的时间，例如:2008-08-08 08:08:08
	 * @作者:丁洪星 
	 * @部门：伏守科技项目开发部
	 * @日期： 2015年11月4日 上午10:35:18 
	 * @版本： V1.0 
	 * @return currentTime 时间字符串  例如:2008-08-08 08:08:08
	 */
	public static String getCurrentTime() {
		
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime =format.format(date);		 
	    return currentTime;
	    
	}
	
	/**
	 * 
	 * @描述：得到当前系统的时间，例如:2008-08-08
	 * @作者:丁洪星 
	 * @部门：伏守科技项目开发部
	 * @日期： 2015年11月4日 上午11:15:39 
	 * @版本： V1.0 
	 * @return currentDay 日期字符串  例如:2008-08-08
	 */
	public static String getCurrentDay(){
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String currentDay =format.format(date);		 
	    return currentDay;
	}
	
	/**
	 * 
	 * @描述：根据日期生产文件夹路径字符串，例如:2008/08/08
	 * @作者:丁洪星 
	 * @部门：伏守科技项目开发部
	 * @日期： 2015年11月4日 上午10:36:48 
	 * @版本： V1.0 
	 * @return folderPath 文件夹字符串，例如2008/08/08
	 */
	public static String createFolderByDateNow() {
		
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		String folderPath = format.format(date);
		return folderPath;
		
	}
	
	/**
	 * huangjufe
	 * @描述：把yyyy-MM-dd HH:mm:ss格式的字符串转换为日期格式
	 * @作者:丁洪星 
	 * @部门：伏守科技项目开发部
	 * @日期： 2015年11月4日 上午10:39:04 
	 * @版本： V1.0 
	 * @param dateString 时间字符串，例如2008-08-08 08:08:08
	 * @return date
	 * @throws ParseException 
	 */
	public static Date parseDate(String dateString) throws ParseException {	
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = format.parse(dateString);
		return date;
		
	}
	
	/**
	 * 
	 * @描述：计算日期 根据某个日期 计算多少天以后的日期或者多少月以后的日期
	 * @作者:丁洪星 
	 * @部门：伏守科技项目开发部
	 * @日期： 2015年11月4日 上午10:52:04 
	 * @版本： V1.0 
	 * @param time 要计算的日期原始数据 格式为：0000-00-00
	 * @param type 只能是CalculationDate.DAY或者CalculationDate.MONDTH
	 * @param count  要加或者减的具体数字
	 * @return 经过计算,格式化后的日期 格式为：0000-00-00
	 * @throws Exception type类型错误或者时间字符串转换错误
	 */
	public static String calculationDate(String time, int type, int count) throws Exception  {
		
		String result = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(time);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if (DAY == type) {
			calendar.add(Calendar.DATE, count);
			result = format.format(calendar.getTime());
		} else if (MONDTH == type) {
			calendar.add(Calendar.MONTH, count);
			result = format.format(calendar.getTime());
		} else {
			throw new Exception("操作类型出错，只能是月或者日增减");
		}
		return result;
		
	}
	
	
}
