package com.team1.sts.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.team1.sts.service.MyPageService;
import com.team1.sts.vo.MemberVO;
import com.team1.sts.vo.OrderVO;

@Controller
@RequestMapping("/mypages")
public class MyPageController {

	@Autowired
	private MyPageService mypageService;
	
	@RequestMapping(value="/mypage", method = RequestMethod.GET)
	public String mypage(HttpServletRequest request, Model model){
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if (loginUser == null) {
			return "redirect:/members/login_form";
			
		} else {
		
			List<OrderVO> orderList = new ArrayList<OrderVO>(mypageService.oderList(loginUser));

			model.addAttribute("title", "진행 중인 주문 내역");
			model.addAttribute("orderList", orderList);
		}
		return "/mypage/mypage";
	}
	
}
