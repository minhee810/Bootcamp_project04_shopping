package com.team1.sts.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.team1.sts.vo.CartVO;

@Repository("cartDAO")
public class CartDAO {

	@Autowired
	private SqlSession sqlSession;

	/** 장바구니 목록 보기 **/
	public List<CartVO> listCart(String userId) {
		return sqlSession.selectList("mapper.cart.listCart", userId);
	}

	/**장바구니 추가 기능**/
	public int insertCart(CartVO cartVO) {
		return sqlSession.insert("mapper.cart.insertCart", cartVO);
	}

	/** 장바구니 삭제 기능 **/
	public int deleteCart(int cseq) {
		return sqlSession.delete("mapper.cart.deleteCart", cseq);
	}
}
