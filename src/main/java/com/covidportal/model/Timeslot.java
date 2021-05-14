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


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="timeslots")
public class Timeslot {
	
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
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="timeslot_id")	
	private int timeslotId;	
	
	@Column(name="date")
//	@Basic	
	private long dateTime; // Type date
	
//	@Column(name="time")// Type time
////	@Basic	
//	private String time;	
	@JsonBackReference
	@OneToMany(mappedBy="timeslot", fetch=FetchType.EAGER)
	private List<Appointment> appointmentList = new ArrayList<>();
	@JsonBackReference
	@OneToMany(mappedBy="timeslot", fetch=FetchType.EAGER)
	private List<TimeslotJoinClinic> timeslotClinicList = new ArrayList<>();
	
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
	

}
