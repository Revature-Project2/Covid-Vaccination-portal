package com.covidportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.covidportal.model.ClinicTimeslotId;
import com.covidportal.model.TimeslotJoinClinic;

public interface TimeslotJoinClinicRepository extends JpaRepository
<TimeslotJoinClinic, ClinicTimeslotId> {

	
	
}
