package com.practice.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.domain.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	List<Employee> findAll();
	Employee findById(int id);
	

}
