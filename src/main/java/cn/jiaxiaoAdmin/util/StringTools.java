package cn.jiaxiaoAdmin.util;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTools {
	


	public static final boolean isEmpty(String str) {
    	
    	if (str == null || "".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
    	
    }
    
    
    
    
    
	public static final String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	

    
	
	
	/**
	 * 
	 * @描述：驾校项目的mark值检测
	 * @作者:黄举飞
	 * @部门：伏守科技项目开发部
	 * @日期： 2016年6月25日  下午8:03:31
	 * @版本： V1.0 
	 * @param str
	 * @return
	 */
	public static final boolean validateMark(String str){
		Pattern p = Pattern.compile("1|2|3|1,2|2,3|1,3|1,2,3");
		Matcher m = p.matcher(str);
		boolean tag = false;
		tag = m.matches();
		return tag;	
	}
	
	
	/**
	 * 
	 * @描述：判断年龄
	 * @作者:黄举飞
	 * @部门：伏守科技项目开发部
	 * @日期： 2016年6月27日  上午11:49:03
	 * @版本： V1.0 
	 * @param str
	 * @return
	 */
	public static final boolean validateAge(String str){
		Pattern r = Pattern.compile("^([0-9]|[0-9]{2})$");
		Matcher m = r.matcher(str);
	
		boolean tag = false;
		tag = m.matches();
		return tag;	
	}
	
	


	/**
	 * 
	 * @描述：判断教练能教授那些科目,格式是否正确
	 * @作者:黄举飞
	 * @部门：伏守科技项目开发部
	 * @日期： 2016年6月27日  下午12:04:54
	 * @版本： V1.0 
	 * @param str
	 * @return
	 */
	public static final boolean validateSub(String str){
		Pattern r = Pattern.compile("c11|c12|c13|c14|c21|c22|c23|c24");
		Matcher m = r.matcher(str);
	
		boolean tag = false;
		tag = m.matches();
		return tag;	
	}
	
	
	
	
	
    
    
    

}
