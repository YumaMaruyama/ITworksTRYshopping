package com.example.demo.login.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.login.domail.model.CartDTO;
import com.example.demo.login.domail.model.CartForm;
import com.example.demo.login.domail.model.CreditDTO;
import com.example.demo.login.domail.model.CreditForm;
import com.example.demo.login.domail.model.GroupOrder;
import com.example.demo.login.domail.model.PcDataDTO;
import com.example.demo.login.domail.model.PcDataForm;
import com.example.demo.login.domail.model.PcDetailDataDTO;
import com.example.demo.login.domail.model.PcDetailDataForm;
import com.example.demo.login.domail.model.PurchaseDTO;
import com.example.demo.login.domail.model.UsersDTO;
import com.example.demo.login.domail.model.UsersListDTO;
import com.example.demo.login.domail.model.UsersListForm;
import com.example.demo.login.domail.service.CartService;
import com.example.demo.login.domail.service.CreditService;
import com.example.demo.login.domail.service.CustomService;
import com.example.demo.login.domail.service.PcDataService;
import com.example.demo.login.domail.service.PurchaseService;
import com.example.demo.login.domail.service.Usege_usersService;
import com.example.demo.login.domail.service.UsersService;

@Controller
public class ShoppingController {

	@Autowired
	PcDataService pcdataService;
	@Autowired
	UsersService usersService;
	@Autowired
	Usege_usersService usegeService;
	@Autowired
	CreditService creditService;
	@Autowired
	CartService cartService;
	@Autowired
	PurchaseService purchaseService;
	@Autowired
	CustomService customService;

	@Autowired // Sessionが使用できる
	HttpSession session;

	@GetMapping("/usersList")
	public String getUsersList(@ModelAttribute UsersListForm form, Model model) {
		model.addAttribute("contents", "shopping/usersList::productListLayout_contents");

		String adminCheck = "admin";
		List<UsersListDTO> getUsers = usersService.selectMany(adminCheck);// 管理者以外のusersテーブル情報取得
		List<UsersListDTO> getUsegeUsers = usegeService.selectMany();

		List<UsersListDTO> usersDetailManyList = new ArrayList<>();// usersテーブルとusegeusersテーブルの情報をユーザーごとにusersDetailManyListに格納していく
		for (int i = 0; i < getUsers.size(); i++) {
			UsersListDTO userslistdto = new UsersListDTO();// usersテーブルのデータ取得用
			UsersListDTO usegeuserslistdto = new UsersListDTO();// usegeusersテーブルのデータを取得用
			UsersListDTO usersdetaillist = new UsersListDTO();// usersとusegeusersテーブルの情報を取得用
			userslistdto = getUsers.get(i);
			usegeuserslistdto = getUsegeUsers.get(i);
			int id = userslistdto.getId();
			String userId = userslistdto.getUserId();
			String userName = userslistdto.getUserName();
			Date birthday = usegeuserslistdto.getBirthday();
			String address = usegeuserslistdto.getAddress();

			usersdetaillist.setId(id);
			usersdetaillist.setUserId(userId);
			usersdetaillist.setUserName(userName);
			usersdetaillist.setBirthday(birthday);
			usersdetaillist.setAddress(address);
			System.out.println("id" + usersdetaillist.getId());
			usersDetailManyList.add(usersdetaillist);// 各ユーザーの情報を追加していく
		}

		model.addAttribute("usersDetailManyList", usersDetailManyList);

		return "shopping/productListLayout";
	}

	@GetMapping("/usersListDetail/{id}")
	public String GetUsersListDetail(@ModelAttribute UsersListForm form, @PathVariable("id") int id, Model model) {
		model.addAttribute("contents", "shopping/usersListDetail::productListLayout_contents");

	
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String user_id = auth.getName();
		int select_id = usersService.select_id(user_id);

		UsersListDTO getUsers = usersService.selectOne(id);// 管理者以外のusersテーブル情報取得
		UsersListDTO getUsegeUsers = usegeService.selectOne(id);

		UsersListDTO usersdetaillist = new UsersListDTO();// usersとusegeusersテーブルの情報を取得用

		String userId = getUsers.getUserId();
		String userName = getUsers.getUserName();
		Date birthday = getUsegeUsers.getBirthday();
		String address = getUsegeUsers.getAddress();

		usersdetaillist.setUserId(userId);
		usersdetaillist.setUserName(userName);
		usersdetaillist.setBirthday(birthday);
		usersdetaillist.setAddress(address);

		model.addAttribute("usersDetailList", usersdetaillist);

		return "shopping/productListLayout";

	}

