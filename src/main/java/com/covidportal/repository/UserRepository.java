package com.covidportal.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.covidportal.model.User;



public interface UserRepository extends JpaRepository<User, Integer>{
	
	public List<User> findAll();
	public User findByHealthCardNumber(String healthCardNumber);
	public User findByConfirmationNumber(String confirmationNumber);
	public User findByConfirmationNumberAndEmail(String confirmationNumber , String email);
<<<<<<< HEAD
    public User findByFirstName(String firstname);
=======
	
>>>>>>> 957759258d9d41a78ac3e5a6c438dd30c674e972

}