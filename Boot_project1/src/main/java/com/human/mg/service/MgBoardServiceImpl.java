package com.human.mg.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.mg.dao.MgBoardDAO;
import com.human.mg.vo.MgBoardVO;
import com.human.mg.vo.PagingVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("mgBoardService")
public class MgBoardServiceImpl implements MgBoardService {

	@Autowired
	MgBoardDAO dao;
	
	@Override
	public void insert(MgBoardVO mgBoardVO) {
		
		try {
			if(mgBoardVO!=null) {
				dao.insert(mgBoardVO);
				log.info("{} 저장성공", mgBoardVO);
			}
		} catch (Exception e) {
			log.info("{} 저장실패", mgBoardVO);
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void delete(MgBoardVO mgBoardVO) {
		if(mgBoardVO!=null) {
			try {
				MgBoardVO dbVO = dao.selectByIdx(mgBoardVO.getIdx());
				if(dbVO!=null && dbVO.getPassword().equals(mgBoardVO.getPassword())) {
					dao.delete(mgBoardVO.getIdx());
					log.info("{} 삭제 성공");
				}
			} catch (Exception e) {
				 e.printStackTrace();
				 log.info("{} 삭제 실패", mgBoardVO);
			}
		}
	}

	@Override
	public void update(MgBoardVO mgBoardVO) {
		if(mgBoardVO!=null) {
			try {
				MgBoardVO dbVO = dao.selectByIdx(mgBoardVO.getIdx());
				if(dbVO!=null && dbVO.getPassword().equals(mgBoardVO.getPassword())) {
					dao.update(mgBoardVO);
					log.info("{} 업데이트 성공", mgBoardVO);
				}
			} catch (Exception e) {
				 e.printStackTrace();
				 log.info("{} 업데이트 실패", mgBoardVO);
			}
		}
			
		
	}

	@Override
	public PagingVO<MgBoardVO> selectList(int currentPage, int sizeOfPage, int sizeOfBlock) {
		PagingVO<MgBoardVO> pv = null;
		
		try {
			
			//전체 개수 구하고
			int totalCount = dao.selectCount();
			
			//페이지 계산하고
			pv = new PagingVO<>(totalCount, currentPage, sizeOfPage, sizeOfBlock);
			
			// 개수가 있다면 글목록을 가져와 넣어준다
			if(pv.getTotalCount()>0) {
				HashMap<String, Integer> map = new HashMap<>();
				map.put("startNo", pv.getStartNo());
				map.put("endNo", pv.getEndNo());
				pv.setList(dao.selectList(map));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pv;
		
 	}

	@Override
	public MgBoardVO selectByIdx(int idx, boolean isReadCount) {
		MgBoardVO mgBoardVO = null;
		
		try {
			mgBoardVO =  dao.selectByIdx(idx);
			
			//해당 글번호의 글이 존재하면서 조회수가 참이면 조회수 1증가
			if(mgBoardVO!=null && isReadCount) {
				dao.incrementReadCount(idx);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mgBoardVO;
	}

}
