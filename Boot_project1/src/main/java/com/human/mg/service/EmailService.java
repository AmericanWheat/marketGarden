package com.human.mg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.human.mg.vo.MailVO;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	public void sendSimpleMessage(MailVO mailVO) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("kistler780@gmail.com");
		message.setTo(mailVO.getAddress());
		message.setSubject(mailVO.getTitle());
		message.setText(mailVO.getContent());
		mailSender.send(message);
	}

}
