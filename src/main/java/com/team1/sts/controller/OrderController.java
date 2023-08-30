package com.team1.sts.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.team1.sts.service.OrderService;
import com.team1.sts.vo.MemberVO;
import com.team1.sts.vo.OrderVO;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	/** 전체 주문 리스트 **/
	@RequestMapping(value = "/order_all", method = RequestMethod.GET)
	public String orderAll(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
	
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");		
		// 해당 아이디로 조회한 주문 리스크를 orderList에 넣음
		List<OrderVO> orderList = orderService.orderAllList(loginUser.getId());
		
		model.addAttribute("title", "총 주문내역"); 
		model.addAttribute("orderList", orderList);
		
		return "mypage/mypage";
	}

	/** 주문 상세보기 페이지 **/
	@RequestMapping(value = "/order_detail", method = {RequestMethod.POST, RequestMethod.GET})
	public String orderDetail(@RequestParam int oseq, @RequestParam(defaultValue="%") String result, 
								Model model,
								HttpServletRequest request) {
		
		System.out.println("야호 여기는 주문상세페이지 ~~");
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		// 2 - 1 : 주문 리스트
		List<OrderVO> orderList = orderService.orderList(loginUser.getId(), result, oseq);

		// 2 - 2 : 사용자가 주문한 총 가격 산출
		int totalPrice = orderService.orderDetailListTotalPrice(loginUser.getId(), result, oseq);

		model.addAttribute("orderDetail", orderList.get(0));
		model.addAttribute("orderList", orderList);
		model.addAttribute("totalPrice", totalPrice);
		System.out.println("######totalPrice##### : " + totalPrice);
		return "/mypage/orderDetail";
	}
	
	/** 주문 목록 **/
	@RequestMapping(value = "/order_list", method = {RequestMethod.POST, RequestMethod.GET})
	public String orderList(HttpServletRequest request, @RequestParam int oseq, @RequestParam String result, @RequestParam int maxOseq){
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		System.out.println("@@@@@@orderList : "+loginUser);
		// 3 - 1. 주문 리스트 리턴
		orderService.orderList(loginUser.getId(), result, oseq);

		return "/mypage/orderList";
	}
	
	/** 주문 추가하기 **/
	@RequestMapping(value = "/order_insert", method = {RequestMethod.POST, RequestMethod.GET})
	public String orderInsert(HttpServletRequest request, Model model){
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
	
		System.out.println("#### orderInsert() -> loginUser : "+loginUser);
		
		int maxOseq = orderService.InsertOrderAndReturnMaxOseq(loginUser.getId());
		
		System.out.println("maxOseq : " + maxOseq);
		
		return "redirect:/order/order_list";
	}
	
	
}
