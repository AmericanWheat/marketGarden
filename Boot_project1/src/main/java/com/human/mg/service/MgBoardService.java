package com.human.mg.service;

import com.human.mg.vo.MgBoardVO;
import com.human.mg.vo.PagingVO;

public interface MgBoardService {
	
//	글쓰기
	void insert(MgBoardVO mgBoardVO);
	
//	글삭제
	void delete(MgBoardVO mgBoardVO);
	
//	글 수정
	void update(MgBoardVO mgBoardVO);

//	목록보기
	PagingVO<MgBoardVO> selectList(int currentPage, int sizeOfPage, int sizeOfBlock);
	
//	내용보기
	MgBoardVO selectByIdx(int idx, boolean isReadCount);

}
