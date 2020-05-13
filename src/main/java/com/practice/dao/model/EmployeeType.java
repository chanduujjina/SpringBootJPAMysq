package com.practice.dao.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="EMPLOYEE_TYPE")
@Data
public class EmployeeType {
	


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String description;
	
	@OneToMany(mappedBy="empType")
	private List<Employee> employeeList;

}
