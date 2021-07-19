package com.example.demo.login.domail.model;



import java.util.Date;

import lombok.Data;

@Data
public class UsersListDTO {

	private String userId;
	
	private String userName;
	
	private Date birthday;
	
	private String address;
	
}
