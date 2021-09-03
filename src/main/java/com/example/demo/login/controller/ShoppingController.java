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

import com.example.demo.login.domail.model.CancelDTO;
import com.example.demo.login.domail.model.CancelForm;
import com.example.demo.login.domail.model.CancelInTransactionForm;
import com.example.demo.login.domail.model.CancelNextForm;
import com.example.demo.login.domail.model.CartDTO;
import com.example.demo.login.domail.model.CartForm;
import com.example.demo.login.domail.model.CouponDTO;
import com.example.demo.login.domail.model.CouponForm;
import com.example.demo.login.domail.model.CreditDTO;
import com.example.demo.login.domail.model.CreditForm;
import com.example.demo.login.domail.model.CustomDTO;
import com.example.demo.login.domail.model.GroupOrder;
import com.example.demo.login.domail.model.InquiryAllDTO;
import com.example.demo.login.domail.model.InquiryDTO;
import com.example.demo.login.domail.model.InquiryForm;
import com.example.demo.login.domail.model.InquiryReplyDTO;
import com.example.demo.login.domail.model.MenberCouponDTO;
import com.example.demo.login.domail.model.MenberCouponForm;
import com.example.demo.login.domail.model.NewsDTO;
import com.example.demo.login.domail.model.NewsForm;
import com.example.demo.login.domail.model.PcDataDTO;
import com.example.demo.login.domail.model.PcDataForm;
import com.example.demo.login.domail.model.PcDetailDataDTO;
import com.example.demo.login.domail.model.PcDetailDataForm;
import com.example.demo.login.domail.model.PointRateChangeForm;
import com.example.demo.login.domail.model.PointUseForm;
import com.example.demo.login.domail.model.PurchaseDTO;
import com.example.demo.login.domail.model.ReviewDTO;
import com.example.demo.login.domail.model.ReviewForm;
import com.example.demo.login.domail.model.Usege_usersDTO;
import com.example.demo.login.domail.model.UserEditForm;
import com.example.demo.login.domail.model.UsersDTO;
import com.example.demo.login.domail.model.UsersListDTO;
import com.example.demo.login.domail.model.UsersListForm;
import com.example.demo.login.domail.service.CancelService;
import com.example.demo.login.domail.service.CartService;
import com.example.demo.login.domail.service.CouponService;
import com.example.demo.login.domail.service.CreditService;
import com.example.demo.login.domail.service.CustomService;
import com.example.demo.login.domail.service.InquiryReplyService;
import com.example.demo.login.domail.service.InquiryService;
import com.example.demo.login.domail.service.MenberCouponService;
import com.example.demo.login.domail.service.NewsService;
import com.example.demo.login.domail.service.PcDataService;
import com.example.demo.login.domail.service.PointRateService;
import com.example.demo.login.domail.service.PurchaseService;
import com.example.demo.login.domail.service.ReviewService;
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
	@Autowired
	InquiryService inquiryService;
	@Autowired
	NewsService newsService;
	@Autowired
	ReviewService reviewService;
	@Autowired
	CancelService cancelService;
	@Autowired
	CouponService couponService;
	@Autowired
	InquiryReplyService inquiryreplyService;
	@Autowired
	MenberCouponService menberCouponService;
	@Autowired
	PointRateService pointRateService;

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

	@PostMapping(value = "usersList", params = "unsubscribe")
	public String postUsersListUnsubscribe(@ModelAttribute UsersListForm form, @RequestParam("id") int id,
			RedirectAttributes redirectAttributes, Model model) {
		model.addAttribute("contents", "shopping/usersList::productListLayout_contents");
		System.out.println("testid" + id);
		usersService.deleteOne(id);// usersテーブル情報を削除
		usegeService.deleteOne(id);// usege_usersテーブル情報を削除

		return "redirect:/usersList";
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
		model.addAttribute("userId", id);// ユーザを退会させるためのユーザーのID
		return "shopping/productListLayout";

	}

