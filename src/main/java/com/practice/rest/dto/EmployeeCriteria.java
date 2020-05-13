package com.practice.rest.dto;

import java.util.Set;

import lombok.Data;

@Data
public class EmployeeCriteria {
	
	private Set<String> names;
	private Set<String> employeeTypes;
	private Set<String> departmentNames;
	private int salary;
	private int experience;

}
