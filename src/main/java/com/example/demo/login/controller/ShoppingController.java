package com.example.demo.login.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

import com.example.demo.login.domail.model.AuctionDataDTO;
import com.example.demo.login.domail.model.AuctionListingForm;
import com.example.demo.login.domail.model.AuctionProductHistoryDTO;
import com.example.demo.login.domail.model.AuctionTenderDataDTO;
import com.example.demo.login.domail.model.AuctionTenderForm;
import com.example.demo.login.domail.model.CancelDTO;
import com.example.demo.login.domail.model.CancelForm;
import com.example.demo.login.domail.model.CancelInTransactionForm;
import com.example.demo.login.domail.model.CancelNextForm;
import com.example.demo.login.domail.model.CartDTO;
import com.example.demo.login.domail.model.CartForm;
import com.example.demo.login.domail.model.ChallengeProgrammingContractDTO;
import com.example.demo.login.domail.model.ChallengeProgrammingContractForm;
import com.example.demo.login.domail.model.ChallengeProgrammingDTO;
import com.example.demo.login.domail.model.ChallengeProgrammingEvaluationDTO;
import com.example.demo.login.domail.model.ChallengeProgrammingForm;
import com.example.demo.login.domail.model.ChallengeProgrammingHistoryDTO;
import com.example.demo.login.domail.model.ChallengeProgrammingMessageDTO;
import com.example.demo.login.domail.model.ChallengeProgrammingTradeForm;
import com.example.demo.login.domail.model.CouponDTO;
import com.example.demo.login.domail.model.CouponForm;
import com.example.demo.login.domail.model.CreditDTO;
import com.example.demo.login.domail.model.CreditForm;
import com.example.demo.login.domail.model.CustomDTO;
import com.example.demo.login.domail.model.GachaContentDTO;
import com.example.demo.login.domail.model.GachaDTO;
import com.example.demo.login.domail.model.GachaPointInterChangeDTO;
import com.example.demo.login.domail.model.GachaPointProductHistoryDTO;
import com.example.demo.login.domail.model.GachaProbabilityDTO;
import com.example.demo.login.domail.model.GroupOrder;
import com.example.demo.login.domail.model.InquiryAllDTO;
import com.example.demo.login.domail.model.InquiryBeforeLoginForm;
import com.example.demo.login.domail.model.InquiryDTO;
import com.example.demo.login.domail.model.InquiryForm;
import com.example.demo.login.domail.model.InquiryReplyDTO;
import com.example.demo.login.domail.model.InquiryReplyForm;
import com.example.demo.login.domail.model.LessonEndForm;
import com.example.demo.login.domail.model.LessonEvaluationForm;
import com.example.demo.login.domail.model.LessonStartForm;
import com.example.demo.login.domail.model.MenberCouponDTO;
import com.example.demo.login.domail.model.MenberCouponForm;
import com.example.demo.login.domail.model.NewsDTO;
import com.example.demo.login.domail.model.NewsForm;
import com.example.demo.login.domail.model.PandPlanExecutionForm;
import com.example.demo.login.domail.model.PcDataDTO;
import com.example.demo.login.domail.model.PcDataForm;
import com.example.demo.login.domail.model.PcDetailDataDTO;
import com.example.demo.login.domail.model.PcDetailDataForm;
import com.example.demo.login.domail.model.PointRateChangeForm;
import com.example.demo.login.domail.model.PointUseForm;
import com.example.demo.login.domail.model.ProductListSearchForm;
import com.example.demo.login.domail.model.PurchaseDTO;
import com.example.demo.login.domail.model.ReviewDTO;
import com.example.demo.login.domail.model.ReviewForm;
import com.example.demo.login.domail.model.SalesManagementForm;
import com.example.demo.login.domail.model.StockInputForm;
import com.example.demo.login.domail.model.Usege_usersDTO;
import com.example.demo.login.domail.model.UserEditForm;
import com.example.demo.login.domail.model.UsersDTO;
import com.example.demo.login.domail.model.UsersListDTO;
import com.example.demo.login.domail.model.UsersListForm;
import com.example.demo.login.domail.model.auctionPaymentProductForm;
import com.example.demo.login.domail.service.AuctionDataService;
import com.example.demo.login.domail.service.AuctionProductHistoryService;
import com.example.demo.login.domail.service.AuctionTenderDataService;
import com.example.demo.login.domail.service.CancelService;
import com.example.demo.login.domail.service.CartService;
import com.example.demo.login.domail.service.ChallengeProgrammingContractService;
import com.example.demo.login.domail.service.ChallengeProgrammingEvaluationService;
import com.example.demo.login.domail.service.ChallengeProgrammingHistoryService;
import com.example.demo.login.domail.service.ChallengeProgrammingLessonStartPasswordService;
import com.example.demo.login.domail.service.ChallengeProgrammingMessageService;
import com.example.demo.login.domail.service.ChallengeProgrammingService;
import com.example.demo.login.domail.service.CouponService;
import com.example.demo.login.domail.service.CreditService;
import com.example.demo.login.domail.service.CustomService;
import com.example.demo.login.domail.service.GachaContentService;
import com.example.demo.login.domail.service.GachaPointInterChangeService;
import com.example.demo.login.domail.service.GachaPointProductHistoryService;
import com.example.demo.login.domail.service.GachaPointsService;
import com.example.demo.login.domail.service.GachaService;
import com.example.demo.login.domail.service.InquiryReplyService;
import com.example.demo.login.domail.service.InquiryService;
import com.example.demo.login.domail.service.MailService;
import com.example.demo.login.domail.service.MenberCouponService;
import com.example.demo.login.domail.service.NewsService;
import com.example.demo.login.domail.service.PcDataService;
import com.example.demo.login.domail.service.PointRateService;
import com.example.demo.login.domail.service.ProductGoodService;
import com.example.demo.login.domail.service.PurchaseService;
import com.example.demo.login.domail.service.ReviewService;
import com.example.demo.login.domail.service.Usege_usersService;
import com.example.demo.login.domail.service.UsersService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

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
	InquiryReplyService inquiryReplyService;
	@Autowired
	MenberCouponService menberCouponService;
	@Autowired
	PointRateService pointRateService;
	@Autowired
	ChallengeProgrammingService challengeProgrammingService;
	@Autowired
	ChallengeProgrammingMessageService challengeProgrammingMessageService;
	@Autowired
	ChallengeProgrammingContractService challengeProgrammingContractService;
	@Autowired
	ChallengeProgrammingLessonStartPasswordService challengeProgrammingLessonStartPasswordService;
	@Autowired
	MailService mailService;
	@Autowired
	ChallengeProgrammingEvaluationService challengeProgrammingEvaluationService;
	@Autowired
	ChallengeProgrammingHistoryService challengeProgrammingHistoryService;
	@Autowired
	GachaService gachaService;
	@Autowired
	GachaContentService gachaContentService;
	@Autowired
	GachaPointsService gachaPointsService;
	@Autowired
	GachaPointInterChangeService gachaPointInterChangeService;
	@Autowired
	GachaPointProductHistoryService gachaPointProductHistoryService;
	@Autowired
	AuctionDataService auctionDataService;
	@Autowired
	AuctionTenderDataService auctionTenderDataService;
	@Autowired
	ProductGoodService productGoodService;
	@Autowired
	AuctionProductHistoryService auctionProductHistoryService;

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
		userslistdto.setPlan(usersdto.getPlan());
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
				// ユーザーのランクを取得
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

		List<PurchaseDTO> purchasePointList = purchaseService.selectPoint(selectId);// 取得ポイントと利用ポイントを取得
		int pointAll = 0;
		int pointUseAll = 0;
		int purchasePoint = 0;
		for (int x = 0; purchasePointList.size() > x; x++) {
			PurchaseDTO purchasedtoOne = purchasePointList.get(x);
			int pointOne = purchasedtoOne.getPoint();
			int pointUseOne = purchasedtoOne.getPointUse();
			pointAll = pointAll + pointOne;
			pointUseAll = pointUseAll + pointUseOne;
			purchasePoint = pointAll - pointUseAll;// 取得ポイント - 利用ポイントで現在保持しているポイントを出す
		}

		List<CancelDTO> cancelPointList = cancelService.selectPoint(selectId);

		int cancelPoint = 0;
		for (int y = 0; cancelPointList.size() > y; y++) {
			CancelDTO canceldtoOne = cancelPointList.get(y);
			int returnPoint = canceldtoOne.getReturnPoint();
			int pointRepayment = canceldtoOne.getPointRepayment();
			cancelPoint = returnPoint - pointRepayment;// 返ってきたポイント - 購入時付加ポイントでキャンセル時の返すポイントを出す
		}

		int ITworksTRYshoppingP = purchasePoint + cancelPoint;
		if (ITworksTRYshoppingP < 1) {
			model.addAttribute("point", 0);
		}
		model.addAttribute("point", ITworksTRYshoppingP);

		return "shopping/productListLayout";
	}
	
	@GetMapping("/pandPlan")
	public String getPandPlan(Model model) {
		model.addAttribute("contents", "shopping/pandPlan::productListLayout_contents");
		
		return "shopping/productListLayout";
	}
	
	@GetMapping("/pandPlanExecution")
	public String getPandPlanExecution(PandPlanExecutionForm form,Model model) {
		model.addAttribute("contents", "shopping/pandPlanExecution::productListLayout_contents");
		
		//ユーザーIDを取得
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String getName = auth.getName();
		int userId = usersService.select_id(getName);
		
		model.addAttribute("userId",userId);
		
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

		// 各ランクのユーザーランク分布処理
		// amateurユーザー
		double amateurRatio = (double) amateurUser / (double) totalUser;
		System.out.println("amateurRatio" + amateurRatio);
		String amateurRatioStr = String.valueOf(amateurRatio);
		int ratioSize = amateurRatioStr.length();
		if (ratioSize >= 4) {
			String amateurRatioStrCheckNew = amateurRatioStr.substring(0, 4);
			System.out.println("334");
			String amateurRatioStrCheckfour = amateurRatioStrCheckNew.substring(2, 4);
			model.addAttribute("amateurUser", amateurRatioStrCheckfour);

		} else {
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
		if (ratioSizePro >= 4) {
			String proRatioStrCheckNew = proRatioStr.substring(0, 4);
			System.out.println("334");
			String proRatioStrCheckfour = proRatioStrCheckNew.substring(2, 4);
			model.addAttribute("proUser", proRatioStrCheckfour);

		} else {
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
		if (ratioSizeBronze >= 4) {
			String bronzeRatioStrCheckNew = bronzeRatioStr.substring(0, 4);
			System.out.println("334");
			String bronzeRatioStrCheckfour = bronzeRatioStrCheckNew.substring(2, 4);
			model.addAttribute("bronzeUser", bronzeRatioStrCheckfour);

		} else {
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
		if (ratioSizeSilver >= 4) {
			String silverRatioStrCheckNew = silverRatioStr.substring(0, 4);
			System.out.println("334");
			String silverRatioStrCheckfour = silverRatioStrCheckNew.substring(2, 4);
			model.addAttribute("silverUser", silverRatioStrCheckfour);

		} else {
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
		if (ratioSizeGold >= 4) {
			String goldRatioStrCheckNew = goldRatioStr.substring(0, 4);
			System.out.println("334");
			String goldRatioStrCheckfour = goldRatioStrCheckNew.substring(2, 4);
			model.addAttribute("goldUser", goldRatioStrCheckfour);

		} else {
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
		if (ratioSizeDiamond >= 4) {
			String diamondRatioStrCheckNew = diamondRatioStr.substring(0, 4);
			System.out.println("334");
			String diamondRatioStrCheckfour = diamondRatioStrCheckNew.substring(2, 4);
			model.addAttribute("diamondUser", diamondRatioStrCheckfour);

		} else {
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
		if (ratioSizePlatinum >= 4) {
			String platinumRatioStrCheckNew = platinumRatioStr.substring(0, 4);
			System.out.println("334");
			String platinumRatioStrCheckfour = platinumRatioStrCheckNew.substring(2, 4);
			model.addAttribute("platinumUser", platinumRatioStrCheckfour);

		} else {
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
		if (ratioSizeAlien >= 4) {
			String alienRatioStrCheckNew = alienRatioStr.substring(0, 4);
			System.out.println("334");
			String alienRatioStrCheckfour = alienRatioStrCheckNew.substring(2, 4);
			model.addAttribute("alienUser", alienRatioStrCheckfour);

		} else {
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
		if (ratioSizegodFox >= 4) {
			String godFoxRatioStrCheckNew = godFoxRatioStr.substring(0, 4);
			System.out.println("334");
			String godFoxRatioStrCheckfour = godFoxRatioStrCheckNew.substring(2, 4);
			model.addAttribute("godFoxUser", godFoxRatioStrCheckfour);

		} else {
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
		if (ratioSizePremiumGod >= 4) {
			String premiumGodRatioStrCheckNew = premiumGodRatioStr.substring(0, 4);
			System.out.println("334");
			String premiumGodRatioStrCheckfour = premiumGodRatioStrCheckNew.substring(2, 4);
			model.addAttribute("premiumGodUser", premiumGodRatioStrCheckfour);

		} else {
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
		if (ratioSizeInductedIntoTheHalOfFameRank >= 4) {
			String inductedIntoTheHalOfFameRatioStrCheckNew = inductedIntoTheHalOfFameRankRatioStr.substring(0, 4);
			System.out.println("334");
			String inductedIntoTheHalOfFameRatioStrCheckfour = inductedIntoTheHalOfFameRatioStrCheckNew.substring(2, 4);
			model.addAttribute("inductedIntoTheHalOfFameUser", inductedIntoTheHalOfFameRatioStrCheckfour);

		} else {
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
						model.addAttribute("inductedIntoTheHalOfFameUser",
								inductedIntoTheHalOfFameRankRatioStrChecktwoNew);
					}
				}
			} else if (ratioSizeInductedIntoTheHalOfFameRank == 4) {
				String inductedIntoTheHalOfFameRankRatioStrCheckthree = inductedIntoTheHalOfFameRankRatioStr
						.substring(2, 3);
				System.out.println("3333");
				if (inductedIntoTheHalOfFameRankRatioStrCheckthree.equals("0")) {
					System.out.println("44");
					String inductedIntoTheHalOfFameRankRatioStrCheckthreeNew = inductedIntoTheHalOfFameRankRatioStr
							.substring(3, 4);
					model.addAttribute("inductedIntoTheHalOfFameUser",
							inductedIntoTheHalOfFameRankRatioStrCheckthreeNew);
				} else {
					System.out.println("334");
					String inductedIntoTheHalOfFameRankRatioStrCheckfour = inductedIntoTheHalOfFameRankRatioStr
							.substring(2, 4);
					model.addAttribute("inductedIntoTheHalOfFameUser", inductedIntoTheHalOfFameRankRatioStrCheckfour);
				}
			} else {
				String inductedIntoTheHalOfFameRankRatioStrCheckfive = inductedIntoTheHalOfFameRankRatioStr.substring(4,
						5);
				String inductedIntoTheHalOfFameRankRatioStrCheckfiveNew = "0."
						+ inductedIntoTheHalOfFameRankRatioStrCheckfive;
				model.addAttribute("inductedIntoTheHalOfFameRankUser",
						inductedIntoTheHalOfFameRankRatioStrCheckfiveNew);
			}
		}

		return "shopping/productListLayout";
	}

	@GetMapping("/editYourDetail/{id}")
	public String getEditYourDetail(@ModelAttribute UserEditForm from, UsersListForm usersListForm,
			@PathVariable("id") int id, Model model) {
		model.addAttribute("contents", "shopping/editYourDetail::productListLayout_contents");

		UsersDTO usersdto = usersService.userInformationSelectOne(id);// usersテーブルから情報を取得
		Usege_usersDTO usegeusersdto = usegeService.userInformationSelectOne(id);// usege_usersテーブルから情報を取得

		UsersListDTO userslistdto = new UsersListDTO();
		userslistdto.setUserName(usersdto.getUser_name());
		userslistdto.setAddress(usegeusersdto.getAddress());
		usersListForm.setUserName(userslistdto.getUserName());
		usersListForm.setAddress(userslistdto.getAddress());
		model.addAttribute("id", id);

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
	public String getPointRateChange(@ModelAttribute PointRateChangeForm form, Model model) {
		model.addAttribute("contents", "shopping/pointRateChange::productListLayout_contents");

		int pointRate = pointRateService.selectOne(1);// 現在のポイント倍率を取得
		model.addAttribute("pointRate", pointRate);

		return "shopping/productListLayout";

	}

	@PostMapping("/pointRateChange")
	public String postPointRateChange(@ModelAttribute @Validated(GroupOrder.class) PointRateChangeForm form,
			BindingResult bindingResult, Model model) {
		model.addAttribute("contents", "shopping/pointRateChangeFinish::productListLayout_contents");

		if (bindingResult.hasErrors()) {
			return getPointRateChange(form, model);
		}

		session.setAttribute("pointRate", form.getPointRate());
		pointRateService.updateOne(session);// 入力されたポイント倍率に更新

		int pointRate = pointRateService.selectOne(1);// 更新後のポイント倍率を取得
		model.addAttribute("pointRate", pointRate);

		return "shopping/productListLayout";

	}

	@GetMapping("termsOfUse")
	public String gettermsOfUse(Model model) {
		model.addAttribute("contents", "shopping/termsOfUse::productListLayout_contents");

		return "shopping/productListLayout";
	}

	@GetMapping("termsOfUseBeforeLogin")
	public String gettermsOfUseBeforeLogin(Model model) {
		model.addAttribute("contents", "shopping/termsOfUseBeforeLogin::loginLayout_contents");

		return "shopping/loginLayout";
	}

	@GetMapping("privacyPolicy")
	public String getPrivacyPolicy(Model model) {
		model.addAttribute("contents", "shopping/privacyPolicy::productListLayout_contents");

		return "shopping/productListLayout";
	}

	@GetMapping("/privacyPolicyBeforeLogin")
	public String getPrivacyPolicyBeforeLogin(Model model) {
		model.addAttribute("contents", "shopping/privacyPolicyBeforeLogin::loginLayout_contents");

		return "shopping/loginLayout";
	}

	@GetMapping("/productListBeforeLogin")
	public String getProductListBeforeLogin(Model model) {
		model.addAttribute("contents", "shopping/productListBeforeLogin::loginLayout_contents");

		List<PcDataDTO> productList = pcdataService.selectMany();
		model.addAttribute("productList", productList);
		model.addAttribute("productListCheck", "yes");

		return "shopping/loginLayout";
	}

	@GetMapping("/appDetail")
	public String getAppDetail(Model model) {
		model.addAttribute("contents", "shopping/appDetail::loginLayout_contents");

		return "shopping/loginLayout";
	}

	@GetMapping("/inquiryBeforeLogin")
	public String getInquiryBeforeLogin(@ModelAttribute InquiryBeforeLoginForm form, Model model) {
		model.addAttribute("contents", "shopping/inquiryBeforeLogin::loginLayout_contents");

		return "shopping/loginLayout";
	}

	@PostMapping(value = "inquiryBeforeLogin", params = "sending")
	public String postInquirySending(@ModelAttribute @Validated(GroupOrder.class) InquiryBeforeLoginForm form,
			BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response, Model model) {

		model.addAttribute("contents", "shopping/inquiryBeforeLoginFinish::loginLayout_contents");

		if (bindingResult.hasErrors()) {// 入力内容がおかしい場合はtrue
			System.out.println("バリデーションエラー到達");
			return getInquiryBeforeLogin(form, model);// もう一度入力画面に遷移させる
		}

		InquiryDTO inquirydto = new InquiryDTO();

		HttpSession session = request.getSession();
		session.setAttribute("title", form.getTitle());
		session.setAttribute("content", form.getContent());
		session.setAttribute("mailAddress", form.getMailAddress());
		model.addAttribute("title", (String) session.getAttribute("title"));
		model.addAttribute("content", (String) session.getAttribute("content"));
		model.addAttribute("mailAddress", (String) session.getAttribute("mailAddress"));

		inquiryService.beforLoginInquiryInsertOne(inquirydto, session);// dtoとusersテーブルのidでお問い合わせの情報を格納する

		return "shopping/loginLayout";

	}

	@GetMapping("/inquiry")
	public String getInquiry(@ModelAttribute InquiryForm form, Model model) {
		model.addAttribute("contents", "shopping/inquiry::productListLayout_contents");

		return "shopping/productListLayout";
	}

	@PostMapping(value = "inquiry", params = "sending")
	public String postInquirySending(@ModelAttribute @Validated(GroupOrder.class) InquiryForm form,
			BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response, Model model) {

		model.addAttribute("contents", "shopping/inquiryFinish::productListLayout_contents");

		if (bindingResult.hasErrors()) {// 入力内容がおかしい場合はtrue
			System.out.println("バリデーションエラー到達1");
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
		inquiryService.insertOne(inquirydto, select_id);// dtoとusersテーブルのidでお問い合わせの情報を格納する
		int maxId = inquiryService.selectMaxId();
		inquiryReplyService.insertOne(inquiryreplydto, maxId);// 格納したお問い合わせ情報を返信テーブルに渡す

		return "shopping/productListLayout";

	}

	@GetMapping("/inquiryReplay/{id}")
	public String getInquiryReply(@ModelAttribute InquiryReplyForm form, @PathVariable("id") int inquiryId,
			Model model) {
		model.addAttribute("contents", "shopping/inquiryReply::productListLayout_contents");
		InquiryDTO inquirydto = inquiryService.selectOne(inquiryId);// inquiryテーブルのIdをもとにinquiryテーブルの情報を取得
		model.addAttribute("inquiryList", inquirydto);
		model.addAttribute("inquiryId", inquirydto.getId());
		model.addAttribute("inquiryId", inquiryId);

		return "shopping/productListLayout";
	}

	@GetMapping("inquiryUserDeletion/{id}")
	public String getInquiryUserDeletion(@ModelAttribute InquiryForm form, @PathVariable("id") int inquiryId,
			Model model) {
		model.addAttribute("contents", "shopping/inquiryUserDeletion::productListLayout_contents");

		InquiryDTO inquirydto = inquiryService.selectOne(inquiryId);// 問い合わせ情報を取得

		InquiryReplyDTO inquiryreplydto = inquiryReplyService.selectOne(inquiryId);// 問い合わせの返信情報を取得

		model.addAttribute("inquiryId", inquiryId);
		model.addAttribute("inquiryDTO", inquirydto);
		model.addAttribute("inquiryReplyDTO", inquiryreplydto);

		return "shopping/productListLayout";
	}

	@PostMapping("inquiryUserDeletion")
	public String postInquiryUserDeletion(@ModelAttribute InquiryForm form, @RequestParam("inquiryId") int inquiryId,
			Model model) {
		model.addAttribute("contents", "shopping/inquiryUserDeletion::productListLayout_contents");

		inquiryService.userDeletionOne(inquiryId);// 問い合わせ情報を削除

		inquiryReplyService.deleteOne(inquiryId);// 問い合わせの返信情報を削除

		return getContactReply(form, model);

	}

	@GetMapping("/inquiryDetail/{id}")
	public String getInquiryDetail(@ModelAttribute InquiryForm form, @PathVariable("id") int inquiryId, Model model) {
		model.addAttribute("contents", "shopping/inquiryDetail::productListLayout_contents");

		try {
			InquiryDTO inquirydto = inquiryService.selectOne(inquiryId);// inquiryテーブルのIdをもとにinquiryテーブルの情報を取得
			model.addAttribute("id", inquirydto.getId());
			model.addAttribute("inquiryList", inquirydto);
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			InquiryDTO inquiryBeforeLoginDTO = inquiryService.beforeLoginSelectOne(inquiryId);
			inquiryBeforeLoginDTO.setUserName("ログイン前ユーザーから");
			model.addAttribute("id", inquiryBeforeLoginDTO.getId());
			model.addAttribute("inquiryList", inquiryBeforeLoginDTO);
		}

		return "shopping/productListLayout";
	}

	@PostMapping("/inquiryDetail")
	public String postInquiryDetail(@ModelAttribute InquiryForm form, @RequestParam("id") int id, Model model) {
		System.out.println("inquiryDetail到達");
		inquiryService.deleteOne(id);// inquiryテーブルのIdをもとにinquiryテーブルの情報を削除
		inquiryReplyService.deleteOne(id);// inquiryテーブルのIdをもとにinquiryReplyテーブルの情報を削除

		return getAdministrator(form, model);
	}

	@PostMapping(value = "inquiry", params = "return")
	public String postInquiryReturn(@ModelAttribute @Validated(GroupOrder.class) InquiryReplyForm inquiryreplyform,
			BindingResult bindingResult, InquiryForm form, @RequestParam("id") int id, HttpServletRequest request,
			HttpServletResponse response, Model model) {

		if (bindingResult.hasErrors()) {
			System.out.println("id" + id);
			return getInquiryReply(inquiryreplyform, id, model);
		}

		InquiryReplyDTO inquiryreplydto = new InquiryReplyDTO();
		inquiryreplydto.setInquiryId(id);
		inquiryreplydto.setTitle(form.getTitle());// 問い合わせの返信タイトルをdtoに入れる
		inquiryreplydto.setContent(form.getContent());// 問い合わせの返信内容をdtoに入れる

		HttpSession session = request.getSession();
		session.setAttribute("id", id);
		session.setAttribute("title", form.getTitle());
		session.setAttribute("content", form.getContent());

		inquiryReplyService.replyUpdateOne(session, inquiryreplydto);// dtoの情報をinquiry_replyテーブルに格納

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
		System.out.println("inquirydtolist" + inquirydtolist);
		List<InquiryDTO> inquirynewdtolist = new ArrayList<>();

		for (int i = 0; inquirydtolist.size() > i; i++) {
			InquiryDTO inquirydto = inquirydtolist.get(i);
			int inquiryId = inquirydto.getId();
			InquiryReplyDTO inquiryreplydto = inquiryReplyService.selectOne(inquiryId);
			inquirydto.setReplyTitle(inquiryreplydto.getTitle());// inquiryテーブルから取得した問い合わせ情報に返信情報を加える

			inquirynewdtolist.add(inquirydto);
		}

		List<InquiryDTO> inquiryBeforeLoginList = inquiryService.beforeLoginSelectMany("-1");
		for (int y = 0; inquiryBeforeLoginList.size() > y; y++) {
			InquiryDTO inquirydtoNext = inquiryBeforeLoginList.get(y);
			int userId = inquirydtoNext.getUser_id();
			String userIdNew = String.valueOf(userId);
			inquirydtoNext.setUserName(userIdNew);
			inquirynewdtolist.add(inquirydtoNext);
		}

		model.addAttribute("inquiryList", inquirynewdtolist);

		return "shopping/productListLayout";
	}

	@GetMapping("/productAdd")
	public String getProductAdd(@ModelAttribute PcDataForm form, Model model) {
		model.addAttribute("contents", "shopping/productAdd::productListLayout_contents");
		return "shopping/productListLayout";
	}

	@PostMapping("/productAdd")
	public String postProductAdd(@ModelAttribute @Validated(GroupOrder.class) PcDataForm form,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

		if (bindingResult.hasErrors()) {
			System.out.println("バリデーションエラー到達");
			return getProductAdd(form, model);
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
			return getProductAdd(form, model);
		}

		if (!imgCheck2.equals(jpg)) {
			model.addAttribute("imgResult2", "商品画像2はJPEG形式（最後が「.jpg」のもの）で入力してください");
			return getProductAdd(form, model);
		}

		if (!imgCheck3.equals(jpg)) {
			model.addAttribute("imgResult3", "商品画像3はJPEG形式（最後が「.jpg」のもの）で入力してください");
			return getProductAdd(form, model);
		}

		if (!img1.startsWith("https://")) {
			model.addAttribute("imgResult1", "商品画像1はhttps://から始まる画像アドレスを入力してください");
			return getProductAdd(form, model);

		}

		if (!img2.startsWith("https://")) {
			model.addAttribute("imgResult1", "商品画像2はhttps://から始まる画像アドレスを入力してください");
			return getProductAdd(form, model);

		}

		if (!img2.startsWith("https://")) {
			model.addAttribute("imgResult1", "商品画像3はhttps://から始まる画像アドレスを入力してください");
			return getProductAdd(form, model);

		}

		// バリデーションエラーにならなければ入力した情報をdtoにいれる
		PcDataDTO pcdatadto = new PcDataDTO();
		pcdatadto.setCompany(form.getCompany());
		pcdatadto.setOs(form.getOs());
		pcdatadto.setPc_name(form.getPc_name());
		int pcSize = Integer.parseInt(form.getPc_size());
		pcdatadto.setPc_size(pcSize);
		int pcdataPrice = Integer.parseInt(form.getPrice());
		pcdatadto.setPrice(pcdataPrice);
		pcdatadto.setDetail(form.getDetail());
		int productStock = Integer.parseInt(form.getProduct_stock());
		pcdatadto.setProduct_stock(productStock);
		int cost = Integer.parseInt(form.getCost());
		pcdatadto.setCost(cost);
		pcdatadto.setPcImg(form.getPcImg());
		pcdatadto.setPcImg2(form.getPcImg2());
		pcdatadto.setPcImg3(form.getPcImg3());

		int result = pcdataService.insertCheckSelectOne(pcdatadto);// dtoの情報をもとに、pcdateテーブルの情報と比較し、同じ商品がないか確かめる
		if (result < 1) {// 同じ商品がなければdtoの情報をpdcataテーブルに格納する
			pcdataService.insertOne(pcdatadto);
		}

		ProductListSearchForm productlistsearchform = new ProductListSearchForm();
		return getProductList(form, productlistsearchform, redirectAttributes, model);
	}

	@GetMapping("/productEdit/{id}")
	public String getProductEdit(@ModelAttribute PcDataForm form, @PathVariable("id") int productId, Model model) {
		model.addAttribute("contents", "shopping/productEdit::productListLayout_contents");

		// 編集する商品のデータを取得
		PcDataDTO pcdatadto = pcdataService.pcdataOne(productId);

		form.setId(pcdatadto.getId());
		form.setCompany(pcdatadto.getCompany());
		form.setOs(pcdatadto.getOs());
		form.setPc_name(pcdatadto.getPc_name());
		String pcSize = String.valueOf(pcdatadto.getPc_size());
		form.setPc_size(pcSize);
		String price = String.valueOf(pcdatadto.getPrice());
		form.setPrice(price);
		form.setDetail(pcdatadto.getDetail());
		form.setPcImg(pcdatadto.getPcImg());
		form.setPcImg2(pcdatadto.getPcImg2());
		form.setPcImg3(pcdatadto.getPcImg3());
		String productStock = String.valueOf(pcdatadto.getProduct_stock());
		form.setProduct_stock(productStock);

		// 編集するデータを画面表示するために渡す
		model.addAttribute("pcDataForm", form);
		model.addAttribute("productId", form.getId());

		return "shopping/productListLayout";
	}

	@PostMapping("/productEdit")
	public String postProductEdit(@ModelAttribute @Validated(GroupOrder.class) PcDataForm form,
			BindingResult bindingResult, RedirectAttributes redirectattributes,
			@RequestParam("productId") int productId, Model model) {
		model.addAttribute("contents", "shopping/productDetail::productListLayout_contents");

		if (bindingResult.hasErrors()) {
			getProductEdit(form, productId, model);
		}

		// 修正されたproductの内容にデータを更新する
		pcdataService.productEditOne(productId, form);

		ProductListSearchForm productlistsearchform = new ProductListSearchForm();
		return getProductList(form, productlistsearchform, redirectattributes, model);
	}

	@GetMapping("/news")
	public String getNews(@ModelAttribute NewsForm form, Model model) {
		model.addAttribute("contents", "shopping/news::productListLayout_contents");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String getName = auth.getName();
		int userId = usersService.select_id(getName);

		String userRole = usersService.selectRole(userId);// おしらせ管理は管理者のみ可能のためroleチェック
		model.addAttribute("role", userRole);

		List<NewsDTO> newsdtoList = newsService.selectMany();// おしらせ情報をすべて取得

		model.addAttribute("newsdtoList", newsdtoList);

		return "shopping/productListLayout";
	}

	@GetMapping("/newsDetail/{id}")
	public String getNewsDetail(@ModelAttribute NewsForm form, @PathVariable("id") int newsId, Model model) {
		model.addAttribute("contents", "shopping/newsDetail::productListLayout_contents");

		NewsDTO newsDtoOne = newsService.selectOne(newsId);// newsテーブルIDをもとにおしらせ情報を取得
		model.addAttribute("newsdto", newsDtoOne);

		return "shopping/productListLayout";

	}

	@GetMapping("/newsDelete/{id}")
	public String getNewsDelete(@ModelAttribute NewsForm form, @PathVariable("id") int newsId,
			RedirectAttributes redirectAttributes, Model model) {
		model.addAttribute("contents", "shopping/news::productListLayout_contents");

		newsService.deleteOne(newsId);// newsテーブルIDをもとにおしらせ情報を削除

		return "redirect:/news";
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
		// 入力されたおしらせ内容をdtoに格納
		newsdto.setTitle(form.getTitle());
		newsdto.setContent(form.getContent());

		newsService.insertOne(newsdto);// 入力されたされたお知らせ内容をnewsテーブルに格納

		return getNews(form, model);
	}

	@GetMapping("/deliveryCheck")
	public String getDeliveryCheck(@ModelAttribute PcDataForm form, Model model) {
		model.addAttribute("contents", "shopping/deliveryCheck::productListLayout_contents");
		// 商品情報リストを取得 ※発送したものは取得してこないようにする
		List<PurchaseDTO> purchasedtoList = purchaseService.deliverySelect();

		List<PurchaseDTO> allPurchaseList = new ArrayList<>();
		PurchaseDTO customList;
		// 購入商品を一つづつ回して値を受け取る
		for (int i = 0; purchasedtoList.size() > i; i++) {
			PurchaseDTO purchasedtoAdd = new PurchaseDTO();

			PurchaseDTO purchaseOne = purchasedtoList.get(i);
			purchasedtoAdd.setId(purchaseOne.getId());// カスタム情報取得に使用
			purchasedtoAdd.setUser_id(purchaseOne.getUser_id());
			purchasedtoAdd.setPurchaseId(purchaseOne.getPurchaseId());
			System.out.println("purchaseId" + purchasedtoAdd.getPurchaseId());
			purchasedtoAdd.setPurchase_date(purchaseOne.getPurchase_date());
			purchasedtoAdd.setCancelCheck(purchaseOne.getCancelCheck());
			purchasedtoAdd.setCouponId(purchaseOne.getCouponId());
			purchasedtoAdd.setPcName(purchaseOne.getPcName());
			purchasedtoAdd.setPrice(purchaseOne.getPrice());
			purchasedtoAdd.setPcImg(purchaseOne.getPcImg());
			purchasedtoAdd.setProduct_count(purchaseOne.getProduct_count());
			purchasedtoAdd.setPurchaseCheck(purchaseOne.getPurchaseCheck());
			purchasedtoAdd.setMenberCouponCheck(purchaseOne.getMenberCouponCheck());
			purchasedtoAdd.setPointUse(purchaseOne.getPointUse());
			purchasedtoAdd.setDeliveryCheck(purchaseOne.getDeliveryCheck());
			purchasedtoAdd.setUserName(purchaseOne.getUserName());
			purchasedtoAdd.setAddress(purchaseOne.getAddress());

			// 購入商品ごとのカスタム情報も取り出す

			int productId = purchasedtoAdd.getId();
			String nullCheck = "null";
			int getCustomId = customService.selectPurchaseCheck(purchasedtoAdd.getUser_id(), productId,
					purchasedtoAdd.getPurchaseCheck(), nullCheck);
			customList = customService.selectMany(getCustomId);

			purchasedtoAdd.setMemory(customList.getMemory());
			purchasedtoAdd.setHardDisc(customList.getHardDisc());
			purchasedtoAdd.setCpu(customList.getCpu());
			purchasedtoAdd.setCustomPrice(customList.getCustomPrice());
			purchasedtoAdd.setTotalPrice(
					purchaseOne.getProduct_count() * (customList.getCustomPrice() + purchaseOne.getPrice()));

			if (purchasedtoAdd.getMenberCouponCheck().equals("会員クーポン使用")) {

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

			purchasedtoAdd.setTotalPrice((purchasedtoAdd.getTotalPrice() - purchasedtoAdd.getPointUse()));

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

				purchasedtoAdd.setCancelResult("true");
			} else {
				purchasedtoAdd.setCancelResult("false");
			}

			CancelDTO canceldto = cancelService.selectCancelCheck(purchasedtoAdd.getPurchaseId());

			if (canceldto.getCancelCheck() != null) {

				purchasedtoAdd.setCancelResult("true");
			}

			allPurchaseList.add(purchasedtoAdd);

		}

		System.out.println("allPurchaseList" + allPurchaseList);
		model.addAttribute("purchaseList", allPurchaseList);
		model.addAttribute("confirmationPending", "返品商品確認待ち");
		model.addAttribute("inTransaction", "キャンセル取引中");
		return "shopping/productListLayout";
	}

	@GetMapping("/deliveryProcedureOK/{id}/{userId}")
	public String postDeliveryCheck(@ModelAttribute @PathVariable("id") int purchaseId,@PathVariable("userId") int userId, Model model) throws MessagingException {

		purchaseService.deliveryProcedureCheckInsertOne(purchaseId);
		
		String mailAddress = usersService.mailAddressSelectOne(userId);
		
		mailService.deliverySendMail(mailAddress);
		
		PcDataForm form = new PcDataForm();
		return getDeliveryCheck(form, model);
	}

	@GetMapping("/cancelCheck")
	public String getCancelCheck(Model model) {
		model.addAttribute("contents", "shopping/cancelCheck::productListLayout_contents");

		// 商品情報リストを取得 ※キャンセル取引中のものだけとる
		List<PurchaseDTO> purchasedtoList = purchaseService.cancelCheckSelectMany();

		List<PurchaseDTO> allPurchaseList = new ArrayList<>();
		PurchaseDTO customList;
		// 購入商品を一つづつ回して値を受け取る
		for (int i = 0; purchasedtoList.size() > i; i++) {
			PurchaseDTO purchasedtoAdd = new PurchaseDTO();

			PurchaseDTO purchaseOne = purchasedtoList.get(i);
			purchasedtoAdd.setId(purchaseOne.getId());// カスタム情報取得に使用
			purchasedtoAdd.setUser_id(purchaseOne.getUser_id());
			purchasedtoAdd.setPurchaseId(purchaseOne.getPurchaseId());
			System.out.println("purchaseId" + purchasedtoAdd.getPurchaseId());
			purchasedtoAdd.setPurchase_date(purchaseOne.getPurchase_date());
			purchasedtoAdd.setCancelCheck(purchaseOne.getCancelCheck());
			purchasedtoAdd.setCouponId(purchaseOne.getCouponId());
			purchasedtoAdd.setPcName(purchaseOne.getPcName());
			purchasedtoAdd.setPrice(purchaseOne.getPrice());
			purchasedtoAdd.setPcImg(purchaseOne.getPcImg());
			purchasedtoAdd.setProduct_count(purchaseOne.getProduct_count());
			purchasedtoAdd.setPurchaseCheck(purchaseOne.getPurchaseCheck());
			purchasedtoAdd.setPointUse(purchaseOne.getPointUse());
			purchasedtoAdd.setMenberCouponCheck(purchaseOne.getMenberCouponCheck());
			purchasedtoAdd.setDeliveryCheck(purchaseOne.getDeliveryCheck());
			purchasedtoAdd.setUserName(purchaseOne.getUserName());
			purchasedtoAdd.setAddress(purchaseOne.getAddress());

			// 購入商品ごとのカスタム情報も取り出す

			int productId = purchasedtoAdd.getId();
			String nullCheck = "null";
			int getCustomId = customService.selectPurchaseCheck(purchasedtoAdd.getUser_id(), productId,
					purchasedtoAdd.getPurchaseCheck(), nullCheck);
			customList = customService.selectMany(getCustomId);

			purchasedtoAdd.setMemory(customList.getMemory());
			purchasedtoAdd.setHardDisc(customList.getHardDisc());
			purchasedtoAdd.setCpu(customList.getCpu());
			purchasedtoAdd.setCustomPrice(customList.getCustomPrice());
			purchasedtoAdd.setTotalPrice(
					purchaseOne.getProduct_count() * (customList.getCustomPrice() + purchaseOne.getPrice()));

			if (purchasedtoAdd.getMenberCouponCheck().equals("会員クーポン使用")) {

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

			purchasedtoAdd.setTotalPrice((purchasedtoAdd.getTotalPrice() - purchasedtoAdd.getPointUse()));

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

				purchasedtoAdd.setCancelResult("true");
			} else {
				purchasedtoAdd.setCancelResult("false");
			}

			CancelDTO canceldto = cancelService.selectCancelCheck(purchasedtoAdd.getPurchaseId());

			if (canceldto.getCancelCheck() != null) {

				purchasedtoAdd.setCancelResult("true");
			}

			allPurchaseList.add(purchasedtoAdd);

		}

		System.out.println("allPurchaseList" + allPurchaseList);
		model.addAttribute("purchaseList", allPurchaseList);
		model.addAttribute("confirmationPending", "返品商品確認待ち");
		model.addAttribute("inTransaction", "キャンセル取引中");
		return "shopping/productListLayout";

	}

	@GetMapping("/userPurchaseHistory/{id}")
	public String getUserPurchaseHistory(@ModelAttribute PcDataForm form, @PathVariable("id") int userId, Model model) {
		model.addAttribute("contents", "shopping/userPurchaseHistory::productListLayout_contents");
		// 購入商品情報リスト取得
		List<PurchaseDTO> purchasedtoList = purchaseService.selectHistory(userId);

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
			String nullCheck = "null";
			int getCustomId = customService.selectPurchaseCheck(userId, productId, purchasedtoAdd.getPurchaseCheck(),
					nullCheck);
			customList = customService.selectMany(getCustomId);

			purchasedtoAdd.setMemory(customList.getMemory());
			purchasedtoAdd.setHardDisc(customList.getHardDisc());
			purchasedtoAdd.setCpu(customList.getCpu());
			purchasedtoAdd.setCustomPrice(customList.getCustomPrice());
			purchasedtoAdd.setTotalPrice(
					purchaseOne.getProduct_count() * (customList.getCustomPrice() + purchaseOne.getPrice()));

			if (purchasedtoAdd.getMenberCouponCheck().equals("会員クーポン使用")) {

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

				purchasedtoAdd.setCancelResult("true");
			} else {
				purchasedtoAdd.setCancelResult("false");
			}

			CancelDTO canceldto = cancelService.selectCancelCheck(purchasedtoAdd.getPurchaseId());

			if (canceldto.getCancelCheck() != null) {

				purchasedtoAdd.setCancelResult("true");
			}

			allPurchaseList.add(purchasedtoAdd);

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
		purchasedto.setPointUse(purchasedtoList.getPointUse());
		purchasedto.setCouponId(purchasedtoList.getCouponId());
		purchasedto.setMenberCouponCheck(purchasedtoList.getMenberCouponCheck());
		purchasedto.setPurchaseCheck(purchasedtoList.getPurchaseCheck());

		Integer reviewId = reviewService.selectManyId(purchasedto.getId());// 購入した商品がすでに口コミ投稿されているかチェック
		model.addAttribute("reviewAdd", "no");
		if (reviewId > 0) {
			model.addAttribute("reviewAdd", "yes");
			return "shopping/productListLayout";
		}

		String nullCheck = "null";
		int getCustomId = customService.selectPurchaseCheck(select_id, purchasedtoList.getProduct_id(), // 購入情報からcustomテーブルIDを取得
				purchasedtoList.getPurchaseCheck(), nullCheck);

		PurchaseDTO customList = customService.selectMany(getCustomId);// 取得したcustomテーブルIDでカスタム情報を取得

		purchasedto.setMemory(customList.getMemory());
		purchasedto.setHardDisc(customList.getHardDisc());
		purchasedto.setCpu(customList.getCpu());
		purchasedto.setCustomPrice(customList.getCustomPrice());
		purchasedto.setTotalPrice(
				(purchasedto.getProduct_count() * (purchasedto.getPrice() + purchasedto.getCustomPrice())));// 商品購入数 *
																																		// 商品価格 *
																																		// カスタム値段

		// クーポンを使用しているならtotalPriceから引く
		if (purchasedto.getMenberCouponCheck().equals("会員クーポン使用")) {
			System.out.println("クーポン使用！");
			int totalPrice = purchasedto.getTotalPrice();
			MenberCouponDTO menbercoupondto = menberCouponService.selectOne(purchasedto.getCouponId());// 会員DBからとる
			int disCount = menbercoupondto.getDiscount();// 割引率(%)
			if (disCount >= 10) {
				double disCountNew = Double.valueOf("0." + disCount);
				double disCountPriceNew = totalPrice * disCountNew;// 割引価格
				int totalPriceAll = (int) (totalPrice - disCountPriceNew);
				System.out.println("totalPriceAll" + totalPriceAll);
				System.out.println("purchasedtoAdd.getPointUse" + purchasedto.getPointUse());
				purchasedto.setTotalPrice(totalPriceAll);
			} else {
				double disCountNew = Double.valueOf("0.0" + disCount);
				double disCountPriceNew = totalPrice * disCountNew;// 割引価格
				int totalPriceAll = (int) (totalPrice - disCountPriceNew);
				System.out.println("totalPriceAll" + totalPriceAll);
				System.out.println("purchasedtoAdd.getPointUse" + purchasedto.getPointUse());
				purchasedto.setTotalPrice(totalPriceAll);
			}
		} else {

			if (purchasedto.getCouponId() > 0) {
				System.out.println("クーポン使用！");
				int totalPrice = purchasedto.getTotalPrice();
				CouponDTO coupondto = couponService.selectOne(purchasedto.getCouponId());
				int disCount = coupondto.getDiscount();// 割引率(%)
				if (disCount >= 10) {
					double disCountNew = Double.valueOf("0." + disCount);
					double disCountPriceNew = totalPrice * disCountNew;// 割引価格
					System.out.println("totalPrice" + totalPrice);
					int totalPriceAll = (int) (totalPrice - disCountPriceNew);
					System.out.println("totalPriceAll" + totalPriceAll);
					purchasedto.setTotalPrice(totalPriceAll);
					System.out.println("setTotalPrice" + purchasedto.getTotalPrice());
				} else {
					double disCountNew = Double.valueOf("0.0" + disCount);
					double disCountPriceNew = totalPrice * disCountNew;// 割引価格
					int totalPriceAll = (int) (totalPrice - disCountPriceNew);
					purchasedto.setTotalPrice(totalPriceAll);
				}
			}
		}
		// ポイントを利用しているのならtotalPriceから引く
		model.addAttribute("totalPrice", (purchasedto.getTotalPrice() - purchasedto.getPointUse()));

		model.addAttribute("purchaseId", id);

		model.addAttribute("purchaseList", purchasedto);

		return "shopping/productListLayout";

	}

	@PostMapping("/reviewAdd")
	public String postReviewAdd(@ModelAttribute @Validated(GroupOrder.class) ReviewForm form,
			BindingResult bindingResult, @RequestParam("pcDataId") int pcDataId,
			@RequestParam("purchaseId") int purchaseId, RedirectAttributes redirectAttributes, Model model) {

		if (bindingResult.hasErrors()) {
			return getReviewAdd(form, purchaseId, model);
		}

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String getName = auth.getName();

		int selectId = usersService.select_id(getName);

		ReviewDTO reviewdto = new ReviewDTO();

		reviewService.reviewInsertOne(reviewdto, selectId, pcDataId, form.getTitle(), form.getContent(), // 口コミ内容をreviewテーブルに格納
				form.getRating(), purchaseId);

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
		purchasedto.setPointUse(purchasedtoList.getPointUse());
		purchasedto.setCouponId(purchasedtoList.getCouponId());
		purchasedto.setMenberCouponCheck(purchasedtoList.getMenberCouponCheck());
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

		purchasedto.setTotalPrice(
				(purchasedto.getProduct_count() * (purchasedto.getPrice() + purchasedto.getCustomPrice())));

		if (purchasedto.getMenberCouponCheck().equals("会員クーポン使用")) {
			System.out.println("クーポン使用！");
			int totalPrice = purchasedto.getTotalPrice();
			MenberCouponDTO menbercoupondto = menberCouponService.selectOne(purchasedto.getCouponId());// 会員DBからとる
			int disCount = menbercoupondto.getDiscount();// 割引率(%)
			if (disCount >= 10) {
				double disCountNew = Double.valueOf("0." + disCount);
				double disCountPriceNew = totalPrice * disCountNew;// 割引価格
				int totalPriceAll = (int) (totalPrice - disCountPriceNew);
				System.out.println("totalPriceAll" + totalPriceAll);
				System.out.println("purchasedtoAdd.getPointUse" + purchasedto.getPointUse());
				purchasedto.setTotalPrice(totalPriceAll);
			} else {
				double disCountNew = Double.valueOf("0.0" + disCount);
				double disCountPriceNew = totalPrice * disCountNew;// 割引価格
				int totalPriceAll = (int) (totalPrice - disCountPriceNew);
				System.out.println("totalPriceAll" + totalPriceAll);
				System.out.println("purchasedtoAdd.getPointUse" + purchasedto.getPointUse());
				purchasedto.setTotalPrice(totalPriceAll);
			}
		} else {

			if (purchasedto.getCouponId() > 0) {
				System.out.println("クーポン使用！");
				int totalPrice = purchasedto.getTotalPrice();
				CouponDTO coupondto = couponService.selectOne(purchasedto.getCouponId());
				int disCount = coupondto.getDiscount();// 割引率(%)
				if (disCount >= 10) {
					double disCountNew = Double.valueOf("0." + disCount);
					double disCountPriceNew = totalPrice * disCountNew;// 割引価格
					System.out.println("totalPrice" + totalPrice);
					int totalPriceAll = (int) (totalPrice - disCountPriceNew);
					System.out.println("totalPriceAll" + totalPriceAll);
					purchasedto.setTotalPrice(totalPriceAll);
					System.out.println("setTotalPrice" + purchasedto.getTotalPrice());
				} else {
					double disCountNew = Double.valueOf("0.0" + disCount);
					double disCountPriceNew = totalPrice * disCountNew;// 割引価格
					int totalPriceAll = (int) (totalPrice - disCountPriceNew);
					purchasedto.setTotalPrice(totalPriceAll);
				}
			}
		}

		model.addAttribute("purchaseId", purchaseId);

		model.addAttribute("purchaseList", purchasedto);
		model.addAttribute("totalPrice", (purchasedto.getTotalPrice() - purchasedto.getPointUse()));
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
		purchasedto.setPointUse(purchasedtoList.getPointUse());
		purchasedto.setCouponId(purchasedtoList.getCouponId());
		purchasedto.setMenberCouponCheck(purchasedtoList.getMenberCouponCheck());
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

		purchasedto.setTotalPrice(
				(purchasedto.getProduct_count() * (purchasedto.getPrice() + purchasedto.getCustomPrice())));

		if (purchasedto.getMenberCouponCheck().equals("会員クーポン使用")) {
			System.out.println("クーポン使用！");
			int totalPrice = purchasedto.getTotalPrice();
			MenberCouponDTO menbercoupondto = menberCouponService.selectOne(purchasedto.getCouponId());// 会員DBからとる
			int disCount = menbercoupondto.getDiscount();// 割引率(%)
			if (disCount >= 10) {
				double disCountNew = Double.valueOf("0." + disCount);
				double disCountPriceNew = totalPrice * disCountNew;// 割引価格
				int totalPriceAll = (int) (totalPrice - disCountPriceNew);
				System.out.println("totalPriceAll" + totalPriceAll);
				System.out.println("purchasedtoAdd.getPointUse" + purchasedto.getPointUse());
				purchasedto.setTotalPrice(totalPriceAll);
			} else {
				double disCountNew = Double.valueOf("0.0" + disCount);
				double disCountPriceNew = totalPrice * disCountNew;// 割引価格
				int totalPriceAll = (int) (totalPrice - disCountPriceNew);
				System.out.println("totalPriceAll" + totalPriceAll);
				System.out.println("purchasedtoAdd.getPointUse" + purchasedto.getPointUse());
				purchasedto.setTotalPrice(totalPriceAll);
			}
		} else {

			if (purchasedto.getCouponId() > 0) {
				System.out.println("クーポン使用！");
				int totalPrice = purchasedto.getTotalPrice();
				CouponDTO coupondto = couponService.selectOne(purchasedto.getCouponId());
				int disCount = coupondto.getDiscount();// 割引率(%)
				if (disCount >= 10) {
					double disCountNew = Double.valueOf("0." + disCount);
					double disCountPriceNew = totalPrice * disCountNew;// 割引価格
					System.out.println("totalPrice" + totalPrice);
					int totalPriceAll = (int) (totalPrice - disCountPriceNew);
					System.out.println("totalPriceAll" + totalPriceAll);
					purchasedto.setTotalPrice(totalPriceAll);
					System.out.println("setTotalPrice" + purchasedto.getTotalPrice());
				} else {
					double disCountNew = Double.valueOf("0.0" + disCount);
					double disCountPriceNew = totalPrice * disCountNew;// 割引価格
					int totalPriceAll = (int) (totalPrice - disCountPriceNew);
					purchasedto.setTotalPrice(totalPriceAll);
				}
			}
		}
		model.addAttribute("totalPrice", purchasedto.getTotalPrice() - purchasedto.getPointUse());

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
		purchasedto.setPointUse(purchasedtoList.getPointUse());
		purchasedto.setCouponId(purchasedtoList.getCouponId());
		purchasedto.setMenberCouponCheck(purchasedtoList.getMenberCouponCheck());
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

		purchasedto.setTotalPrice(
				(purchasedto.getProduct_count() * (purchasedto.getPrice() + purchasedto.getCustomPrice())));

		if (purchasedto.getMenberCouponCheck().equals("会員クーポン使用")) {
			System.out.println("クーポン使用！");
			int totalPrice = purchasedto.getTotalPrice();
			MenberCouponDTO menbercoupondto = menberCouponService.selectOne(purchasedto.getCouponId());// 会員DBからとる
			int disCount = menbercoupondto.getDiscount();// 割引率(%)
			if (disCount >= 10) {
				double disCountNew = Double.valueOf("0." + disCount);
				double disCountPriceNew = totalPrice * disCountNew;// 割引価格
				int totalPriceAll = (int) (totalPrice - disCountPriceNew);
				System.out.println("totalPriceAll" + totalPriceAll);
				System.out.println("purchasedtoAdd.getPointUse" + purchasedto.getPointUse());
				purchasedto.setTotalPrice(totalPriceAll);
			} else {
				double disCountNew = Double.valueOf("0.0" + disCount);
				double disCountPriceNew = totalPrice * disCountNew;// 割引価格
				int totalPriceAll = (int) (totalPrice - disCountPriceNew);
				System.out.println("totalPriceAll" + totalPriceAll);
				System.out.println("purchasedtoAdd.getPointUse" + purchasedto.getPointUse());
				purchasedto.setTotalPrice(totalPriceAll);
			}
		} else {

			if (purchasedto.getCouponId() > 0) {
				System.out.println("クーポン使用！");
				int totalPrice = purchasedto.getTotalPrice();
				CouponDTO coupondto = couponService.selectOne(purchasedto.getCouponId());
				int disCount = coupondto.getDiscount();// 割引率(%)
				if (disCount >= 10) {
					double disCountNew = Double.valueOf("0." + disCount);
					double disCountPriceNew = totalPrice * disCountNew;// 割引価格
					System.out.println("totalPrice" + totalPrice);
					int totalPriceAll = (int) (totalPrice - disCountPriceNew);
					System.out.println("totalPriceAll" + totalPriceAll);
					purchasedto.setTotalPrice(totalPriceAll);
					System.out.println("setTotalPrice" + purchasedto.getTotalPrice());
				} else {
					double disCountNew = Double.valueOf("0.0" + disCount);
					double disCountPriceNew = totalPrice * disCountNew;// 割引価格
					int totalPriceAll = (int) (totalPrice - disCountPriceNew);
					purchasedto.setTotalPrice(totalPriceAll);
				}
			}
		}
		model.addAttribute("totalPrice", purchasedto.getTotalPrice() - purchasedto.getPointUse());

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
		purchasedto.setPointUse(purchasedtoList.getPointUse());
		purchasedto.setCouponId(purchasedtoList.getCouponId());
		purchasedto.setMenberCouponCheck(purchasedtoList.getMenberCouponCheck());

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

		purchasedto.setTotalPrice(
				(purchasedto.getProduct_count() * (purchasedto.getPrice() + purchasedto.getCustomPrice())));

		if (purchasedto.getMenberCouponCheck().equals("会員クーポン使用")) {
			System.out.println("クーポン使用！");
			int totalPrice = purchasedto.getTotalPrice();
			MenberCouponDTO menbercoupondto = menberCouponService.selectOne(purchasedto.getCouponId());// 会員DBからとる
			int disCount = menbercoupondto.getDiscount();// 割引率(%)
			if (disCount >= 10) {
				double disCountNew = Double.valueOf("0." + disCount);
				double disCountPriceNew = totalPrice * disCountNew;// 割引価格
				int totalPriceAll = (int) (totalPrice - disCountPriceNew);
				System.out.println("totalPriceAll" + totalPriceAll);
				System.out.println("purchasedtoAdd.getPointUse" + purchasedto.getPointUse());
				purchasedto.setTotalPrice(totalPriceAll);
			} else {
				double disCountNew = Double.valueOf("0.0" + disCount);
				double disCountPriceNew = totalPrice * disCountNew;// 割引価格
				int totalPriceAll = (int) (totalPrice - disCountPriceNew);
				System.out.println("totalPriceAll" + totalPriceAll);
				System.out.println("purchasedtoAdd.getPointUse" + purchasedto.getPointUse());
				purchasedto.setTotalPrice(totalPriceAll);
			}
		} else {

			if (purchasedto.getCouponId() > 0) {
				System.out.println("クーポン使用！");
				int totalPrice = purchasedto.getTotalPrice();
				CouponDTO coupondto = couponService.selectOne(purchasedto.getCouponId());
				int disCount = coupondto.getDiscount();// 割引率(%)
				if (disCount >= 10) {
					double disCountNew = Double.valueOf("0." + disCount);
					double disCountPriceNew = totalPrice * disCountNew;// 割引価格
					System.out.println("totalPrice" + totalPrice);
					int totalPriceAll = (int) (totalPrice - disCountPriceNew);
					System.out.println("totalPriceAll" + totalPriceAll);
					purchasedto.setTotalPrice(totalPriceAll);
					System.out.println("setTotalPrice" + purchasedto.getTotalPrice());
				} else {
					double disCountNew = Double.valueOf("0.0" + disCount);
					double disCountPriceNew = totalPrice * disCountNew;// 割引価格
					int totalPriceAll = (int) (totalPrice - disCountPriceNew);
					purchasedto.setTotalPrice(totalPriceAll);
				}
			}
		}
		model.addAttribute("totalPrice", purchasedto.getTotalPrice() - purchasedto.getPointUse());

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
				CancelDTO canceldtoNext = cancelService.cancelCheckSelect(maxId);

				if (canceldtoNext.getCancelCheck() != "null") {
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
		purchasedto.setPointRepayment(purchasedtoList.getPointRepayment());
		purchasedto.setPointUse(purchasedtoList.getPointUse());
		model.addAttribute("pointUse", purchasedto.getPointUse());
		purchasedto.setCouponId(purchasedtoList.getCouponId());
		purchasedto.setMenberCouponCheck(purchasedtoList.getMenberCouponCheck());
		purchasedto.setPurchaseCheck(purchasedtoList.getPurchaseCheck());

		PurchaseDTO customList = customService.selectMany(customId);// 購入した商品のcustomテーブル情報を取得
		System.out.println("costomList" + customList);

		purchasedto.setMemory(customList.getMemory());
		purchasedto.setHardDisc(customList.getHardDisc());
		purchasedto.setCpu(customList.getCpu());
		purchasedto.setCustomPrice(customList.getCustomPrice());

		purchasedto.setTotalPrice(
				(purchasedto.getProduct_count() * (purchasedto.getPrice() + purchasedto.getCustomPrice())));

		if (purchasedto.getMenberCouponCheck().equals("会員クーポン使用")) {
			System.out.println("クーポン使用！");
			int totalPrice = purchasedto.getTotalPrice();
			MenberCouponDTO menbercoupondto = menberCouponService.selectOne(purchasedto.getCouponId());// 会員DBからとる
			int disCount = menbercoupondto.getDiscount();// 割引率(%)
			if (disCount >= 10) {
				double disCountNew = Double.valueOf("0." + disCount);
				double disCountPriceNew = totalPrice * disCountNew;// 割引価格
				int totalPriceAll = (int) (totalPrice - disCountPriceNew);
				System.out.println("totalPriceAll" + totalPriceAll);
				System.out.println("purchasedtoAdd.getPointUse" + purchasedto.getPointUse());
				purchasedto.setTotalPrice(totalPriceAll);
			} else {
				double disCountNew = Double.valueOf("0.0" + disCount);
				double disCountPriceNew = totalPrice * disCountNew;// 割引価格
				int totalPriceAll = (int) (totalPrice - disCountPriceNew);
				System.out.println("totalPriceAll" + totalPriceAll);
				System.out.println("purchasedtoAdd.getPointUse" + purchasedto.getPointUse());
				purchasedto.setTotalPrice(totalPriceAll);
			}
		} else {

			if (purchasedto.getCouponId() > 0) {
				System.out.println("クーポン使用！");
				int totalPrice = purchasedto.getTotalPrice();
				CouponDTO coupondto = couponService.selectOne(purchasedto.getCouponId());
				int disCount = coupondto.getDiscount();// 割引率(%)
				if (disCount >= 10) {
					double disCountNew = Double.valueOf("0." + disCount);
					double disCountPriceNew = totalPrice * disCountNew;// 割引価格
					System.out.println("totalPrice" + totalPrice);
					int totalPriceAll = (int) (totalPrice - disCountPriceNew);
					System.out.println("totalPriceAll" + totalPriceAll);
					purchasedto.setTotalPrice(totalPriceAll);
					System.out.println("setTotalPrice" + purchasedto.getTotalPrice());
				} else {
					double disCountNew = Double.valueOf("0.0" + disCount);
					double disCountPriceNew = totalPrice * disCountNew;// 割引価格
					int totalPriceAll = (int) (totalPrice - disCountPriceNew);
					purchasedto.setTotalPrice(totalPriceAll);
				}
			}
		}
		model.addAttribute("totalPrice", purchasedto.getTotalPrice() - purchasedto.getPointUse());

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

		cancelService.cancelCompletedUpdate(purchaseId, purchasedto.getPointUse(), purchasedto.getPointRepayment());// キャンセル完了に伴い、キャンセルチェックをキャンセル完了に変更
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
	public String postCancelDeliveryComplete(@ModelAttribute @Validated(GroupOrder.class) CancelForm form,
			BindingResult bindingResult, CancelInTransactionForm intransactionform, @RequestParam("id") int purchaseId,
			@RequestParam("customId") int customId, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		model.addAttribute("contents", "shopping/cancelDeliveryComplete::productListLayout_contents");

		if (bindingResult.hasErrors()) {
			return postCancelDetail(form, purchaseId, model);
		}

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
		purchasedto.setPointUse(purchasedtoList.getPointUse());
		purchasedto.setCouponId(purchasedtoList.getCouponId());
		purchasedto.setMenberCouponCheck(purchasedtoList.getMenberCouponCheck());
		purchasedto.setPurchaseCheck(purchasedtoList.getPurchaseCheck());

		PurchaseDTO customList = customService.selectMany(customId);// 購入した商品のcustomテーブル情報を取得
		System.out.println("costomList" + customList);
		purchasedto.setCustom_id(customId);
		purchasedto.setMemory(customList.getMemory());
		purchasedto.setHardDisc(customList.getHardDisc());
		purchasedto.setCpu(customList.getCpu());
		purchasedto.setCustomPrice(customList.getCustomPrice());

		purchasedto.setTotalPrice(
				(purchasedto.getProduct_count() * (purchasedto.getPrice() + purchasedto.getCustomPrice())));

		if (purchasedto.getMenberCouponCheck().equals("会員クーポン使用")) {
			System.out.println("クーポン使用！");
			int totalPrice = purchasedto.getTotalPrice();
			MenberCouponDTO menbercoupondto = menberCouponService.selectOne(purchasedto.getCouponId());// 会員DBからとる
			int disCount = menbercoupondto.getDiscount();// 割引率(%)
			if (disCount >= 10) {
				double disCountNew = Double.valueOf("0." + disCount);
				double disCountPriceNew = totalPrice * disCountNew;// 割引価格
				int totalPriceAll = (int) (totalPrice - disCountPriceNew);
				System.out.println("totalPriceAll" + totalPriceAll);
				System.out.println("purchasedtoAdd.getPointUse" + purchasedto.getPointUse());
				purchasedto.setTotalPrice(totalPriceAll);
			} else {
				double disCountNew = Double.valueOf("0.0" + disCount);
				double disCountPriceNew = totalPrice * disCountNew;// 割引価格
				int totalPriceAll = (int) (totalPrice - disCountPriceNew);
				System.out.println("totalPriceAll" + totalPriceAll);
				System.out.println("purchasedtoAdd.getPointUse" + purchasedto.getPointUse());
				purchasedto.setTotalPrice(totalPriceAll);
			}
		} else {

			if (purchasedto.getCouponId() > 0) {
				System.out.println("クーポン使用！");
				int totalPrice = purchasedto.getTotalPrice();
				CouponDTO coupondto = couponService.selectOne(purchasedto.getCouponId());
				int disCount = coupondto.getDiscount();// 割引率(%)
				if (disCount >= 10) {
					double disCountNew = Double.valueOf("0." + disCount);
					double disCountPriceNew = totalPrice * disCountNew;// 割引価格
					System.out.println("totalPrice" + totalPrice);
					int totalPriceAll = (int) (totalPrice - disCountPriceNew);
					System.out.println("totalPriceAll" + totalPriceAll);
					purchasedto.setTotalPrice(totalPriceAll);
					System.out.println("setTotalPrice" + purchasedto.getTotalPrice());
				} else {
					double disCountNew = Double.valueOf("0.0" + disCount);
					double disCountPriceNew = totalPrice * disCountNew;// 割引価格
					int totalPriceAll = (int) (totalPrice - disCountPriceNew);
					purchasedto.setTotalPrice(totalPriceAll);
				}
			}
		}
		model.addAttribute("totalPrice", purchasedto.getTotalPrice() - purchasedto.getPointUse());

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

			model.addAttribute("purchaseId", purchaseId);

			model.addAttribute("purchaseList", purchasedto);
			model.addAttribute("contents", "shopping/cancelInquiry::productListLayout_contents");
			return "shopping/productListLayout";
		}

		HttpSession session = request.getSession();
		String title = (String) session.getAttribute("title");

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

				CancelDTO canceldtoNext = cancelService.cancelCheckSelect(maxId);
				System.out.println("canceldtocancelCheck" + canceldtoNext.getCancelCheck());

				if (canceldtoNext.getCancelCheck() == "null") {

					cancelService.insertOneCancelCheck(canceldto, userId, purchaseId, productId, title, content,
							bankNumber, storeName);
					purchaseService.insertOneCancelCheck(purchaseId);
				} else if ((canceldtoNext.getCancelCheck() == "キャンセル取引中")
						&& (canceldtoNext.getCancelCheck() != "返品商品確認待ち")) {

					model.addAttribute("result", "すでに口座情報が入力されています。キャンセル取り消しをする場合は商品履歴画面から上記商品の商品問題から行ってください。");
					model.addAttribute("contents", "shopping/cancelDeliveredDetail::productListLayout_contents");
					return "shopping/productListLayout";
				} else {

				}

			}
		} else {

		}

		int result = cancelService.deliveryAddressSelect(purchaseId);

		if (result == 1) {

			model.addAttribute("result", "すでに発送場所が入力済みです。発送状況を確認するには商品履歴画面の商品問題からご覧になれます。");
			model.addAttribute("contents", "shopping/cancelDeliveryComplete::productListLayout_contents");
			String deriveredCheck = cancelService.deriveredCheckSelect(purchaseId);
			if ((deriveredCheck != "null") && (deriveredCheck != "キャンセル取引中")) {

				model.addAttribute("notCancel", "notCancel");
			}

			return "shopping/productListLayout";
		}
		return "shopping/productListLayout";

	}

	@GetMapping("/cancelDeliveryComplete")
	public String getCancelDeliveryCompleteValidation(@ModelAttribute @Validated(GroupOrder.class) CancelForm form,
			BindingResult bindingResult, CancelInTransactionForm intransactionform, @RequestParam("id") int purchaseId,
			@RequestParam("customId") int customId, HttpServletRequest request, HttpServletResponse response,
			Model model) {
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

		purchasedto.setPcName(purchasedtoList.getPcName());
		purchasedto.setPrice(purchasedtoList.getPrice());
		purchasedto.setProduct_count(purchasedtoList.getProduct_count());
		purchasedto.setPointUse(purchasedtoList.getPointUse());
		purchasedto.setCouponId(purchasedtoList.getCouponId());
		purchasedto.setMenberCouponCheck(purchasedtoList.getMenberCouponCheck());
		purchasedto.setPurchaseCheck(purchasedtoList.getPurchaseCheck());

		PurchaseDTO customList = customService.selectMany(customId);// 購入した商品のcustomテーブル情報を取得
		purchasedto.setCustom_id(customId);
		purchasedto.setMemory(customList.getMemory());
		purchasedto.setHardDisc(customList.getHardDisc());
		purchasedto.setCpu(customList.getCpu());
		purchasedto.setCustomPrice(customList.getCustomPrice());

		purchasedto.setTotalPrice(
				(purchasedto.getProduct_count() * (purchasedto.getPrice() + purchasedto.getCustomPrice())));

		if (purchasedto.getMenberCouponCheck().equals("会員クーポン使用")) {
			System.out.println("クーポン使用！");
			int totalPrice = purchasedto.getTotalPrice();
			MenberCouponDTO menbercoupondto = menberCouponService.selectOne(purchasedto.getCouponId());// 会員DBからとる
			int disCount = menbercoupondto.getDiscount();// 割引率(%)
			if (disCount >= 10) {
				double disCountNew = Double.valueOf("0." + disCount);
				double disCountPriceNew = totalPrice * disCountNew;// 割引価格
				int totalPriceAll = (int) (totalPrice - disCountPriceNew);

				purchasedto.setTotalPrice(totalPriceAll);
			} else {
				double disCountNew = Double.valueOf("0.0" + disCount);
				double disCountPriceNew = totalPrice * disCountNew;// 割引価格
				int totalPriceAll = (int) (totalPrice - disCountPriceNew);

				purchasedto.setTotalPrice(totalPriceAll);
			}
		} else {

			if (purchasedto.getCouponId() > 0) {
				System.out.println("クーポン使用！");
				int totalPrice = purchasedto.getTotalPrice();
				CouponDTO coupondto = couponService.selectOne(purchasedto.getCouponId());
				int disCount = coupondto.getDiscount();// 割引率(%)
				if (disCount >= 10) {
					double disCountNew = Double.valueOf("0." + disCount);
					double disCountPriceNew = totalPrice * disCountNew;// 割引価格
					System.out.println("totalPrice" + totalPrice);
					int totalPriceAll = (int) (totalPrice - disCountPriceNew);
					System.out.println("totalPriceAll" + totalPriceAll);
					purchasedto.setTotalPrice(totalPriceAll);
					System.out.println("setTotalPrice" + purchasedto.getTotalPrice());
				} else {
					double disCountNew = Double.valueOf("0.0" + disCount);
					double disCountPriceNew = totalPrice * disCountNew;// 割引価格
					int totalPriceAll = (int) (totalPrice - disCountPriceNew);
					purchasedto.setTotalPrice(totalPriceAll);
				}
			}
		}
		model.addAttribute("totalPrice", purchasedto.getTotalPrice() - purchasedto.getPointUse());

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

			model.addAttribute("purchaseId", purchaseId);

			model.addAttribute("purchaseList", purchasedto);
			model.addAttribute("contents", "shopping/cancelInquiry::productListLayout_contents");
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
			return getCancelDeliveryCompleteValidation(form, bindingResult, intransactionform, purchaseId, customId,
					request, response, model);
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
			cancelService.deliveryDateUpdate(purchaseId, nowTime);// 商品の返品発送手続き完了時刻を格納
		}

		// 購入商品情報取得
		PurchaseDTO purchasedtoList = purchaseService.reviewSelectHistory(userId, purchaseId);// Pathで取得した購入IDでpurchaseテーブルの情報を取得
		purchasedto.setId(purchasedtoList.getId());
		purchasedto.setPurchaseId(purchasedtoList.getPurchaseId());
		purchasedto.setPurchase_date(purchasedtoList.getPurchase_date());
		purchasedto.setPcDataId(purchasedtoList.getPcDataId());
		purchasedto.setProduct_id(purchasedtoList.getProduct_id());

		purchasedto.setPcName(purchasedtoList.getPcName());
		purchasedto.setPrice(purchasedtoList.getPrice());
		purchasedto.setProduct_count(purchasedtoList.getProduct_count());
		purchasedto.setPointUse(purchasedtoList.getPointUse());
		purchasedto.setCouponId(purchasedtoList.getCouponId());
		purchasedto.setMenberCouponCheck(purchasedtoList.getMenberCouponCheck());

		purchasedto.setPurchaseCheck(purchasedtoList.getPurchaseCheck());

		PurchaseDTO customList = customService.selectMany(customId);// 購入した商品のcustomテーブル情報を取得

		purchasedto.setCustom_id(customId);
		purchasedto.setMemory(customList.getMemory());
		purchasedto.setHardDisc(customList.getHardDisc());
		purchasedto.setCpu(customList.getCpu());
		purchasedto.setCustomPrice(customList.getCustomPrice());

		purchasedto.setTotalPrice(
				(purchasedto.getProduct_count() * (purchasedto.getPrice() + purchasedto.getCustomPrice())));

		if (purchasedto.getMenberCouponCheck().equals("会員クーポン使用")) {
			System.out.println("クーポン使用！");
			int totalPrice = purchasedto.getTotalPrice();
			MenberCouponDTO menbercoupondto = menberCouponService.selectOne(purchasedto.getCouponId());// 会員DBからとる
			int disCount = menbercoupondto.getDiscount();// 割引率(%)
			if (disCount >= 10) {
				double disCountNew = Double.valueOf("0." + disCount);
				double disCountPriceNew = totalPrice * disCountNew;// 割引価格
				int totalPriceAll = (int) (totalPrice - disCountPriceNew);

				purchasedto.setTotalPrice(totalPriceAll);
			} else {
				double disCountNew = Double.valueOf("0.0" + disCount);
				double disCountPriceNew = totalPrice * disCountNew;// 割引価格
				int totalPriceAll = (int) (totalPrice - disCountPriceNew);

				purchasedto.setTotalPrice(totalPriceAll);
			}
		} else {

			if (purchasedto.getCouponId() > 0) {
				System.out.println("クーポン使用！");
				int totalPrice = purchasedto.getTotalPrice();
				CouponDTO coupondto = couponService.selectOne(purchasedto.getCouponId());
				int disCount = coupondto.getDiscount();// 割引率(%)
				if (disCount >= 10) {
					double disCountNew = Double.valueOf("0." + disCount);
					double disCountPriceNew = totalPrice * disCountNew;// 割引価格

					int totalPriceAll = (int) (totalPrice - disCountPriceNew);

					purchasedto.setTotalPrice(totalPriceAll);

				} else {
					double disCountNew = Double.valueOf("0.0" + disCount);
					double disCountPriceNew = totalPrice * disCountNew;// 割引価格
					int totalPriceAll = (int) (totalPrice - disCountPriceNew);
					purchasedto.setTotalPrice(totalPriceAll);
				}
			}
		}
		model.addAttribute("totalPrice", purchasedto.getTotalPrice() - purchasedto.getPointUse());

		model.addAttribute("pointUse", purchasedto.getPointUse());
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
			cancelService.cancelCheckUpdate(purchaseId, intransactionform.getDeliveryAddress());// 入力された発送場所を格納
			purchaseService.cancelCheckUpdateNext(purchaseId);// 画面表示内容をキャンセル取引中から返品商品確認待ちに更新
		} else {
			// 次のページから戻ってきた場合の処理
			model.addAttribute("result", "すでに発送場所が入力済みです。発送状況を確認するには商品履歴画面の商品問題からご覧になれます。");
			model.addAttribute("contents", "shopping/cancelDeliveryComplete::productListLayout_contents");
			String deriveredCheck = cancelService.deriveredCheckSelect(purchaseId);
			if ((deriveredCheck != "null") && (deriveredCheck != "キャンセル取引中")) {

				model.addAttribute("notCancel", "notCancel");
			}

			return "shopping/productListLayout";
		}

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
		purchasedto.setPcName(purchasedtoList.getPcName());
		purchasedto.setPrice(purchasedtoList.getPrice());
		purchasedto.setProduct_count(purchasedtoList.getProduct_count());
		purchasedto.setCouponId(purchasedtoList.getCouponId());
		purchasedto.setPointUse(purchasedtoList.getPointUse());
		purchasedto.setMenberCouponCheck(purchasedtoList.getMenberCouponCheck());
		purchasedto.setPurchaseCheck(purchasedtoList.getPurchaseCheck());

		PurchaseDTO customList = customService.selectMany(customId);// 購入した商品のcustomテーブル情報を取得

		purchasedto.setCustom_id(customId);
		purchasedto.setMemory(customList.getMemory());
		purchasedto.setHardDisc(customList.getHardDisc());
		purchasedto.setCpu(customList.getCpu());
		purchasedto.setCustomPrice(customList.getCustomPrice());

		purchasedto.setTotalPrice(
				(purchasedto.getProduct_count() * (purchasedto.getPrice() + purchasedto.getCustomPrice())));

		if (purchasedto.getMenberCouponCheck().equals("会員クーポン使用")) {
			System.out.println("クーポン使用！");
			int totalPrice = purchasedto.getTotalPrice();
			MenberCouponDTO menbercoupondto = menberCouponService.selectOne(purchasedto.getCouponId());// 会員DBからとる
			int disCount = menbercoupondto.getDiscount();// 割引率(%)
			if (disCount >= 10) {
				double disCountNew = Double.valueOf("0." + disCount);
				double disCountPriceNew = totalPrice * disCountNew;// 割引価格
				int totalPriceAll = (int) (totalPrice - disCountPriceNew);
				purchasedto.setTotalPrice(totalPriceAll);
			} else {
				double disCountNew = Double.valueOf("0.0" + disCount);
				double disCountPriceNew = totalPrice * disCountNew;// 割引価格
				int totalPriceAll = (int) (totalPrice - disCountPriceNew);
				purchasedto.setTotalPrice(totalPriceAll);
			}
		} else {

			if (purchasedto.getCouponId() > 0) {
				System.out.println("クーポン使用！");
				int totalPrice = purchasedto.getTotalPrice();
				CouponDTO coupondto = couponService.selectOne(purchasedto.getCouponId());
				int disCount = coupondto.getDiscount();// 割引率(%)
				if (disCount >= 10) {
					double disCountNew = Double.valueOf("0." + disCount);
					double disCountPriceNew = totalPrice * disCountNew;// 割引価格
					System.out.println("totalPrice" + totalPrice);
					int totalPriceAll = (int) (totalPrice - disCountPriceNew);
					System.out.println("totalPriceAll" + totalPriceAll);
					purchasedto.setTotalPrice(totalPriceAll);
					System.out.println("setTotalPrice" + purchasedto.getTotalPrice());
				} else {
					double disCountNew = Double.valueOf("0.0" + disCount);
					double disCountPriceNew = totalPrice * disCountNew;// 割引価格
					int totalPriceAll = (int) (totalPrice - disCountPriceNew);
					purchasedto.setTotalPrice(totalPriceAll);
				}
			}
		}
		model.addAttribute("totalPrice", purchasedto.getTotalPrice() - purchasedto.getPointUse());

		model.addAttribute("purchaseId", purchaseId);

		model.addAttribute("purchaseList", purchasedto);

		return "shopping/productListLayout";

	}

	@PostMapping(value = "/cancelDelete", params = "yes")
	public String postCancelDeliveryCompleteYes(@ModelAttribute CancelForm form,
			CancelInTransactionForm intransactionform, @RequestParam("id") int purchaseId,
			@RequestParam("customId") int customId, Model model) {

		cancelService.deleteOne(purchaseId);// キャンセル取りやめのためcancelテーブルを購入したpurchaseIDをもとに削除
		purchaseService.cancelCheckUpdate(purchaseId);// purchaseテーブルのcancel_checkを初期に戻す

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
		purchasedto.getProduct_id();
		purchasedto.setPcName(purchasedtoList.getPcName());
		purchasedto.setPrice(purchasedtoList.getPrice());
		purchasedto.setProduct_count(purchasedtoList.getProduct_count());
		purchasedto.setPointUse(purchasedtoList.getPointUse());
		purchasedto.setCouponId(purchasedtoList.getCouponId());
		purchasedto.setMenberCouponCheck(purchasedtoList.getMenberCouponCheck());
		purchasedto.setPurchaseCheck(purchasedtoList.getPurchaseCheck());

		PurchaseDTO customList = customService.selectMany(customId);// 購入した商品のcustomテーブル情報を取得

		purchasedto.setCustom_id(customId);
		purchasedto.setMemory(customList.getMemory());
		purchasedto.setHardDisc(customList.getHardDisc());
		purchasedto.setCpu(customList.getCpu());
		purchasedto.setCustomPrice(customList.getCustomPrice());

		purchasedto.setTotalPrice(
				(purchasedto.getProduct_count() * (purchasedto.getPrice() + purchasedto.getCustomPrice())));

		if (purchasedto.getMenberCouponCheck().equals("会員クーポン使用")) {
			System.out.println("クーポン使用！");
			int totalPrice = purchasedto.getTotalPrice();
			MenberCouponDTO menbercoupondto = menberCouponService.selectOne(purchasedto.getCouponId());// 会員DBからとる
			int disCount = menbercoupondto.getDiscount();// 割引率(%)
			if (disCount >= 10) {
				double disCountNew = Double.valueOf("0." + disCount);
				double disCountPriceNew = totalPrice * disCountNew;// 割引価格
				int totalPriceAll = (int) (totalPrice - disCountPriceNew);
				System.out.println("totalPriceAll" + totalPriceAll);
				System.out.println("purchasedtoAdd.getPointUse" + purchasedto.getPointUse());
				purchasedto.setTotalPrice(totalPriceAll);
			} else {
				double disCountNew = Double.valueOf("0.0" + disCount);
				double disCountPriceNew = totalPrice * disCountNew;// 割引価格
				int totalPriceAll = (int) (totalPrice - disCountPriceNew);
				System.out.println("totalPriceAll" + totalPriceAll);
				System.out.println("purchasedtoAdd.getPointUse" + purchasedto.getPointUse());
				purchasedto.setTotalPrice(totalPriceAll);
			}
		} else {

			if (purchasedto.getCouponId() > 0) {
				System.out.println("クーポン使用！");
				int totalPrice = purchasedto.getTotalPrice();
				CouponDTO coupondto = couponService.selectOne(purchasedto.getCouponId());
				int disCount = coupondto.getDiscount();// 割引率(%)
				if (disCount >= 10) {
					double disCountNew = Double.valueOf("0." + disCount);
					double disCountPriceNew = totalPrice * disCountNew;// 割引価格
					System.out.println("totalPrice" + totalPrice);
					int totalPriceAll = (int) (totalPrice - disCountPriceNew);
					System.out.println("totalPriceAll" + totalPriceAll);
					purchasedto.setTotalPrice(totalPriceAll);
					System.out.println("setTotalPrice" + purchasedto.getTotalPrice());
				} else {
					double disCountNew = Double.valueOf("0.0" + disCount);
					double disCountPriceNew = totalPrice * disCountNew;// 割引価格
					int totalPriceAll = (int) (totalPrice - disCountPriceNew);
					purchasedto.setTotalPrice(totalPriceAll);
				}
			}
		}
		model.addAttribute("totalPrice", purchasedto.getTotalPrice() - purchasedto.getPointUse());

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
		purchasedto.setPcName(purchasedtoList.getPcName());
		purchasedto.setPrice(purchasedtoList.getPrice());
		purchasedto.setProduct_count(purchasedtoList.getProduct_count());
		purchasedto.setPointUse(purchasedtoList.getPointUse());
		purchasedto.setCouponId(purchasedtoList.getCouponId());
		purchasedto.setMenberCouponCheck(purchasedtoList.getMenberCouponCheck());
		purchasedto.setPurchaseCheck(purchasedtoList.getPurchaseCheck());

		String nullCheck = "null";
		int customId = customService.selectPurchaseCheck(userId, purchasedtoList.getProduct_id(),
				purchasedtoList.getPurchaseCheck(), nullCheck); // 購入した商品のcustomテーブルIDを取得

		PurchaseDTO customList = customService.selectMany(customId);// 購入した商品のcustomテーブル情報を取得

		purchasedto.setCustom_id(customId);
		purchasedto.setMemory(customList.getMemory());
		purchasedto.setHardDisc(customList.getHardDisc());
		purchasedto.setCpu(customList.getCpu());
		purchasedto.setCustomPrice(customList.getCustomPrice());

		purchasedto.setTotalPrice(
				(purchasedto.getProduct_count() * (purchasedto.getPrice() + purchasedto.getCustomPrice())));

		if (purchasedto.getMenberCouponCheck().equals("会員クーポン使用")) {
			System.out.println("クーポン使用！");
			int totalPrice = purchasedto.getTotalPrice();
			MenberCouponDTO menbercoupondto = menberCouponService.selectOne(purchasedto.getCouponId());// 会員DBからとる
			int disCount = menbercoupondto.getDiscount();// 割引率(%)
			if (disCount >= 10) {
				double disCountNew = Double.valueOf("0." + disCount);
				double disCountPriceNew = totalPrice * disCountNew;// 割引価格
				int totalPriceAll = (int) (totalPrice - disCountPriceNew);
				System.out.println("totalPriceAll" + totalPriceAll);
				System.out.println("purchasedtoAdd.getPointUse" + purchasedto.getPointUse());
				purchasedto.setTotalPrice(totalPriceAll);
			} else {
				double disCountNew = Double.valueOf("0.0" + disCount);
				double disCountPriceNew = totalPrice * disCountNew;// 割引価格
				int totalPriceAll = (int) (totalPrice - disCountPriceNew);
				System.out.println("totalPriceAll" + totalPriceAll);
				System.out.println("purchasedtoAdd.getPointUse" + purchasedto.getPointUse());
				purchasedto.setTotalPrice(totalPriceAll);
			}
		} else {

			if (purchasedto.getCouponId() > 0) {
				System.out.println("クーポン使用！");
				int totalPrice = purchasedto.getTotalPrice();
				CouponDTO coupondto = couponService.selectOne(purchasedto.getCouponId());
				int disCount = coupondto.getDiscount();// 割引率(%)
				if (disCount >= 10) {
					double disCountNew = Double.valueOf("0." + disCount);
					double disCountPriceNew = totalPrice * disCountNew;// 割引価格
					System.out.println("totalPrice" + totalPrice);
					int totalPriceAll = (int) (totalPrice - disCountPriceNew);
					System.out.println("totalPriceAll" + totalPriceAll);
					purchasedto.setTotalPrice(totalPriceAll);
					System.out.println("setTotalPrice" + purchasedto.getTotalPrice());
				} else {
					double disCountNew = Double.valueOf("0.0" + disCount);
					double disCountPriceNew = totalPrice * disCountNew;// 割引価格
					int totalPriceAll = (int) (totalPrice - disCountPriceNew);
					purchasedto.setTotalPrice(totalPriceAll);
				}
			}
		}
		model.addAttribute("totalPrice", purchasedto.getTotalPrice() - purchasedto.getPointUse());

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
		purchasedto.setPointUse(purchasedtoList.getPointUse());
		purchasedto.setCouponId(purchasedtoList.getCouponId());
		purchasedto.setMenberCouponCheck(purchasedtoList.getMenberCouponCheck());
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

		purchasedto.setTotalPrice(
				(purchasedto.getProduct_count() * (purchasedto.getPrice() + purchasedto.getCustomPrice())));

		if (purchasedto.getMenberCouponCheck().equals("会員クーポン使用")) {
			System.out.println("クーポン使用！");
			int totalPrice = purchasedto.getTotalPrice();
			MenberCouponDTO menbercoupondto = menberCouponService.selectOne(purchasedto.getCouponId());// 会員DBからとる
			int disCount = menbercoupondto.getDiscount();// 割引率(%)
			if (disCount >= 10) {
				double disCountNew = Double.valueOf("0." + disCount);
				double disCountPriceNew = totalPrice * disCountNew;// 割引価格
				int totalPriceAll = (int) (totalPrice - disCountPriceNew);
				System.out.println("totalPriceAll" + totalPriceAll);
				System.out.println("purchasedtoAdd.getPointUse" + purchasedto.getPointUse());
				purchasedto.setTotalPrice(totalPriceAll);
			} else {
				double disCountNew = Double.valueOf("0.0" + disCount);
				double disCountPriceNew = totalPrice * disCountNew;// 割引価格
				int totalPriceAll = (int) (totalPrice - disCountPriceNew);
				System.out.println("totalPriceAll" + totalPriceAll);
				System.out.println("purchasedtoAdd.getPointUse" + purchasedto.getPointUse());
				purchasedto.setTotalPrice(totalPriceAll);
			}
		} else {

			if (purchasedto.getCouponId() > 0) {
				System.out.println("クーポン使用！");
				int totalPrice = purchasedto.getTotalPrice();
				CouponDTO coupondto = couponService.selectOne(purchasedto.getCouponId());
				int disCount = coupondto.getDiscount();// 割引率(%)
				if (disCount >= 10) {
					double disCountNew = Double.valueOf("0." + disCount);
					double disCountPriceNew = totalPrice * disCountNew;// 割引価格
					System.out.println("totalPrice" + totalPrice);
					int totalPriceAll = (int) (totalPrice - disCountPriceNew);
					System.out.println("totalPriceAll" + totalPriceAll);
					purchasedto.setTotalPrice(totalPriceAll);
					System.out.println("setTotalPrice" + purchasedto.getTotalPrice());
				} else {
					double disCountNew = Double.valueOf("0.0" + disCount);
					double disCountPriceNew = totalPrice * disCountNew;// 割引価格
					int totalPriceAll = (int) (totalPrice - disCountPriceNew);
					purchasedto.setTotalPrice(totalPriceAll);
				}
			}
		}
		model.addAttribute("totalPrice", purchasedto.getTotalPrice() - purchasedto.getPointUse());

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
		purchasedto.getProduct_id();
		purchasedto.setPcName(purchasedtoList.getPcName());
		purchasedto.setPrice(purchasedtoList.getPrice());
		purchasedto.setProduct_count(purchasedtoList.getProduct_count());
		purchasedto.setPointUse(purchasedtoList.getPointUse());
		purchasedto.setCouponId(purchasedtoList.getCouponId());
		purchasedto.setMenberCouponCheck(purchasedtoList.getMenberCouponCheck());
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

		purchasedto.setTotalPrice(
				(purchasedto.getProduct_count() * (purchasedto.getPrice() + purchasedto.getCustomPrice())));

		if (purchasedto.getMenberCouponCheck().equals("会員クーポン使用")) {
			System.out.println("クーポン使用！");
			int totalPrice = purchasedto.getTotalPrice();
			MenberCouponDTO menbercoupondto = menberCouponService.selectOne(purchasedto.getCouponId());// 会員DBからとる
			int disCount = menbercoupondto.getDiscount();// 割引率(%)
			if (disCount >= 10) {
				double disCountNew = Double.valueOf("0." + disCount);
				double disCountPriceNew = totalPrice * disCountNew;// 割引価格
				int totalPriceAll = (int) (totalPrice - disCountPriceNew);

				purchasedto.setTotalPrice(totalPriceAll);
			} else {
				double disCountNew = Double.valueOf("0.0" + disCount);
				double disCountPriceNew = totalPrice * disCountNew;// 割引価格
				int totalPriceAll = (int) (totalPrice - disCountPriceNew);

				purchasedto.setTotalPrice(totalPriceAll);
			}
		} else {

			if (purchasedto.getCouponId() > 0) {
				System.out.println("クーポン使用！");
				int totalPrice = purchasedto.getTotalPrice();
				CouponDTO coupondto = couponService.selectOne(purchasedto.getCouponId());
				int disCount = coupondto.getDiscount();// 割引率(%)
				if (disCount >= 10) {
					double disCountNew = Double.valueOf("0." + disCount);
					double disCountPriceNew = totalPrice * disCountNew;// 割引価格
					System.out.println("totalPrice" + totalPrice);
					int totalPriceAll = (int) (totalPrice - disCountPriceNew);
					System.out.println("totalPriceAll" + totalPriceAll);
					purchasedto.setTotalPrice(totalPriceAll);
					System.out.println("setTotalPrice" + purchasedto.getTotalPrice());
				} else {
					double disCountNew = Double.valueOf("0.0" + disCount);
					double disCountPriceNew = totalPrice * disCountNew;// 割引価格
					int totalPriceAll = (int) (totalPrice - disCountPriceNew);
					purchasedto.setTotalPrice(totalPriceAll);
				}
			}
		}
		model.addAttribute("totalPrice", purchasedto.getTotalPrice() - purchasedto.getPointUse());

		model.addAttribute("pointUse", purchasedto.getPointUse());
		model.addAttribute("purchaseId", purchaseId);

		model.addAttribute("purchaseList", purchasedto);

		CancelDTO canceldto = new CancelDTO();
		canceldto = cancelService.selectOne(purchaseId);
		model.addAttribute("bankNumber", canceldto.getBankNumber());
		model.addAttribute("storeName", canceldto.getStoreName());
		model.addAttribute("cancelCheck", "返品完了");
		System.out.println("candto" + canceldto);

		cancelService.cancelCompletedUpdate(purchaseId, purchasedto.getPointUse(), purchasedto.getPointRepayment());// キャンセル完了に伴い、キャンセルチェックをキャンセル完了に変更
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
				long d = (expirationDate.getTime() - now.getTime()) / (1000 * 60 * 60 * 24);// 購入日と現在の日付を比べる
				int count = (int) -d;
				if (count < 0) {
					coupondtoListAdd.add(coupondtoOne);
				}

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

				long d = (expirationDate.getTime() - now.getTime()) / (1000 * 60 * 60 * 24);// 購入日と現在の日付を比べる
				int count = (int) -d;

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

				// 購入商品ごとのカスタム情報も取り出す
				int productId = purchasedtoAdd.getId();

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

			// 購入金額でランクを決定
			if ((allTotalPrice >= 0) && (allTotalPrice < 50000)) {
				rankNumber = 1;
				model.addAttribute("rank", "アマチュアランク");
			} else if ((allTotalPrice >= 50000) && (allTotalPrice < 100000)) {
				rankNumber = 2;
				model.addAttribute("rank", "プロランク");
			} else if ((allTotalPrice >= 100000) && (allTotalPrice < 200000)) {
				rankNumber = 3;
				model.addAttribute("rank", "ブロンズランク");
			} else if ((allTotalPrice >= 200000) && (allTotalPrice < 400000)) {
				rankNumber = 4;
				model.addAttribute("rank", "シルバーランク");
			} else if ((allTotalPrice >= 400000) && (allTotalPrice < 800000)) {
				rankNumber = 5;
				model.addAttribute("rank", "ゴールドランク");
			} else if ((allTotalPrice >= 800000) && (allTotalPrice < 1000000)) {
				rankNumber = 6;
				model.addAttribute("rank", "ダイヤモンドランク");
			} else if ((allTotalPrice >= 1000000) && (allTotalPrice < 1500000)) {
				rankNumber = 7;
				model.addAttribute("rank", "プラチナランク");
			} else if ((allTotalPrice >= 1500000) && (allTotalPrice < 3000000)) {
				rankNumber = 8;
				model.addAttribute("rank", "エイリアンランク");
			} else if ((allTotalPrice >= 3000000) && (allTotalPrice < 5000000)) {
				rankNumber = 9;
				model.addAttribute("rank", "ゴッドフォックスランク");
			} else if ((allTotalPrice >= 5000000) && (allTotalPrice < 8000000)) {
				rankNumber = 10;
				model.addAttribute("rank", "プレミアムゴッドランク");
			} else if ((allTotalPrice >= 8000000)) {
				rankNumber = 11;
				model.addAttribute("rank", "InductedIntoTheHalOfFameRank");
			}

		} else {
			rankNumber = 1;// 一度も購入していない場合はアマチュアランク
			model.addAttribute("rank", "アマチュアランク");
		}

		// ランクが確定したらランクによって使用できるクーポンのチェックを行う
		List<MenberCouponDTO> menbercoupondtoList = menberCouponService.selectMany();// ここですべて取ってきて自分のrankNumberより上のやつはDTO新規作成変数に違う値を入れる

		List<MenberCouponDTO> menbercoupondtoListNew = new ArrayList<>();
		MenberCouponDTO menbercoupondtoOne = new MenberCouponDTO();
		for (int i = 0; menbercoupondtoList.size() > i; i++) {
			menbercoupondtoOne = menbercoupondtoList.get(i);
			int rankNumberCheck = menbercoupondtoOne.getCouponRank();
			if (rankNumberCheck == 1) {
				menbercoupondtoOne.setMenberRank("アマチュアランク");
				if (menbercoupondtoOne.getCouponRank() <= rankNumber) {
					menbercoupondtoOne.setCouponUseCheck("使用可能");
				} else {
					menbercoupondtoOne.setCouponUseCheck("使用不可");
				}
			} else if (rankNumberCheck == 2) {
				menbercoupondtoOne.setMenberRank("プロランク");
				if (menbercoupondtoOne.getCouponRank() <= rankNumber) {
					menbercoupondtoOne.setCouponUseCheck("使用可能");
				} else {
					menbercoupondtoOne.setCouponUseCheck("使用不可");
				}
			} else if (rankNumberCheck == 3) {
				menbercoupondtoOne.setMenberRank("ブロンズランク");
				if (menbercoupondtoOne.getCouponRank() <= rankNumber) {
					menbercoupondtoOne.setCouponUseCheck("使用可能");
				} else {
					menbercoupondtoOne.setCouponUseCheck("使用不可");
				}
			} else if (rankNumberCheck == 4) {
				menbercoupondtoOne.setMenberRank("シルバーランク");
				if (menbercoupondtoOne.getCouponRank() <= rankNumber) {
					menbercoupondtoOne.setCouponUseCheck("使用可能");
				} else {
					menbercoupondtoOne.setCouponUseCheck("使用不可");
				}
			} else if (rankNumberCheck == 5) {
				menbercoupondtoOne.setMenberRank("ゴールドランク");
				if (menbercoupondtoOne.getCouponRank() <= rankNumber) {
					menbercoupondtoOne.setCouponUseCheck("使用可能");
				} else {
					menbercoupondtoOne.setCouponUseCheck("使用不可");
				}
			} else if (rankNumberCheck == 6) {
				menbercoupondtoOne.setMenberRank("ダイヤモンドランク");
				if (menbercoupondtoOne.getCouponRank() <= rankNumber) {
					menbercoupondtoOne.setCouponUseCheck("使用可能");
				} else {
					menbercoupondtoOne.setCouponUseCheck("使用不可");
				}
			} else if (rankNumberCheck == 7) {
				menbercoupondtoOne.setMenberRank("プラチナランク");
				if (menbercoupondtoOne.getCouponRank() <= rankNumber) {
					menbercoupondtoOne.setCouponUseCheck("使用可能");
				} else {
					menbercoupondtoOne.setCouponUseCheck("使用不可");
				}
			} else if (rankNumberCheck == 8) {
				menbercoupondtoOne.setMenberRank("エイリアンランク");
				if (menbercoupondtoOne.getCouponRank() <= rankNumber) {
					menbercoupondtoOne.setCouponUseCheck("使用可能");
				} else {
					menbercoupondtoOne.setCouponUseCheck("使用不可");
				}
			} else if (rankNumberCheck == 9) {
				menbercoupondtoOne.setMenberRank("ゴッドフォックスランク");
				if (menbercoupondtoOne.getCouponRank() <= rankNumber) {
					menbercoupondtoOne.setCouponUseCheck("使用可能");
				} else {
					menbercoupondtoOne.setCouponUseCheck("使用不可");
				}
			} else if (rankNumberCheck == 10) {
				menbercoupondtoOne.setMenberRank("プレミアムゴッドランク");
				if (menbercoupondtoOne.getCouponRank() <= rankNumber) {
					menbercoupondtoOne.setCouponUseCheck("使用可能");
				} else {
					menbercoupondtoOne.setCouponUseCheck("使用不可");
				}
			} else if (rankNumberCheck == 11) {
				menbercoupondtoOne.setMenberRank("InductedIntoTheHalOfFameRank");
				if (menbercoupondtoOne.getCouponRank() <= rankNumber) {
					menbercoupondtoOne.setCouponUseCheck("使用可能");
				} else {
					menbercoupondtoOne.setCouponUseCheck("使用不可");
				}
			}
			menbercoupondtoListNew.add(menbercoupondtoOne);
			model.addAttribute("menbercoupondtoList", menbercoupondtoListNew);
		}

		if (menbercoupondtoList.size() == 0) {
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
			System.out.println("coupondtoList" + coupondtoList);
			List<MenberCouponDTO> coupondtoListAdd = new ArrayList<>();

			for (int i = 0; coupondtoList.size() > i; i++) {// クーポン情報を一つづつ取り出す
				MenberCouponDTO coupondtoOne = coupondtoList.get(i);
				System.out.println("couponRanktest" + coupondtoOne.getCouponRank());
				int rankNumberCheck = coupondtoOne.getCouponRank();
				if (rankNumberCheck == 1) {
					coupondtoOne.setMenberRank("アマチュアランク");
					if (coupondtoOne.getCouponRank() <= rankNumber) {
						coupondtoOne.setCouponUseCheck("使用可能");
					} else {
						coupondtoOne.setCouponUseCheck("使用不可");
					}
				} else if (rankNumberCheck == 2) {
					coupondtoOne.setMenberRank("プロランク");
					if (coupondtoOne.getCouponRank() <= rankNumber) {
						coupondtoOne.setCouponUseCheck("使用可能");
					} else {
						coupondtoOne.setCouponUseCheck("使用不可");
					}
				} else if (rankNumberCheck == 3) {
					coupondtoOne.setMenberRank("ブロンズランク");
					if (coupondtoOne.getCouponRank() <= rankNumber) {
						coupondtoOne.setCouponUseCheck("使用可能");
					} else {
						coupondtoOne.setCouponUseCheck("使用不可");
					}
				} else if (rankNumberCheck == 4) {
					coupondtoOne.setMenberRank("シルバーランク");
					if (coupondtoOne.getCouponRank() <= rankNumber) {
						coupondtoOne.setCouponUseCheck("使用可能");
					} else {
						coupondtoOne.setCouponUseCheck("使用不可");
					}
				} else if (rankNumberCheck == 5) {
					coupondtoOne.setMenberRank("ゴールドランク");
					if (coupondtoOne.getCouponRank() <= rankNumber) {
						coupondtoOne.setCouponUseCheck("使用可能");
					} else {
						coupondtoOne.setCouponUseCheck("使用不可");
					}
				} else if (rankNumberCheck == 6) {
					coupondtoOne.setMenberRank("ダイヤモンドランク");
					if (coupondtoOne.getCouponRank() <= rankNumber) {
						coupondtoOne.setCouponUseCheck("使用可能");
					} else {
						coupondtoOne.setCouponUseCheck("使用不可");
					}
				} else if (rankNumberCheck == 7) {
					coupondtoOne.setMenberRank("プラチナランク");
					if (coupondtoOne.getCouponRank() <= rankNumber) {
						coupondtoOne.setCouponUseCheck("使用可能");
					} else {
						coupondtoOne.setCouponUseCheck("使用不可");
					}
				} else if (rankNumberCheck == 8) {
					coupondtoOne.setMenberRank("エイリアンランク");
					if (coupondtoOne.getCouponRank() <= rankNumber) {
						coupondtoOne.setCouponUseCheck("使用可能");
					} else {
						coupondtoOne.setCouponUseCheck("使用不可");
					}
				} else if (rankNumberCheck == 9) {
					coupondtoOne.setMenberRank("ゴッドフォックスランク");
					if (coupondtoOne.getCouponRank() <= rankNumber) {
						coupondtoOne.setCouponUseCheck("使用可能");
					} else {
						coupondtoOne.setCouponUseCheck("使用不可");
					}
				} else if (rankNumberCheck == 10) {
					coupondtoOne.setMenberRank("プレミアムゴッドランク");
					if (coupondtoOne.getCouponRank() <= rankNumber) {
						coupondtoOne.setCouponUseCheck("使用可能");
					} else {
						coupondtoOne.setCouponUseCheck("使用不可");
					}
				} else if (rankNumberCheck == 11) {
					coupondtoOne.setMenberRank("InductedIntoTheHalOfFameRank");
					if (coupondtoOne.getCouponRank() <= rankNumber) {
						coupondtoOne.setCouponUseCheck("使用可能");
					} else {
						coupondtoOne.setCouponUseCheck("使用不可");
					}
				}

				coupondtoListAdd.add(coupondtoOne);

				model.addAttribute("couponList", coupondtoListAdd);

			}
			if (coupondtoList.size() == 0) {
				model.addAttribute("notCoupon", "yes");
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
					if (coupondtoOne.getCouponRank() <= rankNumber) {
						coupondtoOne.setCouponUseCheck("使用可能");
					} else {
						coupondtoOne.setCouponUseCheck("使用不可");
					}
				} else if (rankNumberCheck == 2) {
					coupondtoOne.setMenberRank("プロランク");
					if (coupondtoOne.getCouponRank() <= rankNumber) {
						coupondtoOne.setCouponUseCheck("使用可能");
					} else {
						coupondtoOne.setCouponUseCheck("使用不可");
					}
				} else if (rankNumberCheck == 3) {
					coupondtoOne.setMenberRank("ブロンズランク");
					if (coupondtoOne.getCouponRank() <= rankNumber) {
						coupondtoOne.setCouponUseCheck("使用可能");
					} else {
						coupondtoOne.setCouponUseCheck("使用不可");
					}
				} else if (rankNumberCheck == 4) {
					coupondtoOne.setMenberRank("シルバーランク");
					if (coupondtoOne.getCouponRank() <= rankNumber) {
						coupondtoOne.setCouponUseCheck("使用可能");
					} else {
						coupondtoOne.setCouponUseCheck("使用不可");
					}
				} else if (rankNumberCheck == 5) {
					coupondtoOne.setMenberRank("ゴールドランク");
					if (coupondtoOne.getCouponRank() <= rankNumber) {
						coupondtoOne.setCouponUseCheck("使用可能");
					} else {
						coupondtoOne.setCouponUseCheck("使用不可");
					}
				} else if (rankNumberCheck == 6) {
					coupondtoOne.setMenberRank("ダイヤモンドランク");
					if (coupondtoOne.getCouponRank() <= rankNumber) {
						coupondtoOne.setCouponUseCheck("使用可能");
					} else {
						coupondtoOne.setCouponUseCheck("使用不可");
					}
				} else if (rankNumberCheck == 7) {
					coupondtoOne.setMenberRank("プラチナランク");
					if (coupondtoOne.getCouponRank() <= rankNumber) {
						coupondtoOne.setCouponUseCheck("使用可能");
					} else {
						coupondtoOne.setCouponUseCheck("使用不可");
					}
				} else if (rankNumberCheck == 8) {
					coupondtoOne.setMenberRank("エイリアンランク");
					if (coupondtoOne.getCouponRank() <= rankNumber) {
						coupondtoOne.setCouponUseCheck("使用可能");
					} else {
						coupondtoOne.setCouponUseCheck("使用不可");
					}
				} else if (rankNumberCheck == 9) {
					coupondtoOne.setMenberRank("ゴッドフォックスランク");
					if (coupondtoOne.getCouponRank() <= rankNumber) {
						coupondtoOne.setCouponUseCheck("使用可能");
					} else {
						coupondtoOne.setCouponUseCheck("使用不可");
					}
				} else if (rankNumberCheck == 10) {
					coupondtoOne.setMenberRank("プレミアムゴッドランク");
					if (coupondtoOne.getCouponRank() <= rankNumber) {
						coupondtoOne.setCouponUseCheck("使用可能");
					} else {
						coupondtoOne.setCouponUseCheck("使用不可");
					}
				} else if (rankNumberCheck == 11) {
					coupondtoOne.setMenberRank("InductedIntoTheHalOfFameRank");
					if (coupondtoOne.getCouponRank() <= rankNumber) {
						coupondtoOne.setCouponUseCheck("使用可能");
					} else {
						coupondtoOne.setCouponUseCheck("使用不可");
					}
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
				long d = (expirationDate.getTime() - now.getTime()) / (1000 * 60 * 60 * 24);// 購入日と現在の日付を比べる
				int count = (int) -d;

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
					long d = (expirationDate.getTime() - now.getTime()) / (1000 * 60 * 60 * 24);// 購入日と現在の日付を比べる
					int count = (int) -d;
					if (count < 0) {
						coupondtoListAdd.add(coupondtoOne);
					}
					model.addAttribute("notCoupon", "no");
				}

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

				// 購入商品ごとのカスタム情報も取り出す
				int productId = purchasedtoAdd.getId();

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

				} catch (NullPointerException e) {
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

			}
			if (coupondtoList.size() == 0) {
				model.addAttribute("notCoupon", "yes");
			}
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();

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

		return "redirect:/cart";
	}

	@GetMapping("/couponUse/{couponId}/{productId}")
	public String getCouponUse(@ModelAttribute CouponForm form, CartForm cartform,
			@PathVariable("couponId") int couponId, @PathVariable("productId") int productId, Model model) {
		model.addAttribute("contents", "shopping/cart::productListLayout_contents");
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
				model.addAttribute("totalPrice", totalPriceAll);
				model.addAttribute("couponAfterPrice", totalPriceOne);
			}
		}

		model.addAttribute("cartList", cartList);
		model.addAttribute("couponId", couponId);// 使用するクーポンIDをhtmlに持っていく

		return "shopping/productListLayout";
	}

	@GetMapping("/menberCouponUse/{couponId}/{productId}")
	public String getMenberCouponUse(@ModelAttribute MenberCouponForm form, CartForm cartform,
			@PathVariable("couponId") int couponId, @PathVariable("productId") int productId, Model model) {
		model.addAttribute("contents", "shopping/cart::productListLayout_contents");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String getUserId = auth.getName();

		System.out.println("couponIdCheck" + couponId);
		int cartId = cartService.selectMaxId(productId);// cartテーブルからクーポンを使う商品IDを取得
		System.out.println("test1" + cartId);
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

		return "redirect:/menberCouponList";
	}

	@GetMapping("/productList")
	public String getProductList(@ModelAttribute PcDataForm form, ProductListSearchForm productlistsearchform,
			RedirectAttributes redirectAttributes, Model model) {
		model.addAttribute("contents", "shopping/productList::productListLayout_contents");

		// 商品情報をすべて取得
		List<PcDataDTO> productList = pcdataService.selectMany();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		UsersDTO headerName = usersService.getUser_name(auth.getName());
		session.setAttribute("sessionGetUser_name", headerName.getUser_name());
		session.setAttribute("sessionGetRole", headerName.getRole());

		model.addAttribute("productList", productList);

		int allProductCount = 0;
		int allTotalPrice = 0;
		String user_id = auth.getName();
		// ログインユーザーのID取得
		int selectId = usersService.select_id(user_id);
		List<PurchaseDTO> purchasedtoList = purchaseService.selectHistory(selectId);

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

	@PostMapping(value = "/productList", params = "search")
	public String postProductList(@ModelAttribute @Validated(GroupOrder.class) ProductListSearchForm form,
			BindingResult bindingResult, Model model) {
		model.addAttribute("contents", "shopping/productList::productListLayout_contents");

		if (bindingResult.hasErrors()) {
			PcDataForm pcdataform = new PcDataForm();
			RedirectAttributes redirectAttributes = null;
			return getProductList(pcdataform, form, redirectAttributes, model);
		}

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String user_id = auth.getName();

		// ユーザーが検索した内容の商品情報を取得
		List<PcDataDTO> productList = pcdataService.searchProductSelectMany(form);
		model.addAttribute("productList", productList);

		UsersDTO headerName = usersService.getUser_name(auth.getName());
		session.setAttribute("sessionGetUser_name", headerName.getUser_name());
		session.setAttribute("sessionGetRole", headerName.getRole());

		int allProductCount = 0;
		int allTotalPrice = 0;

		// ログインユーザーのID取得
		int selectId = usersService.select_id(user_id);
		List<PurchaseDTO> purchasedtoList = purchaseService.selectHistory(selectId);

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

	@GetMapping("productStockOut")
	public String getProductStockOut(@ModelAttribute PcDataForm form, ProductListSearchForm productlistsearchform,
			RedirectAttributes redirectAttributes, Model model) {
		model.addAttribute("contents", "shopping/productStockOut::productListLayout_contents");

		// 在庫がない商品情報をすべて取得
		List<PcDataDTO> productList = pcdataService.stockOutProductSelectMany();
		if (productList.size() > 0) {
			model.addAttribute("subText", "また入荷次第購入可能になる可能性があります");
		} else {
			model.addAttribute("subText", "在庫なしの商品は現在ありません");
		}

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		UsersDTO headerName = usersService.getUser_name(auth.getName());
		session.setAttribute("sessionGetUser_name", headerName.getUser_name());
		session.setAttribute("sessionGetRole", headerName.getRole());

		model.addAttribute("productList", productList);

		int allProductCount = 0;
		int allTotalPrice = 0;
		String user_id = auth.getName();
		// ログインユーザーのID取得
		int selectId = usersService.select_id(user_id);
		List<PurchaseDTO> purchasedtoList = purchaseService.selectHistory(selectId);

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
	
	@GetMapping("/stockOutProductResurrection")
	public String getStockOutProductResurrection(Model model) {
		model.addAttribute("contents", "shopping/stockOutProductResurrection::productListLayout_contents");
		
		// 在庫がない商品情報をすべて取得
		List<PcDataDTO> productList = pcdataService.stockOutProductSelectMany();
		if (productList.size() > 0) {
			model.addAttribute("subText", "また入荷次第購入可能になる可能性があります");
		} else {
			model.addAttribute("subText", "在庫なしの商品は現在ありません");
		}

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		UsersDTO headerName = usersService.getUser_name(auth.getName());
		session.setAttribute("sessionGetUser_name", headerName.getUser_name());
		session.setAttribute("sessionGetRole", headerName.getRole());

		model.addAttribute("productList", productList);

		int allProductCount = 0;
		int allTotalPrice = 0;
		String user_id = auth.getName();
		// ログインユーザーのID取得
		int selectId = usersService.select_id(user_id);
		List<PurchaseDTO> purchasedtoList = purchaseService.selectHistory(selectId);

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
	
	@GetMapping("/stockInput/{id}")
	public String getStockInput(@PathVariable("id") int pcdataId,StockInputForm from,Model model) {
		model.addAttribute("contents", "shopping/stockInput::productListLayout_contents");
		model.addAttribute("pcdataId",pcdataId);
		
		return "shopping/productListLayout";
	}
	
	@PostMapping("/stockInput")
	public String postStockInput(@RequestParam("id") int pcdataId,@Validated(GroupOrder.class) StockInputForm form,BindingResult bindingResult,Model model) {
		
		if(bindingResult.hasErrors()) {
			return getStockInput(pcdataId,form,model);
			
		}
		
		int productStock = Integer.valueOf(form.getProductStock());
		//入力された在庫数に変更
		pcdataService.productStockUpdate(pcdataId,productStock);
		
		return getStockOutProductResurrection(model);
	}

	@GetMapping("/productDetail/{id}")
	public String getProductDetail(@ModelAttribute PcDetailDataForm form, PcDataForm pcdataform,
			HttpServletRequest request, Model model, @PathVariable("id") int id) {
		model.addAttribute("contents", "shopping/productDetail::productListLayout_contents");

		
		try {
			List<ReviewDTO> reviewList = reviewService.selectRating(id);// 商品IDをもとにその商品の口コミをすべて取得
			if (reviewList.size() != 0) {
				System.out.println("reviewList" + reviewList);
				double totalRating = 0;
				for (int i = 0; reviewList.size() > i; i++) {
					ReviewDTO reviewdto = reviewList.get(i);
					totalRating = totalRating + reviewdto.getRating();

				}

				double evaluation = totalRating / reviewList.size();// ５段階評価の平均点を出す

				model.addAttribute("reviews", reviewList.size());

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
		model.addAttribute("productId",id);
		model.addAttribute("pcdatadtoOne", pcdatadtoOne);

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String user_id = auth.getName();

		// ログインユーザーのID取得
		int select_id = usersService.select_id(user_id);

		int goodResult = productGoodService.goodCheck(select_id,id);
		if(goodResult == 0) {
			model.addAttribute("goodCheck","ok");
		}else {
			model.addAttribute("goodCheck","no");
		}
		
		CustomDTO customdto = customService.selectCustomProduct_id(id, select_id);
		System.out.println("test" + customdto.getProductId());
		System.out.println("test2" + id);
		if (customdto.getProductId() != id) {
			String defaultMemory = "4GB";
			String defaultHardDisc = "SSD";
			String defaultCpu = "CORE3";
			int customPrice = 0;

			customService.insertCustomData(id, select_id, defaultMemory, defaultHardDisc, defaultCpu, // デフォルトカスタムデータを格納
					customPrice);
		}

		PcDetailDataDTO pcdetaildatadto = customService.selectOne(id, select_id);

		// 商品編集のため、adminUserのみの情報を取得
		String adminCheck = usersService.selectRole(select_id);
		model.addAttribute("adminCheck", adminCheck);

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

		PcDataDTO pcdatadtoOne = pcdataService.selectOne(id);// 商品Idをもとにその商品の情報を取得
		System.out.println("pcdatadtoOne" + pcdatadtoOne);
		model.addAttribute("pcdatadtoOne", pcdatadtoOne);

		int getPrice = pcdatadtoOne.getPrice();

		// カスタムデータによって商品の金額を変更する
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

	@GetMapping("/pointUse/{id}")
	public String getPointUse(@ModelAttribute PointUseForm form, @PathVariable("id") int couponId, Model model) {
		model.addAttribute("contents", "shopping/pointUse::productListLayout_contents");

		model.addAttribute("couponId", couponId);// 遷移先へhiddenで渡す
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String user_id = auth.getName();
		// ログインユーザーのID取得
		int userId = usersService.select_id(user_id);

		List<PurchaseDTO> purchasePointList = purchaseService.selectPoint(userId);// purchaseテーブルから購入時付与ポイントと購入時利用ポイントを取得
		int pointAll = 0;
		int pointUseAll = 0;
		int purchasePoint = 0;
		for (int x = 0; purchasePointList.size() > x; x++) {
			PurchaseDTO purchasedtoOne = purchasePointList.get(x);
			int pointOne = purchasedtoOne.getPoint();
			int pointUseOne = purchasedtoOne.getPointUse();
			pointAll = pointAll + pointOne;
			pointUseAll = pointUseAll + pointUseOne;
			purchasePoint = pointAll - pointUseAll;// 購入時付与ポイント - 購入時利用ポイントで現在保持しているポイントを出す
		}

		List<CancelDTO> cancelPointList = cancelService.selectPoint(userId);// cancelテーブルからキャンセルによる利用したポイントの返却分と購入後付与ポイントの回収値を取得

		int cancelPoint = 0;
		for (int y = 0; cancelPointList.size() > y; y++) {
			CancelDTO canceldtoOne = cancelPointList.get(y);
			int returnPoint = canceldtoOne.getReturnPoint();
			int pointRepayment = canceldtoOne.getPointRepayment();
			cancelPoint = returnPoint - pointRepayment;// キャンセルによる利用したポイントの返却分 - 購入後付与ポイントの回収値でキャンセル時の返すポイントを出す
		}

		int ITworksTRYshoppingP = purchasePoint + cancelPoint;
		if (ITworksTRYshoppingP < 1) {
			model.addAttribute("point", 0);
		}
		model.addAttribute("point", ITworksTRYshoppingP);

		List<PcDataDTO> cartList = cartService.selectMany(user_id);
		int totalPriceAll = 0;// カート全体価格
		int totalPriceOne = 0;// 各商品価格
		double disCountPrice = 0;

		for (int i = 0; i < cartList.size(); i++) {
			PcDataDTO pcdatadto = cartList.get(i);

			totalPriceAll = totalPriceAll + pcdatadto.getProduct_count() * pcdatadto.getAfterCustomPrice();// カート内の合計金額

			totalPriceOne = pcdatadto.getProduct_count() * pcdatadto.getAfterCustomPrice();// 各商品の金額

			try {
				if (pcdatadto.getMenberCouponCheck().equals("会員ランク特典使用")) {
					System.out.println("会員ランク特典使用");
					MenberCouponDTO menbercoupondto = menberCouponService.selectOne(couponId);// menber_couponテーブルから会員ランククーポン情報を取得
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
							disCountPrice = totalPriceOne * disCountNew;// クーポンを使用した商品の割引数取得

							// ここから割引分の値が入っているのでそれをトータルからひく、小数点以下も切り捨てる
							totalPriceAll = (int) (totalPriceAll - disCountPrice);
							model.addAttribute("totalPrice", totalPriceAll);
						} else {
							double disCountNew = Double.valueOf("0.0" + disCount);
							System.out.println("discoutnnew" + disCountNew);
							disCountPrice = totalPriceOne * disCountNew;// クーポンを使用した商品の割引数取得

							// ここから割引分の値が入っているのでそれをトータルからひく、小数点以下も切り捨てる
							totalPriceAll = (int) (totalPriceAll - disCountPrice);
							model.addAttribute("totalPrice", totalPriceAll);
						}
					}
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
				if (pcdatadto.getCouponId() == 0) {
					System.out.println("クーポン使用なし");
					model.addAttribute("totalPrice", totalPriceAll);

				} else {// クーポンを使用した商品はelse
					System.out.println("クーポン使用！");
					CouponDTO coupondto = couponService.selectOne(couponId);
					int disCount = coupondto.getDiscount();
					if (disCount >= 10) {
						double disCountNew = Double.valueOf("0." + disCount);
						disCountPrice = totalPriceOne * disCountNew;// クーポンを使用した商品の割引数取得

						// ここから割引分の値が入っているのでそれをトータルからひく、小数点以下も切り捨てる
						totalPriceAll = (int) (totalPriceAll - disCountPrice);
						model.addAttribute("totalPrice", totalPriceAll);
					} else {
						double disCountNew = Double.valueOf("0.0" + disCount);
						disCountPrice = totalPriceOne * disCountNew;// クーポンを使用した商品の割引数取得

						// ここから割引分の値が入っているのでそれをトータルからひく、小数点以下も切り捨てる
						totalPriceAll = (int) (totalPriceAll - disCountPrice);
						model.addAttribute("totalPrice", totalPriceAll);
					}
				}
			}
			System.out.println("form" + form);
		}

		return "shopping/productListLayout";
	}

	@PostMapping("/pointUse/{id}")
	public String postPointUse(@ModelAttribute @Validated(GroupOrder.class) PointUseForm form,
			BindingResult bindingResult, @PathVariable("id") int couponId, HttpServletRequest request, Model model) {
		model.addAttribute("contents", "shopping/pointUseNext::productListLayout_contents");
		model.addAttribute("couponId", couponId);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String user_id = auth.getName();
		// ログインユーザーのID取得
		int userId = usersService.select_id(user_id);

		if (bindingResult.hasErrors()) {
			return getPointUse(form, couponId, model);
		}

		if (form.getPointUse().equals("0")) {
			model.addAttribute("errormessage", "0のみは入力できません");
			return getPointUse(form, couponId, model);
		}

		List<PurchaseDTO> purchasePointList = purchaseService.selectPoint(userId);// 取得ポイントと利用ポイントを取得
		int pointAll = 0;
		int pointUseAll = 0;
		int purchasePoint = 0;
		for (int x = 0; purchasePointList.size() > x; x++) {
			PurchaseDTO purchasedtoOne = purchasePointList.get(x);
			int pointOne = purchasedtoOne.getPoint();
			int pointUseOne = purchasedtoOne.getPointUse();
			pointAll = pointAll + pointOne;
			pointUseAll = pointUseAll + pointUseOne;
			purchasePoint = pointAll - pointUseAll;// 取得ポイント - 利用ポイントで現在保持しているポイントを出す
		}

		List<CancelDTO> cancelPointList = cancelService.selectPoint(userId);

		int cancelPoint = 0;
		for (int y = 0; cancelPointList.size() > y; y++) {
			CancelDTO canceldtoOne = cancelPointList.get(y);
			int returnPoint = canceldtoOne.getReturnPoint();
			int pointRepayment = canceldtoOne.getPointRepayment();
			cancelPoint = returnPoint - pointRepayment;// 返ってきたポイント - 購入時付加ポイントでキャンセル時の返すポイントを出す
		}

		int ITworksTRYshoppingP = purchasePoint + cancelPoint;
		if (ITworksTRYshoppingP < 1) {
			model.addAttribute("point", 0);
		}

		int pointUse = Integer.parseInt(form.getPointUse());
		model.addAttribute("point", (ITworksTRYshoppingP - pointUse));

		List<PcDataDTO> cartList = cartService.selectMany(user_id);
		int totalPriceAll = 0;// カート全体価格
		int totalPriceOne = 0;// 各商品価格
		double disCountPrice = 0;

		for (int i = 0; i < cartList.size(); i++) {
			PcDataDTO pcdatadto = cartList.get(i);

			totalPriceAll = totalPriceAll + pcdatadto.getProduct_count() * pcdatadto.getAfterCustomPrice();// カート内の合計金額

			totalPriceOne = pcdatadto.getProduct_count() * pcdatadto.getAfterCustomPrice();// 各商品の金額

			try {
				if (pcdatadto.getMenberCouponCheck().equals("会員ランク特典使用")) {
					System.out.println("会員ランク特典使用");
					MenberCouponDTO menbercoupondto = menberCouponService.selectOne(couponId);
					int menberCouponDisCount = menbercoupondto.getDiscount();
					double disCountNew = 0;
					if (menberCouponDisCount < 10) {
						disCountNew = Double.valueOf("0.0" + menberCouponDisCount);

						double disCountPriceNew = totalPriceOne * disCountNew;// 割引価格
						int disCountPriceNewNext = (int) disCountPriceNew;
						pcdatadto.setDisCountPriceNew(disCountPriceNewNext);
						totalPriceAll = (int) (totalPriceAll - disCountPriceNew);
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
						model.addAttribute("totalPrice", totalPriceAll);

					} else {// クーポンを使用した商品はelse
						System.out.println("クーポン使用！");
						CouponDTO coupondto = couponService.selectOne(couponId);
						int disCount = coupondto.getDiscount();
						if (disCount >= 10) {
							double disCountNew = Double.valueOf("0." + disCount);
							disCountPrice = totalPriceOne * disCountNew;// クーポンを使用した商品の割引数取得

							// ここから割引分の値が入っているのでそれをトータルからひく、小数点以下も切り捨てる
							totalPriceAll = (int) (totalPriceAll - disCountPrice);
							model.addAttribute("totalPrice", totalPriceAll);
						} else {
							double disCountNew = Double.valueOf("0.0" + disCount);
							disCountPrice = totalPriceOne * disCountNew;// クーポンを使用した商品の割引数取得

							// ここから割引分の値が入っているのでそれをトータルからひく、小数点以下も切り捨てる
							totalPriceAll = (int) (totalPriceAll - disCountPrice);
							model.addAttribute("totalPrice", totalPriceAll);
						}
					}
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
				if (pcdatadto.getCouponId() == 0) {
					System.out.println("クーポン使用なし");
					model.addAttribute("totalPrice", totalPriceAll);

				} else {// クーポンを使用した商品はelse
					System.out.println("クーポン使用！");
					CouponDTO coupondto = couponService.selectOne(couponId);
					int disCount = coupondto.getDiscount();
					if (disCount >= 10) {
						double disCountNew = Double.valueOf("0." + disCount);
						disCountPrice = totalPriceOne * disCountNew;// クーポンを使用した商品の割引数取得

						// ここから割引分の値が入っているのでそれをトータルからひく、小数点以下も切り捨てる
						totalPriceAll = (int) (totalPriceAll - disCountPrice);
						model.addAttribute("totalPrice", totalPriceAll);
					} else {
						double disCountNew = Double.valueOf("0.0" + disCount);
						System.out.println("discoutnnew" + disCountNew);
						disCountPrice = totalPriceOne * disCountNew;// クーポンを使用した商品の割引数取得

						// ここから割引分の値が入っているのでそれをトータルからひく、小数点以下も切り捨てる

						totalPriceAll = (int) (totalPriceAll - disCountPrice);
						model.addAttribute("totalPrice", totalPriceAll);
					}
				}
			}
			System.out.println("form" + form);
		}

		int pointUseCheck = Integer.parseInt(form.getPointUse());
		model.addAttribute("errorCheck", "false");
		if (pointUseCheck > totalPriceAll) {// 利用ポイントが合計支払金額より多く入力されたらtrue

			model.addAttribute("error", "合計支払金額以下で入力してください");
			model.addAttribute("errorCheck", "true");
			return getPointUse(form, couponId, model);
		}

		if (pointUseCheck > (pointAll - pointUseAll)) {// 利用ポイントが保持しているポイントより多く入力されたらtrue

			model.addAttribute("error", "保持しているポイント以下で入力してください");
			model.addAttribute("errorCheck", "true");
			return getPointUse(form, couponId, model);
		}

		if ((pointUse > pointAll) || (pointUse > totalPriceAll)) {
			return getPointUse(form, couponId, model);
		}

		HttpSession session = request.getSession();
		session.setAttribute("pointUse", form.getPointUse());
		model.addAttribute("pointUse", (String) session.getAttribute("pointUse"));
		int pointUseAfterPoint = pointAll - pointUse;
		int pointUseAfterPrice = totalPriceAll - pointUse;
		model.addAttribute("pointUseAfterPoint", pointUseAfterPoint);
		model.addAttribute("pointUseAfterPrice", pointUseAfterPrice);

		return "shopping/productListLayout";

	}

	@GetMapping("/pointUseClearing/{id}")
	public String getPointUseAfterPoint(@ModelAttribute CreditForm form, PointUseForm pointUseForm,
			@PathVariable("id") int couponId, @RequestParam("pointUse") int pointUse, Model model) {
		model.addAttribute("contents", "shopping/pointUseClearing::productListLayout_contents");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String getName = auth.getName();

		model.addAttribute("pointUse", pointUse);

		List<PcDataDTO> cartList = cartService.selectMany(getName);
		int totalPriceAll = 0;// カート全体価格
		int totalPriceOne = 0;// 各商品価格
		double disCountPrice = 0;

		for (int i = 0; i < cartList.size(); i++) {
			PcDataDTO pcdatadto = cartList.get(i);

			totalPriceAll = totalPriceAll + pcdatadto.getProduct_count() * pcdatadto.getAfterCustomPrice();// カート内の合計金額

			totalPriceOne = pcdatadto.getProduct_count() * pcdatadto.getAfterCustomPrice();// 各商品の金額

			try {
				if (pcdatadto.getMenberCouponCheck().equals("会員ランク特典使用")) {
					System.out.println("会員ランク特典使用");
					MenberCouponDTO menbercoupondto = menberCouponService.selectOne(couponId);
					int menberCouponDisCount = menbercoupondto.getDiscount();
					double disCountNew = 0;
					if (menberCouponDisCount < 10) {
						disCountNew = Double.valueOf("0.0" + menberCouponDisCount);

						double disCountPriceNew = totalPriceOne * disCountNew;// 割引価格
						int disCountPriceNewNext = (int) disCountPriceNew;
						pcdatadto.setDisCountPriceNew(disCountPriceNewNext);
						totalPriceAll = (int) (totalPriceAll - disCountPriceNew);
						model.addAttribute("totalPrice", (totalPriceAll - pointUse));
					} else {
						disCountNew = Double.valueOf("0." + menberCouponDisCount);

						double disCountPriceNew = totalPriceOne * disCountNew;// 割引価格
						int disCountPriceNewNext = (int) disCountPriceNew;
						pcdatadto.setDisCountPriceNew(disCountPriceNewNext);
						totalPriceAll = (int) (totalPriceAll - disCountPriceNew);
						model.addAttribute("totalPrice", (totalPriceAll - pointUse));
					}
				} else {
					System.out.println("会員ランク特典不使用");
					if (pcdatadto.getCouponId() == 0) {
						System.out.println("クーポン使用なし");
						model.addAttribute("totalPrice", (totalPriceAll - pointUse));

					} else {// クーポンを使用した商品はelse
						System.out.println("クーポン使用！");
						CouponDTO coupondto = couponService.selectOne(couponId);
						int disCount = coupondto.getDiscount();
						if (disCount >= 10) {
							double disCountNew = Double.valueOf("0." + disCount);
							disCountPrice = totalPriceOne * disCountNew;// クーポンを使用した商品の割引数取得

							// ここから割引分の値が入っているのでそれをトータルからひく、小数点以下も切り捨てる
							totalPriceAll = (int) (totalPriceAll - disCountPrice);
							model.addAttribute("totalPrice", (totalPriceAll - pointUse));
						} else {
							double disCountNew = Double.valueOf("0.0" + disCount);
							disCountPrice = totalPriceOne * disCountNew;// クーポンを使用した商品の割引数取得

							// ここから割引分の値が入っているのでそれをトータルからひく、小数点以下も切り捨てる
							totalPriceAll = (int) (totalPriceAll - disCountPrice);
							model.addAttribute("totalPrice", (totalPriceAll - pointUse));
						}
					}
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
				if (pcdatadto.getCouponId() == 0) {
					System.out.println("クーポン使用なし");
					model.addAttribute("totalPrice", (totalPriceAll - pointUse));

				} else {// クーポンを使用した商品はelse
					System.out.println("クーポン使用！");
					CouponDTO coupondto = couponService.selectOne(couponId);
					int disCount = coupondto.getDiscount();
					if (disCount >= 10) {
						double disCountNew = Double.valueOf("0." + disCount);
						disCountPrice = totalPriceOne * disCountNew;// クーポンを使用した商品の割引数取得

						// ここから割引分の値が入っているのでそれをトータルからひく、小数点以下も切り捨てる
						totalPriceAll = (int) (totalPriceAll - disCountPrice);
						model.addAttribute("totalPrice", (totalPriceAll - pointUse));
					} else {
						double disCountNew = Double.valueOf("0.0" + disCount);
						disCountPrice = totalPriceOne * disCountNew;// クーポンを使用した商品の割引数取得

						// ここから割引分の値が入っているのでそれをトータルからひく、小数点以下も切り捨てる
						totalPriceAll = (int) (totalPriceAll - disCountPrice);
						model.addAttribute("totalPrice", (totalPriceAll - pointUse));
					}
				}
			}
		}
		if (couponId != 0) {
			model.addAttribute("couponId", couponId);
		} else {
			model.addAttribute("couponId", -1);
		}

		int pointUseCheck = Integer.parseInt(pointUseForm.getPointUse());
		model.addAttribute("pointAfterPrice0", "false");
		if ((totalPriceAll - pointUseCheck) == 0) {// 合計支払金額 - 利用ポイントで値が0になればtrue
			model.addAttribute("pointAfterPrice0", "true");
		}

		return "shopping/productListLayout";

	}

	@GetMapping("/clearing")
	public String getCardClearing(@ModelAttribute CreditForm form, @RequestParam("couponId") int couponId,
			Model model) {
		model.addAttribute("contents", "shopping/clearing::productListLayout_contents");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String getName = auth.getName();

		model.addAttribute("pointUse", 0);
		List<PcDataDTO> cartList = cartService.selectMany(getName);// ログインユーザーのカート情報をすべて取得
		int totalPriceAll = 0;// カート全体価格
		int totalPriceOne = 0;// 各商品価格
		double disCountPrice = 0;

		if (cartList.size() == 1) {
			model.addAttribute("pointUseBtnCheck", "true");
		}
		for (int i = 0; i < cartList.size(); i++) {
			PcDataDTO pcdatadto = cartList.get(i);

			totalPriceAll = totalPriceAll + pcdatadto.getProduct_count() * pcdatadto.getAfterCustomPrice();// カート内の合計金額

			totalPriceOne = pcdatadto.getProduct_count() * pcdatadto.getAfterCustomPrice();// 各商品の金額

			try {
				if (pcdatadto.getMenberCouponCheck().equals("会員ランク特典使用")) {
					System.out.println("会員ランク特典使用");
					MenberCouponDTO menbercoupondto = menberCouponService.selectOne(couponId);
					int menberCouponDisCount = menbercoupondto.getDiscount();
					double disCountNew = 0;
					if (menberCouponDisCount < 10) {

						disCountNew = Double.valueOf("0.0" + menberCouponDisCount);

						double disCountPriceNew = totalPriceOne * disCountNew;// 割引価格
						int disCountPriceNewNext = (int) disCountPriceNew;
						pcdatadto.setDisCountPriceNew(disCountPriceNewNext);
						totalPriceAll = (int) (totalPriceAll - disCountPriceNew);
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
						model.addAttribute("totalPrice", totalPriceAll);

					} else {// クーポンを使用した商品はelse
						System.out.println("クーポン使用！");
						CouponDTO coupondto = couponService.selectOne(couponId);
						int disCount = coupondto.getDiscount();
						if (disCount >= 10) {
							double disCountNew = Double.valueOf("0." + disCount);
							disCountPrice = totalPriceOne * disCountNew;// クーポンを使用した商品の割引数取得

							// ここから割引分の値が入っているのでそれをトータルからひく、小数点以下も切り捨てる
							totalPriceAll = (int) (totalPriceAll - disCountPrice);
							model.addAttribute("totalPrice", totalPriceAll);
						} else {
							double disCountNew = Double.valueOf("0.0" + disCount);
							disCountPrice = totalPriceOne * disCountNew;// クーポンを使用した商品の割引数取得

							// ここから割引分の値が入っているのでそれをトータルからひく、小数点以下も切り捨てる
							totalPriceAll = (int) (totalPriceAll - disCountPrice);
							model.addAttribute("totalPrice", totalPriceAll);
						}
					}
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
				if (pcdatadto.getCouponId() == 0) {
					System.out.println("クーポン使用なし");
					model.addAttribute("totalPrice", totalPriceAll);

				} else {// クーポンを使用した商品はelse
					System.out.println("クーポン使用！");
					CouponDTO coupondto = couponService.selectOne(couponId);
					int disCount = coupondto.getDiscount();
					if (disCount >= 10) {
						double disCountNew = Double.valueOf("0." + disCount);
						disCountPrice = totalPriceOne * disCountNew;// クーポンを使用した商品の割引数取得

						// ここから割引分の値が入っているのでそれをトータルからひく、小数点以下も切り捨てる
						totalPriceAll = (int) (totalPriceAll - disCountPrice);
						model.addAttribute("totalPrice", totalPriceAll);
					} else {
						double disCountNew = Double.valueOf("0.0" + disCount);
						disCountPrice = totalPriceOne * disCountNew;// クーポンを使用した商品の割引数取得

						// ここから割引分の値が入っているのでそれをトータルからひく、小数点以下も切り捨てる
						totalPriceAll = (int) (totalPriceAll - disCountPrice);
						model.addAttribute("totalPrice", totalPriceAll);
					}
				}
			}
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
			@PathVariable("couponId") int couponId, @RequestParam("pointUse") int pointUse, HttpServletRequest request,
			HttpServletResponse response, Model model) {

		if (bindingResult.hasErrors()) {
			return getCardClearing(form, couponId, model);
		}

		// クレジット情報をsessionに格納
		HttpSession session = request.getSession();
		session.setAttribute("digits_3_code", digits_3_code);
		session.setAttribute("cardName", cardName);
		session.setAttribute("cardNumber", cardNumber);
		session.setAttribute("couponId", couponId);
		session.setAttribute("pointUse", pointUse);

		return "redirect:/confirmation";
	}

	@PostMapping("/noClearing/{couponId}")
	public String getNoClearing(@ModelAttribute CreditForm form, RedirectAttributes redirectAttributes,
			@PathVariable("couponId") int couponId, @RequestParam("pointUse") int pointUse, HttpServletRequest request,
			HttpServletResponse response, Model model) {

		// ポイントだけですべて支払った場合は、クレジット情報がいらないので下記の情報が格納される
		HttpSession session = request.getSession();
		session.setAttribute("digits_3_code", "000");
		session.setAttribute("cardName", "noClearing");
		session.setAttribute("cardNumber", "0000000000000000");
		session.setAttribute("couponId", couponId);
		session.setAttribute("pointUse", pointUse);

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

		int creditId = purchaseService.selectCreditId(select_id);// purchaseテーブルからcreditIdを取得

		List<PcDataDTO> purchaseList = purchaseService.selectMany(select_id, creditId);// purchaseテーブルから購入情報を取得
		System.out.println("purchaseList" + purchaseList);
		PcDataDTO pcdatadto = new PcDataDTO();

		int totalPrice = 0;

		for (int i = 0; purchaseList.size() > i; i++) {
			pcdatadto = purchaseList.get(i);
			totalPrice = totalPrice + pcdatadto.getPrice() * pcdatadto.getProduct_count();
			model.addAttribute("totalPrice", totalPrice);
		}
		model.addAttribute("purchaseList", purchaseList);

		String receivingAddress = usegeService.selectAddress(select_id);// ログインユーザーの住所を取得
		model.addAttribute("receivingAddress", receivingAddress);

		// 購入日取得
		Date purchaseDate = purchaseService.selectPurchaseDate(creditId);// ログインユーザーの購入日を取得

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(purchaseDate);
		calendar.add(Calendar.DATE, 3);
		purchaseDate = calendar.getTime();

		Date purchaseDateNext = purchaseDate;
		calendar.setTime(purchaseDate);
		calendar.add(Calendar.DATE, 2);
		purchaseDateNext = calendar.getTime();

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
		List<PcDataDTO> cartList = cartService.selectMany(user_id);// ログインユーザーのカート情報を取得
		if (cartList == null || cartList.size() == 0) {
			model.addAttribute("totalPrice", 0);
			model.addAttribute("notProduct", "yes");

		} else {
			int totalPrice = 0;
			for (int i = 0; i < cartList.size(); i++) {
				PcDataDTO pcdatadto = cartList.get(i);
				totalPrice = totalPrice
						+ pcdatadto.getProduct_count() * (pcdatadto.getPrice() + pcdatadto.getCustomPrice());
				model.addAttribute("totalPrice", totalPrice);
			}
		}

		model.addAttribute("cartList", cartList);
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

		PcDataDTO pcdatadto = pcdataService.selectOne(id);// カスタム更新をした商品情報を取得
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

		customService.UpdateOne(id, select_id, memory, hardDisc, cpu, customPrice);

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

		List<ReviewDTO> reviewList = reviewService.selectMany(productId);// クリックした商品の口コミ情報を取得
		model.addAttribute("reviewList", reviewList);

		PcDataDTO pcdatadto = pcdataService.selectPcName(productId);// クリックした商品の商品名を取得
		model.addAttribute("productId", productId);
		model.addAttribute("pcName", pcdatadto.getPc_name());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String getName = auth.getName();
		model.addAttribute("userId", getName);

		return "shopping/productListLayout";
	}

	@GetMapping("reviewSeeDetail/{id}/{productId}")
	public String getReviewSeeDetail(@ModelAttribute ReviewForm form, @PathVariable("id") int reviewId,
			@PathVariable("productId") int productId, Model model) {
		model.addAttribute("contents", "shopping/reviewSeeDetail::productListLayout_contents");

		ReviewDTO reviewdto = reviewService.selectReviewDetailOne(reviewId);// クリックした商品の口コミのうち、一つの情報を取得
		model.addAttribute("reviewList", reviewdto);

		PcDataDTO pcdatadto = pcdataService.selectPcName(productId);//// クリックした商品の口コミのうち、一つの商品名を取得
		model.addAttribute("pcName", pcdatadto.getPc_name());

		model.addAttribute("productId", productId);

		return "shopping/productListLayout";
	}

	@PostMapping(value = "reviewSeeDetail", params = "delete")
	public String postReviewSeeDetailDelete(@ModelAttribute ReviewForm form, @RequestParam("reviewId") int reviewId,
			@RequestParam("productId") int productId, Model model) {

		reviewService.deleteOne(reviewId);// 選択した口コミ一つを削除

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
			cartService.insertOne(cartdto, product_id, select_id);
		}
		return cart(form, model);
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

			cartService.deleteOne(id, getId);
			return cart(form, model);
		}

		String productCount = form.getProduct_count();
		if (productCount == null) {
		}

		System.out.println("product_id" + id);
		model.addAttribute("contents", "shopping/cart::productListLayout_contents");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String getName = auth.getName();

		int getId = usersService.select_id(getName);

		cartService.deleteOne(id, getId);// クリックした商品をcartテーブルから削除

		customService.deleteCustomOne(id, getId);// クリックした商品のカスタム情報をcustomテーブルから削除

		return "redirect:/cart";
	}

	@PostMapping(value = "/cart/{id}", params = "countUpdate")
	public String postCartCountUpdate(@ModelAttribute @Validated(GroupOrder.class) CartForm form,
			BindingResult bindingResult, Model model, RedirectAttributes redirectattributes,
			@PathVariable("id") int productId) {

		if (bindingResult.hasErrors()) {
			return cart(form, model);
		}

		int productcount = Integer.parseInt(form.getProduct_count());
		if (productcount == 0) {
			return cart(form, model);
		}

		String productCount = form.getProduct_count();
		int newProductCount = Integer.parseInt(productCount);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String getName = auth.getName();

		int userId = usersService.select_id(getName);
		cartService.updateOne(productId, newProductCount, userId);// 購入数を入力された数に更新

		return "redirect:/cart";

	}

	@GetMapping("/confirmation")
	public String getConfirmation(@ModelAttribute PcDataForm from, CreditForm creditForm, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		model.addAttribute("contents", "shopping/confirmation::productListLayout_contents");
		model.addAttribute("pointUseCheck", (int) session.getAttribute("pointUse"));
		System.out.println("pointUseCheck" + (int) session.getAttribute("pointUse"));

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String getName = auth.getName();

		List<PcDataDTO> cartList = cartService.selectMany(getName);// ログインユーザーのカート情報をすべて取得
		int totalPriceAll = 0;// カート全体価格
		int totalPriceOne = 0;// 各商品価格
		for (int i = 0; i < cartList.size(); i++) {
			PcDataDTO pcdatadto = cartList.get(i);
			totalPriceAll = totalPriceAll + pcdatadto.getProduct_count() * pcdatadto.getAfterCustomPrice();
			totalPriceOne = pcdatadto.getProduct_count() * pcdatadto.getAfterCustomPrice();
			pcdatadto.setTotalPriceOne(totalPriceOne);

			try {
				if (pcdatadto.getMenberCouponCheck().equals("会員ランク特典使用")) {
					System.out.println("会員ランク特典使用");
					MenberCouponDTO menbercoupondto = menberCouponService.selectOne(pcdatadto.getCouponId());// カート情報のクーポンIDをもとに利用クーポン情報を取得
					int menberCouponDisCount = menbercoupondto.getDiscount();
					double disCountNew = 0;
					pcdatadto.setCouponCheck(true);
					if (menberCouponDisCount < 10) {
						disCountNew = Double.valueOf("0.0" + menberCouponDisCount);

						double disCountPriceNew = totalPriceOne * disCountNew;// 割引価格
						int disCountPriceNewNext = (int) disCountPriceNew;
						pcdatadto.setDisCountPriceNew(disCountPriceNewNext);
						totalPriceAll = totalPriceAll - disCountPriceNewNext;
						pcdatadto.setTotalPrice(totalPriceAll);
						model.addAttribute("totalPrice", totalPriceAll);
					} else {
						disCountNew = Double.valueOf("0." + menberCouponDisCount);

						double disCountPriceNew = totalPriceOne * disCountNew;// 割引価格
						int disCountPriceNewNext = (int) disCountPriceNew;
						pcdatadto.setDisCountPriceNew(disCountPriceNewNext);
						totalPriceAll = totalPriceAll - disCountPriceNewNext;
						pcdatadto.setTotalPrice(totalPriceAll);
						model.addAttribute("totalPrice", totalPriceAll);
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
			model.addAttribute("pointRate", pointRate);
			String pointRateString = String.valueOf(pointRate);
			String pointRateNew = 0.0 + pointRateString;
			double pointRateDouble = Double.parseDouble(pointRateNew);
			int point = (int) (totalPriceAll * pointRateDouble);
			model.addAttribute("point", point);// 購入後に付くポイント
			model.addAttribute("pointminusTotalPrice", (totalPriceAll - (int) session.getAttribute("pointUse")));// 合計金額から利用ポイント分を引いた金額
			int pointminusTotalPrice = (totalPriceAll - (int) session.getAttribute("pointUse"));

			if (pointminusTotalPrice != totalPriceAll) {// ポイントを使用していればtrue
				session.setAttribute("pointminusTotalPrice", (int) session.getAttribute("pointUse"));
			} else {
				session.setAttribute("pointminusTotalPrice", 0);
			}

			session.setAttribute("point", point);
			model.addAttribute("cartList", cartList);

			// クレジット情報をsessionから取得
			String digits_3_code = (String) session.getAttribute("digits_3_code");
			String cardName = (String) session.getAttribute("cardName");
			String cardNumber = (String) session.getAttribute("cardNumber");
			int couponId = (int) session.getAttribute("couponId");
			int pointUse = (int) session.getAttribute("pointUse");
			System.out.println("pointUse2" + pointUse);

			model.addAttribute("digits_3_code", digits_3_code);
			model.addAttribute("cardName", cardName);
			model.addAttribute("cardNumber", cardNumber);
			model.addAttribute("couponId", couponId);
			model.addAttribute("pointUse", pointUse);

		}
		return "shopping/productListLayout";
	}

	@PostMapping("/confirmation")
	public String postConfirmation(@ModelAttribute PcDataForm form, RedirectAttributes redirectattributes,
			@RequestParam("digits_3_code") String digits_3_code, @RequestParam("cardName") String cardName,
			@RequestParam("cardNumber") String cardNumber, @RequestParam("totalPrice") int totalPrice,
			@RequestParam("couponId") int couponId, HttpServletRequest request, HttpServletResponse response,
			Model model) throws MessagingException {

		int pointminusTotalPrice = (int) session.getAttribute("pointminusTotalPrice");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String getName = auth.getName();
		System.out.println("getname" + getName);
		int select_id = usersService.select_id(getName);

		boolean result = cartService.selectProductCount(select_id);// ユーザーが購入した、各商品の購入数を取得

		int point = (int) session.getAttribute("point");

		if (result == false) {
			redirectattributes.addFlashAttribute("result", "在庫数が購入数より少ない商品、又は0の商品があります。再度ご確認の上決済画面へ進んでください。");
			return "redirect:/cart";
		}

		CreditDTO creditdto = new CreditDTO();
		creditdto.setDigits_3_code(digits_3_code);
		creditdto.setCardName(cardName);
		creditdto.setCardNumber(cardNumber);

		creditService.clearingInsertOne(creditdto, select_id, totalPrice);// creditテーブルにクレジット情報を格納

		int creditId = creditService.selectMaxId();// 上記で格納した情報のクレジットIDを取得

		List<CartDTO> cartList = cartService.purchaseSelectMany(select_id);// ログインユーザーのカート情報を取得

		PurchaseDTO purchasedto = new PurchaseDTO();

		for (int i = 0; i < cartList.size(); i++) {

			CartDTO cartdto = cartList.get(i);

			int productId = cartdto.getProduct_id();
			int productid = cartdto.getProduct_id();
			int customId = customService.selectCustomId(productId, select_id);
			int purchaseCount = cartdto.getProduct_count();
			int couponCheck = cartdto.getCouponId();

			try {

				if (cartdto.getMenberCouponCheck().equals("会員ランク特典使用")) {

					// purchaseテーブルに購入データを格納(会員ランククーポンを利用して購入した場合)
					purchaseService.insertMenberCoupon(purchasedto, productid, purchaseCount, select_id, creditId,
							customId, couponId, point, pointminusTotalPrice);

				} else if (couponCheck > 0) {
					System.out.println("クーポン使用");

					// purchaseテーブルに購入データを格納(期間限定クーポンを利用して購入した場合)
					purchaseService.insert(purchasedto, productid, purchaseCount, select_id, creditId, customId,
							couponId, point, pointminusTotalPrice);
				} else {
					System.out.println("クーポン未使用");

					// purchaseテーブルに購入データを格納(クーポンを利用せず購入した場合)
					purchaseService.insertNotCoupon(purchasedto, productid, purchaseCount, select_id, creditId,
							customId, point, pointminusTotalPrice);
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
				if (couponCheck > 0) {
					System.out.println("クーポン使用");

					// purchaseテーブルに購入データを格納(期間限定クーポンを利用して購入した場合)
					purchaseService.insert(purchasedto, productid, purchaseCount, select_id, creditId, customId,
							couponId, point, pointminusTotalPrice);
				} else {
					System.out.println("クーポン未使用");

					// purchaseテーブルに購入データを格納(クーポンを利用せず購入した場合)
					purchaseService.insertNotCoupon(purchasedto, productid, purchaseCount, select_id, creditId,
							customId, point, pointminusTotalPrice);
				}
			}
			int purchaseId = purchaseService.selectMaxPurchaseId();// 上記で格納した購入データのIDを取得
			customService.pruchaseIdUpdate(purchaseId, productId, select_id);// customテーブルの購入チェックをアップデート
			cartService.idInsertOne(purchaseId, productId, select_id);// cartテーブルの購入チェックをアップデート
		}
		//各ユーザーに購入完了mail送信
		mailService.purchaseSendMail(getName);
		mailService.adminSendMail(getName);

		return getAfter_purchase(model);
	}

	@GetMapping("/challengeProgramming")
	public String getChallengeProgramming(@ModelAttribute ChallengeProgrammingForm form, Model model) {
		model.addAttribute("contents", "shopping/challengeProgramming::productListLayout_contents");

		List<ChallengeProgrammingDTO> challengeProgrammingdto = challengeProgrammingService.projectSelectMany();// 商品の情報をすべて取得

		// 時間をhh:mm表記に変更する
		for (int x = 0; challengeProgrammingdto.size() > x; x++) {
			ChallengeProgrammingDTO challengeProgrammingOne = challengeProgrammingdto.get(x);
			System.out.println("test" + challengeProgrammingOne);
			String fixableTimeFromGetTime = new SimpleDateFormat("ah:mm")
					.format(challengeProgrammingOne.getFixableTimeFrom());
			String fixableTimeToGetTime = new SimpleDateFormat("ah:mm")
					.format(challengeProgrammingOne.getFixableTimeTo());
			challengeProgrammingOne.setFixableTimeFromGetTime(fixableTimeFromGetTime);
			challengeProgrammingOne.setFixableTimeToGetTime(fixableTimeToGetTime);

		}

		model.addAttribute("challengeProgrammingList", challengeProgrammingdto);

		return "shopping/productListLayout";
	}

	@GetMapping("/challengeProgrammingDetail/{id}")
	public String getChallengeProgrammingDetail(@ModelAttribute ChallengeProgrammingForm form,
			@PathVariable("id") int productId, Model model) {
		model.addAttribute("contents", "shopping/challengeProgrammingDetail::productListLayout_contents");

		ChallengeProgrammingDTO challengeProgrammingdtoOne = challengeProgrammingService.projectSelectOne(productId);// 選択した商品の情報をすべて取得

		// 時間をhh:mm表記に変更する
		String fixableTimeFromGetTime = new SimpleDateFormat("ah:mm")
				.format(challengeProgrammingdtoOne.getFixableTimeFrom());
		String fixableTimeToGetTime = new SimpleDateFormat("ah:mm")
				.format(challengeProgrammingdtoOne.getFixableTimeTo());
		challengeProgrammingdtoOne.setFixableTimeFromGetTime(fixableTimeFromGetTime);
		challengeProgrammingdtoOne.setFixableTimeToGetTime(fixableTimeToGetTime);
		model.addAttribute("challengeProgrammingOne", challengeProgrammingdtoOne);

		// 契約productIdを取得
		model.addAttribute("productId", challengeProgrammingdtoOne.getId());

		// html(challengeProgrammingDetail)に契約productIDを渡す
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String getName = auth.getName();
		int userId = usersService.select_id(getName);
		// 契約したユーザーのDBのuserIDを取得
		model.addAttribute("userId", userId);

		return "shopping/productListLayout";
	}

	@GetMapping("/challengeProgrammingBeforeContract/{id}")
	public String getChallengeProgrammingBeforeContract(@ModelAttribute ChallengeProgrammingContractForm form,
			@PathVariable("id") int productId, Model model) {
		model.addAttribute("contents", "shopping/challengeProgrammingBeforeContract::productListLayout_contents");

		model.addAttribute("productId", productId);

		return "shopping/productListLayout";
	}

	@PostMapping("/challenge_programmingContract")
	public String postChallenge_programmingContract(
			@ModelAttribute @Validated(GroupOrder.class) ChallengeProgrammingContractForm form,
			BindingResult bindingResult, @RequestParam("productId") int productId, Model model) {
		model.addAttribute("contents", "shopping/challengeProgrammingContract::productListLayout_contents");

		// バリデーションに引っかかると前のページに戻る
		if (bindingResult.hasErrors()) {
			return getChallengeProgrammingBeforeContract(form, productId, model);
		}

		// 契約したユーザーのDBのuserIDを取得
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String getName = auth.getName();
		int userId = usersService.select_id(getName);

		// すでに契約していないかチェックする
		int duplicateCheck = challengeProgrammingContractService.duplicateCheck(userId);
		if (duplicateCheck > 0) {
			model.addAttribute("errorMessage", "すでに何らかの契約をされています。同時進行は１つの契約までになります。");
			return getChallengeProgrammingBeforeContract(form, productId, model);
		}

		ChallengeProgrammingContractDTO challengeProgrammingContractdto = new ChallengeProgrammingContractDTO();

		String mailAddress = form.getMailAddress();
		String phoneNumber = form.getPhoneNumber();
		String digits3Code = form.getDigits_3_code();
		String cardName = form.getCardName();
		String cardNumber = form.getCardNumber();
		// チャットメッセージをすべて取得
		ChallengeProgrammingMessageDTO challengeProgrammingMessagedto = challengeProgrammingMessageService.selectOne();

		// 入力された情報と契約者情報をDBに格納
		challengeProgrammingContractService.insertOne(challengeProgrammingContractdto, mailAddress, phoneNumber,
				digits3Code, cardName, cardNumber, userId, productId, challengeProgrammingMessagedto);
		// 契約された情報を更新
		challengeProgrammingService.contractUpdate(userId, productId);

		// リターン先をmodelのコンテンツから変更
		// 初めに表示されるメッセージを取得
		//		ChallengeProgrammingContractDTO challengeProgrammingContractTeacherMessage = challengeProgrammingContractService.teacherMessege1Select(productId);
		//		challengeProgrammingContractTeacherMessage.setProductId(productId);
		//		challengeProgrammingContractTeacherMessage.setMyMessage1("bloc");
		//		model.addAttribute("chatContents",challengeProgrammingContractTeacherMessage);
		//		model.addAttribute("chatCheck","no");
		//		
		//		
		//		//進行バーの初期表示設定(挨拶)
		//		model.addAttribute("progressStatus",1);

		ChallengeProgrammingTradeForm returnForm = new ChallengeProgrammingTradeForm();
		return postChallengeProgrammingTrade(returnForm, productId, model);
	}

	@PostMapping("/challengeProgrammingTrade")
	public String postChallengeProgrammingTrade(@ModelAttribute ChallengeProgrammingTradeForm form,
			@RequestParam("productId") int productId, Model model) {
		model.addAttribute("contents", "shopping/challengeProgrammingTrade::productListLayout_contents");

		// チャットが完了しているか確認
		int chatCheck = challengeProgrammingContractService.selectChatCheck(productId);

		// チャットが完了していなければチャット機能を実施する
		if (chatCheck == 1) {
			// 初めに表示されるメッセージを取得
			ChallengeProgrammingContractDTO challengeProgrammingContractTeacherMessage = challengeProgrammingContractService
					.teacherMessege1Select(productId);
			challengeProgrammingContractTeacherMessage.setProductId(productId);
			model.addAttribute("productId", challengeProgrammingContractTeacherMessage.getProductId());
			model.addAttribute("chatContents", challengeProgrammingContractTeacherMessage);
			model.addAttribute("chatCheck", "no");

			// 進行バーの初期表示設定(挨拶)
			model.addAttribute("progressStatus", 1);

			// チャットが完了している場合はチャット機能はさせない
		} else if (chatCheck == 0) {
			// チャットメッセージをすべて取得
			ChallengeProgrammingContractDTO challengeProgrammingContractTmMm = challengeProgrammingContractService
					.tm3Mm3select(productId);
			challengeProgrammingContractTmMm.setProductId(productId);
			model.addAttribute("productId", challengeProgrammingContractTmMm.getProductId());
			model.addAttribute("chatContents", challengeProgrammingContractTmMm);
			model.addAttribute("chatCheck", "yes");

			// 進行バーの初期表示設定(日程設定)
			model.addAttribute("progressStatus", 2);
		}

		// 日程設定を行っているかcheckする(行っていなければ「未設定」が取れる)
		String lessonDay = challengeProgrammingContractService.lessonDaySelectOne(productId);

		if (!lessonDay.equals("未設定")) {
			// チャットメッセージをすべて取得
			ChallengeProgrammingContractDTO challengeProgrammingContractTmMm = challengeProgrammingContractService
					.tm3Mm3select(productId);
			challengeProgrammingContractTmMm.setProductId(productId);
			model.addAttribute("productId", challengeProgrammingContractTmMm.getProductId());
			model.addAttribute("chatContents", challengeProgrammingContractTmMm);
			model.addAttribute("chatCheck", "yes");
			model.addAttribute("lessonDay", lessonDay);
			System.out.print("lessonDay" + lessonDay);
			model.addAttribute("lessonDayCheck", "yes");
			model.addAttribute("lessonEnd", "no");
			// 進行バーの初期表示設定(持ち物チェック)
			model.addAttribute("progressStatus", 3);
		} else {
			model.addAttribute("lessonDay", lessonDay);
			model.addAttribute("lessonDayCheck", "no");
		}

		// 持ち物チェックを行っているかcheckする(行っていなければ「未設定」が取れる)
		String belongngs = challengeProgrammingContractService.belongngsSelectOne(productId);

		if (chatCheck == 0 & belongngs.equals("持ち物チェック完了")) {
			// チャットメッセージをすべて取得
			ChallengeProgrammingContractDTO challengeProgrammingContractTmMm = challengeProgrammingContractService
					.tm3Mm3select(productId);
			challengeProgrammingContractTmMm.setProductId(productId);
			model.addAttribute("belongngs", "yes");
			// 進行バーの初期表示設定(場所確認)
			model.addAttribute("progressStatus", 4);

		} else {
			model.addAttribute("belongngs", "no");
		}

		// 場所確認を行っているかcheckする(行っていなければ「未設定」が取れる)
		String location = challengeProgrammingContractService.locationSelectOne(productId);

		if (belongngs.equals("持ち物チェック完了") & location.equals("場所確認完了")) {
			ChallengeProgrammingContractDTO challengeProgrammingContractTmMm = challengeProgrammingContractService
					.tm3Mm3select(productId);
			challengeProgrammingContractTmMm.setProductId(productId);
			model.addAttribute("location", "yes");
			model.addAttribute("javaIconModify", "yes");
			// 進行バーの初期表示設定(場所確認)
			model.addAttribute("progressStatus", 5);
		} else {
			model.addAttribute("location", "no");
		}

		String lessonCheck = challengeProgrammingContractService.lessonCheckSelectOne(productId);

		if (location.equals("場所確認完了") & lessonCheck.equals("講座中")) {
			model.addAttribute("lessonCheck", "yes");
			model.addAttribute("lessonEnd", "no");
			// 進行バーの初期表示設定(場所確認)
			model.addAttribute("progressStatus", 6);
		} else if (location.equals("場所確認完了") & lessonCheck.equals("講座終了")) {
			model.addAttribute("progressStatus", 7);
			model.addAttribute("lessonCheck", "yes");
			ChallengeProgrammingContractDTO challengeprogrammingcontractdto = new ChallengeProgrammingContractDTO();
			challengeprogrammingcontractdto = challengeProgrammingContractService.startAndEndDateSelectOne(productId);
			Date startDate = challengeprogrammingcontractdto.getStartDate();
			Date endDate = challengeprogrammingcontractdto.getEndDate();
			SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String simpleStartDate = simpleFormat.format(startDate);
			String simpleEndDate = simpleFormat.format(endDate);
			model.addAttribute("startDate", simpleStartDate);
			model.addAttribute("endDate", simpleEndDate);

			model.addAttribute("lessonEnd", "yes");
		} else {
			model.addAttribute("lessonCheck", "no");
		}

		return "shopping/productListLayout";
	}

	@GetMapping("/chatReturn1/{id}")
	public String getChatReturn(@PathVariable("id") int productId, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		model.addAttribute("contents", "shopping/challengeProgrammingTrade::productListLayout_contents");

		ChallengeProgrammingContractDTO challengeProgrammingContractTmMm = challengeProgrammingContractService
				.tm1Mm1Select(productId);
		model.addAttribute("chatContents", challengeProgrammingContractTmMm);
		model.addAttribute("chatCheck", "no");

		// 進行バーの初期表示設定(挨拶)
		model.addAttribute("progressStatus", 1);

		return "shopping/productListLayout";
	}

	@GetMapping("/chatReturn2/{id}")
	public String getChatReturn2(@PathVariable("id") int productId, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		model.addAttribute("contents", "shopping/challengeProgrammingTrade::productListLayout_contents");

		ChallengeProgrammingContractDTO challengeProgrammingContractTmMm = challengeProgrammingContractService
				.tm2Mm2Select(productId);
		model.addAttribute("chatContents", challengeProgrammingContractTmMm);
		model.addAttribute("chatCheck", "no");

		// 進行バーの初期表示設定(挨拶)
		model.addAttribute("progressStatus", 1);

		return "shopping/productListLayout";
	}

	@GetMapping("/chatReturn3/{id}")
	public String getChatReturn3(@PathVariable("id") int productId, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		model.addAttribute("contents", "shopping/challengeProgrammingTrade::productListLayout_contents");

		ChallengeProgrammingContractDTO challengeProgrammingContractTmMm = challengeProgrammingContractService
				.tm3Mm3select(productId);
		model.addAttribute("chatContents", challengeProgrammingContractTmMm);
		model.addAttribute("chatCheck", "no");

		// 進行バーの初期表示設定(挨拶)
		model.addAttribute("progressStatus", 1);

		return "shopping/productListLayout";
	}

	@GetMapping("/progressStatus/{id}")
	public String getProgressStatus(@ModelAttribute ChallengeProgrammingTradeForm form,
			@PathVariable("id") int productId, Model model) {
		model.addAttribute("contents", "shopping/challengeProgrammingTrade::productListLayout_contents");
		challengeProgrammingContractService.chatComplete(productId);

		return postChallengeProgrammingTrade(form, productId, model);
	}

	@GetMapping("/scheduleAdjustment/{id}")
	public String getScheduleAdjustment(@PathVariable("id") int productId, Model model) {
		model.addAttribute("contents", "shopping/scheduleAdjustment::productListLayout_contents");

		// 講座日程設定	
		Calendar calendar = Calendar.getInstance();
		int getYear = calendar.get(Calendar.YEAR);
		int getMonth = calendar.get(Calendar.MONTH);
		int getDate = calendar.get(Calendar.DATE);

		// 今月の最終日を取得\
		calendar.set(getYear, getMonth + 1, 1);
		calendar.add(Calendar.DATE, -1);
		int lastDay = calendar.get(Calendar.DATE);

		calendar.set(getYear, getMonth, getDate);

		// 設定日を除いた一週間分から選択してもらう
		String[] getDayList = new String[7];
		for (int x = 1; x < 8; x++) {
			int ifX = x;
			int oneMove = getDate + x;
			// 月最終日から次の月の頭になる場合に入る処理
			if (oneMove > lastDay) {
				System.out.println("月初め");
				getDate = 0;
				int z = 1;
				for (int y = 1; y <= 7 - ifX; ifX++) {

					oneMove = getDate + z++;
					calendar.set(getYear, getMonth + 2, 1);
					int month = calendar.get(Calendar.MONTH);
					if (month == 0) {
						month = 12;
					}
					getDayList[ifX - 1] = month + "月" + oneMove + "日";

				}

				for (int y = 0; y <= 7 - ifX;) {
					oneMove = getDate + z++;
					calendar.set(getYear, getMonth + 2, 1);
					int month = calendar.get(Calendar.MONTH);
					if (month == 0) {
						month = 12;
					}
					getDayList[ifX - 1] = month + "月" + oneMove + "日";
					System.out.println(Arrays.toString(getDayList));
					model.addAttribute("dayList", getDayList);
					model.addAttribute("productId", productId);

					return "shopping/productListLayout";
				}

				if (ifX == 7) {
					System.out.println(Arrays.toString(getDayList));
					model.addAttribute("dayList", getDayList);
					model.addAttribute("productId", productId);

					return "shopping/productListLayout";
				}
			} else {
				System.out.println("月初めなし");
				calendar.set(getYear, getMonth + 1, 1);
				int month = calendar.get(Calendar.MONTH);
				if (month == 0) {
					month = 12;
				}
				getDayList[x - 1] = month + "月" + oneMove + "日";
			}

		}
		System.out.println(Arrays.toString(getDayList));
		model.addAttribute("dayList", getDayList);
		model.addAttribute("productId", productId);

		return "shopping/productListLayout";
	}

	@PostMapping("/acheduleAdjustment/{id}")
	public String postAcheduleAdjustment(@PathVariable("id") int productId, @RequestParam("day") String lessonDay,
			Model model) {

		challengeProgrammingContractService.lessonDayInsertOne(lessonDay, productId);

		ChallengeProgrammingTradeForm form = new ChallengeProgrammingTradeForm();

		return postChallengeProgrammingTrade(form, productId, model);

	}

	@GetMapping("/belongingsCheck/{id}")
	public String getBelongingsCheck(@PathVariable("id") int productId, Model model) {
		model.addAttribute("contents", "shopping/belongingsCheck::productListLayout_contents");

		ChallengeProgrammingDTO challengeprogrammingdto = challengeProgrammingService.selectBelongings(productId);
		model.addAttribute("belongingsList", challengeprogrammingdto);

		model.addAttribute("productId", productId);

		return "shopping/productListLayout";
	}

	@PostMapping("/belongingsCheck")
	public String postBelongingsCheck(@RequestParam("id") int productId, Model model) {
		model.addAttribute("productId", productId);

		// 持ち物を確認した証拠をDBに格納
		challengeProgrammingContractService.belongingsCheckInsertOne(productId);

		ChallengeProgrammingTradeForm form = new ChallengeProgrammingTradeForm();
		return postChallengeProgrammingTrade(form, productId, model);
	}

	@GetMapping("/locationConfirmation/{id}")
	public String getLocationConfirmation(@ModelAttribute @PathVariable("id") int productId, Model model) {
		model.addAttribute("contents", "shopping/locationConfirmation::productListLayout_contents");

		model.addAttribute("productId", productId);

		String location = challengeProgrammingService.locationSelectOne(productId);
		model.addAttribute("location", location);

		return "shopping/productListLayout";
	}

	@PostMapping("/locationConfirmation")
	public String postLocationConfirmation(@RequestParam("id") int productId, Model model) {
		model.addAttribute("productId", productId);

		// 持ち物を確認した証拠をDBに格納
		//
		challengeProgrammingContractService.locationConfirmationInsertOne(productId);

		ChallengeProgrammingTradeForm form = new ChallengeProgrammingTradeForm();
		return postChallengeProgrammingTrade(form, productId, model);
	}

	@GetMapping("/currentDayTrading/{id}")
	public String getCurrentDayTrading(@PathVariable("id") int productId, Model model) {
		model.addAttribute("contents", "shopping/currentDayTrading::productListLayout_contents");
		// QRコード表示設定
		model.addAttribute("qrcodeCreate", "yes");
		model.addAttribute("qrcodeCheck", "no");

		return "shopping/productListLayout";
	}

	@GetMapping("/qrCodeCreate")
	public String getQrCodeCreate(Model model) {
		model.addAttribute("contents", "shopping/currentDayTrading::productListLayout_contents");

		// QRコード生成処理(読み込み遷移先、横幅、縦幅、img名)
		String content = "https://github.com/YumaMaruyama/ITworksTRYshopping/blob/main/src/main/resources/templates/shopping/qrcodeFormInfomation.txt";
		int width = 100;
		int height = 100;
		String output = "qrcode.png";

		try {
			QRCodeWriter qrWriter = new QRCodeWriter();

			// 読み込み遷移先、横幅、縦幅、img名を格納
			BitMatrix bitMatrix = qrWriter.encode(content, BarcodeFormat.QR_CODE, width, height);
			// コンソール内で画像出力
			System.out.println("bitMatrix" + bitMatrix);

			BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);

			// QRコード表示設定
			model.addAttribute("qrcodeCheck", "yes");
			model.addAttribute("qrcodeCreate", "no");

			// 画像を画像ファイルに出力する
			ImageIO.write(image, "png", new File(output));

		} catch (WriterException | IOException e) {
			System.err.println("[" + content + "] error発生");
			e.printStackTrace();
		}

		return "shopping/productListLayout";
	}

	@PostMapping("/currentDayTrading")
	public String postCurrentDayTrading(@RequestParam("id") int productId, Model model) {

		// QRコード生成処理
		String content = "https://github.com/YumaMaruyama/ITworksTRYshopping/blob/main/src/main/resources/templates/shopping/qrcodeFormInfomation.txt";
		int width = 100;
		int height = 100;
		String output = "qrcode.png";

		try {
			QRCodeWriter qrWriter = new QRCodeWriter();

			// QRWriter.encode()にエンコード対象の文字列、バーコードに埋め込みたい情報出力バーコード書式、画像のwidth、画像のheightを格納
			BitMatrix bitMatrix = qrWriter.encode(content, BarcodeFormat.QR_CODE, width, height);
			System.out.println(bitMatrix);
			BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);

			// 画像を画像ファイルに出力する
			ImageIO.write(image, "png", new File(output));

		} catch (WriterException | IOException e) {
			e.printStackTrace();
		}

		return "shopping/productListLayout";
	}

	@GetMapping("/lessonStart/{id}")
	public String getLessonStart(@ModelAttribute @PathVariable("id") int productId, LessonStartForm form, Model model) {
		model.addAttribute("contents", "shopping/lessonStart::productListLayout_contents");

		model.addAttribute("productId", productId);

		return "shopping/productListLayout";
	}

	@PostMapping("/lessonStart")
	public String postLessonStart(@ModelAttribute @RequestParam("id") int productId,
			@Validated(GroupOrder.class) LessonStartForm form, BindingResult bindingResult, Model model)
			throws MessagingException {

		if (bindingResult.hasErrors()) {
			return getLessonStart(productId, form, model);
		}

		// パスワードが正しいかチェックする
		boolean passwordCheck = challengeProgrammingLessonStartPasswordService.lessonStartPasswordCheck(form);

		// 正しければ…
		if (passwordCheck == true) {
			ChallengeProgrammingTradeForm challengeprogrammingtradeform = new ChallengeProgrammingTradeForm();
			challengeProgrammingContractService.lessonCheckInsertOne(productId);
			Date nowDate = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String simpleDate = format.format(nowDate);
			challengeProgrammingContractService.startDateInsertOne(productId, simpleDate);

			return postChallengeProgrammingTrade(challengeprogrammingtradeform, productId, model);
		} else {
			model.addAttribute("errorMessage", "開始パスワードが正しくありません");
			return getLessonStart(productId, form, model);
		}
	}

	@GetMapping("/lessonEnd/{id}")
	public String getLessonEnd(@ModelAttribute LessonEndForm form, @PathVariable("id") int productId, Model model) {
		model.addAttribute("contents", "shopping/lessonEnd::productListLayout_contents");

		model.addAttribute("productId", productId);

		return "shopping/productListLayout";
	}

	@PostMapping("/lessonEnd")
	public String postLessonEnd(@ModelAttribute @Validated(GroupOrder.class) LessonEndForm form,
			BindingResult bindingResult, @RequestParam("id") int productId, Model model) {

		if (bindingResult.hasErrors()) {
			return getLessonEnd(form, productId, model);
		}

		// パスワードが正しいかチェックする
		boolean passwordCheck = challengeProgrammingLessonStartPasswordService.lessonEndPasswordCheck(form);

		// 正しければ…
		if (passwordCheck == true) {
			ChallengeProgrammingTradeForm challengeprogrammingtradeform = new ChallengeProgrammingTradeForm();
			challengeProgrammingContractService.lessonCheckUpdateOne(productId);

			Date nowDate = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String simpleDate = format.format(nowDate);
			challengeProgrammingContractService.EndDateInsertOne(productId, simpleDate);

			return postChallengeProgrammingTrade(challengeprogrammingtradeform, productId, model);
		} else {
			model.addAttribute("errorMessage", "完了パスワードが正しくありません");
			return getLessonEnd(form, productId, model);
		}
	}

	@GetMapping("/lessonEvaluation/{id}")
	public String getLessonEvaluation(@ModelAttribute LessonEvaluationForm form, @PathVariable("id") int productId,
			Model model) {
		model.addAttribute("contents", "shopping/lessonEvaluation::productListLayout_contents");

		model.addAttribute("productId", productId);

		return "shopping/productListLayout";
	}

	@PostMapping("/lessonEvaluation")
	public String postLessonEvaluation(@ModelAttribute @Validated(GroupOrder.class) LessonEvaluationForm form,
			BindingResult bindingResult, @RequestParam("id") int productId, Model model) {
		model.addAttribute("contents", "shopping/lessonEvaluation::productListLayout_contents");

		if (bindingResult.hasErrors()) {
			return getLessonEvaluation(form, productId, model);
		}

		// ユーザーのIDを取得
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String getName = auth.getName();
		int userId = usersService.select_id(getName);

		// 評価内容を評価テーブルに格納
		ChallengeProgrammingEvaluationDTO challengeprogrammingevaluationDTO = new ChallengeProgrammingEvaluationDTO();
		challengeProgrammingEvaluationService.evaluationInsertOne(userId, productId, challengeprogrammingevaluationDTO,
				form);

		// 契約情報を取得
		ChallengeProgrammingDTO challengeprogrammingDTO = challengeProgrammingService.lessonSelectOne(productId);
		// 契約情報を履歴テーブルに格納
		ChallengeProgrammingHistoryDTO challengeProgrammingHistoryDTO = new ChallengeProgrammingHistoryDTO();
		challengeProgrammingHistoryService.historyInsertOne(challengeprogrammingDTO, challengeProgrammingHistoryDTO);
		// 契約情報を削除
		challengeProgrammingContractService.deleteOne(productId);
		// 契約情報の上書き
		challengeProgrammingService.updateOne(productId);

		ChallengeProgrammingForm challengeprogrammingForm = new ChallengeProgrammingForm();
		return getChallengeProgramming(challengeprogrammingForm, model);
	}

	@GetMapping("/evaluationList/{id}")
	public String getEvaluationList(@PathVariable("id") int productId, Model model) {
		model.addAttribute("contents", "shopping/evaluationList::productListLayout_contents");

		// 選択した先生の評価をすべて取得
		List<ChallengeProgrammingEvaluationDTO> challengeprogrammingevaluationDTOList = challengeProgrammingEvaluationService
				.evaluationSelectMany(productId);
		double totalRate = 0;
		for (int i = 0; challengeprogrammingevaluationDTOList.size() > i; i++) {
			ChallengeProgrammingEvaluationDTO challengeprogrammingevaluationDTOOne = challengeprogrammingevaluationDTOList
					.get(i);
			int rate = challengeprogrammingevaluationDTOOne.getRate();
			totalRate = totalRate + rate;
			if (rate == 1) {
				challengeprogrammingevaluationDTOOne.setConversionRate("悪い(1)");
			} else if (rate == 2) {
				challengeprogrammingevaluationDTOOne.setConversionRate("普通(2)");
			} else if (rate == 3) {
				challengeprogrammingevaluationDTOOne.setConversionRate("良い(3)");
			} else {
				challengeprogrammingevaluationDTOOne.setConversionRate("素晴らしい(4)");
			}
		}

		double averageRate = totalRate / challengeprogrammingevaluationDTOList.size();
		if (averageRate >= 3.75) {
			model.addAttribute("starRate", "4");
			model.addAttribute("starRateDetail", "4");
		} else if ((averageRate >= 3.25) && (averageRate <= 3.74)) {
			model.addAttribute("starRate", "3.5");
			model.addAttribute("starRateDetail", "3.5");
		} else if ((averageRate >= 2.75) && (averageRate <= 3.24)) {
			model.addAttribute("starRate", "3");
			model.addAttribute("starRateDetail", "3");
		} else if ((averageRate >= 2.25) && (averageRate <= 2.74)) {
			model.addAttribute("starRate", "2.5");
			model.addAttribute("starRateDetail", "2.5");
		} else if ((averageRate >= 1.75) && (averageRate <= 2.24)) {
			model.addAttribute("starRate", "2");
			model.addAttribute("starRateDetail", "2");
		} else if ((averageRate >= 1.25) && (averageRate <= 1.74)) {
			model.addAttribute("starRate", "1.5");
			model.addAttribute("starRateDetail", "1.5");
		} else {
			model.addAttribute("starRate", "1");
			model.addAttribute("starRateDetail", "1");
		}

		System.out.println("averageRate" + averageRate);
		System.out.println("totalRate" + totalRate);
		System.out.println("dtoSize" + challengeprogrammingevaluationDTOList.size());

		model.addAttribute("evaluationList", challengeprogrammingevaluationDTOList);

		return "shopping/productListLayout";
	}

	@GetMapping("/lessonHistory")
	public String getLessonHistory(Model model) {
		model.addAttribute("contents", "shopping/lessonHistory::productListLayout_contents");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String getName = auth.getName();
		int userId = usersService.select_id(getName);

		// ユーザーごとの受講した講座をすべて取得
		List<ChallengeProgrammingHistoryDTO> challengeprogramminghistoryDTOList = challengeProgrammingHistoryService
				.historySelectMany(userId);
		model.addAttribute("historyList", challengeprogramminghistoryDTOList);

		return "shopping/productListLayout";
	}

	@GetMapping("/purchaseHistory")
	public String getPurchaseHistory(@ModelAttribute PcDataForm form, Model model) {
		model.addAttribute("contents", "shopping/purchaseHistory::productListLayout_contents");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String getName = auth.getName();
		int select_id = usersService.select_id(getName);

		// 購入商品情報リスト取得
		List<PurchaseDTO> purchasedtoList = purchaseService.selectHistory(select_id);// ログインユーザーの購入商品情報をすべて取得

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
			purchasedtoAdd.setPointUse(purchaseOne.getPointUse());
			model.addAttribute("pointUseCheck", "false");
			if (purchasedtoAdd.getPointUse() > 0) {// ポイントを利用していたらtrue
				model.addAttribute("pointUseCheck", "true");
			}
			purchasedtoAdd.setCouponId(purchaseOne.getCouponId());
			purchasedtoAdd.setPcName(purchaseOne.getPcName());
			purchasedtoAdd.setPrice(purchaseOne.getPrice());
			purchasedtoAdd.setPcImg(purchaseOne.getPcImg());
			purchasedtoAdd.setProduct_count(purchaseOne.getProduct_count());
			purchasedtoAdd.setPurchaseCheck(purchaseOne.getPurchaseCheck());
			purchasedtoAdd.setMenberCouponCheck(purchaseOne.getMenberCouponCheck());

			// 購入商品ごとのカスタム情報も取り出す
			int productId = purchasedtoAdd.getId();
			String nullCheck = "null";
			int getCustomId = customService.selectPurchaseCheck(select_id, productId, purchasedtoAdd.getPurchaseCheck(), // ログインユーザーが購入した商品のカスタム情報の入ったカスタムIDを取得
					nullCheck);
			customList = customService.selectMany(getCustomId);// カスタムIDに基づいてcustomテーブルから

			purchasedtoAdd.setMemory(customList.getMemory());
			purchasedtoAdd.setHardDisc(customList.getHardDisc());
			purchasedtoAdd.setCpu(customList.getCpu());
			purchasedtoAdd.setCustomPrice(customList.getCustomPrice());
			purchasedtoAdd.setTotalPrice(
					(purchaseOne.getProduct_count() * (customList.getCustomPrice() + purchaseOne.getPrice())));

			if (purchasedtoAdd.getMenberCouponCheck().equals("会員クーポン使用")) {
				System.out.println("クーポン使用！");
				int totalPrice = purchasedtoAdd.getTotalPrice();
				MenberCouponDTO menbercoupondto = menberCouponService.selectOne(purchasedtoAdd.getCouponId());// 会員ランククーポンのIDをもとにmenber_couponテーブルから会員ランククーポン情報を取得
				int disCount = menbercoupondto.getDiscount();// 割引率(%)
				if (disCount >= 10) {
					double disCountNew = Double.valueOf("0." + disCount);
					double disCountPriceNew = totalPrice * disCountNew;// 割引価格
					int totalPriceAll = (int) (totalPrice - disCountPriceNew);
					purchasedtoAdd.setTotalPrice(totalPriceAll);
				} else {
					double disCountNew = Double.valueOf("0.0" + disCount);
					double disCountPriceNew = totalPrice * disCountNew;// 割引価格
					int totalPriceAll = (int) (totalPrice - disCountPriceNew);
					purchasedtoAdd.setTotalPrice(totalPriceAll);
				}
			} else {

				if (purchasedtoAdd.getCouponId() > 0) {
					System.out.println("クーポン使用！");
					int totalPrice = purchasedtoAdd.getTotalPrice();
					CouponDTO coupondto = couponService.selectOne(purchasedtoAdd.getCouponId());// 期間限定クーポンのIDをもとにcouponテーブルから期間限定クーポン情報を取得
					int disCount = coupondto.getDiscount();// 割引率(%)
					if (disCount >= 10) {
						double disCountNew = Double.valueOf("0." + disCount);
						double disCountPriceNew = totalPrice * disCountNew;// 割引価格
						int totalPriceAll = (int) (totalPrice - disCountPriceNew);
						purchasedtoAdd.setTotalPrice(totalPriceAll);
					} else {
						double disCountNew = Double.valueOf("0.0" + disCount);
						double disCountPriceNew = totalPrice * disCountNew;// 割引価格
						int totalPriceAll = (int) (totalPrice - disCountPriceNew);
						purchasedtoAdd.setTotalPrice(totalPriceAll);
					}
				}
			}

			purchasedtoAdd.setTotalPrice((purchasedtoAdd.getTotalPrice() - purchasedtoAdd.getPointUse()));

			Calendar calendar = Calendar.getInstance();
			Date now = calendar.getTime();
			calendar.setTime(purchasedtoAdd.getPurchase_date());
			Date purchaseDate = calendar.getTime();// 購入日付
			model.addAttribute("purchaseCheck", purchaseDate);
			calendar.add(Calendar.DATE, 10);
			Date purchaseDateAddTen = calendar.getTime();// キャンセル期間外の購入日付から10日後の日付
			model.addAttribute("result", purchaseDateAddTen);
			long d = (purchaseDate.getTime() - now.getTime()) / (1000 * 60 * 60 * 24);// 購入日と現在の日付を比べる
			int count = (int) -d;

			if (count <= 10) {
				purchasedtoAdd.setCancelResult("true");
			} else {
				purchasedtoAdd.setCancelResult("false");
			}

			CancelDTO canceldto = cancelService.selectCancelCheck(purchasedtoAdd.getPurchaseId());// 購入IDをもとにcancelテーブルからcancelCheck
			if (canceldto.getCancelCheck() != null) {
				purchasedtoAdd.setCancelResult("true");
			}
			allPurchaseList.add(purchasedtoAdd);
		}

		model.addAttribute("purchaseList", allPurchaseList);
		model.addAttribute("confirmationPending", "返品商品確認待ち");
		model.addAttribute("inTransaction", "キャンセル取引中");
		return "shopping/productListLayout";

	}

	@GetMapping("/gacha")
	public String getGacha(Model model) {
		model.addAttribute("contents", "shopping/dailyGacha::productListLayout_contents");
		model.addAttribute("gachaTurn", "yes");
		//現在の日付とユーザーIDを取得
		Date nowDate = new Date();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String getName = auth.getName();
		int userId = usersService.select_id(getName);
		GachaDTO gachadto = new GachaDTO();

		//本日ガチャを回したかチェック
		//直近に回したガチャの日付を取得
		Date gachaTurnDate = gachaService.gachaTurnedCheck(userId);
		//本日の日付と取得した日付を見比べる
		//日付がないなら一回もガチャを回していないことになるので回させる
		if (gachaTurnDate != null) {
			String newGachaTurnDate = new SimpleDateFormat("yyyy-MM-dd").format(gachaTurnDate);
			String newNowDate = new SimpleDateFormat("yyyy-MM-dd").format(nowDate);
			//直近に回したガチャの日付を現在の日付が一致すれば回させない
			if (newGachaTurnDate.equals(newNowDate)) {
				model.addAttribute("gachaTurnCheck", "no");
			} else {
				model.addAttribute("gachaTurnCheck", "yes");
			}
		} else {
			model.addAttribute("gachaTurnCheck", "yes");
		}

		//現在のユーザーのポイントを取得
		int nowPoint = gachaPointsService.selectPointOne(userId);
		model.addAttribute("nowPoint", nowPoint);

		return "shopping/productListLayout";
	}

	@PostMapping(value = "/dailyGacha", params = "gachaTurn")
	public String postGachaTurn(Model model) {
		model.addAttribute("contents", "shopping/dailyGacha::productListLayout_contents");
		model.addAttribute("gachaTurnCheck", "yes");
		model.addAttribute("gachaTurn", "no");

		//ユーザーIDを取得
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String getName = auth.getName();
		int userId = usersService.select_id(getName);

		//現在のユーザーのポイントを取得
		int nowPoint = gachaPointsService.selectPointOne(userId);
		model.addAttribute("nowPoint", nowPoint);

		return "shopping/productListLayout";
	}

	@PostMapping(value = "/dailyGacha", params = "gachaResult")
	public String postGacha(Model model) {
		model.addAttribute("contents", "shopping/dailyGachaResult::productListLayout_contents");
		model.addAttribute("gachaTurn", "yes");

		//現在の日付とユーザーIDを取得
		Date nowDate = new Date();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String getName = auth.getName();
		int userId = usersService.select_id(getName);
		GachaDTO gachadto = new GachaDTO();

		//結果をロードで変更できないように回したらロード不可にする
		Date gachaTurnDate = gachaService.gachaTurnedCheck(userId);
		String newGachaTurnDate = new SimpleDateFormat("yyyy-MM-dd").format(gachaTurnDate);
		String newNowDate = new SimpleDateFormat("yyyy-MM-dd").format(nowDate);
		if (newGachaTurnDate.equals(newNowDate)) {
			return getGacha(model);
		}

		//ガチャを回したユーザーと日付を格納
		gachaService.gachaTurnInsertOne(gachadto, userId, nowDate);

		// 十連ガチャ
		//十連の結果を格納するリスト
		List<GachaContentDTO> gachaResultList = new ArrayList<>();
		int totalPoint = 0;

		//ガチャを10回回す処理
		for (int i = 0; 10 > i; i++) {
			int rundomNumber = ((int) Math.ceil(Math.random() * 100));
			GachaContentDTO gachacontentdto = new GachaContentDTO();
			// 確率1%(星5)
			// 星5の1
			if (rundomNumber < 1) {
				gachacontentdto = gachaContentService.selectFiveSS();
				gachacontentdto.setImg("star5");
				totalPoint = totalPoint + gachacontentdto.getPoint();
				gachaResultList.add(gachacontentdto);
			}

			// 確率2％(星4)
			// 星4の1
			if (rundomNumber >= 1 && rundomNumber <= 2) {
				gachacontentdto = gachaContentService.selectFourSS();
				gachacontentdto.setImg("star4");
				totalPoint = totalPoint + gachacontentdto.getPoint();
				gachaResultList.add(gachacontentdto);
			}
			// 星4の2
			if (rundomNumber >= 3 && rundomNumber <= 4) {
				gachacontentdto = gachaContentService.selectFourS();
				gachacontentdto.setImg("star4");
				totalPoint = totalPoint + gachacontentdto.getPoint();
				gachaResultList.add(gachacontentdto);
			}
			// 星4の3
			if (rundomNumber >= 5 && rundomNumber <= 6) {
				gachacontentdto = gachaContentService.selectFourA();
				gachacontentdto.setImg("star4");
				totalPoint = totalPoint + gachacontentdto.getPoint();
				gachaResultList.add(gachacontentdto);
			}
			// 星4の4
			if (rundomNumber >= 7 && rundomNumber <= 8) {
				gachacontentdto = gachaContentService.selectFourB();
				gachacontentdto.setImg("star4");
				totalPoint = totalPoint + gachacontentdto.getPoint();
				gachaResultList.add(gachacontentdto);
			}
			// 星4の5
			if (rundomNumber >= 9 && rundomNumber <= 10) {
				gachacontentdto = gachaContentService.selectFourC();
				gachacontentdto.setImg("star4");
				totalPoint = totalPoint + gachacontentdto.getPoint();
				gachaResultList.add(gachacontentdto);
			}

			// 確率4％(星3)
			// 星3の1
			if (rundomNumber >= 11 && rundomNumber <= 14) {
				gachacontentdto = gachaContentService.selectThreeSS();
				gachacontentdto.setImg("star3");
				totalPoint = totalPoint + gachacontentdto.getPoint();
				gachaResultList.add(gachacontentdto);
			}

			// 星3の2
			if (rundomNumber >= 15 && rundomNumber <= 18) {
				gachacontentdto = gachaContentService.selectThreeS();
				gachacontentdto.setImg("star3");
				totalPoint = totalPoint + gachacontentdto.getPoint();
				gachaResultList.add(gachacontentdto);
			}

			// 星3の3
			if (rundomNumber >= 19 && rundomNumber <= 22) {
				gachacontentdto = gachaContentService.selectThreeA();
				gachacontentdto.setImg("star3");
				totalPoint = totalPoint + gachacontentdto.getPoint();
				gachaResultList.add(gachacontentdto);
			}

			// 星3の4
			if (rundomNumber >= 23 && rundomNumber <= 26) {
				gachacontentdto = gachaContentService.selectThreeB();
				gachacontentdto.setImg("star3");
				totalPoint = totalPoint + gachacontentdto.getPoint();
				gachaResultList.add(gachacontentdto);
			}
			// 星3の5
			if (rundomNumber >= 27 && rundomNumber <= 30) {
				gachacontentdto = gachaContentService.selectThreeC();
				gachacontentdto.setImg("star3");
				totalPoint = totalPoint + gachacontentdto.getPoint();
				gachaResultList.add(gachacontentdto);
			}

			// 確率6％(星2)
			// 星2の1
			if (rundomNumber >= 31 && rundomNumber <= 36) {
				gachacontentdto = gachaContentService.selectTwoSS();
				gachacontentdto.setImg("star2");
				totalPoint = totalPoint + gachacontentdto.getPoint();
				gachaResultList.add(gachacontentdto);
			}

			// 星2の2
			if (rundomNumber >= 37 && rundomNumber <= 42) {
				gachacontentdto = gachaContentService.selectTwoS();
				gachacontentdto.setImg("star2");
				totalPoint = totalPoint + gachacontentdto.getPoint();
				gachaResultList.add(gachacontentdto);
			}

			// 星2の3
			if (rundomNumber >= 43 && rundomNumber <= 48) {
				gachacontentdto = gachaContentService.selectTwoA();
				gachacontentdto.setImg("star2");
				totalPoint = totalPoint + gachacontentdto.getPoint();
				gachaResultList.add(gachacontentdto);
			}

			// 星2の4
			if (rundomNumber >= 49 && rundomNumber <= 54) {
				gachacontentdto = gachaContentService.selectTwoB();
				gachacontentdto.setImg("star2");
				totalPoint = totalPoint + gachacontentdto.getPoint();
				gachaResultList.add(gachacontentdto);
			}

			// 星2の5
			if (rundomNumber >= 55 && rundomNumber <= 60) {
				gachacontentdto = gachaContentService.selectTwoC();
				gachacontentdto.setImg("star2");
				totalPoint = totalPoint + gachacontentdto.getPoint();
				gachaResultList.add(gachacontentdto);
			}

			// 確率8％(星1)
			// 星1の1
			if (rundomNumber >= 61 && rundomNumber <= 68) {
				gachacontentdto = gachaContentService.selectOneSS();
				gachacontentdto.setImg("star1");
				totalPoint = totalPoint + gachacontentdto.getPoint();
				gachaResultList.add(gachacontentdto);
			}

			// 星1の2
			if (rundomNumber >= 69 && rundomNumber <= 76) {
				gachacontentdto = gachaContentService.selectOneS();
				gachacontentdto.setImg("star1");
				totalPoint = totalPoint + gachacontentdto.getPoint();
				gachaResultList.add(gachacontentdto);
			}

			// 星1の3
			if (rundomNumber >= 77 && rundomNumber <= 84) {
				gachacontentdto = gachaContentService.selectOneA();
				gachacontentdto.setImg("star1");
				totalPoint = totalPoint + gachacontentdto.getPoint();
				gachaResultList.add(gachacontentdto);
			}

			// 星1の4
			if (rundomNumber >= 85 && rundomNumber <= 92) {
				gachacontentdto = gachaContentService.selectOneB();
				gachacontentdto.setImg("star1");
				totalPoint = totalPoint + gachacontentdto.getPoint();
				gachaResultList.add(gachacontentdto);
			}

			// 星1の5
			if (rundomNumber >= 93 && rundomNumber <= 100) {
				gachacontentdto = gachaContentService.selectOneC();
				gachacontentdto.setImg("star1");
				totalPoint = totalPoint + gachacontentdto.getPoint();
				gachaResultList.add(gachacontentdto);
			}

		}

		//ガチャで獲得したポイントを加える
		gachaPointsService.dailyGachaGetPointAdd(userId, totalPoint);

		model.addAttribute("gachaResultList", gachaResultList);

		return "shopping/productListLayout";
	}

	@PostMapping(value = "/dailyGacha", params = "probability")
	public String postDailyGachaProbability(Model model) {
		model.addAttribute("contents", "shopping/dailyGachaProbability::productListLayout_contents");

		//ガチャのアイテムをすべて取得
		List<GachaProbabilityDTO> gachaPointList = gachaContentService.pointSelectMany();

		//ガチャの詳細をDTOに渡して画面に表示する
		GachaProbabilityDTO gachaprobatilitysetdto = new GachaProbabilityDTO();
		for (int x = 0; gachaPointList.size() > x; x++) {
			GachaProbabilityDTO gachaprobatilitydto = gachaPointList.get(x);
			if (x == 0) {
				gachaprobatilitysetdto.setStarFiveSS(gachaprobatilitydto.getAllStarPoints());
				model.addAttribute("starFiveSSpoint", gachaprobatilitysetdto.getStarFiveSS());
			}

			if (x == 1) {
				gachaprobatilitysetdto.setStarFourSS(gachaprobatilitydto.getAllStarPoints());
				model.addAttribute("starFourSSpoint", gachaprobatilitysetdto.getStarFourSS());
			}

			if (x == 2) {
				gachaprobatilitysetdto.setStarFourS(gachaprobatilitydto.getAllStarPoints());
				model.addAttribute("starFourSpoint", gachaprobatilitysetdto.getStarFourS());
			}

			if (x == 3) {
				gachaprobatilitysetdto.setStarFourA(gachaprobatilitydto.getAllStarPoints());
				model.addAttribute("starFourApoint", gachaprobatilitysetdto.getStarFourA());
			}

			if (x == 4) {
				gachaprobatilitysetdto.setStarFourB(gachaprobatilitydto.getAllStarPoints());
				model.addAttribute("starFourBpoint", gachaprobatilitysetdto.getStarFourB());
			}

			if (x == 5) {
				gachaprobatilitysetdto.setStarFourC(gachaprobatilitydto.getAllStarPoints());
				model.addAttribute("starFourCpoint", gachaprobatilitysetdto.getStarFourC());
			}

			if (x == 6) {
				gachaprobatilitysetdto.setStarThreeSS(gachaprobatilitydto.getAllStarPoints());
				model.addAttribute("starThreeSSpoint", gachaprobatilitysetdto.getStarThreeSS());
			}

			if (x == 7) {
				gachaprobatilitysetdto.setStarThreeS(gachaprobatilitydto.getAllStarPoints());
				model.addAttribute("starThreeSpoint", gachaprobatilitysetdto.getStarThreeS());
			}

			if (x == 8) {
				gachaprobatilitysetdto.setStarThreeA(gachaprobatilitydto.getAllStarPoints());
				model.addAttribute("starThreeApoint", gachaprobatilitysetdto.getStarThreeA());
			}

			if (x == 9) {
				gachaprobatilitysetdto.setStarThreeB(gachaprobatilitydto.getAllStarPoints());
				model.addAttribute("starThreeBpoint", gachaprobatilitysetdto.getStarThreeB());
			}

			if (x == 10) {
				gachaprobatilitysetdto.setStarThreeC(gachaprobatilitydto.getAllStarPoints());
				model.addAttribute("starThreeCpoint", gachaprobatilitysetdto.getStarThreeC());
			}

			if (x == 11) {
				gachaprobatilitysetdto.setStarTwoSS(gachaprobatilitydto.getAllStarPoints());
				model.addAttribute("starTwoSSpoint", gachaprobatilitysetdto.getStarTwoSS());
			}

			if (x == 12) {
				gachaprobatilitysetdto.setStarTwoS(gachaprobatilitydto.getAllStarPoints());
				model.addAttribute("starTwoSpoint", gachaprobatilitysetdto.getStarTwoS());
			}

			if (x == 13) {
				gachaprobatilitysetdto.setStarTwoA(gachaprobatilitydto.getAllStarPoints());
				model.addAttribute("starTwoApoint", gachaprobatilitysetdto.getStarTwoA());
			}

			if (x == 14) {
				gachaprobatilitysetdto.setStarTwoB(gachaprobatilitydto.getAllStarPoints());
				model.addAttribute("starTwoBpoint", gachaprobatilitysetdto.getStarTwoB());
			}

			if (x == 15) {
				gachaprobatilitysetdto.setStarTwoC(gachaprobatilitydto.getAllStarPoints());
				model.addAttribute("starTwoCpoint", gachaprobatilitysetdto.getStarTwoC());
			}

			if (x == 16) {
				gachaprobatilitysetdto.setStarOneSS(gachaprobatilitydto.getAllStarPoints());
				model.addAttribute("starOneSSpoint", gachaprobatilitysetdto.getStarOneSS());
			}

			if (x == 17) {
				gachaprobatilitysetdto.setStarOneS(gachaprobatilitydto.getAllStarPoints());
				model.addAttribute("starOneSpoint", gachaprobatilitysetdto.getStarOneS());
			}

			if (x == 18) {
				gachaprobatilitysetdto.setStarOneA(gachaprobatilitydto.getAllStarPoints());
				model.addAttribute("starOneApoint", gachaprobatilitysetdto.getStarOneA());
			}

			if (x == 19) {
				gachaprobatilitysetdto.setStarOneB(gachaprobatilitydto.getAllStarPoints());
				model.addAttribute("starOneBpoint", gachaprobatilitysetdto.getStarOneB());
			}

			if (x == 20) {
				gachaprobatilitysetdto.setStarOneC(gachaprobatilitydto.getAllStarPoints());
				model.addAttribute("starOneCpoint", gachaprobatilitysetdto.getStarOneC());
			}
		}

		model.addAttribute("star5SS", "1");
		model.addAttribute("star4SS", "2");
		model.addAttribute("star4S", "2");
		model.addAttribute("star4A", "2");
		model.addAttribute("star4B", "2");
		model.addAttribute("star4C", "2");
		model.addAttribute("star3SS", "4");
		model.addAttribute("star3S", "4");
		model.addAttribute("star3A", "4");
		model.addAttribute("star3B", "4");
		model.addAttribute("star3C", "4");
		model.addAttribute("star2SS", "6");
		model.addAttribute("star2S", "6");
		model.addAttribute("star2A", "6");
		model.addAttribute("star2B", "6");
		model.addAttribute("star2C", "6");
		model.addAttribute("star1SS", "8");
		model.addAttribute("star1S", "8");
		model.addAttribute("star1A", "8");
		model.addAttribute("star1B", "8");
		model.addAttribute("star1C", "8");

		return "shopping/productListLayout";
	}

	@PostMapping(value = "/dailyGacha", params = "dailyGachaPointInterchange")
	public String postDailyGachaDailyGachaPointUse(Model model) {
		model.addAttribute("contents", "shopping/dailyGachaPointInterchange::productListLayout_contents");

		//ユーザーIDを取得
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String getName = auth.getName();
		int userId = usersService.select_id(getName);

		//現在のユーザーのポイントを取得
		int nowPoint = gachaPointsService.selectPointOne(userId);

		//ポイントで交換する商品をすべて取得
		List<GachaPointInterChangeDTO> gachaPointInterChangeProductList = gachaPointInterChangeService.selectMany();
		//交換画面でユーザーのポイントが各交換商品のポイントよりも少なければ交換できないようにする
		for (int x = 0; gachaPointInterChangeProductList.size() > x; x++) {
			GachaPointInterChangeDTO gachaPointInterChangeProductOne = gachaPointInterChangeProductList.get(x);
			if (nowPoint < gachaPointInterChangeProductOne.getPoint()) {
				gachaPointInterChangeProductOne.setPointCheck("交換不可");
			} else {
				gachaPointInterChangeProductOne.setPointCheck("交換可能");
			}
		}

		model.addAttribute("gachaPointInterChangeProductList", gachaPointInterChangeProductList);

		return "shopping/productListLayout";
	}

	@PostMapping(value = "/dailyGacha", params = "dailyGachaPointProductHistory")
	public String postDailyGachaPointProductHistory(Model model) {
		model.addAttribute("contents", "shopping/dailyGachaPointProductHistory::productListLayout_contents");

		//ユーザーIDを取得
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String getName = auth.getName();
		int userId = usersService.select_id(getName);

		//ユーザーのポイント交換履歴をすべて取得
		List<GachaPointProductHistoryDTO> gachaPointProductHistorydtoList = gachaPointProductHistoryService
				.productHistorySelectOne(userId);
		model.addAttribute("gachaPointProductHistorydtoList", gachaPointProductHistorydtoList);

		return "shopping/productListLayout";
	}

	@GetMapping("/gachaPointInterChangeExecution/{id}")
	public String getGachaPointInterChangeExecution(@PathVariable("id") int gachaPointProductId, Model model) {
		model.addAttribute("contents", "shopping/gachaPointInterChangeExecution::productListLayout_contents");

		//選択したポイントで交換する商品を取得
		GachaPointInterChangeDTO gachaPointInterChangeProductList = gachaPointInterChangeService
				.selectOne(gachaPointProductId);
		model.addAttribute("gachaPointInterChangeProductList", gachaPointInterChangeProductList);
		model.addAttribute("gachaPointProductId", gachaPointProductId);

		//ユーザーIDを取得
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String getName = auth.getName();
		int userId = usersService.select_id(getName);

		//ユーザーの現在のポイントを取得
		int totalPoint = gachaPointsService.selectPointOne(userId);
		model.addAttribute("totalPoint", totalPoint);
		//交換後のポイントを取得
		int afterExchangeTotalPoint = totalPoint - gachaPointInterChangeProductList.getPoint();
		model.addAttribute("afterExchangeTotalPoint", afterExchangeTotalPoint);

		return "shopping/productListLayout";
	}

	@PostMapping("/gachaPointInterChangeExecution")
	public String postGachaPointInterChangeExecution(@RequestParam("id") int gachaPointProductId, Model model) {

		//ユーザーIDを取得
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String getName = auth.getName();
		int userId = usersService.select_id(getName);

		//選択したポイントで交換する商品を取得
		GachaPointInterChangeDTO gachaPointInterChangeProductList = gachaPointInterChangeService
				.selectOne(gachaPointProductId);
		int consumptionPoint = gachaPointInterChangeProductList.getPoint();

		//現在のユーザーのポイントを取得
		int nowPoint = gachaPointsService.selectPointOne(userId);
		//交換後のポイント取得し更新
		int consumptionNowPoint = nowPoint - consumptionPoint;
		gachaPointsService.update(consumptionNowPoint, userId);

		//ユーザーが交換した商品の情報を格納
		gachaPointProductHistoryService.productHistoryInsertOne(userId, gachaPointProductId);

		return postDailyGachaPointProductHistory(model);
	}

	@GetMapping("/interChangeProductManagement")
	public String getInterChangeProductManagement(Model model) {
		model.addAttribute("contents", "shopping/interChangeProductManagement::productListLayout_contents");

		List<GachaPointProductHistoryDTO> gachapointproducthistorydto = gachaPointProductHistoryService
				.productHistorySelectMany();
		model.addAttribute("gachaPointProductHistoryList", gachapointproducthistorydto);

		return "shopping/productListLayout";
	}

	@GetMapping("/interChangeProductDeliveryProcedureOK/{id}")
	public String getInterChangeProductDeliveryProcedureOK(@PathVariable("id") int gachaPointProductId, Model model) {

		gachaPointProductHistoryService.deriveryUpdateOne(gachaPointProductId);

		return getInterChangeProductManagement(model);
	}

	@GetMapping("/salesManagement")
	public String getSalesManagement(@ModelAttribute SalesManagementForm form, Model model) {
		model.addAttribute("contents", "shopping/salesManagement::productListLayout_contents");

		//ユーザーIDを取得
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String getName = auth.getName();
		int userId = usersService.select_id(getName);

		int salesProductTotalPrice = 0;
		int totalCost = 0;

		List<PurchaseDTO> purchasedtoAllList = new ArrayList<>();

		// 購入商品情報取得
		List<PurchaseDTO> salesList = purchaseService.productSalesSelectMany();
		for (int x = 0; salesList.size() > x; x++) {
			PurchaseDTO purchasedto = new PurchaseDTO();
			PurchaseDTO purchasedtoList = salesList.get(x);
			purchasedto.setId(purchasedtoList.getId());
			purchasedto.setPurchaseId(purchasedtoList.getPurchaseId());
			purchasedto.setProduct_id(purchasedtoList.getProduct_id());
			purchasedto.setUserName(purchasedtoList.getUserName());
			purchasedto.setPurchase_date(purchasedtoList.getPurchase_date());
			purchasedto.setPcDataId(purchasedtoList.getPcDataId());
			purchasedto.setPcName(purchasedtoList.getPcName());
			purchasedto.setPrice(purchasedtoList.getPrice());
			purchasedto.setCost(purchasedtoList.getCost());
			purchasedto.setProduct_count(purchasedtoList.getProduct_count());
			purchasedto.setPointUse(purchasedtoList.getPointUse());
			purchasedto.setCouponId(purchasedtoList.getCouponId());
			purchasedto.setMenberCouponCheck(purchasedtoList.getMenberCouponCheck());
			purchasedto.setPurchaseCheck(purchasedtoList.getPurchaseCheck());

			System.out.println(Arrays.asList("purchasedto" + purchasedto));
			String nullCheck = "null";
			int getCustomId = customService.selectPurchaseCheck(userId, purchasedto.getProduct_id(),
					purchasedto.getPurchaseCheck(), nullCheck); // 購入した商品のcustomテーブルIDを取得
			PurchaseDTO customList = customService.selectMany(getCustomId);// 購入した商品のcustomテーブル情報を取得
			System.out.println("costomList" + customList);
			purchasedto.setCustom_id(getCustomId);
			purchasedto.setMemory(customList.getMemory());
			purchasedto.setHardDisc(customList.getHardDisc());
			purchasedto.setCpu(customList.getCpu());
			purchasedto.setCustomPrice(customList.getCustomPrice());

			purchasedto.setTotalPrice(
					(purchasedto.getProduct_count() * (purchasedto.getPrice() + purchasedto.getCustomPrice())));

			if (purchasedto.getMenberCouponCheck().equals("会員クーポン使用")) {
				System.out.println("クーポン使用！");
				int totalPrice = purchasedto.getTotalPrice();
				MenberCouponDTO menbercoupondto = menberCouponService.selectOne(purchasedto.getCouponId());// 会員DBからとる
				int disCount = menbercoupondto.getDiscount();// 割引率(%)
				if (disCount >= 10) {
					double disCountNew = Double.valueOf("0." + disCount);
					double disCountPriceNew = totalPrice * disCountNew;// 割引価格
					int totalPriceAll = (int) (totalPrice - disCountPriceNew);
					System.out.println("totalPriceAll" + totalPriceAll);
					System.out.println("purchasedtoAdd.getPointUse" + purchasedto.getPointUse());
					purchasedto.setTotalPrice(totalPriceAll);
				} else {
					double disCountNew = Double.valueOf("0.0" + disCount);
					double disCountPriceNew = totalPrice * disCountNew;// 割引価格
					int totalPriceAll = (int) (totalPrice - disCountPriceNew);
					System.out.println("totalPriceAll" + totalPriceAll);
					System.out.println("purchasedtoAdd.getPointUse" + purchasedto.getPointUse());
					purchasedto.setTotalPrice(totalPriceAll);
				}
			} else {

				if (purchasedto.getCouponId() > 0) {
					System.out.println("クーポン使用！");
					int totalPrice = purchasedto.getTotalPrice();
					CouponDTO coupondto = couponService.selectOne(purchasedto.getCouponId());
					int disCount = coupondto.getDiscount();// 割引率(%)
					if (disCount >= 10) {
						double disCountNew = Double.valueOf("0." + disCount);
						double disCountPriceNew = totalPrice * disCountNew;// 割引価格
						System.out.println("totalPrice" + totalPrice);
						int totalPriceAll = (int) (totalPrice - disCountPriceNew);
						System.out.println("totalPriceAll" + totalPriceAll);
						purchasedto.setTotalPrice(totalPriceAll);
						System.out.println("setTotalPrice" + purchasedto.getTotalPrice());
					} else {
						double disCountNew = Double.valueOf("0.0" + disCount);
						double disCountPriceNew = totalPrice * disCountNew;// 割引価格
						int totalPriceAll = (int) (totalPrice - disCountPriceNew);
						purchasedto.setTotalPrice(totalPriceAll);
					}
				}
			}
			//総売上
			salesProductTotalPrice = salesProductTotalPrice + (purchasedto.getTotalPrice() - purchasedto.getPointUse());
			//コスト
			totalCost = totalCost + purchasedto.getCost();

			purchasedto.setTotalPrice(purchasedto.getTotalPrice() - purchasedto.getPointUse());
			purchasedto.setRevenue((purchasedto.getTotalPrice() - purchasedto.getPointUse()) - purchasedto.getCost());
			purchasedtoAllList.add(purchasedto);
			model.addAttribute("purchaseList", purchasedto);
			System.out.println("1" + Arrays.asList(purchasedtoAllList));
		}
		System.out.println("2" + Arrays.asList(purchasedtoAllList));

		//利益
		int revenue = salesProductTotalPrice - totalCost;

		model.addAttribute("salesProductTotalPrice", salesProductTotalPrice);
		model.addAttribute("totalCost", totalCost);
		model.addAttribute("revenue", revenue);
		model.addAttribute("purchasedtoAllList", purchasedtoAllList);

		return "shopping/productListLayout";
	}

	@PostMapping(value = "/salesManagement", params = "search")
	public String postSalesManagement(@ModelAttribute @Validated(GroupOrder.class) SalesManagementForm form,
			BindingResult bindingResult, Model model) {
		model.addAttribute("contents", "shopping/salesManagement::productListLayout_contents");

		if (bindingResult.hasErrors()) {
			return getSalesManagement(form, model);
		}

		//ユーザーIDを取得
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String getName = auth.getName();
		int userId = usersService.select_id(getName);

		int salesProductTotalPrice = 0;
		int totalCost = 0;

		List<PurchaseDTO> purchasedtoAllList = new ArrayList<>();

		// 購入商品情報取得
		List<PurchaseDTO> salesList = purchaseService.productSalesSearchSelectMany(form);
		for (int x = 0; salesList.size() > x; x++) {
			PurchaseDTO purchasedto = new PurchaseDTO();
			PurchaseDTO purchasedtoList = salesList.get(x);
			purchasedto.setId(purchasedtoList.getId());
			purchasedto.setPurchaseId(purchasedtoList.getPurchaseId());
			purchasedto.setProduct_id(purchasedtoList.getProduct_id());
			purchasedto.setUserName(purchasedtoList.getUserName());
			purchasedto.setPurchase_date(purchasedtoList.getPurchase_date());
			purchasedto.setPcDataId(purchasedtoList.getPcDataId());
			purchasedto.setPcName(purchasedtoList.getPcName());
			purchasedto.setPrice(purchasedtoList.getPrice());
			purchasedto.setCost(purchasedtoList.getCost());
			purchasedto.setProduct_count(purchasedtoList.getProduct_count());
			purchasedto.setPointUse(purchasedtoList.getPointUse());
			purchasedto.setCouponId(purchasedtoList.getCouponId());
			purchasedto.setMenberCouponCheck(purchasedtoList.getMenberCouponCheck());
			purchasedto.setPurchaseCheck(purchasedtoList.getPurchaseCheck());

			System.out.println(Arrays.asList("purchasedto" + purchasedto));
			String nullCheck = "null";
			int getCustomId = customService.selectPurchaseCheck(userId, purchasedto.getProduct_id(),
					purchasedto.getPurchaseCheck(), nullCheck); // 購入した商品のcustomテーブルIDを取得
			PurchaseDTO customList = customService.selectMany(getCustomId);// 購入した商品のcustomテーブル情報を取得
			System.out.println("costomList" + customList);
			purchasedto.setCustom_id(getCustomId);
			purchasedto.setMemory(customList.getMemory());
			purchasedto.setHardDisc(customList.getHardDisc());
			purchasedto.setCpu(customList.getCpu());
			purchasedto.setCustomPrice(customList.getCustomPrice());

			purchasedto.setTotalPrice(
					(purchasedto.getProduct_count() * (purchasedto.getPrice() + purchasedto.getCustomPrice())));

			if (purchasedto.getMenberCouponCheck().equals("会員クーポン使用")) {
				System.out.println("クーポン使用！");
				int totalPrice = purchasedto.getTotalPrice();
				MenberCouponDTO menbercoupondto = menberCouponService.selectOne(purchasedto.getCouponId());// 会員DBからとる
				int disCount = menbercoupondto.getDiscount();// 割引率(%)
				if (disCount >= 10) {
					double disCountNew = Double.valueOf("0." + disCount);
					double disCountPriceNew = totalPrice * disCountNew;// 割引価格
					int totalPriceAll = (int) (totalPrice - disCountPriceNew);
					System.out.println("totalPriceAll" + totalPriceAll);
					System.out.println("purchasedtoAdd.getPointUse" + purchasedto.getPointUse());
					purchasedto.setTotalPrice(totalPriceAll);
				} else {
					double disCountNew = Double.valueOf("0.0" + disCount);
					double disCountPriceNew = totalPrice * disCountNew;// 割引価格
					int totalPriceAll = (int) (totalPrice - disCountPriceNew);
					System.out.println("totalPriceAll" + totalPriceAll);
					System.out.println("purchasedtoAdd.getPointUse" + purchasedto.getPointUse());
					purchasedto.setTotalPrice(totalPriceAll);
				}
			} else {

				if (purchasedto.getCouponId() > 0) {
					System.out.println("クーポン使用！");
					int totalPrice = purchasedto.getTotalPrice();
					CouponDTO coupondto = couponService.selectOne(purchasedto.getCouponId());
					int disCount = coupondto.getDiscount();// 割引率(%)
					if (disCount >= 10) {
						double disCountNew = Double.valueOf("0." + disCount);
						double disCountPriceNew = totalPrice * disCountNew;// 割引価格
						System.out.println("totalPrice" + totalPrice);
						int totalPriceAll = (int) (totalPrice - disCountPriceNew);
						System.out.println("totalPriceAll" + totalPriceAll);
						purchasedto.setTotalPrice(totalPriceAll);
						System.out.println("setTotalPrice" + purchasedto.getTotalPrice());
					} else {
						double disCountNew = Double.valueOf("0.0" + disCount);
						double disCountPriceNew = totalPrice * disCountNew;// 割引価格
						int totalPriceAll = (int) (totalPrice - disCountPriceNew);
						purchasedto.setTotalPrice(totalPriceAll);
					}
				}
			}
			//総売上
			salesProductTotalPrice = salesProductTotalPrice + (purchasedto.getTotalPrice() - purchasedto.getPointUse());
			//コスト
			totalCost = totalCost + purchasedto.getCost();

			purchasedto.setTotalPrice(purchasedto.getTotalPrice() - purchasedto.getPointUse());
			purchasedto.setRevenue((purchasedto.getTotalPrice() - purchasedto.getPointUse()) - purchasedto.getCost());
			purchasedtoAllList.add(purchasedto);
			model.addAttribute("purchaseList", purchasedto);
			System.out.println("1" + Arrays.asList(purchasedtoAllList));
		}
		System.out.println("2" + Arrays.asList(purchasedtoAllList));

		//利益
		int revenue = salesProductTotalPrice - totalCost;

		model.addAttribute("salesProductTotalPrice", salesProductTotalPrice);
		model.addAttribute("totalCost", totalCost);
		model.addAttribute("revenue", revenue);
		model.addAttribute("purchasedtoAllList", purchasedtoAllList);
		return "shopping/productListLayout";
	}

	@GetMapping("/listingProductStop")
	public String getListingProductStop(Model model) {
		model.addAttribute("contents", "shopping/listingProductStop::productListLayout_contents");

		// 商品情報をすべて取得
		List<PcDataDTO> productList = pcdataService.listingStopProductSelectMany();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		UsersDTO headerName = usersService.getUser_name(auth.getName());
		session.setAttribute("sessionGetUser_name", headerName.getUser_name());
		session.setAttribute("sessionGetRole", headerName.getRole());

		model.addAttribute("productList", productList);

		int allProductCount = 0;
		int allTotalPrice = 0;
		String user_id = auth.getName();
		// ログインユーザーのID取得
		int selectId = usersService.select_id(user_id);
		List<PurchaseDTO> purchasedtoList = purchaseService.selectHistory(selectId);

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

	@GetMapping("/listingStop/{id}")
	public String getListingStop(@PathVariable("id") int productId, Model model) {

		pcdataService.listingStopUpdateOne(productId);

		return getListingProductStop(model);
	}

	@GetMapping("/listingRestart/{id}")
	public String getListingRestart(@PathVariable("id") int productId, Model model) {

		pcdataService.listingRestartUpdateOne(productId);

		return getListingProductStop(model);
	}
	
	@GetMapping("/auction")
	public String getAuction(Model model) {
		model.addAttribute("contents", "shopping/auction::productListLayout_contents");
		
		//出品期限内商品更新処理
		List<AuctionDataDTO> auctiondatadtoList = auctionDataService.selectMany();
		for(int x = 0; x < auctiondatadtoList.size(); x++) {
			AuctionDataDTO auctiondatadtoOne = auctiondatadtoList.get(x);
			String tenderEndDate = auctiondatadtoOne.getTenderEndDate();
			int auctionId = auctiondatadtoOne.getId();
			int tenderPrice = auctiondatadtoOne.getTenderPrice();
			Date nowDate = new Date();
			SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy/MM/dd");
			String newNowDate = simpleFormat.format(nowDate);
			int endDateCheck = tenderEndDate.compareTo(newNowDate);
			System.out.println("enddatacheck"+endDateCheck);
			//入札があるかどうか調べる
			if(tenderPrice > 0) {
				//ここで入札あり期限終了チェックを入れる(出品期限が過ぎているかその当日)
				if(endDateCheck < 1) {
					auctionDataService.yesTenderUpdateOne(auctionId);
				}	
			}else {
				//ここで入札なし期限終了チェックを入れる(出品期限が過ぎているかその当日)
				if(endDateCheck < 1) {
					auctionDataService.noTenderUpdateOne(auctionId);
				}		
			}
		}
		
		//出品期限内商品のみ取得
		List<AuctionDataDTO> auctiondatadtoListWithinTimeLimit = auctionDataService.withinTimeLimitSelectMany();
		model.addAttribute("auctionDataDTOList",auctiondatadtoListWithinTimeLimit);
		
		
		
		return "shopping/productListLayout";
	}
	
	@GetMapping("/successfulBidProduct")
	public String getSuccessfulBid(Model model) {
		model.addAttribute("contents", "shopping/successfulBidProduct::productListLayout_contents");
		
		//落札済みの商品IDを取得
		List<Integer> successfulBidProductId = new ArrayList<>();
		successfulBidProductId = auctionDataService.getSuccessfulBIdProductIdSelectMany();
		
		//ユーザーIDを取得
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String getName = auth.getName();
		int userId = usersService.select_id(getName);
		
		//自分が落札した商品IDを取得
		List<Integer> successfulBidUserProductId = new ArrayList<>();
		for(int x = 0; successfulBidProductId.size() > x; x++) {
		int successfulBidProductIdOne = successfulBidProductId.get(x);
		int getId = auctionDataService.getSuccessfulBIdUserProductIdSelectOne(successfulBidProductIdOne,userId);
		successfulBidUserProductId.add(getId);
		}
		
		//自分が落札した商品情報を取得
		List<AuctionDataDTO> auctiondatadtoList = new ArrayList<>();
		for(int x = 0;successfulBidUserProductId.size() > x; x++) {
			int successfulBidUserProductIdOne = successfulBidUserProductId.get(x);
			AuctionDataDTO auctiondatadtoListOne = auctionDataService.getSuccessfulBIdUserProductSelectMany(successfulBidUserProductIdOne);
			int auctionIdOne = auctiondatadtoListOne.getId();
			int deliveryCheck = auctionProductHistoryService.getSelectDeliveryProductIdOne(auctionIdOne,userId);
			if(deliveryCheck == 1) {
				auctiondatadtoListOne.setDeliveryElement("支払い完了・発送前");
			}else {
				auctiondatadtoListOne.setDeliveryElement("支払い前");
			}
			auctiondatadtoList.add(auctiondatadtoListOne);
		}
		
		model.addAttribute("auctionDataDTOList",auctiondatadtoList);
		
		return "shopping/productListLayout";
	}
	
	@GetMapping("/auctionProductManagement")
	public String getAuctionProductManagement(Model model) {
		model.addAttribute("contents", "shopping/auctionProductManagement::productListLayout_contents");
		
		//落札済みの商品をすべて取得
		List<AuctionProductHistoryDTO> auctionProductHistoryDtoList =  auctionProductHistoryService.selectAuctionProductMany();
		for(int x = 0; auctionProductHistoryDtoList.size() > x; x++) {
			AuctionProductHistoryDTO auctionProductHistoryOne = auctionProductHistoryDtoList.get(x);
			String deliveryCheck = auctionProductHistoryOne.getDeliveryCheck();
				if(deliveryCheck.equals("発送前")) {
					auctionProductHistoryOne.setDeliveryCheckOk("no");
				} else {
					auctionProductHistoryOne.setDeliveryCheckOk("yes");
				}
		}
		
		model.addAttribute("auctionProduct",auctionProductHistoryDtoList);
		
		return "shopping/productListLayout";
	}
	
	@GetMapping("/auctionProductDeliveryFinish/{id}")
	public String getAuctionProductDeliveryFinish(@PathVariable("id") int auctionProductHistoryId,Model model) {
		
		auctionProductHistoryService.deliveryUpdateOne(auctionProductHistoryId);
		
		return getAuctionProductManagement(model);
		
	}
	
	
	
	@GetMapping("/auctionPaymentProduct/{id}")
	public String getAuctionPaymentProduct(@PathVariable("id") int auctionId,auctionPaymentProductForm form,Model model) {
		model.addAttribute("contents", "shopping/auctionPaymentProduct::productListLayout_contents");
		
		AuctionDataDTO auctiondatadtoList = auctionDataService.tenderSelectOne(auctionId);
		model.addAttribute("auctionDataDTOList",auctiondatadtoList);
		model.addAttribute("auctionId",auctionId);
		
		return "shopping/productListLayout";
	}
	
	@PostMapping("auctionPayment")
	public String postAuctionPayment(@RequestParam("id") int auctionId,auctionPaymentProductForm form,Model model) {
		model.addAttribute("contents", "shopping/auctionPayment::productListLayout_contents");
		
		//ユーザーIDを取得
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String getName = auth.getName();
		int userId = usersService.select_id(getName);
		//落札商品の情報を格納
		auctionProductHistoryService.insertOne(auctionId,userId,form);
		
		
		return "shopping/productListLayout";
		
	}
	
	@GetMapping("/auctionProductDetail/{id}")
	public String getAuctionProductDetail(@PathVariable("id") int auctionId,AuctionTenderForm form,Model model) {
		model.addAttribute("contents", "shopping/auctionProductDetail::productListLayout_contents");
		
		AuctionDataDTO auctiondatadtoList = auctionDataService.selectOne(auctionId);
		model.addAttribute("auctionDataDTOList",auctiondatadtoList);
		model.addAttribute("auctionProductId",auctionId);
		return "shopping/productListLayout";
	}
	
	@GetMapping("/auctionDetail/{id}")
	public String getAuctionDetail(@PathVariable("id") int auctionId,Model model) {
		model.addAttribute("contents", "shopping/auctionDetail::productListLayout_contents");
		
		List<AuctionTenderDataDTO> auctiontenderdatadto = auctionTenderDataService.selectMany(auctionId);
		
		for(int x = 0;auctiontenderdatadto.size() > x;x++) {
			AuctionTenderDataDTO auctiontenderdatadtoOne = auctiontenderdatadto.get(x);
			int userId = auctiontenderdatadtoOne.getUserId();
			String userName = usersService.userNameSelectOne(userId);
			auctiontenderdatadtoOne.setUserName(userName);
		}
		model.addAttribute("auctionTenderDataList",auctiontenderdatadto);
		
		return "shopping/productListLayout";
	}
	
	
	
	
	@PostMapping("/productTender")
	public String postProductTender(@Validated(GroupOrder.class) AuctionTenderForm form,BindingResult bindingResult,@RequestParam("id") int auctiondataId,Model model) {
		model.addAttribute("contents", "shopping/tenderFinish::productListLayout_contents");
	
		//出品期限内商品更新処理
		List<AuctionDataDTO> auctiondatadtoList = auctionDataService.selectMany();
		for(int x = 0; x < auctiondatadtoList.size(); x++) {
			AuctionDataDTO auctiondatadtoOne = auctiondatadtoList.get(x);
			String tenderEndDate = auctiondatadtoOne.getTenderEndDate();
			int auctionId = auctiondatadtoOne.getId();
			Date nowDate = new Date();
			SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy/MM/dd");
			String newNowDate = simpleFormat.format(nowDate);
			int endDateCheck = tenderEndDate.compareTo(newNowDate);
			//ここで入札なし期限終了チェックを入れる(出品期限が過ぎているかその当日)
			if(endDateCheck < 1) {
				auctionDataService.noTenderUpdateOne(auctionId);
			}			
		}
		//商品出品期限内かチェック
		int result = auctionDataService.noTenderCheckSelectOne(auctiondataId);
		if(result != 0) {
			model.addAttribute("error","先ほど入札を行おうとした商品の期間が終了しました");
			return getAuction(model);
		}
		
		//ユーザーIDを取得
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String getName = auth.getName();
		int userId = usersService.select_id(getName);
		
		//バリデーション
		if(bindingResult.hasErrors()) {
			return getAuctionProductDetail(auctiondataId,form,model);
		}
		
		int tenderPrice = Integer.valueOf(form.getTenderPrice());
		form.setNewTenderPrice(tenderPrice);
		
		//入札額が適切な額かチェック
		AuctionDataDTO auctiondatadto = auctionDataService.priceSelectOne(auctiondataId);
		//入札が一回でもされていれば入る
		if(auctiondatadto.getNewTenderPrice() != 0) {
			//現在の入札価格と入力入札価格を比べる
			if(auctiondatadto.getInitialPrice() < form.getNewTenderPrice()) {
			//入札額と入札数を更新
			int tenderNumber = auctionDataService.tenderUpdateOne(form,auctiondataId);
			//最新入札情報を入れる前に、落札者情報を入れ替えのために情報更新
			auctionTenderDataService.statusUpdate(auctiondataId);
			//誰が何回目の入札かを格納
			auctionTenderDataService.insertOne(form,auctiondataId,userId);
			model.addAttribute("tenderPrice",form.getTenderPrice());
			model.addAttribute("tenderNumber",tenderNumber);
			} else {
				model.addAttribute("errorText","現在入札価格よりも上の金額を入力してください");
				return getAuctionProductDetail(auctiondataId,form,model);
			}
		}else {
			//入札がされていないので開始価格と入力入札価格を比べる
			if(auctiondatadto.getInitialPrice() <= form.getNewTenderPrice()) {
				//入札額と入札数を更新
				int tenderNumber = auctionDataService.tenderUpdateOne(form,auctiondataId);
				//最新入札情報を入れる前に、落札者情報を入れ替えのために情報更新
				auctionTenderDataService.statusUpdate(auctiondataId);
				//誰が何回目の入札かを格納
				auctionTenderDataService.insertOne(form,auctiondataId,userId);
				model.addAttribute("tenderPrice",form.getTenderPrice());
				model.addAttribute("tenderNumber",tenderNumber);
			}else  {
				model.addAttribute("errorText","開始価格以上の金額を入力してください");
				return getAuctionProductDetail(auctiondataId,form,model);
				
			}
		}
		
		return "shopping/productListLayout"; 
	}
	
	@GetMapping("/auctionListing")
	public String getAuctionListing(AuctionListingForm form,Model model) {
		model.addAttribute("contents", "shopping/auctionListing::productListLayout_contents");
		
		return "shopping/productListLayout";
	}
	
	@PostMapping("auctionListing")
	public String postAuctionListing(AuctionListingForm form,Model model) {
		model.addAttribute("contents", "shopping/auctionListing::productListLayout_contents");

		String img1 = form.getImg();// 画像アドレスを変数に入れる
		String img2 = form.getImg2();

		String imgCheck1 = img1.substring(img1.length() - 4);// 画像アドレスが.jpgか確かめるため最後に4文字を取得
		String imgCheck2 = img2.substring(img2.length() - 4);
		String jpg = ".jpg";
		
		if (!imgCheck1.equals(jpg)) {
			model.addAttribute("imgResult1", "商品画像1はJPEG形式（最後が「.jpg」のもの）で入力してください");
			return getAuctionListing(form, model);
		}

		if (!imgCheck2.equals(jpg)) {
			model.addAttribute("imgResult2", "商品画像2はJPEG形式（最後が「.jpg」のもの）で入力してください");
			return getAuctionListing(form, model);
		}


		if (!img1.startsWith("https://")) {
			model.addAttribute("imgResult1", "商品画像1はhttps://から始まる画像アドレスを入力してください");
			return getAuctionListing(form, model);

		}

		if (!img2.startsWith("https://")) {
			model.addAttribute("imgResult1", "商品画像2はhttps://から始まる画像アドレスを入力してください");
			return getAuctionListing(form, model);
		}
		
		System.out.println("datetest"+form.getTenderEndDate());
		
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date tenderEndDate = form.getTenderEndDate();
		String newTenderEndDate = simpleFormat.format(tenderEndDate);
		System.out.println("newTenderEndDate"+newTenderEndDate);
		String cutTenderendDate = newTenderEndDate.substring(0,10);
		System.out.println("cutTenderendDate"+cutTenderendDate);
		form.setNewTenderEndDate(cutTenderendDate);
		
		//入力されたオークション出品商品情報を格納
		AuctionDataDTO auctiondatadto = new AuctionDataDTO();
		auctionDataService.auctionDataInsertOne(auctiondatadto,form);
		
		return getAuction(model);
	}
	
	@GetMapping("/good")
	public String getGood(Model model) {
	model.addAttribute("contents", "shopping/good::productListLayout_contents");
	
	//ユーザーIDを取得
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	String getName = auth.getName();
	int userId = usersService.select_id(getName);
	
	List<Integer> productGoodId = new ArrayList<>();
	List<PcDataDTO> productList = new ArrayList<>();
	
	//ユーザーがいいねした商品IDを取得
	productGoodId = productGoodService.selectMany(userId);
	for(int x = 0; x < productGoodId.size(); x++) {
		int productId = productGoodId.get(x);
		PcDataDTO productListOne = pcdataService.selectOne(productId);
		productList.add(productListOne);
	}
	
	model.addAttribute("productList", productList);
	
	
	return "shopping/productListLayout";
	
	}
	
	@GetMapping("/productGood/{id}")
	public String getProductGoot(@PathVariable("id") int productId,Model model) {
	model.addAttribute("contents", "shopping/good::productListLayout_contents");
	
	//ユーザーIDを取得
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	String getName = auth.getName();
	int userId = usersService.select_id(getName);
	
	//いいねボタンを押した商品を格納
	productGoodService.insertOne(userId,productId);
	
	return getGood(model);
	
	}
	
	@GetMapping("/productGoodRemove/{id}")
	public String getProductGootRemove(@PathVariable("id") int productId,Model model) {
	model.addAttribute("contents", "shopping/good::productListLayout_contents");
	
	//ユーザーIDを取得
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	String getName = auth.getName();
	int userId = usersService.select_id(getName);
	
	//いいねボタンを押した商品を削除
	productGoodService.deleteOne(userId,productId);
	
	return getGood(model);
	
	}

	// ログアウト用メソッド
	@GetMapping("logout")
	public String getLogout() {

		// ログイン画面にリダイレクト
		return "redirect:/login";
	}
}
