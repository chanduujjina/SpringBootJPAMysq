package com.practice.dto;

import java.util.List;

import lombok.Data;
@Data
public class EmployeeDto {
	
	

	private int id;
	private String name;
	private String employeeType;
	private String departmentName;
	private List<AddressDto> addressList;
	
	private List<SkillSetDto> skillSetList;
	private int salary;
	private int experience;


}
