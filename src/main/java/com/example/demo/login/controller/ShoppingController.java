package com.example.demo.login.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.login.domail.model.PcDataDTO;
import com.example.demo.login.domail.model.PcDataForm;
import com.example.demo.login.domail.model.PcDetailDataForm;
import com.example.demo.login.domail.model.UsersDTO;
import com.example.demo.login.domail.service.PcDataService;
import com.example.demo.login.domail.service.UsersService;

@Controller
public class ShoppingController {

	@Autowired
	PcDataService pcdataService;
	@Autowired
	UsersService usersService;

	@Autowired //Sessionが使用できる
	HttpSession session;

	@GetMapping("/admin")
	public String getAdmin(@ModelAttribute PcDataForm form, Model model) {
		model.addAttribute("contents", "shopping/admin::productListLayout_contents");
		return "shopping/productListLayout";
	}

	@PostMapping("/admin")
	public String postAdmin(@ModelAttribute PcDataForm form, Model model) {

		PcDataDTO pcdatadto = new PcDataDTO();
		pcdatadto.setCompany(form.getCompany());
		pcdatadto.setOs(form.getOs());
		pcdatadto.setPcName(form.getPcName());
		pcdatadto.setPcSize(form.getPcSize());
		pcdatadto.setPrice(form.getPrice());
		pcdatadto.setDetail(form.getDetail());
		pcdatadto.setPcImg(form.getPcImg());
		pcdatadto.setPcImg2(form.getPcImg2());
		pcdatadto.setPcImg3(form.getPcImg3());


		int pcData = pcdataService.insertOne(pcdatadto);

		return getProductList(form,model);
	}

	@GetMapping("/productList")
	public String getProductList(@ModelAttribute PcDataForm form, Model model) {
		model.addAttribute("contents", "shopping/productList::productListLayout_contents");
		List<PcDataDTO> productList = pcdataService.selectMany();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());

		//@AutowiredがついてるのでUsersServiceのインスタンス（usersService）を使う
				UsersDTO headerName = usersService.getUser_name(auth.getName());
				System.out.println("headerName" + headerName);

				session.setAttribute("getUser_name", headerName.getUser_name());
				System.out.println("getUser_name" + headerName.getUser_name());

		System.out.println("productList" + productList);

		model.addAttribute("productList", productList);

		return "shopping/productListLayout";
	}

	//	@PostMapping("/productList")
	//	public String postProductList(@ModelAttribute Model model) {
	//
	//	}

	@GetMapping("/productDetail/{id}")
	public String getProductDetail(@ModelAttribute PcDetailDataForm form,PcDataForm pcdataform, Model model,@PathVariable("id") int id) {
		model.addAttribute("contents", "shopping/productDetail::productListLayout_contents");

		PcDataDTO pcdatadtoOne = pcdataService.selectOne(id);
		System.out.println("pcdatadtoOne" + pcdatadtoOne);
		model.addAttribute("pcdatadtoOne",pcdatadtoOne);


		return "shopping/productListLayout";
	}

	@PostMapping("/productDetail")
	public String postProductDetailCustom(@ModelAttribute PcDetailDataForm form,PcDataForm pcdataform, Model model,@RequestParam("price") int price) {

		String gb = form.getGb();
		String hardDisc = form.getHardDisc();
		String cpu = form.getCpu();


		if (gb.equals("4GB")) {

		}

		if (gb.equals("8GB")) {
			price = price + 5000;
		}

		if (gb.equals("16GB")) {
			price = price + 15000;
		}

		if (gb.equals("32GB")) {
			price = price + 40000;
		}

		if (hardDisc.equals("SSD")) {

		}

		if (hardDisc.equals("HDD")) {
			price = price + 1000;
		}

		if (cpu.equals("CORE3")) {

		}

		if (cpu.equals("CORE5")) {
			price = price + 20000;
		}

		if (cpu.equals("CORE7")) {
			price = price + 40000;
		}

		if (cpu.equals("CORE9")) {
			price = price + 70000;
		}

		if (cpu.equals("Ryzen3")) {
			price = price + 10000;
		}

		if (cpu.equals("Ryzen5")) {
			price = price + 50000;
		}

		if (cpu.equals("Ryzen7")) {
			price = price + 70000;
		}

		if (cpu.equals("Ryzen9")) {
			price = price + 100000;
		}


		return getFefore_purchase(pcdataform,form,model,price);
	}

	@GetMapping("/before_purchase")
	public String getFefore_purchase(@ModelAttribute PcDataForm pcdataform,PcDetailDataForm form, Model model,int price) {
		model.addAttribute("contents", "shopping/before_purchase::productListLayout_contents");

		return "shopping/productListLayout";
	}

	@GetMapping("test")
	public String getTest(Model model) {
		return "shopping/test";
	}

}
