package com.covidportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.covidportal.model.Timeslot;

public interface TimeslotRepository extends JpaRepository<Timeslot, Integer> {
	public List<Timeslot> findAll();
	public Timeslot findByTimeslotId(int timeslotId);
//	public List<Timeslot> findByClinicId(int clinicId);
<<<<<<< HEAD
    public  Timeslot findBydateTime(long datetime);
=======

>>>>>>> 957759258d9d41a78ac3e5a6c438dd30c674e972
}
