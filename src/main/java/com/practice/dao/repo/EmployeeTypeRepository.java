package com.practice.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.dao.model.EmployeeType;


@Repository
public interface EmployeeTypeRepository extends JpaRepository<EmployeeType, Long>{
	EmployeeType findByDescription(String desc);
}
