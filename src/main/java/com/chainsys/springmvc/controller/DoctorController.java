package com.chainsys.springmvc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chainsys.springmvc.dao.DoctorRepository;
import com.chainsys.springmvc.dao.EmployeeDao;
import com.chainsys.springmvc.pojo.Appointments;
import com.chainsys.springmvc.pojo.Doctor;
import com.chainsys.springmvc.pojo.DoctorAppointmentsDTO;
import com.chainsys.springmvc.pojo.Employee;
import com.chainsys.springmvc.service.DoctorService;



@Controller
@RequestMapping("/doctor")
public class DoctorController {
	@Autowired
	private DoctorService docservice;
	
	
//	@Bean
//	public void setRepo(DoctorRepository repo) {
//		this.repo = repo;
//	}
	@GetMapping("addform")
	public String doctorAddForm(Model model)
	{
		Doctor doc = new Doctor();
		model.addAttribute("adddoctor",doc);
		return "add-doctor-form";
	}
	@PostMapping("/add")
	public String addnewdoctors(@ModelAttribute("adddoctor") Doctor doc1)
	{
		docservice.save(doc1);
		return "redirect:/doctor/getalldoctors";
	}
	@GetMapping("/updateform")
	public String showUpdateForm(@RequestParam("docid") int id,Model model)
	{
		Doctor doc=docservice.findById(id);
		model.addAttribute("updatedoctor", doc);
		return "update-doctor-form";
	}
    @PostMapping("/updatedoctor")
	public String updatedoctor(@ModelAttribute("updatedoctor") Doctor doc) {
		docservice.save(doc);
		return "redirect:/doctor/getalldoctors";
	}
    @GetMapping("/deletedoctor")
   	public String deletedoctor(@RequestParam("docid") int id) {
   		docservice.deleteById(id);
   		return "redirect:/doctor/getalldoctors";
   	}
       @GetMapping("/finddoctorbyid")
   	public String findDoctorById(@RequestParam("docid") int id, Model model) {
       	Doctor doc= docservice.findById(id);
       	model.addAttribute("finddoctorbyid", doc);
   		return "find-doctor-id-form";
   	}
       @GetMapping("/getalldoctors")
   	public String getAllDoctors(Model model)
   	{
   		List <Doctor> doclist = docservice.getDoctors();
   		model.addAttribute("alldoctors", doclist);
   		return "list-doctors";
   	}
       @GetMapping("/getdocapp")
       public String getAppointments(@RequestParam("id") int id, Model model) {
           DoctorAppointmentsDTO dto = docservice.getDoctorAndAppointments(id);
           model.addAttribute("getdoc", dto.getDoctor());
           model.addAttribute("applist", dto.getAppointments());
           return "list-doctor-appointments";
       } 	
    // localhost:8080/doctor/trans?id=666 --> Here we need to give which id not have in doctor table
       @GetMapping("/trans")
       public void testTransaction(@RequestParam("id") int id) {
           DoctorAppointmentsDTO dto = new DoctorAppointmentsDTO();
           Doctor dr = new Doctor();
           dr.setDoctor_id(id);
           dr.setDoctor_name("Batman");
           Date dt = new Date(97,8,14);
           dr.setDob(dt);
           dr.setSpeciality("Dentist");
           dr.setCity("Chennai");
           dr.setPhone_no(9988998899l);
           dr.setStandard_fees(70000);
           dto.setDoctor(dr);
          // List<Appointments> appList = new <Appointments> ArrayList();
          // int nextAppId = docservice.getNextAppointmentId();
           for(int i=0;i<=2;i++) {
               Appointments app = new Appointments();
               app.setApp_id(i);
               Date date = new Date(22,7,25);
               app.setApp_date(date);
               app.setDoc_id(id);
               app.setPatient_name("Bruce Wayne");
               app.setFees_collected(i*500);
               dto.addAppointments(app);
           }
           docservice.addDoctorAndAppointments(dto);
           System.out.println("Successfully completed!!!");
       }
       /*
        * 
        * http://localhost:8080/doctor/getalldoctors
        * http://localhost:8080/doctor/addform
        * http://localhost:8080/doctor/getdoctor?id=1200
        * http://localhost:8080/doctor/deletedoctor?id=681
        */
   }
