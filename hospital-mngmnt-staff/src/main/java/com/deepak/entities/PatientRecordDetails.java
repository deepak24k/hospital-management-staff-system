package com.deepak.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity 
@Table(name = "patient_records")
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PatientRecordDetails {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private int recordId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "patientId")
	private Patient patient;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "doctorId")
	private Doctor doctor;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfAdmit;
	private int roomNo;
	private double expenses;
	
	@Enumerated(EnumType.STRING)
	private AdmitStatus status;

}
