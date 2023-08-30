package com.team1.sts.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team1.sts.dao.QnaDAO;
import com.team1.sts.vo.QnaVO;

@Service
public class AdminQnaService {
    
	@Autowired
	private final QnaDAO qnaDAO;
	
	@Autowired
	public AdminQnaService(QnaDAO qnaDAO){
		this.qnaDAO = qnaDAO;
	}
    
    
    // 어드민 Q&A 목록을 가져오는 메서드
    public List<QnaVO> adminQnaList() {
        // QnaDAO를 통해 모든 Q&A 항목을 가져옴
        List<QnaVO> qnaList = qnaDAO.listAllQna();
        
        return qnaList;
    }

    // 선택한 Q&A 항목의 상세 정보를 가져오는 메서드
    public QnaVO adminQnaDetail(int qseq) {
    	
        // QnaDAO를 통해 특정 Q&A 항목의 정보를 조회
        QnaVO qnaVO = qnaDAO.getQna(qseq);
        
        return qnaVO;
    }

    // Q&A 답변을 저장하는 메서드
    public void adminQnaResave(String qseq, String reply) {
    	
        // QnaVO 객체 생성 및 데이터 설정
        QnaVO qnaVO = new QnaVO();
        qnaVO.setQseq(Integer.parseInt(qseq));
        qnaVO.setReply(reply);

        // Q&A 답변을 업데이트하는 메서드 호출
        qnaDAO.updateQna(qnaVO);
    }
}