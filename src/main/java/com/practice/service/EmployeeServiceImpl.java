package com.practice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.practice.Dao.EmployeeRepository;
import com.practice.domain.Employee;
@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	//@Autowired
	EmployeeRepository empRepository;

	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return empRepository.findAll();
	}

}
