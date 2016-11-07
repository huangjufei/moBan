package cn.jiaxiaoAdmin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.jiaxiaoAdmin.model.Order;
import cn.jiaxiaoAdmin.model.Pages;
import cn.jiaxiaoAdmin.service.OrderService;




@Controller
@RequestMapping("/order")
public class AdminOrderController {

	@Resource
	private OrderService orderService;

	
	 /**
     * 
     * @描述：根据不同的查询条件查询订单列表
     * @作者:曾国龙
     * @部门：伏守科技项目开发部
     * @日期： 2015年11月4日 下午4:30:33 
     * @版本： V1.0 
     * @param request
     * @param response
     * @param order 查询条件封装后的实体
     * @param page 分页参数
     * @return
     */
    @RequestMapping("selectOrder")
    public ModelAndView selectOrder(HttpServletRequest request, HttpServletResponse response, Order order,Pages pages) {
    	System.out.println("订单详情查询");
    	ModelAndView view = new ModelAndView();
    	boolean flag = true;
    	if(flag) {
    		//表示该用户已经登录
    		Map<String,Object> map = new HashMap<String, Object>();
    		
        	map.put("orderStatus",order.getOrderStatus()); //订单状态
        	map.put("payWay",order.getPayWay()); //支付方式
    		try {
    			int currentPage = pages.getPage();//当前页码
            	int pageSize = pages.getPagesize();//页大小
            	int totalNums = orderService.selectOrderCount(map); //查询出总的条数
            	int totalPages = totalNums%pageSize==0 ? totalNums/pageSize : totalNums/pageSize + 1;
            	if(currentPage<=0&&totalPages>0) {
            		currentPage = 1;
            	}
            	if(currentPage>totalPages&&totalPages>0) {
            		currentPage = totalPages;
            	}
            	int pageFrom = (currentPage-1)*pageSize;
            	map.put("pageFrom", pageFrom);//起始页
            	map.put("pageSize", pageSize);//页大小
    			List<Order> orderList = orderService.selectOrder(map);//根据不同条件查询订单列表
    			if(orderList==null||orderList.isEmpty()) {
    				view.addObject("totalNums", 0);
    			} else {
    				view.addObject("totalNums", totalNums);
    			}
    			view.addObject("page", pages);
    			view.addObject("queryOrder", order);
    	    	view.addObject("orderList", orderList);
    	    	view.setViewName("admin/orderView");
    		} catch (Exception e) {
    			view.addObject("errorInfo","服务器出错了");
    			view.setViewName("error/500");
    			e.printStackTrace();
    		}
    	}else{
    		//表示该用户未登录
    		view.setViewName("redirect:/admin/login.do");
    	}
    	return view;
    }
}