//	@GetMapping("/userPurchaseHistory/{id}")
//	public String getUserPurchaseList(@ModelAttribute UsersListForm form, @PathVariable("id") int id, Model model) {
//		model.addAttribute("contents", "shopping/userPurchaseHistory::productListLayout_contents");
//
//		// ユーザーの購入商品情報リスト取得
//		List<PurchaseDTO> purchasedtoList = purchaseService.selectHistory(id);
//		System.out.println("text1");
//		PurchaseDTO purchasedto = new PurchaseDTO();
//
//		List<PurchaseDTO> allPurchaseList = new ArrayList<>();
//		PurchaseDTO customList;
//		// PurchaseDTO purchasedtoAdd;
//		// 購入商品を一つづつ回して値を受け取る
//		for (int i = 0; purchasedtoList.size() > i; i++) {
//			PurchaseDTO purchasedtoAdd = new PurchaseDTO();
//			System.out.println("test1");
//			PurchaseDTO purchaseOne = purchasedtoList.get(i);
//			purchasedtoAdd.setId(purchaseOne.getId());// カスタム情報取得に使用
//			purchasedtoAdd.setPurchase_date(purchaseOne.getPurchase_date());
//			purchasedtoAdd.setPcName(purchaseOne.getPcName());
//			purchasedtoAdd.setPrice(purchaseOne.getPrice());
//			purchasedtoAdd.setProduct_count(purchaseOne.getProduct_count());
//			purchasedtoAdd.setPurchaseCheck(purchaseOne.getPurchaseId());
//
//			System.out.println("test2");
//			// 購入商品ごとのカスタム情報を取得
//			System.out.println("test3");
//			int productId = purchasedtoAdd.getId();
//			System.out.println("productId" + productId);
//			System.out.println(id);
//			int customId = purchasedtoAdd.getCustom_id();
//			System.out.println("customId");
//			// カスタムテーブルに購入チェックをつける
//			int result = customService.purchaseCheckUpdate(id, purchasedtoAdd.getId());
//			String nullCheck = "null";
//			int getCustomId = customService.selectPurchaseCheck(id, purchasedtoAdd.getId(),
//					purchasedtoAdd.getPurchaseCheck(), nullCheck);
//			System.out.println("getCustomId" + getCustomId);
//
//			customList = customService.selectMany(getCustomId);
//			System.out.println("costomList" + customList);
//
//			purchasedtoAdd.setMemory(customList.getMemory());
//			purchasedtoAdd.setHardDisc(customList.getHardDisc());
//			purchasedtoAdd.setCpu(customList.getCpu());
//			purchasedtoAdd.setCustomPrice(customList.getCustomPrice());
//			purchasedtoAdd.setTotalPrice(
//					purchaseOne.getProduct_count() * (customList.getCustomPrice() + purchaseOne.getPrice()));
//
//			allPurchaseList.add(purchasedtoAdd);
//
//			System.out.println("allPurchaseList" + allPurchaseList);
//
//		}
//
//		model.addAttribute("purchaseList", allPurchaseList);
//
//		return "shopping/productListLayout";
//	}

	@GetMapping("/editYour")
	public String getEditYour(@ModelAttribute UserEditForm form, Model model) {
		model.addAttribute("contents", "shopping/editYour::productListLayout_contents");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();// メソッドに入ってきたユーザのuser_idを取得
		System.out.println("auth" + auth.getName());
		String userId = auth.getName();
		int selectId = usersService.select_id(userId);// 取得したuser_idでusersテーブルのidを取得

		UsersDTO usersdto = usersService.userInformationSelectOne(selectId);// usersテーブルから引数のidをもとに情報を取得
		Usege_usersDTO usegeusersdto = usegeService.userInformationSelectOne(selectId);// usege_usersテーブルから引数のidをもとに情報を取得

		UsersListDTO userslistdto = new UsersListDTO();
		userslistdto.setId(usersdto.getId());
		userslistdto.setUserId(usersdto.getUser_id());
		userslistdto.setUserName(usersdto.getUser_name());
		userslistdto.setBirthday(usegeusersdto.getBirthday());
		userslistdto.setAddress(usegeusersdto.getAddress());

		model.addAttribute("usersList", userslistdto);
		model.addAttribute("id", userslistdto.getId());

		int allProductCount = 0;
		int allTotalPrice = 0;

		List<PurchaseDTO> purchasedtoList = purchaseService.selectHistory(selectId);// メソッドに入ったユーザーの購入情報を取得
		List<PurchaseDTO> allPurchaseList = new ArrayList<>();
		PurchaseDTO customList;
		// 購入商品を一つづつ回して値を受け取る
		if (purchasedtoList.size() > 0) {
			for (int i = 0; purchasedtoList.size() > i; i++) {
				PurchaseDTO purchasedtoAdd = new PurchaseDTO();
				PurchaseDTO purchaseOne = purchasedtoList.get(i);
				purchasedtoAdd.setId(purchaseOne.getId());// カスタム情報取得に使用
				purchasedtoAdd.setCancelCheck(purchaseOne.getCancelCheck());
				purchasedtoAdd.setPrice(purchaseOne.getPrice());
				purchasedtoAdd.setProduct_count(purchaseOne.getProduct_count());
				purchasedtoAdd.setPurchaseCheck(purchaseOne.getPurchaseCheck());
				// 購入商品ごとのカスタム情報も取り出す
				int productId = purchasedtoAdd.getId();
				// int customId = purchasedtoAdd.getCustom_id();

				String nullCheck = "null";
				int getCustomId = customService.selectPurchaseCheck(selectId, productId,
						purchasedtoAdd.getPurchaseCheck(), nullCheck);

				customList = customService.selectMany(getCustomId);// customテーブルのIDでカスタム情報を取得
				purchasedtoAdd.setCustomPrice(customList.getCustomPrice());
				purchasedtoAdd.setTotalPrice(
						purchaseOne.getProduct_count() * (customList.getCustomPrice() + purchaseOne.getPrice()));
				allTotalPrice = allTotalPrice + purchasedtoAdd.getTotalPrice();// ユーザーの購入した商品の合計金額を取得
				allProductCount = allProductCount + purchasedtoAdd.getProduct_count();// ユーザーの購入した商品の数を取得
				model.addAttribute("purchaseCount", allProductCount);
				model.addAttribute("allTotalPrice", allTotalPrice);
//			int priceRank = allTotalPrice * 10;
//			int countRank = allProductCount * 30000;
//			int rankPoint = priceRank + countRank;
//			System.out.println(rankPoint);
				if ((allTotalPrice > 0) && (allTotalPrice < 50000)) {
					model.addAttribute("rankPoint", "アマチュアランク");
				} else if ((allTotalPrice >= 50000) && (allTotalPrice < 100000)) {
					model.addAttribute("rankPoint", "プロランク");
				} else if ((allTotalPrice >= 100000) && (allTotalPrice < 200000)) {
					model.addAttribute("rankPoint", "ブロンズランク");
				} else if ((allTotalPrice >= 200000) && (allTotalPrice < 400000)) {
					model.addAttribute("rankPoint", "シルバーランク");
				} else if ((allTotalPrice >= 400000) && (allTotalPrice < 800000)) {
					model.addAttribute("rankPoint", "ゴールドランク");
				} else if ((allTotalPrice >= 800000) && (allTotalPrice < 1000000)) {
					model.addAttribute("rankPoint", "ダイヤモンドランク");
				} else if ((allTotalPrice >= 1000000) && (allTotalPrice < 1500000)) {
					model.addAttribute("rankPoint", "プラチナランク");
				} else if ((allTotalPrice >= 1500000) && (allTotalPrice < 3000000)) {
					model.addAttribute("rankPoint", "エイリアンランク");
				} else if ((allTotalPrice >= 3000000) && (allTotalPrice < 5000000)) {
					model.addAttribute("rankPoint", "ゴッドフォックスランク");
				} else if ((allTotalPrice >= 5000000) && (allTotalPrice < 8000000)) {
					model.addAttribute("rankPoint", "プレミアムゴッドランク");
				} else if ((allTotalPrice >= 8000000)) {
					model.addAttribute("rankPoint", "InductedIntoTheHalOfFameRank");
				}
				allPurchaseList.add(purchasedtoAdd);
			}
		} else {
			model.addAttribute("purchaseCount", 0);
			model.addAttribute("allTotalPrice", 0);
			model.addAttribute("rankPoint", "アマチュアランク");
		}
		
		List<PurchaseDTO> purchasePointList = purchaseService.selectPoint(selectId);
		List<Integer> pointAdd = new ArrayList<>();
		int pointAll = 0;
		for(int x = 0; purchasePointList.size() > x; x++) {
			PurchaseDTO purchasedtoOne = purchasePointList.get(x);
			int pointOne = purchasedtoOne.getPoint();
			pointAll = pointAll + pointOne;	
			model.addAttribute("point",pointAll);
		}
		
		return "shopping/productListLayout";
	}

	@GetMapping("/userRankDetail")
	public String getUserRankDetail(@ModelAttribute UserEditForm form, Model model) {
		model.addAttribute("contents", "shopping/userRankDetail::productListLayout_contents");

		UsersDTO usersdto = new UsersDTO();
		List<UsersDTO> usersIdList = new ArrayList<>();
		usersIdList = usersService.selectMany();
		int totalUser = usersIdList.size();
		int amateurUser = 0;
		int proUser = 0;
		int bronzeUser = 0;
		int silverUser = 0;
		int goldUser = 0;
		int diamondUser = 0;
		int platinumUser = 0;
		int alienUser = 0;
		int godFoxUser = 0;
		int premiumGodUser = 0;
		int inductedIntoTheHalOfFameUser = 0;
		for (int y = 0; usersIdList.size() > y; y++) {
			usersdto = usersIdList.get(y);
			int userId = usersdto.getId();

			int allProductCount = 0;
			int allTotalPrice = 0;

			List<PurchaseDTO> purchasedtoList = purchaseService.selectHistory(userId);// メソッドに入ったユーザーの購入情報を取得
			List<PurchaseDTO> allPurchaseList = new ArrayList<>();
			PurchaseDTO customList;
			// 購入商品を一つづつ回して値を受け取る
			if (purchasedtoList.size() > 0) {
				for (int i = 0; purchasedtoList.size() > i; i++) {
					PurchaseDTO purchasedtoAdd = new PurchaseDTO();
					PurchaseDTO purchaseOne = purchasedtoList.get(i);
					purchasedtoAdd.setId(purchaseOne.getId());// カスタム情報取得に使用
					purchasedtoAdd.setCancelCheck(purchaseOne.getCancelCheck());
					purchasedtoAdd.setPrice(purchaseOne.getPrice());
					purchasedtoAdd.setProduct_count(purchaseOne.getProduct_count());
					purchasedtoAdd.setPurchaseCheck(purchaseOne.getPurchaseCheck());
					// 購入商品ごとのカスタム情報も取り出す
					int productId = purchasedtoAdd.getId();
					// int customId = purchasedtoAdd.getCustom_id();

					String nullCheck = "null";
					int getCustomId = customService.selectPurchaseCheck(userId, productId,
							purchasedtoAdd.getPurchaseCheck(), nullCheck);

					customList = customService.selectMany(getCustomId);// customテーブルのIDでカスタム情報を取得
					purchasedtoAdd.setCustomPrice(customList.getCustomPrice());
					purchasedtoAdd.setTotalPrice(
							purchaseOne.getProduct_count() * (customList.getCustomPrice() + purchaseOne.getPrice()));
					allTotalPrice = allTotalPrice + purchasedtoAdd.getTotalPrice();// ユーザーの購入した商品の合計金額を取得
					allProductCount = allProductCount + purchasedtoAdd.getProduct_count();// ユーザーの購入した商品の数を取得
					model.addAttribute("purchaseCount", allProductCount);
					model.addAttribute("allTotalPrice", allTotalPrice);

				}

				if ((allTotalPrice > 0) && (allTotalPrice < 50000)) {
					model.addAttribute("rankPoint", "アマチュアランク");
					amateurUser = amateurUser + 1;
				} else if ((allTotalPrice >= 50000) && (allTotalPrice < 100000)) {
					model.addAttribute("rankPoint", "プロランク");
					proUser = proUser + 1;
				} else if ((allTotalPrice >= 100000) && (allTotalPrice < 200000)) {
					model.addAttribute("rankPoint", "ブロンズランク");
					bronzeUser = bronzeUser + 1;
				} else if ((allTotalPrice >= 200000) && (allTotalPrice < 400000)) {
					model.addAttribute("rankPoint", "シルバーランク");
					silverUser = silverUser + 1;
				} else if ((allTotalPrice >= 400000) && (allTotalPrice < 800000)) {
					model.addAttribute("rankPoint", "ゴールドランク");
					goldUser = goldUser + 1;
				} else if ((allTotalPrice >= 800000) && (allTotalPrice < 1000000)) {
					model.addAttribute("rankPoint", "ダイヤモンドランク");
					diamondUser = diamondUser + 1;
				} else if ((allTotalPrice >= 1000000) && (allTotalPrice < 1500000)) {
					model.addAttribute("rankPoint", "プラチナランク");
					platinumUser = platinumUser + 1;
				} else if ((allTotalPrice >= 1500000) && (allTotalPrice < 3000000)) {
					model.addAttribute("rankPoint", "エイリアンランク");
					alienUser = alienUser + 1;
				} else if ((allTotalPrice >= 3000000) && (allTotalPrice < 5000000)) {
					model.addAttribute("rankPoint", "ゴッドフォックスランク");
					godFoxUser = godFoxUser + 1;
				} else if ((allTotalPrice >= 5000000) && (allTotalPrice < 8000000)) {
					model.addAttribute("rankPoint", "プレミアムゴッドランク");
					premiumGodUser = premiumGodUser + 1;
				} else if ((allTotalPrice >= 8000000)) {
					model.addAttribute("rankPoint", "InductedIntoTheHalOfFameRank");
					inductedIntoTheHalOfFameUser = inductedIntoTheHalOfFameUser + 1;
				}

			} else {
				model.addAttribute("purchaseCount", 0);
				model.addAttribute("allTotalPrice", 0);
				model.addAttribute("rankPoint", "アマチュアランク");
				model.addAttribute("rankPoint", "アマチュアランク");
				amateurUser = amateurUser + 1;
			}

		}
		System.out.println("amateurUser" + amateurUser);
		System.out.println("totalUser" + totalUser);
		System.out.println(amateurUser);
		System.out.println(proUser);
		System.out.println(bronzeUser);
		System.out.println(silverUser);
		System.out.println(goldUser);
		System.out.println(diamondUser);
		System.out.println(platinumUser);
		System.out.println(alienUser);
		System.out.println(godFoxUser);
		System.out.println(premiumGodUser);
		System.out.println(inductedIntoTheHalOfFameUser);
		// amateurユーザー
		double amateurRatio = (double) amateurUser / (double) totalUser;
		System.out.println("amateurRatio" + amateurRatio);
		String amateurRatioStr = String.valueOf(amateurRatio);
		int ratioSize = amateurRatioStr.length();
		if(ratioSize >= 4) {
			String amateurRatioStrCheckNew = amateurRatioStr.substring(0,4);
			System.out.println("334");
			String amateurRatioStrCheckfour = amateurRatioStrCheckNew.substring(2, 4);
			model.addAttribute("amateurUser", amateurRatioStrCheckfour);
			
		}else {
		System.out.println("ratioSize" + ratioSize);
		if (ratioSize == 3) {
			System.out.println("11");
			String amateurRatioStrCheck = amateurRatioStr.substring(0, 1);
			if (amateurRatioStrCheck.equals("1")) {
				System.out.println("22");
				model.addAttribute("amateurUser", 100);
			} else {
				if (amateurRatioStr.equals("0.0")) {
					model.addAttribute("amateurUser", 0);
				} else {
					System.out.println("33");
					String amateurRatioStrChecktwo = amateurRatioStr.substring(2, 3);
					String amateurRatioStrChecktwoNew = amateurRatioStrChecktwo + "0";
					model.addAttribute("amateurUser", amateurRatioStrChecktwoNew);
				}
			}
		} else if (ratioSize == 4) {
			String amateurRatioStrCheckthree = amateurRatioStr.substring(2, 3);
			System.out.println("3333");
			if (amateurRatioStrCheckthree.equals("0")) {
				System.out.println("44");
				String amateurRatioStrCheckthreeNew = amateurRatioStr.substring(3, 4);
				model.addAttribute("amateurUser", amateurRatioStrCheckthreeNew);
			} else {
				System.out.println("334");
				String amateurRatioStrCheckfour = amateurRatioStr.substring(2, 4);
				model.addAttribute("amateurUser", amateurRatioStrCheckfour);
			}
		} else {
			String amateurRatioStrCheckfive = amateurRatioStr.substring(4, 5);
			String amateurRatioStrCheckfiveNew = "0." + amateurRatioStrCheckfive;
			model.addAttribute("amateurUser", amateurRatioStrCheckfiveNew);
		}
		}

		// proユーザー
		double proRatio = (double) proUser / (double) totalUser;
		System.out.println("proRatio" + proRatio);
		String proRatioStr = String.valueOf(proRatio);
		int ratioSizePro = proRatioStr.length();
		System.out.println("ratioSizePro" + ratioSizePro);
		if(ratioSizePro >= 4) {
			String proRatioStrCheckNew = proRatioStr.substring(0,4);
			System.out.println("334");
			String proRatioStrCheckfour = proRatioStrCheckNew.substring(2, 4);
			model.addAttribute("proUser", proRatioStrCheckfour);
			
		}else {
		if (ratioSizePro == 3) {
			System.out.println("11");
			String proRatioStrCheck = proRatioStr.substring(0, 1);
			if (proRatioStrCheck.equals("1")) {
				System.out.println("22");
				model.addAttribute("proUser", 100);
			} else {
				if (proRatioStr.equals("0.0")) {
					model.addAttribute("proUser", 0);
				} else {
					System.out.println("33");
					String proRatioStrChecktwo = proRatioStr.substring(2, 3);
					String proRatioStrChecktwoNew = proRatioStrChecktwo + "0";
					model.addAttribute("proUser", proRatioStrChecktwoNew);
				}
			}
		} else if (ratioSizePro == 4) {
			String proRatioStrCheckthree = proRatioStr.substring(2, 3);
			System.out.println("3333");
			if (proRatioStrCheckthree.equals("0")) {
				System.out.println("44");
				String proRatioStrCheckthreeNew = proRatioStr.substring(3, 4);
				model.addAttribute("proUser", proRatioStrCheckthreeNew);
			} else {
				System.out.println("334");
				String proRatioStrCheckfour = proRatioStr.substring(2, 4);
				model.addAttribute("proUser", proRatioStrCheckfour);
			}
		} else {
			String proRatioStrCheckfive = proRatioStr.substring(4, 5);
			String proRatioStrCheckfiveNew = "0." + proRatioStrCheckfive;
			model.addAttribute("proUser", proRatioStrCheckfiveNew);
		}
		}
		// bronzeユーザー
		double bronzeRatio = (double) bronzeUser / (double) totalUser;
		System.out.println("bronzeRatio" + bronzeRatio);
		String bronzeRatioStr = String.valueOf(bronzeRatio);
		int ratioSizeBronze = bronzeRatioStr.length();
		System.out.println("ratioSizeBronze" + ratioSizeBronze);
		if(ratioSizeBronze >= 4) {
			String bronzeRatioStrCheckNew = bronzeRatioStr.substring(0,4);
			System.out.println("334");
			String bronzeRatioStrCheckfour = bronzeRatioStrCheckNew.substring(2, 4);
			model.addAttribute("bronzeUser", bronzeRatioStrCheckfour);
			
		}else {
		if (ratioSizeBronze == 3) {
			System.out.println("11");
			String bronzeRatioStrCheck = bronzeRatioStr.substring(0, 1);
			if (bronzeRatioStrCheck.equals("1")) {
				System.out.println("22");
				model.addAttribute("bronzeUser", 100);
			} else {
				if (bronzeRatioStr.equals("0.0")) {
					model.addAttribute("bronzeUser", 0);
				} else {
					System.out.println("33");
					String bronzeRatioStrChecktwo = bronzeRatioStr.substring(2, 3);
					String bronzeRatioStrChecktwoNew = bronzeRatioStrChecktwo + "0";
					model.addAttribute("bronzeUser", bronzeRatioStrChecktwoNew);
				}
			}
		} else if (ratioSizeBronze == 4) {
			String bronzeRatioStrCheckthree = bronzeRatioStr.substring(2, 3);
			System.out.println("3333");
			if (bronzeRatioStrCheckthree.equals("0")) {
				System.out.println("44");
				String bronzeRatioStrCheckthreeNew = bronzeRatioStr.substring(3, 4);
				model.addAttribute("bronzeUser", bronzeRatioStrCheckthreeNew);
			} else {
				System.out.println("334");
				String bronzeRatioStrCheckfour = bronzeRatioStr.substring(2, 4);
				model.addAttribute("bronzeUser", bronzeRatioStrCheckfour);
			}
		} else {
			String bronzeRatioStrCheckfive = bronzeRatioStr.substring(4, 5);
			String bronzeRatioStrCheckfiveNew = "0." + bronzeRatioStrCheckfive;
			model.addAttribute("bronzeUser", bronzeRatioStrCheckfiveNew);
		}
		}

		// sivlerユーザー
		double silverRatio = (double) silverUser / (double) totalUser;
		System.out.println("silverRatio" + silverRatio);
		String silverRatioStr = String.valueOf(silverRatio);
		int ratioSizeSilver = silverRatioStr.length();
		System.out.println("ratioSizeSilver" + ratioSizeSilver);
		if(ratioSizeSilver >= 4) {
			String silverRatioStrCheckNew = silverRatioStr.substring(0,4);
			System.out.println("334");
			String silverRatioStrCheckfour = silverRatioStrCheckNew.substring(2, 4);
			model.addAttribute("silverUser", silverRatioStrCheckfour);
			
		}else {
		if (ratioSizeSilver == 3) {
			System.out.println("11");
			String silverRatioStrCheck = silverRatioStr.substring(0, 1);
			if (silverRatioStrCheck.equals("1")) {
				System.out.println("22");
				model.addAttribute("silverUser", 100);
			} else {
				if (silverRatioStr.equals("0.0")) {
					model.addAttribute("silverUser", 0);
				} else {
					System.out.println("33");
					String silverRatioStrChecktwo = silverRatioStr.substring(2, 3);
					String silverRatioStrChecktwoNew = silverRatioStrChecktwo + "0";
					model.addAttribute("silverUser", silverRatioStrChecktwoNew);
				}
			}
		} else if (ratioSizeSilver == 4) {
			String silverRatioStrCheckthree = silverRatioStr.substring(2, 3);
			System.out.println("3333");
			if (silverRatioStrCheckthree.equals("0")) {
				System.out.println("44");
				String silverRatioStrCheckthreeNew = silverRatioStr.substring(3, 4);
				model.addAttribute("silverUser", silverRatioStrCheckthreeNew);
			} else {
				System.out.println("334");
				String silverRatioStrCheckfour = silverRatioStr.substring(2, 4);
				model.addAttribute("silverUser", silverRatioStrCheckfour);
			}
		} else {
			String silverRatioStrCheckfive = silverRatioStr.substring(4, 5);
			String silverRatioStrCheckfiveNew = "0." + silverRatioStrCheckfive;
			model.addAttribute("silverUser", silverRatioStrCheckfiveNew);
		}
		}
		
		// goldユーザー
		double goldRatio = (double) goldUser / (double) totalUser;
		System.out.println("goldRatio" + goldRatio);
		String goldRatioStr = String.valueOf(goldRatio);
		int ratioSizeGold = goldRatioStr.length();
		System.out.println("ratioSizeGold" + ratioSizeGold);
		if(ratioSizeGold >= 4) {
			String goldRatioStrCheckNew = goldRatioStr.substring(0,4);
			System.out.println("334");
			String goldRatioStrCheckfour = goldRatioStrCheckNew.substring(2, 4);
			model.addAttribute("goldUser", goldRatioStrCheckfour);
			
		}else {
		if (ratioSizeGold == 3) {
			System.out.println("11");
			String goldRatioStrCheck = goldRatioStr.substring(0, 1);
			if (goldRatioStrCheck.equals("1")) {
				System.out.println("22");
				model.addAttribute("goldUser", 100);
			} else {
				if (goldRatioStr.equals("0.0")) {
					model.addAttribute("goldUser", 0);
				} else {
					System.out.println("33");
					String goldRatioStrChecktwo = goldRatioStr.substring(2, 3);
					String goldRatioStrChecktwoNew = goldRatioStrChecktwo + "0";
					model.addAttribute("goldUser", goldRatioStrChecktwoNew);
				}
			}
		} else if (ratioSizeGold == 4) {
			String goldRatioStrCheckthree = goldRatioStr.substring(2, 3);
			System.out.println("3333");
			if (goldRatioStrCheckthree.equals("0")) {
				System.out.println("44");
				String goldRatioStrCheckthreeNew = goldRatioStr.substring(3, 4);
				model.addAttribute("goldUser", goldRatioStrCheckthreeNew);
			} else {
				System.out.println("334");
				String goldRatioStrCheckfour = goldRatioStr.substring(2, 4);
				model.addAttribute("goldUser", goldRatioStrCheckfour);
			}
		} else {
			String goldRatioStrCheckfive = goldRatioStr.substring(4, 5);
			String goldRatioStrCheckfiveNew = "0." + goldRatioStrCheckfive;
			model.addAttribute("goldUser", goldRatioStrCheckfiveNew);
		}
		}

		// diamondユーザー
		double diamondRatio = (double) diamondUser / (double) totalUser;
		System.out.println("diamondRatio" + diamondRatio);
		String diamondRatioStr = String.valueOf(diamondRatio);
		int ratioSizeDiamond = diamondRatioStr.length();
		System.out.println("ratioSizeDiamond" + ratioSizeDiamond);
		if(ratioSizeDiamond >= 4) {
			String diamondRatioStrCheckNew = diamondRatioStr.substring(0,4);
			System.out.println("334");
			String diamondRatioStrCheckfour = diamondRatioStrCheckNew.substring(2, 4);
			model.addAttribute("diamondUser", diamondRatioStrCheckfour);
			
		}else {
		if (ratioSizeDiamond == 3) {
			System.out.println("11");
			String diamondRatioStrCheck = diamondRatioStr.substring(0, 1);
			if (diamondRatioStrCheck.equals("1")) {
				System.out.println("22");
				model.addAttribute("diamondUser", 100);
			} else {
				if (diamondRatioStr.equals("0.0")) {
					model.addAttribute("diamondUser", 0);
				} else {
					System.out.println("33");
					String diamondRatioStrChecktwo = diamondRatioStr.substring(2, 3);
					String diamondRatioStrChecktwoNew = diamondRatioStrChecktwo + "0";
					model.addAttribute("diamondUser", diamondRatioStrChecktwoNew);
				}
			}
		} else if (ratioSizeDiamond == 4) {
			String diamondRatioStrCheckthree = diamondRatioStr.substring(2, 3);
			System.out.println("3333");
			if (diamondRatioStrCheckthree.equals("0")) {
				System.out.println("44");
				String diamondRatioStrCheckthreeNew = diamondRatioStr.substring(3, 4);
				model.addAttribute("diamondUser", diamondRatioStrCheckthreeNew);
			} else {
				System.out.println("334");
				String diamondRatioStrCheckfour = diamondRatioStr.substring(2, 4);
				model.addAttribute("diamondUser", diamondRatioStrCheckfour);
			}
		} else {
			String diamondRatioStrCheckfive = diamondRatioStr.substring(4, 5);
			String diamondRatioStrCheckfiveNew = "0." + diamondRatioStrCheckfive;
			model.addAttribute("diamondUser", diamondRatioStrCheckfiveNew);
		}
		}

		// platinumユーザー
		double platinumRatio = (double) platinumUser / (double) totalUser;
		System.out.println("platinumRatio" + platinumRatio);
		String platinumRatioStr = String.valueOf(platinumRatio);
		int ratioSizePlatinum = platinumRatioStr.length();
		System.out.println("ratioSizePlatinum" + ratioSizePlatinum);
		if(ratioSizePlatinum >= 4) {
			String platinumRatioStrCheckNew = platinumRatioStr.substring(0,4);
			System.out.println("334");
			String platinumRatioStrCheckfour = platinumRatioStrCheckNew.substring(2, 4);
			model.addAttribute("platinumUser", platinumRatioStrCheckfour);
			
		}else {
		if (ratioSizePlatinum == 3) {
			System.out.println("11");
			String platinumRatioStrCheck = platinumRatioStr.substring(0, 1);
			if (platinumRatioStrCheck.equals("1")) {
				System.out.println("22");
				model.addAttribute("platinumUser", 100);
			} else {
				if (platinumRatioStr.equals("0.0")) {
					model.addAttribute("platinumUser", 0);
				} else {
					System.out.println("33");
					String platinumRatioStrChecktwo = platinumRatioStr.substring(2, 3);
					String platinumRatioStrChecktwoNew = platinumRatioStrChecktwo + "0";
					model.addAttribute("platinumUser", platinumRatioStrChecktwoNew);
				}
			}
		} else if (ratioSizePlatinum == 4) {
			String platinumRatioStrCheckthree = platinumRatioStr.substring(2, 3);
			System.out.println("3333");
			if (platinumRatioStrCheckthree.equals("0")) {
				System.out.println("44");
				String platinumRatioStrCheckthreeNew = platinumRatioStr.substring(3, 4);
				model.addAttribute("platinumUser", platinumRatioStrCheckthreeNew);
			} else {
				System.out.println("334");
				String platinumRatioStrCheckfour = platinumRatioStr.substring(2, 4);
				model.addAttribute("platinumUser", platinumRatioStrCheckfour);
			}
		} else {
			String platinumRatioStrCheckfive = platinumRatioStr.substring(4, 5);
			String platinumRatioStrCheckfiveNew = "0." + platinumRatioStrCheckfive;
			model.addAttribute("platinumUser", platinumRatioStrCheckfiveNew);
		}
		}

		// alienユーザー
		double alienRatio = (double) alienUser / (double) totalUser;
		System.out.println("alienRatio" + alienRatio);
		String alienRatioStr = String.valueOf(alienRatio);
		int ratioSizeAlien = alienRatioStr.length();
		System.out.println("ratioSizeAlien" + ratioSizeAlien);
		if(ratioSizeAlien >= 4) {
			String alienRatioStrCheckNew = alienRatioStr.substring(0,4);
			System.out.println("334");
			String alienRatioStrCheckfour = alienRatioStrCheckNew.substring(2, 4);
			model.addAttribute("alienUser", alienRatioStrCheckfour);
			
		}else {
		if (ratioSizeAlien == 3) {
			System.out.println("11");
			String alienRatioStrCheck = alienRatioStr.substring(0, 1);
			if (alienRatioStrCheck.equals("1")) {
				System.out.println("22");
				model.addAttribute("alienUser", 100);
			} else {
				if (alienRatioStr.equals("0.0")) {
					model.addAttribute("alienUser", 0);
				} else {
					System.out.println("33");
					String alienRatioStrChecktwo = alienRatioStr.substring(2, 3);
					String alienRatioStrChecktwoNew = alienRatioStrChecktwo + "0";
					model.addAttribute("alienUser", alienRatioStrChecktwoNew);
				}
			}
		} else if (ratioSizeAlien == 4) {
			String alienRatioStrCheckthree = alienRatioStr.substring(2, 3);
			System.out.println("3333");
			if (alienRatioStrCheckthree.equals("0")) {
				System.out.println("44");
				String alienRatioStrCheckthreeNew = alienRatioStr.substring(3, 4);
				model.addAttribute("alienUser", alienRatioStrCheckthreeNew);
			} else {
				System.out.println("334");
				String alienRatioStrCheckfour = alienRatioStr.substring(2, 4);
				model.addAttribute("alienUser", alienRatioStrCheckfour);
			}
		} else {
			String alienRatioStrCheckfive = alienRatioStr.substring(4, 5);
			String alienRatioStrCheckfiveNew = "0." + alienRatioStrCheckfive;
			model.addAttribute("alienUser", alienRatioStrCheckfiveNew);
		}
		}

		// godFoxユーザー
		double godFoxRatio = (double) godFoxUser / (double) totalUser;
		System.out.println("godFoxRatio" + godFoxRatio);
		String godFoxRatioStr = String.valueOf(godFoxRatio);
		int ratioSizegodFox = godFoxRatioStr.length();
		System.out.println("ratioSizegodFox" + ratioSizegodFox);
		if(ratioSizegodFox >= 4) {
			String godFoxRatioStrCheckNew = godFoxRatioStr.substring(0,4);
			System.out.println("334");
			String godFoxRatioStrCheckfour = godFoxRatioStrCheckNew.substring(2, 4);
			model.addAttribute("godFoxUser", godFoxRatioStrCheckfour);
			
		}else {
		if (ratioSizegodFox == 3) {
			System.out.println("11");
			String godFoxRatioStrCheck = godFoxRatioStr.substring(0, 1);
			if (godFoxRatioStrCheck.equals("1")) {
				System.out.println("22");
				model.addAttribute("godFoxUser", 100);
			} else {
				if (godFoxRatioStr.equals("0.0")) {
					model.addAttribute("godFoxUser", 0);
				} else {
					System.out.println("33");
					String godFoxRatioStrChecktwo = godFoxRatioStr.substring(2, 3);
					String godFoxRatioStrChecktwoNew = godFoxRatioStrChecktwo + "0";
					model.addAttribute("godFoxUser", godFoxRatioStrChecktwoNew);
				}
			}
		} else if (ratioSizegodFox == 4) {
			String godFoxRatioStrCheckthree = godFoxRatioStr.substring(2, 3);
			System.out.println("3333");
			if (godFoxRatioStrCheckthree.equals("0")) {
				System.out.println("44");
				String godFoxRatioStrCheckthreeNew = godFoxRatioStr.substring(3, 4);
				model.addAttribute("godFoxUser", godFoxRatioStrCheckthreeNew);
			} else {
				System.out.println("334");
				String godFoxRatioStrCheckfour = godFoxRatioStr.substring(2, 4);
				model.addAttribute("godFoxUser", godFoxRatioStrCheckfour);
			}
		} else {
			String godFoxRatioStrCheckfive = godFoxRatioStr.substring(4, 5);
			String godFoxRatioStrCheckfiveNew = "0." + godFoxRatioStrCheckfive;
			model.addAttribute("godFoxUser", godFoxRatioStrCheckfiveNew);
		}
		}

		// premiumGodユーザー
		double premiumGodRatio = (double) premiumGodUser / (double) totalUser;
		System.out.println("premiumGodRatio" + premiumGodRatio);
		String premiumGodRatioStr = String.valueOf(premiumGodRatio);
		int ratioSizePremiumGod = premiumGodRatioStr.length();
		System.out.println("ratioSizePremiumGod" + ratioSizePremiumGod);
		if(ratioSizePremiumGod >= 4) {
			String premiumGodRatioStrCheckNew = premiumGodRatioStr.substring(0,4);
			System.out.println("334");
			String premiumGodRatioStrCheckfour = premiumGodRatioStrCheckNew.substring(2, 4);
			model.addAttribute("premiumGodUser", premiumGodRatioStrCheckfour);
			
		}else {
		if (ratioSizePremiumGod == 3) {
			System.out.println("11");
			String premiumGodRatioStrCheck = premiumGodRatioStr.substring(0, 1);
			if (premiumGodRatioStrCheck.equals("1")) {
				System.out.println("22");
				model.addAttribute("premiumGodUser", 100);
			} else {
				if (premiumGodRatioStr.equals("0.0")) {
					model.addAttribute("premiumGodUser", 0);
				} else {
					System.out.println("33");
					String premiumGodRatioStrChecktwo = premiumGodRatioStr.substring(2, 3);
					String premiumGodRatioStrChecktwoNew = premiumGodRatioStrChecktwo + "0";
					model.addAttribute("premiumGodUser", premiumGodRatioStrChecktwoNew);
				}
			}
		} else if (ratioSizePremiumGod == 4) {
			String premiumGodRatioStrCheckthree = premiumGodRatioStr.substring(2, 3);
			System.out.println("3333");
			if (premiumGodRatioStrCheckthree.equals("0")) {
				System.out.println("44");
				String premiumGodRatioStrCheckthreeNew = premiumGodRatioStr.substring(3, 4);
				model.addAttribute("premiumGodUser", premiumGodRatioStrCheckthreeNew);
			} else {
				System.out.println("334");
				String premiumGodRatioStrCheckfour = premiumGodRatioStr.substring(2, 4);
				model.addAttribute("premiumGodUser", premiumGodRatioStrCheckfour);
			}
		} else {
			String premiumGodRatioStrCheckfive = premiumGodRatioStr.substring(4, 5);
			String premiumGodRatioStrCheckfiveNew = "0." + premiumGodRatioStrCheckfive;
			model.addAttribute("premiumGodUser", premiumGodRatioStrCheckfiveNew);
		}
		}

		// inductedIntoTheHalOfFameRankユーザー
		double inductedIntoTheHalOfFameRankRatio = (double) inductedIntoTheHalOfFameUser / (double) totalUser;
		System.out.println("inductedIntoTheHalOfFameRankRatio" + inductedIntoTheHalOfFameRankRatio);
		String inductedIntoTheHalOfFameRankRatioStr = String.valueOf(inductedIntoTheHalOfFameRankRatio);
		int ratioSizeInductedIntoTheHalOfFameRank = inductedIntoTheHalOfFameRankRatioStr.length();
		System.out.println("ratioSizeInductedIntoTheHalOfFameRank" + ratioSizeInductedIntoTheHalOfFameRank);
		if(ratioSizeInductedIntoTheHalOfFameRank >= 4) {
			String inductedIntoTheHalOfFameRatioStrCheckNew = inductedIntoTheHalOfFameRankRatioStr.substring(0,4);
			System.out.println("334");
			String inductedIntoTheHalOfFameRatioStrCheckfour = inductedIntoTheHalOfFameRatioStrCheckNew.substring(2, 4);
			model.addAttribute("inductedIntoTheHalOfFameUser", inductedIntoTheHalOfFameRatioStrCheckfour);
			
		}else {
		if (ratioSizeInductedIntoTheHalOfFameRank == 3) {
			System.out.println("11");
			String inductedIntoTheHalOfFameRankRatioStrCheck = inductedIntoTheHalOfFameRankRatioStr.substring(0, 1);
			if (inductedIntoTheHalOfFameRankRatioStrCheck.equals("1")) {
				System.out.println("22");
				model.addAttribute("inductedIntoTheHalOfFameUser", 100);
			} else {
				if (inductedIntoTheHalOfFameRankRatioStr.equals("0.0")) {
					model.addAttribute("inductedIntoTheHalOfFameUser", 0);
				} else {
					System.out.println("33");
					String inductedIntoTheHalOfFameRankRatioStrChecktwo = inductedIntoTheHalOfFameRankRatioStr
							.substring(2, 3);
					String inductedIntoTheHalOfFameRankRatioStrChecktwoNew = inductedIntoTheHalOfFameRankRatioStrChecktwo
							+ "0";
					model.addAttribute("inductedIntoTheHalOfFameUser", inductedIntoTheHalOfFameRankRatioStrChecktwoNew);
				}
			}
		} else if (ratioSizeInductedIntoTheHalOfFameRank == 4) {
			String inductedIntoTheHalOfFameRankRatioStrCheckthree = inductedIntoTheHalOfFameRankRatioStr.substring(2,
					3);
			System.out.println("3333");
			if (inductedIntoTheHalOfFameRankRatioStrCheckthree.equals("0")) {
				System.out.println("44");
				String inductedIntoTheHalOfFameRankRatioStrCheckthreeNew = inductedIntoTheHalOfFameRankRatioStr
						.substring(3, 4);
				model.addAttribute("inductedIntoTheHalOfFameUser", inductedIntoTheHalOfFameRankRatioStrCheckthreeNew);
			} else {
				System.out.println("334");
				String inductedIntoTheHalOfFameRankRatioStrCheckfour = inductedIntoTheHalOfFameRankRatioStr.substring(2,
						4);
				model.addAttribute("inductedIntoTheHalOfFameUser", inductedIntoTheHalOfFameRankRatioStrCheckfour);
			}
		} else {
			String inductedIntoTheHalOfFameRankRatioStrCheckfive = inductedIntoTheHalOfFameRankRatioStr.substring(4, 5);
			String inductedIntoTheHalOfFameRankRatioStrCheckfiveNew = "0."
					+ inductedIntoTheHalOfFameRankRatioStrCheckfive;
			model.addAttribute("inductedIntoTheHalOfFameRankUser", inductedIntoTheHalOfFameRankRatioStrCheckfiveNew);
		}
		}

		return "shopping/productListLayout";
	}

	@GetMapping("/editYourDetail/{id}")
	public String getEditYourDetail(@ModelAttribute UserEditForm from, UsersListForm usersListForm,
			@PathVariable("id") int id, Model model) {
		model.addAttribute("contents", "shopping/editYourDetail::productListLayout_contents");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String userId = auth.getName();
		int selectId = usersService.select_id(userId);

		UsersDTO usersdto = usersService.userInformationSelectOne(id);
		Usege_usersDTO usegeusersdto = usegeService.userInformationSelectOne(id);

		UsersListDTO userslistdto = new UsersListDTO();
		userslistdto.setUserName(usersdto.getUser_name());
		userslistdto.setAddress(usegeusersdto.getAddress());
		usersListForm.setUserName(userslistdto.getUserName());
		usersListForm.setAddress(userslistdto.getAddress());
		model.addAttribute("id", id);

		// 商品購入数と合計金額を取得、クーポン一覧の上の画面にも表示してクーポン使えるかどうかも判定 とりあえずユーザー情報に表示する

		return "shopping/productListLayout";
	}

	@PostMapping(value = "/editYourDetail", params = "update")
	public String postEditYourDetailUpdate(@ModelAttribute UserEditForm form,
			@Validated(GroupOrder.class) UsersListForm usersListForm, BindingResult bindingResult,
			@RequestParam("id") int id, Model model) {

		if (bindingResult.hasErrors()) {
			System.out.println("バリデーションエラー到達");

			return getEditYourDetail(form, usersListForm, id, model);
		}

		UsersDTO usersdto = new UsersDTO();
		usersdto.setId(id);
		usersdto.setUser_name(usersListForm.getUserName());// 変更された名前をdtoに入れる
		Usege_usersDTO usegeusersdto = new Usege_usersDTO();
		usegeusersdto.setId(id);
		usegeusersdto.setAddress(usersListForm.getAddress());// 変更された住所をdtoに入れる
		usersService.updateOne(usersdto);// 変更された名前に更新
		usegeService.updateOne(usegeusersdto);// 変更された住所に更新

		return getEditYour(form, model);
	}

	@PostMapping(value = "/editYourDetail", params = "delete")
	public String postEditYourDetailDelete(@ModelAttribute UserEditForm form, @RequestParam("id") int id, Model model) {

		usersService.deleteOne(id);// idをもとにusersテーブルの情報を削除
		usegeService.deleteOne(id);// idをもとにusege_usersテーブルの情報を削除

		return getLogout();
	}
	
	@GetMapping("/pointRateChange")
	public String getPointRateChange(@ModelAttribute PointRateChangeForm form,Model model) {
		model.addAttribute("contents", "shopping/pointRateChange::productListLayout_contents");
		
		
		return "shopping/productListLayout";
		
	}
	
	@PostMapping("/pointRateChange")
	public String postPointRateChange(@ModelAttribute PointRateChangeForm form,Model model) {
		model.addAttribute("contents", "shopping/pointRateChangeFinish::productListLayout_contents");
		
		session.setAttribute("pointRate",form.getPointRate());
		pointRateService.updateOne(session);
		
		int pointRate = pointRateService.selectOne(1);
		model.addAttribute("pointRate",pointRate);
		
		return "shopping/productListLayout";
		
	}

	@GetMapping("termsOfUse")
	public String gettermsOfUse(Model model) {
		model.addAttribute("contents", "shopping/termsOfUse::productListLayout_contents");

		return "shopping/productListLayout";
	}

	@GetMapping("privacyPolicy")
	public String getPrivacyPolicy(Model model) {
		model.addAttribute("contents", "shopping/privacyPolicy::productListLayout_contents");

		return "shopping/productListLayout";
	}

	@GetMapping("/inquiry")
	public String getInquiry(@ModelAttribute InquiryForm form, Model model) {
		model.addAttribute("contents", "shopping/inquiry::productListLayout_contents");

		return "shopping/productListLayout";
	}

	@PostMapping(value = "inquiry", params = "sending")
	public String postInquirySending(@ModelAttribute @Validated(GroupOrder.class) InquiryForm form,
			BindingResult bindingResult, InquiryForm form2, HttpServletRequest request, HttpServletResponse response,
			Model model) {

		model.addAttribute("contents", "shopping/inquiryFinish::productListLayout_contents");

		if (bindingResult.hasErrors()) {// 入力内容がおかしい場合はtrue
			System.out.println("バリデーションエラー到達");
			return getInquiry(form, model);// もう一度入力画面に遷移させる
		}

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String user_id = auth.getName();
		int select_id = usersService.select_id(user_id);

		InquiryDTO inquirydto = new InquiryDTO();
		inquirydto.setTitle(form.getTitle());// 問い合わせタイトルをdtoに入れる
		inquirydto.setContent(form.getContent());// 問い合わせ内容をdtoに入れる

		HttpSession session = request.getSession();
		session.setAttribute("title", form.getTitle());
		session.setAttribute("content", form.getContent());
		model.addAttribute("title", (String) session.getAttribute("title"));
		model.addAttribute("content", (String) session.getAttribute("content"));

		InquiryReplyDTO inquiryreplydto = new InquiryReplyDTO();
		int result = inquiryService.insertOne(inquirydto, select_id);// dtoとusersテーブルのidでお問い合わせの情報を格納する
		int maxId = inquiryService.selectMaxId();
		inquiryreplyService.insertOne(inquiryreplydto, maxId);

		return "shopping/productListLayout";

	}

	@GetMapping("/inquiryReplay/{id}")
	public String getInquiryReply(@ModelAttribute InquiryForm form, @PathVariable("id") int inquiryId, Model model) {
		model.addAttribute("contents", "shopping/inquiryReply::productListLayout_contents");
		InquiryDTO inquirydto = inquiryService.selectOne(inquiryId);// inquiryテーブルのIdをもとにinquiryテーブルの情報を取得
		model.addAttribute("inquiryList", inquirydto);
		model.addAttribute("inquiryId", inquiryId);

		return "shopping/productListLayout";
	}

	@GetMapping("/inquiryDetail/{id}")
	public String getInquiryDetail(@ModelAttribute InquiryForm form, @PathVariable("id") int inquiryId, Model model) {
		model.addAttribute("contents", "shopping/inquiryDetail::productListLayout_contents");

		InquiryDTO inquirydto = inquiryService.selectOne(inquiryId);// inquiryテーブルのIdをもとにinquiryテーブルの情報を取得
		model.addAttribute("id", inquirydto.getId());
		model.addAttribute("inquiryList", inquirydto);

		return "shopping/productListLayout";
	}

	@PostMapping("/inquiryDetail")
	public String postInquiryDetail(@ModelAttribute InquiryForm form, @RequestParam("id") int id, Model model) {
		System.out.println("inquiryDetail到達");
		int result = inquiryService.deleteOne(id);// inquiryテーブルのIdをもとにinquiryテーブルの情報を削除

		return getAdministrator(form, model);
	}

	@PostMapping(value = "inquiry", params = "return")
	public String postInquiryReturn(@ModelAttribute InquiryForm form, @RequestParam("id") int id,
			HttpServletRequest request, HttpServletResponse response, Model model) {

		InquiryReplyDTO inquiryreplydto = new InquiryReplyDTO();
		inquiryreplydto.setInquiryId(id);
		inquiryreplydto.setTitle(form.getTitle());// 問い合わせの返信タイトルをdtoに入れる
		inquiryreplydto.setContent(form.getContent());// 問い合わせの返信内容をdtoに入れる

		HttpSession session = request.getSession();
		session.setAttribute("id", id);
		session.setAttribute("title", form.getTitle());
		session.setAttribute("content", form.getContent());

		int result = inquiryreplyService.replyUpdateOne(session, inquiryreplydto);// dtoの情報をinquiry_replyテーブルに格納

		return getAdministrator(form, model);

	}

	@GetMapping("contactReply")
	public String getContactReply(@ModelAttribute InquiryForm form, Model model) {
		model.addAttribute("contents", "shopping/contactReply::productListLayout_contents");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String user_id = auth.getName();
		int select_id = usersService.select_id(user_id);

		List<InquiryAllDTO> inquiryreplydtolist = inquiryService.everyUserSelectMany(select_id);// usersテーブルのidをもとにinquiryテーブルとinquiry_replyテーブル情報を取得
		System.out.println("inquiryList" + inquiryreplydtolist);

		model.addAttribute("inquiryAllDto", inquiryreplydtolist);

		return "shopping/productListLayout";
	}

	@GetMapping("/administrator")
	public String getAdministrator(@ModelAttribute InquiryForm form, Model model) {
		model.addAttribute("contents", "shopping/administrator::productListLayout_contents");

		List<InquiryDTO> inquirydtolist = inquiryService.selectMany();// inquiryテーブル情報をすべて取得
		model.addAttribute("inquiryList", inquirydtolist);

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

		String img1 = form.getPcImg();// 画像アドレスを変数に入れる
		String img2 = form.getPcImg2();
		String img3 = form.getPcImg3();

		String imgCheck1 = img1.substring(img1.length() - 4);// 画像アドレスが.jpgか確かめるため最後に4文字を取得
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

		// バリデーションエラーにならなければ入力した情報をdtoにいれる
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

//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		System.out.println("auth" + auth.getName());
//		String user_id = auth.getName();
//		int select_id = usersService.select_id(user_id);

		int result = pcdataService.insertCheckSelectOne(pcdatadto);// dtoの情報をもとに、pcdateテーブルの情報と比較し、同じ商品がないか確かめる
		if (result < 1) {// 同じ商品がなければdtoの情報をpdcataテーブルに格納する
			int pcData = pcdataService.insertOne(pcdatadto);
		}
		return getProductList(form, model);
	}

	@GetMapping("/news")
	public String getNews(@ModelAttribute NewsForm form, Model model) {
		model.addAttribute("contents", "shopping/news::productListLayout_contents");

		List<NewsDTO> newsdtoList = newsService.selectMany();

		model.addAttribute("newsdtoList", newsdtoList);

		return "shopping/productListLayout";
	}

	@GetMapping("/newsAdd")
	public String getNewsAdd(@ModelAttribute NewsForm form, Model model) {
		model.addAttribute("contents", "shopping/newsAdd::productListLayout_contents");

		return "shopping/productListLayout";
	}

	@PostMapping("newsAdd")
	public String postNewsAdd(@ModelAttribute @Validated(GroupOrder.class) NewsForm form, BindingResult bidingResult,
			Model model) {
		model.addAttribute("contents", "shopping/newsAdd::productListLayout_contents");

		if (bidingResult.hasErrors()) {
			System.out.println("バリデーションエラー到達");
			return getNewsAdd(form, model);
		}

		NewsDTO newsdto = new NewsDTO();
		newsdto.setTitle(form.getTitle());
		newsdto.setContent(form.getContent());

		int result = newsService.insertOne(newsdto);

		return "shopping/productListLayout";
	}
	
	@GetMapping("/userPurchaseHistory/{id}")
	public String getUserPurchaseHistory(@ModelAttribute PcDataForm form,@PathVariable("id") int userId,Model model) {
		model.addAttribute("contents", "shopping/userPurchaseHistory::productListLayout_contents");
		// 購入商品情報リスト取得
				List<PurchaseDTO> purchasedtoList = purchaseService.selectHistory(userId);

				PurchaseDTO purchasedto = new PurchaseDTO();

				List<PurchaseDTO> allPurchaseList = new ArrayList<>();
				PurchaseDTO customList;
				// 購入商品を一つづつ回して値を受け取る
				for (int i = 0; purchasedtoList.size() > i; i++) {
					PurchaseDTO purchasedtoAdd = new PurchaseDTO();

					PurchaseDTO purchaseOne = purchasedtoList.get(i);
					purchasedtoAdd.setId(purchaseOne.getId());// カスタム情報取得に使用
					purchasedtoAdd.setPurchaseId(purchaseOne.getPurchaseId());
					purchasedtoAdd.setPurchase_date(purchaseOne.getPurchase_date());
					purchasedtoAdd.setCancelCheck(purchaseOne.getCancelCheck());
					purchasedtoAdd.setCouponId(purchaseOne.getCouponId());
					purchasedtoAdd.setPcName(purchaseOne.getPcName());
					purchasedtoAdd.setPrice(purchaseOne.getPrice());
					purchasedtoAdd.setPcImg(purchaseOne.getPcImg());
					purchasedtoAdd.setProduct_count(purchaseOne.getProduct_count());
					purchasedtoAdd.setPurchaseCheck(purchaseOne.getPurchaseCheck());
					purchasedtoAdd.setMenberCouponCheck(purchaseOne.getMenberCouponCheck());

					// 購入商品ごとのカスタム情報も取り出す

					int productId = purchasedtoAdd.getId();
					System.out.println("productId" + productId);
					System.out.println(userId);
					int customId = purchasedtoAdd.getCustom_id();
					System.out.println("customId");
					String nullCheck = "null";
					int getCustomId = customService.selectPurchaseCheck(userId, productId, purchasedtoAdd.getPurchaseCheck(),
							nullCheck);
					System.out.println("getCustomId" + getCustomId);

					customList = customService.selectMany(getCustomId);
					System.out.println("costomList" + customList);

					purchasedtoAdd.setMemory(customList.getMemory());
					purchasedtoAdd.setHardDisc(customList.getHardDisc());
					purchasedtoAdd.setCpu(customList.getCpu());
					purchasedtoAdd.setCustomPrice(customList.getCustomPrice());
					purchasedtoAdd.setTotalPrice(
							purchaseOne.getProduct_count() * (customList.getCustomPrice() + purchaseOne.getPrice()));

					if (purchasedtoAdd.getMenberCouponCheck().equals("会員クーポン使用")) {
						System.out.println("クーポン使用！");
						int totalPrice = purchasedtoAdd.getTotalPrice();
						MenberCouponDTO menbercoupondto = menberCouponService.selectOne(purchasedtoAdd.getCouponId());// 会員DBからとる
						int disCount = menbercoupondto.getDiscount();// 割引率(%)
						if (disCount >= 10) {
							double disCountNew = Double.valueOf("0." + disCount);
							double disCountPriceNew = totalPrice * disCountNew;// 割引価格
							purchasedtoAdd.setTotalPrice((int) (totalPrice - disCountPriceNew));
						} else {
							double disCountNew = Double.valueOf("0.0" + disCount);
							double disCountPriceNew = totalPrice * disCountNew;// 割引価格
							purchasedtoAdd.setTotalPrice((int) (totalPrice - disCountPriceNew));
						}
					} else {

						if (purchasedtoAdd.getCouponId() > 0) {
							System.out.println("クーポン使用！");
							int totalPrice = purchasedtoAdd.getTotalPrice();
							CouponDTO coupondto = couponService.selectOne(purchasedtoAdd.getCouponId());
							int disCount = coupondto.getDiscount();// 割引率(%)
							if (disCount >= 10) {
								double disCountNew = Double.valueOf("0." + disCount);
								double disCountPriceNew = totalPrice * disCountNew;// 割引価格
								purchasedtoAdd.setTotalPrice((int) (totalPrice - disCountPriceNew));
							} else {
								double disCountNew = Double.valueOf("0.0" + disCount);
								double disCountPriceNew = totalPrice * disCountNew;// 割引価格
								purchasedtoAdd.setTotalPrice((int) (totalPrice - disCountPriceNew));
							}
						}
					}
					// Date purchaseDate = purchasedtoAdd.getPurchase_date();
					Calendar calendar = Calendar.getInstance();
					Date now = calendar.getTime();
					calendar.setTime(purchasedtoAdd.getPurchase_date());
					Date purchaseDate = calendar.getTime();// 購入日付
					model.addAttribute("purchaseCheck", purchaseDate);
					calendar.add(Calendar.DATE, 10);
					Date purchaseDateAddTen = calendar.getTime();// キャンセル期間外の購入日付から10日後の日付
					model.addAttribute("result", purchaseDateAddTen);
					System.out.println("pur" + purchaseDate.getTime());
					System.out.println("now" + now.getTime());
					long d = (purchaseDate.getTime() - now.getTime()) / (1000 * 60 * 60 * 24);// 購入日と現在の日付を比べる
					int count = (int) -d;

					if (count <= 10) {
						System.out.println("true");
						purchasedtoAdd.setCancelResult("true");
					} else {
						purchasedtoAdd.setCancelResult("false");
					}

					System.out.println("testfalse");
					CancelDTO canceldto = cancelService.selectCancelCheck(purchasedtoAdd.getPurchaseId());
					System.out.println("testdd" + canceldto);
					if (canceldto.getCancelCheck() != null) {
						System.out.println("truefdfdfffdf");
						purchasedtoAdd.setCancelResult("true");
					}

					System.out.println("purId" + purchasedtoAdd.getPurchaseId());

					allPurchaseList.add(purchasedtoAdd);
					System.out.println("allPurchaseList" + allPurchaseList);

				}

				model.addAttribute("purchaseList", allPurchaseList);
				model.addAttribute("confirmationPending", "返品商品確認待ち");
				model.addAttribute("inTransaction", "キャンセル取引中");
				return "shopping/productListLayout";
	}

	@GetMapping("/reviewAdd/{id}")
	public String getReviewAdd(@ModelAttribute ReviewForm form, @PathVariable("id") int id, Model model) {
		model.addAttribute("contents", "shopping/reviewAdd::productListLayout_contents");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String getName = auth.getName();

		int select_id = usersService.select_id(getName);

		PurchaseDTO purchasedto = new PurchaseDTO();

		// 購入商品情報取得
		PurchaseDTO purchasedtoList = purchaseService.reviewSelectHistory(select_id, id);
		purchasedto.setId(purchasedtoList.getId());
		purchasedto.setPurchaseId(purchasedtoList.getPurchaseId());
		purchasedto.setPurchase_date(purchasedtoList.getPurchase_date());
		purchasedto.setPcDataId(purchasedtoList.getPcDataId());
		purchasedto.setPcName(purchasedtoList.getPcName());
		purchasedto.setPrice(purchasedtoList.getPrice());
		purchasedto.setProduct_count(purchasedtoList.getProduct_count());
		purchasedto.setPurchaseCheck(purchasedtoList.getPurchaseCheck());

		String nullCheck = "null";
		int getCustomId = customService.selectPurchaseCheck(select_id, purchasedtoList.getProduct_id(),
				purchasedtoList.getPurchaseCheck(), nullCheck);
		System.out.println("getCustomId" + getCustomId);

		PurchaseDTO customList = customService.selectMany(getCustomId);
		System.out.println("costomList" + customList);

		purchasedto.setMemory(customList.getMemory());
		purchasedto.setHardDisc(customList.getHardDisc());
		purchasedto.setCpu(customList.getCpu());
		purchasedto.setCustomPrice(customList.getCustomPrice());
		model.addAttribute("totalPrice", purchasedto.getPrice() + purchasedto.getCustomPrice());
		model.addAttribute("purchaseId", id);

		model.addAttribute("purchaseList", purchasedto);

		return "shopping/productListLayout";

	}

	@PostMapping("/reviewAdd")
	public String postReviewAdd(@ModelAttribute ReviewForm form, @RequestParam("pcDataId") int pcDataId,
			@RequestParam("purchaseId") int purchaseId, RedirectAttributes redirectAttributes, Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String getName = auth.getName();

		int selectId = usersService.select_id(getName);

		ReviewDTO reviewdto = new ReviewDTO();

		int selectResult = reviewService.selectOne(selectId, pcDataId, purchaseId);

		if (selectResult <= 0) {
			reviewService.reviewInsertOne(reviewdto, selectId, pcDataId, form.getTitle(), form.getContent(),
					form.getRating(), purchaseId);
		} else {
			model.addAttribute("result", "この商品はすでに口コミ投稿しています。");
			return getReviewAdd(form, purchaseId, model);
		}

		return "redirect:/purchaseHistory";

	}

	// キャンセル画面に遷移
	@GetMapping("/cancel/{id}")
	public String getCancel(@ModelAttribute CancelForm form, CancelNextForm nextform,
			@PathVariable("id") int purchaseId, Model model) {
		model.addAttribute("contents", "shopping/cancel::productListLayout_contents");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String getName = auth.getName();
		int select_id = usersService.select_id(getName);// メソッドに入ったユーザーのIDを取得

		PurchaseDTO purchasedto = new PurchaseDTO();

		// 購入商品情報取得
		PurchaseDTO purchasedtoList = purchaseService.reviewSelectHistory(select_id, purchaseId);// Pathで取得した購入IDでpurchaseテーブルの情報を取得
		purchasedto.setId(purchasedtoList.getId());
		purchasedto.setPurchaseId(purchasedtoList.getPurchaseId());
		purchasedto.setPurchase_date(purchasedtoList.getPurchase_date());
		purchasedto.setPcDataId(purchasedtoList.getPcDataId());
		purchasedto.setPcName(purchasedtoList.getPcName());
		purchasedto.setPrice(purchasedtoList.getPrice());
		purchasedto.setProduct_count(purchasedtoList.getProduct_count());
		purchasedto.setPurchaseCheck(purchasedtoList.getPurchaseCheck());

		String nullCheck = "null";
		int getCustomId = customService.selectPurchaseCheck(select_id, purchasedtoList.getProduct_id(),
				purchasedtoList.getPurchaseCheck(), nullCheck); // 購入した商品のcustomテーブルIDを取得

		System.out.println("getCustomId" + getCustomId);

		PurchaseDTO customList = customService.selectMany(getCustomId);// 購入した商品のcustomテーブル情報を取得
		System.out.println("costomList" + customList);

		purchasedto.setMemory(customList.getMemory());
		purchasedto.setHardDisc(customList.getHardDisc());
		purchasedto.setCpu(customList.getCpu());
		purchasedto.setCustomPrice(customList.getCustomPrice());
		model.addAttribute("totalPrice", purchasedto.getPrice() + purchasedto.getCustomPrice());
		model.addAttribute("purchaseId", purchaseId);

		model.addAttribute("purchaseList", purchasedto);

		return "shopping/productListLayout";

	}

	@PostMapping(value = "/cancel", params = "cancelNext")
	public String postCancelCancelNext(@ModelAttribute CancelForm form, CancelNextForm nextform,
			@RequestParam("id") int purchaseId, HttpServletRequest request, HttpServletResponse response, Model model,
			String title) {
		model.addAttribute("contents", "shopping/cancelNext::productListLayout_contents");

		System.out.println("formnai" + form.getTitle());
		try {
			if (form.getTitle().equals("0")) {
				System.out.println("バリデーションエラー到達");
				model.addAttribute("result", "キャンセル理由を選択してください");
				return getCancel(form, nextform, purchaseId, model);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		System.out.println("cancelNext到達");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String getName = auth.getName();
		int select_id = usersService.select_id(getName);// メソッドに入ったユーザーのIDを取得

		PurchaseDTO purchasedto = new PurchaseDTO();

		// 購入商品情報取得
		PurchaseDTO purchasedtoList = purchaseService.reviewSelectHistory(select_id, purchaseId);// Pathで取得した購入IDでpurchaseテーブルの情報を取得
		purchasedto.setId(purchasedtoList.getId());
		purchasedto.setPurchaseId(purchasedtoList.getPurchaseId());
		purchasedto.setPurchase_date(purchasedtoList.getPurchase_date());
		purchasedto.setPcDataId(purchasedtoList.getPcDataId());
		purchasedto.setPcName(purchasedtoList.getPcName());
		purchasedto.setPrice(purchasedtoList.getPrice());
		purchasedto.setProduct_count(purchasedtoList.getProduct_count());
		purchasedto.setPurchaseCheck(purchasedtoList.getPurchaseCheck());

		String nullCheck = "null";
		int getCustomId = customService.selectPurchaseCheck(select_id, purchasedtoList.getProduct_id(), // 購入した商品のcustomテーブルIDを取得

				purchasedtoList.getPurchaseCheck(), nullCheck);
		System.out.println("getCustomId" + getCustomId);

		PurchaseDTO customList = customService.selectMany(getCustomId);// 購入した商品のcustomテーブル情報を取得
		System.out.println("costomList" + customList);

		purchasedto.setMemory(customList.getMemory());
		purchasedto.setHardDisc(customList.getHardDisc());
		purchasedto.setCpu(customList.getCpu());
		purchasedto.setCustomPrice(customList.getCustomPrice());
		model.addAttribute("totalPrice", purchasedto.getPrice() + purchasedto.getCustomPrice());
		model.addAttribute("purchaseId", purchaseId);

		model.addAttribute("purchaseList", purchasedto);

		HttpSession session = request.getSession();
		try {
			if (title == null) {
				System.out.println("title" + form.getTitle());
				System.out.println("sessionTitle" + session.getAttribute("title"));
				model.addAttribute("title", (String) session.getAttribute("title"));
			} else {
				System.out.println("titleelse" + title);
				session.setAttribute("title", title);
				model.addAttribute("title", (String) session.getAttribute("title"));
				session.setAttribute(title, (String) session.getAttribute("title"));
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		return "shopping/productListLayout";
	}

	@PostMapping(value = "/cancel", params = "confirmation")
	public String postCancelConfirmation(@ModelAttribute @Validated(GroupOrder.class) CancelNextForm nextform,
			BindingResult bindingResult, CancelForm form, @RequestParam("id") int purchaseId,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("contents", "shopping/cancelConfirmation::productListLayout_contents");

		if (bindingResult.hasErrors()) {
			System.out.println("バリデーションエラー到達");
			// model.addAttribute("result","キャンセル詳細を入力してください");
			String titleNew = (String) session.getAttribute("title");
			return postCancelCancelNext(form, nextform, purchaseId, request, response, model, titleNew);
		}

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String getName = auth.getName();
		int select_id = usersService.select_id(getName);// メソッドに入ったユーザーのIDを取得

		PurchaseDTO purchasedto = new PurchaseDTO();

		// 購入商品情報取得
		PurchaseDTO purchasedtoList = purchaseService.reviewSelectHistory(select_id, purchaseId);// Pathで取得した購入IDでpurchaseテーブルの情報を取得
		purchasedto.setId(purchasedtoList.getId());
		purchasedto.setPurchaseId(purchasedtoList.getPurchaseId());
		purchasedto.setPurchase_date(purchasedtoList.getPurchase_date());
		purchasedto.setPcDataId(purchasedtoList.getPcDataId());
		purchasedto.setPcName(purchasedtoList.getPcName());
		purchasedto.setPrice(purchasedtoList.getPrice());
		purchasedto.setProduct_count(purchasedtoList.getProduct_count());
		purchasedto.setPurchaseCheck(purchasedtoList.getPurchaseCheck());

		String nullCheck = "null";
		int getCustomId = customService.selectPurchaseCheck(select_id, purchasedtoList.getProduct_id(), // 購入した商品のcustomテーブルIDを取得

				purchasedtoList.getPurchaseCheck(), nullCheck);
		System.out.println("getCustomId" + getCustomId);

		PurchaseDTO customList = customService.selectMany(getCustomId);// 購入した商品のcustomテーブル情報を取得
		System.out.println("costomList" + customList);

		purchasedto.setMemory(customList.getMemory());
		purchasedto.setHardDisc(customList.getHardDisc());
		purchasedto.setCpu(customList.getCpu());
		purchasedto.setCustomPrice(customList.getCustomPrice());
		model.addAttribute("totalPrice", purchasedto.getPrice() + purchasedto.getCustomPrice());
		model.addAttribute("purchaseId", purchaseId);

		model.addAttribute("purchaseList", purchasedto);

		HttpSession session = request.getSession();

		model.addAttribute("title", (String) session.getAttribute("title"));
		session.setAttribute("content", nextform.getContent());
		model.addAttribute("content", nextform.getContent());

		return "shopping/productListLayout";
	}

	@PostMapping(value = "/cancel", params = "detail")
	public String postCancelDetail(@ModelAttribute CancelForm form, @RequestParam("id") int purchaseId, Model model) {

		model.addAttribute("contents", "shopping/cancelDetail::productListLayout_contents");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String getName = auth.getName();
		int select_id = usersService.select_id(getName);// メソッドに入ったユーザーのIDを取得

		PurchaseDTO purchasedto = new PurchaseDTO();

		// 購入商品情報取得
		PurchaseDTO purchasedtoList = purchaseService.reviewSelectHistory(select_id, purchaseId);// Pathで取得した購入IDでpurchaseテーブルの情報を取得
		purchasedto.setId(purchasedtoList.getId());
		purchasedto.setPurchaseId(purchasedtoList.getPurchaseId());
		purchasedto.setPurchase_date(purchasedtoList.getPurchase_date());
		System.out.println("datetest" + purchasedto.getPurchase_date());
		purchasedto.setPcDataId(purchasedtoList.getPcDataId());
		purchasedto.setPcName(purchasedtoList.getPcName());
		purchasedto.setPrice(purchasedtoList.getPrice());
		purchasedto.setProduct_count(purchasedtoList.getProduct_count());
		purchasedto.setPurchaseCheck(purchasedtoList.getPurchaseCheck());

		String nullCheck = "null";
		int getCustomId = customService.selectPurchaseCheck(select_id, purchasedtoList.getProduct_id(), // 購入した商品のcustomテーブルIDを取得
				purchasedtoList.getPurchaseCheck(), nullCheck);
		purchasedto.setCustom_id(getCustomId);
		System.out.println("getCustomId" + getCustomId);

		PurchaseDTO customList = customService.selectMany(getCustomId);// 購入した商品のcustomテーブル情報を取得
		System.out.println("costomList" + customList);

		purchasedto.setMemory(customList.getMemory());
		purchasedto.setHardDisc(customList.getHardDisc());
		purchasedto.setCpu(customList.getCpu());
		purchasedto.setCustomPrice(customList.getCustomPrice());
		model.addAttribute("totalPrice", purchasedto.getPrice() + purchasedto.getCustomPrice());
		model.addAttribute("purchaseId", purchaseId);

		model.addAttribute("purchaseList", purchasedto);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(purchasedto.getPurchase_date());// 商品の購入日をセット;
		calendar.add(Calendar.DATE, 4);// 発送予定日が購入日から3日から5日の間なので、間の4日目に発送する設定とする
		Date purchaseDate = calendar.getTime();
		Date nowDate = new Date();
		boolean check = nowDate.before(purchaseDate);
		System.out.println("purchaseDate" + purchaseDate);
		calendar.add(Calendar.DATE, 6);
		Date cancelOutDate = calendar.getTime();
		boolean cancelOutCheck = nowDate.before(cancelOutDate);
		System.out.println("cancelOutCheck" + cancelOutCheck);

		if (cancelOutCheck == false) {
			model.addAttribute("contents", "shopping/cancelInquiry::productListLayout_contents");

			return "shopping/productListLayout";
		}

		if (check == false) {
			model.addAttribute("contents", "shopping/cancelDeliveredDetail::productListLayout_contents");
			int maxId = cancelService.selectCancelCheck(purchaseId, select_id);
			if (maxId != 0) {
				System.out.println("maxid" + maxId);
				CancelDTO canceldtoNext = cancelService.cancelCheckSelect(maxId);
				System.out.println("canceldtocancelCheck" + canceldtoNext.getCancelCheck());
				String checkName = canceldtoNext.getCancelCheck();
				if (canceldtoNext.getCancelCheck() != "null") {
					System.out.println("2");
					return postCancelDeliveredDetail(form, model);
				}

			} else {
				return "shopping/productListLayout";
			}
		}

		return "shopping/productListLayout";
	}

	@PostMapping(value = "/cancel", params = "completed")
	public String postCancelCompleted(@ModelAttribute @Validated(GroupOrder.class) CancelForm form,
			BindingResult bidingResult, @RequestParam("id") int purchaseId, @RequestParam("customId") int customId,
			HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectattributes,
			Model model) {
		model.addAttribute("contents", "shopping/cancelCompleted::productListLayout_contents");

		if (bidingResult.hasErrors()) {
			System.out.println("バリデーションエラー到達");
			return postCancelDetail(form, purchaseId, model);
		}

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String getName = auth.getName();
		int userId = usersService.select_id(getName);// メソッドに入ったユーザーのIDを取得

		PurchaseDTO purchasedto = new PurchaseDTO();

		// 購入商品情報取得
		PurchaseDTO purchasedtoList = purchaseService.reviewSelectHistory(userId, purchaseId);// Pathで取得した購入IDでpurchaseテーブルの情報を取得
		if (purchasedtoList.getId() == 0) {
			return "redirect:/purchaseHistory";
		}
		purchasedto.setId(purchasedtoList.getId());
		purchasedto.setPurchaseId(purchasedtoList.getPurchaseId());
		purchasedto.setPurchase_date(purchasedtoList.getPurchase_date());
		purchasedto.setPcDataId(purchasedtoList.getPcDataId());
		purchasedto.setProduct_id(purchasedtoList.getProduct_id());
		int productId = purchasedto.getProduct_id();
		purchasedto.setPcName(purchasedtoList.getPcName());
		purchasedto.setPrice(purchasedtoList.getPrice());
		purchasedto.setProduct_count(purchasedtoList.getProduct_count());
		int productStock = purchasedto.getProduct_count();
		System.out.println("stock" + productStock);
		purchasedto.setPurchaseCheck(purchasedtoList.getPurchaseCheck());

		PurchaseDTO customList = customService.selectMany(customId);// 購入した商品のcustomテーブル情報を取得
		System.out.println("costomList" + customList);

		purchasedto.setMemory(customList.getMemory());
		purchasedto.setHardDisc(customList.getHardDisc());
		purchasedto.setCpu(customList.getCpu());
		purchasedto.setCustomPrice(customList.getCustomPrice());
		model.addAttribute("totalPrice", purchasedto.getPrice() + purchasedto.getCustomPrice());
		model.addAttribute("purchaseId", purchaseId);

		model.addAttribute("purchaseList", purchasedto);

		int stock = pcdataService.updateOne(purchasedto, productStock);
		System.out.println("stock2" + stock);

		HttpSession session = request.getSession();
		String title = (String) session.getAttribute("title");
		System.out.println("title" + title);
		String content = (String) session.getAttribute("content");
		CancelDTO canceldto = new CancelDTO();
		int bankNumber = Integer.parseInt(form.getBankNumber());
		model.addAttribute("bankNumber", bankNumber);
		int storeName = Integer.parseInt(form.getStoreName());
		model.addAttribute("storeName", storeName);
		cancelService.insertOne(canceldto, userId, purchaseId, productId, title, content, bankNumber, storeName);

		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		calendar.setTime(purchasedto.getPurchase_date());
		Date purchaseDate = calendar.getTime();// 購入日付
		long d = (purchaseDate.getTime() - now.getTime()) / (1000 * 60 * 60 * 24);// 購入日と現在の日付を比べる
		int count = (int) -d;

		if (count > 10) {
			System.out.println("true");
			model.addAttribute("result", "購入日から10日経過したため、キャンセル期間外になりました。");
			model.addAttribute("totalPrice", purchasedto.getPrice() + purchasedto.getCustomPrice());
			model.addAttribute("purchaseId", purchaseId);

			model.addAttribute("purchaseList", purchasedto);
			model.addAttribute("contents", "shopping/cancelInquiry::productListLayout_contents");
			return "shopping/productListLayout";
		}
		cancelService.cancelCompletedUpdate(purchaseId);// キャンセル完了に伴い、キャンセルチェックをキャンセル完了に変更
		customService.deleteOne(customId);// キャンセル完了に伴い、カスタムデータ削除
		purchaseService.deleteOne(purchaseId);// キャンセル完了に伴い、購入データ削除

		return "shopping/productListLayout";

	}

	@PostMapping("/cancelDeliveredDetail::productListLayout_contents")
	public String postCancelDeliveredDetail(@ModelAttribute CancelForm form, Model model) {
		model.addAttribute("contents", "shopping/cancelDeliveredDetail::productListLayout_contents");
		model.addAttribute("result", "すでに口座情報が入力されています。次のページ表示や、キャンセル取り消しをする場合は商品履歴画面から上記商品の商品問題から行ってください。");
		model.addAttribute("check", "再入力不可");
		return "shopping/productListLayout";
	}

	@PostMapping("/cancelDeliveryComplete")
	public String postCancelDeliveryComplete(@ModelAttribute CancelForm form, CancelInTransactionForm intransactionform,
			@RequestParam("id") int purchaseId, @RequestParam("customId") int customId, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		model.addAttribute("contents", "shopping/cancelDeliveryComplete::productListLayout_contents");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String getName = auth.getName();
		int userId = usersService.select_id(getName);// メソッドに入ったユーザーのIDを取得

		PurchaseDTO purchasedto = new PurchaseDTO();

		// 購入商品情報取得
		PurchaseDTO purchasedtoList = purchaseService.reviewSelectHistory(userId, purchaseId);// Pathで取得した購入IDでpurchaseテーブルの情報を取得
		purchasedto.setId(purchasedtoList.getId());
		purchasedto.setPurchaseId(purchasedtoList.getPurchaseId());
		purchasedto.setPurchase_date(purchasedtoList.getPurchase_date());
		purchasedto.setPcDataId(purchasedtoList.getPcDataId());
		purchasedto.setProduct_id(purchasedtoList.getProduct_id());
		int productId = purchasedto.getProduct_id();
		purchasedto.setPcName(purchasedtoList.getPcName());
		purchasedto.setPrice(purchasedtoList.getPrice());
		purchasedto.setProduct_count(purchasedtoList.getProduct_count());
		int productStock = purchasedto.getProduct_count();
		System.out.println("stock" + productStock);
		purchasedto.setPurchaseCheck(purchasedtoList.getPurchaseCheck());

		PurchaseDTO customList = customService.selectMany(customId);// 購入した商品のcustomテーブル情報を取得
		System.out.println("costomList" + customList);
		purchasedto.setCustom_id(customId);
		purchasedto.setMemory(customList.getMemory());
		purchasedto.setHardDisc(customList.getHardDisc());
		purchasedto.setCpu(customList.getCpu());
		purchasedto.setCustomPrice(customList.getCustomPrice());
		model.addAttribute("totalPrice", purchasedto.getPrice() + purchasedto.getCustomPrice());
		model.addAttribute("purchaseId", purchaseId);

		model.addAttribute("purchaseList", purchasedto);

		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		calendar.setTime(purchasedto.getPurchase_date());
		Date purchaseDate = calendar.getTime();// 購入日付
		long d = (purchaseDate.getTime() - now.getTime()) / (1000 * 60 * 60 * 24);// 購入日と現在の日付を比べる
		int count = (int) -d;

		if (count > 10) {
			System.out.println("true");
			model.addAttribute("result", "購入日から10日経過したため、キャンセル期間外になりました。");
			model.addAttribute("totalPrice", purchasedto.getPrice() + purchasedto.getCustomPrice());
			model.addAttribute("purchaseId", purchaseId);

			model.addAttribute("purchaseList", purchasedto);
			model.addAttribute("contents", "shopping/cancelInquiry::productListLayout_contents");
			return "shopping/productListLayout";
		}

		HttpSession session = request.getSession();
		String title = (String) session.getAttribute("title");
		System.out.println("title" + title);
		String content = (String) session.getAttribute("content");
		CancelDTO canceldto = new CancelDTO();
		if (form.getBankNumber() != null) {
			int bankNumber = Integer.parseInt(form.getBankNumber());
			model.addAttribute("bankNumber", bankNumber);
			int storeName = Integer.parseInt(form.getStoreName());

			int maxId = cancelService.selectCancelCheck(purchaseId, userId);
			if (maxId == 0) {
				cancelService.insertOneCancelCheck(canceldto, userId, purchaseId, productId, title, content, bankNumber,
						storeName);
				purchaseService.insertOneCancelCheck(purchaseId);
				return "shopping/productListLayout";
			} else {
				System.out.println("maxid" + maxId);
				CancelDTO canceldtoNext = cancelService.cancelCheckSelect(maxId);
				System.out.println("canceldtocancelCheck" + canceldtoNext.getCancelCheck());
				String checkName = canceldtoNext.getCancelCheck();
				if (canceldtoNext.getCancelCheck() == "null") {
					System.out.println("1");
					cancelService.insertOneCancelCheck(canceldto, userId, purchaseId, productId, title, content,
							bankNumber, storeName);
					purchaseService.insertOneCancelCheck(purchaseId);
				} else if ((canceldtoNext.getCancelCheck() == "キャンセル取引中")
						&& (canceldtoNext.getCancelCheck() != "返品商品確認待ち")) {
					System.out.println("2");
					model.addAttribute("result", "すでに口座情報が入力されています。キャンセル取り消しをする場合は商品履歴画面から上記商品の商品問題から行ってください。");
					model.addAttribute("contents", "shopping/cancelDeliveredDetail::productListLayout_contents");
					return "shopping/productListLayout";
				} else {
					System.out.println("3");
				}

			}
		} else {

		}

		int result = cancelService.deliveryAddressSelect(purchaseId);
//		if (result == 0) {
//			System.out.println("1");
//			cancelService.cancelCheckUpdate(purchaseId, intransactionform.getDeliveryAddress());
//			purchaseService.cancelCheckUpdateNext(purchaseId);
		if (result == 1) {
			System.out.println("22");
			model.addAttribute("result", "すでに発送場所が入力済みです。発送状況を確認するには商品履歴画面の商品問題からご覧になれます。");
			model.addAttribute("contents", "shopping/cancelDeliveryComplete::productListLayout_contents");
			String deriveredCheck = cancelService.deriveredCheckSelect(purchaseId);
			if ((deriveredCheck != "null") && (deriveredCheck != "キャンセル取引中")) {
				System.out.println("3");
				model.addAttribute("notCancel", "notCancel");
			}
			System.out.println("4");
			return "shopping/productListLayout";
		}
		return "shopping/productListLayout";

	}

	@PostMapping(value = "/cancelDeliveryComplete", params = "deliveredCompleted")
	public String postCancelDeliveryCompleteDeliveredCompleted(@ModelAttribute CancelForm form,
			@Validated(GroupOrder.class) CancelInTransactionForm intransactionform, BindingResult bindingResult,
			@RequestParam("id") int purchaseId, @RequestParam("customId") int customId, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		model.addAttribute("contents", "shopping/cancelDeliveredCompleted::productListLayout_contents");

		if (bindingResult.hasErrors()) {
			System.out.println("1");
			return postCancelDeliveryComplete(form, intransactionform, purchaseId, customId, request, response, model);
		}

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String getName = auth.getName();
		int userId = usersService.select_id(getName);// メソッドに入ったユーザーのIDを取得

		PurchaseDTO purchasedto = new PurchaseDTO();

		CancelDTO canceldto = new CancelDTO();

		Calendar calendar = Calendar.getInstance();
		Date nowTime = calendar.getTime();
		canceldto = cancelService.deliveryDateCheck(canceldto, purchaseId);
		if (canceldto.getDeliveryDate() == null) {
			System.out.println("dateがnull");
			cancelService.deliveryDateUpdate(purchaseId, nowTime);
		}

		// 購入商品情報取得
		PurchaseDTO purchasedtoList = purchaseService.reviewSelectHistory(userId, purchaseId);// Pathで取得した購入IDでpurchaseテーブルの情報を取得
		purchasedto.setId(purchasedtoList.getId());
		purchasedto.setPurchaseId(purchasedtoList.getPurchaseId());
		purchasedto.setPurchase_date(purchasedtoList.getPurchase_date());
		purchasedto.setPcDataId(purchasedtoList.getPcDataId());
		purchasedto.setProduct_id(purchasedtoList.getProduct_id());
		int productId = purchasedto.getProduct_id();
		purchasedto.setPcName(purchasedtoList.getPcName());
		purchasedto.setPrice(purchasedtoList.getPrice());
		purchasedto.setProduct_count(purchasedtoList.getProduct_count());
		int productStock = purchasedto.getProduct_count();
		System.out.println("stock" + productStock);
		purchasedto.setPurchaseCheck(purchasedtoList.getPurchaseCheck());

		PurchaseDTO customList = customService.selectMany(customId);// 購入した商品のcustomテーブル情報を取得
		System.out.println("costomList" + customList);

		purchasedto.setCustom_id(customId);
		purchasedto.setMemory(customList.getMemory());
		purchasedto.setHardDisc(customList.getHardDisc());
		purchasedto.setCpu(customList.getCpu());
		purchasedto.setCustomPrice(customList.getCustomPrice());
		model.addAttribute("totalPrice", purchasedto.getPrice() + purchasedto.getCustomPrice());
		model.addAttribute("purchaseId", purchaseId);

		model.addAttribute("purchaseList", purchasedto);

		canceldto = cancelService.selectDerivaryDate(purchaseId);// 返品商品の発送日を取得
		Date deliveryDate = canceldto.getDeliveryDate();
		Date now = calendar.getTime();// 現在の日付を取得
		long nowNew = now.getTime();
		calendar.setTime(deliveryDate);
		Date deliveryDateNew = calendar.getTime();
		long deliveryDateNewNext = deliveryDateNew.getTime();// 返品商品の発送日
		long oneDateTime = 1000 * 60 * 40 * 24;// 返品商品の発送日と現在の日付の差を出すために使用する値
		double diffDays = (deliveryDateNewNext - nowNew) / oneDateTime;// 返品商品の発送日と現在の日付の差
		double diffDaysNew = -diffDays;
		System.out.println("nowNew" + nowNew);
		System.out.println(deliveryDateNewNext);
		System.out.println(diffDaysNew);
		System.out.println(oneDateTime);

		if (1 >= diffDaysNew) {

			model.addAttribute("deliveryInformation", "1");
		} else if (2 >= diffDaysNew) {

			model.addAttribute("deliveryInformation", "2");
		} else if (3 >= diffDaysNew) {
			model.addAttribute("deliveryInformation", "3");
		} else if (4 >= diffDaysNew) {
			model.addAttribute("deliveryInformation", "4");
		} else if (5 >= diffDaysNew) {
			model.addAttribute("deliveryInformation", "5");
		} else {
			model.addAttribute("deliveryInformation", "6");
		}

		int result = cancelService.deliveryAddressSelect(purchaseId);
		if (result == 0) {
			System.out.println("1");
			cancelService.cancelCheckUpdate(purchaseId, intransactionform.getDeliveryAddress());
			purchaseService.cancelCheckUpdateNext(purchaseId);
		} else {
			System.out.println("2");
			model.addAttribute("result", "すでに発送場所が入力済みです。発送状況を確認するには商品履歴画面の商品問題からご覧になれます。");
			model.addAttribute("contents", "shopping/cancelDeliveryComplete::productListLayout_contents");
			String deriveredCheck = cancelService.deriveredCheckSelect(purchaseId);
			if ((deriveredCheck != "null") && (deriveredCheck != "キャンセル取引中")) {
				System.out.println("3");
				model.addAttribute("notCancel", "notCancel");
			}
			System.out.println("4");
			return "shopping/productListLayout";
		}
		System.out.println("5");
		return "shopping/productListLayout";

	}

	@PostMapping(value = "/cancelDeliveryComplete", params = "cancelDelete")
	public String postCancelDeliveryCompleteCancelDelete(@ModelAttribute CancelForm form,
			CancelInTransactionForm intransactionform, @RequestParam("id") int purchaseId,
			@RequestParam("customId") int customId, Model model) {

		model.addAttribute("contents", "shopping/cancelDelete::productListLayout_contents");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String getName = auth.getName();
		int userId = usersService.select_id(getName);// メソッドに入ったユーザーのIDを取得

		PurchaseDTO purchasedto = new PurchaseDTO();

		// 購入商品情報取得
		PurchaseDTO purchasedtoList = purchaseService.reviewSelectHistory(userId, purchaseId);// Pathで取得した購入IDでpurchaseテーブルの情報を取得
		purchasedto.setId(purchasedtoList.getId());
		purchasedto.setPurchaseId(purchasedtoList.getPurchaseId());
		purchasedto.setPurchase_date(purchasedtoList.getPurchase_date());
		purchasedto.setPcDataId(purchasedtoList.getPcDataId());
		purchasedto.setProduct_id(purchasedtoList.getProduct_id());
		int productId = purchasedto.getProduct_id();
		purchasedto.setPcName(purchasedtoList.getPcName());
		purchasedto.setPrice(purchasedtoList.getPrice());
		purchasedto.setProduct_count(purchasedtoList.getProduct_count());
		int productStock = purchasedto.getProduct_count();
		System.out.println("stock" + productStock);
		purchasedto.setPurchaseCheck(purchasedtoList.getPurchaseCheck());

		PurchaseDTO customList = customService.selectMany(customId);// 購入した商品のcustomテーブル情報を取得
		System.out.println("costomList" + customList);

		purchasedto.setCustom_id(customId);
		purchasedto.setMemory(customList.getMemory());
		purchasedto.setHardDisc(customList.getHardDisc());
		purchasedto.setCpu(customList.getCpu());
		purchasedto.setCustomPrice(customList.getCustomPrice());
		model.addAttribute("totalPrice", purchasedto.getPrice() + purchasedto.getCustomPrice());
		model.addAttribute("purchaseId", purchaseId);

		model.addAttribute("purchaseList", purchasedto);

		return "shopping/productListLayout";

	}

	@PostMapping(value = "/cancelDelete", params = "yes")
	public String postCancelDeliveryCompleteYes(@ModelAttribute CancelForm form,
			CancelInTransactionForm intransactionform, @RequestParam("id") int purchaseId,
			@RequestParam("customId") int customId, Model model) {

		cancelService.deleteOne(purchaseId);
		purchaseService.cancelCheckUpdate(purchaseId);

		PcDataForm pcdataForm = new PcDataForm();
		return getPurchaseHistory(pcdataForm, model);

	}

	@PostMapping(value = "/cancelDelete", params = "no")
	public String postCancelDeleteNo(@ModelAttribute CancelForm form, CancelInTransactionForm intransactionform,
			@RequestParam("id") int purchaseId, @RequestParam("customId") int customId, HttpServletRequest request,
			HttpServletResponse response, Model model) {

		model.addAttribute("contents", "shopping/cancelDeliveryComplete::productListLayout_contents");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String getName = auth.getName();
		int userId = usersService.select_id(getName);// メソッドに入ったユーザーのIDを取得

		PurchaseDTO purchasedto = new PurchaseDTO();

		// 購入商品情報取得
		PurchaseDTO purchasedtoList = purchaseService.reviewSelectHistory(userId, purchaseId);// Pathで取得した購入IDでpurchaseテーブルの情報を取得
		purchasedto.setId(purchasedtoList.getId());
		purchasedto.setPurchaseId(purchasedtoList.getPurchaseId());
		purchasedto.setPurchase_date(purchasedtoList.getPurchase_date());
		purchasedto.setPcDataId(purchasedtoList.getPcDataId());
		purchasedto.setProduct_id(purchasedtoList.getProduct_id());
		int productId = purchasedto.getProduct_id();
		purchasedto.setPcName(purchasedtoList.getPcName());
		purchasedto.setPrice(purchasedtoList.getPrice());
		purchasedto.setProduct_count(purchasedtoList.getProduct_count());
		int productStock = purchasedto.getProduct_count();
		System.out.println("stock" + productStock);
		purchasedto.setPurchaseCheck(purchasedtoList.getPurchaseCheck());

		PurchaseDTO customList = customService.selectMany(customId);// 購入した商品のcustomテーブル情報を取得
		System.out.println("costomList" + customList);

		purchasedto.setCustom_id(customId);
		purchasedto.setMemory(customList.getMemory());
		purchasedto.setHardDisc(customList.getHardDisc());
		purchasedto.setCpu(customList.getCpu());
		purchasedto.setCustomPrice(customList.getCustomPrice());
		model.addAttribute("totalPrice", purchasedto.getPrice() + purchasedto.getCustomPrice());
		model.addAttribute("purchaseId", purchaseId);

		model.addAttribute("purchaseList", purchasedto);

		return "shopping/productListLayout";
	}

	@GetMapping("/cancelInTransaction/{id}")
	public String getCancelInTransaction(@ModelAttribute CancelInTransactionForm form,
			@PathVariable("id") int purchaseId, HttpServletRequest request, HttpServletResponse response, Model model) {

		model.addAttribute("contents", "shopping/cancelDeliveryComplete::productListLayout_contents");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String getName = auth.getName();
		int userId = usersService.select_id(getName);// メソッドに入ったユーザーのIDを取得

		PurchaseDTO purchasedto = new PurchaseDTO();

		// 購入商品情報取得
		PurchaseDTO purchasedtoList = purchaseService.reviewSelectHistory(userId, purchaseId);// Pathで取得した購入IDでpurchaseテーブルの情報を取得
		purchasedto.setId(purchasedtoList.getId());
		purchasedto.setPurchaseId(purchasedtoList.getPurchaseId());
		purchasedto.setPurchase_date(purchasedtoList.getPurchase_date());
		purchasedto.setPcDataId(purchasedtoList.getPcDataId());
		purchasedto.setProduct_id(purchasedtoList.getProduct_id());
		int productId = purchasedto.getProduct_id();
		purchasedto.setPcName(purchasedtoList.getPcName());
		purchasedto.setPrice(purchasedtoList.getPrice());
		purchasedto.setProduct_count(purchasedtoList.getProduct_count());
		int productStock = purchasedto.getProduct_count();
		System.out.println("stock" + productStock);
		purchasedto.setPurchaseCheck(purchasedtoList.getPurchaseCheck());

		String nullCheck = "null";
		int customId = customService.selectPurchaseCheck(userId, purchasedtoList.getProduct_id(),
				purchasedtoList.getPurchaseCheck(), nullCheck); // 購入した商品のcustomテーブルIDを取得

		PurchaseDTO customList = customService.selectMany(customId);// 購入した商品のcustomテーブル情報を取得
		System.out.println("costomList" + customList);

		purchasedto.setCustom_id(customId);
		purchasedto.setMemory(customList.getMemory());
		purchasedto.setHardDisc(customList.getHardDisc());
		purchasedto.setCpu(customList.getCpu());
		purchasedto.setCustomPrice(customList.getCustomPrice());
		model.addAttribute("totalPrice", purchasedto.getPrice() + purchasedto.getCustomPrice());
		model.addAttribute("purchaseId", purchaseId);

		model.addAttribute("purchaseList", purchasedto);

		return "shopping/productListLayout";

	}

	@GetMapping("/confirmationPending/{id}")
	public String getConfirmationPending(@ModelAttribute CancelForm form, CancelInTransactionForm intransactionform,
			@PathVariable("id") int purchaseId, Model model) {
		model.addAttribute("contents", "shopping/cancelDeliveredCompleted::productListLayout_contents");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String getName = auth.getName();
		int select_id = usersService.select_id(getName);// メソッドに入ったユーザーのIDを取得

		PurchaseDTO purchasedto = new PurchaseDTO();

		// 購入商品情報取得
		PurchaseDTO purchasedtoList = purchaseService.reviewSelectHistory(select_id, purchaseId);// Pathで取得した購入IDでpurchaseテーブルの情報を取得
		purchasedto.setId(purchasedtoList.getId());
		purchasedto.setPurchaseId(purchasedtoList.getPurchaseId());
		purchasedto.setPurchase_date(purchasedtoList.getPurchase_date());
		purchasedto.setPcDataId(purchasedtoList.getPcDataId());
		purchasedto.setPcName(purchasedtoList.getPcName());
		purchasedto.setPrice(purchasedtoList.getPrice());
		purchasedto.setProduct_count(purchasedtoList.getProduct_count());
		purchasedto.setPurchaseCheck(purchasedtoList.getPurchaseCheck());

		String nullCheck = "null";
		int getCustomId = customService.selectPurchaseCheck(select_id, purchasedtoList.getProduct_id(),
				purchasedtoList.getPurchaseCheck(), nullCheck); // 購入した商品のcustomテーブルIDを取得
		PurchaseDTO customList = customService.selectMany(getCustomId);// 購入した商品のcustomテーブル情報を取得
		System.out.println("costomList" + customList);

		purchasedto.setCustom_id(getCustomId);
		purchasedto.setMemory(customList.getMemory());
		purchasedto.setHardDisc(customList.getHardDisc());
		purchasedto.setCpu(customList.getCpu());
		purchasedto.setCustomPrice(customList.getCustomPrice());
		model.addAttribute("totalPrice", purchasedto.getPrice() + purchasedto.getCustomPrice());
		model.addAttribute("purchaseId", purchaseId);

		model.addAttribute("purchaseList", purchasedto);

		CancelDTO canceldto = new CancelDTO();
		Calendar calendar = Calendar.getInstance();
		Date nowTime = calendar.getTime();
		canceldto = cancelService.deliveryDateCheck(canceldto, purchaseId);
		if (canceldto.getDeliveryDate() == null) {
			System.out.println("dateがnull");
			cancelService.deliveryDateUpdate(purchaseId, nowTime);
		}

		canceldto = cancelService.selectDerivaryDate(purchaseId);// 返品商品の発送日を取得
		Date deliveryDate = canceldto.getDeliveryDate();
		Date now = calendar.getTime();// 現在の日付を取得
		long nowNew = now.getTime();
		calendar.setTime(deliveryDate);
		Date deliveryDateNew = calendar.getTime();
		long deliveryDateNewNext = deliveryDateNew.getTime();// 返品商品の発送日
		long oneDateTime = 1000 * 60 * 40 * 24;// 返品商品の発送日と現在の日付の差を出すために使用する値
		double diffDays = (deliveryDateNewNext - nowNew) / oneDateTime;// 返品商品の発送日と現在の日付の差
		double diffDaysNew = -diffDays;
		System.out.println("nowNew" + nowNew);
		System.out.println(deliveryDateNewNext);
		System.out.println(diffDaysNew);
		System.out.println(oneDateTime);

		if (1 >= diffDaysNew) {

			model.addAttribute("deliveryInformation", "1");
		} else if (2 >= diffDaysNew) {

			model.addAttribute("deliveryInformation", "2");
		} else if (3 >= diffDaysNew) {
			model.addAttribute("deliveryInformation", "3");
		} else if (4 >= diffDaysNew) {
			model.addAttribute("deliveryInformation", "4");
		} else if (5 >= diffDaysNew) {
			model.addAttribute("deliveryInformation", "5");
		} else {
			model.addAttribute("deliveryInformation", "6");
		}

		if (6 >= diffDaysNew) {
			model.addAttribute("productConfirmation", "商品確認中");
		} else {
			model.addAttribute("productConfirmation", "商品確認完了");
		}
		return "shopping/productListLayout";
	}

	@GetMapping("/cancelCompleted/{id}")
	public String getCancelCompleted(@ModelAttribute CancelForm form, @PathVariable("id") int purchaseId, Model model) {
		model.addAttribute("contents", "shopping/cancelCompleted::productListLayout_contents");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String getName = auth.getName();
		int userId = usersService.select_id(getName);// メソッドに入ったユーザーのIDを取得

		PurchaseDTO purchasedto = new PurchaseDTO();

		// 購入商品情報取得
		PurchaseDTO purchasedtoList = purchaseService.reviewSelectHistory(userId, purchaseId);// Pathで取得した購入IDでpurchaseテーブルの情報を取得
		purchasedto.setId(purchasedtoList.getId());
		purchasedto.setPurchaseId(purchasedtoList.getPurchaseId());
		purchasedto.setPurchase_date(purchasedtoList.getPurchase_date());
		purchasedto.setPcDataId(purchasedtoList.getPcDataId());
		purchasedto.setProduct_id(purchasedtoList.getProduct_id());
		int productId = purchasedto.getProduct_id();
		purchasedto.setPcName(purchasedtoList.getPcName());
		purchasedto.setPrice(purchasedtoList.getPrice());
		purchasedto.setProduct_count(purchasedtoList.getProduct_count());
		int productStock = purchasedto.getProduct_count();
		System.out.println("stock" + productStock);
		purchasedto.setPurchaseCheck(purchasedtoList.getPurchaseCheck());

		String nullCheck = "null";
		int customId = customService.selectPurchaseCheck(userId, purchasedtoList.getProduct_id(),
				purchasedtoList.getPurchaseCheck(), nullCheck); // 購入した商品のcustomテーブルIDを取得

		PurchaseDTO customList = customService.selectMany(customId);// 購入した商品のcustomテーブル情報を取得
		System.out.println("costomList" + customList);

		purchasedto.setCustom_id(customId);
		purchasedto.setMemory(customList.getMemory());
		purchasedto.setHardDisc(customList.getHardDisc());
		purchasedto.setCpu(customList.getCpu());
		purchasedto.setCustomPrice(customList.getCustomPrice());
		model.addAttribute("totalPrice", purchasedto.getPrice() + purchasedto.getCustomPrice());
		model.addAttribute("purchaseId", purchaseId);

		model.addAttribute("purchaseList", purchasedto);

		CancelDTO canceldto = new CancelDTO();
		canceldto = cancelService.selectOne(purchaseId);
		model.addAttribute("bankNumber", canceldto.getBankNumber());
		model.addAttribute("storeName", canceldto.getStoreName());
		model.addAttribute("cancelCheck", "返品完了");
		System.out.println("candto" + canceldto);

		cancelService.cancelCompletedUpdate(purchaseId);// キャンセル完了に伴い、キャンセルチェックをキャンセル完了に変更
		customService.deleteOne(customId);// キャンセル完了に伴い、カスタムデータ削除
		purchaseService.deleteOne(purchaseId);// キャンセル完了に伴い、購入データ削除

		return "shopping/productListLayout";

	}

	@GetMapping("/couponList")
	public String getCouponList(@ModelAttribute CouponForm form, Model model) {
		model.addAttribute("contents", "shopping/couponList::productListLayout_contents");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String getName = auth.getName();
		int userId = usersService.select_id(getName);// メソッドに入ったユーザーのIDを取得

		int allProductCount = 0;
		int allTotalPrice = 0;

		List<PurchaseDTO> purchasedtoList = purchaseService.selectHistory(userId);// メソッドに入ったユーザーの購入情報を取得
		List<PurchaseDTO> allPurchaseList = new ArrayList<>();
		List<Integer> couponUsedId = new ArrayList<>();// 使用済みクーポンIDを格納
		PurchaseDTO customList;
		// 購入商品を一つづつ回して値を受け取る
		for (int i = 0; purchasedtoList.size() > i; i++) {
			PurchaseDTO purchasedtoAdd = new PurchaseDTO();
			PurchaseDTO purchaseOne = purchasedtoList.get(i);
			purchasedtoAdd.setId(purchaseOne.getId());// カスタム情報取得に使用
			purchasedtoAdd.setCancelCheck(purchaseOne.getCancelCheck());
			purchasedtoAdd.setPrice(purchaseOne.getPrice());
			purchasedtoAdd.setProduct_count(purchaseOne.getProduct_count());
			purchasedtoAdd.setPurchaseCheck(purchaseOne.getPurchaseCheck());
			purchasedtoAdd.setCouponId(purchaseOne.getCouponId());// 購入者のクーポンID情報を取得
			if (purchasedtoAdd.getCouponId() > 0) {// クーポンを使用して購入していればtrue
				int couponId = purchasedtoAdd.getCouponId();
				couponUsedId.add(couponId);
			} else {
				couponUsedId.add(-1);
			}
			int productId = purchasedtoAdd.getId();
			// 購入商品ごとのカスタム情報も取り出す
			String nullCheck = "null";
			int getCustomId = customService.selectPurchaseCheck(userId, productId, purchasedtoAdd.getPurchaseCheck(),
					nullCheck);

			customList = customService.selectMany(getCustomId);// customテーブルのIDでカスタム情報を取得
			purchasedtoAdd.setCustomPrice(customList.getCustomPrice());
			purchasedtoAdd.setTotalPrice(
					purchaseOne.getProduct_count() * (customList.getCustomPrice() + purchaseOne.getPrice()));
			allTotalPrice = allTotalPrice + purchasedtoAdd.getTotalPrice();// ユーザーの購入した商品の合計金額を取得
			allProductCount = allProductCount + purchasedtoAdd.getProduct_count();// ユーザーの購入した商品の数を取得
			model.addAttribute("purchaseCount", allProductCount);
			model.addAttribute("allTotalPrice", allTotalPrice);
			allPurchaseList.add(purchasedtoAdd);
		}

		// 一度使用したクーポンは表示されないようにする
		// 使用済みクーポンの一枚目のIDを使用して、使用前クーポンListを作成

		try {
			List<Integer> couponId = couponService.selectIdMany();// 全クーポン
			System.out.println("全クーポン" + couponId);
			System.out.println("使用済みクーポン" + couponUsedId);
			List<Integer> beforeUseCouponId = new ArrayList<>();// 使用前クーポン
			for (int i = 0; couponId.size() > i; i++) {// 全クーポンの数文入る
				if (couponId.get(i) != couponUsedId.get(0)) {// 全クーポンと使用済みクーポンの最初に一枚を比べて、使用前クーポンならtrue
					beforeUseCouponId.add(couponId.get(i));// 使用済みクーポン一枚に対しての、使用前クーポンListを構築
				}
				System.out.println("beforeUseCouponId" + beforeUseCouponId);
			}

			// 上記で使用済みクーポン一枚に対しての、使用前クーポンListを構築したため下記に移る
			// 下記では残りの使用済みクーポンIDを上記で作成した使用前クーポンListと比べている

			if (couponUsedId.size() >= 2) {// 使用済みクーポンが2枚以上あればtrue
				for (int x = 1; couponUsedId.size() > x; x++) {// 上記のコードで使用した使用済みクーポン１枚を除いた使用済みクーポン文を繰り返す
					List<Integer> dummyBeforeUseCouponId = new ArrayList<>();
					for (int i = 0; beforeUseCouponId.size() > i; i++) {// 上記で構築した使用前クーポンの数文繰り返す
						if (beforeUseCouponId.get(i) != couponUsedId.get(x)) {
							dummyBeforeUseCouponId.add(beforeUseCouponId.get(i));
						}
					}
					beforeUseCouponId = dummyBeforeUseCouponId;
				}
				System.out.println("beforeUseCouponId" + beforeUseCouponId);
			}

			List<CouponDTO> coupondtoList = new ArrayList<>();

			for (int i = 0; beforeUseCouponId.size() > i; i++) {
				List<CouponDTO> dummyCoupondtoList = new ArrayList<>();
				dummyCoupondtoList = couponService.selectMany(beforeUseCouponId.get(i));// couponテーブルからクーポン情報をすべて取得
				coupondtoList.addAll(dummyCoupondtoList);
			}

			List<CouponDTO> coupondtoListAdd = new ArrayList<>();

			for (int i = 0; coupondtoList.size() > i; i++) {// クーポン情報を一つづつ取り出す
				CouponDTO coupondtoOne = coupondtoList.get(i);
				int allCount = coupondtoOne.getPurchaseCountTarget();
				int allPrice = coupondtoOne.getPurchaseTotalPriceTarget();

				boolean countCheck = false;
				boolean priceCheck = false;

				if (allProductCount >= allCount) {// ユーザーの商品購入数と、クーポンの使用条件の商品購入数を比較
					countCheck = true;
				}
				if (allTotalPrice >= allPrice) {// ユーザーの全商品購入金額と、クーポンの使用条件の全商品購入金額を比較
					priceCheck = true;
				}

				coupondtoOne.setCouponCheck(false);
				if ((countCheck == true) && (priceCheck == true)) {// 商品購入数と全商品購入金額がクーポンの使用条件に達しているか比較
					coupondtoOne.setCouponCheck(true);
				}
				Calendar calendar = Calendar.getInstance();
				Date now = calendar.getTime();
				calendar.setTime(coupondtoOne.getExpirationDate());
				Date expirationDate = calendar.getTime();// 購入日付
				model.addAttribute("expirationDate", expirationDate);
				System.out.println("nw" + now);
				System.out.println("ex" + expirationDate);
				long d = (expirationDate.getTime() - now.getTime()) / (1000 * 60 * 60 * 24);// 購入日と現在の日付を比べる
				int count = (int) -d;
				System.out.println("count+++++++++++++++++++++++++++++++" + count);
				if (count < 0) {
					coupondtoListAdd.add(coupondtoOne);
				}
				System.out.println("couponCheck" + coupondtoOne.isCouponCheck());

			}

			model.addAttribute("couponList", coupondtoListAdd);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();

			List<CouponDTO> coupondtoListAdd = new ArrayList<>();
			List<CouponDTO> coupondtoList = couponService.beforePurchaseSelectMany();
			for (int i = 0; coupondtoList.size() > i; i++) {// クーポン情報を一つづつ取り出す
				CouponDTO coupondtoOne = coupondtoList.get(i);
				int allCount = coupondtoOne.getPurchaseCountTarget();
				int allPrice = coupondtoOne.getPurchaseTotalPriceTarget();

				Calendar calendar = Calendar.getInstance();
				Date now = calendar.getTime();
				calendar.setTime(coupondtoOne.getExpirationDate());
				Date expirationDate = calendar.getTime();// 購入日付
				model.addAttribute("expirationDate", expirationDate);
				System.out.println("nw" + now);
				System.out.println("ex" + expirationDate);
				long d = (expirationDate.getTime() - now.getTime()) / (1000 * 60 * 60 * 24);// 購入日と現在の日付を比べる
				int count = (int) -d;
				System.out.println("count+++++++++++++++++++++++++++++++" + count);

				boolean countCheck = false;
				boolean priceCheck = false;

				if (allProductCount >= allCount) {// ユーザーの商品購入数と、クーポンの使用条件の商品購入数を比較
					countCheck = true;
				}
				if (allTotalPrice >= allPrice) {// ユーザーの全商品購入金額と、クーポンの使用条件の全商品購入金額を比較
					priceCheck = true;
				}

				coupondtoOne.setCouponCheck(false);
				if ((countCheck == true) && (priceCheck == true)) {// 商品購入数と全商品購入金額がクーポンの使用条件に達しているか比較
					coupondtoOne.setCouponCheck(true);
				}

				if (count < 0) {

					coupondtoListAdd.add(coupondtoOne);
				}
				System.out.println("couponCheck" + coupondtoOne.isCouponCheck());
				model.addAttribute("couponList", coupondtoListAdd);

			}

			model.addAttribute("purchaseCount", 0);
			model.addAttribute("allTotalPrice", 0);
		}
		return "shopping/productListLayout";
	}
	
	@GetMapping("/menberCouponList")
	public String getMenberCouponList(@ModelAttribute MenberCouponForm form, Model model) {
		model.addAttribute("contents", "shopping/menberCouponList::productListLayout_contents");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String user_id = auth.getName();
		// ログインユーザーのID取得
		int userId = usersService.select_id(user_id);

		int allProductCount = 0;
		int allTotalPrice = 0;
		int rankNumber = 0;

		List<PurchaseDTO> purchasedtoList = purchaseService.selectHistory(userId);// メソッドに入ったユーザーの購入情報を取得
		List<PurchaseDTO> allPurchaseList = new ArrayList<>();
		
		List<Integer> menberCouponUsedId = new ArrayList<>();//使用済みmenberCouponIDを格納
		PurchaseDTO customList;
		// 購入商品を一つづつ回して値を受け取る
		if (purchasedtoList.size() > 0) {
			for (int i = 0; purchasedtoList.size() > i; i++) {
				PurchaseDTO purchasedtoAdd = new PurchaseDTO();
				PurchaseDTO purchaseOne = purchasedtoList.get(i);
				purchasedtoAdd.setId(purchaseOne.getId());// カスタム情報取得に使用
				purchasedtoAdd.setCancelCheck(purchaseOne.getCancelCheck());
				purchasedtoAdd.setPrice(purchaseOne.getPrice());
				purchasedtoAdd.setProduct_count(purchaseOne.getProduct_count());
				purchasedtoAdd.setPurchaseCheck(purchaseOne.getPurchaseCheck());
				purchasedtoAdd.setCouponId(purchaseOne.getCouponId());// 購入者のクーポンID情報を取得
				purchasedtoAdd.setMenberCouponCheck(purchaseOne.getMenberCouponCheck());//クーポンを使用したかどうかを判別するための値(menberCoupon or coupon　or notCoupon)
				
				if(purchasedtoAdd.getMenberCouponCheck().equals("会員クーポン使用")) {
					int couponId = purchasedtoAdd.getCouponId();
					menberCouponUsedId.add(couponId);//menberCouponリスト
					
				}
			
				// 購入商品ごとのカスタム情報も取り出す
				int productId = purchasedtoAdd.getId();
				// int customId = purchasedtoAdd.getCustom_id();

				String nullCheck = "null";
				int getCustomId = customService.selectPurchaseCheck(userId, productId,
						purchasedtoAdd.getPurchaseCheck(), nullCheck);

				customList = customService.selectMany(getCustomId);// customテーブルのIDでカスタム情報を取得
				purchasedtoAdd.setCustomPrice(customList.getCustomPrice());
				purchasedtoAdd.setTotalPrice(
						purchaseOne.getProduct_count() * (customList.getCustomPrice() + purchaseOne.getPrice()));
				allTotalPrice = allTotalPrice + purchasedtoAdd.getTotalPrice();// ユーザーの購入した商品の合計金額を取得
				allProductCount = allProductCount + purchasedtoAdd.getProduct_count();// ユーザーの購入した商品の数を取得
				model.addAttribute("purchaseCount", allProductCount);
				model.addAttribute("allTotalPrice", allTotalPrice);

			}
			
			

			if ((allTotalPrice >= 0) && (allTotalPrice < 50000)) {
				rankNumber = 1;
			} else if ((allTotalPrice >= 50000) && (allTotalPrice < 100000)) {
				rankNumber = 2;
			} else if ((allTotalPrice >= 100000) && (allTotalPrice < 200000)) {
				rankNumber = 3;
			} else if ((allTotalPrice >= 200000) && (allTotalPrice < 400000)) {
				rankNumber = 4;
			} else if ((allTotalPrice >= 400000) && (allTotalPrice < 800000)) {
				rankNumber = 5;
			} else if ((allTotalPrice >= 800000) && (allTotalPrice < 1000000)) {
				rankNumber = 6;
			} else if ((allTotalPrice >= 1000000) && (allTotalPrice < 1500000)) {
				rankNumber = 7;
			} else if ((allTotalPrice >= 1500000) && (allTotalPrice < 3000000)) {
				rankNumber = 8;
			} else if ((allTotalPrice >= 3000000) && (allTotalPrice < 5000000)) {
				rankNumber = 9;
			} else if ((allTotalPrice >= 5000000) && (allTotalPrice < 8000000)) {
				rankNumber = 10;
			} else if ((allTotalPrice >= 8000000)) {
				rankNumber = 11;
			}
			
		} else {
			rankNumber = 1;//一度も購入していない場合はアマチュアランク
		}
		
		
	
	

			List<MenberCouponDTO> menbercoupondtoList = menberCouponService.selectMany();//ここですべて取ってきて自分のrankNumberより上のやつはDTO新規作成変数に違う値を入れる
			
			List<MenberCouponDTO> menbercoupondtoListNew = new ArrayList<>();
			MenberCouponDTO menbercoupondtoOne = new MenberCouponDTO();
			for(int i = 0; menbercoupondtoList.size() > i; i++) {
				 menbercoupondtoOne = menbercoupondtoList.get(i);
				int rankNumberCheck = menbercoupondtoOne.getCouponRank();
				if (rankNumberCheck == 1) {
					menbercoupondtoOne.setMenberRank("アマチュアランク");
					if(menbercoupondtoOne.getCouponRank() <= rankNumber) {
						menbercoupondtoOne.setCouponUseCheck("使用可能");
					}else {
						menbercoupondtoOne.setCouponUseCheck("使用不可");
					}
				} else if (rankNumberCheck == 2) {
					menbercoupondtoOne.setMenberRank("プロランク");
					if(menbercoupondtoOne.getCouponRank() <= rankNumber) {
						menbercoupondtoOne.setCouponUseCheck("使用可能");
					}else {
						menbercoupondtoOne.setCouponUseCheck("使用不可");
					}
				} else if (rankNumberCheck == 3) {
					menbercoupondtoOne.setMenberRank("ブロンズランク");
					if(menbercoupondtoOne.getCouponRank() <= rankNumber) {
						menbercoupondtoOne.setCouponUseCheck("使用可能");
					}else {
						menbercoupondtoOne.setCouponUseCheck("使用不可");
					}
				} else if (rankNumberCheck == 4) {
					menbercoupondtoOne.setMenberRank("シルバーランク");
					if(menbercoupondtoOne.getCouponRank() <= rankNumber) {
						menbercoupondtoOne.setCouponUseCheck("使用可能");
					}else {
						menbercoupondtoOne.setCouponUseCheck("使用不可");
					}
				} else if (rankNumberCheck == 5) {
					menbercoupondtoOne.setMenberRank("ゴールドランク");
					if(menbercoupondtoOne.getCouponRank() <= rankNumber) {
						menbercoupondtoOne.setCouponUseCheck("使用可能");
					}else {
						menbercoupondtoOne.setCouponUseCheck("使用不可");
					}
				} else if (rankNumberCheck == 6) {
					menbercoupondtoOne.setMenberRank("ダイヤモンドランク");
					if(menbercoupondtoOne.getCouponRank() <= rankNumber) {
						menbercoupondtoOne.setCouponUseCheck("使用可能");
					}else {
						menbercoupondtoOne.setCouponUseCheck("使用不可");
					}
				} else if (rankNumberCheck == 7) {
					menbercoupondtoOne.setMenberRank("プラチナランク");
					if(menbercoupondtoOne.getCouponRank() <= rankNumber) {
						menbercoupondtoOne.setCouponUseCheck("使用可能");
					}else {
						menbercoupondtoOne.setCouponUseCheck("使用不可");
					}
				} else if (rankNumberCheck == 8) {
					menbercoupondtoOne.setMenberRank("エイリアンランク");
					if(menbercoupondtoOne.getCouponRank() <= rankNumber) {
						menbercoupondtoOne.setCouponUseCheck("使用可能");
					}else {
						menbercoupondtoOne.setCouponUseCheck("使用不可");
					}
				} else if (rankNumberCheck == 9) {
					menbercoupondtoOne.setMenberRank("ゴッドフォックスランク");
					if(menbercoupondtoOne.getCouponRank() <= rankNumber) {
						menbercoupondtoOne.setCouponUseCheck("使用可能");
					}else {
						menbercoupondtoOne.setCouponUseCheck("使用不可");
					}
				} else if (rankNumberCheck == 10) {
					menbercoupondtoOne.setMenberRank("プレミアムゴッドランク");
					if(menbercoupondtoOne.getCouponRank() <= rankNumber) {
						menbercoupondtoOne.setCouponUseCheck("使用可能");
					}else {
						menbercoupondtoOne.setCouponUseCheck("使用不可");
					}
				} else if (rankNumberCheck == 11) {
					menbercoupondtoOne.setMenberRank("InductedIntoTheHalOfFameRank");
					if(menbercoupondtoOne.getCouponRank() <= rankNumber) {
						menbercoupondtoOne.setCouponUseCheck("使用可能");
					}else {
						menbercoupondtoOne.setCouponUseCheck("使用不可");
					}
				}
				menbercoupondtoListNew.add(menbercoupondtoOne);
				model.addAttribute("menbercoupondtoList",menbercoupondtoListNew);
			}
			
			if(menbercoupondtoList.size() == 0) {
				menbercoupondtoOne.setCouponUseCheck("使用不可");
				
			}
			
			
			// 一度使用したクーポンは表示されないようにする
			// 使用済みクーポンの一枚目のIDを使用して、使用前クーポンListを作成
			try {
				List<Integer> menberCouponId = menberCouponService.selectMenberCouponId();// 全クーポン
				System.out.println("全クーポン" + menberCouponId);
				System.out.println("使用済みクーポン" + menberCouponUsedId);
				List<Integer> beforeUseMenberCouponId = new ArrayList<>();// 使用前クーポン
				for (int i = 0; menberCouponId.size() > i; i++) {// 全クーポンの数文入る
					if (menberCouponId.get(i) != menberCouponUsedId.get(0)) {// 全クーポンと使用済みクーポンの最初に一枚を比べて、使用前クーポンならtrue
						beforeUseMenberCouponId.add(menberCouponId.get(i));// 使用済みクーポン一枚に対しての、使用前クーポンListを構築
					}
					System.out.println("beforeUseMenberCouponId" + beforeUseMenberCouponId);
				}

				// 上記で使用済みクーポン一枚に対しての、使用前クーポンListを構築したため下記に移る
				// 下記では残りの使用済みクーポンIDを上記で作成した使用前クーポンListと比べている

				if (menberCouponUsedId.size() >= 2) {// 使用済みクーポンが2枚以上あればtrue
					for (int x = 1; menberCouponUsedId.size() > x; x++) {// 上記のコードで使用した使用済みクーポン１枚を除いた使用済みクーポン文を繰り返す
						List<Integer> dummyBeforeUseMenberCouponId = new ArrayList<>();
						for (int i = 0; beforeUseMenberCouponId.size() > i; i++) {// 上記で構築した使用前クーポンの数文繰り返す
							if (beforeUseMenberCouponId.get(i) != menberCouponUsedId.get(x)) {
								dummyBeforeUseMenberCouponId.add(beforeUseMenberCouponId.get(i));
							}
						}
						beforeUseMenberCouponId = dummyBeforeUseMenberCouponId;
					}
					System.out.println("beforeUseMenberCouponId" + beforeUseMenberCouponId);
				}

				List<MenberCouponDTO> coupondtoList = new ArrayList<>();

				for (int i = 0; beforeUseMenberCouponId.size() > i; i++) {
					List<MenberCouponDTO> dummyCoupondtoList = new ArrayList<>();
					dummyCoupondtoList = menberCouponService.selectManyBeforeCoupon(beforeUseMenberCouponId.get(i));// couponテーブルからクーポン情報をすべて取得
					coupondtoList.addAll(dummyCoupondtoList);
					
				}
				System.out.println("coupondtoList"+coupondtoList);
				List<MenberCouponDTO> coupondtoListAdd = new ArrayList<>();

				for (int i = 0; coupondtoList.size() > i; i++) {// クーポン情報を一つづつ取り出す
					MenberCouponDTO coupondtoOne = coupondtoList.get(i);
					System.out.println("couponRanktest"+coupondtoOne.getCouponRank());
					int rankNumberCheck = coupondtoOne.getCouponRank();
					if (rankNumberCheck == 1) {
						coupondtoOne.setMenberRank("アマチュアランク");
						if(coupondtoOne.getCouponRank() <= rankNumber) {
							coupondtoOne.setCouponUseCheck("使用可能");
						}else {
							coupondtoOne.setCouponUseCheck("使用不可");
						}
					} else if (rankNumberCheck == 2) {
						coupondtoOne.setMenberRank("プロランク");
						if(coupondtoOne.getCouponRank() <= rankNumber) {
							coupondtoOne.setCouponUseCheck("使用可能");
						}else {
							coupondtoOne.setCouponUseCheck("使用不可");
						}
					} else if (rankNumberCheck == 3) {
						coupondtoOne.setMenberRank("ブロンズランク");
						if(coupondtoOne.getCouponRank() <= rankNumber) {
							coupondtoOne.setCouponUseCheck("使用可能");
						}else {
							coupondtoOne.setCouponUseCheck("使用不可");
						}
					} else if (rankNumberCheck == 4) {
						coupondtoOne.setMenberRank("シルバーランク");
						if(coupondtoOne.getCouponRank() <= rankNumber) {
							coupondtoOne.setCouponUseCheck("使用可能");
						}else {
							coupondtoOne.setCouponUseCheck("使用不可");
						}
					} else if (rankNumberCheck == 5) {
						coupondtoOne.setMenberRank("ゴールドランク");
						if(coupondtoOne.getCouponRank() <= rankNumber) {
							coupondtoOne.setCouponUseCheck("使用可能");
						}else {
							coupondtoOne.setCouponUseCheck("使用不可");
						}
					} else if (rankNumberCheck == 6) {
						coupondtoOne.setMenberRank("ダイヤモンドランク");
						if(coupondtoOne.getCouponRank() <= rankNumber) {
							coupondtoOne.setCouponUseCheck("使用可能");
						}else {
							coupondtoOne.setCouponUseCheck("使用不可");
						}
					} else if (rankNumberCheck == 7) {
						coupondtoOne.setMenberRank("プラチナランク");
						if(coupondtoOne.getCouponRank() <= rankNumber) {
							coupondtoOne.setCouponUseCheck("使用可能");
						}else {
							coupondtoOne.setCouponUseCheck("使用不可");
						}
					} else if (rankNumberCheck == 8) {
						coupondtoOne.setMenberRank("エイリアンランク");
						if(coupondtoOne.getCouponRank() <= rankNumber) {
							coupondtoOne.setCouponUseCheck("使用可能");
						}else {
							coupondtoOne.setCouponUseCheck("使用不可");
						}
					} else if (rankNumberCheck == 9) {
						coupondtoOne.setMenberRank("ゴッドフォックスランク");
						if(coupondtoOne.getCouponRank() <= rankNumber) {
							coupondtoOne.setCouponUseCheck("使用可能");
						}else {
							coupondtoOne.setCouponUseCheck("使用不可");
						}
					} else if (rankNumberCheck == 10) {
						coupondtoOne.setMenberRank("プレミアムゴッドランク");
						if(coupondtoOne.getCouponRank() <= rankNumber) {
							coupondtoOne.setCouponUseCheck("使用可能");
						}else {
							coupondtoOne.setCouponUseCheck("使用不可");
						}
					} else if (rankNumberCheck == 11) {
						coupondtoOne.setMenberRank("InductedIntoTheHalOfFameRank");
						if(coupondtoOne.getCouponRank() <= rankNumber) {
							coupondtoOne.setCouponUseCheck("使用可能");
						}else {
							coupondtoOne.setCouponUseCheck("使用不可");
						}
					}
					
					coupondtoListAdd.add(coupondtoOne);
					
					model.addAttribute("couponList",coupondtoListAdd);
				
				}
				if(coupondtoList.size() == 0) {
					model.addAttribute("notCoupon","yes");
					}
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();

				List<MenberCouponDTO> coupondtoListAdd = new ArrayList<>();
				List<MenberCouponDTO> coupondtoList = menberCouponService.selectMany();
				for (int i = 0; coupondtoList.size() > i; i++) {// クーポン情報を一つづつ取り出す
					MenberCouponDTO coupondtoOne = coupondtoList.get(i);
					
					int rankNumberCheck = coupondtoOne.getCouponRank();
					if (rankNumberCheck == 1) {
						coupondtoOne.setMenberRank("アマチュアランク");
						if(coupondtoOne.getCouponRank() <= rankNumber) {
							coupondtoOne.setCouponUseCheck("使用可能");
						}else {
							coupondtoOne.setCouponUseCheck("使用不可");
						}
					} else if (rankNumberCheck == 2) {
						coupondtoOne.setMenberRank("プロランク");
						if(coupondtoOne.getCouponRank() <= rankNumber) {
							coupondtoOne.setCouponUseCheck("使用可能");
						}else {
							coupondtoOne.setCouponUseCheck("使用不可");
						}
					} else if (rankNumberCheck == 3) {
						coupondtoOne.setMenberRank("ブロンズランク");
						if(coupondtoOne.getCouponRank() <= rankNumber) {
							coupondtoOne.setCouponUseCheck("使用可能");
						}else {
							coupondtoOne.setCouponUseCheck("使用不可");
						}
					} else if (rankNumberCheck == 4) {
						coupondtoOne.setMenberRank("シルバーランク");
						if(coupondtoOne.getCouponRank() <= rankNumber) {
							coupondtoOne.setCouponUseCheck("使用可能");
						}else {
							coupondtoOne.setCouponUseCheck("使用不可");
						}
					} else if (rankNumberCheck == 5) {
						coupondtoOne.setMenberRank("ゴールドランク");
						if(coupondtoOne.getCouponRank() <= rankNumber) {
							coupondtoOne.setCouponUseCheck("使用可能");
						}else {
							coupondtoOne.setCouponUseCheck("使用不可");
						}
					} else if (rankNumberCheck == 6) {
						coupondtoOne.setMenberRank("ダイヤモンドランク");
						if(coupondtoOne.getCouponRank() <= rankNumber) {
							coupondtoOne.setCouponUseCheck("使用可能");
						}else {
							coupondtoOne.setCouponUseCheck("使用不可");
						}
					} else if (rankNumberCheck == 7) {
						coupondtoOne.setMenberRank("プラチナランク");
						if(coupondtoOne.getCouponRank() <= rankNumber) {
							coupondtoOne.setCouponUseCheck("使用可能");
						}else {
							coupondtoOne.setCouponUseCheck("使用不可");
						}
					} else if (rankNumberCheck == 8) {
						coupondtoOne.setMenberRank("エイリアンランク");
						if(coupondtoOne.getCouponRank() <= rankNumber) {
							coupondtoOne.setCouponUseCheck("使用可能");
						}else {
							coupondtoOne.setCouponUseCheck("使用不可");
						}
					} else if (rankNumberCheck == 9) {
						coupondtoOne.setMenberRank("ゴッドフォックスランク");
						if(coupondtoOne.getCouponRank() <= rankNumber) {
							coupondtoOne.setCouponUseCheck("使用可能");
						}else {
							coupondtoOne.setCouponUseCheck("使用不可");
						}
					} else if (rankNumberCheck == 10) {
						coupondtoOne.setMenberRank("プレミアムゴッドランク");
						if(coupondtoOne.getCouponRank() <= rankNumber) {
							coupondtoOne.setCouponUseCheck("使用可能");
						}else {
							coupondtoOne.setCouponUseCheck("使用不可");
						}
					} else if (rankNumberCheck == 11) {
						coupondtoOne.setMenberRank("InductedIntoTheHalOfFameRank");
						if(coupondtoOne.getCouponRank() <= rankNumber) {
							coupondtoOne.setCouponUseCheck("使用可能");
						}else {
							coupondtoOne.setCouponUseCheck("使用不可");
						}
					}
					
					coupondtoListAdd.add(coupondtoOne);
					
					model.addAttribute("couponList",coupondtoListAdd);

				
			
				if(coupondtoList.size() == 0) {
					model.addAttribute("notCoupon","yes");
					}

				}
			
			}
			
			
			return "shopping/productListLayout";
			
			
		

	}

	@GetMapping("/couponSee")
	public String getCouponSee(@ModelAttribute CouponForm form, Model model) {
		model.addAttribute("contents", "shopping/couponSee::productListLayout_contents");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String getName = auth.getName();
		int userId = usersService.select_id(getName);// メソッドに入ったユーザーのIDを取得

		int allProductCount = 0;
		int allTotalPrice = 0;
		List<PurchaseDTO> purchasedtoList = purchaseService.selectHistory(userId);// メソッドに入ったユーザーの購入情報を取得
		List<PurchaseDTO> allPurchaseList = new ArrayList<>();
		List<Integer> couponUsedId = new ArrayList<>();// 使用済みクーポンIDを格納
		List<Integer> menberCouponUsedId = new ArrayList<>();// 使用済みmenberCouponIDを格納
		PurchaseDTO customList;
		// 購入商品を一つづつ回して値を受け取る
		for (int i = 0; purchasedtoList.size() > i; i++) {
			PurchaseDTO purchasedtoAdd = new PurchaseDTO();
			PurchaseDTO purchaseOne = purchasedtoList.get(i);
			purchasedtoAdd.setId(purchaseOne.getId());// カスタム情報取得に使用
			purchasedtoAdd.setCancelCheck(purchaseOne.getCancelCheck());
			purchasedtoAdd.setPrice(purchaseOne.getPrice());
			purchasedtoAdd.setProduct_count(purchaseOne.getProduct_count());
			purchasedtoAdd.setPurchaseCheck(purchaseOne.getPurchaseCheck());
			purchasedtoAdd.setCouponId(purchaseOne.getCouponId());// 購入者のクーポンID情報を取得
			purchasedtoAdd.setMenberCouponCheck(purchaseOne.getMenberCouponCheck());// クーポンを使用したかどうかを判別するための値(menberCoupon
																					// or coupon or notCoupon)

			if (purchasedtoAdd.getMenberCouponCheck().equals("会員クーポン使用")) {
				int couponId = purchasedtoAdd.getCouponId();
				menberCouponUsedId.add(couponId);// menberCouponリスト
			} else {

				if (purchasedtoAdd.getCouponId() > 0) {// クーポンを使用して購入していればtrue
					int couponId = purchasedtoAdd.getCouponId();
					couponUsedId.add(couponId);// couponリスト
				} else {
					couponUsedId.add(-1);
				}
			}
			int productId = purchasedtoAdd.getId();
			// 購入商品ごとのカスタム情報も取り出す
			String nullCheck = "null";
			int getCustomId = customService.selectPurchaseCheck(userId, productId, purchasedtoAdd.getPurchaseCheck(),
					nullCheck);

			customList = customService.selectMany(getCustomId);// customテーブルのIDでカスタム情報を取得
			purchasedtoAdd.setCustomPrice(customList.getCustomPrice());
			purchasedtoAdd.setTotalPrice(
					purchaseOne.getProduct_count() * (customList.getCustomPrice() + purchaseOne.getPrice()));
			allTotalPrice = allTotalPrice + purchasedtoAdd.getTotalPrice();// ユーザーの購入した商品の合計金額を取得
			allProductCount = allProductCount + purchasedtoAdd.getProduct_count();// ユーザーの購入した商品の数を取得
			model.addAttribute("purchaseCount", allProductCount);
			model.addAttribute("allTotalPrice", allTotalPrice);
			allPurchaseList.add(purchasedtoAdd);
		}

		// 一度使用したクーポンは表示されないようにする
		// 使用済みクーポンの一枚目のIDを使用して、使用前クーポンListを作成
		try {
			List<Integer> couponId = couponService.selectIdMany();// 全クーポン
			System.out.println("全クーポン" + couponId);
			System.out.println("使用済みクーポン" + couponUsedId);
			List<Integer> beforeUseCouponId = new ArrayList<>();// 使用前クーポン
			for (int i = 0; couponId.size() > i; i++) {// 全クーポンの数文入る
				if (couponId.get(i) != couponUsedId.get(0)) {// 全クーポンと使用済みクーポンの最初に一枚を比べて、使用前クーポンならtrue
					beforeUseCouponId.add(couponId.get(i));// 使用済みクーポン一枚に対しての、使用前クーポンListを構築
				}
				System.out.println("beforeUseCouponId" + beforeUseCouponId);
			}

			// 上記で使用済みクーポン一枚に対しての、使用前クーポンListを構築したため下記に移る
			// 下記では残りの使用済みクーポンIDを上記で作成した使用前クーポンListと比べている

			if (couponUsedId.size() >= 2) {// 使用済みクーポンが2枚以上あればtrue
				for (int x = 1; couponUsedId.size() > x; x++) {// 上記のコードで使用した使用済みクーポン１枚を除いた使用済みクーポン文を繰り返す
					List<Integer> dummyBeforeUseCouponId = new ArrayList<>();
					for (int i = 0; beforeUseCouponId.size() > i; i++) {// 上記で構築した使用前クーポンの数文繰り返す
						if (beforeUseCouponId.get(i) != couponUsedId.get(x)) {
							dummyBeforeUseCouponId.add(beforeUseCouponId.get(i));
						}
					}
					beforeUseCouponId = dummyBeforeUseCouponId;
				}
				System.out.println("beforeUseCouponId" + beforeUseCouponId);
			}

			List<CouponDTO> coupondtoList = new ArrayList<>();

			for (int i = 0; beforeUseCouponId.size() > i; i++) {
				List<CouponDTO> dummyCoupondtoList = new ArrayList<>();
				dummyCoupondtoList = couponService.selectMany(beforeUseCouponId.get(i));// couponテーブルからクーポン情報をすべて取得
				coupondtoList.addAll(dummyCoupondtoList);
			}
			List<CouponDTO> coupondtoListAdd = new ArrayList<>();

			for (int i = 0; coupondtoList.size() > i; i++) {// クーポン情報を一つづつ取り出す
				CouponDTO coupondtoOne = coupondtoList.get(i);
				int allCount = coupondtoOne.getPurchaseCountTarget();
				int allPrice = coupondtoOne.getPurchaseTotalPriceTarget();

				Calendar calendar = Calendar.getInstance();
				Date now = calendar.getTime();
				calendar.setTime(coupondtoOne.getExpirationDate());
				Date expirationDate = calendar.getTime();// 購入日付
				model.addAttribute("expirationDate", expirationDate);
				System.out.println("nw" + now);
				System.out.println("ex" + expirationDate);
				long d = (expirationDate.getTime() - now.getTime()) / (1000 * 60 * 60 * 24);// 購入日と現在の日付を比べる
				int count = (int) -d;
				System.out.println("count+++++++++++++++++++++++++++++++" + count);

				boolean countCheck = false;
				boolean priceCheck = false;

				if (allProductCount >= allCount) {// ユーザーの商品購入数と、クーポンの使用条件の商品購入数を比較
					countCheck = true;
				}
				if (allTotalPrice >= allPrice) {// ユーザーの全商品購入金額と、クーポンの使用条件の全商品購入金額を比較
					priceCheck = true;
				}

				coupondtoOne.setCouponCheck(false);
				if ((countCheck == true) && (priceCheck == true)) {// 商品購入数と全商品購入金額がクーポンの使用条件に達しているか比較
					coupondtoOne.setCouponCheck(true);
				}

				if (coupondtoOne.isCouponCheck() == true) {// 商品購入数と全商品購入金額がクーポンの使用条件に達しているもののみList追加
					if (count < 0) {
						coupondtoListAdd.add(coupondtoOne);
					}
					System.out.println("couponCheck" + coupondtoOne.isCouponCheck());

				}
			}

			model.addAttribute("couponList", coupondtoListAdd);
			if (coupondtoListAdd.size() == 0) {
				model.addAttribute("notCoupon", "yes");
			}
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();

			List<CouponDTO> coupondtoListAdd = new ArrayList<>();
			List<CouponDTO> coupondtoList = couponService.beforePurchaseSelectMany();
			for (int i = 0; coupondtoList.size() > i; i++) {// クーポン情報を一つづつ取り出す
				CouponDTO coupondtoOne = coupondtoList.get(i);
				int allCount = coupondtoOne.getPurchaseCountTarget();
				int allPrice = coupondtoOne.getPurchaseTotalPriceTarget();

				boolean countCheck = false;
				boolean priceCheck = false;

				if (allProductCount >= allCount) {// ユーザーの商品購入数と、クーポンの使用条件の商品購入数を比較
					countCheck = true;
				}
				if (allTotalPrice >= allPrice) {// ユーザーの全商品購入金額と、クーポンの使用条件の全商品購入金額を比較
					priceCheck = true;
				}

				coupondtoOne.setCouponCheck(false);
				if ((countCheck == true) && (priceCheck == true)) {// 商品購入数と全商品購入金額がクーポンの使用条件に達しているか比較
					coupondtoOne.setCouponCheck(true);
				}
				model.addAttribute("notCoupon", "yes");
				if (coupondtoOne.isCouponCheck() == true) {
					Calendar calendar = Calendar.getInstance();
					Date now = calendar.getTime();
					calendar.setTime(coupondtoOne.getExpirationDate());
					Date expirationDate = calendar.getTime();// 購入日付
					model.addAttribute("expirationDate", expirationDate);
					System.out.println("nw" + now);
					System.out.println("ex" + expirationDate);
					long d = (expirationDate.getTime() - now.getTime()) / (1000 * 60 * 60 * 24);// 購入日と現在の日付を比べる
					int count = (int) -d;
					System.out.println("count+++++++++++++++++++++++++++++++" + count);
					if (count < 0) {
						coupondtoListAdd.add(coupondtoOne);
					}
					model.addAttribute("notCoupon", "no");
				}

				System.out.println("couponCheck" + coupondtoOne.isCouponCheck());
				model.addAttribute("couponList", coupondtoListAdd);
			}
			model.addAttribute("purchaseCount", 0);
			model.addAttribute("allTotalPrice", 0);

		}
		return "shopping/productListLayout";
	}

	@GetMapping("/menberCouponSee")
	public String getMenberCouponSee(@ModelAttribute MenberCouponForm form, Model model) {
		model.addAttribute("contents", "shopping/menberCouponSee::productListLayout_contents");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String user_id = auth.getName();
		// ログインユーザーのID取得
		int userId = usersService.select_id(user_id);

		int allProductCount = 0;
		int allTotalPrice = 0;
		int rankNumber = 0;

		List<PurchaseDTO> purchasedtoList = purchaseService.selectHistory(userId);// メソッドに入ったユーザーの購入情報を取得
		List<PurchaseDTO> allPurchaseList = new ArrayList<>();

		List<Integer> menberCouponUsedId = new ArrayList<>();// 使用済みmenberCouponIDを格納
		PurchaseDTO customList;
		// 購入商品を一つづつ回して値を受け取る
		if (purchasedtoList.size() > 0) {
			for (int i = 0; purchasedtoList.size() > i; i++) {
				PurchaseDTO purchasedtoAdd = new PurchaseDTO();
				PurchaseDTO purchaseOne = purchasedtoList.get(i);
				purchasedtoAdd.setId(purchaseOne.getId());// カスタム情報取得に使用
				purchasedtoAdd.setCancelCheck(purchaseOne.getCancelCheck());
				purchasedtoAdd.setPrice(purchaseOne.getPrice());
				purchasedtoAdd.setProduct_count(purchaseOne.getProduct_count());
				purchasedtoAdd.setPurchaseCheck(purchaseOne.getPurchaseCheck());
				purchasedtoAdd.setCouponId(purchaseOne.getCouponId());// 購入者のクーポンID情報を取得
				purchasedtoAdd.setMenberCouponCheck(purchaseOne.getMenberCouponCheck());// クーポンを使用したかどうかを判別するための値(menberCoupon
																						// or coupon or notCoupon)

				if (purchasedtoAdd.getMenberCouponCheck().equals("会員クーポン使用")) {
					int couponId = purchasedtoAdd.getCouponId();
					menberCouponUsedId.add(couponId);// menberCouponリスト

				}
				System.out.println("test2222"+menberCouponUsedId);

				// 購入商品ごとのカスタム情報も取り出す
				int productId = purchasedtoAdd.getId();
				// int customId = purchasedtoAdd.getCustom_id();

				String nullCheck = "null";
				int getCustomId = customService.selectPurchaseCheck(userId, productId,
						purchasedtoAdd.getPurchaseCheck(), nullCheck);

				customList = customService.selectMany(getCustomId);// customテーブルのIDでカスタム情報を取得
				purchasedtoAdd.setCustomPrice(customList.getCustomPrice());
				purchasedtoAdd.setTotalPrice(
						purchaseOne.getProduct_count() * (customList.getCustomPrice() + purchaseOne.getPrice()));
				allTotalPrice = allTotalPrice + purchasedtoAdd.getTotalPrice();// ユーザーの購入した商品の合計金額を取得
				allProductCount = allProductCount + purchasedtoAdd.getProduct_count();// ユーザーの購入した商品の数を取得
				model.addAttribute("purchaseCount", allProductCount);
				model.addAttribute("allTotalPrice", allTotalPrice);

			}

			if ((allTotalPrice > 0) && (allTotalPrice < 50000)) {
				rankNumber = 1;
			} else if ((allTotalPrice >= 50000) && (allTotalPrice < 100000)) {
				rankNumber = 2;
			} else if ((allTotalPrice >= 100000) && (allTotalPrice < 200000)) {
				rankNumber = 3;
			} else if ((allTotalPrice >= 200000) && (allTotalPrice < 400000)) {
				rankNumber = 4;
			} else if ((allTotalPrice >= 400000) && (allTotalPrice < 800000)) {
				rankNumber = 5;
			} else if ((allTotalPrice >= 800000) && (allTotalPrice < 1000000)) {
				rankNumber = 6;
			} else if ((allTotalPrice >= 1000000) && (allTotalPrice < 1500000)) {
				rankNumber = 7;
			} else if ((allTotalPrice >= 1500000) && (allTotalPrice < 3000000)) {
				rankNumber = 8;
			} else if ((allTotalPrice >= 3000000) && (allTotalPrice < 5000000)) {
				rankNumber = 9;
			} else if ((allTotalPrice >= 5000000) && (allTotalPrice < 8000000)) {
				rankNumber = 10;
			} else if ((allTotalPrice >= 8000000)) {
				rankNumber = 11;
			}

		} else {
			rankNumber = 1;// 一度も購入していない場合はアマチュアランク
		}

		List<MenberCouponDTO> menbercoupondtoList = menberCouponService.selectMany(rankNumber);

		List<MenberCouponDTO> menbercoupondtoListNew = new ArrayList<>();
		for (int i = 0; menbercoupondtoList.size() > i; i++) {
			MenberCouponDTO menbercoupondtoOne = menbercoupondtoList.get(i);
			int rankNumberCheck = menbercoupondtoOne.getCouponRank();
			if (rankNumberCheck == 1) {
				menbercoupondtoOne.setMenberRank("アマチュアランク");
			} else if (rankNumberCheck == 2) {
				menbercoupondtoOne.setMenberRank("プロランク");
			} else if (rankNumberCheck == 3) {
				menbercoupondtoOne.setMenberRank("ブロンズランク");
			} else if (rankNumberCheck == 4) {
				menbercoupondtoOne.setMenberRank("シルバーランク");
			} else if (rankNumberCheck == 5) {
				menbercoupondtoOne.setMenberRank("ゴールドランク");
			} else if (rankNumberCheck == 6) {
				menbercoupondtoOne.setMenberRank("ダイヤモンドランク");
			} else if (rankNumberCheck == 7) {
				menbercoupondtoOne.setMenberRank("プラチナランク");
			} else if (rankNumberCheck == 8) {
				menbercoupondtoOne.setMenberRank("エイリアンランク");
			} else if (rankNumberCheck == 9) {
				menbercoupondtoOne.setMenberRank("ゴッドフォックスランク");
			} else if (rankNumberCheck == 10) {
				menbercoupondtoOne.setMenberRank("プレミアムゴッドランク");
			} else if (rankNumberCheck == 11) {
				menbercoupondtoOne.setMenberRank("InductedIntoTheHalOfFameRank");
			}
			menbercoupondtoListNew.add(menbercoupondtoOne);
			model.addAttribute("menbercoupondtoList", menbercoupondtoListNew);
		}

		// 一度使用したクーポンは表示されないようにする
		// 使用済みクーポンの一枚目のIDを使用して、使用前クーポンListを作成
		try {
			List<Integer> menberCouponId = menberCouponService.selectMenberCouponId(rankNumber);// 全クーポン
			System.out.println("全クーポン" + menberCouponId);
			System.out.println("使用済みクーポン" + menberCouponUsedId);
			List<Integer> beforeUseMenberCouponId = new ArrayList<>();// 使用前クーポン
			for (int i = 0; menberCouponId.size() > i; i++) {// 全クーポンの数文入る
				if (menberCouponId.get(i) != menberCouponUsedId.get(0)) {// 全クーポンと使用済みクーポンの最初に一枚を比べて、使用前クーポンならtrue
					beforeUseMenberCouponId.add(menberCouponId.get(i));// 使用済みクーポン一枚に対しての、使用前クーポンListを構築
				}
				System.out.println("beforeUseMenberCouponId" + beforeUseMenberCouponId);
			}

			// 上記で使用済みクーポン一枚に対しての、使用前クーポンListを構築したため下記に移る
			// 下記では残りの使用済みクーポンIDを上記で作成した使用前クーポンListと比べている

			if (menberCouponUsedId.size() >= 2) {// 使用済みクーポンが2枚以上あればtrue
				for (int x = 1; menberCouponUsedId.size() > x; x++) {// 上記のコードで使用した使用済みクーポン１枚を除いた使用済みクーポン文を繰り返す
					List<Integer> dummyBeforeUseMenberCouponId = new ArrayList<>();
					for (int i = 0; beforeUseMenberCouponId.size() > i; i++) {// 上記で構築した使用前クーポンの数文繰り返す
						if (beforeUseMenberCouponId.get(i) != menberCouponUsedId.get(x)) {
							dummyBeforeUseMenberCouponId.add(beforeUseMenberCouponId.get(i));
						}
					}
					beforeUseMenberCouponId = dummyBeforeUseMenberCouponId;
				}
				System.out.println("beforeUseMenberCouponId" + beforeUseMenberCouponId);
			}

			List<MenberCouponDTO> coupondtoList = new ArrayList<>();

			for (int i = 0; beforeUseMenberCouponId.size() > i; i++) {
				MenberCouponDTO dummyCoupondtoList = new MenberCouponDTO();
				dummyCoupondtoList = menberCouponService.selectManyBeforeMenberCoupon(beforeUseMenberCouponId.get(i));// couponテーブルからクーポン情報をすべて取得
				try {
				coupondtoList.add(dummyCoupondtoList);
				

			}catch(NullPointerException e) {
				e.printStackTrace();
				System.out.println("tigauchatch");
			}
			}
			System.out.println("coupondtoList" + coupondtoList);
			List<MenberCouponDTO> coupondtoListAdd = new ArrayList<>();

			for (int i = 0; coupondtoList.size() > i; i++) {// クーポン情報を一つづつ取り出す
				MenberCouponDTO coupondtoOne = coupondtoList.get(i);

				int rankNumberCheck = coupondtoOne.getCouponRank();
				if (rankNumberCheck == 1) {
					coupondtoOne.setMenberRank("アマチュアランク");
				} else if (rankNumberCheck == 2) {
					coupondtoOne.setMenberRank("プロランク");
				} else if (rankNumberCheck == 3) {
					coupondtoOne.setMenberRank("ブロンズランク");
				} else if (rankNumberCheck == 4) {
					coupondtoOne.setMenberRank("シルバーランク");
				} else if (rankNumberCheck == 5) {
					coupondtoOne.setMenberRank("ゴールドランク");
				} else if (rankNumberCheck == 6) {
					coupondtoOne.setMenberRank("ダイヤモンドランク");
				} else if (rankNumberCheck == 7) {
					coupondtoOne.setMenberRank("プラチナランク");
				} else if (rankNumberCheck == 8) {
					coupondtoOne.setMenberRank("エイリアンランク");
				} else if (rankNumberCheck == 9) {
					coupondtoOne.setMenberRank("ゴッドフォックスランク");
				} else if (rankNumberCheck == 10) {
					coupondtoOne.setMenberRank("プレミアムゴッドランク");
				} else if (rankNumberCheck == 11) {
					coupondtoOne.setMenberRank("InductedIntoTheHalOfFameRank");
				}

				coupondtoListAdd.add(coupondtoOne);

				model.addAttribute("couponList", coupondtoListAdd);
				System.out.println("dkdkdkdkdkdkdk322");
			}
			if (coupondtoList.size() == 0) {
				model.addAttribute("notCoupon", "yes");
			}
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("dkdkdkdkdkdkdkdkdkdkdkdk");
			List<MenberCouponDTO> coupondtoListAdd = new ArrayList<>();
			List<MenberCouponDTO> coupondtoList = menberCouponService.selectRankNumberCheckMany(rankNumber);
			for (int i = 0; coupondtoList.size() > i; i++) {// クーポン情報を一つづつ取り出す
				MenberCouponDTO coupondtoOne = coupondtoList.get(i);

				int rankNumberCheck = coupondtoOne.getCouponRank();
				if (rankNumberCheck == 1) {
					coupondtoOne.setMenberRank("アマチュアランク");
				} else if (rankNumberCheck == 2) {
					coupondtoOne.setMenberRank("プロランク");
				} else if (rankNumberCheck == 3) {
					coupondtoOne.setMenberRank("ブロンズランク");
				} else if (rankNumberCheck == 4) {
					coupondtoOne.setMenberRank("シルバーランク");
				} else if (rankNumberCheck == 5) {
					coupondtoOne.setMenberRank("ゴールドランク");
				} else if (rankNumberCheck == 6) {
					coupondtoOne.setMenberRank("ダイヤモンドランク");
				} else if (rankNumberCheck == 7) {
					coupondtoOne.setMenberRank("プラチナランク");
				} else if (rankNumberCheck == 8) {
					coupondtoOne.setMenberRank("エイリアンランク");
				} else if (rankNumberCheck == 9) {
					coupondtoOne.setMenberRank("ゴッドフォックスランク");
				} else if (rankNumberCheck == 10) {
					coupondtoOne.setMenberRank("プレミアムゴッドランク");
				} else if (rankNumberCheck == 11) {
					coupondtoOne.setMenberRank("InductedIntoTheHalOfFameRank");
				}

				coupondtoListAdd.add(coupondtoOne);

				model.addAttribute("couponList", coupondtoListAdd);

				if (coupondtoList.size() == 0) {
					model.addAttribute("notCoupon", "yes");
				}

			}
		}

		return "shopping/productListLayout";

	}

	@GetMapping("/couponCancel")
	public String getCouponCancel(@ModelAttribute CouponForm form, RedirectAttributes redirectattributes, Model model) {

		CartForm cartForm = new CartForm();
		return "redirect:/cart";
	}

	@GetMapping("/couponUse/{couponId}/{productId}")
	public String getCouponUse(@ModelAttribute CouponForm form, CartForm cartform,
			@PathVariable("couponId") int couponId, @PathVariable("productId") int productId, Model model) {
		model.addAttribute("contents", "shopping/cart::productListLayout_contents");
		System.out.println("coupon入った");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String getUserId = auth.getName();

		int cartId = cartService.selectMaxId(productId);// cartテーブルからクーポンを使う商品IDを取得
		cartService.updateCouponId(cartId, couponId);// 商品IDでselectし、クーポン情報を加える

		List<PcDataDTO> cartList = cartService.selectMany(getUserId);// ログインユーザーのみのカートの情報を取得
		int totalPriceAll = 0;// カート全体価格
		int totalPriceOne = 0;// 各商品価格
		int disCountPrice = 0;// 割引数
		if (cartList == null || cartList.size() == 0) {// カートが0の場合
			model.addAttribute("totalPrice", 0);
			
		} else {
			System.out.println("ok4");
			for (int i = 0; i < cartList.size(); i++) {
				PcDataDTO pcdatadto = cartList.get(i);

				totalPriceAll = totalPriceAll
						+ pcdatadto.getProduct_count() * (pcdatadto.getPrice() + pcdatadto.getCustomPrice());// 購入数+商品金額+カスタム金額

				totalPriceOne = totalPriceOne
						+ pcdatadto.getProduct_count() * (pcdatadto.getPrice() + pcdatadto.getCustomPrice());

				disCountPrice = pcdatadto.getProduct_count() * (pcdatadto.getPrice() + pcdatadto.getCustomPrice());

				if (pcdatadto.getCouponId() >= 1) {// 商品にクーポンが適用されていればtrue
					System.out.println("ok5");
					CouponDTO coupondto = couponService.selectOne(couponId);
					int disCount = coupondto.getDiscount();// 割引率(%)
					System.out.println("disCount" + disCount);
					double disCountNew = 0;
					if (disCount < 10) {
						System.out.println("ok6");
						disCountNew = Double.valueOf("0.0" + disCount);

						double disCountPriceNew = disCountPrice * disCountNew;// 割引価格
						int disCountPriceNewNext = (int) disCountPriceNew;
						pcdatadto.setDisCountPriceNew(disCountPriceNewNext);
						totalPriceOne = (int) (totalPriceOne - disCountPriceNew);
					} else {
						disCountNew = Double.valueOf("0." + disCount);

						double disCountPriceNew = disCountPrice * disCountNew;// 割引価格
						int disCountPriceNewNext = (int) disCountPriceNew;
						pcdatadto.setDisCountPriceNew(disCountPriceNewNext);
						totalPriceOne = (int) (totalPriceOne - disCountPriceNew);
					}
				}
				model.addAttribute("totalPrice", totalPriceAll);
				model.addAttribute("couponAfterPrice", totalPriceOne);
			}
		}

		model.addAttribute("cartList", cartList);
		// model.addAttribute("couponUseCheck","couponUse");//クーポンチェック文字をhtmlにもっていく
		model.addAttribute("couponId", couponId);// 使用するクーポンIDをhtmlに持っていく

		return "shopping/productListLayout";
	}

	@GetMapping("/menberCouponUse/{couponId}/{productId}")
	public String getMenberCouponUse(@ModelAttribute MenberCouponForm form, CartForm cartform,
			@PathVariable("couponId") int couponId, @PathVariable("productId") int productId, Model model) {
		model.addAttribute("contents", "shopping/cart::productListLayout_contents");
		System.out.println("menberCouponUse到達");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String getUserId = auth.getName();

		System.out.println("couponIdCheck"+couponId);
		int cartId = cartService.selectMaxId(productId);// cartテーブルからクーポンを使う商品IDを取得
		System.out.println("test1"+cartId);
		cartService.updateMenberCouponId(cartId, couponId);// 商品IDでselectし、クーポン情報を加える

		List<PcDataDTO> cartList = cartService.selectMany(getUserId);// ログインユーザーのみのカートの情報を取得
		System.out.println("cartList" + cartList);
		int totalPriceAll = 0;// カート全体価格
		int totalPriceOne = 0;// 各商品価格
		int disCountPrice = 0;// 割引数
		if (cartList == null || cartList.size() == 0) {// カートが0の場合
			model.addAttribute("totalPrice", 0);
		} else {

			for (int i = 0; i < cartList.size(); i++) {
				PcDataDTO pcdatadto = cartList.get(i);

				totalPriceAll = totalPriceAll
						+ pcdatadto.getProduct_count() * (pcdatadto.getPrice() + pcdatadto.getCustomPrice());// 購入数+商品金額+カスタム金額

				totalPriceOne = totalPriceOne
						+ pcdatadto.getProduct_count() * (pcdatadto.getPrice() + pcdatadto.getCustomPrice());

				disCountPrice = pcdatadto.getProduct_count() * (pcdatadto.getPrice() + pcdatadto.getCustomPrice());

				if (pcdatadto.getMenberCouponCheck().equals("会員ランク特典使用")) {
					System.out.println("会員ランク特典使用");
					MenberCouponDTO menbercoupondto = menberCouponService.selectOne(couponId);
					int menberCouponDisCount = menbercoupondto.getDiscount();
					double disCountNew = 0;
					if (menberCouponDisCount < 10) {
						System.out.println("test");
						disCountNew = Double.valueOf("0.0" + menberCouponDisCount);

						double disCountPriceNew = disCountPrice * disCountNew;// 割引価格
						int disCountPriceNewNext = (int) disCountPriceNew;
						pcdatadto.setDisCountPriceNew(disCountPriceNewNext);
						totalPriceOne = (int) (totalPriceOne - disCountPriceNew);
					} else {
						disCountNew = Double.valueOf("0." + menberCouponDisCount);

						double disCountPriceNew = disCountPrice * disCountNew;// 割引価格
						int disCountPriceNewNext = (int) disCountPriceNew;
						pcdatadto.setDisCountPriceNew(disCountPriceNewNext);
						totalPriceOne = (int) (totalPriceOne - disCountPriceNew);
					}

				} else {
					System.out.println("会員ランク特典不使用");
					if (pcdatadto.getCouponId() >= 1) {// 商品にクーポンが適用されていればtrue
						System.out.println("test1111");
						CouponDTO coupondto = couponService.selectOne(couponId);
						int disCount = coupondto.getDiscount();// 割引率(%)
						System.out.println("disCount" + disCount);
						double disCountNew = 0;
						if (disCount < 10) {
							disCountNew = Double.valueOf("0.0" + disCount);

							double disCountPriceNew = disCountPrice * disCountNew;// 割引価格
							int disCountPriceNewNext = (int) disCountPriceNew;
							pcdatadto.setDisCountPriceNew(disCountPriceNewNext);
							totalPriceOne = (int) (totalPriceOne - disCountPriceNew);
						} else {
							disCountNew = Double.valueOf("0." + disCount);

							double disCountPriceNew = disCountPrice * disCountNew;// 割引価格
							int disCountPriceNewNext = (int) disCountPriceNew;
							pcdatadto.setDisCountPriceNew(disCountPriceNewNext);
							totalPriceOne = (int) (totalPriceOne - disCountPriceNew);
						}
					}
				}
				model.addAttribute("totalPrice", totalPriceAll);
				model.addAttribute("couponAfterPrice", totalPriceOne);
			}
		}

		model.addAttribute("cartList", cartList);
		// model.addAttribute("couponUseCheck","couponUse");//クーポンチェック文字をhtmlにもっていく
		model.addAttribute("couponId", couponId);// 使用するクーポンIDをhtmlに持っていく

		return "shopping/productListLayout";
	}

	@GetMapping("couponProductChoose/{id}")
	public String getCouponProductChoose(@ModelAttribute CouponForm form, @PathVariable("id") int couponId,
			Model model) {
		model.addAttribute("contents", "shopping/couponProductChoose::productListLayout_contents");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String user_id = auth.getName();
		// ログインユーザーのみのカートの情報を取得
		List<PcDataDTO> cartList = cartService.selectMany(user_id);
		System.out.println("cartList" + cartList);

		model.addAttribute("cartList", cartList);
		model.addAttribute("couponId", couponId);

		return "shopping/productListLayout";

	}

	@GetMapping("menberCouponProductChoose/{id}")
	public String getMenberCouponProductChoose(@ModelAttribute MenberCouponForm form,
			@PathVariable("id") int menberCouponId, Model model) {
		model.addAttribute("contents", "shopping/menberCouponProductChoose::productListLayout_contents");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String user_id = auth.getName();
		// ログインユーザーのみのカートの情報を取得
		List<PcDataDTO> cartList = cartService.selectMany(user_id);
		System.out.println("cartList" + cartList);

		model.addAttribute("cartList", cartList);
		model.addAttribute("couponId", menberCouponId);

		return "shopping/productListLayout";

	}

	@GetMapping("couponAdd")
	public String getCouponAdd(@ModelAttribute CouponForm form, Model model) {
		model.addAttribute("contents", "shopping/couponAdd::productListLayout_contents");

		return "shopping/productListLayout";
	}

	@PostMapping(value = "couponAdd", params = "distribution")
	public String postCouponAdd(@ModelAttribute @Validated(GroupOrder.class) CouponForm form,
			BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response, Model model) {

		if (bindingResult.hasErrors()) {
			return getCouponAdd(form, model);
		}

		HttpSession session = request.getSession();// 入力されたクーポン情報をsession保存
		session.setAttribute("title", form.getTitle());
		session.setAttribute("discount", form.getDiscount());
		session.setAttribute("purchaseCountTarget", form.getPurchaseCountTarget());
		session.setAttribute("purchaseTotalPriceTarget", form.getPurchaseTotalPriceTarget());
		session.setAttribute("expirationDate", form.getExpirationDate());

		CouponDTO coupondto = new CouponDTO();

		couponService.couponInsert(coupondto, session);// couponテーブルにsessionに保存したデータを格納

		return "redirect:/couponList";
	}

	@GetMapping("menberCouponAdd")
	public String getMenberCouponAdd(@ModelAttribute MenberCouponForm form, Model model) {
		model.addAttribute("contents", "shopping/menberCouponAdd::productListLayout_contents");

		return "shopping/productListLayout";
	}

	@PostMapping("/menberCouponAdd")
	public String postMenberCouponAdd(@ModelAttribute @Validated(GroupOrder.class) MenberCouponForm form,
			BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response, Model model) {

		if (bindingResult.hasErrors()) {
			return getMenberCouponAdd(form, model);
		}

		HttpSession session = request.getSession();// 入力されたクーポン情報をsession保存
		session.setAttribute("title", form.getTitle());
		session.setAttribute("discount", form.getDiscount());
		session.setAttribute("purchaseCountTarget", 0);
		session.setAttribute("purchaseTotalPriceTarget", 0);
		session.setAttribute("couponRank", form.getCouponRank());

		MenberCouponDTO menbercoupondto = new MenberCouponDTO();

		menberCouponService.menberCouponInsert(menbercoupondto, session);// couponテーブルにsessionに保存したデータを格納

		return "redirect:/couponList";
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
		session.setAttribute("sessionGetRole", headerName.getRole());
		System.out.println("getUser_name" + headerName.getUser_name());

		System.out.println("productList" + productList);

		model.addAttribute("productList", productList);

		int allProductCount = 0;
		int allTotalPrice = 0;
		String user_id = auth.getName();
		// ログインユーザーのID取得
		int selectId = usersService.select_id(user_id);
		List<PurchaseDTO> purchasedtoList = purchaseService.selectHistory(selectId);// メソッドに入ったユーザーの購入情報を取得
		List<PurchaseDTO> allPurchaseList = new ArrayList<>();
		PurchaseDTO customList;
		// 購入商品を一つづつ回して値を受け取る
		if (purchasedtoList.size() > 0) {
			for (int i = 0; purchasedtoList.size() > i; i++) {
				PurchaseDTO purchasedtoAdd = new PurchaseDTO();
				PurchaseDTO purchaseOne = purchasedtoList.get(i);
				purchasedtoAdd.setId(purchaseOne.getId());// カスタム情報取得に使用
				purchasedtoAdd.setCancelCheck(purchaseOne.getCancelCheck());
				purchasedtoAdd.setPrice(purchaseOne.getPrice());
				purchasedtoAdd.setProduct_count(purchaseOne.getProduct_count());
				purchasedtoAdd.setPurchaseCheck(purchaseOne.getPurchaseCheck());
				// 購入商品ごとのカスタム情報も取り出す
				int productId = purchasedtoAdd.getId();
				// int customId = purchasedtoAdd.getCustom_id();

				String nullCheck = "null";
				int getCustomId = customService.selectPurchaseCheck(selectId, productId,
						purchasedtoAdd.getPurchaseCheck(), nullCheck);

				customList = customService.selectMany(getCustomId);// customテーブルのIDでカスタム情報を取得
				purchasedtoAdd.setCustomPrice(customList.getCustomPrice());
				purchasedtoAdd.setTotalPrice(
						purchaseOne.getProduct_count() * (customList.getCustomPrice() + purchaseOne.getPrice()));
				allTotalPrice = allTotalPrice + purchasedtoAdd.getTotalPrice();// ユーザーの購入した商品の合計金額を取得
				allProductCount = allProductCount + purchasedtoAdd.getProduct_count();// ユーザーの購入した商品の数を取得
				model.addAttribute("purchaseCount", allProductCount);
				model.addAttribute("allTotalPrice", allTotalPrice);
//			int priceRank = allTotalPrice * 10;
//			int countRank = allProductCount * 30000;
//			int rankPoint = priceRank + countRank;
//			System.out.println(rankPoint);
				System.out.println("allTotalPrice" + allTotalPrice);
				if ((allTotalPrice > 0) && (allTotalPrice < 50000)) {

					session.setAttribute("rankPoint", "アマチュアランク");
				} else if ((allTotalPrice >= 50000) && (allTotalPrice < 100000)) {

					session.setAttribute("rankPoint", "プロランク");
				} else if ((allTotalPrice >= 100000) && (allTotalPrice < 200000)) {

					session.setAttribute("rankPoint", "ブロンズランク");
				} else if ((allTotalPrice >= 200000) && (allTotalPrice < 400000)) {

					session.setAttribute("rankPoint", "シルバーランク");
				} else if ((allTotalPrice >= 400000) && (allTotalPrice < 800000)) {

					session.setAttribute("rankPoint", "ゴールドランク");
				} else if ((allTotalPrice >= 800000) && (allTotalPrice < 1000000)) {

					session.setAttribute("rankPoint", "ダイヤモンドランク");
				} else if ((allTotalPrice >= 1000000) && (allTotalPrice < 1500000)) {

					session.setAttribute("rankPoint", "プラチナランク");
				} else if ((allTotalPrice >= 1500000) && (allTotalPrice < 3000000)) {

					session.setAttribute("rankPoint", "エイリアンランク");
				} else if ((allTotalPrice >= 3000000) && (allTotalPrice < 5000000)) {

					session.setAttribute("rankPoint", "ゴッドフォックスランク");
				} else if ((allTotalPrice >= 5000000) && (allTotalPrice < 8000000)) {

					session.setAttribute("rankPoint", "プレミアムゴッドランク");
				} else if ((allTotalPrice >= 8000000)) {

					session.setAttribute("rankPoint", "InductedIntoTheHalOfFameRank");
				}
			}
		} else {
			session.setAttribute("rankPoint", "アマチュアランク");
		}
		return "shopping/productListLayout";
	}

	@GetMapping("/productDetail/{id}")
	public String getProductDetail(@ModelAttribute PcDetailDataForm form, PcDataForm pcdataform,
			HttpServletRequest request, Model model, @PathVariable("id") int id) {
		model.addAttribute("contents", "shopping/productDetail::productListLayout_contents");

		System.out.println("redirectcheck");
		try {
			List<ReviewDTO> reviewList = reviewService.selectRating(id);
			if (reviewList.size() != 0) {
				System.out.println("reviewList" + reviewList);
				double totalRating = 0;
				for (int i = 0; reviewList.size() > i; i++) {
					ReviewDTO reviewdto = reviewList.get(i);
					totalRating = totalRating + reviewdto.getRating();
					System.out.println("totalRating" + totalRating);
				}

				double evaluation = totalRating / reviewList.size();
				System.out.println("evaluation" + evaluation);
				model.addAttribute("reviews", reviewList.size());
				System.out.println("evaluation" + evaluation);
				model.addAttribute("evaluation", evaluation);
			} else {

				model.addAttribute("evaluation", "評価はまだありません");
			}
		} catch (ArithmeticException e) {

			e.printStackTrace();
			model.addAttribute("evaluation", "評価はまだありません");
		}

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

		CustomDTO customdto = customService.selectCustomProduct_id(id, select_id);
		System.out.println("test" + customdto.getProductId());
		System.out.println("test2" + id);
		if (customdto.getProductId() != id) {
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

	@GetMapping("/pointUse")
	public String getPointUse(@ModelAttribute PointUseForm form,Model model) {
		model.addAttribute("contents", "shopping/pointUse::productListLayout_contents");
		
		
		
		return "shopping/productListLayout";
	}
	
	@GetMapping("/clearing")
	public String getCardClearing(@ModelAttribute CreditForm form, @RequestParam("couponId") int couponId,
			Model model) {
		model.addAttribute("contents", "shopping/clearing::productListLayout_contents");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String getName = auth.getName();

		List<PcDataDTO> cartList = cartService.selectMany(getName);
		int totalPriceAll = 0;// カート全体価格
		int totalPriceOne = 0;// 各商品価格
		double disCountPrice = 0;

		for (int i = 0; i < cartList.size(); i++) {
			PcDataDTO pcdatadto = cartList.get(i);

			totalPriceAll = totalPriceAll + pcdatadto.getProduct_count() * pcdatadto.getAfterCustomPrice();// カート内の合計金額
			System.out.println("totalPriceAll" + totalPriceAll);

			totalPriceOne = pcdatadto.getProduct_count() * pcdatadto.getAfterCustomPrice();// 各商品の金額

			try {
				if (pcdatadto.getMenberCouponCheck().equals("会員ランク特典使用")) {
					System.out.println("会員ランク特典使用");
					MenberCouponDTO menbercoupondto = menberCouponService.selectOne(couponId);
					int menberCouponDisCount = menbercoupondto.getDiscount();
					double disCountNew = 0;
					if (menberCouponDisCount < 10) {
						System.out.println("test");
						disCountNew = Double.valueOf("0.0" + menberCouponDisCount);

						double disCountPriceNew = totalPriceOne * disCountNew;// 割引価格
						int disCountPriceNewNext = (int) disCountPriceNew;
						System.out.println("disCounttest" + disCountPriceNewNext);
						System.out.println("disCounttest" + totalPriceAll);
						pcdatadto.setDisCountPriceNew(disCountPriceNewNext);
						totalPriceAll = (int) (totalPriceAll - disCountPriceNew);
						System.out.println("totalPriceAll" + totalPriceAll);
						model.addAttribute("totalPrice", totalPriceAll);
					} else {
						disCountNew = Double.valueOf("0." + menberCouponDisCount);

						double disCountPriceNew = totalPriceOne * disCountNew;// 割引価格
						int disCountPriceNewNext = (int) disCountPriceNew;
						pcdatadto.setDisCountPriceNew(disCountPriceNewNext);
						totalPriceAll = (int) (totalPriceAll - disCountPriceNew);
						model.addAttribute("totalPrice", totalPriceAll);
					}
				} else {
					System.out.println("会員ランク特典不使用");
					if (pcdatadto.getCouponId() == 0) {
						System.out.println("クーポン使用なし");
						int couponAfterPrice = (int) totalPriceAll;
						System.out.println("couponAfterPrice" + couponAfterPrice);
						model.addAttribute("totalPrice", totalPriceAll);

					} else {// クーポンを使用した商品はelse
						System.out.println("クーポン使用！");
						CouponDTO coupondto = couponService.selectOne(couponId);
						int disCount = coupondto.getDiscount();
						if (disCount >= 10) {
							double disCountNew = Double.valueOf("0." + disCount);
							System.out.println("discoutnnew" + disCountNew);
							disCountPrice = totalPriceOne * disCountNew;// クーポンを使用した商品の割引数取得

							System.out.println("totalAll" + totalPriceAll);
							System.out.println("totalOne" + totalPriceOne);
							System.out.println("Dis" + disCountPrice);
							// ここから割引分の値が入っているのでそれをトータルからひく、小数点以下も切り捨てる
							// model.addAttribute("totalPrice", couponAfterPrice);
							totalPriceAll = (int) (totalPriceAll - disCountPrice);
							model.addAttribute("totalPrice", totalPriceAll);
						} else {
							double disCountNew = Double.valueOf("0.0" + disCount);
							System.out.println("discoutnnew" + disCountNew);
							disCountPrice = totalPriceOne * disCountNew;// クーポンを使用した商品の割引数取得

							System.out.println("totalAll" + totalPriceAll);
							System.out.println("totalOne" + totalPriceOne);
							System.out.println("Dis" + disCountPrice);
							// ここから割引分の値が入っているのでそれをトータルからひく、小数点以下も切り捨てる
							// model.addAttribute("totalPrice", couponAfterPrice);
							totalPriceAll = (int) (totalPriceAll - disCountPrice);
							model.addAttribute("totalPrice", totalPriceAll);
						}
					}
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
				if (pcdatadto.getCouponId() == 0) {
					System.out.println("クーポン使用なし");
					int couponAfterPrice = (int) totalPriceAll;
					System.out.println("couponAfterPrice" + couponAfterPrice);
					model.addAttribute("totalPrice", totalPriceAll);

				} else {// クーポンを使用した商品はelse
					System.out.println("クーポン使用！");
					CouponDTO coupondto = couponService.selectOne(couponId);
					int disCount = coupondto.getDiscount();
					if (disCount >= 10) {
						double disCountNew = Double.valueOf("0." + disCount);
						System.out.println("discoutnnew" + disCountNew);
						disCountPrice = totalPriceOne * disCountNew;// クーポンを使用した商品の割引数取得

						System.out.println("totalAll" + totalPriceAll);
						System.out.println("totalOne" + totalPriceOne);
						System.out.println("Dis" + disCountPrice);
						// ここから割引分の値が入っているのでそれをトータルからひく、小数点以下も切り捨てる
						// model.addAttribute("totalPrice", couponAfterPrice);
						totalPriceAll = (int) (totalPriceAll - disCountPrice);
						model.addAttribute("totalPrice", totalPriceAll);
					} else {
						double disCountNew = Double.valueOf("0.0" + disCount);
						System.out.println("discoutnnew" + disCountNew);
						disCountPrice = totalPriceOne * disCountNew;// クーポンを使用した商品の割引数取得

						System.out.println("totalAll" + totalPriceAll);
						System.out.println("totalOne" + totalPriceOne);
						System.out.println("Dis" + disCountPrice);
						// ここから割引分の値が入っているのでそれをトータルからひく、小数点以下も切り捨てる
						// model.addAttribute("totalPrice", couponAfterPrice);
						totalPriceAll = (int) (totalPriceAll - disCountPrice);
						model.addAttribute("totalPrice", totalPriceAll);
					}
				}

			}

			// model.addAttribute("product_count",pcdatadto.getProduct_count());
			// form.setProduct_count(pcdatadto.getProduct_count());
			System.out.println("form" + form);
		}
		if (couponId != 0) {
			model.addAttribute("couponId", couponId);
		} else {
			model.addAttribute("couponId", -1);
		}
		System.out.println("coId" + couponId);
		return "shopping/productListLayout";

	}

	@PostMapping("/clearing/{couponId}")
	public String getClearing(@ModelAttribute @Validated(GroupOrder.class) CreditForm form, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, @RequestParam("digits_3_code") String digits_3_code,
			@RequestParam("cardName") String cardName, @RequestParam("cardNumber") String cardNumber,
			@PathVariable("couponId") int couponId, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// model.addAttribute("contents",
		// "shopping/confirmation::productListLayout_contents");
		if (bindingResult.hasErrors()) {
			System.out.println("バリデーションエラー");
			return getCardClearing(form, couponId, model);
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
		session.setAttribute("couponId", couponId);

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

		cartService.couponCancelUpdate();// クーポンを不使用に変更

		// ログインユーザーのみのカートの情報を取得
		List<PcDataDTO> cartList = cartService.selectMany(user_id);
		System.out.println("cartList" + cartList);
		if (cartList == null || cartList.size() == 0) {
			model.addAttribute("totalPrice", 0);
			model.addAttribute("notProduct", "yes");

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

		model.addAttribute("cartList", cartList);
		// model.addAttribute("couponUseCheck","couponNotUse");
		model.addAttribute("couponId", 0);
		model.addAttribute("couponAfterPrice", "-1");// クーポン使用していない時に表示するテーブルを出すための値

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

	@GetMapping(value = "/cart/{id}", params = "review")
	public String getReview(@ModelAttribute ReviewForm form, @PathVariable("id") int productId, Model model) {
		model.addAttribute("contents", "shopping/reviewSee::productListLayout_contents");

		List<ReviewDTO> reviewList = reviewService.selectMany(productId);
		System.out.println("reviewList" + reviewList);
		model.addAttribute("reviewList", reviewList);

		PcDataDTO pcdatadto = pcdataService.selectPcName(productId);
		model.addAttribute("productId", productId);
		model.addAttribute("pcName", pcdatadto.getPc_name());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String getName = auth.getName();
		System.out.println("getName" + getName);
		model.addAttribute("userId", getName);

		return "shopping/productListLayout";
	}

	@GetMapping("reviewSeeDetail/{id}/{productId}")
	public String getReviewSeeDetail(@ModelAttribute ReviewForm form, @PathVariable("id") int reviewId,
			@PathVariable("productId") int productId, Model model) {
		model.addAttribute("contents", "shopping/reviewSeeDetail::productListLayout_contents");

		ReviewDTO reviewdto = reviewService.selectReviewDetailOne(reviewId);
		model.addAttribute("reviewList", reviewdto);

		PcDataDTO pcdatadto = pcdataService.selectPcName(productId);
		model.addAttribute("pcName", pcdatadto.getPc_name());

		model.addAttribute("productId", productId);

		return "shopping/productListLayout";
	}

	@PostMapping(value = "reviewSeeDetail", params = "delete")
	public String postReviewSeeDetailDelete(@ModelAttribute ReviewForm form, @RequestParam("reviewId") int reviewId,
			@RequestParam("productId") int productId, Model model) {

		reviewService.deleteOne(reviewId);

		return getReview(form, productId, model);
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
		return cart(form, model);
		// return "redirect:/cart";
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
	public String postCartCountUpdate(@ModelAttribute @Validated(GroupOrder.class) CartForm form,
			BindingResult bindingResult, Model model, RedirectAttributes redirectattributes,
			@PathVariable("id") int productId) {
		System.out.println("countUpdate到達");

		if (bindingResult.hasErrors()) {
			System.out.println("バリデーションエラー到達");

			return cart(form, model);
		}

		int productcount = Integer.parseInt(form.getProduct_count());
		if (productcount == 0) {
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
		int totalPriceAll = 0;// カート全体価格
		int totalPriceOne = 0;// 各商品価格
		for (int i = 0; i < cartList.size(); i++) {
			PcDataDTO pcdatadto = cartList.get(i);
			totalPriceAll = totalPriceAll + pcdatadto.getProduct_count() * pcdatadto.getAfterCustomPrice();
			totalPriceOne = pcdatadto.getProduct_count() * pcdatadto.getAfterCustomPrice();
			pcdatadto.setTotalPriceOne(totalPriceOne);
			System.out.println("totalPrice" + totalPriceAll);

			try {
				if (pcdatadto.getMenberCouponCheck().equals("会員ランク特典使用")) {
					System.out.println("会員ランク特典使用");
					MenberCouponDTO menbercoupondto = menberCouponService.selectOne(pcdatadto.getCouponId());
					int menberCouponDisCount = menbercoupondto.getDiscount();
					double disCountNew = 0;
					pcdatadto.setCouponCheck(true);
					if (menberCouponDisCount < 10) {
						System.out.println("testgggggggggggggggggggggg");
						disCountNew = Double.valueOf("0.0" + menberCouponDisCount);

						double disCountPriceNew = totalPriceOne * disCountNew;// 割引価格
						int disCountPriceNewNext = (int) disCountPriceNew;
						System.out.println("disCounttest" + disCountPriceNewNext);
						System.out.println("disCounttest" + totalPriceAll);
						pcdatadto.setDisCountPriceNew(disCountPriceNewNext);
						totalPriceAll = totalPriceAll - disCountPriceNewNext;
						System.out.println("disCounttest" + totalPriceAll);
						pcdatadto.setTotalPrice(totalPriceAll);
						model.addAttribute("totalPrice",totalPriceAll);
					} else {
						disCountNew = Double.valueOf("0." + menberCouponDisCount);

						double disCountPriceNew = totalPriceOne * disCountNew;// 割引価格
						int disCountPriceNewNext = (int) disCountPriceNew;
						pcdatadto.setDisCountPriceNew(disCountPriceNewNext);
						totalPriceAll = totalPriceAll - disCountPriceNewNext;
						pcdatadto.setTotalPrice(totalPriceAll);
						model.addAttribute("totalPrice",totalPriceAll);
					}
				} else {
					System.out.println("会員ランク特典不使用");

					if (pcdatadto.getCouponId() == 0) {
						model.addAttribute("totalPrice", totalPriceAll);
					} else {// クーポンを使用していればelse
						CouponDTO coupondto = couponService.selectOne(pcdatadto.getCouponId());// クーポン情報取得
						int disCount = coupondto.getDiscount();// 割引％
						if (disCount >= 10) {
							double disCountNew = Double.valueOf("0." + disCount);
							double disCountPrice = totalPriceOne * disCountNew;// 割引数
							pcdatadto.setTotalPriceOne((int) (totalPriceOne - disCountPrice));
							totalPriceAll = (int) (totalPriceAll - disCountPrice);
							pcdatadto.setTotalPrice(totalPriceAll);
							pcdatadto.setCouponCheck(true);
						} else {
							double disCountNew = Double.valueOf("0.0" + disCount);
							double disCountPrice = totalPriceOne * disCountNew;// 割引数
							pcdatadto.setTotalPriceOne((int) (totalPriceOne - disCountPrice));
							totalPriceAll = (int) (totalPriceAll - disCountPrice);
							pcdatadto.setTotalPrice(totalPriceAll);
							pcdatadto.setCouponCheck(true);
						}
					}
				}

			} catch (NullPointerException e) {
				e.printStackTrace();
				if (pcdatadto.getCouponId() == 0) {
					model.addAttribute("totalPrice", totalPriceAll);
				} else {// クーポンを使用していればelse
					CouponDTO coupondto = couponService.selectOne(pcdatadto.getCouponId());// クーポン情報取得
					int disCount = coupondto.getDiscount();// 割引％
					if (disCount >= 10) {
						double disCountNew = Double.valueOf("0." + disCount);
						double disCountPrice = totalPriceOne * disCountNew;// 割引数
						pcdatadto.setTotalPriceOne((int) (totalPriceOne - disCountPrice));
						totalPriceAll = (int) (totalPriceAll - disCountPrice);
						pcdatadto.setTotalPrice(totalPriceAll);
						pcdatadto.setCouponCheck(true);
					} else {
						double disCountNew = Double.valueOf("0.0" + disCount);
						double disCountPrice = totalPriceOne * disCountNew;// 割引数
						pcdatadto.setTotalPriceOne((int) (totalPriceOne - disCountPrice));
						totalPriceAll = (int) (totalPriceAll - disCountPrice);
						pcdatadto.setTotalPrice(totalPriceAll);
						pcdatadto.setCouponCheck(true);
					}
				}
			}
			model.addAttribute("totalPrice", totalPriceAll);
			
			int pointRate = pointRateService.selectOne(1);
			String pointRateString = String.valueOf(pointRate);
			String pointRateNew = 0.0 + pointRateString;
			System.out.println("pointRateNew"+pointRateNew);
			double pointRateDouble = Double.parseDouble(pointRateNew);
			System.out.println("pointRateDouble"+pointRateDouble);
			int point = (int) (totalPriceAll * pointRateDouble);//購入金額の3%をポイントとする
			System.out.println("pointfinish"+point);
			model.addAttribute("point",point);
			
			session.setAttribute("point",point);
			int pointNew = (int) session.getAttribute("point");
			
			int getId = usersService.select_id(getName);

			System.out.println("cartList " + cartList);
			model.addAttribute("cartList", cartList);

			// クレジット情報をsessionから取得
			String digits_3_code = (String) session.getAttribute("digits_3_code");
			String cardName = (String) session.getAttribute("cardName");
			String cardNumber = (String) session.getAttribute("cardNumber");
			int couponId = (int) session.getAttribute("couponId");

			model.addAttribute("digits_3_code", digits_3_code);
			model.addAttribute("cardName", cardName);
			model.addAttribute("cardNumber", cardNumber);
			model.addAttribute("couponId", couponId);
			

		}
		return "shopping/productListLayout";
	}

	@PostMapping("/confirmation")
	public String postConfirmation(@ModelAttribute PcDataForm form, RedirectAttributes redirectattributes,
			@RequestParam("digits_3_code") String digits_3_code, @RequestParam("cardName") String cardName,
			@RequestParam("cardNumber") String cardNumber, @RequestParam("totalPrice") int totalPrice,
			@RequestParam("couponId") int couponId, HttpServletRequest request, HttpServletResponse response,
			Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String getName = auth.getName();

		int select_id = usersService.select_id(getName);
		// 各商品のユーザーが購入した数を取得
		boolean result = cartService.selectProductCount(select_id);
		
		int point = (int) session.getAttribute("point");
		

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
			System.out.println("proIddto"+cartdto.getProduct_id());
			int cartId = cartdto.getId();
			// int selectProductId = cartService.selectProductId(cartId);
			int id = cartdto.getId();
			int productId = cartdto.getProduct_id();

			int productid = cartdto.getProduct_id();
			System.out.println("productId"+productid);
			int customId = customService.selectCustomId(productId, select_id);
			int purchaseCount = cartdto.getProduct_count();
			int couponCheck = cartdto.getCouponId();
			System.out.println("menbercouponcheck?" + cartdto.getMenberCouponCheck());

			// customService.pruchaseIdInsertOne(productid);
			try {

				if (cartdto.getMenberCouponCheck().equals("会員ランク特典使用")) {

					purchaseService.insertMenberCoupon(purchasedto, productid, purchaseCount, select_id,
							purchaseCreditId, customId, couponId,point);
				} else if (couponCheck > 0) {
					System.out.println("クーポン使用");
					purchaseService.insert(purchasedto, productid, purchaseCount, select_id, purchaseCreditId, customId,
							couponId,point);
				} else {
					System.out.println("クーポン未使用");
					purchaseService.insertNotCoupon(purchasedto, productid, purchaseCount, select_id, purchaseCreditId,
							customId,point);
				}
			} catch (NullPointerException e) {
				System.out.println("purchaseInsertnot");
				e.printStackTrace();
				if (couponCheck > 0) {
					System.out.println("クーポン使用");
					purchaseService.insert(purchasedto, productid, purchaseCount, select_id, purchaseCreditId, customId,
							couponId,point);
				} else {
					System.out.println("クーポン未使用");
					purchaseService.insertNotCoupon(purchasedto, productid, purchaseCount, select_id, purchaseCreditId,
							customId,point);
				}
			}
			int purchaseId = purchaseService.selectPurchaseIdOne();
			System.out.println("purchaseId" + purchaseId);
			customService.pruchaseIdUpdate(purchaseId, productId, select_id);
			cartService.idInsertOne(purchaseId, productId, select_id);
		}

		// return "redirect:/after_purchase";
		return getAfter_purchase(model);
	}

	@GetMapping("/purchaseHistory")
	public String getPurchaseHistory(@ModelAttribute PcDataForm form, Model model) {
		model.addAttribute("contents", "shopping/purchaseHistory::productListLayout_contents");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String getName = auth.getName();

		int select_id = usersService.select_id(getName);

		// 購入商品情報リスト取得
		List<PurchaseDTO> purchasedtoList = purchaseService.selectHistory(select_id);

		PurchaseDTO purchasedto = new PurchaseDTO();

		List<PurchaseDTO> allPurchaseList = new ArrayList<>();
		PurchaseDTO customList;
		// 購入商品を一つづつ回して値を受け取る
		for (int i = 0; purchasedtoList.size() > i; i++) {
			PurchaseDTO purchasedtoAdd = new PurchaseDTO();

			PurchaseDTO purchaseOne = purchasedtoList.get(i);
			purchasedtoAdd.setId(purchaseOne.getId());// カスタム情報取得に使用
			purchasedtoAdd.setPurchaseId(purchaseOne.getPurchaseId());
			purchasedtoAdd.setPurchase_date(purchaseOne.getPurchase_date());
			purchasedtoAdd.setCancelCheck(purchaseOne.getCancelCheck());
			purchasedtoAdd.setCouponId(purchaseOne.getCouponId());
			purchasedtoAdd.setPcName(purchaseOne.getPcName());
			purchasedtoAdd.setPrice(purchaseOne.getPrice());
			purchasedtoAdd.setPcImg(purchaseOne.getPcImg());
			purchasedtoAdd.setProduct_count(purchaseOne.getProduct_count());
			purchasedtoAdd.setPurchaseCheck(purchaseOne.getPurchaseCheck());
			purchasedtoAdd.setMenberCouponCheck(purchaseOne.getMenberCouponCheck());

			// 購入商品ごとのカスタム情報も取り出す

			int productId = purchasedtoAdd.getId();
			System.out.println("productId" + productId);
			System.out.println(select_id);
			int customId = purchasedtoAdd.getCustom_id();
			System.out.println("customId");
			String nullCheck = "null";
			int getCustomId = customService.selectPurchaseCheck(select_id, productId, purchasedtoAdd.getPurchaseCheck(),
					nullCheck);
			System.out.println("getCustomId" + getCustomId);

			customList = customService.selectMany(getCustomId);
			System.out.println("costomList" + customList);

			purchasedtoAdd.setMemory(customList.getMemory());
			purchasedtoAdd.setHardDisc(customList.getHardDisc());
			purchasedtoAdd.setCpu(customList.getCpu());
			purchasedtoAdd.setCustomPrice(customList.getCustomPrice());
			purchasedtoAdd.setTotalPrice(
					purchaseOne.getProduct_count() * (customList.getCustomPrice() + purchaseOne.getPrice()));

			if (purchasedtoAdd.getMenberCouponCheck().equals("会員クーポン使用")) {
				System.out.println("クーポン使用！");
				int totalPrice = purchasedtoAdd.getTotalPrice();
				MenberCouponDTO menbercoupondto = menberCouponService.selectOne(purchasedtoAdd.getCouponId());// 会員DBからとる
				int disCount = menbercoupondto.getDiscount();// 割引率(%)
				if (disCount >= 10) {
					double disCountNew = Double.valueOf("0." + disCount);
					double disCountPriceNew = totalPrice * disCountNew;// 割引価格
					purchasedtoAdd.setTotalPrice((int) (totalPrice - disCountPriceNew));
				} else {
					double disCountNew = Double.valueOf("0.0" + disCount);
					double disCountPriceNew = totalPrice * disCountNew;// 割引価格
					purchasedtoAdd.setTotalPrice((int) (totalPrice - disCountPriceNew));
				}
			} else {

				if (purchasedtoAdd.getCouponId() > 0) {
					System.out.println("クーポン使用！");
					int totalPrice = purchasedtoAdd.getTotalPrice();
					CouponDTO coupondto = couponService.selectOne(purchasedtoAdd.getCouponId());
					int disCount = coupondto.getDiscount();// 割引率(%)
					if (disCount >= 10) {
						double disCountNew = Double.valueOf("0." + disCount);
						double disCountPriceNew = totalPrice * disCountNew;// 割引価格
						purchasedtoAdd.setTotalPrice((int) (totalPrice - disCountPriceNew));
					} else {
						double disCountNew = Double.valueOf("0.0" + disCount);
						double disCountPriceNew = totalPrice * disCountNew;// 割引価格
						purchasedtoAdd.setTotalPrice((int) (totalPrice - disCountPriceNew));
					}
				}
			}
			// Date purchaseDate = purchasedtoAdd.getPurchase_date();
			Calendar calendar = Calendar.getInstance();
			Date now = calendar.getTime();
			calendar.setTime(purchasedtoAdd.getPurchase_date());
			Date purchaseDate = calendar.getTime();// 購入日付
			model.addAttribute("purchaseCheck", purchaseDate);
			calendar.add(Calendar.DATE, 10);
			Date purchaseDateAddTen = calendar.getTime();// キャンセル期間外の購入日付から10日後の日付
			model.addAttribute("result", purchaseDateAddTen);
			System.out.println("pur" + purchaseDate.getTime());
			System.out.println("now" + now.getTime());
			long d = (purchaseDate.getTime() - now.getTime()) / (1000 * 60 * 60 * 24);// 購入日と現在の日付を比べる
			int count = (int) -d;

			if (count <= 10) {
				System.out.println("true");
				purchasedtoAdd.setCancelResult("true");
			} else {
				purchasedtoAdd.setCancelResult("false");
			}

			System.out.println("testfalse");
			CancelDTO canceldto = cancelService.selectCancelCheck(purchasedtoAdd.getPurchaseId());
			System.out.println("testdd" + canceldto);
			if (canceldto.getCancelCheck() != null) {
				System.out.println("truefdfdfffdf");
				purchasedtoAdd.setCancelResult("true");
			}

			System.out.println("purId" + purchasedtoAdd.getPurchaseId());

			allPurchaseList.add(purchasedtoAdd);
			System.out.println("allPurchaseList" + allPurchaseList);

		}

		model.addAttribute("purchaseList", allPurchaseList);
		model.addAttribute("confirmationPending", "返品商品確認待ち");
		model.addAttribute("inTransaction", "キャンセル取引中");
		return "shopping/productListLayout";

	}

	// ログアウト用メソッド
	@GetMapping("logout")
	public String getLogout() {

		// ログイン画面にリダイレクト
		return "redirect:/login";
	}
}
