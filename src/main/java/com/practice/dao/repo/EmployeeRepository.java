package com.practice.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.practice.dao.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>,JpaSpecificationExecutor<Employee>{
	
	Employee findById(int id);
	
	
}
