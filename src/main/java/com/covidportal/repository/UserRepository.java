package com.covidportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.covidportal.model.User;




public interface UserRepository extends JpaRepository<User, Integer>{
	
	public List<User> findAll();
	public User findByUserId(int userId);
	public User findByHealthCardNumber(String healthCardNumber);
	public User findByConfirmationNumber(String confirmationNumber);
	public User findByFirstName(String firstname);
	
	@Transactional
	public void deleteByUserId(int id);
	public User findByConfirmationNumberAndEmail(String confirmationNumber , String email);
}