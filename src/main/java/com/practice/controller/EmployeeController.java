package com.practice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.practice.Dao.EmployeeRepository;
import com.practice.domain.Employee;
import com.practice.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "EmployeeManage")
@RestController
@RequestMapping("/empDetails")
public class EmployeeController {
	@Autowired
	EmployeeRepository empRepository;
	
	@ApiOperation(
            value = "get all employee deratils",
            notes = "get all employee details in reponse body"
    )
	@GetMapping("/employes")
	public ResponseEntity getEmployeeDetails(){
		List<Employee> emp= empRepository.findAll();
		System.out.println(emp.size());
		return new ResponseEntity<>(emp, HttpStatus.OK);
	}
	@ApiOperation(
            value = "Get employee by id",
            notes = "Get the particular employeee by the given employee id"
    )
	@GetMapping("/{id}")
	public ResponseEntity getEmployee(@PathVariable("id") int id){
		Employee emp= empRepository.findById(id);
		System.out.println(emp);
		return new ResponseEntity<>(emp, HttpStatus.OK);
	} 
	
	@ApiOperation(
            value = "Create a new Employee",
            notes = "Create a new Employee using the provided JSON in the request body"
    )
	@RequestMapping(method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity saveEmployee(@RequestBody List<Employee> empList) {
		List<Employee> returnList=empRepository.saveAll(empList);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@ApiOperation(
            value = "Update the exsisting Employee by the given id",
            notes = "Update the exsisting Employee using the provided JSON in the request body"
    )
	@RequestMapping(value="/{id}",method=RequestMethod.PUT,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity updateEmployee(@RequestBody Employee emp,@PathVariable("id") Integer id) {
		Employee updatedEmp= empRepository.findById(id);
		ResponseEntity entity=null;
		if(null!=updatedEmp) {
			empRepository.save(emp);
			entity= new ResponseEntity<>(HttpStatus.ACCEPTED);
		}
		else {
			entity= new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return entity;
	}
	
	

}
