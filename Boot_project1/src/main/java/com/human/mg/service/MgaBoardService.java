package com.human.mg.service;


import java.sql.SQLException;

import com.human.mg.vo.MgaBoardVO;
import com.human.mg.vo.PagingVO;

public interface MgaBoardService {
	
//	글쓰기
	void insert(MgaBoardVO mgaBoardVO);
	
//	글삭제
	void delete(MgaBoardVO mgaBoardVO);
	
//	글 수정
	void update(MgaBoardVO mgaBoardVO);

//	목록보기
	PagingVO<MgaBoardVO> selectList(int currentPage, int sizeOfPage, int sizeOfBlock);
	
//	내용보기
	MgaBoardVO selectByIdx(int idx, boolean isReadCount);
	
//	글 목록보기(검색추가)
	PagingVO<MgaBoardVO> selectListSearch(int currentPage, int sizeOfPage, int sizeOfBlock ,String searchType ,String keyword);
	
//	글 개수보기(검색추가)
	int searchCount(String searchType, String keyword) throws SQLException;

}
