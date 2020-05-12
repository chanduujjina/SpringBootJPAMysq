package com.practice.dao.spec;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;


import com.practice.dao.Employee;
import com.practice.dao.util.Criteria;
import com.practice.dao.util.CriteriaBuilder;
import com.practice.dao.util.Operator;
import com.practice.dto.EmployeeCriteria;



@Component
public class EmployeeSpecification {
	
	 public Specification<Employee> searchCriteriaIn(EmployeeCriteria employeeCriteria) {
		 
		 List<Criteria> criteriaList= new CriteriaBuilder().addCriteria(getNameCriteria(employeeCriteria.getNames())).
				 addCriteria(getEmployeeTypeCriteria(employeeCriteria.getEmployeeTypes())).
				 addCriteria(getDeptDetailCriteria(employeeCriteria.getDepartmentNames())).
				 addCriteria(getExperienceCriteria(employeeCriteria.getExperience()))
				 .build();
		 return BaseSpecification.consolidateSpecifications(criteriaList);
	 
	 }
	 
	 private Criteria getDeptDetailCriteria(Set<String> deptDetails) {
		 if (!CollectionUtils.isEmpty(deptDetails)) {
	        	return new Criteria().setOperator(Operator.NESTED_IN).setParentKey("deptDetail")
                     .setKey("description").setValues(deptDetails);
	        }
	        return null; 
	 }
	 
	 private Criteria getNameCriteria(Set<String> nameSet) {
	        if (!CollectionUtils.isEmpty(nameSet)) {
	            return new Criteria().setOperator(Operator.IN).setKey("name").setValues(nameSet);
	        }
	        return null;
	    }
	 
	 private Criteria getEmployeeTypeCriteria(Set<String> employeeTypeSet) {
	        if (!CollectionUtils.isEmpty(employeeTypeSet)) {
	        	return new Criteria().setOperator(Operator.NESTED_IN).setParentKey("empType")
                        .setKey("description").setValues(employeeTypeSet);
	        }
	        return null;
	    }
	 
	 private Criteria getExperienceCriteria(int value) {
	        if (value!=0) {
	            return new Criteria().setOperator(Operator.EQ).setKey("experience").setValue(value);
	        }
	        return null;
	    }

}
