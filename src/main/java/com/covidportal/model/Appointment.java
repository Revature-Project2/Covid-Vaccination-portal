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

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
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
	
	@JsonBackReference
	@ManyToOne(/* cascade=CascadeType.ALL, */fetch=FetchType.LAZY)
	@JoinColumn(name="clinic_id")
	private Clinic clinic;
	
	@JsonBackReference
	@ManyToOne(/* cascade=CascadeType.ALL, */fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	@JsonBackReference
	@ManyToOne(/* cascade=CascadeType.ALL, */ fetch=FetchType.LAZY)
	@JoinColumn(name="timeslot_id")
	private Timeslot timeslot;
	
	@JsonBackReference
	@ManyToOne(/* cascade=CascadeType.ALL, */ fetch=FetchType.LAZY)
	@JoinColumn(name="appointment_status_id")
	private AppointmentStatus appointmentStatus;
	
	@JsonBackReference
	@ManyToOne(/* cascade=CascadeType.ALL, */ fetch=FetchType.LAZY)
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

