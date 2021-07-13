package com.example.demo.login.domail.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CartForm {

	private int id;

	private int user_id;

	private int product_id;

	@NotBlank
	private String product_count;
}
