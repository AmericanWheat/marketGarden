package com.human.mg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.human.mg.service.TestService;

@Controller
public class TestController {

	@Autowired
	TestService service;
	
	@GetMapping("/test")
	public String main(Model model) {
		model.addAttribute("time", service.selectToday());
		return "test";
	}
	
	
	
	
}
