package com.covidportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.covidportal.model.Clinic;
import com.covidportal.model.ClinicTimeslotId;
import com.covidportal.model.Timeslot;
import com.covidportal.model.TimeslotJoinClinic;

public interface TimeslotJoinClinicRepository extends JpaRepository
<TimeslotJoinClinic, ClinicTimeslotId> {

	TimeslotJoinClinic findByTimeslotAndClinic(Timeslot t, Clinic c);
	List<TimeslotJoinClinic> findTimeslotsByClinicAndStatus(Clinic c, Boolean status);
//	List<Integer> findTimeslotIdByClinicAndStatus(Clinic c, Boolean status);
	
}
