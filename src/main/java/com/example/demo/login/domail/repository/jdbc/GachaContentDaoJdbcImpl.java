package com.example.demo.login.domail.repository.jdbc;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domail.model.GachaContentDTO;
import com.example.demo.login.domail.repository.GachaContentDao;

@Repository
public class GachaContentDaoJdbcImpl implements GachaContentDao {

	@Autowired
	JdbcTemplate jdbc;
	
	public GachaContentDTO selectFiveSS() {
		Map<String,Object> map = jdbc.queryForMap("select * from gacha_content where rarity = '星5-SS'");
		
		GachaContentDTO gachacontentdto = new GachaContentDTO();
		gachacontentdto.setId((int)map.get("id"));
		gachacontentdto.setRarity((String)map.get("rarity"));
		gachacontentdto.setContent((String)map.get("content"));
		
		return gachacontentdto;
	}
	
}
