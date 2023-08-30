package com.team1.sts.service.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team1.sts.dao.OrderDAO;
import com.team1.sts.vo.OrderVO;

@Service("adminOrderService")
public class AdminOrderService {
	
	@Autowired
	private final OrderDAO orderDAO;
	@Autowired
	public AdminOrderService(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}
	
	// 관리자페이지 주문 리스트
	public List orderList(String key) {
		List<OrderVO> orderList = null;
		if(key==""){
			orderList = orderDAO.listOrderAll();
		}else{
			orderList = orderDAO.listOrder(key);
			
		}
		return orderList;
	}
	
	// 관리자페이지 주문 처리
	public void updateOrderResult(String oseq) {
		orderDAO.updateOrderResult(oseq);
	}
	
}
