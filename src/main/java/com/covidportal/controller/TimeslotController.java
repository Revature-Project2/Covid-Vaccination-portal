package com.covidportal.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.covidportal.model.Appointment;
import com.covidportal.model.Timeslot;
import com.covidportal.model.TimeslotJoinClinic;
import com.covidportal.service.TimeslotService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping(value="/timeslots")
@CrossOrigin(origins="*")
@NoArgsConstructor
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class TimeslotController {
	
	private TimeslotService tService;
	
	
	
	@GetMapping("/dates")
	public ResponseEntity<List<Long>> getTimeslotsForCLinic(@RequestParam("status") Boolean status,
															@RequestParam("clinic") String clinic){
		
		List<Long> tss =tService.findTimeslotsByClinicAndStatus(status, clinic);
		return new ResponseEntity<List<Long>>(tss,HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/appointment")
	public ResponseEntity<List<Appointment>> getTimeslotsForCLinic(@RequestParam("time") long Time){
		List<Appointment> aps= tService.findAppointments(Time);
//		for(Appointment t:aps) {
//			System.out.println(t);
//		}
		return new ResponseEntity<List<Appointment>>(aps,HttpStatus.ACCEPTED);
	}

	
	@GetMapping("/appointments")
	public ResponseEntity<List<Long>> getTimeslotsForCLinic(@RequestParam("clinic") String clinic){
		List<Appointment> aps= tService.findAppointmentsByClinic(clinic);
		List<Long> tis = new ArrayList<>();
		for(Appointment t:aps) {
			System.out.println(t);
			int timeid= tService.findByAppointmentId(t.getAppointmentId());
			long ti = tService.findByTimeslotId(timeid).getDateTime();
			tis.add(ti);
		}
		
		return new ResponseEntity<List<Long>>(tis,HttpStatus.ACCEPTED);
	}
	
	
	@PutMapping("/opendates")
	public ResponseEntity<List<Timeslot>> insertAvailableDates(@RequestParam("date1") long date1, 
														@RequestParam("date2") long date2,
														@RequestParam("clinic") String clinic){
		System.out.println(date1);
		System.out.println(date2);
		System.out.println(clinic);
		List<Timeslot> times = new ArrayList<>();
		for(long k=0L; k<(date2-date1); k+=600000) {
			if(!(tService.checkTimeExists(date1+k))) {
				times.add(new Timeslot(date1+k));
			}else {
				times.add(tService.findbydateandtime(date1+k));
			}
		}
		tService.createTimeslotAll(times, clinic);
		return new ResponseEntity<List<Timeslot>>(times, HttpStatus.CREATED);
	}

	
//	@PostMapping("/closedates")
//	public ResponseEntity<String> closeAvailableDates(@RequestParam("date1") long date1, 
//			@RequestParam("date2") long date2,
//			@RequestParam("clinic") String clinic){
//		List<Timeslot> times = new ArrayList<>();
//		for(long k=0L; k<(date2-date1); k+=600000) {
//			times.add(new Timeslot(date1+k));		
//		}
//		tService.closeTimeslotAll(times, clinic);
//		return new ResponseEntity<String>("Timeslots created", HttpStatus.CREATED);
//	}

	//new Date(2021, 04, 15, 11,40, 00)
	
	
//	@PostMapping("/closeDates")
	
	
	@GetMapping("/initial")
	public ResponseEntity<String> insertInitialValues(){
//		List<Timeslot> tList = new ArrayList<Timeslot>(Arrays.asList(
//				new Timeslot(Date.valueOf("2021-05-09"), Time.valueOf("9:15") ),
//				new Timeslot(Date.valueOf("2021-05-09"), Time.valueOf("9:25") ),
//				new Timeslot(Date.valueOf("2021-05-09"), Time.valueOf("9:35") )));
//		for(Timeslot timeslot: tList) {
//			tService.createTimeslot(timeslot);
//		}
		return new ResponseEntity<String>("Innitial Timeslots Created", HttpStatus.CREATED);
	}
	
	@PostMapping("/newTime")
	public ResponseEntity<String> createTimeslot(@RequestBody Timeslot timeslot){
		System.out.println(timeslot);
		tService.createTimeslot(timeslot);
		return new ResponseEntity<String>("Timeslot Created", HttpStatus.CREATED);
	}	
	
	@GetMapping("/updateTime")
	public ResponseEntity<String> updateStatus2(@RequestBody TimeslotJoinClinic ts){
		tService.updateTime(ts);
		return new ResponseEntity<String>("Innitial Timeslots Created", HttpStatus.CREATED);
}
}
