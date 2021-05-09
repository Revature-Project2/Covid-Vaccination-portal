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

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="timeslots")
public class Timeslot {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="timeslot_id")	
	private int timeslotId;	
	
	@Column(name="date")
	@Basic	
	private Date date; // Type date
	
	@Column(name="time")// Type time
	@Basic	
	private Time time;	
	
	@OneToMany(mappedBy="timeslot", fetch=FetchType.LAZY)
	private List<Appointment> appointmentList = new ArrayList<>();
	
	public Timeslot( Date date, Time time) {
		super();		
		this.date = date;
		this.time = time;		
	}	

}
