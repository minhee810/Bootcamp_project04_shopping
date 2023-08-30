package com.team1.sts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team1.sts.dao.ProductDAO;
import com.team1.sts.vo.ProductVO;

@Service
public class ProductService {
	
	@Autowired
	ProductDAO productDAO;
	
	public List<ProductVO> listNewProduct() {
		return productDAO.listNewProduct();
	}

	public List<ProductVO> listBestProduct() {
		return productDAO.listBestProduct();
	}

	// 상품 상세보기 (Detail)
	public ProductVO productDetail(String pseq) {
		return productDAO.getProduct(pseq);
	}

	// category click
	public List<ProductVO> productKind(String kind) {
		return productDAO.listKindProduct(kind);
	}
	
	public List<ProductVO> listProduct(int parseInt, String key) {
		return productDAO.listProduct(parseInt, key);
	}
	
	

}
