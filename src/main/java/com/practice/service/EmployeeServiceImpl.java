package com.practice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.practice.dao.model.Employee;
import com.practice.dao.repo.EmployeeRepository;
import com.practice.dao.specification.EmployeeSpecification;
import com.practice.rest.dto.EmployeeCriteria;
import com.practice.rest.dto.EmployeeDto;
import com.practice.rest.mapper.EmployeeCriteriaMapper;
import com.practice.rest.mapper.EmployeeMapper;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	EmployeeRepository repo;
	
	@Autowired
	EmployeeMapper mapper;
	
	@Autowired
	 EmployeeCriteriaMapper criteriaMapper ;
	
	@Autowired
	EmployeeSpecification specification;
	
	@Override
	public EmployeeDto getEmployeeById(int id) {
		
		EmployeeDto empdto= mapper.convertToDTO(repo.findById(id));
		return empdto;
	}

	@Override
	public void save(List<EmployeeDto> dtoList) {
		
		List<Employee>  employeeList= repo.saveAll(mapper.convertEmployee(dtoList));
	}

	@Override
	public List<EmployeeDto> getEmployeeByAttributes(MultiValueMap<String, String> allParams) {
		EmployeeCriteria employeeCriteria= criteriaMapper.convertToEmployeeCriteria(allParams);
		List<Employee> empList= repo.findAll(specification.searchCriteriaIn(employeeCriteria));
		List<EmployeeDto> dtoList = new ArrayList<EmployeeDto>();
		empList.forEach(emp->{
			dtoList.add(mapper.convertToDTO(emp));
		});
		return dtoList;
	}

}
