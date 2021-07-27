package com.example.demo.login.domail.repository;

import java.util.List;

import com.example.demo.login.domail.model.ReviewDTO;

public interface ReviewDao {

	public int reviewInsertOne(ReviewDTO reviewdto,int selectId,int pcDataId,String title,String content,String rating,int purchaseId);
	
	public int selectOne(int selectId,int pcDataId,int purchaseId);
	
	public List<ReviewDTO> selectMany(int productId);
	
	public ReviewDTO selectReviewDetailOne(int reviewId);
	
	public int deleteOne(int reviewId);
}
