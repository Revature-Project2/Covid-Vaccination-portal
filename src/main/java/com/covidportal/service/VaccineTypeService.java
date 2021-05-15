package com.covidportal.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.covidportal.model.VaccineType;
import com.covidportal.repository.AppointmentRepository;
import com.covidportal.repository.VaccineTypeRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@Service
@NoArgsConstructor
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class VaccineTypeService {
    private VaccineTypeRepository vsRepo;
    public List<VaccineType> findvaccinebyid(int id){
        return vsRepo.findByVaccineTypeId(id);
    }
}