package com.human.mg.service;


import java.sql.SQLException;

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

//	글 목록보기(검색추가)
	PagingVO<MghBoardVO> selectListSearch(int currentPage, int sizeOfPage, int sizeOfBlock ,String searchType ,String keyword);
	
//	글 개수보기(검색추가)
	int searchCount(String searchType, String keyword) throws SQLException;
}