	@GetMapping("/userPurchaseHistory/{id}")
	public String getUserPurchaseList(@ModelAttribute UsersListForm form, @PathVariable("id") int id,
			Model model) {
		model.addAttribute("contents", "shopping/userPurchaseHistory::productListLayout_contents");

		// ユーザーの購入商品情報リスト取得
		List<PurchaseDTO> purchasedtoList = purchaseService.selectHistory(id);
		System.out.println("text1");
		PurchaseDTO purchasedto = new PurchaseDTO();

		List<PurchaseDTO> allPurchaseList = new ArrayList<>();
		PurchaseDTO customList;
		// PurchaseDTO purchasedtoAdd;
		// 購入商品を一つづつ回して値を受け取る
		for (int i = 0; purchasedtoList.size() > i; i++) {
			PurchaseDTO purchasedtoAdd = new PurchaseDTO();
			System.out.println("test1");
			PurchaseDTO purchaseOne = purchasedtoList.get(i);
			purchasedtoAdd.setId(purchaseOne.getId());// カスタム情報取得に使用
			purchasedtoAdd.setPurchase_date(purchaseOne.getPurchase_date());
			purchasedtoAdd.setPcName(purchaseOne.getPcName());
			purchasedtoAdd.setPrice(purchaseOne.getPrice());
			purchasedtoAdd.setProduct_count(purchaseOne.getProduct_count());

			System.out.println("test2");
			// 購入商品ごとのカスタム情報を取得
			System.out.println("test3");
			int productId = purchasedtoAdd.getId();
			System.out.println("productId" + productId);
			System.out.println(id);
			int customId = purchasedtoAdd.getCustom_id();
			System.out.println("customId");
			// カスタムテーブルに購入チェックをつける
			int result = customService.purchaseCheckUpdate(id, purchasedtoAdd.getId());
			String nullCheck = "null";
			int getCustomId = customService.selectPurchaseCheck(id, purchasedtoAdd.getId(), nullCheck);
			System.out.println("getCustomId" + getCustomId);

			customList = customService.selectMany(getCustomId);
			System.out.println("costomList" + customList);

			purchasedtoAdd.setMemory(customList.getMemory());
			purchasedtoAdd.setHardDisc(customList.getHardDisc());
			purchasedtoAdd.setCpu(customList.getCpu());
			purchasedtoAdd.setCustomPrice(customList.getCustomPrice());
			purchasedtoAdd.setTotalPrice(
					purchaseOne.getProduct_count() * (customList.getCustomPrice() + purchaseOne.getPrice()));

			allPurchaseList.add(purchasedtoAdd);

			System.out.println("allPurchaseList" + allPurchaseList);

		}

		model.addAttribute("purchaseList", allPurchaseList);

		return "shopping/productListLayout";
	}

	@GetMapping("/admin")
	public String getAdmin(@ModelAttribute PcDataForm form, Model model) {
		model.addAttribute("contents", "shopping/admin::productListLayout_contents");
		return "shopping/productListLayout";
	}

	@PostMapping("/admin")
	public String postAdmin(@ModelAttribute @Validated(GroupOrder.class) PcDataForm form, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			System.out.println("バリデーションエラー到達");
			return getAdmin(form, model);
		}

		String img1 = form.getPcImg();
		String img2 = form.getPcImg2();
		String img3 = form.getPcImg3();

		String imgCheck1 = img1.substring(img1.length() - 4);
		String imgCheck2 = img2.substring(img2.length() - 4);
		String imgCheck3 = img3.substring(img3.length() - 4);
		String jpg = ".jpg";
		if (!imgCheck1.equals(jpg)) {
			model.addAttribute("imgResult1", "商品画像1はJPEG形式（最後が「.jpg」のもの）で入力してください");
			return getAdmin(form, model);
		}

		if (!imgCheck2.equals(jpg)) {
			model.addAttribute("imgResult2", "商品画像2はJPEG形式（最後が「.jpg」のもの）で入力してください");
			return getAdmin(form, model);
		}

