package com.human.mg.service;


import java.sql.SQLException;

import com.human.mg.vo.MgmBoardVO;
import com.human.mg.vo.PagingVO;

public interface MgmBoardService {
	
//	글쓰기
	void insert(MgmBoardVO mgmBoardVO);
	
//	글삭제
	void delete(MgmBoardVO mgmBoardVO);
	
//	글 수정
	void update(MgmBoardVO mgmBoardVO);

//	목록보기
	PagingVO<MgmBoardVO> selectList(int currentPage, int sizeOfPage, int sizeOfBlock);
	
//	내용보기
	MgmBoardVO selectByIdx(int idx, boolean isReadCount);
	
//	글 목록보기(검색추가)
	PagingVO<MgmBoardVO> selectListSearch(int currentPage, int sizeOfPage, int sizeOfBlock ,String searchType ,String keyword);
	
//	글 개수보기(검색추가)
	int searchCount(String searchType, String keyword) throws SQLException;

}
