package com.deepak.dtos;

import java.util.Date;
import java.util.List;

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
public class DoctorNameListDTO {

	private List<String> doctorNameList;
}
