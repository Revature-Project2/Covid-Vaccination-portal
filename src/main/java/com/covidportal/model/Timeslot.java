package com.covidportal.model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@Table(name="timeslots")
public class Timeslot {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="timeslot_id")	
	private int timeslotId;	
	
	@Column(name="date", unique=true, nullable=false)
//	@Basic	
	private long dateTime; // Type date
	
//	@Column(name="time")// Type time
////	@Basic	
//	private String time;	
	
	@JsonIgnore
	//@JsonBackReference
	@OneToMany(mappedBy="timeslot", fetch=FetchType.EAGER)
	private List<Appointment> appointmentList = new ArrayList<>();
	
	@JsonIgnore
	//@JsonBackReference
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

	public int getTimeslotId() {
		return timeslotId;
	}

	public void setTimeslotId(int timeslotId) {
		this.timeslotId = timeslotId;
	}

	public long getDateTime() {
		return dateTime;
	}

	public void setDateTime(long dateTime) {
		this.dateTime = dateTime;
	}

	public List<Appointment> getAppointmentList() {
		return appointmentList;
	}

	public void setAppointmentList(List<Appointment> appointmentList) {
		this.appointmentList = appointmentList;
	}

	public List<TimeslotJoinClinic> getTimeslotClinicList() {
		return timeslotClinicList;
	}

	public void setTimeslotClinicList(List<TimeslotJoinClinic> timeslotClinicList) {
		this.timeslotClinicList = timeslotClinicList;
	}	
	

}
