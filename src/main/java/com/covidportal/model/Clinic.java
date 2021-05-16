package com.covidportal.model;
import java.sql.Time;
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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="clinics")
public class Clinic {
	
	@Id
	@Column(name="clinic_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int clinicId;
	
	@Column(name="clinic_name", unique=true, nullable=false)
	private String clinicName;
	
	@Column(name="clinic_address", unique=true, nullable=false)
	private String address;
	
	@Column(name="number_of_beds")
	private int numberOfBeds;
	
	@Column(name="opening_time")
	private Time openingTime;
	
	@Column(name="closing_time")
	private Time closingTime;
	
	
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToMany(mappedBy="clinic", fetch=FetchType.EAGER)
	//@JsonBackReference
	@JsonIgnore
	private List<Appointment> appointmentList = new ArrayList<>();
	
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToMany(mappedBy="clinic", fetch=FetchType.EAGER)
	//@JsonBackReference
	@JsonIgnore
	private List<TimeslotJoinClinic> timeslotClinicList = new ArrayList<>();
	
	public Clinic() {
		// TODO Auto-generated constructor stub
	}

	public Clinic(int clinicId, String clinicName, String clinicAddress, int numberOfBeds, Time openingTime,
			Time closingTime, List<Appointment> appointmentList, List<TimeslotJoinClinic> timeslotClinicList) {
		super();
		this.clinicId = clinicId;
		this.clinicName = clinicName;
		this.address = clinicAddress;
		this.numberOfBeds = numberOfBeds;
		this.openingTime = openingTime;
		this.closingTime = closingTime;
		this.appointmentList = appointmentList;
		this.timeslotClinicList = timeslotClinicList;
	}
	
	
	public Clinic(String clinicName, String clinicAddress, int numberOfBeds, Time openingTime,
			Time closingTime) {
		super();
	
		this.clinicName = clinicName;
		this.address = clinicAddress;
		this.numberOfBeds = numberOfBeds;
		this.openingTime = openingTime;
		this.closingTime = closingTime;
	}
	
	public Clinic(int clinicId, String clinicName, String clinicAddress, int numberOfBeds, Time openingTime,
			Time closingTime) {
		super();
		this.clinicId = clinicId;
		this.clinicName = clinicName;
		this.address = clinicAddress;
		this.numberOfBeds = numberOfBeds;
		this.openingTime = openingTime;
		this.closingTime = closingTime;
	}

	public String getClinicName() {
		return clinicName;
	}

	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}

	public String getClinicAddress() {
		return address;
	}

	public void setClinicAddress(String clinicAddress) {
		this.address = clinicAddress;
	}

	public int getNumberOfBeds() {
		return numberOfBeds;
	}

	public void setNumberOfBeds(int numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}

	public Time getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(Time openingTime) {
		this.openingTime = openingTime;
	}

	public Time getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(Time closingTime) {
		this.closingTime = closingTime;
	}

	public int getClinicId() {
		return clinicId;
	}
	public void setClinicId(int clinicId2) {
		// TODO Auto-generated method stub
		
	}
	
	
//	@Override
//	public String toString() {
//		return "Clinic [clinicId=" + clinicId + ", clinicName=" + clinicName + ", clinicAddress=" + address
//				+ ", numberOfBeds=" + numberOfBeds + ", openingTime=" + openingTime + ", closingTime=" + closingTime
//				+ "]";
//	}

	

	

}
