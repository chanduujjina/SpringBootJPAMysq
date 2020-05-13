package com.practice.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.dao.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{
	Department findByDescription(String desc);
}
