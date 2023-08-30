package com.team1.sts.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.team1.sts.service.MemberService;
import com.team1.sts.vo.MemberVO;

@Controller
@RequestMapping("/members")
public class MemberController {
	
	@Autowired
	private MemberService memberService;

	/** 개인정보동의 페이지 **/
	@RequestMapping(value = "/contract", method = RequestMethod.GET)
	public String contract() throws Exception {
		return "/member/contract";
	}
	
	/** 개인정보 동의 유무를 넘겨줌 **/
	@RequestMapping(value = "/join_form", method = RequestMethod.POST)
	public String joinForm() throws Exception {
		return "/member/join";
	}
	
	/** 아이디 중복 확인 **/
	@RequestMapping(value = "/id_check_form", method = {RequestMethod.GET, RequestMethod.POST})
	public String idCheckForm(@RequestParam String id, Model model) throws Exception {

	    model.addAttribute("message", memberService.idCheck(id));
	    model.addAttribute("id", id);
	    
		return "/member/idcheck";
	}
	
	/** 주소 찾기 팝업 창 **/
	@RequestMapping(value = "/find_zip_Form", method = RequestMethod.GET)
	public String findZipForm() throws Exception {
		
		return "/member/findZipNum";
	}
	
	/** 주소 찾기 **/
	@RequestMapping(value = "/find_zip_num", method = RequestMethod.POST)
	public String findZipNum(@RequestParam String dong, Model model) throws Exception {
		
		if (dong != null && dong.trim().equals("") == false) {
			model.addAttribute("addressList", memberService.findZip(dong.trim()));
		}
		
		return "/member/findZipNum";
	}
	
	/** 회원가입 **/
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute MemberVO memberVO,
						HttpServletRequest request, Model model) throws Exception {
		
		HttpSession session = request.getSession();
		session.setAttribute("id", memberVO.getId());
		
		String address = request.getParameter("addr1") + request.getParameter("addr2");
		memberVO.setAddress(address);

		memberService.join(memberVO);
		
		return "/member/login";
	}
	
	/** 로그인 입력 **/
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam String id, 
			 			@RequestParam String pwd,
			 			HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		
		if (memberService.login(id, pwd) != null) {
			session.removeAttribute("id");
			session.setAttribute("loginUser", memberService.login(id, pwd));
			return "redirect:/products/viewItem";
		}
		return "/member/login_fail";
		
	}
	
	/** 로그인 **/
	@RequestMapping(value = "/login_form", method = RequestMethod.GET)
	public String loginForm() throws Exception {		
		
		return "/member/login";
	}
	
	/** 로그아웃 **/
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession(false);
		
		if (session != null) {
			session.invalidate();
		}
		
		return "redirect:/products/viewItem";
	}
	
	/** 아이디 찾기 **/
	@RequestMapping(value = "/findID", method = RequestMethod.POST)
	public String findID(@RequestParam String name, @RequestParam String email,
						Model model) throws Exception {
		
		if (name != null && name.trim().equals("") == false) {
			if (email != null && email.trim().equals("") == false) {
				model.addAttribute("findID", memberService.FindID(name,email));
			}
		}
		
		return "/member/foundIdPwd";
	}
	
	/** 비밀번호 찾기 **/
	@RequestMapping(value = "/findPW", method = RequestMethod.POST)
	public String findPW(@RequestParam String id,
						 @RequestParam String name,
						 @RequestParam String email,
						 Model model) throws Exception {
		
		model.addAttribute("findPW", memberService.FindPW(id,name,email));
		
		return "/member/foundIdPwd";
	}
	
	/** 아이디 비밀번호 찾기 페이지 이동 **/
	@RequestMapping(value = "/findIdPwd", method = RequestMethod.GET)
	public String findIdPwd() throws Exception {
		return "/member/findIdAndPassword";
	}
}
