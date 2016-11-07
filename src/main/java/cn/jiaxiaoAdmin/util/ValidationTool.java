package cn.jiaxiaoAdmin.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @描述：严重工具
 * @作者： 丁洪星
 * @文件名：ValidationTool.java 
 * @包名：cn.util 
 * @项目名：zhuanquanquan
 * @部门：伏守科技项目开发部
 * @日期： 2015年11月4日 上午11:48:39 
 * @版本： V1.0
 */
public class ValidationTool {
	
	/**
	 * 
	 * @描述：验证一个对象是否为空，为空返回true,否则返回false
	 * @作者:丁洪星 
	 * @部门：伏守科技项目开发部
	 * @日期： 2015年11月4日 上午11:48:56 
	 * @版本： V1.0 
	 * @param object 需要被验证的对象
	 * @return 验证结果true或者false
	 */
	public static boolean isNull(Object object) {
		
		if (object == null) {
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	 * 
	 * @描述：验证一个List<Object>中是否包含空对象 ,lists为空或者包含空对象返回true，不包含返回false
	 * @作者:丁洪星 
	 * @部门：伏守科技项目开发部
	 * @日期： 2015年11月4日 上午11:54:13 
	 * @版本： V1.0 
	 * @param lists 需要被验证的List
	 * @return 验证结果true或者false
	 */
	public static boolean isContainNull(List<Object> lists){
		
		if(lists == null){
			return true;
		}
		
		for (Object object : lists) {
			if(object == null){
				return true;
			}
		}
		return false;
		
	}
	
	/**
	 * 
	 * @描述：验证一个字符串是否为空字符串
	 * @作者:丁洪星 
	 * @部门：伏守科技项目开发部
	 * @日期： 2015年11月4日 上午11:52:34 
	 * @版本： V1.0 
	 * @param str 需要被验证的字符串
	 * @return 验证结果true或者false
	 */
	public static boolean isEmptyString(String str){
		
		if (str == null || "".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	 * 
	 * @描述：验证一个List<String>中是否包含空字符串 ,lists为空或者包含空字符串返回true，不包含返回false
	 * @作者:丁洪星 
	 * @部门：伏守科技项目开发部
	 * @日期： 2015年11月4日 上午11:54:13 
	 * @版本： V1.0 
	 * @param lists 需要被验证的List
	 * @return 验证结果true或者false
	 */
	public static boolean isContainEmptyString(List<String> lists){

		if(lists == null){
			return true;
		}
		
		for (String str : lists) {
			if(str == null || "".equals(str)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @描述：验证一个字符串是否为电话号码格式，例如：18584511342或者023-54720171或者54720171
	 * @作者:丁洪星 
	 * @部门：伏守科技项目开发部
	 * @日期： 2015年11月4日 上午11:58:40 
	 * @版本： V1.0 
	 * @param str 需要被验证的字符串
	 * @return 验证结果true或者false
	 */
	public static boolean isTelephoneOrMobile(String str){
		
		Pattern p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号  
		Pattern p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");  // 验证带区号的  
		Pattern p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");         // 验证没有区号的  
       
        Matcher matcher = null;  
        boolean flag = false;    
        
        if(str.length() > 9) {
        	//可能是带区号的座机号码或者手机号
        	if(str.contains("-")) {
        		//带区号的座机号
        		matcher = p1.matcher(str);  
             	flag = matcher.matches(); 
        	} else {
        		matcher = p.matcher(str);  
             	flag = matcher.matches(); 
        	}
        } else {
        	//没有带区号的
        	matcher = p2.matcher(str);  
         	flag = matcher.matches(); 
        }
        return flag;
	}
	
	/**
	 * 
	 * @描述：验证必须是手机号，例如18584511342
	 * @作者:丁洪星 
	 * @部门：伏守科技项目开发部
	 * @日期： 2015年11月4日 下午12:11:07 
	 * @版本： V1.0 
	 * @param str 需要被验证的字符串
	 * @return 验证结果true或者false
	 */
    public static boolean isMobile(String str) {   
    	
        Pattern pattern = null;  
        Matcher matcher = null;  
        boolean flag = false;   
        pattern = Pattern.compile("^[1][3,5,7,8][0-9]{9}$"); // 验证手机号  
        matcher = pattern.matcher(str);  
        flag = matcher.matches();   
        return flag;  
        
    }  
    
    /**
     * 
     * @描述：电话号码验证，例如：023-54720171或者54720171
     * @作者:丁洪星 
     * @部门：伏守科技项目开发部
     * @日期： 2015年11月4日 下午12:36:31 
     * @版本： V1.0 
     * @param str 需要被验证的字符串
     * @return 验证结果true或者false
     */
    public static boolean isTelephone(String str) {   
    	
        Pattern p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");
        Pattern p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");  
        Matcher matcher = null;  
        boolean flag = false;    
        p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");  // 验证带区号的  
        p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");         // 验证没有区号的  
        if(str.length() > 9) {
        	//带区号的座机号码
        	matcher = p1.matcher(str);  
            flag = matcher.matches();    
        } else {
        	//不带区号的座机号码
        	matcher = p2.matcher(str);  
            flag = matcher.matches();   
        }
        return flag;  
    } 
    
    /**
     * 
     * @描述：邮政编码验证 例如：400065
     * @作者:丁洪星 
     * @部门：伏守科技项目开发部
     * @日期： 2015年11月4日 下午12:40:26 
     * @版本： V1.0 
     * @param str 需要被验证的字符串
     * @return 验证结果true或者false
     */
    public static boolean isPostCode(String str) {  
    	
    	Pattern pattern = Pattern.compile("[1-9]\\d{5}(?!\\d)");
    	boolean flag = false;
    	Matcher matcher = pattern.matcher(str);  
    	flag = matcher.matches(); 
    	return flag;
    	
    } 
    
    /**
     * 
     * @描述：验证邮箱，例如51523362@qq.com
     * @作者:丁洪星 
     * @部门：伏守科技项目开发部
     * @日期： 2015年11月4日 下午12:43:46 
     * @版本： V1.0 
     * @param str 需要被验证的字符串
     * @return 验证结果true或者false
     */
    public static boolean isEmail(String str) {  
    	
    	Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
    	boolean flag = false;
    	Matcher matcher = pattern.matcher(str);  
    	flag = matcher.matches(); 
    	return flag;
    	
    } 
    
    /**
     * 
     * @描述：验证非负整数 正整数和 0
     * @作者:丁洪星 
     * @部门：伏守科技项目开发部
     * @日期： 2015年11月4日 下午12:46:23 
     * @版本： V1.0 
     * @param str 需要被验证的字符串
     * @return 验证结果true或者false
     */
    public static boolean isNonnegativeInteger(String str) {
    	
    	Pattern pattern = Pattern.compile("^\\d+$");
    	boolean flag = false;
    	Matcher matcher = pattern.matcher(str);  
    	flag = matcher.matches(); 
    	return flag;
    	
    }
    
    /**
     * 
     * @描述：验证正整数
     * @作者:丁洪星 
     * @部门：伏守科技项目开发部
     * @日期： 2015年11月4日 下午12:46:23 
     * @版本： V1.0 
     * @param str 需要被验证的字符串
     * @return 验证结果true或者false
     */
    public static boolean isPositiveInteger(String str) {
    	
    	Pattern pattern = Pattern.compile("^[0-9]*[1-9][0-9]*$");
    	boolean flag = false;
    	Matcher matcher = pattern.matcher(str);  
    	flag = matcher.matches(); 
    	return flag;
    	
    }
    
    /**
     * 
     * @描述：验证非负浮点数 正浮点数和 0
     * @作者:丁洪星 
     * @部门：伏守科技项目开发部
     * @日期： 2015年11月4日 下午12:46:23 
     * @版本： V1.0 
     * @param str 需要被验证的字符串
     * @return 验证结果true或者false
     */
    public static boolean isNonnegativeFloat(String str) {
    	
    	Pattern pattern = Pattern.compile("^\\d+(\\.\\d+)?$");
    	boolean flag = false;
    	Matcher matcher = pattern.matcher(str);
    	flag = matcher.matches(); 
    	return flag;
    	
    }
    
    /**
     * 
     * @描述：验证正浮点数
     * @作者:丁洪星 
     * @部门：伏守科技项目开发部
     * @日期： 2015年11月4日 下午12:46:23 
     * @版本： V1.0 
     * @param str 需要被验证的字符串
     * @return 验证结果true或者false
     */
    public static boolean isPositiveFloat(String str) {
    	
    	Pattern pattern = Pattern.compile("^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$");
    	boolean flag = false;
    	Matcher matcher = pattern.matcher(str);  
    	flag = matcher.matches(); 
    	return flag;
    	
    }
    
    public static void main(String[] args) {
		String str = "18584511343";
		System.out.println(isTelephoneOrMobile(str));
	}
    
    
}
