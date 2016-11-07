package cn.jiaxiaoAdmin.model;

/**
 * 
 * @描述：关于分页和排序的参数组合对象
 * @作者： 丁洪星
 * @文件名：Page.java 
 * @包名：cn.zhuanquanquan.model 
 * @项目名：zhuanquanquan
 * @部门：伏守科技项目开发部
 * @日期： 2015年11月17日 上午9:45:10 
 * @版本： V1.0
 */
public class Pages {
	/**
	 * 每页展示数据的条数
	 */
	private int pagesize = 10;
	/**
	 * 当前页码值
	 */
	private int page = 1;

	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
}
