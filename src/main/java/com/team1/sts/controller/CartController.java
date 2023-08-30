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

import com.team1.sts.service.CartService;
import com.team1.sts.vo.CartVO;
import com.team1.sts.vo.MemberVO;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	/** 장바구니 목록 보기 **/
	@RequestMapping(value = "/cartList", method = RequestMethod.GET)
	public String cartList(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
	
		if (loginUser == null) {
			return "redirect:/members/login_form.do";
		} else {
			model.addAttribute("cartList",  cartService.getCartListForUser(loginUser));
		}
		return "mypage/cartList";
	}

	/** 장바구니 추가 기능 **/
	@RequestMapping(value = "/cartInsert", method = RequestMethod.POST)
	public String cartInsert(HttpServletRequest request, Model model, @RequestParam int pseq,
			@RequestParam int quantity) {

		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		if (loginUser == null) {
			return "redirect:/members/login_form.do";

		} else {
			// 카트에 추가
			cartService.insertCart(loginUser, pseq, quantity);
			// 아이디의 카트 리스트 보내기
			List<CartVO> cartList = cartService.getCartListForUser(loginUser);
			model.addAttribute("cartList", cartList); 
			// 장바구기 총 액 보내기
			model.addAttribute("totalPrice", cartService.getTotalPrice(cartList));
	
		}
		return "/mypage/cartList";
	}

	/** 장바구니 삭제 기능 **/
	@RequestMapping(value = "/cartDelete", method = RequestMethod.POST)
	public String cartDelete(HttpServletRequest request, Model model, @RequestParam String[] cseq) {
		
		System.out.println(cseq);
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		cartService.deleteCartList(cseq);  // 장바구니에서 삭제 
		
		model.addAttribute("cartList", cartService.getCartListForUser(loginUser));  // 유저의 장바구니 목록 재 출력

		return "/mypage/cartList";
	}

}
