package com.covidportal.controller;

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.covidportal.model.Admin;
import com.covidportal.model.PHash;
import com.covidportal.service.AdminService;

@RestController
@RequestMapping(value="/admin")
@CrossOrigin(origins="*") 
public class AdminController {
	
	private AdminService aServe;
	PHash ph;

	public AdminController() {
	}
	
	
	@Autowired
	public AdminController(AdminService aServe) {
		super();
		this.aServe = aServe;
	}
	
	
	@GetMapping("/initial")
	public ResponseEntity<String> insertInitialValues(){
		
				
		BigInteger phone = new BigInteger("14167777777");
		ph = new PHash();
		ph.setPassword("password");
		String passwd= ph.getHash();
		
		List<Admin> aList = new ArrayList<Admin>(Arrays.asList(new Admin("nash",passwd,"Nisanth","Variyath","nisanth@fakemai.com",phone)));
		for(Admin admin: aList) {
			aServe.insertAdmin(admin);
		}
			
		return new ResponseEntity<String>("Admin Inserted", HttpStatus.CREATED);
	}
	
	
	
	@PostMapping()
	public ResponseEntity<String> insertAdmin(@RequestBody Admin a){
		System.out.println(a);
		aServe.insertAdmin(a);
		return new ResponseEntity<String>("Admin created", HttpStatus.CREATED);
	}
	
	@GetMapping()
	public ResponseEntity<List<Admin>> getAllAdmins(){
		return new ResponseEntity<List<Admin>>(aServe.getAllAdmin(), HttpStatus.OK);
	}
	

		// 	@PostMapping("/login")
	// public ResponseEntity<Object> validateLogin(@RequestBody Admin adm){

	// 	Admin a = aServe.getByUserName(adm.getUserName());
	// 	if(a==null) {
	// 		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	// 	}
	// 	if (adm.getPassword().equals(a.getPassword())) {
	// 		return new ResponseEntity<Object>(a, HttpStatus.OK);
	// 	}
		
	// 	return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
	// }
	
	@PostMapping("/login")
	public ResponseEntity<Admin> getUserNameAndPassword(@RequestBody Admin a){
		
	
		
		
		 Admin a1 = aServe.getByUserName(a.getUserName());
		
		if(a1==null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		else
		{
		
			ph =new PHash(a1.getPassword());
		
		
			
			if( ph.checkPassword(a.getPassword())) {
		          return new ResponseEntity<Admin>(a1, HttpStatus.OK);
		          
			}
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	
	@GetMapping("/{userName}")
	public ResponseEntity<Admin> getUserName(@PathVariable("userName") String userName){
		Admin a = aServe.getByUserName(userName);
		if(a==null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Admin>(a, HttpStatus.OK);
	}

	
	@PostMapping("/pass")
    public ResponseEntity<Object> getByUserNameAndPassword(@RequestBody  Admin a, BindingResult result){

        System.out.println(a);

        if(result.hasErrors()) {
            System.out.println(result.getFieldError());
            return new ResponseEntity<>(result.getFieldError().getCode() + " " + result.getFieldError().getDefaultMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        Admin a1 = aServe.getByUserName(a.getUserName());
        if(a1==null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        else
        {

            ph =new PHash(a1.getPassword());



            if( ph.checkPassword(a.getPassword())) {
                  return new ResponseEntity<Object>(a1, HttpStatus.OK);

            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

	
}
