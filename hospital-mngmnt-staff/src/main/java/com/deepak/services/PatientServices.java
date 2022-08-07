package com.deepak.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deepak.custom_exception.NoSuchPatientFoundException;
import com.deepak.custom_exception.PatientAlreadyExistsException;
import com.deepak.dtos.PatientDTO;
import com.deepak.entities.AdmitStatus;
import com.deepak.entities.Doctor;
import com.deepak.entities.Patient;
import com.deepak.entities.PatientRecordDetails;
import com.deepak.repository.DoctorRepository;
import com.deepak.repository.PatientRecordsRepository;
import com.deepak.repository.PatientRepository;

@Service
@Transactional
public class PatientServices {

	@Autowired
	PatientRepository patientDao;

	@Autowired
	PatientRecordsRepository patientRecordsDao;

	@Autowired
	DoctorRepository doctorRepository;

	@Autowired
	ModelMapper modelMapper;

	public int addPatient(PatientDTO patientDetails) throws PatientAlreadyExistsException {
		if (!patientDao.existsByEmail(patientDetails.getEmail())) {
			Patient patient = modelMapper.map(patientDetails, Patient.class);

			PatientRecordDetails prd = new PatientRecordDetails();
			prd.setPatient(patient);
			prd.setStatus(AdmitStatus.ADMIT);
			prd.setDateOfAdmit(patientDetails.getDateOfAdmit());
			prd.setExpenses(patientDetails.getExpenses());
			prd.setRoomNo(patientDetails.getRoomNo());

			Doctor doc = doctorRepository.getById(patientDetails.getDoctorId());
			prd.setDoctor(doc);

			List<PatientRecordDetails> list = patient.getPatientRecordDetails();
			if(list == null)
				list = new ArrayList<>();
			list.add(prd);
			patient.setPatientRecordDetails(list);
			
			Patient dbPatient = patientDao.save(patient);
			if (dbPatient != null)
				return 1;
			else
				return 0;
		} else {
			throw new PatientAlreadyExistsException("patient with email = " + patientDetails.getEmail() + " exists!!!");
		}
	}

	public List<PatientDTO> getAllPatients() {
		List<Patient> patients = patientDao.findAll();

		if (patients.size() > 0) {
			List<PatientDTO> patientDTOList = new ArrayList<>();

			for (Patient p : patients) {
				PatientDTO patientDTO = modelMapper.map(p, PatientDTO.class);

				if (p.getPatientRecordDetails().size() > 0) {
					PatientRecordDetails prd = p.getPatientRecordDetails().get(0);
					patientDTO.setDateOfAdmit(prd.getDateOfAdmit());
					patientDTO.setDoctorId(prd.getDoctor().getDoctorId());
					patientDTO.setDoctorName(prd.getDoctor().getDoctorName());
					patientDTO.setExpenses(prd.getExpenses());
					patientDTO.setRoomNo(prd.getRoomNo());
					patientDTO.setStatus(prd.getStatus());
				}
				patientDTOList.add(patientDTO);
			}
			return patientDTOList;
		} else {
			throw new NoSuchPatientFoundException("Empty list of patient.");
		}
	}

	public PatientDTO getPatientById(int id) throws NoSuchPatientFoundException {
		if (patientDao.existsById(id)) {
			Patient patient = patientDao.getById(id);
			PatientDTO patientDTO = modelMapper.map(patient, PatientDTO.class);
			
			if (patient.getPatientRecordDetails().size() > 0) {
				PatientRecordDetails prd = patient.getPatientRecordDetails().get(0);
				patientDTO.setDateOfAdmit(prd.getDateOfAdmit());
				patientDTO.setDoctorId(prd.getDoctor().getDoctorId());
				patientDTO.setDoctorName(prd.getDoctor().getDoctorName());
				patientDTO.setExpenses(prd.getExpenses());
				patientDTO.setRoomNo(prd.getRoomNo());
				patientDTO.setStatus(prd.getStatus());
			}
			return patientDTO;
		} else {
			throw new NoSuchPatientFoundException("patient with id = " + id + " does not exists!!!");
		}
	}

	public int removePatientById(int id) throws NoSuchPatientFoundException {
		if (patientDao.existsById(id)) {
			patientDao.deleteById(id);
			return 1;
		} else {
			throw new NoSuchPatientFoundException("patient with id = " + id + " does not exists!!!");
		}
	}

	public int dischargePatientById(int recordId) {
		if (patientRecordsDao.existsById(recordId)) {
			PatientRecordDetails prd = patientRecordsDao.getById(recordId);
			
			if(prd.getStatus().equals(AdmitStatus.ADMIT)) {
				patientRecordsDao.dischargeById(recordId);
				return 1;
			}else {
				throw new PatientAlreadyExistsException("Record: " + recordId  + " is already in Discharge state");
			}
		} else {
			throw new NoSuchPatientFoundException("patient with id = " + recordId + " does not exists!!!");
		}
	}

	public void addMockDoctor(List<String> doctorData) {
		for (String name : doctorData) {
			Doctor doc = new Doctor();
			doc.setDoctorName(name);
			doctorRepository.save(doc);
		}
	}

}
