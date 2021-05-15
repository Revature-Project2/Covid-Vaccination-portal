package com.covidportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.covidportal.model.Timeslot;

public interface TimeslotRepository extends JpaRepository<Timeslot, Integer> {
	public List<Timeslot> findAll();
	public Timeslot findByTimeslotId(int timeslotId);
//	public List<Timeslot> findByClinicId(int clinicId);
	public  Timeslot findBydateTime(long datetime);
}
