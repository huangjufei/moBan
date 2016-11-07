package cn.jiaxiaoAdmin.util;

public class Image {

	/**
	 * 图片的宽度
	 */
	private int width;
	/**
	 * 图片的高度
	 */
	private int height;/**
	 * 图片的大小
	 */
	private Long size;
	
	
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

	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	
	@Override
	public String toString() {
		return "Image [width=" + width + ", height=" + height + ", size=" + size + "]";
	}
	
	

}
