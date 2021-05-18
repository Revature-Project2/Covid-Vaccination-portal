package com.covidportal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covidportal.model.Appointment;
import com.covidportal.model.Clinic;
import com.covidportal.model.Timeslot;
import com.covidportal.model.TimeslotJoinClinic;
import com.covidportal.repository.AppointmentRepository;
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
	private AppointmentRepository aRepo;
	
	
	public List<TimeslotJoinClinic> findTimeslotsJoinByClinicAndStatus(Boolean status, String cName) {
		Clinic c= cRepo.findByClinicName(cName);
		return tcRepo.findTimeslotsByClinicAndStatus(c,status);
	}
	
	public int findByAppointmentId(int id) {
		return (aRepo.findByAppointmentId(id).getTimeslot().getTimeslotId());
	}
	
	
	public List<Long> findTimeslotsByClinicAndStatus(Boolean status, String cName) {
		Clinic c= cRepo.findByClinicName(cName);
		System.out.println(c);
		List<TimeslotJoinClinic> joins = tcRepo.findTimeslotsByClinicAndStatus(c, status);
		List<Long> times = new ArrayList<>();
		for(TimeslotJoinClinic j:joins) {
//			System.out.println(j.getTimeslot().getDateTime());
			Long time = j.getTimeslot().getDateTime();
			times.add(time);
		}
		return times;		
	}
	
//	public List<Timeslot> findTimeslotsByClinicAndStatus(Boolean status, String cName) {
//		Clinic c= cRepo.findByClinicName(cName);
//		List<TimeslotJoinClinic> joins = tcRepo.findTimeslotsByClinicAndStatus(c,status);
//		System.out.println(joins);
//		List<Timeslot> ts = new ArrayList<>();
////		for(TimeslotJoinClinic j: joins) {
//////			int tId = j.getId().getTimeslotId();
////			int tId = j.getTimeslot().getTimeslotId();
////			ts.add(tRepo.findByTimeslotId(tId));
////		}
//		System.out.println(ts);
//		return ts;
//	}
//	
	public List<Appointment> findAppointments(long time){
		Timeslot t= tRepo.findBydateTime(time);
		return aRepo.findByTimeslot(t);
	}
	
	public List<Appointment> findAppointmentsByClinic(String clinic){
		Clinic c= cRepo.findByClinicName(clinic);
		return aRepo.findByClinic(c);
	}
	
	
	public void createTimeslot(Timeslot tslot) {
		tRepo.save(tslot);
	}

	public boolean checkTimeExists(long t) {
		if(tRepo.findBydateTime(t)!=null) {
			return true;
		}
		return false;
	}
	
	public boolean checkjoinTimeExists(Timeslot t, Clinic c) {
		if(tcRepo.findByTimeslotAndClinic(t,c)!=null) {
			return true;
		}
		return false;
	}
	public void createTimeslotAll(List<Timeslot> tslots, String clinicName) {
		List<TimeslotJoinClinic> tcs = new ArrayList<>();
		Clinic clinic = cRepo.findByClinicName(clinicName);
		tRepo.saveAll(tslots);
		for(Timeslot tss: tslots) {
			if(tcRepo.findByTimeslotAndClinic(tss,clinic)==null) {
				tcs.add(new TimeslotJoinClinic(true, clinic,tss));
			}
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
