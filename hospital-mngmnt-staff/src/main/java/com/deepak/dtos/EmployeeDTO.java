package com.deepak.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
public class EmployeeDTO {
	
	  // users data
		private String firstName;
		private String lastName;
		private String email;
		@JsonProperty(access = Access.WRITE_ONLY)
		private String password;
		private String role;
		private String phoneNumber;
		//to generate the jwt token
		private String token;
		
		// appending Employee data (in future add more like employee)
		private Date dob;
		private Date joiningDate;
}
