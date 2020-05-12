package com.practice.dao;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="employee")
@Data
public class Employee {
	
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private int salary;
	
	private int experience;
	
	@ManyToOne(cascade=CascadeType.DETACH)
	@JoinColumn(name="DEPT_ID",referencedColumnName="ID")
	private Department deptDetail;
	
	@ManyToOne(cascade=CascadeType.DETACH)
	@JoinColumn(name="TYPE_ID",referencedColumnName="ID")
	private EmployeeType empType;
	
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy = "employee",targetEntity=AddressDetails.class)
	private List<AddressDetails> addressList;  
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy = "employee",targetEntity=SkillSet.class)
	private List<SkillSet> SkillSetList;
	
	
	

	

}
