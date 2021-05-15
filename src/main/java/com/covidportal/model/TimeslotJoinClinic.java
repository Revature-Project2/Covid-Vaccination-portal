package com.covidportal.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

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
@Table(name="Timeslot_Join_Clinic")
public class TimeslotJoinClinic {
	
	@EmbeddedId
	private ClinicTimeslotId id = new ClinicTimeslotId();

	
	@Column(name="status")
	private boolean status=true;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
//	@JoinColumn(name="clinic_id")
	@MapsId("clinicId")
	private Clinic clinic;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
//	@JoinColumn(name="timeslot_id")
	@MapsId("timeslotId")
	private Timeslot timeslot;

	public TimeslotJoinClinic(ClinicTimeslotId id, Clinic clinic, Timeslot timeslot) {
		super();
		this.id = id;
		this.clinic = clinic;
		this.timeslot = timeslot;
	}

	public TimeslotJoinClinic(boolean status, Clinic clinic, Timeslot timeslot) {
		super();
		this.status = status;
		this.clinic = clinic;
		this.timeslot = timeslot;
	}
	
	
	
}
