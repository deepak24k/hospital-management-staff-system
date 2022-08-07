package com.deepak.dtos;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import com.deepak.entities.AdmitStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class PatientDTO {
	
	private String firstName;
	private String lastName;
	private String email;
	private int age;
	private String bloodGroup;
	
	// patientRecordDetails
	private int doctorId;
	private String doctorName;
	private Date dateOfAdmit;
	private int roomNo;
	private double expenses;
	
	@Enumerated(EnumType.STRING)
	private AdmitStatus status;

}
