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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
@Entity
@Table(name="appointment_status")
public class AppointmentStatus {
	
	@Id
	@Column(name="appointment_status_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int appointmentStatusId;
	
	
	@Column(name="appointment_status")
	private String appointmentStatus;
	
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	
	@JsonBackReference
	@OneToMany(mappedBy="appointmentStatus", fetch=FetchType.EAGER)
	private List<Appointment> appointmentList = new ArrayList<>();

}
