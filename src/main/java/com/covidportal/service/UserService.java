package com.covidportal.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covidportal.repository.UserRepository;
import com.covidportal.model.User;


@Service
public class UserService {
	
	private UserRepository userRepo;
	
	public UserService() {
		
	}
	
	@Autowired
	public UserService(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}

	
	
	
	public List<User> findAll()
	{
		 return userRepo.findAll();
	}

	public void insertUser(User user) {
		// TODO Auto-generated method stub
		userRepo.save(user);
	}
	
	
	public User getUserByHealthCardNumber(String healthCardNumber)
	{
		return userRepo.findByHealthCardNumber(healthCardNumber);
	}
	
	
	public User getUserByConfirmationNumber(String confirmationNumber)
	{
			return userRepo.findByConfirmationNumber(confirmationNumber);
	}
	
	public void deleteUser(User user)
	{
		userRepo.delete(user);
	}
	
}
