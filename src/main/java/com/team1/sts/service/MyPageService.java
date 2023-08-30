package com.team1.sts.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team1.sts.dao.OrderDAO;
import com.team1.sts.vo.MemberVO;
import com.team1.sts.vo.OrderVO;

@Service("mypageService")
public class MyPageService {
	
	@Autowired
	private OrderDAO orderDAO;

	public List<OrderVO> oderList(MemberVO loginUser) {
		
		
		List<Integer> oseqList = orderDAO.selectSeqOrderIng(loginUser.getId());

		 List<OrderVO> orderList = new ArrayList<OrderVO>();

		for (int oseq : oseqList) {
			List<OrderVO> orderListIng = orderDAO.listOrderById(loginUser.getId(), "1", oseq);

			OrderVO orderVO = orderListIng.get(0);
			orderVO.setPname(orderVO.getPname() + " 외 " + orderListIng.size() + "건");

			int totalPrice = 0;
			for (OrderVO ovo : orderListIng) {
				totalPrice += ovo.getPrice2() * ovo.getQuantity();
			}
			orderVO.setPrice2(totalPrice);
			orderList.add(orderVO);
		}
		return orderList;
	}

}
