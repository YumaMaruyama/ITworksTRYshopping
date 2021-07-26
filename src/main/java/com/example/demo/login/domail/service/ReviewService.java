package com.example.demo.login.domail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domail.model.ReviewDTO;
import com.example.demo.login.domail.repository.ReviewDao;

@Service
public class ReviewService {

	@Autowired
	ReviewDao dao;
	
	public int reviewInsertOne(ReviewDTO reviewdto,int selectId,int pcDataId,String title,String content,String rating,int purchaseId) {
		return dao.reviewInsertOne(reviewdto,selectId,pcDataId,title,content,rating,purchaseId);
	}
	
	public int selectOne(int selectId,int pcDataId,int purchaseId) {
		
		return dao.selectOne(selectId,pcDataId,purchaseId);
	}
}
