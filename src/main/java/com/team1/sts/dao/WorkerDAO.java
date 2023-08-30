package com.team1.sts.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WorkerDAO {
	
	@Autowired
	private SqlSession sqlSession;

	public int adminLogin(String id, String pw) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("id",  id);
		paramMap.put("pwd",  pw);
		
		return sqlSession.selectOne("mapper.worker.workerCheck", paramMap);
		
	}

}