package com.practice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.rest.dto.EmployeeDto;
import com.practice.service.EmployeeService;



@RestController
@RequestMapping("/employeeDetails")
public class EmployeeResource {
	
	@Autowired
	EmployeeService service;
	@GetMapping("/{id}")
	public ResponseEntity getEmployee(@PathVariable("id") int id){
		EmployeeDto emp= service.getEmployeeById(id);
		System.out.println(emp);
		return new ResponseEntity<>(emp, HttpStatus.OK);
	} 
	
	@RequestMapping(value="/empByAtt", produces=MediaType.APPLICATION_JSON_VALUE, method=RequestMethod.GET)
	public ResponseEntity getEmployeeByAttribute(@RequestParam MultiValueMap<String, String> allParams) {
		List<EmployeeDto> dtoList=service.getEmployeeByAttributes(allParams);
		return new ResponseEntity<>(dtoList, HttpStatus.OK);
		
	}
	
	
	@RequestMapping(method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity saveEmployee(@RequestBody List<EmployeeDto> dtoList) {
		service.save(dtoList);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
}
