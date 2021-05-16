package com.covidportal.repository;

import java.sql.Date;
import java.util.List;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.covidportal.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{
	public List<Appointment> findAll();
	@Query("SELECT appointment from Appointment as appointment WHERE appointment.user.userId = :userId")
	public List<Appointment> findByUserId(int userId);
	
	public List<Appointment> findByUser(com.covidportal.model.User user);
	
	@Transactional
	public void deleteByAppointmentId(int appointmentId);
	
//	public List<Appointment> findByUserId(int user_id);
//	public List<Appointment> findByClinicId(int clinic_id);
//	public List<Appointment> findByClinicIdAndTimeslotId(int clinicId, int timeslotId);
//	public List<Appointment> findByClinicIdAndDate(int clinicId, Date date); // cast to Date
//	public List<Appointment> findByConfirmationNumber(int confirmationNumber);
	public Appointment findByAppointmentId(int appointmentId);
}
