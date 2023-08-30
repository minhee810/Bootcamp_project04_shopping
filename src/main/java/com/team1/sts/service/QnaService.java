package com.team1.sts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team1.sts.dao.QnaDAO;
import com.team1.sts.vo.MemberVO;
import com.team1.sts.vo.QnaVO;


@Service("qnaService")
public class QnaService {

	@Autowired
	private QnaDAO qnaDAO;
	
	// 해당하는 아이디가 포함되어 있는 전체 리스트 출력
	public List<QnaVO> listQna(MemberVO loginUser) {
		
		// QnaDAO를 통해 사용자의 ID를 기반으로 Q&A 목록을 가져옴
        List<QnaVO> qnaList = qnaDAO.listQna(loginUser.getId());

        return qnaList;
	}
	
	// Q&A 글을 작성 버튼 클릭시
	public void qnaWrite(QnaVO qnaVO) {
		
		// QnaDAO를 통해 Q&A 글을 작성함 (qnaVO에 저장된 정보와 로그인한 사용자 ID 사용)
        qnaDAO.insertqna(qnaVO);
	}

	public void qnaDelete(String qseq) {
		qnaDAO.deleteQna(qseq);
	}

	public QnaVO qnaView(int qseq) {
		QnaVO qnaVO = qnaDAO.getQna(qseq);
        return qnaVO;
	}

	

}
