package com.chainsys.springmvc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.chainsys.springmvc.pojo.Appointments;

public interface AppointmentsRepository extends CrudRepository<Appointments, Integer> {
	
	Appointments findById (int id);
	Appointments save(Appointments app);
	void deleteById(int app_id);
	List <Appointments> findAll();
	// Define the native query for this method
	// @Query("")
	// int getNextId();

}
