package com.covidportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.covidportal.model.VaccineType;

public interface VaccineTypeRepository extends JpaRepository<VaccineType, Integer> {
	
	
	public List<VaccineType> findByVaccineTypeId(int id);

}
