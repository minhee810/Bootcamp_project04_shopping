package com.team1.sts.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.team1.sts.service.QnaService;
import com.team1.sts.vo.MemberVO;
import com.team1.sts.vo.QnaVO;

@Controller("qnaController")
@RequestMapping("/qna")
public class QnaController {

	@Autowired
	private QnaService qnaService;
	
	@Autowired
	private QnaVO qnaVO;

	/** Q&A 목록을 가져와서 뷰에 전달 **/
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listQna(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");

		String url = "qna/qnaList";
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		ModelAndView mav = new ModelAndView();
		
		 if (loginUser == null) { // 로그인되지 않은 상태일 때 로그인 페이지로 이동
		 url = "member/login";

		 } else {
		List<QnaVO> qnaList = qnaService.listQna(loginUser);
		mav.addObject("qnaList", qnaList);
		 }

		mav.setViewName(url);
		return mav;

	}

	
	/** Q&A 작성 페이지 **/
	@RequestMapping(value = "/writeForm", method = RequestMethod.GET)
	public ModelAndView writeForm(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String url = "qna/qnaWrite";
		ModelAndView mav = new ModelAndView(url);
		return mav;
	}

	
	/** Q&A 글 작성 후 목록 페이지 **/
	@RequestMapping(value = "/writeSave", method = RequestMethod.POST)
	public ModelAndView writeSave(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String url = "/qna/list";
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		qnaVO.setId(loginUser.getId());
		qnaVO.setSubject(request.getParameter("subject"));
		qnaVO.setContent(request.getParameter("content"));
		qnaService.qnaWrite(qnaVO);
		
		ModelAndView mav = new ModelAndView("redirect:"+url);
		return mav;
	}

	
	/** Q&A 게시글 삭제 **/
	@RequestMapping(value = "/qnadelete", method = RequestMethod.GET)
	public void qnadDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String url = "list";

		String qseq = request.getParameter("qseq");
		qnaService.qnaDelete(qseq);
		
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	/** 특정 Q&A 항목을 가져와서 뷰에 전달 **/
	@RequestMapping(value = "/qnaView", method = RequestMethod.GET)
	public ModelAndView qnaView(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String url = "qna/qnaView";
		 int qseq = Integer.parseInt(request.getParameter("qseq"));
         QnaVO qnaVO = qnaService.qnaView(qseq);
         
         request.setAttribute("qnaVO", qnaVO);
		ModelAndView mav = new ModelAndView(url);
		return mav;
	}

}
