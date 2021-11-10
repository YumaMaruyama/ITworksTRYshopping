package com.example.demo.login.domail.repository;

import java.util.List;

import com.example.demo.login.domail.model.ChallengeProgrammingDTO;

public interface ChallengeProgrammingDao {

	public List<ChallengeProgrammingDTO> projectSelectMany();
	
	public ChallengeProgrammingDTO projectSelectOne(int projectId);
	
	public int contractUpdate(int userId,int productId);

	public ChallengeProgrammingDTO selectBelongings(int productId);
	
	public int updateOne(int productId);
	
}
