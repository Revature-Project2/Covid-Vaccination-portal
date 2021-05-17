package com.covidportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covidportal.model.User;
import com.covidportal.repository.UserRepository;


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


	
	
	public User findByUserId(int userId) {
        return userRepo.findByUserId(userId);
    }
	
	public void deleteByUserId(int id) {
        userRepo.deleteByUserId(id);
    }
	public void deleteUser(User user)
	{
		userRepo.delete(user);
	}
	
	public User findByFirstname(String firstname)
    {
        return userRepo.findByFirstName(firstname);
    }
	
	public User getUserByConfirmationNumberAndEmail(String confirmationNumber, String email)
	{
			return userRepo.findByConfirmationNumberAndEmail(confirmationNumber, email);
	}
	
}
