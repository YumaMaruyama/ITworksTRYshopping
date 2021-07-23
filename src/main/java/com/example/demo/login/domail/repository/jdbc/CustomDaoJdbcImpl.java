package com.example.demo.login.domail.repository.jdbc;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domail.model.CustomDTO;
import com.example.demo.login.domail.model.PcDetailDataDTO;
import com.example.demo.login.domail.model.PurchaseDTO;
import com.example.demo.login.domail.repository.CustomDao;

@Repository
public class CustomDaoJdbcImpl implements CustomDao{

	@Autowired
	JdbcTemplate jdbc;

	public int UpdateOne(int id,int select_id,String memory,String hardDisc,String cpu,int customPrice) {

		int result = jdbc.update("update custom set memory = ? ,hard_disc = ? ,cpu = ? ,custom_price = ? where product_id = ? and user_id = ?",memory,hardDisc,cpu,customPrice,id,select_id);

		return result;
	}

	public PcDetailDataDTO selectOne(int id,int select_id) {

		Map<String,Object> map = jdbc.queryForMap("select * from custom where product_id = ? and user_id = ?",id,select_id);

		PcDetailDataDTO pcdetaildatadto = new PcDetailDataDTO();
		pcdetaildatadto.setId((int)map.get("id"));
		pcdetaildatadto.setPurduct_id((int)map.get("product_id"));
		pcdetaildatadto.setUser_id((int)map.get("user_id"));
		pcdetaildatadto.setMemory((String)map.get("memory"));
		pcdetaildatadto.setHardDisc((String)map.get("hard_disc"));
		pcdetaildatadto.setCpu((String)map.get("cpu"));
		pcdetaildatadto.setCustomPrice((int)map.get("custom_price"));

		return pcdetaildatadto;
	}

	public CustomDTO selectCustomProduct_id(int id,int select_id) {

		int result = 0;

		CustomDTO customdto = new CustomDTO();
		
		try {
		Map<String,Object> map = jdbc.queryForMap("select * from custom where product_id = ? and user_id = ? and purchase_check = 'null'",id,select_id);
		
		result = 1;
		int i = 1;
		customdto.setSelectCheck(i);
		}catch(IncorrectResultSizeDataAccessException e) {
			e.printStackTrace();
			
			
		}
		return customdto;
	}

	public int insertCustomData(int id, int select_id, String defaultMemory, String defaultHardDisc,
			String defaultCpu,int customPrice) {

		int result = jdbc.update("insert into custom (product_id,"
				+ " user_id,"
				+ " memory,"
				+ " hard_disc,"
				+ " cpu,"
				+ " custom_price)"
				+ " value(?,?,?,?,?,?)",id,select_id,defaultMemory,defaultHardDisc,defaultCpu,customPrice);

		return result;
	}

	public int deleteCustomOne(int id,int getId) {

		int result = jdbc.update("delete from custom where product_id = ? and user_id = ?",id,getId);
		return result;
	}

	public int selectCustomId(int purchaseId,int select_id) {
		int result = jdbc.queryForObject("select custom.id from custom where product_id = ? and user_id = ?",Integer.class,purchaseId,select_id);

		return result;

	}

	public PurchaseDTO selectMany(int customId) {
		Map<String,Object> map = jdbc.queryForMap("select * from custom where id = ?",customId);

		System.out.println("textdididididi");

			PurchaseDTO purchasedto = new PurchaseDTO();
			purchasedto.setMemory((String)map.get("memory"));
			purchasedto.setHardDisc((String)map.get("hard_disc"));
			purchasedto.setCpu((String)map.get("cpu"));
			purchasedto.setCustomPrice((int)map.get("custom_price"));

			System.out.println("2dkdjffeifjifjjfjeiofj");

		return purchasedto;

	}
	
	public int purchaseCheckUpdate(int select_id,int product_id) {
		int result = jdbc.update("update custom set purchase_check = ? where user_id = ? and product_id = ?",product_id,select_id,product_id);
		return result;
	}
	
	public int selectPurchaseCheck(int select_id,int product_id,String nullCheck) {
		int result = jdbc.queryForObject("select custom.id from custom where user_id = ? and purchase_check = ? and purchase_check != ?",Integer.class,select_id,product_id,nullCheck);
		return result;
	}

}
