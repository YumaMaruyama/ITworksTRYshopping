package com.example.demo.login.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domail.model.GroupOrder;
import com.example.demo.login.domail.model.SignupForm;
import com.example.demo.login.domail.model.Usege_usersDTO;
import com.example.demo.login.domail.model.UsersDTO;
import com.example.demo.login.domail.service.MailService;
import com.example.demo.login.domail.service.Usege_usersService;
import com.example.demo.login.domail.service.UsersService;

@Controller
public class SignupController {

	@Autowired
	UsersService usersService;
	@Autowired
	Usege_usersService usegeService;
	@Autowired
	MailService mailService;

	@GetMapping("/signup")
	public String getSignup(@ModelAttribute SignupForm form,Model model) {
		model.addAttribute("contents", "shopping/signup::loginLayout_contents");
		System.out.println("getSignup到達");
		return "shopping/loginLayout";
	}

	@PostMapping("/signup")
	public String postSignup(@ModelAttribute @Validated(GroupOrder.class) SignupForm form, BindingResult bindingResult,Model model) throws MessagingException {
		System.out.println("PostSignup到達");
		model.addAttribute("contents", "shopping/login::loginLayout_contents");

		if(bindingResult.hasErrors()) {
			System.out.println("validated");
			return getSignup(form,model);
		}

		UsersDTO usersdto = new UsersDTO();

		usersdto.setUser_id(form.getUser_id());
		usersdto.setPassword(form.getPassword());
		usersdto.setUser_name(form.getUser_name());
		usersdto.setRole("notAdmin");
		String user_id = usersdto.getUser_id();
		
		//新規ユーザー登録時にすでに登録されているユーザーIDを入力された場合ははじく
		String result = usersService.check(user_id);
		if(result.equals("1")) {
			model.addAttribute("result","入力されたユーザーIDはすでに使用されています。");
			return getSignup(form,model);
		}

		//ユーザー情報をDBに格納
		usersService.insertOne(usersdto);

		String id = usersService.selectId(user_id);
		int getId = Integer.parseInt(id);

		Usege_usersDTO usegedto = new Usege_usersDTO();
		usegedto.setUser_id(getId);
		usegedto.setBirthday(form.getBirthday());
		usegedto.setAddress(form.getAddress());

		usegeService.insertOne(usegedto);
		
		//新規ユーザー登録完了メールを送信
		mailService.signupSendMail();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String mailAddress = auth.getName();
		mailService.adminSendMail(mailAddress);

		return "shopping/loginLayout";
	}

}
