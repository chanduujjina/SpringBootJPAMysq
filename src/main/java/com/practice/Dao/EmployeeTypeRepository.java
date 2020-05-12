package com.practice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeTypeRepository extends JpaRepository<EmployeeType, Long>{
	EmployeeType findByDescription(String desc);
}
