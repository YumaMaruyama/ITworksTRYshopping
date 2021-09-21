package com.example.demo.login.domail.repository.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domail.model.ReviewDTO;
import com.example.demo.login.domail.repository.ReviewDao;

@Repository
public class ReviewDaoJdbcImpl implements ReviewDao {

	@Autowired
	JdbcTemplate jdbc;

	public int reviewInsertOne(ReviewDTO reviewdto, int selectId, int pcDataId, String title, String content,
			String rating, int purchaseId) {
		int result = jdbc.update(
				"insert into review (id," + " user_id," + " product_id," + " title," + " content," + " rating,"
						+ " purchase_id)" + " value(?,?,?,?,?,?,?)",
				reviewdto.getId(), selectId, pcDataId, title, content, rating, purchaseId);

		return result;
	}

	public int selectOne(int selectId, int purchaseId) throws EmptyResultDataAccessException {
		int selectResult = 0;

		try {
			int result = jdbc.queryForObject("select review.id from review where user_id = ? and purchase_id = ?",
					Integer.class, selectId, purchaseId);
			selectResult = 1;
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();

		}

		return selectResult;
	}

	public List<ReviewDTO> selectMany(int productId) {

		List<Map<String, Object>> map = jdbc.queryForList(
				"select review.id,review.user_id,rating,review.content,review.title,review.registration_date,users.user_name,users.user_id as usersId from review join users on review.user_id = users.id where product_id = ?",
				productId);

		List<ReviewDTO> reviewList = new ArrayList<>();
		for (Map<String, Object> oneMap : map) {
			ReviewDTO reviewdto = new ReviewDTO();
			reviewdto.setId((int) oneMap.get("id"));
			reviewdto.setUserId((int) oneMap.get("user_id"));
			reviewdto.setRating((int) oneMap.get("rating"));
			reviewdto.setContent((String) oneMap.get("content"));
			reviewdto.setTitle((String) oneMap.get("title"));
			reviewdto.setRegistrationDate((Date) oneMap.get("registration_date"));
			reviewdto.setUserName((String) oneMap.get("user_name"));
			reviewdto.setUsersId((String) oneMap.get("usersId"));

			reviewList.add(reviewdto);
		}

		return reviewList;
	}

	public ReviewDTO selectReviewDetailOne(int reviewId) {

		Map<String, Object> map = jdbc.queryForMap(
				"select review.id,review.user_id,rating,review.content,review.title,review.registration_date,users.user_name,users.user_id as usersId from review join users on review.user_id = users.id where review.id = ?",
				reviewId);
		ReviewDTO reviewdto = new ReviewDTO();
		reviewdto.setId((int) map.get("id"));
		reviewdto.setUserId((int) map.get("user_id"));
		reviewdto.setRating((int) map.get("rating"));
		reviewdto.setContent((String) map.get("content"));
		reviewdto.setTitle((String) map.get("title"));
		reviewdto.setRegistrationDate((Date) map.get("registration_date"));
		reviewdto.setUserName((String) map.get("user_name"));
		reviewdto.setUsersId((String) map.get("usersId"));

		return reviewdto;
	}

	public int deleteOne(int reviewId) {

		int result = jdbc.update("delete from review where id = ?", reviewId);
		return result;
	}

	public List<ReviewDTO> selectRating(int productId) {

		List<Map<String, Object>> map = jdbc.queryForList("select * from review where product_id = ?", productId);

		List<ReviewDTO> reviewList = new ArrayList<>();
		for (Map<String, Object> oneMap : map) {
			ReviewDTO reviewdto = new ReviewDTO();
			reviewdto.setRating((int) oneMap.get("rating"));

			reviewList.add(reviewdto);
		}
		return reviewList;
	}

	public Integer selectManyId(int purchaseId) {

		Integer reviewId = 0;
		try {
			reviewId = jdbc.queryForObject("select review.id from review where purchase_id = ?", Integer.class,
					purchaseId);
		} catch (NullPointerException | EmptyResultDataAccessException e) {
			e.printStackTrace();

		}
		return reviewId;
	}
}
