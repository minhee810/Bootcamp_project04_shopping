package com.team1.sts.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.team1.sts.service.admin.AdminOrderService;
import com.team1.sts.vo.OrderVO;

@Controller("adminOrderController")
@RequestMapping("/adminOrder")
public class AdminOrderController {

	@Autowired
	private AdminOrderService adminOrderService;
	
	/** 관리자 주문 리스트 **/
	@RequestMapping(value = "/admin_order_list", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView adminOrderList(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String url = "admin/order/orderList";
		
		String key = "";
		if (request.getParameter("key") != null) {
			key = request.getParameter("key");
		}
		
		List<OrderVO> orderList = adminOrderService.orderList(key);
		request.setAttribute("orderList", orderList);
		
		ModelAndView mav = new ModelAndView(url);
		return mav;
	}

	/** 주문 목록 하단 주문처리(입금확인) **/
	@RequestMapping(value = "/admin_order_save", method = RequestMethod.POST)
	public ModelAndView adminOrderSave(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String url = "/adminOrder/admin_order_list";
		
		String[] resultArr = request.getParameterValues("result");
		for (String oseq : resultArr) {
			adminOrderService.updateOrderResult(oseq);
		}
		
		ModelAndView mav = new ModelAndView("redirect:"+url);
		return mav;
	}
}
