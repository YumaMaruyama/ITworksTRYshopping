package com.example.demo.login.domail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domail.model.ReviewDTO;
import com.example.demo.login.domail.repository.ReviewDao;

@Service
public class ReviewService {

	@Autowired
	ReviewDao dao;

	public int reviewInsertOne(ReviewDTO reviewdto, int selectId, int pcDataId, String title, String content,
			String rating, int purchaseId) {
		return dao.reviewInsertOne(reviewdto, selectId, pcDataId, title, content, rating, purchaseId);
	}

	public int selectOne(int selectId, int purchaseId) {

		return dao.selectOne(selectId, purchaseId);
	}

	public List<ReviewDTO> selectMany(int productId) {
		return dao.selectMany(productId);
	}

	public ReviewDTO selectReviewDetailOne(int reviewId) {
		return dao.selectReviewDetailOne(reviewId);
	}

	public int deleteOne(int reviewId) {
		return dao.deleteOne(reviewId);
	}

	public List<ReviewDTO> selectRating(int productId) {
		return dao.selectRating(productId);
	}

	public Integer selectManyId(int purchaseId) {
		return dao.selectManyId(purchaseId);
	}
}
