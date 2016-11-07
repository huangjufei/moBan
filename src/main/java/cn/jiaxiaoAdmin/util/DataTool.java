package cn.jiaxiaoAdmin.util;

import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

/**
 * 
 * @描述：数据工具类
 * @作者： 丁洪星
 * @文件名：DataTool.java 
 * @包名：cn.util 
 * @项目名：zhuanquanquan
 * @部门：伏守科技项目开发部
 * @日期： 2015年11月4日 上午11:10:22 
 * @版本： V1.0
 */
public class DataTool {
	
	/**
	 * 数字字符
	 */
	public static final String NUMBER_CHAR = "0123456789";
	/**
	 * 数字加大小写字母
	 */
	public static final String ALL_CHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	/**
	 * 大小写字母
	 */
	public static final String LETTER_CHAR = "abcdefghijkllmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	/**
	 * 
	 * @描述：把字符串去掉前后空格,如果传入str为null，则返回空字符串
	 * @作者:丁洪星 
	 * @部门：伏守科技项目开发部
	 * @日期： 2015年11月4日 下午4:41:58 
	 * @版本： V1.0 
	 * @param str 需要被去空格的字符串
	 * @return
	 */
	public static String trimStr(String str) {
		
		if(str == null) {
			return "";
		} else {
			return str.trim();
		}
		
	}
	
	/**
	 * 
	 * @描述：生成数据库主键
	 * @作者:丁洪星 
	 * @部门：伏守科技项目开发部
	 * @日期： 2015年11月4日 上午11:11:26 
	 * @版本： V1.0 
	 * @return primaryKey
	 */
	public static String generatPrimaryKey() {
		
		UUID uuid = UUID.randomUUID();
		String primaryKey = uuid.toString().replaceAll("-", "");
		return primaryKey;
		
	}
	
	/**
	 * 
	 * @描述：生成指定位数的随机数（只包含数字）
	 * @作者:丁洪星 
	 * @部门：伏守科技项目开发部
	 * @日期： 2015年11月4日 下午1:41:39 
	 * @版本： V1.0 
	 * @param length 指定的位数
	 * @return 生成的随机数
	 */
	public static String generatRandomDigit(int length) {
		
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(NUMBER_CHAR.charAt(random.nextInt(NUMBER_CHAR.length())));
		}
		return sb.toString();	
		
	}
	
	/**
	 * 
	 * @描述：生成指定位数的随机数(只包含大小写字母)
	 * @作者:丁洪星 
	 * @部门：伏守科技项目开发部
	 * @日期： 2015年11月4日 下午1:41:39 
	 * @版本： V1.0 
	 * @param length 指定的位数
	 * @return 生成的随机数
	 */
	public static String generatRandomLetter(int length) {
		
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(LETTER_CHAR.charAt(random.nextInt(LETTER_CHAR.length())));
		}
		return sb.toString();	
		
	}
	
	/**
	 * 
	 * @描述：生成指定位数的随机数(只包含数字或者大小写字母)
	 * @作者:丁洪星 
	 * @部门：伏守科技项目开发部
	 * @日期： 2015年11月4日 下午1:41:39 
	 * @版本： V1.0 
	 * @param length 指定的位数
	 * @return 生成的随机数
	 */
	public static String generatRandomLetterDigit(int length) {
		
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(ALL_CHAR.charAt(random.nextInt(ALL_CHAR.length())));
		}
		return sb.toString();	
	}
	
	/**
	 * 
	 * @描述：随机生成字符串
	 * @作者:丁洪星 
	 * @部门：伏守科技项目开发部
	 * @日期： 2015年11月4日 下午1:41:39 
	 * @版本： V1.0 
	 * @param length 指定的位数
	 * @return 生成的随机数
	 */
	public static String generatRandomStringByLength(int length) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
		 
	/**
	 * 
	 * @描述：把map转化为xml字符串
	 * @作者:丁洪星 
	 * @部门：伏守科技项目开发部
	 * @日期： 2015年11月4日 下午1:41:39 
	 * @版本： V1.0 
	 * @param map
	 */
	public static String mapToXml(Map<String,Object> map) {
		StringBuffer str=new StringBuffer("<xml>");
		//循环这个map
		for (Map.Entry<String, Object> entry : map.entrySet()) {  
			str.append("<"+entry.getKey()+">"+entry.getValue()+"</"+entry.getKey()+">");
		}  
		str.append("</xml>");
		return str.toString();
	}
	
	/**
	 * 
	 * @描述：把xml转化为map字符串
	 * @作者:丁洪星 
	 * @部门：伏守科技项目开发部
	 * @日期： 2015年11月4日 下午1:41:39 
	 * @版本： V1.0 
	 * @param map
	 */
	 public static Map<String,Object> xmlElements(String xmlDoc)throws Exception {
		 //创建一个新的字符串
		 StringReader read = new StringReader(xmlDoc);
	        //创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
	        InputSource source = new InputSource(read);
	        //创建一个新的SAXBuilder
	        SAXBuilder sb = new SAXBuilder();
	        Map<String,Object> map=new HashMap<String, Object>();
	            //通过输入源构造一个Document
	            Document doc = sb.build(source);
	            //取的根元素
	            Element root = doc.getRootElement();
	            //System.out.println(root.getName());//输出根元素的名称（测试）
	            //得到根元素所有子元素的集合
	            @SuppressWarnings("rawtypes")
	            List jiedian = root.getChildren();
	            
	            //获得XML中的命名空间（XML中未定义可不写）
	            Element et = null;
	            for(int i=0;i<jiedian.size();i++){
	                et = (Element) jiedian.get(i);//循环依次得到子元素
	               // System.out.println(et.getName()+":"+et.getValue());
	                map.put(et.getName(), et.getValue());
	            }
	           
	            return map;

	 }
	
}
