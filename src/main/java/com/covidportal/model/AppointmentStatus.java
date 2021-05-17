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

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	
	@JsonManagedReference
	@OneToMany(mappedBy="appointmentStatus", fetch=FetchType.LAZY)
	private List<Appointment> appointmentList = new ArrayList<>();

	public AppointmentStatus(String appointmentStatus) {
		super();
		this.appointmentStatus = appointmentStatus;
	}

	public AppointmentStatus(int appointmentStatusId, String appointmentStatus) {
		super();
		this.appointmentStatusId = appointmentStatusId;
		this.appointmentStatus = appointmentStatus;
	}
	
	

}
