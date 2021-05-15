package com.covidportal.model;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

<<<<<<< HEAD

=======
@ToString
>>>>>>> 957759258d9d41a78ac3e5a6c438dd30c674e972
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="timeslots")
public class Timeslot {
	
<<<<<<< HEAD
//	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
//	@Column(name="timeslot_id")	
//	private int timeslotId;	
//	
//	@Column(name="date")
//	@Basic	
//	private Date date; // Type date
//	
//	@Column(name="time")// Type time
//	@Basic	
//	private Time time;	
//	
//	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//	@JsonBackReference
//	@OneToMany(mappedBy="timeslot", fetch=FetchType.EAGER)
//	private List<Appointment> appointmentList = new ArrayList<>();
//
//	
//	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//	@JsonBackReference
//	@OneToMany(mappedBy="timeslot", fetch=FetchType.EAGER)
//	private List<TimeslotJoinClinic> timeslotClinicList = new ArrayList<>();
//	
//	public Timeslot( Date date, Time time, List<Appointment> appointmentList, List<TimeslotJoinClinic> timeslotClinicList) {
//		super();		
//		this.date = date;
//		this.time = time;
//		this.appointmentList = appointmentList;
//		this.timeslotClinicList = timeslotClinicList;
//	}
	
=======
>>>>>>> 957759258d9d41a78ac3e5a6c438dd30c674e972
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="timeslot_id")	
	private int timeslotId;	
	
	@Column(name="date")
<<<<<<< HEAD
//	@Basic	
	private long dateTime; // Type date
	
//	@Column(name="time")// Type time
////	@Basic	
//	private String time;	
	@JsonBackReference
	@OneToMany(mappedBy="timeslot", fetch=FetchType.EAGER)
	private List<Appointment> appointmentList = new ArrayList<>();
=======
	@Basic	
	private Date date; // Type date
	
	@Column(name="time")// Type time
	@Basic	
	private Time time;	
	
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JsonBackReference
	@OneToMany(mappedBy="timeslot", fetch=FetchType.EAGER)
	private List<Appointment> appointmentList = new ArrayList<>();

	
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
>>>>>>> 957759258d9d41a78ac3e5a6c438dd30c674e972
	@JsonBackReference
	@OneToMany(mappedBy="timeslot", fetch=FetchType.EAGER)
	private List<TimeslotJoinClinic> timeslotClinicList = new ArrayList<>();
	
<<<<<<< HEAD
	public Timeslot( long dateTime, List<Appointment> appointmentList, List<TimeslotJoinClinic> timeslotClinicList) {
		super();		
		this.dateTime = dateTime;
		this.appointmentList = appointmentList;
		this.timeslotClinicList = timeslotClinicList;
	}

	public Timeslot(long dateTime) {
		super();
		this.dateTime = dateTime;
	}	
	
=======
	public Timeslot( Date date, Time time, List<Appointment> appointmentList, List<TimeslotJoinClinic> timeslotClinicList) {
		super();		
		this.date = date;
		this.time = time;
		this.appointmentList = appointmentList;
		this.timeslotClinicList = timeslotClinicList;
	}	
>>>>>>> 957759258d9d41a78ac3e5a6c438dd30c674e972

}
