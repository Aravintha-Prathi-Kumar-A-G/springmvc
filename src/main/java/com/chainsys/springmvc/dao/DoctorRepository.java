package com.chainsys.springmvc.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.chainsys.springmvc.pojo.Doctor;

public interface DoctorRepository extends CrudRepository<Doctor,Integer>{
	Doctor findById(int id);
	Doctor save(Doctor dr);
	// Used for both adding new Doctor and Modifying new Doctor
	void deleteById(int dr_id);
	List<Doctor> findAll();
}
