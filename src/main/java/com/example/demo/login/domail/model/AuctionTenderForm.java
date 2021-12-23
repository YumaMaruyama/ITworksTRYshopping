package com.example.demo.login.domail.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class AuctionTenderForm {

	@NotBlank(groups = ValidGroup1.class)
	@Pattern(regexp = "[0-9]*", groups = ValidGroup2.class)
	private String tenderPrice;
	
	private int newTenderPrice;
}
