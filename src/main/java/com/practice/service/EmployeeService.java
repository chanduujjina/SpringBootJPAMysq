package com.practice.service;

import java.util.List;

import org.springframework.util.MultiValueMap;

import com.practice.dao.Employee;
import com.practice.dto.EmployeeDto;


public interface EmployeeService {
	
	EmployeeDto getEmployeeById(int id);
	
	void save(List<EmployeeDto> dtoList);
	
	List<EmployeeDto> getEmployeeByAttributes(MultiValueMap<String, String> allParams);

}
