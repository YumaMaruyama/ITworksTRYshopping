package com.example.demo.login.domail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domail.repository.CancelDao;



@Service
public class CancelService {

	@Autowired
	CancelDao dao;
}
