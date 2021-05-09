package com.covidportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covidportal.model.Clinic;
import com.covidportal.repository.ClinicRepository;

@Service
public class ClinicService {

	private ClinicRepository CRepo;

	public ClinicService() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public ClinicService(ClinicRepository cRepo) {
		super();
		CRepo = cRepo;
	}
	
	public Clinic getClinicByName(String name) {
		return CRepo.findByClinicName(name);
	}
	
	public void insertClinic(Clinic c) {
		CRepo.save(c);             //  save method is actually a saveOrUpdate
	}
	
	public List<Clinic> getAllClinic(){
		return CRepo.findAll();
	}
	
	public List<Clinic> getClinicByAddress(String address){
		return CRepo.findByAddress(address);
	}
	
	public void deleteClinic(Clinic c) {
		CRepo.delete(c);
	}
	
	public Clinic getByClinicId(int id) {
		return CRepo.findByClinicId(id);
	}
	
	public List<Clinic> getByNumberOfBeds(int numberOfBeds) {
		return CRepo.findByNumberOfBeds(numberOfBeds);
	}

}