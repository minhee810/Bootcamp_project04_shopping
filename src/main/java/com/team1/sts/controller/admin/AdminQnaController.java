package com.team1.sts.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.team1.sts.service.admin.AdminQnaService;
import com.team1.sts.vo.QnaVO;

@Controller
@RequestMapping("/adminqnas")
public class AdminQnaController {

	@Autowired
	private AdminQnaService adminQnaService;
	
	@Autowired
	private QnaVO qnaVO;
	
	@Autowired
	public AdminQnaController(AdminQnaService adminQnaService, QnaVO qnaVO) {
		this.adminQnaService = adminQnaService;
		this.qnaVO = qnaVO;
	}

	/** 관리자용 Q&A 목록 페이지 **/
	@RequestMapping(value = "/adminlist", method = RequestMethod.GET)
	public ModelAndView adminList(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String url = "admin/qna/qnaList";
		List<QnaVO> qnaList = adminQnaService.adminQnaList();
        request.setAttribute("qnaList", qnaList);
		ModelAndView mav = new ModelAndView(url);
		return mav;
	}
	
	/** Q&A 답변 저장 **/
	@RequestMapping(value = "/adminqnaresave", method = RequestMethod.GET)
	public ModelAndView adminQnaRresave(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "/adminqnas/adminqnadetail";
		
		String qseq = request.getParameter("qseq").trim();
        String reply = request.getParameter("reply").trim();

        adminQnaService.adminQnaResave(qseq, reply);
		ModelAndView mav = new ModelAndView("redirect:"+url+"?qseq="+qseq);
		return mav;
	}
	
	/** 관리자용 Q&A 상세 정보 페이지 **/
	@RequestMapping(value = "/adminqnadetail", method = RequestMethod.GET)
	public ModelAndView adminQnaDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "admin/qna/qnaDetail";
		
		int qseq = Integer.parseInt(request.getParameter("qseq"));
        qnaVO = adminQnaService.adminQnaDetail(qseq);

        request.setAttribute("qnaVO", qnaVO);
		ModelAndView mav = new ModelAndView(url);
		return mav;
	}
}
