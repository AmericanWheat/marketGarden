package com.human.mg.service;


import com.human.mg.vo.MghBoardVO;
import com.human.mg.vo.PagingVO;

public interface MghBoardService {
	
//	글쓰기
	void insert(MghBoardVO mghBoardVO);
	
//	글삭제
	void delete(MghBoardVO mghBoardVO);
	
//	글 수정
	void update(MghBoardVO mghBoardVO);

//	목록보기
	PagingVO<MghBoardVO> selectList(int currentPage, int sizeOfPage, int sizeOfBlock);
	
//	내용보기
	MghBoardVO selectByIdx(int idx, boolean isReadCount);

}
