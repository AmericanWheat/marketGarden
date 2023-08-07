package com.human.mg.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.mg.dao.MghBoardDAO;
import com.human.mg.vo.MghBoardVO;
import com.human.mg.vo.PagingVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("mghBoardService")
public class MghBoardServiceImpl implements MghBoardService {

	@Autowired
	MghBoardDAO dao;
	
	@Override
	public void insert(MghBoardVO mghBoardVO) {
		
		try {
			if(mghBoardVO!=null) {
				dao.insert(mghBoardVO);
				log.info("{} 저장성공", mghBoardVO);
			}
		} catch (Exception e) {
			log.info("{} 저장실패", mghBoardVO);
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void delete(MghBoardVO mghBoardVO) {
		if(mghBoardVO!=null) {
			try {
				MghBoardVO dbVO = dao.selectByIdx(mghBoardVO.getIdx());
				if(dbVO!=null && dbVO.getPassword().equals(mghBoardVO.getPassword())) {
					dao.delete(mghBoardVO.getIdx());
					log.info("{} 삭제 성공");
				}
			} catch (Exception e) {
				 e.printStackTrace();
				 log.info("{} 삭제 실패", mghBoardVO);
			}
		}
	}

	@Override
	public void update(MghBoardVO mghBoardVO) {
		if(mghBoardVO!=null) {
			try {
				MghBoardVO dbVO = dao.selectByIdx(mghBoardVO.getIdx());
				if(dbVO!=null && dbVO.getPassword().equals(mghBoardVO.getPassword())) {
					dao.update(mghBoardVO);
					log.info("{} 업데이트 성공", mghBoardVO);
				}
			} catch (Exception e) {
				 e.printStackTrace();
				 log.info("{} 업데이트 실패", mghBoardVO);
			}
		}
			
		
	}

	@Override
	public PagingVO<MghBoardVO> selectList(int currentPage, int sizeOfPage, int sizeOfBlock) {
		PagingVO<MghBoardVO> pv = null;
		
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
	public MghBoardVO selectByIdx(int idx, boolean isReadCount) {
		MghBoardVO mghBoardVO = null;
		
		try {
			mghBoardVO =  dao.selectByIdx(idx);
			
			//해당 글번호의 글이 존재하면서 조회수가 참이면 조회수 1증가
			if(mghBoardVO!=null && isReadCount) {
				dao.incrementReadCount(idx);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mghBoardVO;
	}


}
