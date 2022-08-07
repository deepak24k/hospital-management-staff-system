package com.deepak.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Doctor")
@Getter 
@Setter 
@ToString 
@JsonInclude(value = Include.NON_NULL)
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int doctorId;
	private String doctorName;
	
	@OneToMany(mappedBy = "doctor")
	private List<PatientRecordDetails> patientRecordDetails;
}
