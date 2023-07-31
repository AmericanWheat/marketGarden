package com.human.mg.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.human.mg.service.MgService;
import com.human.mg.vo.MgVO;

@Controller
public class MgController {

	@Autowired
	private MgService mgService;

	// 메인 컨트롤러
	@GetMapping(value={"/","/main"})
	public String main(Principal principal, Model model) {

		if (principal != null) {
			model.addAttribute("userName", principal.getName());
			model.addAttribute("auth", principal);
		}

		return "main";
	}

	// 가입
	@GetMapping("/join")
	public String join() {
		return "join";
	}

	// get 방식 가입
	// get방식 아무처리도 안하고 메인으로 돌려보냄
	@GetMapping("/insertOK")
	public String insertGet() {
		return "redirect:/main";
	}

//	post 방식 가입
	// post방식 처리하고 메인으로 돌려보냄
	@PostMapping("/insertOk")
	public String insertPost(@ModelAttribute MgVO mgVO) {
		mgService.insert(mgVO);
		return "redirect:/main";
	}

	// 회원정보수정
	@GetMapping("/update")
	public String update(@ModelAttribute MgVO mgVO, Model model) {
		if (mgService.passwordCheck(mgVO.getIdx(), mgVO.getPassword())) {
			// 글의 정보를 가지고 수정폼으로 간다
			model.addAttribute("vo", mgService.selectByIdx(mgVO.getIdx()));
			return "update";
		} else {
			return "redirect:/main";
		}

	}

	// get 방식 수정
	// get방식 아무처리도 안하고 메인으로 돌려보냄
	@GetMapping("/updateOk")
	public String updateGet() {
		return "redirect:/main";
	}

//		post 방식 수정
	// post방식 처리하고 메인으로 돌려보냄
	@PostMapping("/updateOk")
	public String updatePost(@ModelAttribute MgVO mgVO) {
		mgService.update(mgVO);
		return "redirect:/main";
	}

	// 회원 삭제

	@GetMapping("/deleteOk")
	public String deleteGet(@ModelAttribute MgVO mgVO) {
		mgService.delete(mgVO);
		return "redirect:/list";
	}

	// 회원 리스트(차후 스프링 시큐리티로 관리자만 접근가능하게 할거임)
	// 카피라이트 문장에 링크 넣어둠
	@GetMapping("/list")
	public String list(Model model) {
		List<MgVO> list = mgService.selectAll();
		model.addAttribute("list", list);
		return "list";
	}

	// 로그인
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout,
			Model model) {
		if (error != null) {
			model.addAttribute("error", "error");
		}

		if (logout != null) {
			model.addAttribute("logout", "logout");
		}
		return "login";
	}

}
