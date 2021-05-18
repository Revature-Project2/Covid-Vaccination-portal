package com.covidportal.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.covidportal.model.AppointmentStatus;
public interface AppointmentStatusRepository extends JpaRepository<AppointmentStatus, Integer> {
	
	
//	@Query("select as from AppointmentStatus as where as.AppointmentStatusId = ?1")
//  public  List<AppointmentStatus> findByAppointmentStatusId(int id);
  public AppointmentStatus findByAppointmentStatusId(int appointmentStatusId);
}