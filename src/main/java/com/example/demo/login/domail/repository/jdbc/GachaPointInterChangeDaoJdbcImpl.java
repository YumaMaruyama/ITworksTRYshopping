package com.example.demo.login.domail.repository.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domail.model.GachaPointInterChangeDTO;
import com.example.demo.login.domail.repository.GachaPointInterChangeDao;

@Repository
public class GachaPointInterChangeDaoJdbcImpl implements GachaPointInterChangeDao {

	@Autowired
	JdbcTemplate jdbc;
	
	public List<GachaPointInterChangeDTO> selectMany() {
		List<Map<String,Object>> map = jdbc.queryForList("select * from gacha_point_interchange");
		
		List<GachaPointInterChangeDTO> gachaPointInterChangeProductList = new ArrayList<>();
		
		for(Map<String,Object> oneMap : map) {
			GachaPointInterChangeDTO gachapointinterchangedto = new GachaPointInterChangeDTO();
			gachapointinterchangedto.setId((int)oneMap.get("id"));
			gachapointinterchangedto.setTitle((String)oneMap.get("title"));
			gachapointinterchangedto.setContent((String)oneMap.get("content"));
			gachapointinterchangedto.setPoint((int)oneMap.get("point"));
			gachapointinterchangedto.setImg((String)oneMap.get("img"));
			gachapointinterchangedto.setCategory((String)oneMap.get("category"));
			
			gachaPointInterChangeProductList.add(gachapointinterchangedto);
		}
		
		return gachaPointInterChangeProductList;
	}
	
	public GachaPointInterChangeDTO selectOne(int gachaPointProductId) {
		Map<String,Object> map = jdbc.queryForMap("select * from gacha_point_interchange where id = ?",gachaPointProductId);
		
		GachaPointInterChangeDTO gachapointinterchangedto = new GachaPointInterChangeDTO();
		gachapointinterchangedto.setId((int)map.get("id"));
		gachapointinterchangedto.setTitle((String)map.get("title"));
		gachapointinterchangedto.setContent((String)map.get("content"));
		gachapointinterchangedto.setPoint((int)map.get("point"));
		gachapointinterchangedto.setImg((String)map.get("img"));
		gachapointinterchangedto.setCategory((String)map.get("category"));
		
		return gachapointinterchangedto;
	}
}
