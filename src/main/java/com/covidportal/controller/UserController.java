package com.covidportal.controller;

import java.sql.Date;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.covidportal.model.User;
import com.covidportal.service.UserService;

@RestController
@RequestMapping(value = "/users")
@CrossOrigin(origins = "*")
public class UserController {

	UserService userServ;

	public UserController() {

	}

	@Autowired
	public UserController(UserService userServ) {
		super();
		this.userServ = userServ;
	}

	// This is tested with postmap and working
	@GetMapping("/initial")
	public ResponseEntity<String> inserInitialValues()

	{

		User user1 = new User();
		String date1 = "1994-10-17";
		user1.setFirstName("Divya");
		user1.setLastName("Reddy");
		user1.setEmail("Divyareddy@gmail.com");
		user1.setDateOfBirth(java.sql.Date.valueOf(date1));
		user1.setConfirmationNumber("Confirm2s2");
		user1.setHealthCardNumber("heah144");
		user1.setAddress("42 Blackdome Cres,Kanata,On,K2T1A9");
		user1.setPhoneNumber("3154502989");

		String phone2 = "6474502989";
		// input.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3")
		User user2 = new User();
		String date2 = "1992-01-17";
		user2.setFirstName("Mona");
		user2.setLastName("Borisagar");
		user2.setEmail("Mona@gmail.com");
		user2.setDateOfBirth(java.sql.Date.valueOf(date2));
		user2.setConfirmationNumber("Conf56s2s2");
		user2.setHealthCardNumber("health147");
		user2.setAddress("42 Blackdome Cres,Toronto,On,K2T1A9");
		user2.setPhoneNumber(phone2);

		// List<User> uList=null;
		/*
		 * List<User> uList=new ArrayList<User>(Arrays.asList(new User("Divya", "Reddy",
		 * new Calendar("1993-01-17"),3154502989,"divya@gmail.com",
		 * "balckdome kanata k2t1A9", "HealthCard00", "confirmation46s"), new
		 * User("Divya", "Reddy", "1993-01-17",3154502989,"divya@gmail.com",
		 * "balckdome kanata k2t1A9", "HealthCard00", "confirmation46s")));
		 */

		ArrayList<User> uList = new ArrayList<User>();

		uList.add(user1);
		uList.add(user2);
		for (User u : uList) {
			userServ.insertUser(u);
		}

		return new ResponseEntity<String>("User inserted", HttpStatus.CREATED);
	}

//This is working.Tested with postmap	
	@GetMapping()
	public ResponseEntity<List<User>> getAllUsers() {
		return new ResponseEntity<List<User>>(userServ.findAll(), HttpStatus.CREATED);
	}

	// This is working.Tested with postmap

	@PostMapping()
	public ResponseEntity<Object> insertUser(@RequestBody User user) {
		System.out.println(user);
		userServ.insertUser(user);
		return new ResponseEntity<Object>(userServ.getUserByHealthCardNumber(user.getHealthCardNumber()),
				HttpStatus.CREATED);

	}

	// This is working.Tested with postmap

	@GetMapping("/findUserByHealtCardNumber/{healthCardNumber}") //chnage to post
	public ResponseEntity<User> getUser(@PathVariable("healthCardNumber") String cardNumber) {

		User user = userServ.getUserByHealthCardNumber(cardNumber);
		if (user == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}

	}

	// This is working.Tested with postmap
//post method
	@PostMapping("/findUserByConfimationNumber/{confirmationNumber}")
	public ResponseEntity<User> getUserByConfirmationNumber(@PathVariable("confirmationNumber") String confirmationNumber) {

		User user = userServ.getUserByConfirmationNumber(confirmationNumber);
		if (user == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}

	}

	//
	// This is working.Tested with postmap

	@DeleteMapping("/{healthCardNumber}")
	public ResponseEntity<String> deleteUser(@PathVariable("healthCardNumber") String cardNumber) {

		User user = userServ.getUserByHealthCardNumber(cardNumber);
		System.out.println("In dlete method" + user);
		userServ.deleteUser(user);
		return new ResponseEntity<String>("User Deleted", HttpStatus.GONE);

	}

}
