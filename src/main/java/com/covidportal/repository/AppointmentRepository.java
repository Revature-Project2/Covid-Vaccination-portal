package com.covidportal.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.covidportal.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{
	public List<Appointment> findAll();
//	public List<Appointment> findByUserId(int user_id);
//	public List<Appointment> findByClinicId(int clinic_id);
//	public List<Appointment> findByClinicIdAndTimeslotId(int clinicId, int timeslotId);
//	public List<Appointment> findByClinicIdAndDate(int clinicId, Date date); // cast to Date
//	public List<Appointment> findByConfirmationNumber(int confirmationNumber);
	public Appointment findByAppointmentId(int appointmentId);
}
