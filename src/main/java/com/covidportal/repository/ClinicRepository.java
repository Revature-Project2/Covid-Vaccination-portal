package com.covidportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.covidportal.model.Clinic;



public interface ClinicRepository extends JpaRepository<Clinic, Integer> {
	
	public List<Clinic> findAll();
	public Clinic findByClinicName(String clinicName);
	public List<Clinic> findByAddress(String address);
	public List<Clinic> findByNumberOfBeds(int numberOfBeds);
<<<<<<< HEAD
	public List<Clinic> findByClinicId(int id);
=======
	public Clinic findByClinicId(int id);
>>>>>>> 957759258d9d41a78ac3e5a6c438dd30c674e972

}
