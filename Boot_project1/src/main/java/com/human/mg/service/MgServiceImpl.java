package com.human.mg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.human.mg.dao.MgDAO;
import com.human.mg.vo.MgVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("mgService")
public class MgServiceImpl implements MgService {

	@Autowired
	MgDAO dao;

	/** 회원가입 */
	@Override
	public void insert(MgVO mgVO) {
		try {
			if (mgVO != null) {
				mgVO.setLev("ROLE_rookie");
				mgVO.setPoint(1000);
				BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
				mgVO.setPassword(bCryptPasswordEncoder.encode(mgVO.getPassword()));
				dao.insert(mgVO);
				log.info("{} 저장성공", mgVO);
			}
		} catch (Exception e) {
			log.info("{} 저장실패", mgVO);
			e.printStackTrace();
		}
	}

	/** 회원정보수정 */
	@Override
	public void update(MgVO mgVO) {
	    try {
	        if (mgVO != null) {
	            MgVO dbVO = dao.selectByIdx(mgVO.getIdx());
	            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	            
	            // 입력된 비밀번호와 DB에 저장된 암호화된 비밀번호를 비교
	            if (dbVO != null && bCryptPasswordEncoder.matches(mgVO.getPassword(), dbVO.getPassword())) {
	                // 여기에서 비밀번호가 일치하면 업데이트 수행
	                dao.update(mgVO);
	            } else {
	                log.info("비밀번호 일치하지않음 {}", mgVO.getPassword());
	            }
	            
	            log.info("{} 업데이트 성공", mgVO);
	        }
	    } catch (Exception e) {
	        log.info("{} 업데이트 실패", mgVO);
	        e.printStackTrace();
	    }
	}

	/** 회원정보삭제 */
	@Override
	public void delete(MgVO mgVO) {
		try {
			if (mgVO != null) {
				MgVO dbVO = dao.selectByIdx(mgVO.getIdx());
			    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
				if (dbVO != null && bCryptPasswordEncoder.matches(mgVO.getPassword(), dbVO.getPassword())) {
					dao.delete(mgVO.getIdx());
					log.info("삭제 성공");
				}
			}
		} catch (Exception e) {
			log.info("삭제 실패");
			e.printStackTrace();
		}
	}

	/** 회원정보리스트 */
	@Override
	public List<MgVO> selectAll() {
		List<MgVO> list = null;
		try {
			list = dao.selectAll();
			log.info("모두 얻기 성공 {}", list);
		} catch (Exception e) {
			log.info("모두 얻기 실패 {}", list);
			e.printStackTrace();
		}
		return list;
	}

	/** 회원정보 자세히 보기 */
	@Override
	public MgVO selectByIdx(int idx) {
		MgVO mgVO = null;
		try {
			mgVO = dao.selectByIdx(idx);
			log.info("조회하기 성공 {}", mgVO);
		} catch (Exception e) {
			log.info("조회하기 실패 {}", mgVO);
			e.printStackTrace();
		}
		return mgVO;
	}

	/** 아이디 중복방지 1과 0으로 구분 */
	@Override
	public int selectByIdxCount(String userid) {
		int count = 0;
		try {
			count = dao.selectByIdxCount(userid);
			log.info("id 개수얻기 성공 {}", count);
		} catch (Exception e) {
			log.info("id 개수얻기 실패 {}", count);
			e.printStackTrace();
		}

		return count;
	}

	/** 비밀번호 체크 확인 */
	@Override
	public boolean passwordCheck(int idx, String password) {
	    try {
	        // DB에 있는 자료와 비번이 같을때만 수정한다
	        MgVO dbVo = dao.selectByIdx(idx);
	        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	        
	        if (dbVo != null && bCryptPasswordEncoder.matches(password, dbVo.getPassword())) {
	            return true;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	@Override
	public MgVO selectByUserid(String userid) {
		MgVO mgVO = dao.selectByUserid(userid);
		log.info("selectByUserid : {}", mgVO);
		return mgVO;
	}

	/*
	 * 3. 시큐리티에서 현재의 VO를 사용하려면 두번째로 다음과 같이 UserDetailsService를 상속한 서비스 인터페이스가를 구현
	 * 하도록 한다.
	 * 
	 * public MemVO loadUserByUsername(String username) throws
	 * UsernameNotFoundException {} 위 메서드에서 리턴 타입을 UserDetails을 구현한 VO로 바꿔주고 DAO에서
	 * Userid로 VO를 얻어 리턴한다.
	 */

	// public UserDetails loadUserByUsername -> 구현한 VO로 바꿔줌
	@Override
	public MgVO loadUserByUsername(String username) throws UsernameNotFoundException {
		MgVO mgVO = null;
		mgVO = dao.selectByUserid(username);
		if (mgVO == null) {
			throw new UsernameNotFoundException("User not authorized.");
//			mgvo가 비었다면 오류 발생
		}
		return mgVO;
	}

}
