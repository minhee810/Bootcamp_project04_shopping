package com.team1.sts.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.team1.sts.controller.QnaController;
import com.team1.sts.vo.QnaVO;

@Repository("qnaDAO")
public class QnaDAO {
	private static final Logger logger = LoggerFactory.getLogger(QnaController.class);
	
	@Autowired
	private SqlSession sqlSession;

	public QnaDAO() {
	}

	public List listQna(String id) throws DataAccessException{
		System.out.println("---------"+id+"-----------");
		List listAll = sqlSession. selectList("mapper.qna.selectAllList",id);
		return listAll;
	}
	
	public QnaVO getQna(int qseq) throws DataAccessException{
		QnaVO QnaDetail = (QnaVO) sqlSession.selectOne("mapper.qna.selectOne",qseq);
		return QnaDetail;
	}

	public void insertqna(QnaVO qnaVO) throws DataAccessException{
		sqlSession.insert("mapper.qna.insertQnA",qnaVO);
	}

	public void deleteQna(String qseq) throws DataAccessException{
		sqlSession.delete("mapper.qna.deleteQnA",Integer.parseInt(qseq));
	}

	public List listAllQna() throws DataAccessException{
		List listAll = sqlSession.selectList("mapper.qna.selectAll");
		return listAll;
	}

	public void updateQna(QnaVO qnaVO) throws DataAccessException{
		sqlSession.update("mapper.qna.updateQnA",qnaVO);
	}

}
