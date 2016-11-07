package cn.jiaxiaoAdmin.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jiaxiaoAdmin.dao.OrderDAO;
import cn.jiaxiaoAdmin.model.Order;
import cn.jiaxiaoAdmin.service.OrderService;




@Transactional
@Service
public class OrderServiceImpl implements OrderService {
	
	@Resource
	private OrderDAO orderDAO;

	@Override
	public List<Order> viewOrderList()throws Exception {
		return orderDAO.viewOrderList();
	}

	@Override
	public List<Order> noViewOrderList() throws Exception {
		return orderDAO.noViewOrderList();
	}

	//查询所有订单信息
	public List<Order> viewOrderAll() throws Exception {
		// TODO Auto-generated method stub
		return orderDAO.viewOrderAll();
	}


	//根据订单状态条件查询订单信息
	public int selectOrderCount(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return orderDAO.selectOrderCount(map);
	}

    //根据条件查询订单列表
	public List<Order> selectOrder(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return orderDAO.selectOrder(map);
	}


}
