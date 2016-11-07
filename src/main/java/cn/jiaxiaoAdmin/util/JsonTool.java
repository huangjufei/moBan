package cn.jiaxiaoAdmin.util;
import java.lang.reflect.Type;

import com.google.gson.Gson;

/**
 * 
 * @描述：Json工具类
 * @作者： 丁洪星
 * @文件名：JsonTool.java 
 * @包名：cn.util 
 * @项目名：zhuanquanquan
 * @部门：伏守科技项目开发部
 * @日期： 2015年11月4日 上午11:29:23 
 * @版本： V1.0
 */
public class JsonTool {
	
	public static final Gson gson = new Gson();
	
	/**
	 * 
	 * @描述：把对象转化为JSON字符串
	 * @作者:丁洪星 
	 * @部门：伏守科技项目开发部
	 * @日期： 2015年11月4日 上午11:29:53 
	 * @版本： V1.0 
	 * @param object 需要被转化为JSON的对象
	 * @return JSON字符串
	 */
    public static String toJson(Object object) {   
    	
        Gson gson = new Gson();  
        return gson.toJson(object);   
        
    }   
	  
    /**
     * 
     * @描述：把JSON串转化为对应的对象
     * @作者:丁洪星 
     * @部门：伏守科技项目开发部
     * @日期： 2015年11月4日 上午11:30:57 
     * @版本： V1.0 
     * @param jsonString JSON串
     * @param type
     * @return 反序列化后的对象
     */
    public static <T> T fromJson(String jsonString, Type type) throws Exception {   
    	
       Gson gson = new Gson();   
       return gson.fromJson(jsonString, type);   
       
    }   
	 
    /**
     * 
     * @描述：
     * @作者:丁洪星 
     * @部门：伏守科技项目开发部
     * @日期： 2015年11月4日 上午11:32:32 
     * @版本： V1.0 
     * @param jsonString JSON串
     * @param type
     * @return 反序列化后的对象
     */
	public static <T> T fromJson(String jsonString, Class<T> type) throws Exception { 
		
        Gson gson = new Gson();   
        return gson.fromJson(jsonString, type);   
        
	}   
	

}
