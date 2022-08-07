package com.deepak.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deepak.dtos.DoctorNameListDTO;
import com.deepak.dtos.PatientDTO;
import com.deepak.dtos.Response;
import com.deepak.services.PatientServices;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/patient")
public class PatientController {
	
	@Autowired
	PatientServices pServices;
	
	@PostMapping("/addMockDoctor")
	public ResponseEntity<?> addMockDoctor(@RequestBody DoctorNameListDTO doctorNameList) {
		pServices.addMockDoctor(doctorNameList.getDoctorNameList());

		return Response.success("Stored");
	}
	
	@PostMapping("/addPatient")	
	public ResponseEntity<?> addPatient(@RequestBody PatientDTO patientData) {
		int updateCount = pServices.addPatient(patientData);
		if (updateCount == 1)
			return Response.success("added");
		return Response.error("adding failed");
	}

	
	@GetMapping("/getAllPatients")
	public ResponseEntity<?> getAllPatients() {
		List<PatientDTO> patients = pServices.getAllPatients();

		return Response.success(patients);
	}

	
	@GetMapping("/getPatient/{id}")
	public ResponseEntity<?> getPatientById(@PathVariable("id") int patientId) {
		PatientDTO patient = pServices.getPatientById(patientId);
		if (patient != null)
			return Response.success(patient);
		return Response.success("Failed invalid patient id");

	}
	
	@DeleteMapping("/removePatient/{id}")
	public ResponseEntity<?> removePatientById(@PathVariable("id") int patientId) {
		pServices.removePatientById(patientId);
		return Response.success("success removed");
	}

	
	@PutMapping("/dischargePatient/{id}")
	public ResponseEntity<?> dischargePatientById(@PathVariable("id") int recordId) {
		int ch = pServices.dischargePatientById(recordId);
		return Response.success("Patient with recordId : " + recordId + " is discharged Successfully.");
	}
	
}
