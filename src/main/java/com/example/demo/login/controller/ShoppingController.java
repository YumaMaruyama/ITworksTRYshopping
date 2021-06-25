package com.example.demo.login.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.login.domail.model.CartDTO;
import com.example.demo.login.domail.model.CartForm;
import com.example.demo.login.domail.model.CreditDTO;
import com.example.demo.login.domail.model.CreditForm;
import com.example.demo.login.domail.model.PcDataDTO;
import com.example.demo.login.domail.model.PcDataForm;
import com.example.demo.login.domail.model.PcDetailDataForm;
import com.example.demo.login.domail.model.UsersDTO;
import com.example.demo.login.domail.service.CartService;
import com.example.demo.login.domail.service.CreditService;
import com.example.demo.login.domail.service.PcDataService;
import com.example.demo.login.domail.service.UsersService;

@Controller
public class ShoppingController {

	@Autowired
	PcDataService pcdataService;
	@Autowired
	UsersService usersService;
	@Autowired
	CreditService creditService;
	@Autowired
	CartService cartService;

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
		pcdatadto.setPc_name(form.getPc_name());
		pcdatadto.setPc_size(form.getPc_size());
		pcdatadto.setPrice(form.getPrice());
		pcdatadto.setDetail(form.getDetail());
		pcdatadto.setProduct_stock(form.getProduct_stock());
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
				session.setAttribute("sessionGetUser_name", headerName.getUser_name());
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

	@PostMapping("/productDetail/{id}")
	public String postProductDetailCustom(@ModelAttribute PcDetailDataForm form,PcDataForm pcdataform, Model model,@RequestParam("price") int price,@PathVariable("id") int id) {

		String gb = form.getGb();
		String hardDisc = form.getHardDisc();
		String cpu = form.getCpu();

		PcDataDTO pcdatadtoOne = pcdataService.selectOne(id);
		System.out.println("pcdatadtoOne" + pcdatadtoOne);
		model.addAttribute("pcdatadtoOne",pcdatadtoOne);

		int getPrice = pcdatadtoOne.getPrice();



		if (gb.equals("8GB")) {
			getPrice = getPrice + 5000;
		}

		if (gb.equals("16GB")) {
			getPrice = getPrice + 15000;
		}

		if (gb.equals("32GB")) {
			getPrice = getPrice + 40000;
		}



		if (hardDisc.equals("HDD")) {
			getPrice = getPrice + 1000;
		}



		if (cpu.equals("CORE5")) {
			getPrice = getPrice + 20000;
		}

		if (cpu.equals("CORE7")) {
			getPrice = getPrice + 40000;
		}

		if (cpu.equals("CORE9")) {
			getPrice = getPrice + 70000;
		}

		if (cpu.equals("Ryzen3")) {
			getPrice = getPrice + 10000;
		}

		if (cpu.equals("Ryzen5")) {
			getPrice = getPrice + 50000;
		}

		if (cpu.equals("Ryzen7")) {
			getPrice = getPrice + 70000;
		}

		if (cpu.equals("Ryzen9")) {
			getPrice = getPrice + 100000;
		}

		model.addAttribute("customPrice",getPrice);

		return getFefore_purchase(pcdataform,form,model,getPrice,id);
	}

	@GetMapping("/before_purchase")
	public String getFefore_purchase(@ModelAttribute PcDataForm pcdataform,PcDetailDataForm form, Model model,int price,int id) {
		model.addAttribute("contents", "shopping/before_purchase::productListLayout_contents");


		return "shopping/productListLayout";
	}

	@GetMapping("credit")
	public String getCredit(@ModelAttribute CreditForm form,Model model) {
		model.addAttribute("contents", "shopping/credit::productListLayout_contents");

		return "shopping/productListLayout";
	}

