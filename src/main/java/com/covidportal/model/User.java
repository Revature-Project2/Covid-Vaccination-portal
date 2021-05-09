package com.covidportal.model;

import java.sql.Date;


import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@Getter @Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name="Users")
public class User {
	
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;

	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="date_of_birth") 
	@Basic
	private Date dateOfBirth;
	
	@Column(name="phone_number")
	private String  phoneNumber;  //change to bigInt
	
	@Column(name="email" ,unique=true)
	private String email;
	
	@Column(name="address")
	private String address;
	
	
	@Column(name="health_card_number" ,unique=true)
	private String healthCardNumber;
	
	
	@Column(name="confirmation_number",unique=true)
	private String confirmationNumber;

	

	public User(String firstName, String lastName, Date dateOfBirth, String phoneNumber, String email,
			String address, String healthCardNumber, String confirmationNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.healthCardNumber = healthCardNumber;
		this.confirmationNumber = confirmationNumber;
	}
	
	
	


	
	
	
	
	

}

