package com.human.mg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.human.mg.service.EmailService;
import com.human.mg.vo.MailVO;

@Controller
public class MailController {

	@Autowired
	private EmailService emailService;
	
    public MailController(EmailService emailService) {
        this.emailService = emailService;
    }
	
	@GetMapping("/mail/send")
    public String main() {
        return "redirect:/footerlink/introduce3";
    }
 
    @PostMapping("/mail/send")
    public String sendMail(MailVO mailVO) {
        emailService.sendSimpleMessage(mailVO);
        System.out.println("메일 전송 완료");
        return "/footerlink/introduce3";
    }
	
	@PostMapping("/mail/send2")
    public String sendmail2(MailVO mailVO) {
		 emailService.sendSimpleMessage(mailVO);	
		 System.out.println("메일 전송 완료");	
		 return "/main";
	}
 
    
}
