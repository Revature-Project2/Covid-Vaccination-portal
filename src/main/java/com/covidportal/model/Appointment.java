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

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
//@ToString
@Entity
@Table(name="Appointments")
public class Appointment {
	
	@Id
	@Column(name="appointment_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Setter(AccessLevel.NONE)
	private int appointmentId;
	
	
	@Column(name="shot_number")
	private int shot_number;
	
	//@JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="clinic_id")
	private Clinic clinic;
	
	//@JsonIgnore
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user;
	
	//@JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="timeslot_id")
	private Timeslot timeslot;
	
	//@JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="appointment_status_id")
	private AppointmentStatus appointmentStatus;
	
	//@JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="vaccine_type_id")
	private VaccineType vaccineType;

	
	public Appointment(int shot_number, Clinic clinic, User user,
			Timeslot timeslot, AppointmentStatus appointmentStatus, VaccineType vaccineType) {
		super();
		this.shot_number = shot_number;
		this.clinic = clinic;
		this.user = user;
		this.timeslot = timeslot;
		this.appointmentStatus = appointmentStatus;
		this.vaccineType = vaccineType;
	}

	@Autowired
	public Appointment(int appointmentId,int shot_number, Clinic clinic,
			User user, Timeslot timeslot, AppointmentStatus appointmentStatus, VaccineType vaccineType) {
		super();
		this.appointmentId = appointmentId;
		this.shot_number = shot_number;
		this.clinic = clinic;
		this.user = user;
		this.timeslot = timeslot;
		this.appointmentStatus = appointmentStatus;
		this.vaccineType = vaccineType;
	}
	
	public Appointment(int appointmentId, int shot_number, Clinic clinic) {
        super();
        this.appointmentId = appointmentId;
        this.shot_number = shot_number;
        this.clinic = clinic;
    }


}
