package com.team1.sts.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.team1.sts.service.admin.AdminMemberService;


@Controller
@RequestMapping("/adminMembers")
public class AdminMemberController {
	
	@Autowired
	AdminMemberService adminMemberService;
	
	/** 관리자 로그인 입력 페이지 **/
	@RequestMapping(value = "/admin_login_form", method = RequestMethod.GET)
	public String adminLoginForm() throws Exception {
		return "/admin/main";
	}
	
	/** 관리자 로그인 성공 페이지 **/
	@RequestMapping(value = "/admin_login", method = RequestMethod.POST)
	public String adminLogin(@RequestParam String id,
							 @RequestParam String pwd,
							 HttpServletRequest request,
							 Model model) throws Exception {

		int result = adminMemberService.adminLogin(id, pwd);
		
		if (result == 1) {
            HttpSession session = request.getSession();
            session.setAttribute("id", id);
            return "redirect:/adminProduct/admin_product_list";
            
			
		} else if (result == 0) {
			model.addAttribute("message", "비밀번호를 확인하세요.");
		} else if (result == -1) {
			model.addAttribute("message", "아이디를 확인하세요.");
		}
		
		return "/admin/main";
	}
	
	/** 관리자 로그아웃 **/
	@RequestMapping(value = "/admin_logout", method = RequestMethod.GET)
	public String adminLogout(HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
			request.setAttribute("message", "");
		}
		
		return "/admin/main";
	}
	
	/** 관리자 회원 목록 **/
	@RequestMapping(value = "/admin_member_list", method = {RequestMethod.GET, RequestMethod.POST})
	public String adminMemberList(@RequestParam(defaultValue = "") String key,
								  Model model) throws Exception {

		model.addAttribute("memberList", adminMemberService.listMember(key));
		
		return "admin/member/memberList";
	}
	
}
