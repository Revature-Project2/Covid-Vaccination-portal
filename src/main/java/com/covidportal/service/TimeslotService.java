package com.covidportal.service;

import java.util.ArrayList;
import java.util.List;

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
public class TimeslotService {
	
	private TimeslotRepository tRepo;
	private TimeslotJoinClinicRepository tcRepo;
	private ClinicRepository cRepo;
	
	public void createTimeslot(Timeslot tslot) {
		tRepo.save(tslot);
	}

	
	public void createTimeslotAll(List<Timeslot> tslots, String clinicName) {
		List<TimeslotJoinClinic> tcs = new ArrayList<>();
		Clinic clinic = cRepo.findByClinicName(clinicName);
		tRepo.saveAll(tslots);
		for(Timeslot tss: tslots) {
			tcs.add(new TimeslotJoinClinic(true, clinic,tss));
		}
		tcRepo.saveAll(tcs);
	}
//	public void closeTimeslotAll(List<Timeslot> tslots, String clinicName) {
//		List<TimeslotJoinClinic> tcs = new ArrayList<>();
//		Clinic clinic = cRepo.findByClinicName(clinicName);
//		tRepo.saveAll(tslots);
//		for(Timeslot tss: tslots) {
//			tcs.add(new TimeslotJoinClinic(false, clinic,tss));
//		}
//		tcRepo.saveAll(tcs);
//	}
//	public void createTimeslotAll(List<Timeslot> tslots) {
//		List<TimeslotJoinClinic> tcs = new ArrayList<>();
//		List<Clinic> clinics = cRepo.findAll();
//		tRepo.saveAll(tslots);
//		for(Clinic c: clinics) {
//			for(Timeslot tss: tslots) {
//				tcs.add(new TimeslotJoinClinic(c,tss));
//			}
//		}
//		tcRepo.saveAll(tcs);
//	}
	
	
	
	public void updateTime(TimeslotJoinClinic ts) {
		tcRepo.save(ts);
	}
	
	public List<Timeslot> findAll(){
		return tRepo.findAll();
	}
	
	public Timeslot findByTimeslotId(int timeslotId) {
		return tRepo.findByTimeslotId(timeslotId);
	}
	
//	public List<Timeslot> findByClinicId(int clinicId){
//		return tRepo.findByClinicId(clinicId);
//	}
	
	
	public Timeslot findbydateandtime(long datetime)
    {
        return tRepo.findBydateTime(datetime);
    }
    public void inserttimeslot(Timeslot timeslot)
    {
        tRepo.save(timeslot);
    }

}
