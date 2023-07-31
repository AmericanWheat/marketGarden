package com.human.mg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.human.mg.vo.MgVO;

@Mapper
public interface MgDAO {

//	회원가입
	void insert(MgVO mgVO);
	
//	회원정보수정
	void update(MgVO mgVO);
	
//	회원정보삭제
	void delete(int idx);

//	회원 목록
	List<MgVO> selectAll();
	
//  회원 1명 가져오기
	MgVO selectByIdx(int idx);
	
//	아이디 중복확인
	int selectByIdxCount(String userid);
	
//	아이디 읽어오기
	MgVO selectByUserid(String userid);
	
	
}
