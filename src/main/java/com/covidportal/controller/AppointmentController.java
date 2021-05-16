package com.covidportal.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.covidportal.model.Appointment;
import com.covidportal.service.AppointmentService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping(value="/appointments")
@CrossOrigin(origins="*")
//@CrossOrigin(origins = "http://localhost:4200")
@NoArgsConstructor
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class AppointmentController {
	
	public AppointmentService aService;	
	
	@GetMapping("/initial")
	public ResponseEntity<String> insertInitialValues(){
//		List<Appointment> aList = new ArrayList<Appointment>(Arrays.asList(
//				new Appointment(1,123, 1, 222, 11, 2),
//				new Appointment(1,123, 2, 555, 11, 2),
//				new Appointment(1,124, 1, 333, 13, 4),
//				new Appointment(1,124, 2, 999, 13, 4)
//				));	
//		for(Appointment app: aList) {
//			aService.bookAppointment(app);
//		}
		
		return new ResponseEntity<String>("Initial Appointments Booked", HttpStatus.CREATED);
	}
	
	@PostMapping()
	public ResponseEntity<String> bookAppointment(@RequestBody Appointment appointment){
		System.out.println(appointment);
		aService.bookAppointment(appointment);
		return new ResponseEntity<String>("Appointed Booked", HttpStatus.CREATED);
	}
	
	@GetMapping("/{user_id}") 
	public ResponseEntity<List<Appointment>> getUserAppointments(@PathVariable("user_id") int user_id) {
		List<Appointment> appList = new ArrayList<>();
		
		appList = aService.getByUserId(user_id);		
		if (appList.size() == 0) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			for(Appointment list : appList) {
				System.out.println(list.getTimeslot().getDateTime());
//				System.out.println(list.getTimeslot().getTime());
			}
			return new ResponseEntity<List<Appointment>>(appList, HttpStatus.OK);
		}

	}
	
	@GetMapping() 
	public ResponseEntity<List<Appointment>> getAllAppointments(){
		return new ResponseEntity<List<Appointment>>(aService.findAllAppointments(), HttpStatus.OK);
	}

}
