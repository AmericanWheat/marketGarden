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
import org.springframework.web.bind.annotation.ResponseBody;

import com.human.mg.service.MgService;
import com.human.mg.vo.MgVO;

@Controller
public class MgController {

	@Autowired
	private MgService mgService;

	// 메인 컨트롤러
	@GetMapping(value = { "/", "/main" })
	public String main(@RequestParam(name = "idx", required = false) String idx, Principal principal, Model model) {

		if (principal != null) {
			model.addAttribute("userName", principal.getName());
			model.addAttribute("auth", principal);
		}

		model.addAttribute("idx", idx);

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

	// 회원 리스트(관리자만 접근가능)
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

	// 회원보기
	@GetMapping("/view")
	public String view(@RequestParam(required = true, defaultValue = "0") int idx, Model model) {
		model.addAttribute("vo", mgService.selectByIdx(idx)); // 글의 정보를 가지고 수정폼으로간다
		return "view";
	}

	// 아이디 중복확인
	@GetMapping("/idCheck")
	@ResponseBody
	public int idCheck(@RequestParam(required = true, defaultValue = "") String userid) {
		return mgService.selectByIdxCount(userid);
	}

	// footer 링크--------------------------------------------------------
	//
	@GetMapping("/intro")
	public String introduce() {
		return "footerlink/introduce";
	}

	@GetMapping("/intro2")
	public String hire() {
		return "footerlink/introduce2";
	}
	
	@GetMapping("/intro3")
	public String intro3() {
		return "footerlink/introduce3";
	}
	
	@GetMapping("/intro4")
	public String intro4() {
		return "footerlink/introduce4";
	}
	
	@GetMapping("/intro5")
	public String intro6() {
		return "footerlink/introduce5";
	}
	
	@GetMapping("/intro6")
	public String intro5() {
		return "footerlink/introduce6";
	}

}
