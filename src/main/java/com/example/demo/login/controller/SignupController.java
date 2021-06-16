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
import com.example.demo.login.domail.model.UsersDTO;
import com.example.demo.login.domail.service.UsersService;

@Controller
public class SignupController {

	@Autowired
	UsersService usersService;

	@GetMapping("/signup")
	public String getSignup(@ModelAttribute SignupForm form,Model model) {
		System.out.println("getSignup到達");
		return "shopping/signup";
	}

	@PostMapping("/signup")
	public String postSignup(@ModelAttribute @Validated(GroupOrder.class) SignupForm form, BindingResult bindingResult,Model model) {
		System.out.println("PostSignup到達");
		if(bindingResult.hasErrors()) {
			System.out.println("validated");
			return getSignup(form,model);
		}

		UsersDTO usersdto = new UsersDTO();

		usersdto.setUser_id(form.getUser_id());
		usersdto.setPassword(form.getPassword());
		usersdto.setUser_name(form.getUser_name());
		usersdto.setBirthday(form.getBirthday());
		usersdto.setRole("notAdmin");

		int usersList = usersService.insertOne(usersdto);


		return "shopping/login";
	}

}
