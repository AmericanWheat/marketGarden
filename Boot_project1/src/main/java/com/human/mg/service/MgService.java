package com.human.mg.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.human.mg.vo.MgVO;

/*
2. 시큐리티에서 현재의 VO를 사용하려면 두번째로  다음과 같이 
   서비스 인터페이스가를 UserDetailsService를 상속하도록 한다.
 */

public interface MgService extends UserDetailsService {

//	회원가입
	void insert(MgVO mgVO);

//	회원정보수정
	void update(MgVO mgVO);
	
//	회원정보삭제
	void delete(MgVO mgVO);
	
//	회원 목록
	List<MgVO> selectAll();
	
//  회원 1명 가져오기
	MgVO selectByIdx(int idx);
	
//	아이디 중복확인
	int selectByIdxCount(String userid);
	
//	idx와 비번을 받아 비번 일치 여부를 확인해주는 메서드
	boolean passwordCheck(int idx, String password);
	
//	아이디 읽어오기
	MgVO selectByUserid(String userid);
}
