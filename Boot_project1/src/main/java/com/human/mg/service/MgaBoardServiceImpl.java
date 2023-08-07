package com.human.mg.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.mg.dao.MgaBoardDAO;
import com.human.mg.vo.MgaBoardVO;
import com.human.mg.vo.PagingVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("mgaBoardService")
public class MgaBoardServiceImpl implements MgaBoardService {

	@Autowired
	MgaBoardDAO dao;

	@Override
	public void insert(MgaBoardVO mgaBoardVO) {

		try {
			if (mgaBoardVO != null) {
				dao.insert(mgaBoardVO);
				log.info("{} 저장성공", mgaBoardVO);
			}
		} catch (Exception e) {
			log.info("{} 저장실패", mgaBoardVO);
			e.printStackTrace();
		}

	}

	@Override
	public void delete(MgaBoardVO mgaBoardVO) {
		if (mgaBoardVO != null) {
			try {
				dao.delete(mgaBoardVO.getIdx());
				log.info("{} 삭제 성공");
			} catch (Exception e) {
				e.printStackTrace();
				log.info("{} 삭제 실패", mgaBoardVO);
			}
		}
	}

	@Override
	public void update(MgaBoardVO mgaBoardVO) {
		if (mgaBoardVO != null) {
			try {
				dao.update(mgaBoardVO);
				log.info("{} 업데이트 성공", mgaBoardVO);

			} catch (Exception e) {
				e.printStackTrace();
				log.info("{} 업데이트 실패", mgaBoardVO);
			}
		}

	}

	@Override
	public PagingVO<MgaBoardVO> selectList(int currentPage, int sizeOfPage, int sizeOfBlock) {
		PagingVO<MgaBoardVO> pv = null;

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
	public MgaBoardVO selectByIdx(int idx, boolean isReadCount) {
		MgaBoardVO mgaBoardVO = null;

		try {
			mgaBoardVO = dao.selectByIdx(idx);

			// 해당 글번호의 글이 존재하면서 조회수가 참이면 조회수 1증가
			if (mgaBoardVO != null && isReadCount) {
				dao.incrementReadCount(idx);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mgaBoardVO;
	}

}
