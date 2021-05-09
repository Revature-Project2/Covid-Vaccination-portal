package com.covidportal.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
@Entity
@Table(name="Appointments")
public class Appointment {
	
	@Id
	@Column(name="appointment_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Setter(AccessLevel.NONE)
	private int appointmentId;
	
	@Column(name="clinic_id")
	@Setter(AccessLevel.NONE)	
	private int clinicId;
	
	@Column(name="user_id", nullable=false)
	@Setter(AccessLevel.NONE)	
	private int userId;
	
	@Column(name="timeslot_id", nullable=false)
	@Setter(AccessLevel.NONE)	
	private int timeslotId;
	
	@Column(name="appointment_status", nullable=false)
	private int appointmentStatus;
	
	@Column(name="vaccine_id", nullable=false)
	private int vaccineId;
	
	@Column(name="shot_number")
	private int shot_number;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="User_FK")
	private User user;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="Timeslot_FK")
	private Timeslot timeslot;
	
	public Appointment(int clinicId, int userId, int timeslotId, int appointmentStatus,
			int vaccineId, int shot_number) {
		super();	
		this.clinicId = clinicId;
		this.userId = userId;
		this.timeslotId = timeslotId;
		this.appointmentStatus = appointmentStatus;
		this.vaccineId = vaccineId;
		this.shot_number = shot_number;
	}	
}

