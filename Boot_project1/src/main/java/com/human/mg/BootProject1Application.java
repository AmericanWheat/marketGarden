package com.human.mg;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import com.human.mg.dao.TestDAO;

@SpringBootApplication
public class BootProject1Application {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	TestDAO dao;
	
	
	public static void main(String[] args) {
		SpringApplication.run(BootProject1Application.class, args);
	}

	@Bean
	CommandLineRunner getCommandLineRunner() {
		return args -> {
			System.out.println("-".repeat(80));
			String today = jdbcTemplate.queryForObject("select sysdate from dual", String.class);
			System.out.println("jdbctemplate을 이용한 oracle DB시간 : " + today);
			
			System.out.println("마이바티스를 이용한 oracle DB시간 : "+dao.selectToday());
			System.out.println("-".repeat(80));
		
		};
	}
	
}
