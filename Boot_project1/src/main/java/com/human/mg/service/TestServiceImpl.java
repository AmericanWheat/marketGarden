package com.human.mg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.mg.dao.TestDAO;

@Service("testService")
public class TestServiceImpl implements TestService {

	@Autowired
	TestDAO dao;
	
	@Override
	public String selectToday() {
		return dao.selectToday() ;
	}

}
