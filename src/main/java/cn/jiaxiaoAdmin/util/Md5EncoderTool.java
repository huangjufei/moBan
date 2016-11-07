package cn.jiaxiaoAdmin.util;

import java.security.MessageDigest;

/**
 * 
 * @描述：Md5加密工具类
 * @作者： 丁洪星
 * @文件名：Md5EncoderTool.java 
 * @包名：cn.util 
 * @项目名：zhuanquanquan
 * @部门：伏守科技项目开发部
 * @日期： 2015年11月4日 上午11:41:35 
 * @版本： V1.0
 */
public class Md5EncoderTool {
	
	/*
	 * 16进制数字数组
	 */
	private final static String[] HEX_DIGITS = { "0", "1", "2", "3", "4", "5",
		"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	
	/**
	 * 
	 * @描述：对字符串进行Md5加密算法加密
	 * @作者:丁洪星 
	 * @部门：伏守科技项目开发部
	 * @日期： 2015年11月4日 上午11:41:57 
	 * @版本： V1.0 
	 * @param str 需要被加密的字符串
	 * @return md5Str 加密后的字符串
	 */
	public static String createMd5(String str) {

		String md5Str = encodeByMD5(str);
		return md5Str;
		
	}
	
	/**
	 * 
	 * @描述：对字符串进行Md5加密算法加密
	 * @作者:丁洪星 
	 * @部门：伏守科技项目开发部
	 * @日期： 2015年11月4日 上午11:42:57 
	 * @版本： V1.0 
	 * @param originString 需要被加密的字符串
	 * @return 加密后的字符串
	 */
	private static String encodeByMD5(String originString) {
		if (originString != null) {
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				byte[] results = md.digest(originString.getBytes());
				String resultString = byteArrayToHexString(results);
				return resultString.toUpperCase();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "";
	}
	
	/**
	 * 
	 * @描述：byte数组转换为字符串
	 * @作者:丁洪星 
	 * @部门：伏守科技项目开发部
	 * @日期： 2015年11月4日 上午11:44:38 
	 * @版本： V1.0 
	 * @param bytes byte数组
	 * @return 转换后的字符串
	 */
	private static String byteArrayToHexString(byte[] bytes) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			resultSb.append(byteToHexString(bytes[i]));
		}
		return resultSb.toString();
	}
	
	/**
	 * 
	 * @描述：byte转为16进制
	 * @作者:丁洪星 
	 * @部门：伏守科技项目开发部
	 * @日期： 2015年11月4日 上午11:45:41 
	 * @版本： V1.0 
	 * @param b byte字节
	 * @return 转换后的字符串
	 */
	private static String byteToHexString(byte b) {
		
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return HEX_DIGITS[d1] + HEX_DIGITS[d2];
		
	}
}
