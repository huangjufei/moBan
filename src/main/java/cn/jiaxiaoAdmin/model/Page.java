package cn.jiaxiaoAdmin.model;

import java.util.List;

/**
 * @ClassName: Page
  * @Description: 分页查询参数对象
  * @author: liupengyan
  * @date: 2016年3月24日 下午2:46:08
 */
public class Page<T> {
	/**
	 * 降序排序
	 */
	public static final String DESC = "DESC";
	/**
	 * 升序排序
	 */
	public static final String ASC = "ASC";
	
	/**
	 * 页码
	 */
	private int pageNum = 1;
	
	/**
	 * 每页显示条数
	 */
	private int pageSize = 20;
	/**
	 * 排序类型DESC ASC
	 */
	private String sortType;
	
	
	/**
	 * 总页数
	 */
	private int totalPage;
	
	
	/**
	 * 总页数
	 */
	private int totalNumber;
	
	

	
	//对象
	private List<T> t;
	

	

	
	public int getTotalNumber() {
		return totalNumber;
	}
	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}
	public List<T> getT() {
		return t;
	}
	public void setT(List<T> t) {
		this.t = t;
	}
	
	
	public int getPageNum() {
		return pageNum;
	}
	
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	public String getSortType() {
		return sortType;
	}
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
}
