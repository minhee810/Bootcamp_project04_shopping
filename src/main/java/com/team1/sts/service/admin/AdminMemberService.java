package com.team1.sts.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team1.sts.dao.MemberDAO;
import com.team1.sts.dao.WorkerDAO;
import com.team1.sts.vo.MemberVO;

@Service
public class AdminMemberService {

	@Autowired
	WorkerDAO workerDAO;
	
	@Autowired
	MemberDAO memberDAO;
	
	// 관리자 로그인
	public int adminLogin(String id, String pw) {
		return workerDAO.adminLogin(id, pw);
	}

	// 회원 목록 확인
	public List<MemberVO> listMember(String key) {
		return memberDAO.listMember(key);
	}

}
