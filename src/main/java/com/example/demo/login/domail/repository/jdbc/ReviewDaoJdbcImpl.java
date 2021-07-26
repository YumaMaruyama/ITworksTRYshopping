package com.example.demo.login.domail.repository.jdbc;

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
	
	public int reviewInsertOne(ReviewDTO reviewdto,int selectId,int pcDataId,String title,String content,String rating,int purchaseId) {
		int result = jdbc.update("insert into review (id,"
				+ " user_id,"
				+ " product_id,"
				+ " title,"
				+ " content,"
				+ " rating,"
				+ " purchase_id)"
				+ " value(?,?,?,?,?,?,?)",reviewdto.getId(),selectId,pcDataId,title,content,rating,purchaseId);
		
		return result;
	}
	
	public int selectOne(int selectId,int pcDataId,int purchaseId) throws EmptyResultDataAccessException {
		int selectResult = 0;
		
		try {
		int result = jdbc.queryForObject("select review.id from review where user_id = ? and product_id = ? and purchase_id = ?",Integer.class,selectId,pcDataId,purchaseId);
		selectResult = 1;
		}catch(EmptyResultDataAccessException e) {
			e.printStackTrace();
			
		}
	
		return selectResult;
	}
}
