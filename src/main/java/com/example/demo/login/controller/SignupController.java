package com.example.demo.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.demo.login.domail.service.Usege_usersService;
import com.example.demo.login.domail.service.UsersService;

@Controller
public class SignupController {

	@Autowired
	UsersService usersService;
	@Autowired
	Usege_usersService usegeService;

	@GetMapping("/signup")
	public String getSignup(@ModelAttribute SignupForm form,Model model) {
		model.addAttribute("contents", "shopping/signup::loginLayout_contents");
		System.out.println("getSignup到達");
		return "shopping/loginLayout";
	}

	@PostMapping("/signup")
	public String postSignup(@ModelAttribute @Validated(GroupOrder.class) SignupForm form, BindingResult bindingResult,Model model) {
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

		String result = usersService.check(user_id);
		if(result.equals("1")) {
			model.addAttribute("result","入力されたユーザーIDはすでに使用されています。");
			return getSignup(form,model);
		}

		int usersList = usersService.insertOne(usersdto);
		System.out.println("user_id" + user_id);

		String id = usersService.selectId(user_id);
		int getId = Integer.parseInt(id);

		Usege_usersDTO usegedto = new Usege_usersDTO();
		usegedto.setUser_id(getId);
		usegedto.setBirthday(form.getBirthday());
		usegedto.setAddress(form.getAddress());

		int usegeList = usegeService.insertOne(usegedto);

		return "shopping/loginLayout";
	}

}
