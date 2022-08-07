package com.deepak.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "patient")
@Getter 
@Setter
@NoArgsConstructor
public class Patient {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int patientId;
	private String firstName;
	private String lastName;
	
	@Column(unique = true)
	private String email;
	private int age;
	private String bloodGroup;
	
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
	private List<PatientRecordDetails> patientRecordDetails;
}
