package com.team1.sts.service.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oreilly.servlet.MultipartRequest;
import com.team1.sts.dao.ProductDAO;
import com.team1.sts.vo.ProductVO;

@Service
public class AdminProductService {

	@Autowired
	ProductDAO productDAO;
	
	public List<ProductVO> listProduct(int parseInt, String key) {
		return productDAO.listProduct(parseInt, key);
	}
	
	public ProductVO getProduct(String pseq) {
		return productDAO.getProduct(pseq);
	}
	
	public String pageNumber(int parseInt, String key, HttpServletRequest request) {
		return productDAO.pageNumber(parseInt, key, request);
	}
	
	public void insertProduct(MultipartRequest multi) {
		ProductVO productVO = new ProductVO();
		
		productVO.setKind(multi.getParameter("kind"));
		productVO.setName(multi.getParameter("name"));
		productVO.setPrice1(Integer.parseInt(multi.getParameter("price1")));
		productVO.setPrice2(Integer.parseInt(multi.getParameter("price2")));
		productVO.setPrice3(
				Integer.parseInt(multi.getParameter("price2")) - Integer.parseInt(multi.getParameter("price1")));
		productVO.setContent(multi.getParameter("content"));
		productVO.setImage(multi.getFilesystemName("image"));
		
		if ((multi.getParameter("bestyn") == null))
			productVO.setBestyn("n");
		else
			productVO.setBestyn(multi.getParameter("bestyn"));			
	
		if ((multi.getParameter("useyn") == null))
			productVO.setUseyn("n");
		else
			productVO.setUseyn(multi.getParameter("useyn"));			

		productDAO.insertProduct(productVO);
	}
	
	public int updateProduct(MultipartRequest multi) {
		ProductVO productVO = new ProductVO();
		
		productVO.setPseq(Integer.parseInt(multi.getParameter("pseq")));
		productVO.setKind(multi.getParameter("kind"));
		productVO.setName(multi.getParameter("name"));
		productVO.setPrice1(Integer.parseInt(multi.getParameter("price1")));
		productVO.setPrice2(Integer.parseInt(multi.getParameter("price2")));
		productVO.setPrice3(
				Integer.parseInt(multi.getParameter("price2")) - Integer.parseInt(multi.getParameter("price1")));
		productVO.setContent(multi.getParameter("content"));
		
		if(multi.getFilesystemName("image") == null)
			productVO.setImage(multi.getParameter("nonmakeImg"));
		else
			productVO.setImage(multi.getFilesystemName("image"));
		
		if ((multi.getParameter("bestyn") == null))
			productVO.setBestyn("n");
		else
			productVO.setBestyn(multi.getParameter("bestyn"));			
	
		if ((multi.getParameter("useyn") == null))
			productVO.setUseyn("n");
		else
			productVO.setUseyn(multi.getParameter("useyn"));	
		
		return productDAO.updateProduct(productVO);
	}
	
	
	
}
