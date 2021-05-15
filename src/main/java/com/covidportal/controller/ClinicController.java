package com.covidportal.controller;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.covidportal.model.Clinic;
import com.covidportal.service.ClinicService;

@RestController
@RequestMapping(value="/clinic")
@CrossOrigin(origins="*") 
public class ClinicController {

	private ClinicService cServe;

	public ClinicController() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public ClinicController(ClinicService cServe) {
		super();
		this.cServe = cServe;
	}
	
	
	@GetMapping("/initial")
	public ResponseEntity<String> insertInitialValues(){
		
		

		Time t1 =  Time.valueOf("08:00:00");
		
		
		Time t2 = Time.valueOf("17:00:00"); 
		
	    //System.out.println("t1:"+t1);
	    //System.out.println("t2:"+t2);
		
		
		
		List<Clinic> cList = new ArrayList<Clinic>(Arrays.asList(new Clinic("north york general", "1600 pensylvania ave, washington dc",50,t1,t2)));
		for(Clinic clinic: cList) {
			cServe.insertClinic(clinic);
		}
			
		return new ResponseEntity<String>("Clinic Inserted", HttpStatus.CREATED);
	}
	
	
	@PostMapping()
	public ResponseEntity<String> insertClinic(@RequestBody Clinic c){
		System.out.println(c);
		cServe.insertClinic(c);
		return new ResponseEntity<String>("Clinic created", HttpStatus.CREATED);
	}
	
	@GetMapping()
	public ResponseEntity<List<Clinic>> getAllClinics(){
		return new ResponseEntity<List<Clinic>>(cServe.getAllClinic(), HttpStatus.OK);
	}
	
	
	@GetMapping("/{clinicName}")
	public ResponseEntity<Clinic> getClinicName(@PathVariable("clinicName") String name){
		Clinic c = cServe.getClinicByName(name);
		if(c==null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Clinic>(c, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{ClinicName}")
	public ResponseEntity<String> deleteClinicByName(@PathVariable("clinicName") String name){
		Clinic c = cServe.getClinicByName(name);
		
		return new ResponseEntity<>("Clinic Deleted", HttpStatus.GONE);
	}
	
	@GetMapping("/{clinicAddress}")
	public ResponseEntity<List<Clinic>> getClinicAddress(@PathVariable("clinicAddress") String address){
		return new ResponseEntity<List<Clinic>>(cServe.getClinicByAddress(address), HttpStatus.OK);
		
	}
	

	@GetMapping("/{numberOfBeds}")
	public ResponseEntity<List<Clinic>> getClinicByNumberOfBeds(@PathVariable("numberOfBeds") int numberOfBeds){
		return new ResponseEntity<List<Clinic>>(cServe.getByNumberOfBeds(numberOfBeds), HttpStatus.OK);
	}
	
//	@PostMapping("/updateclinic")
//	public ResponseEntity<Clinic> updateClinic(@RequestBody Clinic c){
//		//System.out.println("Inside update clinic "+ c);
//		String name=c.getClinicName();
//		Clinic tempClinic;
//		Clinic c1= cServe.getClinicByName(c.getClinicName());
//		//System.out.println("In Update clinic"+ c1);
//		c1.setClinicId(c1.getClinicId());
//		if(c.getClosingTime() != null)
//			c1.setClosingTime(c.getClosingTime());
//		
//		if(c.getOpeningTime() != null)
//			c1.setOpeningTime(c.getOpeningTime());
//		
//		if(c.getNumberOfBeds() > 0)
//			c1.setNumberOfBeds(c.getNumberOfBeds());
//	//	System.out.println(c1);
//		cServe.insertClinic(c1);
//	
//		return new ResponseEntity<Clinic>(cServe.getClinicByName(name), HttpStatus.OK);
//	}
	
}
