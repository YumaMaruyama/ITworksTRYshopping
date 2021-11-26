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
	
	
	public GachaContentDTO selectFourSS() {
		Map<String,Object> map = jdbc.queryForMap("select * from gacha_content where rarity = '星4-SS'");
		
		GachaContentDTO gachacontentdto = new GachaContentDTO();
		gachacontentdto.setId((int)map.get("id"));
		gachacontentdto.setRarity((String)map.get("rarity"));
		gachacontentdto.setContent((String)map.get("content"));
		
		return gachacontentdto;
	}
	
	public GachaContentDTO selectFourS() {
		Map<String,Object> map = jdbc.queryForMap("select * from gacha_content where rarity = '星4-S'");
		
		GachaContentDTO gachacontentdto = new GachaContentDTO();
		gachacontentdto.setId((int)map.get("id"));
		gachacontentdto.setRarity((String)map.get("rarity"));
		gachacontentdto.setContent((String)map.get("content"));
		
		return gachacontentdto;
	}
	
	public GachaContentDTO selectFourA() {
		Map<String,Object> map = jdbc.queryForMap("select * from gacha_content where rarity = '星4-A'");
		
		GachaContentDTO gachacontentdto = new GachaContentDTO();
		gachacontentdto.setId((int)map.get("id"));
		gachacontentdto.setRarity((String)map.get("rarity"));
		gachacontentdto.setContent((String)map.get("content"));
		
		return gachacontentdto;
	}
	
	public GachaContentDTO selectFourB() {
		Map<String,Object> map = jdbc.queryForMap("select * from gacha_content where rarity = '星4-B'");
		
		GachaContentDTO gachacontentdto = new GachaContentDTO();
		gachacontentdto.setId((int)map.get("id"));
		gachacontentdto.setRarity((String)map.get("rarity"));
		gachacontentdto.setContent((String)map.get("content"));
		
		return gachacontentdto;
	}
	
	public GachaContentDTO selectFourC() {
		Map<String,Object> map = jdbc.queryForMap("select * from gacha_content where rarity = '星4-C'");
		
		GachaContentDTO gachacontentdto = new GachaContentDTO();
		gachacontentdto.setId((int)map.get("id"));
		gachacontentdto.setRarity((String)map.get("rarity"));
		gachacontentdto.setContent((String)map.get("content"));
		
		return gachacontentdto;
	}
	
	public GachaContentDTO selectThreeSS() {
		Map<String,Object> map = jdbc.queryForMap("select * from gacha_content where rarity = '星3-SS'");
		
		GachaContentDTO gachacontentdto = new GachaContentDTO();
		gachacontentdto.setId((int)map.get("id"));
		gachacontentdto.setRarity((String)map.get("rarity"));
		gachacontentdto.setContent((String)map.get("content"));
		
		return gachacontentdto;
	}
	
	public GachaContentDTO selectThreeS() {
		Map<String,Object> map = jdbc.queryForMap("select * from gacha_content where rarity = '星3-S'");
		
		GachaContentDTO gachacontentdto = new GachaContentDTO();
		gachacontentdto.setId((int)map.get("id"));
		gachacontentdto.setRarity((String)map.get("rarity"));
		gachacontentdto.setContent((String)map.get("content"));
		
		return gachacontentdto;
	}
	
	public GachaContentDTO selectThreeA() {
		Map<String,Object> map = jdbc.queryForMap("select * from gacha_content where rarity = '星3-A'");
		
		GachaContentDTO gachacontentdto = new GachaContentDTO();
		gachacontentdto.setId((int)map.get("id"));
		gachacontentdto.setRarity((String)map.get("rarity"));
		gachacontentdto.setContent((String)map.get("content"));
		
		return gachacontentdto;
	}
	
	public GachaContentDTO selectThreeB() {
		Map<String,Object> map = jdbc.queryForMap("select * from gacha_content where rarity = '星3-B'");
		
		GachaContentDTO gachacontentdto = new GachaContentDTO();
		gachacontentdto.setId((int)map.get("id"));
		gachacontentdto.setRarity((String)map.get("rarity"));
		gachacontentdto.setContent((String)map.get("content"));
		
		return gachacontentdto;
	}
	
	public GachaContentDTO selectThreeC() {
		Map<String,Object> map = jdbc.queryForMap("select * from gacha_content where rarity = '星3-C'");
		
		GachaContentDTO gachacontentdto = new GachaContentDTO();
		gachacontentdto.setId((int)map.get("id"));
		gachacontentdto.setRarity((String)map.get("rarity"));
		gachacontentdto.setContent((String)map.get("content"));
		
		return gachacontentdto;
	}
	
	public GachaContentDTO selectTwoSS() {
		Map<String,Object> map = jdbc.queryForMap("select * from gacha_content where rarity = '星2-SS'");
		
		GachaContentDTO gachacontentdto = new GachaContentDTO();
		gachacontentdto.setId((int)map.get("id"));
		gachacontentdto.setRarity((String)map.get("rarity"));
		gachacontentdto.setContent((String)map.get("content"));
		
		return gachacontentdto;
	}
	
	public GachaContentDTO selectTwoS() {
		Map<String,Object> map = jdbc.queryForMap("select * from gacha_content where rarity = '星2-S'");
		
		GachaContentDTO gachacontentdto = new GachaContentDTO();
		gachacontentdto.setId((int)map.get("id"));
		gachacontentdto.setRarity((String)map.get("rarity"));
		gachacontentdto.setContent((String)map.get("content"));
		
		return gachacontentdto;
	}
	
	public GachaContentDTO selectTwoA() {
		Map<String,Object> map = jdbc.queryForMap("select * from gacha_content where rarity = '星2-A'");
		
		GachaContentDTO gachacontentdto = new GachaContentDTO();
		gachacontentdto.setId((int)map.get("id"));
		gachacontentdto.setRarity((String)map.get("rarity"));
		gachacontentdto.setContent((String)map.get("content"));
		
		return gachacontentdto;
	}
	
	public GachaContentDTO selectTwoB() {
		Map<String,Object> map = jdbc.queryForMap("select * from gacha_content where rarity = '星2-B'");
		
		GachaContentDTO gachacontentdto = new GachaContentDTO();
		gachacontentdto.setId((int)map.get("id"));
		gachacontentdto.setRarity((String)map.get("rarity"));
		gachacontentdto.setContent((String)map.get("content"));
		
		return gachacontentdto;
	}
	
	public GachaContentDTO selectTwoC() {
		Map<String,Object> map = jdbc.queryForMap("select * from gacha_content where rarity = '星2-C'");
		
		GachaContentDTO gachacontentdto = new GachaContentDTO();
		gachacontentdto.setId((int)map.get("id"));
		gachacontentdto.setRarity((String)map.get("rarity"));
		gachacontentdto.setContent((String)map.get("content"));
		
		return gachacontentdto;
	}
	
	public GachaContentDTO selectOneSS() {
		Map<String,Object> map = jdbc.queryForMap("select * from gacha_content where rarity = '星1-SS'");
		
		GachaContentDTO gachacontentdto = new GachaContentDTO();
		gachacontentdto.setId((int)map.get("id"));
		gachacontentdto.setRarity((String)map.get("rarity"));
		gachacontentdto.setContent((String)map.get("content"));
		
		return gachacontentdto;
	}
	
	public GachaContentDTO selectOneS() {
		Map<String,Object> map = jdbc.queryForMap("select * from gacha_content where rarity = '星1-S'");
		
		GachaContentDTO gachacontentdto = new GachaContentDTO();
		gachacontentdto.setId((int)map.get("id"));
		gachacontentdto.setRarity((String)map.get("rarity"));
		gachacontentdto.setContent((String)map.get("content"));
		
		return gachacontentdto;
	}
	
	public GachaContentDTO selectOneA() {
		Map<String,Object> map = jdbc.queryForMap("select * from gacha_content where rarity = '星1-A'");
		
		GachaContentDTO gachacontentdto = new GachaContentDTO();
		gachacontentdto.setId((int)map.get("id"));
		gachacontentdto.setRarity((String)map.get("rarity"));
		gachacontentdto.setContent((String)map.get("content"));
		
		return gachacontentdto;
	}
	
	public GachaContentDTO selectOneB() {
		Map<String,Object> map = jdbc.queryForMap("select * from gacha_content where rarity = '星1-B'");
		
		GachaContentDTO gachacontentdto = new GachaContentDTO();
		gachacontentdto.setId((int)map.get("id"));
		gachacontentdto.setRarity((String)map.get("rarity"));
		gachacontentdto.setContent((String)map.get("content"));
		
		return gachacontentdto;
	}
	
	public GachaContentDTO selectOneC() {
		Map<String,Object> map = jdbc.queryForMap("select * from gacha_content where rarity = '星1-C'");
		
		GachaContentDTO gachacontentdto = new GachaContentDTO();
		gachacontentdto.setId((int)map.get("id"));
		gachacontentdto.setRarity((String)map.get("rarity"));
		gachacontentdto.setContent((String)map.get("content"));
		
		return gachacontentdto;
	}
	
	
	
}
