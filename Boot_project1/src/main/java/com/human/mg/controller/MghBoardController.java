package com.human.mg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.human.mg.service.MghBoardService;
import com.human.mg.vo.CommVO;
import com.human.mg.vo.MghBoardVO;
import com.human.mg.vo.PagingVO;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RequestMapping("/hboard")
@Controller
public class MghBoardController {

	@Autowired
	private MghBoardService boardService;
	
	//글 목록보기
	@GetMapping("/list")
	public String boardlist(@ModelAttribute CommVO commVO, Model model){
		log.info("넘어온값 : {}", commVO);
		model.addAttribute("cv", commVO);
		PagingVO<MghBoardVO> pagingVO = boardService.selectList(commVO.getCurrentPage(),commVO.getSizeOfPage() ,commVO.getSizeOfBlock());
		model.addAttribute("pv",pagingVO);
		log.info("가져온값 : {}", pagingVO);
		
		return "hboard/hboardlist";
	}
	
	//글쓰기 
	@GetMapping("/insert")
	public String insert(@ModelAttribute CommVO commVO, Model model) {
		log.info("넘어온값 : {}", commVO);
		model.addAttribute("cv", commVO);
		return "hboard/hboardinsert";
	}
	
	//저장하기 (get방식은 리스트로 돌아가기)
	@GetMapping("/insertOk")
	public String insertOkGet(@ModelAttribute CommVO commVO, Model model) {
		return "redirect:/hboard/list";
	}
	
	@PostMapping("/insertOk")
	public String insertOkPost(@ModelAttribute CommVO commVO,
			@ModelAttribute MghBoardVO boardVO,
			HttpServletRequest request) {
		log.info("넘어온값1 : {}", commVO);
		boardVO.setIp(request.getRemoteAddr());
		log.info("넘어온값2 : {}", boardVO);
		boardService.insert(boardVO);
		return "redirect:/hboard/list?p=1&s="+commVO.getSizeOfPage()+"&b="+commVO.getSizeOfBlock();
	}
	
	//글 보기 view
	@GetMapping("/view")
	public String view(@ModelAttribute CommVO commVO, Model model) {
		MghBoardVO boardVO = boardService.selectByIdx(commVO.getIdx(), true);
		//글을 보면 조회수 증가
	
		if(boardVO!=null) {
			boardVO.setReadCount(boardVO.getReadCount()+1);
			model.addAttribute("cv" , commVO);
			model.addAttribute("vo" , boardVO);
			model.addAttribute("newLine" , "\n");
			model.addAttribute("br" , "<br/>");
			return "hboard/hboardview";
		} else {
			return "redirect:/hboard/list";
		}
	}
	
	
	//수정하기
	@GetMapping("/update")
	public String update(@ModelAttribute CommVO commVO,  Model model) {
		log.info("넘어온값: {}", commVO);
		model.addAttribute("cv", commVO);
		//수정할때 조회수 증가하면 안됨 false값을 가져야함
		MghBoardVO mghboardVO = boardService.selectByIdx(commVO.getIdx(), false);
		
			if(mghboardVO!=null) {
				model.addAttribute("cv", commVO);
				model.addAttribute("vo", mghboardVO);
				return "hboard/hboardupdate";
			} else {
				return "redirect:/hboard/list";
			}
	}
	
	//수정하기(get 방식 list이동)
	@GetMapping("/updateOk")
	public String updateOkGet() {
		return "redirect:/hboard/list";
	}
	
	@PostMapping("/updateOk")
	public String updateOkPost(@ModelAttribute CommVO commVO, @ModelAttribute MghBoardVO mghboardVO, HttpServletRequest http) {
		log.info("넘어온값 {}", commVO);
		mghboardVO.setIp(http.getRemoteAddr());
		log.info("넘어온값 {}", mghboardVO);
		boardService.update(mghboardVO);
		
		return "redirect:/hboard/view?idx="+commVO.getIdx()+"&p="+commVO.getCurrentPage()+"&s"+commVO.getSizeOfBlock();
	}
	
	//삭제하기
	@GetMapping("/delete")
	public String delete(@ModelAttribute CommVO commVO , Model model) {
		
		log.info("넘어온값 : {}", commVO);
		model.addAttribute("cv", commVO);
		MghBoardVO mghboardVO = boardService.selectByIdx(commVO.getIdx(), false);
		if(mghboardVO!=null) {
			model.addAttribute("cv", commVO);
			model.addAttribute("vo", mghboardVO);
			return "hboard/hboarddelete";
		}else {
			return "redirect:/hboard/list";
		}
	}
	
	// 삭제하기 : Get방식일 경우
	@GetMapping("/deleteOk")
	public String deleteOkGet(@ModelAttribute CommVO commVO, Model model) {
		return "redirect:/hboard/list";
	}
	
	@PostMapping("/deleteOk")
	public String deleteOkPost(@ModelAttribute CommVO commVO, @ModelAttribute MghBoardVO mghboardVO ,HttpServletRequest request) {
		log.info("넘어온값 : {}", commVO);
		log.info("넘어온값 : {}", mghboardVO);
		boardService.delete(mghboardVO);
		return "redirect:/hboard/list";
	}
	


}
