package com.team1.sts.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team1.sts.dao.CartDAO;
import com.team1.sts.dao.ProductDAO;
import com.team1.sts.vo.CartVO;
import com.team1.sts.vo.MemberVO;
import com.team1.sts.vo.ProductVO;

@Service("cartService")
public class CartService {

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private CartDAO cartDAO;

	public void home(HttpServletRequest request) {
		List<ProductVO> newProductList = productDAO.listNewProduct();
		List<ProductVO> bestProductList = productDAO.listBestProduct();

		request.setAttribute("newProductList", newProductList);
		request.setAttribute("bestProductList", bestProductList);
	}

	/** 장바구니 목록 보기 **/
	public List<CartVO> getCartListForUser(MemberVO loginUser) {
		return cartDAO.listCart(loginUser.getId());
	}

	public int getTotalPrice(List<CartVO> cartList) {
		int totalPrice = 0;
		for (CartVO cartVO : cartList) {
			totalPrice += cartVO.getPrice2() * cartVO.getQuantity();
		}
		return totalPrice;
	}

	/** 장바구니 추가 기능 **/
	public void insertCart(MemberVO user, int pseq, int quantity) {
		CartVO cartVO = new CartVO();
		cartVO.setId(user.getId());
		cartVO.setPseq(pseq);
		cartVO.setQuantity(quantity);

		cartDAO.insertCart(cartVO);
	}

	/** 장바구니 삭제 기능 **/
	public void deleteCartList(String[] cseqArr) {
		for (String cseq : cseqArr) {
			cartDAO.deleteCart(Integer.parseInt(cseq));
		}

	}

	public List<CartVO> listCart(String id) {
		return cartDAO.listCart(id);
	}

}
