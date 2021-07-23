package com.example.demo.login.domail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domail.repository.ReviewDao;

@Service
public class ReviewService {

	@Autowired
	ReviewDao dao;
}
