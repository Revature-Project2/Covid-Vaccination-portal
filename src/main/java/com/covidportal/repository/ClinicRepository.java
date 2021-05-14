package com.covidportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.covidportal.model.Clinic;



public interface ClinicRepository extends JpaRepository<Clinic, Integer> {
	
	public List<Clinic> findAll();
	public Clinic findByClinicName(String clinicName);
	public List<Clinic> findByAddress(String address);
	public List<Clinic> findByNumberOfBeds(int numberOfBeds);
	public List<Clinic> findByClinicId(int id);

}
