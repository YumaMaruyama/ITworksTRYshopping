package com.example.demo.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String getLogin(Model model) {
		model.addAttribute("contents", "shopping/login::loginLayout_contents");
		return "shopping/loginLayout";
	}

	@PostMapping("/login")
	public String postLogin(Model model) {
		System.out.println("postLogin到達");
		//model.addAttribute("contents", "shopping/productList::loginLayout_contents");
		return "redirect/productList";
	}
}

