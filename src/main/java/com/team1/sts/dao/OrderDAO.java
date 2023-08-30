package com.team1.sts.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.team1.sts.vo.CartVO;
import com.team1.sts.vo.OrderVO;

@Repository("orderDAO")
public class OrderDAO {
		
	@Autowired
	private SqlSession sqlSession;
	
	
	/** 주문하기 **/
	@Transactional
	public int insertOrder(List<CartVO> cartList, String id) throws DataAccessException {
		int maxOseq = sqlSession.selectOne("mapper.order.selectMaxOseq");
		sqlSession.insert("mapper.order.insertOrder", id);

		for (CartVO cartVO : cartList) {
			insertOrderDetail(cartVO, maxOseq);
		}
		return maxOseq;
	}

	/** 주문 상세 보기 **/
	@Transactional
	public void insertOrderDetail(CartVO cartVO, int maxOseq) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("oseq", maxOseq);
		params.put("pseq", cartVO.getPseq());
		params.put("quantity", cartVO.getQuantity());
		params.put("cseq", cartVO.getCseq());

		sqlSession.insert("mapper.order.insertOrderDetail", params);
		sqlSession.update("mapper.order.updateCartResult", params);
	}

	public void updateCartResult(CartVO cartVO, int maxOseq) {
		sqlSession.insert("mapper.order.updateCartResult", cartVO);
	}

	/** 사용자가 주문 내역 검색 **/
	@Transactional
	public List<OrderVO> listOrderById(String id, String result, int oseq) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("result", result);
		params.put("oseq", oseq);
		List<OrderVO> orderList = sqlSession.selectList("mapper.order.listOrderById", params);
		return orderList;
	}

	/** 현재 진행 중인 주문 내역만 조회 **/
	public List<Integer> selectSeqOrderIng(String id) {
		List<Integer> oseqList = sqlSession.selectList("mapper.order.selectSeqOrderIng", id);
		return oseqList;
	}
	
	
	/** 관리자 모드에서 사용되는 메소드 **/
	public List<OrderVO> listOrder(String key) throws DataAccessException {
		List<OrderVO> listAll = sqlSession.selectList("mapper.order.selectNameList", key);
		return listAll;
	}

	public void updateOrderResult(String oseq) {
		sqlSession.update("mapper.order.updateOrderResult",oseq);
	}


	public List<OrderVO> listOrderAll() {
		List<OrderVO> listAll = sqlSession.selectList("mapper.order.selectAll");
		return listAll;
	}

	
}
