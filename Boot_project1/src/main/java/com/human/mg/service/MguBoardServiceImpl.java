package com.human.mg.service;

import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.mg.dao.MguBoardDAO;
import com.human.mg.vo.MguBoardVO;
import com.human.mg.vo.PagingVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("mguBoardService")
public class MguBoardServiceImpl implements MguBoardService {

	@Autowired
	MguBoardDAO dao;

	@Override
	public void insert(MguBoardVO mguBoardVO) {

		try {
			if (mguBoardVO != null) {
				dao.insert(mguBoardVO);
				log.info("{} 저장성공", mguBoardVO);
			}
		} catch (Exception e) {
			log.info("{} 저장실패", mguBoardVO);
			e.printStackTrace();
		}

	}

	@Override
	public void delete(MguBoardVO mguBoardVO) {
		if (mguBoardVO != null) {
			try {
				dao.delete(mguBoardVO.getIdx());
				log.info("{} 삭제 성공");
			} catch (Exception e) {
				e.printStackTrace();
				log.info("{} 삭제 실패", mguBoardVO);
			}
		}
	}

	@Override
	public void update(MguBoardVO mguBoardVO) {
		if (mguBoardVO != null) {
			try {
				dao.update(mguBoardVO);
				log.info("{} 업데이트 성공", mguBoardVO);

			} catch (Exception e) {
				e.printStackTrace();
				log.info("{} 업데이트 실패", mguBoardVO);
			}
		}

	}

	@Override
	public PagingVO<MguBoardVO> selectList(int currentPage, int sizeOfPage, int sizeOfBlock) {
		PagingVO<MguBoardVO> pv = null;

		try {

			// 전체 개수 구하고
			int totalCount = dao.selectCount();

			// 페이지 계산하고
			pv = new PagingVO<>(totalCount, currentPage, sizeOfPage, sizeOfBlock);

			// 개수가 있다면 글목록을 가져와 넣어준다
			if (pv.getTotalCount() > 0) {
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
	public MguBoardVO selectByIdx(int idx, boolean isReadCount) {
		MguBoardVO mguBoardVO = null;

		try {
			mguBoardVO = dao.selectByIdx(idx);

			// 해당 글번호의 글이 존재하면서 조회수가 참이면 조회수 1증가
			if (mguBoardVO != null && isReadCount) {
				dao.incrementReadCount(idx);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mguBoardVO;
	}

	@Override
	public void incrementExchange(int idx) {
		try {
	        dao.incrementExchange(idx);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public PagingVO<MguBoardVO> selectListSearch(int currentPage, int sizeOfPage, int sizeOfBlock, String searchType,
			String keyword) {
		PagingVO<MguBoardVO> pv = null;

		try {

			// 전체 개수 구하고
			int totalCount = dao.searchCount(searchType, keyword);

			// 페이지 계산하고
			pv = new PagingVO<>(totalCount, currentPage, sizeOfPage, sizeOfBlock);

			// 개수가 있다면 글목록을 가져와 넣어준다 + 검색종류와 키워드를 넣어줌
			if (pv.getTotalCount() > 0) {
				HashMap<String, Integer> map = new HashMap<>();
				map.put("startNo", pv.getStartNo());
				map.put("endNo", pv.getEndNo());
				pv.setList(dao.selectListSearch(map, searchType, keyword));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pv;
	}

	@Override
	public int searchCount(String searchType, String keyword) throws SQLException {
		return dao.searchCount(searchType, keyword);
	}
	
	
}
