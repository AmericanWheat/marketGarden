package com.human.mg.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.human.mg.vo.MgaBoardVO;

@Mapper
public interface MgaBoardDAO {
	
//	글쓰기
	void insert(MgaBoardVO mgaBoardVO) throws SQLException;
	
//	글삭제
	void delete(int idx) throws SQLException;
	
//	글 수정
	void update(MgaBoardVO mgaBoardVO) throws SQLException;
	
//	글 목록보기
	List<MgaBoardVO> selectList(HashMap<String, Integer> map) throws SQLException;
	
//	글 1개 보기
	MgaBoardVO selectByIdx(int idx) throws SQLException;

//	글 개수보기 
	int selectCount() throws SQLException;
	
//	조회수 증가
	void incrementReadCount(int idx) throws SQLException;
	
}
