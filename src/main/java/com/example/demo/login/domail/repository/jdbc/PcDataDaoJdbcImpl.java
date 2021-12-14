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
import com.example.demo.login.domail.model.ProductListSearchForm;
import com.example.demo.login.domail.model.PurchaseDTO;
import com.example.demo.login.domail.repository.PcDataDao;

@Repository
public class PcDataDaoJdbcImpl implements PcDataDao {

	@Autowired
	JdbcTemplate jdbc;

	public int insertOne(PcDataDTO pcdatadto) {

		int rowNumber = jdbc.update(
				"insert into pcdata (id," + "company," + "os," + "pc_name," + "pc_size," + "price," + "detail,"
						+ "product_stock," + "cost," + "pcImg," + "pcImg2," + "pcImg3)" + "value(?,?,?,?,?,?,?,?,?,?,?,?)",
				pcdatadto.getId(), pcdatadto.getCompany(), pcdatadto.getOs(), pcdatadto.getPc_name(),
				pcdatadto.getPc_size(), pcdatadto.getPrice(), pcdatadto.getDetail(), pcdatadto.getProduct_stock(),
				pcdatadto.getCost(),pcdatadto.getPcImg(), pcdatadto.getPcImg2(), pcdatadto.getPcImg3());

		return rowNumber;
	}

	public List<PcDataDTO> selectMany() {

		List<Map<String, Object>> productList = jdbc.queryForList("select * from pcdata where product_stock >= 1 and listing_stop_check is null");

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
			pcdatadto.setListingStopCheck((String)map.get("listing_stop_check"));

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
	
	public List<PcDataDTO>  searchProductSelectMany(ProductListSearchForm form) {
	
		
		System.out.println("test1"+form.getProduct());
		System.out.println("test2"+form.getOs());
		System.out.println("test3"+form.getPriceFrom());
		System.out.println("test4"+form.getPriceTo());
		
		
		
		 StringBuilder sql = new StringBuilder();
		 	sql.append("select * from pcdata where product_stock >= 1");

		 	List<Object> list = new ArrayList<Object>();
		 	
		 	if((form.getProduct() != null) && (!form.getProduct().isEmpty())){
		 		System.out.println("product");
		 		sql.append(" and pcdata.pc_name like ?");
				list.add("%" + form.getProduct() + "%");
			}
			if((form.getOs() != null) && (!form.getOs().isEmpty())){
				System.out.println("os");
				sql.append(" and pcdata.os like ?");
				list.add("%" + form.getOs() + "%");
			}
			
			if((!form.getPriceFrom().isEmpty()) && (!form.getPriceTo().isEmpty())) {
				int priceFrom = Integer.parseInt(form.getPriceFrom());
				int priceTo = Integer.parseInt(form.getPriceTo());
				System.out.println("priceW");
				sql.append(" and pcdata.price >= ? and pcdata.price <= ?");
				list.add(priceFrom);
				list.add(priceTo);
			}else if((!form.getPriceFrom().isEmpty()) && (form.getPriceTo().isEmpty())) {
				int priceFrom = Integer.parseInt(form.getPriceFrom());
				System.out.println("priceFrom");
				sql.append(" and pcdata.price >= ?");
				list.add(priceFrom);
			}else if((form.getPriceFrom().isEmpty()) && (!form.getPriceTo().isEmpty())) {
				int priceTo = Integer.parseInt(form.getPriceTo());
				System.out.println("priceTo");
				sql.append(" and pcdata.price <= ?");
				list.add(priceTo);
			}
			Object[] addList = list.toArray(new Object[list.size()]);
			String sqlNew = sql.toString();
			List<Map<String,Object>> rowNumber = jdbc.queryForList(sqlNew,addList);
			List<PcDataDTO> pcdataList = new ArrayList<>();
			
			for(Map<String,Object> map : rowNumber) {
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
				
				pcdataList.add(pcdatadto);
			}
			
			return pcdataList;
	}
	
	public List<PcDataDTO> stockOutProductSelectMany() {
		List<Map<String, Object>> productList = jdbc.queryForList("select * from pcdata where product_stock = 0");

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
	
	public List<PcDataDTO> listingStopProductSelectMany() {
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
			pcdatadto.setListingStopCheck((String)map.get("listing_stop_check"));

			pcdatadtoList.add(pcdatadto);
		}

		return pcdatadtoList;

	}
	
	public int listingStopUpdateOne(int productId) {
		int result = jdbc.update("update pcdata set listing_stop_check = '出品停止' where id = ?",productId);
		
		return result;
	}
	
	public int listingRestartUpdateOne(int productId) {
		int result = jdbc.update("update pcdata set listing_stop_check = null where id = ?",productId);
		
		return result;
	}

}
