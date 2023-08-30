package com.team1.sts.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.team1.sts.dao.CartDAO;
import com.team1.sts.dao.OrderDAO;
import com.team1.sts.vo.CartVO;
import com.team1.sts.vo.OrderVO;


@Service("oderService")
@Transactional(propagation = Propagation.REQUIRED)
public class OrderService {
	
	@Autowired
	private CartDAO cartDAO;
	
	@Autowired
	private OrderDAO orderDAO;

	/**1. 총 주문 리스트 출력 **/
	public List<OrderVO> orderAllList(String userId) {

		// 해당 회원의 주문 중인 상품 리스트
		List<Integer> oseqList = orderDAO.selectSeqOrderIng(userId);
		System.out.println("oseqList : " + oseqList);
		List<OrderVO> orderList = new ArrayList<OrderVO>();

		// 주문 진행중인 상품리스트를 반복하며 **외 ##건 + 가격 출력
		for (int oseq : oseqList) {
			List<OrderVO> orderListIng = orderDAO.listOrderById(userId, "%", oseq);

			System.out.println("Service -> orderAllList() : " + orderListIng);
			// 1) **외 ##건 출력
			OrderVO orderVO = orderListIng.get(0);
			orderVO.setPname(orderVO.getPname() + " 외 " + orderListIng.size() + "건");
			
			// 2) 총 가격 출력
			int totalPrice = 0;
			for (OrderVO ovo : orderListIng) {
				totalPrice += ovo.getPrice2() * ovo.getQuantity();
			}
			orderVO.setPrice2(totalPrice);
			orderList.add(orderVO);	
		}
		return orderList;
	}

	/** 2. 주문 상세보기 페이지 -> 사용자 주문 총 가격 산출 **/
	public int orderDetailListTotalPrice(String userId, String result, int oseq) {
		List<OrderVO> orderList = orderDAO.listOrderById(userId, result, oseq); // 사용자의 주문 리스트 생성

		int totalPrice = 0;
		System.out.println(orderList.size());// 사용자의 주문 총 가격 산출
		for (OrderVO ovo : orderList) {
			totalPrice += ovo.getPrice2() * ovo.getQuantity();
		}
		return totalPrice;
	}

	/** 4-1. 주문 리스트**/
	public List<OrderVO> orderList(String userId, String result, int oseq) {
		return orderDAO.listOrderById(userId, result, oseq);
	}

	/** 4-2. 총 가격 리스트**/
	public int orderListTotalPrice(String userId, String result, int oseq) {
		List<OrderVO> orderList = orderDAO.listOrderById(userId, "1", oseq);

		int totalPrice = 0;
		for (OrderVO orderVO : orderList) {
			totalPrice += orderVO.getPrice2() * orderVO.getQuantity();
		}
		return totalPrice;
	}

	/** 5. 주문하기 + 주문 번호 리턴 -> 주문 리스트로 **/
	public int InsertOrderAndReturnMaxOseq(String userId) {
		List<CartVO> cartList = cartDAO.listCart(userId);
		return orderDAO.insertOrder(cartList, userId);
	}

}