	@PostMapping("credit")
	public String postCredit(@ModelAttribute CreditForm form,PcDataForm pcdataform,Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	System.out.println("auth" + auth.getName());
	String getName = auth.getName();

	CreditDTO creditdto = new CreditDTO();

	creditdto.setExpire_date(form.getExpire_date());
	creditdto.setCardName(form.getCardName());
	creditdto.setCardNumber(form.getCardNumber());

	int result = creditService.insertOne(creditdto,getName);

	return getProductList(pcdataform,model);

	}

	@GetMapping("/clearing")
	public String getCardClearing(@ModelAttribute CreditForm form, Model model) {
		model.addAttribute("contents", "shopping/clearing::productListLayout_contents");
		List<PcDataDTO> cartList = cartService.cartDataSelectMany();
		model.addAttribute("cartList",cartList);
		PcDataDTO pcdatadto = new PcDataDTO();
		int totalPrice = pcdatadto.getTotalPrice();
		model.addAttribute("totalPrice",totalPrice);
		return "shopping/productListLayout";
	}

	@PostMapping("/clearing")
	public String getClearing(@ModelAttribute @Validated CreditForm form,BindingResult bindingResult,Model model) {
		model.addAttribute("contents","shopping/clearing::productListLayout_contents");
		if(bindingResult.hasErrors()) {
			System.out.println("バリデーションエラー");
			return getCardClearing(form,model);
		}

		return getAfter_purchase(model);
	}

	@GetMapping("/after_purchase")
	public String getAfter_purchase(@ModelAttribute Model model) {
		model.addAttribute("contents","shopping/after_purchase::productListLayout_contents");



		return "shopping/productListLayout";


	}

	@GetMapping("/clearing/{id}")
	public String getClearing(@ModelAttribute CreditForm form,Model model,@RequestParam("customPrice") int customPrice,@PathVariable("id") int id) {
		model.addAttribute("contents", "shopping/clearing::productListLayout_contents");

		System.out.println(id);//商品番号
		System.out.println(customPrice);//決済金額
		model.addAttribute("customPrice",customPrice);
		return "shopping/productListLayout";
	}

	@PostMapping("/clearing/{id}")
	public String postClearing(@ModelAttribute CreditForm form,Model model,@RequestParam("customPrice") int customPrice,@PathVariable("id") int id) {
		model.addAttribute("contents", "shopping/productReceiving::productListLayout_contents");
		CreditDTO creditdto = new CreditDTO();
		//ユーザーが入力した決済番号
		creditdto.setExpire_date(form.getExpire_date());
		creditdto.setCardName(form.getCardName());
		creditdto.setCardNumber(form.getCardNumber());


		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String getName = auth.getName();

		//決済でカード内容入力したユーザーがクレジット登録をしているか確認　そのユーザのユーザIDのカード情報が取れていればその情報と入力した情報を比べる
		CreditDTO getCredit = creditService.selectOne(getName);


		return "shopping/productListLayout";

	}

	@GetMapping("/cart/{id}")
	public String getCart(@ModelAttribute CartForm form,Model model,@PathVariable("id") int product_id) {
		model.addAttribute("contents", "shopping/cart::productListLayout_contents");

		CartDTO cartdto = new CartDTO();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String getName = auth.getName();

		int result = cartService.insertOne(cartdto,product_id,getName);

		List<PcDataDTO> cartList = cartService.selectMany(getName);
		for(int i = 0; i < 1; i++) {
			PcDataDTO pcdatadto = cartList.get(i);
			int totalPrice = pcdatadto.getTotalPrice();
			System.out.println("totalPrice" + totalPrice);
			model.addAttribute("totalPrice",totalPrice);
		}

		System.out.println("cartList " + cartList);
		model.addAttribute("cartList",cartList);
		return "shopping/productListLayout";
	}
	//clearingからproductReceiving



	//ログアウト用メソッド
			@GetMapping("logout")
			public String getLogout() {

				//ログイン画面にリダイレクト
				return "redirect:/login";
			}
}
