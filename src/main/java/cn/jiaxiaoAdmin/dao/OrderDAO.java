package cn.jiaxiaoAdmin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.jiaxiaoAdmin.model.Order;



@Repository
public interface OrderDAO {


	public List<Order> viewOrderList()throws Exception;

	public List<Order> noViewOrderList()throws Exception;
	
	//查询订单所有信息
	public List<Order> viewOrderAll()throws Exception;
	//查询根据总条数
	public int selectOrderCount(Map<String, Object> map) throws Exception;
	//根据不同条件查询订单
	public List<Order> selectOrder(Map<String, Object> map) throws Exception;
}
