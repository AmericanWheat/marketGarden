package com.human.mg.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.mg.dao.MgmBoardDAO;
import com.human.mg.vo.MgmBoardVO;
import com.human.mg.vo.PagingVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("mgmBoardService")
public class MgmBoardServiceImpl implements MgmBoardService {

	@Autowired
	MgmBoardDAO dao;
	
	@Override
	public void insert(MgmBoardVO mgmBoardVO) {
		
		try {
			if(mgmBoardVO!=null) {
				dao.insert(mgmBoardVO);
				log.info("{} 저장성공", mgmBoardVO);
			}
		} catch (Exception e) {
			log.info("{} 저장실패", mgmBoardVO);
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void delete(MgmBoardVO mgmBoardVO) {
		if(mgmBoardVO!=null) {
			try {
				MgmBoardVO dbVO = dao.selectByIdx(mgmBoardVO.getIdx());
				if(dbVO!=null && dbVO.getPassword().equals(mgmBoardVO.getPassword())) {
					dao.delete(mgmBoardVO.getIdx());
					log.info("{} 삭제 성공");
				}
			} catch (Exception e) {
				 e.printStackTrace();
				 log.info("{} 삭제 실패", mgmBoardVO);
			}
		}
	}

	@Override
	public void update(MgmBoardVO mgmBoardVO) {
		if(mgmBoardVO!=null) {
			try {
				MgmBoardVO dbVO = dao.selectByIdx(mgmBoardVO.getIdx());
				if(dbVO!=null && dbVO.getPassword().equals(mgmBoardVO.getPassword())) {
					dao.update(mgmBoardVO);
					log.info("{} 업데이트 성공", mgmBoardVO);
				}
			} catch (Exception e) {
				 e.printStackTrace();
				 log.info("{} 업데이트 실패", mgmBoardVO);
			}
		}
			
		
	}

	@Override
	public PagingVO<MgmBoardVO> selectList(int currentPage, int sizeOfPage, int sizeOfBlock) {
		PagingVO<MgmBoardVO> pv = null;
		
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
	public MgmBoardVO selectByIdx(int idx, boolean isReadCount) {
		MgmBoardVO mgmBoardVO = null;
		
		try {
			mgmBoardVO =  dao.selectByIdx(idx);
			
			//해당 글번호의 글이 존재하면서 조회수가 참이면 조회수 1증가
			if(mgmBoardVO!=null && isReadCount) {
				dao.incrementReadCount(idx);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mgmBoardVO;
	}


}
