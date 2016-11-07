package cn.jiaxiaoAdmin.model;

import java.io.Serializable;

/**
 * 
 * @描述：订单类
 * @作者:黄举飞
 * @部门：伏守科技项目开发部
 * @日期： 2016年4月26日 上午10:53:34
 * @版本： V1.0
 * @return
 */
public class Order implements Serializable {

	/**
	 * CREATE TABLE tb_order( orderId varchar(50) NOT NULL, -- 订单主键 classId
	 * varchar(50) NOT NULL, -- 选择的具体班型 (索引classId_index) classType varchar(100)
	 * NOT NULL, -- c1或者c2等 className varchar(100) NOT NULL, -- 班级名称 学生,白领, 普通
	 * areaId varchar(50) NOT NULL, -- 选择场地 (索引areaId_index) driverId
	 * varchar(50) NOT NULL, -- 选择教练 (索引driverId_index) userName varchar(50) NOT
	 * NULL, -- 身份证名字 phone varchar(50) NOT NULL, -- 手机号(索引phone_index)
	 * totalPrice decimal(10,2) default 0.00, -- 需要支付的金额 orderTime varchar(100)
	 * NOT NULL, -- 下单时间 strPaytime varchar(50) 支付时间 orderStatus int default 1,
	 * -- 1,等待支付,2支付成功, -1 取消状态 payTime varchar(100) default '', -- 微信支付时间
	 * liushuihao varchar(255) default '', -- 三方支付流水号 payway int 支付方式1 微信,2 支付宝
	 * studentId varchar(50) NOT NULL, -- 这订单属于那个用户(索引studentId_index) PRIMARY
	 * KEY (orderId) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	 */

	private String orderId; // 订单主键
	private String classId;
	private String classType;
	private String className;
	private String areaId;
	private String userName;
	private String phone;
	private double totalPrice;
	private String orderTime;
	private int orderStatus;
	private String payTime;
	private String liushuihao;
	private int payWay;
	private String strPaytime;
	private String areaInfo;//场地信息

	public String getStrPaytime() {
		return strPaytime;
	}

	public void setStrPaytime(String strPaytime) {
		this.strPaytime = strPaytime;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}



	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getPayWay() {
		return payWay;
	}

	public void setPayWay(int payWay) {
		this.payWay = payWay;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getLiushuihao() {
		return liushuihao;
	}

	public void setLiushuihao(String liushuihao) {
		this.liushuihao = liushuihao;
	}

    


	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	public String getAreaInfo() {
		return areaInfo;
	}

	public void setAreaInfo(String areaInfo) {
		this.areaInfo = areaInfo;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", classId=" + classId
				+ ", classType=" + classType + ", className=" + className
				+ ", areaId=" + areaId + ", userName=" + userName + ", phone="
				+ phone + ", totalPrice=" + totalPrice + ", orderTime="
				+ orderTime + ", orderStatus=" + orderStatus + ", payTime="
				+ payTime + ", liushuihao=" + liushuihao + ", payway=" + payWay
				+ ", strPaytime=" + strPaytime + "]";
	}
	

}
