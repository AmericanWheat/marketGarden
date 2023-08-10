package com.human.mg.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.human.mg.vo.MghBoardVO;

@Mapper
public interface MghBoardDAO {
	
//	글쓰기
	void insert(MghBoardVO mghBoardVO) throws SQLException;
	
//	글삭제
	void delete(int idx) throws SQLException;
	
//	글 수정
	void update(MghBoardVO mghBoardVO) throws SQLException;
	
//	글 목록보기
	List<MghBoardVO> selectList(HashMap<String, Integer> map) throws SQLException;
	
//	글 1개 보기
	MghBoardVO selectByIdx(int idx) throws SQLException;

//	글 개수보기 
	int selectCount() throws SQLException;
	
//	조회수 증가
	void incrementReadCount(int idx) throws SQLException;
	
//	글 목록보기(검색추가)
	List<MghBoardVO> selectListSearch(HashMap<String, Integer> map, String searchType, String keyword ) throws SQLException;
		
//	글 개수보기(검색추가)
	int searchCount(String searchType, String keyword) throws SQLException;
	
}
