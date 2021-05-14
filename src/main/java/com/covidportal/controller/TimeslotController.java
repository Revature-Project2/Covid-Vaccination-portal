package com.covidportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
