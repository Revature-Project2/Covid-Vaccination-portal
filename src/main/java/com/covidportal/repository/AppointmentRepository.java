package com.covidportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.covidportal.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{
	public List<Appointment> findAll();
	public List<Appointment> findByUserId(int userId);
	public List<Appointment> findByClinicId(int clinicId);
	public List<Appointment> findByClinicIdAndTimeslotId(int clinicId, int timeslotId);
	public List<Appointment> findByClinicIdAndDate(int clinicId, String date); // cast to Date
	public List<Appointment> findByConfirmationNumber(int confirmationNumber);
	public Appointment findByAppointmentId(int appointmentId);
}
