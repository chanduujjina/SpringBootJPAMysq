package com.practice.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name="skill_set")
@Data
public class SkillSet {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="emp_id")
	private Employee employee;
	
	

	@Column(name="skill_type")
	private String skillType;
	
	@Column(name="skill_name")
	private String skillName;
	
	@Column(name="relevent_exp")
	private int releventExperience;
	
}
