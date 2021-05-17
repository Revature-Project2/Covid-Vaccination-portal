package com.covidportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covidportal.model.Clinic;
import com.covidportal.model.Timeslot;
import com.covidportal.model.TimeslotJoinClinic;
import com.covidportal.repository.ClinicRepository;
import com.covidportal.repository.TimeslotJoinClinicRepository;
import com.covidportal.repository.TimeslotRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class TimeslotJoinClinicService {

	private TimeslotRepository tRepo;
	private TimeslotJoinClinicRepository tcRepo;
	private ClinicRepository cRepo;
	
//	public Timeslot findTimeslotsByClinicAndStatus(Boolean status, String cName) {
//		Clinic c= cRepo.findByClinicName(cName);
//		return tcRepo.findTimeslotsByClinicAndStatus(status, c);
//	}
}