		if (!imgCheck3.equals(jpg)) {
			model.addAttribute("imgResult3", "商品画像3はJPEG形式（最後が「.jpg」のもの）で入力してください");
			return getAdmin(form, model);
		}

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

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String user_id = auth.getName();
		int select_id = usersService.select_id(user_id);
		int result = pcdataService.insertCheckSelectOne(pcdatadto);
		if (result < 1) {
			System.out.println("insert到達");
			int pcData = pcdataService.insertOne(pcdatadto);
		}
		return getProductList(form, model);
	}

	@GetMapping("/productList")
	public String getProductList(@ModelAttribute PcDataForm form, Model model) {
		model.addAttribute("contents", "shopping/productList::productListLayout_contents");
		List<PcDataDTO> productList = pcdataService.selectMany();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());

		// @AutowiredがついてるのでUsersServiceのインスタンス（usersService）を使う
		UsersDTO headerName = usersService.getUser_name(auth.getName());
		session.setAttribute("sessionGetUser_name", headerName.getUser_name());
		System.out.println("getUser_name" + headerName.getUser_name());

		System.out.println("productList" + productList);

		model.addAttribute("productList", productList);

		return "shopping/productListLayout";
	}

	@GetMapping("/productDetail/{id}")
	public String getProductDetail(@ModelAttribute PcDetailDataForm form, PcDataForm pcdataform,
			HttpServletRequest request, Model model, @PathVariable("id") int id) {
		model.addAttribute("contents", "shopping/productDetail::productListLayout_contents");

		System.out.println("redirectcheck");

		PcDataDTO pcdatadtoOne = pcdataService.selectOne(id);
		String pcName = pcdatadtoOne.getPc_name();
		model.addAttribute("pcName", pcName);
		System.out.println("pcdatadtoOne" + pcdatadtoOne);
		model.addAttribute("pcdatadtoOne", pcdatadtoOne);

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String user_id = auth.getName();

		// ログインユーザーのID取得
		int select_id = usersService.select_id(user_id);

		int result = customService.selectCustomProduct_id(id, select_id);
		if (result == 0) {
			String defaultMemory = "4GB";
			String defaultHardDisc = "SSD";
			String defaultCpu = "CORE3";
			int customPrice = 0;
			int insertResult = customService.insertCustomData(id, select_id, defaultMemory, defaultHardDisc, defaultCpu,
					customPrice);
		}

		PcDetailDataDTO pcdetaildatadto = customService.selectOne(id, select_id);

		model.addAttribute("memory", pcdetaildatadto.getMemory());
		model.addAttribute("hardDisc", pcdetaildatadto.getHardDisc());
		model.addAttribute("cpu", pcdetaildatadto.getCpu());
		model.addAttribute("afterCustom", pcdatadtoOne.getPrice() + pcdetaildatadto.getCustomPrice());

		return "shopping/productListLayout";
	}

	@PostMapping("/productDetail/{id}")
	public String postProductDetailCustom(@ModelAttribute PcDetailDataForm form, PcDataForm pcdataform, Model model,
			@RequestParam("price") int price, @PathVariable("id") int id) {

		String memory = form.getMemory();
		String hardDisc = form.getHardDisc();
		String cpu = form.getCpu();

		PcDataDTO pcdatadtoOne = pcdataService.selectOne(id);
		System.out.println("pcdatadtoOne" + pcdatadtoOne);
		model.addAttribute("pcdatadtoOne", pcdatadtoOne);

		int getPrice = pcdatadtoOne.getPrice();

		if (memory.equals("8GB")) {
			getPrice = getPrice + 5000;
		}

		if (memory.equals("16GB")) {
			getPrice = getPrice + 15000;
		}

		if (memory.equals("32GB")) {
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

		model.addAttribute("customPrice", getPrice);

		return getFefore_purchase(pcdataform, form, model, getPrice, id);
	}

	@GetMapping("/before_purchase")
	public String getFefore_purchase(@ModelAttribute PcDataForm pcdataform, PcDetailDataForm form, Model model,
			int price, int id) {
		model.addAttribute("contents", "shopping/before_purchase::productListLayout_contents");

		return "shopping/productListLayout";
	}

	@GetMapping("credit")
	public String getCredit(@ModelAttribute CreditForm form, Model model) {
		model.addAttribute("contents", "shopping/credit::productListLayout_contents");

		return "shopping/productListLayout";
	}

	@PostMapping("credit")
	public String postCredit(@ModelAttribute CreditForm form, PcDataForm pcdataform, Model model,
			@PathVariable("totalPrice") int totalPrice) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String getName = auth.getName();

		CreditDTO creditdto = new CreditDTO();

		// creditdto.setExpire_date(form.getExpire_date());
		creditdto.setCardName(form.getCardName());
		creditdto.setCardNumber(form.getCardNumber());

		int result = creditService.insertOne(creditdto, getName);

		return getProductList(pcdataform, model);

	}

	@GetMapping("/clearing")
	public String getCardClearing(@ModelAttribute CreditForm form, Model model) {
		model.addAttribute("contents", "shopping/clearing::productListLayout_contents");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String getName = auth.getName();

		List<PcDataDTO> cartList = cartService.selectMany(getName);
		int totalPrice = 0;
		for (int i = 0; i < cartList.size(); i++) {
			PcDataDTO pcdatadto = cartList.get(i);
			totalPrice = totalPrice + pcdatadto.getProduct_count() * pcdatadto.getAfterCustomPrice();
			System.out.println("totalPrice" + totalPrice);
			model.addAttribute("totalPrice", totalPrice);
			// model.addAttribute("product_count",pcdatadto.getProduct_count());
			// form.setProduct_count(pcdatadto.getProduct_count());
			System.out.println("form" + form);
		}

		return "shopping/productListLayout";
	}

	@PostMapping("/clearing")
	public String getClearing(@ModelAttribute @Validated(GroupOrder.class) CreditForm form, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, @RequestParam("digits_3_code") String digits_3_code,
			@RequestParam("cardName") String cardName, @RequestParam("cardNumber") String cardNumber,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("contents", "shopping/confirmation::productListLayout_contents");
		if (bindingResult.hasErrors()) {
			System.out.println("バリデーションエラー");
			return getCardClearing(form, model);
		}

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String user_id = auth.getName();
		// ログインユーザーのID取得
		int select_id = usersService.select_id(user_id);

		HttpSession session = request.getSession();
		session.setAttribute("digits_3_code", digits_3_code);
		session.setAttribute("cardName", cardName);
		session.setAttribute("cardNumber", cardNumber);

		return "redirect:/confirmation";
	}

	@GetMapping("/after_purchase")
	public String getAfter_purchase(@ModelAttribute Model model) {
		model.addAttribute("contents", "shopping/after_purchase::productListLayout_contents");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String user_id = auth.getName();
		// ログインユーザーのID取得
		int select_id = usersService.select_id(user_id);

		int purchaseNumber = purchaseService.selectPurchaseNumber(select_id);

		List<PcDataDTO> purchaseList = purchaseService.selectMany(select_id, purchaseNumber);
		System.out.println("purchaseList" + purchaseList);
		PcDataDTO pcdatadto = new PcDataDTO();

		int totalPrice = 0;

		for (int i = 0; purchaseList.size() > i; i++) {
			pcdatadto = purchaseList.get(i);
			totalPrice = totalPrice + pcdatadto.getPrice() * pcdatadto.getProduct_count();
			System.out.println("totalPrice" + totalPrice);
			model.addAttribute("totalPrice", totalPrice);
		}
		model.addAttribute("purchaseList", purchaseList);

		String receivingAddress = usegeService.selectAddress(select_id);
		System.out.println("address" + receivingAddress);
		model.addAttribute("receivingAddress", receivingAddress);

		// 購入日取得
		Date purchaseDate = purchaseService.selectPurchaseDate(purchaseNumber);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(purchaseDate);
		calendar.add(Calendar.DATE, 3);
		purchaseDate = calendar.getTime();
		System.out.println("purchaseDate" + purchaseDate);

		Date purchaseDateNext = purchaseDate;
		calendar.setTime(purchaseDate);
		calendar.add(Calendar.DATE, 2);
		purchaseDateNext = calendar.getTime();
		System.out.println("purchaseDateNext" + purchaseDateNext);

		model.addAttribute("purchaseDate", purchaseDate);
		model.addAttribute("purchaseDateNext", purchaseDateNext);

		return "shopping/productListLayout";

	}

	@GetMapping("/cart")
	public String cart(@ModelAttribute CartForm form, Model model) {
		System.out.println("cart________");
		model.addAttribute("contents", "shopping/cart::productListLayout_contents");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String user_id = auth.getName();
		// ログインユーザーのみのカートの情報を取得
		List<PcDataDTO> cartList = cartService.selectMany(user_id);
		System.out.println("cartList" + cartList);
		if (cartList == null || cartList.size() == 0) {
			model.addAttribute("totalPrice", 0);
		} else {
			int totalPrice = 0;
			for (int i = 0; i < cartList.size(); i++) {
				PcDataDTO pcdatadto = cartList.get(i);
				totalPrice = totalPrice
						+ pcdatadto.getProduct_count() * (pcdatadto.getPrice() + pcdatadto.getCustomPrice());
				System.out.println("テストtotalPrice" + totalPrice);
				model.addAttribute("totalPrice", totalPrice);

			}
		}

		System.out.println("cartList " + cartList);
		model.addAttribute("cartList", cartList);

		return "shopping/productListLayout";
	}

	@GetMapping(value = "/cart/{id}", params = "customUpdate")
	public String customUpdate(@ModelAttribute PcDetailDataForm form, RedirectAttributes redirectattributes,
			HttpServletRequest request, HttpServletResponse response, Model model, @PathVariable("id") int id) {
		String memory = form.getMemory();
		String hardDisc = form.getHardDisc();
		String cpu = form.getCpu();

		PcDetailDataDTO pcdetaildatadto = new PcDetailDataDTO();
		pcdetaildatadto.setMemory(form.getMemory());
		pcdetaildatadto.setHardDisc(form.getHardDisc());
		pcdetaildatadto.setMemory(form.getCpu());

		PcDataDTO pcdatadto = pcdataService.selectOne(id);
		int getPrice = pcdatadto.getPrice();
		int priceSumCustom = pcdatadto.getPrice();

		int customPrice = 0;

		if (memory.equals("8GB")) {
			getPrice = getPrice + 5000;
			customPrice = customPrice + 5000;
		}

		if (memory.equals("16GB")) {
			getPrice = getPrice + 15000;
			customPrice = customPrice + 15000;
		}

		if (memory.equals("32GB")) {
			getPrice = getPrice + 40000;
			customPrice = customPrice + 40000;
		}

		if (hardDisc.equals("HDD")) {
			getPrice = getPrice + 1000;
			customPrice = customPrice + 1000;
		}

		if (cpu.equals("CORE5")) {
			getPrice = getPrice + 20000;
			customPrice = customPrice + 20000;
		}

		if (cpu.equals("CORE7")) {
			getPrice = getPrice + 40000;
			customPrice = customPrice + 40000;
		}

		if (cpu.equals("CORE9")) {
			getPrice = getPrice + 70000;
			customPrice = customPrice + 70000;
		}

		if (cpu.equals("Ryzen3")) {
			getPrice = getPrice + 10000;
			customPrice = customPrice + 10000;
		}

		if (cpu.equals("Ryzen5")) {
			getPrice = getPrice + 50000;
			customPrice = customPrice + 50000;
		}

		if (cpu.equals("Ryzen7")) {
			getPrice = getPrice + 70000;
			customPrice = customPrice + 70000;
		}

		if (cpu.equals("Ryzen9")) {
			getPrice = getPrice + 100000;
			customPrice = customPrice + 100000;
		}

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String user_id = auth.getName();

		// ログインユーザーのID取得
		int select_id = usersService.select_id(user_id);

		int result = customService.UpdateOne(id, select_id, memory, hardDisc, cpu, customPrice);

		HttpSession session = request.getSession();
		session.setAttribute("afterCustom", priceSumCustom + customPrice);
		session.setAttribute("memory", memory);
		session.setAttribute("hardDisc", hardDisc);
		session.setAttribute("cpu", cpu);

		model.addAttribute("afterCustom", priceSumCustom + customPrice);

		// カスタム後に商品詳細画面にリダイレクト
		return "redirect:/productDetail/{id}";
	}

	@GetMapping(value = "/cart/{id}", params = "cartAdd")
	public String getCart(@ModelAttribute CartForm form, Model model, RedirectAttributes redirectattributes,
			@PathVariable("id") int product_id) {
		model.addAttribute("contents", "shopping/cart::productListLayout_contents");

		CartDTO cartdto = new CartDTO();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String user_id = auth.getName();
		// ログインユーザーのID取得
		int select_id = usersService.select_id(user_id);
		// カートに追加する際の重複チェック
		int selectResult = cartService.selectOne(cartdto, product_id, select_id);

		// カートに入っていないデータを追加
		if (selectResult < 1) {
			System.out.println("カートにデータがない商品なのでインサート");
			int insertResult = cartService.insertOne(cartdto, product_id, select_id);
		}

		return "redirect:/cart";
	}

	@PostMapping(value = "/cart/{id}", params = "delete")
	public String postCartDetail(@ModelAttribute @Validated CartForm form, BindingResult bindingResult, Model model,
			RedirectAttributes redirectattributes, @PathVariable("id") int id) {

		if (bindingResult.hasErrors()) {
			System.out.println("バリデーションエラー到達");
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			System.out.println("auth" + auth.getName());
			String getName = auth.getName();

			int getId = usersService.select_id(getName);

			int result = cartService.deleteOne(id, getId);
			return cart(form, model);
		}

		System.out.println("test");
		String getText = form.getProduct_count();
		String getText2 = String.valueOf(getText);
		System.out.println("getText2" + getText2);
		if (getText2 == null) {

		}

		System.out.println("product_id" + id);
		model.addAttribute("contents", "shopping/cart::productListLayout_contents");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String getName = auth.getName();

		int getId = usersService.select_id(getName);

		int deleteCartResult = cartService.deleteOne(id, getId);

		int deleteCustomResult = customService.deleteCustomOne(id, getId);

		return "redirect:/cart";
	}
	// clearingからproductReceiving

	@PostMapping(value = "/cart/{id}", params = "countUpdate")
	public String postCartCountUpdate(@ModelAttribute @Validated CartForm form, BindingResult bindingResult,
			Model model, RedirectAttributes redirectattributes, @PathVariable("id") int productId) {
		System.out.println("countUpdate到達");

		if (bindingResult.hasErrors()) {
			System.out.println("バリデーションエラー到達");

			return cart(form, model);
		}

		String productCount = form.getProduct_count();
		int newProductCount = Integer.parseInt(productCount);
		System.out.println("newProductCount" + newProductCount);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String getName = auth.getName();

		int userId = usersService.select_id(getName);
		System.out.println("userId" + userId);
		System.out.println("productId" + productId);
		int result = cartService.updateOne(productId, newProductCount, userId);

		return "redirect:/cart";

	}

	@GetMapping("/confirmation")
	public String getConfirmation(@ModelAttribute PcDataForm from, CreditForm creditForm, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		model.addAttribute("contents", "shopping/confirmation::productListLayout_contents");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String getName = auth.getName();

		List<PcDataDTO> cartList = cartService.selectMany(getName);
		int totalPrice = 0;
		for (int i = 0; i < cartList.size(); i++) {
			PcDataDTO pcdatadto = cartList.get(i);
			totalPrice = totalPrice + pcdatadto.getProduct_count() * pcdatadto.getAfterCustomPrice();
			System.out.println("totalPrice" + totalPrice);
			model.addAttribute("totalPrice", totalPrice);
		}

		int getId = usersService.select_id(getName);

		System.out.println("cartList " + cartList);
		model.addAttribute("cartList", cartList);

		HttpSession session = request.getSession();
		String digits_3_code = (String) session.getAttribute("digits_3_code");
		String cardName = (String) session.getAttribute("cardName");
		String cardNumber = (String) session.getAttribute("cardNumber");
		model.addAttribute("digits_3_code", digits_3_code);
		model.addAttribute("cardName", cardName);
		model.addAttribute("cardNumber", cardNumber);

		return "shopping/productListLayout";
	}

	@PostMapping("/confirmation")
	public String postConfirmation(@ModelAttribute PcDataForm form, RedirectAttributes redirectattributes,
			@RequestParam("digits_3_code") String digits_3_code, @RequestParam("cardName") String cardName,
			@RequestParam("cardNumber") String cardNumber, @RequestParam("totalPrice") int totalPrice,
			HttpServletRequest request, HttpServletResponse response, Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String getName = auth.getName();

		int select_id = usersService.select_id(getName);
		// 各商品のユーザーが購入した数を取得
		boolean result = cartService.selectProductCount(select_id);

		if (result == false) {
			redirectattributes.addFlashAttribute("result", "在庫数が購入数より少ない商品、又は0の商品があります。再度ご確認の上決済画面へ進んでください。");
			return "redirect:/cart";
		}

		CreditDTO creditdto = new CreditDTO();
		creditdto.setDigits_3_code(digits_3_code);
		creditdto.setCardName(cardName);
		creditdto.setCardNumber(cardNumber);
		// ここに商品購入したらその商品IDを入れる(customDBに（あらたにcolumnつくってね）)
		// そうしたらnewcolumn > 0でカスタムDBからカスタムしているだけの奴をのぞいた列を取得できる

		int insertResult = creditService.clearingInsertOne(creditdto, select_id, totalPrice);

		int purchaseCreditId = creditService.selectMaxId();

		List<CartDTO> cartList = cartService.purchaseSelectMany(select_id);

		PurchaseDTO purchasedto = new PurchaseDTO();

		for (int i = 0; i < cartList.size(); i++) {

			CartDTO cartdto = cartList.get(i);
			int cartId = cartdto.getId();
			// int selectProductId = cartService.selectProductId(cartId);
			int purchaseId = cartdto.getProduct_id();
			int customId = customService.selectCustomId(purchaseId, select_id);
			int purchaseCount = cartdto.getProduct_count();
			int purchaseInsertResult = purchaseService.insert(purchasedto, purchaseId, purchaseCount, select_id,
					purchaseCreditId, customId);

		}

		// return "redirect:/after_purchase";
		return getAfter_purchase(model);
	}

	@GetMapping("/purchaseHistory")
	public String getPurchaseHistory(@ModelAttribute PcDataForm form, Model model) {
		model.addAttribute("contents", "shopping/purchaseHistory::productListLayout_contents");

		System.out.println("text1");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String getName = auth.getName();

		int select_id = usersService.select_id(getName);
		System.out.println("text1");
		// 購入商品情報リスト取得
		List<PurchaseDTO> purchasedtoList = purchaseService.selectHistory(select_id);
		System.out.println("text1");
		PurchaseDTO purchasedto = new PurchaseDTO();

		List<PurchaseDTO> allPurchaseList = new ArrayList<>();
		PurchaseDTO customList;
		// PurchaseDTO purchasedtoAdd;
		// 購入商品を一つづつ回して値を受け取る
		for (int i = 0; purchasedtoList.size() > i; i++) {
			PurchaseDTO purchasedtoAdd = new PurchaseDTO();
			System.out.println("test1");
			PurchaseDTO purchaseOne = purchasedtoList.get(i);
			purchasedtoAdd.setId(purchaseOne.getId());// カスタム情報取得に使用
			purchasedtoAdd.setPurchase_date(purchaseOne.getPurchase_date());
			purchasedtoAdd.setPcName(purchaseOne.getPcName());
			purchasedtoAdd.setPrice(purchaseOne.getPrice());
			purchasedtoAdd.setProduct_count(purchaseOne.getProduct_count());
			// purchasedtoAdd.setCustom_id(purchaseOne.getCustom_id());
			System.out.println("test2");
			// 購入商品ごとのカスタム情報も取り出す
			System.out.println("test3");
			int productId = purchasedtoAdd.getId();
			System.out.println("productId" + productId);
			System.out.println(select_id);
			int customId = purchasedtoAdd.getCustom_id();
			System.out.println("customId");
			// カスタムテーブルに購入チェックを入れる
			int result = customService.purchaseCheckUpdate(select_id, purchasedtoAdd.getId());
			String nullCheck = "null";
			int getCustomId = customService.selectPurchaseCheck(select_id, purchasedtoAdd.getId(), nullCheck);
			System.out.println("getCustomId" + getCustomId);
			// ここにはカスタムID(purchaseDB)を入れる 購入のIDを入れているからでないんだよ
			// 上のhistoryでもカスタムIDを取得できないからカスタムテーブルに商品購入時に購入マークを入れる

			customList = customService.selectMany(getCustomId);
			System.out.println("costomList" + customList);

			System.out.println("test4");
			System.out.println("text1");

			System.out.println("test5");

			purchasedtoAdd.setMemory(customList.getMemory());
			purchasedtoAdd.setHardDisc(customList.getHardDisc());
			purchasedtoAdd.setCpu(customList.getCpu());
			purchasedtoAdd.setCustomPrice(customList.getCustomPrice());
			purchasedtoAdd.setTotalPrice(
					purchaseOne.getProduct_count() * (customList.getCustomPrice() + purchaseOne.getPrice()));
			System.out.println("test6");

			allPurchaseList.add(purchasedtoAdd);
			System.out.println("test7");
			System.out.println("allPurchaseList" + allPurchaseList);

		}

		model.addAttribute("purchaseList", allPurchaseList);

		return "shopping/productListLayout";

	}

	// ログアウト用メソッド
	@GetMapping("logout")
	public String getLogout() {

		// ログイン画面にリダイレクト
		return "redirect:/login";
	}
}
