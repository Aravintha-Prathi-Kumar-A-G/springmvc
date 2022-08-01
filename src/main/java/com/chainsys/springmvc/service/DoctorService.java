package com.chainsys.springmvc.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chainsys.springmvc.dao.AppointmentsRepository;
import com.chainsys.springmvc.dao.DoctorRepository;
import com.chainsys.springmvc.pojo.Appointments;
import com.chainsys.springmvc.pojo.Doctor;
import com.chainsys.springmvc.pojo.DoctorAppointmentsDTO;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository repo;
    @Autowired
    private AppointmentsRepository apprepo;

    public List<Doctor> getDoctors() {
        List<Doctor> docList = repo.findAll();
        return docList;
    }
    @Transactional
    public Doctor save(Doctor dr) {
        return repo.save(dr);
    }

    public Doctor findById(int id) {
        return repo.findById(id);
    }
    @Transactional
    public void deleteById(int id) {
        repo.deleteById(id);
    }
    public DoctorAppointmentsDTO getDoctorAndAppointments(int id) {
        Doctor dr = findById(id);
        DoctorAppointmentsDTO dto = new DoctorAppointmentsDTO();
        dto.setDoctor(dr);
        for (int i = 0; i <= 5; i++) {
            Appointments app = new Appointments();
            app.setApp_id(i);
            Date dt = new Date(22, 7, 25);
            app.setApp_date(dt);
            app.setDoc_id(id);
            app.setPatient_name("Aravinth");
            app.setFees_collected(i * 500);
            dto.addAppointments(app);
        }
        return dto;
    }
    @Transactional
    public void addDoctorAndAppointments(DoctorAppointmentsDTO dto) {
        Doctor dr = dto.getDoctor();
        save(dr);
        List<Appointments> appList = dto.getAppointments();
        int count = appList.size();
        for(int i=0;i<count;i++) {
            apprepo.save(appList.get(i));
        }
    }
	/*
	 * public int getNextAppointmentId() { return apprepo.getNextId(); }
	 */
}