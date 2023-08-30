package com.team1.sts.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.team1.sts.vo.AddressVO;

@Repository
public class AddressDAO {
	
	@Autowired
	private SqlSession sqlSession;

	public List<AddressVO> selectAddressByDong(String dong) {
		return sqlSession.selectList("mapper.adderss.selectAddressByDong", dong);
	}
}
