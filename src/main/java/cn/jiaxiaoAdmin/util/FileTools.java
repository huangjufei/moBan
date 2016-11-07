package cn.jiaxiaoAdmin.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


/**
 *  对上传的文件进行判断工具类
 *  如 :图片,视屏的大小,高宽,后缀名,图片个数等
 * @author Administrator
 *
 */
@Component
public final class FileTools {
	
	/**
	 * 图片支持的后缀名
	 */
	public static final String [] SUFFIX_ARR = {"jpg","png"};
	
	/**
	 * 图片大小不能大于1M
	 */
	public static final int IMAGESIZE = 1024000;

	/**
	 * 图片的宽度
	 */
	public static final int IMAGEWIDTH = 2000;
	/**
	 * 图片的高度
	 */
	public static final int IMAGEHEIGHT = 700;

	/**
	 * 多张图片上传数量不能超过5张
	 */
	public static final int IMAGENUMBER = 5;
	
	/**
	 * 视屏不能大于10M
	 */
	public static final int VIDEOSIZE = 10240000;
	

	/**
	 * Logo大小不能大于500Kb
	 */
	public static final int LOGOSIZE = 500000;

	/**
	 * Logo图片的宽度
	 */
	public static final int LOGOWIDTH = 300;
	/**
	 * Logo图片的高度
	 */
	public static final int LOGOHEIGHT = 300;
	

	/**
	 * 图片根路径
	 */
	public static final String IMAGE_ROOT_PATH = "http://www.515xueche.com:88/picpath/";
	
	/**
	 * 图片上传路径
	 */
	public static final String FILE_UPLOAD_URL = "/mnt/picpath/";
	
	
	
	
	
	
	
	/**
	 * 
	 * @描述：读取上传的文件的基本信息
	 * @作者:黄举飞
	 * @部门：伏守科技项目开发部
	 * @日期： 2016年6月22日  上午10:38:13
	 * @版本： V1.0 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static Image readImageInfo(MultipartFile file) throws Exception {

		Image image = new Image();

		if (file != null) {
			InputStream in = file.getInputStream();
			BufferedImage input = ImageIO.read(in);
			image.setHeight(input.getHeight());
			image.setWidth(input.getWidth());
			image.setSize(file.getSize());
			return image;
		}
		return image;

	}
	
	
	/**
	 * @描述：判断后缀明是否符合要求
	 * @作者:黄举飞
	 * @部门：伏守科技项目开发部
	 * @日期： 2016年6月22日  上午10:37:22
	 * @版本： V1.0 
	 * @param file
	 * @return 合法返回true
	 */
	public static boolean validateSuffix(MultipartFile file){		
		String originName =  file.getOriginalFilename();	
		
		boolean flag = false;

		for (String nowName : SUFFIX_ARR) {
			 flag = nowName.equalsIgnoreCase(originName.substring(originName.lastIndexOf(".")+1, originName.length()));
			 if(flag){
				 
				 return true;	 
			 }						
		}	
		return flag;		
	}
	
	
	/**
	 * @描述：比较上传文件的大小是否超出了规定
	 * @作者:黄举飞
	 * @部门：伏守科技项目开发部
	 * @日期： 2016年6月22日  上午10:41:48
	 * @版本： V1.0 
	 * @param image
	 * @return 合法返回true
	 */
	public static boolean validateSize(Image image){
		
		if(image.getSize() <= IMAGESIZE){
			return true;
		}
		return false;
		
	}
	
	
	/**
	 * @描述：比较上传的Logo大小是否超出了规定
	 * @作者:黄举飞
	 * @部门：伏守科技项目开发部
	 * @日期： 2016年6月22日  上午10:41:48
	 * @版本： V1.0 
	 * @param image
	 * @return 合法返回true
	 */
	public static boolean validateLogoSize(Image image){
		
		if(image.getSize() <= LOGOSIZE){
			return true;
		}
		return false;
		
	}
	
	
	/**
	 * 
	 * @描述：检查多张上传数量不能超过规定数量
	 * @作者:黄举飞
	 * @部门：伏守科技项目开发部
	 * @日期： 2016年6月22日  上午10:58:57
	 * @版本： V1.0 
	 * @param i
	 * @return 合法返回true
	 */
	public static boolean validateImageNumber(int i){
		
		if(i <= IMAGENUMBER){
			return true;
		}
		return false;
		
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
	public static String upload(MultipartFile file) throws RuntimeException, IOException {

		String fileName = file.getOriginalFilename();
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		String randomString = DataTool.generatPrimaryKey();
		String sourceFilePath = DateTool.createFolderByDateNow() + "/" + randomString + "." + suffix;
		String path = FILE_UPLOAD_URL + sourceFilePath;
		File fileFolder = new File(path);
		if (!fileFolder.exists())
			fileFolder.mkdirs();
		file.transferTo(fileFolder);
		return sourceFilePath;

	}
	
	
	
	
	
	/**
	 * @描述：删除文件
	 * @作者:黄举飞
	 * @部门：伏守科技项目开发部
	 * @日期： 2016年6月29日  上午11:16:25
	 * @版本： V1.0 
	 * @param strings
	 */
	public static void deleteImage(String strings){
		
		if (!StringTools.isEmpty(strings)) {
			String [] images = strings.split(",");

			if (images.length > 0) {
				for (String image : images) {

					String realPath = FileTools.FILE_UPLOAD_URL + image.trim();

					File file = new File(realPath);
					
					if (file.exists()) {
						file.delete();
					}

				}
			}
	}
	
	
	
	}
}
	
	

















