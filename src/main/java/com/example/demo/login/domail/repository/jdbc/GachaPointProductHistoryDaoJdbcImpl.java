package com.example.demo.login.domail.repository.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domail.model.GachaPointProductHistoryDTO;
import com.example.demo.login.domail.repository.GachaPointProductHistoryDao;

@Repository
public class GachaPointProductHistoryDaoJdbcImpl implements GachaPointProductHistoryDao {

	@Autowired
	JdbcTemplate jdbc;
	
	public int productHistoryInsertOne(int userId,int gachaPointProductId) {
		int result = jdbc.update("insert into gacha_point_product_history (user_id,point_interchange_id,delivery_check) value(?,?,?)",userId,gachaPointProductId,"発送前");
		
		return result;
	}
	
	public List<GachaPointProductHistoryDTO> gachaPointProductHistorydtoList(int userId) {
		List<Map<String,Object>> map = jdbc.queryForList("select gacha_point_product_history.id,gacha_point_product_history.user_id,gacha_point_product_history.point_interchange_id,gacha_point_product_history.purchase_date,gacha_point_product_history.delivery_check,gacha_point_interchange.img,gacha_point_interchange.title,gacha_point_interchange.content from gacha_point_product_history join gacha_point_interchange on gacha_point_interchange.id = gacha_point_product_history.point_interchange_id  where gacha_point_product_history.user_id = ?",userId);
		
		List<GachaPointProductHistoryDTO> gachapointproducthistorydtoList = new ArrayList<>();
		
		for(Map<String,Object> oneMap : map) {
			GachaPointProductHistoryDTO gachapointproducthistorydto = new GachaPointProductHistoryDTO();
			gachapointproducthistorydto.setId((int)oneMap.get("id"));
			gachapointproducthistorydto.setUserId((int)oneMap.get("user_id"));
			gachapointproducthistorydto.setPointInterchangeId((int)oneMap.get("point_interchange_id"));
			gachapointproducthistorydto.setPurchaseDate((Date)oneMap.get("purchase_date"));
			gachapointproducthistorydto.setDeliveryCheck((String)oneMap.get("delivery_check"));
			gachapointproducthistorydto.setImg((String)oneMap.get("img"));
			gachapointproducthistorydto.setTitle((String)oneMap.get("title"));
			gachapointproducthistorydto.setContent((String)oneMap.get("content"));
			
			
			gachapointproducthistorydtoList.add(gachapointproducthistorydto);
		}
		
		return gachapointproducthistorydtoList;
	}
	
}
