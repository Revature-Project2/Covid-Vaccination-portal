package com.covidportal.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	
	@PostMapping("/opendates")
	public ResponseEntity<String> insertAvailableDates(@RequestParam("date1") long date1, 
														@RequestParam("date2") long date2,
														@RequestParam("clinic") String clinic){
		List<Timeslot> times = new ArrayList<>();
		for(long k=0L; k<(date2-date1); k+=600000) {
			times.add(new Timeslot(date1+k));		
		}
		tService.createTimeslotAll(times, clinic);
		return new ResponseEntity<String>("Timeslots created", HttpStatus.CREATED);
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
