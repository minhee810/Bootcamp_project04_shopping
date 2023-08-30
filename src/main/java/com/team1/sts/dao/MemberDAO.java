package com.team1.sts.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.team1.sts.vo.MemberVO;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSession sqlSession;

	public int idCheck(String id) {
		MemberVO memberVO = sqlSession.selectOne("mapper.member.getMember", id);
		
		if (memberVO == null)
			return -1;
		else
			return 1;
	}
	
	public MemberVO getMember(String id) {
		return sqlSession.selectOne("mapper.member.getMember", id);
	}

	public int insertMember(MemberVO memberVO) {
		return sqlSession.insert("mapper.member.insertMember", memberVO);
	}
	
	public String findID(String name, String email) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("name",  name);
		paramMap.put("email", email);
		
		return sqlSession.selectOne("mapper.member.findID", paramMap);
	}
	

	public String findPW(String id, String name, String email) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("id",  id);
		paramMap.put("name",  name);
		paramMap.put("email", email);
		
		return sqlSession.selectOne("mapper.member.findPW", paramMap);
	}

	
	/*
	 * * 관리자 모드에서 사용되는 메소드 * *
	 */
	public List<MemberVO> listMember(String member_name) {
		return sqlSession.selectList("mapper.member.listMember", member_name);
	}
}
