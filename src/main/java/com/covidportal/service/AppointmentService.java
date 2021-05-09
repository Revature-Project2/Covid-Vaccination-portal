package com.covidportal.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covidportal.model.Appointment;
import com.covidportal.repository.AppointmentRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class AppointmentService {
	
	private AppointmentRepository aRepo;	
	
	public void bookAppointment(Appointment appointment) {
		 aRepo.save(appointment);
	}
	
	public List<Appointment> findAllAppointments(){
		return aRepo.findAll();
	}
	
	public List<Appointment> findByUserId(int userId){
		return aRepo.findByUserId(userId);
	}
	
	public List<Appointment> findByClinicId(int clinicId){
		return aRepo.findByClinicId(clinicId);
	}
	
	public List<Appointment> findByClinicIdAndTimeslotId(int clinicId, int timeslotId){
		return aRepo.findByClinicIdAndTimeslotId(clinicId, timeslotId);
	}
	
	public List<Appointment> findByClinicIdAndDate(int clinicId, Date date){
		return aRepo.findByClinicIdAndDate(clinicId, date);
	}
	
	public List<Appointment> findByConfirmationNumber(int confirmationNumber){
		return aRepo.findByConfirmationNumber(confirmationNumber);
	}
	
	public Appointment findByAppointmentId(int appointmentId) {
		return aRepo.findByAppointmentId(appointmentId);
	} 
}














