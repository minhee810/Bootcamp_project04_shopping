package com.team1.sts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.team1.sts.service.ProductService;
import com.team1.sts.vo.ProductVO;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/viewItem", method = RequestMethod.GET)
    public String viewItem(Model model) {
        List<ProductVO> newProductList = productService.listNewProduct();
        List<ProductVO> bestProductList = productService.listBestProduct();

        model.addAttribute("bestProductList", bestProductList);
        model.addAttribute("newProductList", newProductList);

        return "/index"; // 뷰 이름
    }
	
	/** 상품 상세보기 */
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(@RequestParam String pseq, Model model) {
		model.addAttribute("productList", productService.productDetail(pseq));
		return "/product/productDetail";
	}

	/** category 클릭 시 나올 상품 정보 조회 **/
	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public String category(@RequestParam String kind, Model model) {
		model.addAttribute("productKindList", productService.productKind(kind));
		return "/product/productKind";
	}

}
