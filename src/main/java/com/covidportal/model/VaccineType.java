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
<<<<<<< HEAD
//@ToString
=======
@ToString
>>>>>>> 957759258d9d41a78ac3e5a6c438dd30c674e972
@Entity
@Table(name="vaccine_types")
public class VaccineType {
	
	@Id
	@Column(name="vaccine_type_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int vaccineTypeId;
	
	
	@Column(name="vaccine_type")	
	private String vaccineType;
	
	
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JsonBackReference
	@OneToMany(mappedBy="vaccineType", fetch=FetchType.EAGER)
	private List<Appointment> appointmentList = new ArrayList<>();

}
