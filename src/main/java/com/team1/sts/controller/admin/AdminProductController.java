package com.team1.sts.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.team1.sts.service.admin.AdminMemberService;
import com.team1.sts.service.admin.AdminProductService;
import com.team1.sts.vo.ProductVO;

@Controller
@RequestMapping("/adminProduct")
public class AdminProductController {

	@Autowired
	AdminMemberService adminMemberService;
	
	@Autowired
	AdminProductService adminProductService;
	
	/** 관리자 상품 목록 페이지 **/
	@RequestMapping(value = "/admin_product_list", method = {RequestMethod.GET, RequestMethod.POST})
	public String adminProductList(@RequestParam(defaultValue="1") String tpage,
								   @RequestParam(defaultValue="") String key,
								   HttpServletRequest request,
								   Model model) throws Exception {
		
		model.addAttribute("tpage", tpage);
		model.addAttribute("key", key);

		List<ProductVO> productList = adminProductService.listProduct(Integer.parseInt(tpage), key);
		String paging = adminProductService.pageNumber(Integer.parseInt(tpage), key, request);

		model.addAttribute("productList", productList);
		
		int n = productList.size();
		model.addAttribute("productListSize", n);
		model.addAttribute("paging", paging);
		
		return "/admin/product/productList";
	}
	
	/** 관리자 상품 등록 (상품 정보 입력) **/
	@RequestMapping(value = "/admin_product_write_form", method = RequestMethod.POST)
	public String adminProductWriteForm(Model model) {
		String kindList[] = { "Heels", "Boots", "Sandals", "Slipers", "Sneakers" };
		model.addAttribute("kindList", kindList);
		return "admin/product/productWrite";
	}
	
	/** 관리자 상품 등록 (입력한 정보 보내기) **/
	@RequestMapping(value = "admin_product_write", method = RequestMethod.POST)
	public String adminProductWrite(HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		
		int sizeLimit = 5 * 1024 * 1024;
		String savePath = "resources/product_images";
		ServletContext context = session.getServletContext();
		String uploadFilePath = context.getRealPath(savePath);
		
		MultipartRequest multi = new MultipartRequest(request,
													  uploadFilePath,
													  sizeLimit,
													  "UTF-8",
													  new DefaultFileRenamePolicy());
		
		adminProductService.insertProduct(multi);
		return "redirect:/adminProduct/admin_product_list";
	}
	
	/** 선택한 상품 정보 상세보기 (하나의 상품에 대해) **/
	@RequestMapping(value = "admin_product_detail", method = RequestMethod.POST)
	public String adminProductDetail(@RequestParam String pseq,
									 @RequestParam(defaultValue="1") String tpage,
									 HttpServletRequest request,
									 Model model) throws IOException {

		String pseq_t = pseq.trim();
		
		ProductVO productVO = adminProductService.getProduct(pseq_t);
		model.addAttribute("productVO", productVO);

		String kindList[] = { "0", "Heels", "Boots", "Sandals", "Slipers", "Sneakers" };
		model.addAttribute("tpage", tpage);
		
		int index = Integer.parseInt(productVO.getKind().trim());
		model.addAttribute("kind", kindList[index]);
		
		return "/admin/product/productDetail";
	}
	
	/** 상품 정보 수정 입력 **/
	@RequestMapping(value = "admin_product_update_form", method = RequestMethod.POST)
	public String adminProductUpdateForm(@RequestParam String pseq,
										@RequestParam(defaultValue="1") String tpage,
										Model model) throws IOException {
		
		String _pseq = pseq.trim();

		String kindList[] = { "Heels", "Boots", "Sandals", "Slipers", "Sneakers" };
		model.addAttribute("productVO", adminProductService.getProduct(_pseq));
		model.addAttribute("tpage", tpage);
		model.addAttribute("kindList", kindList);
		
		return "/admin/product/productUpdate";
	}
	
	/** 수정한 상품 정보 등록 **/
	@RequestMapping(value = "/admin_product_update", method = RequestMethod.POST)
	public String adminProductUpdate(HttpServletRequest request) throws IOException {
		
		HttpSession session = request.getSession();

		int sizeLimit = 5 * 1024 * 1024;
		String savePath = "resources/product_images";
		ServletContext context = session.getServletContext();
		String uploadFilePath = context.getRealPath(savePath);

		MultipartRequest multi = new MultipartRequest(request,
													  uploadFilePath,
													  sizeLimit,
													  "UTF-8",
													  new DefaultFileRenamePolicy());

		adminProductService.updateProduct(multi);
		
		return "redirect:/adminProduct/admin_product_list";
	}
	
	
}
