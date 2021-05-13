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
@Table(name="vaccine_types")
public class VaccineType {
	
	@Id
	@Column(name="vaccine_type_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int vaccineTypeId;
	
	
	@Column(name="vaccine_type")	
	private String vaccineType;
	
	@OneToMany(mappedBy="vaccineType", fetch=FetchType.LAZY)
	private List<Appointment> appointmentList = new ArrayList<>();

}
