package cn.jiaxiaoAdmin.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileTool {

	/**
	 * 图片后缀名
	 */
	public static final String [] SUFFIX_ARR = {"jpg","png"};
	/**
	 * 检查像素比例
	 */
	public static final int CHECK_PIXEL_RATIO = 1;
	/**
	 * 不检查像素
	 */
	public static final int NO_CHCK = -1;
	
	/**
	 * 检查像素尺寸
	 */
	public static final int CHECK_PIXEL_SIZE = 2;
	/**
	 * 图片大小超出限制错误
	 */
	public static final String SIZE_EXCEPTION = "-1";
	/**
	 * 像素比例与限制不匹配错误
	 */
	public static final String PIXEL_RATIO_EXCEPTION = "-2";
	/**
	 * 像素尺寸与限制不匹配错误
	 */
	public static final String PIXEL_SIZE_EXCEPTION = "-3";
	/**
	 * 上传文件不是图片错误
	 */
	public static final String NOT_PIC_EXCEPTION = "-4";
	
	/**
	 * @功能: 不对图片进行任何限制上传
	 * @作者: 丁洪星 
	 * @部门：伏守科技项目开发部
	 * @日期 2015年7月13日 上午11:08:42 
	 * @版本 V1.0 
	 * @param multiRequest
	 * @return
	 * @throws RuntimeException
	 * @throws IOException
	 */
	public String imagUpload(MultipartFile file) throws RuntimeException, IOException{
		
		return imagUpload(file,-1,-1,-1,NO_CHCK);
		
	}
	
	/**
	 * @功能: 限制图片大小上传
	 * @作者: 丁洪星 
	 * @部门：伏守科技项目开发部
	 * @日期 2015年7月13日 上午11:45:44 
	 * @版本 V1.0 
	 * @param request
	 * @param size 字节数
	 * @return
	 * @throws RuntimeException
	 * @throws IOException
	 */
	public String imagUpload(MultipartFile file,int size) throws RuntimeException, IOException{
		
		return imagUpload(file,-1,-1,size,NO_CHCK);
		
	}
	
	/**
	 * @功能: 限制像素比例上传（按照像素比例上传checkType=CHECK_PIXEL_RATIO,按照像素尺寸上传checkType=CHECK_PIXEL_SIZE），不限制图片大小
	 * @作者: 丁洪星 
	 * @部门：伏守科技项目开发部
	 * @日期 2015年7月13日 上午11:48:10 
	 * @版本 V1.0 
	 * @param file
	 * @param width
	 * @param height
	 * @param checktype
	 * @return
	 * @throws RuntimeException
	 * @throws IOException
	 */
	public String imagUpload(MultipartFile file,int width,int height,int checktype) throws RuntimeException, IOException {
		
		return imagUpload(file,width,height,-1,checktype);
		
	}
	
	/**
	 * 
	 * @描述：根据file还原文件
	 * @作者:丁洪星 
	 * @部门：伏守科技项目开发部
	 * @日期： 2015年11月12日 上午9:55:41 
	 * @版本： V1.0 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public ImageInfo getImageInfo(MultipartFile file) throws IOException {
		
		BufferedImage sourceImg = ImageIO.read(file.getInputStream());
		int imageWidth = sourceImg.getWidth();
		int imageHeight = sourceImg.getHeight();
		long size = file.getSize();
		ImageInfo imageInfo = new ImageInfo();
		imageInfo.setHeight(imageHeight);
		imageInfo.setWidth(imageWidth);
		imageInfo.setSize(size);
		return imageInfo;
		
	}
	
	
	/**
	 * @功能: 限制图片大小或者像素比例上传（按照像素比例上传checkType=CHECK_PIXEL_RATIO,按照像素尺寸上传checkType=CHECK_PIXEL_SIZE）
	 * @作者: 丁洪星 
	 * @部门：伏守科技项目开发部
	 * @日期 2015年7月13日 上午11:46:17 
	 * @版本 V1.0 
	 * @param file
	 * @param width
	 * @param height
	 * @param size 字节数
	 * @param checktype
	 * @return
	 * @throws RuntimeException
	 * @throws IOException
	 */
	public String imagUpload(MultipartFile file,int width,int height,int size,int checktype) throws RuntimeException,IOException {
		
		String fileName = file.getOriginalFilename().trim();
		boolean flag = checkIsImage(fileName,file);
		if(flag) {
			ImageInfo imageInfo = getImageInfo(file);
			if(checktype==CHECK_PIXEL_RATIO){
				//按像素比例检查上传的图片
				if(size==-1){
					//不限制图片大小，只限制比例
					String uploadPicRatio = new BigDecimal(imageInfo.getWidth()).divide(new BigDecimal(imageInfo.getHeight()),8,BigDecimal.ROUND_HALF_UP).toString();
					String picRatio = new BigDecimal(width).divide(new BigDecimal(height),8,BigDecimal.ROUND_HALF_UP).toString();
					if(uploadPicRatio.equals(picRatio)) {
						return upload(file);
					} else {
						return PIXEL_RATIO_EXCEPTION;
					}
				}else{
					// 检测文件大小  和 width  height 比例检测
					if(imageInfo.getSize()>size) {
						return SIZE_EXCEPTION;
					} else {
						String uploadPicRatio = new BigDecimal(imageInfo.getWidth()).divide(new BigDecimal(imageInfo.getHeight()),8,BigDecimal.ROUND_HALF_UP).toString();
						String picRatio = new BigDecimal(width).divide(new BigDecimal(height),8,BigDecimal.ROUND_HALF_UP).toString();
						if(uploadPicRatio.equals(picRatio)) {
							return upload(file);
						} else {
							return PIXEL_RATIO_EXCEPTION;
						}
					}
				}
			}else if(checktype==CHECK_PIXEL_SIZE){
				//按像素尺寸检查上传的图片
				if(size==-1){
					//不限制图片大小，只限像素尺寸
					if(imageInfo.getWidth()==width && imageInfo.getHeight()==height) {
						return upload(file);
					} else {
						return PIXEL_SIZE_EXCEPTION;
					}
				}else{
					// 检测文件大小  和 width  height 像素检测
					if(imageInfo.getSize()>size) {
						return SIZE_EXCEPTION;
					} else {
						if(imageInfo.getWidth()==width && imageInfo.getHeight()==height) {
							return upload(file);
						} else {
							return PIXEL_SIZE_EXCEPTION;
						}
					}
				}
			}else{
				if(size==-1){
					return upload(file);
				}else{
					//检测文件大小
					if(imageInfo.getSize()>size) {
						return SIZE_EXCEPTION;
					} else {
						return upload(file);
					}
				} 
			}
		} else {
			//该文件不是图片
			return NOT_PIC_EXCEPTION;
		}
	}
	
	/**
	 * @功能: 普通文件上传
	 * @作者: 丁洪星 
	 * @部门：伏守科技项目开发部
	 * @日期 2015年7月13日 上午11:08:26 
	 * @版本 V1.0 
	 * @param multiRequest
	 * @return
	 * @throws RuntimeException
	 * @throws IOException
	 */
	public String fileUpload(MultipartFile file) throws RuntimeException, IOException{
		return upload(file);
	}
	
	/**
	 * 
	 * @描述：文件上传到服务器
	 * @作者:丁洪星 
	 * @部门：伏守科技项目开发部
	 * @日期： 2015年11月12日 上午9:50:25 
	 * @版本： V1.0 
	 * @param file
	 * @return
	 * @throws RuntimeException
	 * @throws IOException
	 */
	private String upload(MultipartFile file) throws RuntimeException, IOException {
		
		String fileName = file.getOriginalFilename();
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		String randomString = DataTool.generatPrimaryKey();
		String sourceFilePath = DateTool.createFolderByDateNow() + "/" + randomString + "." + suffix;
		String path=StaticConstants.FILE_UPLOAD_URL + sourceFilePath; 
		File fileFolder = new File(path); 
		if(!fileFolder.exists()) 
			fileFolder.mkdirs();
		file.transferTo(fileFolder);
		return sourceFilePath;
		
	}
	
	/**
	 * 
	 * @描述：检查上传文件是否为指定的类型
	 * @作者:丁洪星 
	 * @部门：伏守科技项目开发部
	 * @日期： 2015年11月12日 上午9:54:26 
	 * @版本： V1.0 
	 * @param fileName
	 * @param file
	 * @return
	 * @throws IOException
	 */
	private boolean checkIsImage(String fileName,MultipartFile file) throws IOException {
		BufferedImage sourceImg = ImageIO.read(file.getInputStream());
		if(sourceImg==null){
			return false;
		}
		//默认不是图片
		boolean flag = false;
		String suffixName = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		for(String suffix : SUFFIX_ARR) {
			if(suffix.equals(suffixName)) {
				flag = true;
				break;
			} else {
				continue;
			}
		}
		return flag;
	}
}



/**
 * 图片对象
 */
class ImageInfo{
	/**
	 * 图片宽
	 */
	private int width=0;
	/**
	 * 图片高
	 */
	private int height=0;
	/**
	 * 图片大小
	 */
	private long size=0;
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
}
