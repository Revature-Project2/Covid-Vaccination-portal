package com.covidportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covidportal.model.Timeslot;
import com.covidportal.model.TimeslotJoinClinic;
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
	
	public void createTimeslot(Timeslot tslot) {
		tRepo.save(tslot);
	}
	
	public void updateTime(TimeslotJoinClinic ts) {
		tcRepo.save(ts);
	}
	
	public List<Timeslot> findAll(){
		return tRepo.findAll();
	}
	
	public Timeslot findByTimeslotId(int timeslotId) {
		return tRepo.findByTimeslotId(timeslotId);
	}
    public Timeslot findbydateandtime(long datetime)
    {
        return tRepo.findBydateTime(datetime);
        
    }
    public void inserttimeslot(Timeslot timeslot)
    {
        tRepo.save(timeslot);
    }
    
//    public List<Timeslot> findByClinicId(int clinicId){
//        return tRepo.findByClinicId(clinicId);
//    }
	
//	public List<Timeslot> findByClinicId(int clinicId){
//		return tRepo.findByClinicId(clinicId);
//	}

}
