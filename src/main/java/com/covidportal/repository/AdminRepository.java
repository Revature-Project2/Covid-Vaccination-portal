package com.covidportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.covidportal.model.Admin;




public interface AdminRepository extends JpaRepository<Admin, Integer> {
	
	
	public List<Admin> findAll();
	public Admin findByuserName(String userName);
	public Admin findByuserNameAndPassword(String userName, String password);
	
	//public List<Admin> findByfirstName(String firstName);
	

}
