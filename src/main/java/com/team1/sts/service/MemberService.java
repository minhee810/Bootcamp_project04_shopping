package com.team1.sts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team1.sts.dao.AddressDAO;
import com.team1.sts.dao.MemberDAO;
import com.team1.sts.vo.AddressVO;
import com.team1.sts.vo.MemberVO;

@Service
public class MemberService {

	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private AddressDAO addressDAO;	
	
	// 회원가입 : 아이디 중복 확인
	public int idCheck(String id) {
		return memberDAO.idCheck(id);
	}
	
	// 회원가입 : 주소 찾기
	public List<AddressVO> findZip(String dong) {
		return addressDAO.selectAddressByDong(dong);
	}
	
	// 회원가입 : 회원등록
	public int join(MemberVO memberVO) {
		return memberDAO.insertMember(memberVO);
	}
	
	// 로그인
	public MemberVO login(String id, String pwd) {
		
		MemberVO memberVO = memberDAO.getMember(id);
		
		if (memberDAO.getMember(id) != null) {
			if (memberVO.getPwd().equals(pwd))
				return memberVO;
		}
		return null;
	}
	
	// 아이디 찾기
	public String FindID(String name, String email) {
		return memberDAO.findID(name, email);
	}
		
	// 비밀번호 찾기
	public String FindPW(String id,String name, String email) {
		return memberDAO.findPW(id, name, email);
	}

}
