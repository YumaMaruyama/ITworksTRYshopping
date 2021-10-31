package com.example.demo.login.domail.repository.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domail.model.PcDataDTO;
import com.example.demo.login.domail.model.PcDataForm;
import com.example.demo.login.domail.model.PurchaseDTO;
import com.example.demo.login.domail.repository.PcDataDao;

@Repository
public class PcDataDaoJdbcImpl implements PcDataDao {

	@Autowired
	JdbcTemplate jdbc;

	public int insertOne(PcDataDTO pcdatadto) {

		int rowNumber = jdbc.update(
				"insert into pcdata (id," + "company," + "os," + "pc_name," + "pc_size," + "price," + "detail,"
						+ "product_stock," + "pcImg," + "pcImg2," + "pcImg3)" + "value(?,?,?,?,?,?,?,?,?,?,?)",
				pcdatadto.getId(), pcdatadto.getCompany(), pcdatadto.getOs(), pcdatadto.getPc_name(),
				pcdatadto.getPc_size(), pcdatadto.getPrice(), pcdatadto.getDetail(), pcdatadto.getProduct_stock(),
				pcdatadto.getPcImg(), pcdatadto.getPcImg2(), pcdatadto.getPcImg3());

		return rowNumber;
	}

	public List<PcDataDTO> selectMany() {

		List<Map<String, Object>> productList = jdbc.queryForList("select * from pcdata where product_stock >= 1");

		List<PcDataDTO> pcdatadtoList = new ArrayList<>();

		for (Map<String, Object> map : productList) {
			PcDataDTO pcdatadto = new PcDataDTO();

			pcdatadto.setId((int) map.get("id"));
			pcdatadto.setCompany((String) map.get("company"));
			pcdatadto.setOs((String) map.get("os"));
			pcdatadto.setPc_name((String) map.get("pc_name"));
			pcdatadto.setPc_size((int) map.get("pc_size"));
			pcdatadto.setPrice((int) map.get("price"));
			pcdatadto.setDetail((String) map.get("detail"));
			pcdatadto.setProduct_stock((int) map.get("product_stock"));
			pcdatadto.setPcImg((String) map.get("pcImg"));
			pcdatadto.setPcImg2((String) map.get("pcImg2"));
			pcdatadto.setPcImg3((String) map.get("pcImg3"));

			pcdatadtoList.add(pcdatadto);
		}

		return pcdatadtoList;

	}

	public PcDataDTO selectOne(int id) {

		Map<String, Object> map = jdbc.queryForMap("select * from pcdata where id = ?", id);

		PcDataDTO pcdatadto = new PcDataDTO();
		pcdatadto.setId((int) map.get("id"));
		pcdatadto.setCompany((String) map.get("company"));
		pcdatadto.setOs((String) map.get("os"));
		pcdatadto.setPc_name((String) map.get("pc_name"));
		pcdatadto.setPc_size((int) map.get("pc_size"));
		pcdatadto.setPrice((int) map.get("price"));
		pcdatadto.setDetail((String) map.get("detail"));
		pcdatadto.setProduct_stock((int) map.get("product_stock"));
		pcdatadto.setPcImg((String) map.get("pcimg"));
		pcdatadto.setPcImg2((String) map.get("pcimg2"));
		pcdatadto.setPcImg3((String) map.get("pcImg3"));

		return pcdatadto;
	}

	public int insertCheckSelectOne(PcDataDTO pcdatadto) throws EmptyResultDataAccessException {

		int selectResult = 0;

		try {
			int result = jdbc.queryForObject("select pcdata.id from pcdata where pc_name = ?", Integer.class,
					pcdatadto.getPc_name());
			selectResult = 1;
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}

		System.out.println("selectResult" + selectResult);
		return selectResult;
	}

	public PcDataDTO selectPcName(int productId) {
		Map<String, Object> map = jdbc.queryForMap("select * from pcdata where id = ?", productId);

		PcDataDTO pcdatadto = new PcDataDTO();
		pcdatadto.setPc_name((String) map.get("pc_name"));

		return pcdatadto;
	}

	public int updateOne(PurchaseDTO purchasedto, int productStock) {
		System.out.println("updateone到達");
		int result = jdbc.update("update pcdata set product_stock = product_stock+? where id = ?", productStock,
				purchasedto.getPcDataId());
		return result;
	}
	
	public PcDataDTO pcdataOne(int productId) {
		Map<String,Object> map = jdbc.queryForMap("select * from pcdata where id = ?",productId);
		
		PcDataDTO pcdatadto = new PcDataDTO();
		pcdatadto.setId((int)map.get("id"));
		pcdatadto.setCompany((String)map.get("company"));
		pcdatadto.setOs((String)map.get("os"));
		pcdatadto.setPc_name((String)map.get("pc_name"));
		pcdatadto.setPc_size((int)map.get("pc_size"));
		pcdatadto.setPrice((int)map.get("price"));
		pcdatadto.setDetail((String)map.get("detail"));
		pcdatadto.setPcImg((String)map.get("pcimg"));
		pcdatadto.setPcImg2((String)map.get("pcimg2"));
		pcdatadto.setPcImg3((String)map.get("pcimg3"));
		pcdatadto.setProduct_stock((int)map.get("product_stock"));
		
		return pcdatadto;
		
	}
	
	public int productEditOne(int productId,PcDataForm form) {
		int updataCheck = jdbc.update("update pcdata set company = ? ,os = ? ,pc_name = ? ,pc_size = ? ,price = ? ,detail = ? ,pcimg = ? ,pcimg2 = ? ,pcimg3 = ? ,product_stock = ? where id = ?",form.getCompany(),form.getOs(),form.getPc_name(),form.getPc_size(),form.getPrice(),form.getDetail(),form.getPcImg(),form.getPcImg2(),form.getPcImg3(),form.getProduct_stock(),productId);
		
		return updataCheck;
	}

}
