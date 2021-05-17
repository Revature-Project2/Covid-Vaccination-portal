package com.covidportal.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.covidportal.model.Appointment;
import com.covidportal.model.AppointmentStatus;
import com.covidportal.model.Clinic;
import com.covidportal.model.Timeslot;
import com.covidportal.model.User;
import com.covidportal.model.VaccineType;
import com.covidportal.service.AppointmentService;
import com.covidportal.service.AppointmentStatusService;
import com.covidportal.service.ClinicService;
import com.covidportal.service.TimeslotService;
import com.covidportal.service.UserService;
import com.covidportal.service.VaccineTypeService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping(value="/appointments")
@CrossOrigin(origins="*")
@NoArgsConstructor
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class AppointmentController {
	
	public AppointmentService aService;	
	public UserService uServe;
	public TimeslotService tServe;
	public VaccineTypeService vServe;
	public ClinicService cServe;
	public AppointmentStatusService asServe;
	
	
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
	
	
	
	@PostMapping("/book")
	public ResponseEntity<String> bookAppointment(@RequestParam("clinic") String cn,				
													@RequestParam("timeslot") long ts,				
													@RequestParam("vaccine") String va,				
													@RequestParam("clinic") String cl,
													@RequestParam("fname") String fn,
													@RequestParam("lname") String ln,
													@RequestParam("email") String em,
													@RequestParam("pnumber") String pn,
													@RequestParam("health") String he,
													@RequestParam("dob") Date dob,
													@RequestParam("addr") String ad												
			
			){
		
		String conf= new UserController().generateConfirmationNumber();
		User newUser = new User(fn, ln, dob, pn, em, ad, he, conf);
		uServe.insertUser(newUser);
		User uId = uServe.getUserByHealthCardNumber(he);
		Timeslot tId = tServe.findbydateandtime(ts);
		VaccineType vId = vServe.findByName(va);
		Clinic cId = cServe.getClinicByName(cl);
		AppointmentStatus aId = asServe.getByStatus(4);
		Appointment app = new Appointment(1,cId, uId, tId, aId, vId);
		
		aService.bookAppointment(app);
		return new ResponseEntity<String>("Appointed Booked", HttpStatus.CREATED);
	}
	
//	public ResponseEntity<List<Appointment>> getAllAppointments(){
//		return new ResponseEntity<List<Appointment>>(aService.findAllAppointments(), HttpStatus.OK);
//	}

}
