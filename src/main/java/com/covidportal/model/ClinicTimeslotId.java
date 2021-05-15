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
@ToString
@Embeddable
public class ClinicTimeslotId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int clinicId;
	private int timeslotId;	
	

}
