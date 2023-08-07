package com.human.mg.service;


import java.sql.SQLException;

import com.human.mg.vo.MguBoardVO;
import com.human.mg.vo.PagingVO;

public interface MguBoardService {
	
//	글쓰기
	void insert(MguBoardVO mguBoardVO);
	
//	글삭제
	void delete(MguBoardVO mguBoardVO);
	
//	글 수정
	void update(MguBoardVO mguBoardVO);

//	목록보기
	PagingVO<MguBoardVO> selectList(int currentPage, int sizeOfPage, int sizeOfBlock);
	
//	내용보기
	MguBoardVO selectByIdx(int idx, boolean isReadCount);

//	거래추천 증가
	void incrementExchange(int idx);
}
