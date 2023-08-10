package com.human.mg.service;

import java.sql.SQLException;

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

//	글 목록보기(검색추가)
	PagingVO<MgBoardVO> selectListSearch(int currentPage, int sizeOfPage, int sizeOfBlock ,String searchType ,String keyword);
	
//	글 개수보기(검색추가)
	int searchCount(String searchType, String keyword) throws SQLException;
	
}
