package com.example.demo.login.domail.repository.jdbc;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domail.model.CancelDTO;
import com.example.demo.login.domail.repository.CancelDao;

@Repository
public class CancelDaoJdbcImpl implements CancelDao {

	@Autowired
	JdbcTemplate jdbc;
	
	public int insertOne(CancelDTO canceldto,int userId, int purchaseId, int product_id,String title, String content,int bankNumber, int storeName) {
		int result = jdbc.update("insert into cancel (id,"
				+ " user_id,"
				+ " purchase_id,"
				+ " product_id,"
				+ " title,"
				+ " content,"
				+ " bank_number,"
				+ " store_name)"
				+ " value(?,?,?,?,?,?,?,?)",canceldto.getId(),userId,purchaseId,product_id,title,content,bankNumber,storeName);
		
		
		return result;
				
	}
	
	public int insertOneCancelCheck(CancelDTO canceldto,int userId, int purchaseId, int productId,String title, String content,int bankNumber, int storeName) {
		int result = jdbc.update("insert into cancel (id,"
				+ " user_id,"
				+ " purchase_id,"
				+ " product_id,"
				+ " title,"
				+ " content,"
				+ " bank_number,"
				+ " store_name,"
				+ " cancel_check)"
				+ " value(?,?,?,?,?,?,?,?,'キャンセル取引中')",canceldto.getId(),userId,purchaseId,productId,title,content,bankNumber,storeName);
		
		return result;
	}
	
	public int deleteOne(int purchaseId) {
		int result = jdbc.update("delete from cancel where purchase_id = ?",purchaseId);
		return result;
	}
	
	public int cancelCheckUpdate(int purchaseId) {
		int result = jdbc.update("update cancel set cancel_check = '返品商品発送済み' where purchase_id = ?",purchaseId);
		return result;
	}
	
	public CancelDTO selectCancelCheck(int purchaseId) {
		
	
		Map<String,Object> map = jdbc.queryForMap("select * from purchase where id = ?",purchaseId);

		CancelDTO canceldto = new CancelDTO();
		canceldto.setCancelCheck((String)map.get("cancel_check"));
		return canceldto;
	}
	
	public int deliveryDateUpdate(CancelDTO canceldto,Date deliveryDate) {
		int result = jdbc.update("insert into cancel delivery_date = ?",canceldto.getDeliveryDate());
		return result;
	}
}
