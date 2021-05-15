package com.covidportal.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
<<<<<<< HEAD
//@ToString
=======
@ToString
>>>>>>> 957759258d9d41a78ac3e5a6c438dd30c674e972
@Embeddable
public class ClinicTimeslotId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int clinicId;
	private int timeslotId;	
	

}
