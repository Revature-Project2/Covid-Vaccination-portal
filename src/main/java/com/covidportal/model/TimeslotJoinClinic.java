package com.covidportal.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@Entity
@Table(name="Timeslot_Join_Clinic")
public class TimeslotJoinClinic {
	
	@EmbeddedId
	private ClinicTimeslotId id = new ClinicTimeslotId();

	
	@Column(name="status", nullable = false, columnDefinition = "boolean default true")
	private boolean status;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JsonManagedReference
//	@JoinColumn(name="clinic_id")
	@MapsId("clinicId")
	private Clinic clinic;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
//	@JoinColumn(name="timeslot_id")
	@JsonManagedReference
	@MapsId("timeslotId")
	private Timeslot timeslot;
}
