package com.covidportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covidportal.model.Admin;
import com.covidportal.repository.AdminRepository;

@Service
public class AdminService {

	private AdminRepository aRepo;
	



	public AdminService() {
	
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	public AdminService(AdminRepository aRepo) {
		super();
		this.aRepo = aRepo;
	}
	
	public List<Admin> getAllAdmin(){
		return aRepo.findAll();
	}
	
	public void insertAdmin(Admin a) {
		aRepo.save(a);              //  save method is actually a saveOrUpdate;
	}
	
	public void deleteAdmin(Admin a) {
		aRepo.delete(a);
	}
	
	public Admin getByUserName(String userName) {
		return  aRepo.findByuserName(userName);

	}
	
	public Admin getByUserNameAndPassword(String userName, String password) {
		return  aRepo.findByuserNameAndPassword(userName, password);

	}
	
	
	
}
