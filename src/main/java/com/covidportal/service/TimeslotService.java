package com.covidportal.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covidportal.model.Timeslot;
import com.covidportal.repository.TimeslotRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class TimeslotService {
	
	private TimeslotRepository tRepo;
	
	public void createTimeslot(Timeslot tslot) {
		tRepo.save(tslot);
	}	

	public List<Timeslot> findAll(){
		return tRepo.findAll();
	}	
	
	public Timeslot findByTimeslotId(int timeslotId) {
		return tRepo.findByTimeslotId(timeslotId);
	}	
	
	public Timeslot findByDateAndTime(Date date, Time time) {
		
		return null;
	}
	
//	public List<Timeslot> findByClinicId(int clinicId){
//		return tRepo.findByClinicId(clinicId);
//	}

}
