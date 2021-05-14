package com.covidportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covidportal.model.AppointmentStatus;
import com.covidportal.repository.AppointmentRepository;
import com.covidportal.repository.AppointmentStatusRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class AppointmentStatusService {

	public AppointmentStatusRepository asServ;
	
	public List<AppointmentStatus> getappointmentvyid(int id)
	{
		return asServ.findByAppointmentStatusId(id);
	}
}
