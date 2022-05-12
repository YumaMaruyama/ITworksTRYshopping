package com.example.demo.login.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
	
	@Autowired
	HttpSession session;
	
	

	@GetMapping("/login")
	public String getLogin(Model model) {
		model.addAttribute("contents", "shopping/login::loginLayout_contents");
		
		session.removeAttribute("SPRING_SECURITY_LAST_EXCEPTION");
		return "shopping/loginLayout";
	}
	
	@GetMapping("/no")
	public String getLoginNo(Model model) {
		model.addAttribute("contents", "shopping/login::loginLayout_contents");
		
		return "shopping/loginLayout";
	}

	@PostMapping("/login")
	public String postLogin(Model model) {
		System.out.println("postLogin到達");
		
		
		return "redirect/productList";
	}
}

