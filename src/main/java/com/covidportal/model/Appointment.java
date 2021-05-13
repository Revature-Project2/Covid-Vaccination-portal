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

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@AllArgsConstructor
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
	
//	@Column(name="clinic_id")
//	@Setter(AccessLevel.NONE)	
//	private int clinicId;
	
//	@Column(name="user_id", nullable=false)
//	@Setter(AccessLevel.NONE)	
//	private int userId;
//	
//	@Column(name="timeslot_id", nullable=false)
//	@Setter(AccessLevel.NONE)	
//	private int timeslotId;
	
//	@Column(name="appointment_status_id", nullable=false)
//	private int appointmentStatus_id;
	
//	@Column(name="vaccine_type_id", nullable=false)
//	private int vaccineTypeId;
	
	@Column(name="shot_number")
	private int shot_number;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="clinic_id")
	private Clinic clinic;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="timeslot_id")
	private Timeslot timeslot;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="appointment_status_id")
	private AppointmentStatus appointmentStatus;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="vaccine_type_id")
	private VaccineType vaccineType;

//	public Appointment(int appointmentStatus_id, int vaccineTypeId, int shot_number, Clinic clinic, User user,
//			Timeslot timeslot, AppointmentStatus appointmentStatus, VaccineType vaccineType) {
//		super();
//		this.appointmentStatus_id = appointmentStatus_id;
//		this.vaccineTypeId = vaccineTypeId;
//		this.shot_number = shot_number;
//		this.clinic = clinic;
//		this.user = user;
//		this.timeslot = timeslot;
//		this.appointmentStatus = appointmentStatus;
//		this.vaccineType = vaccineType;
//	}
//
//	public Appointment(int appointmentId, int appointmentStatus_id, int vaccineTypeId, int shot_number, Clinic clinic,
//			User user, Timeslot timeslot, AppointmentStatus appointmentStatus, VaccineType vaccineType) {
//		super();
//		this.appointmentId = appointmentId;
//		this.appointmentStatus_id = appointmentStatus_id;
//		this.vaccineTypeId = vaccineTypeId;
//		this.shot_number = shot_number;
//		this.clinic = clinic;
//		this.user = user;
//		this.timeslot = timeslot;
//		this.appointmentStatus = appointmentStatus;
//		this.vaccineType = vaccineType;
//	}
	
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


}

