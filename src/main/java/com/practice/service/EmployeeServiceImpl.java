package com.practice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.practice.EmployeeCriteriaMapper;
import com.practice.dao.Employee;
import com.practice.dao.EmployeeRepository;
import com.practice.dao.spec.EmployeeSpecification;
import com.practice.dto.EmployeeCriteria;
import com.practice.dto.EmployeeDto;
import com.practice.dto.EmployeeMapper;

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
