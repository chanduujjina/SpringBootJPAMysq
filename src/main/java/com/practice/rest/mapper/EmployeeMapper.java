package com.practice.rest.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.practice.dao.model.AddressDetails;
import com.practice.dao.model.Employee;
import com.practice.dao.model.SkillSet;
import com.practice.dao.repo.DepartmentRepository;
import com.practice.dao.repo.EmployeeRepository;
import com.practice.dao.repo.EmployeeTypeRepository;
import com.practice.rest.dto.AddressDto;
import com.practice.rest.dto.EmployeeDto;
import com.practice.rest.dto.SkillSetDto;

@Component
public class EmployeeMapper {
	
	@Autowired
	EmployeeRepository repo;
	
	@Autowired
	EmployeeTypeRepository typeRepository;
	
	@Autowired
	DepartmentRepository deptRepository;
	
	public EmployeeDto convertToDTO(Employee emp) {
		EmployeeDto employeeDto=new EmployeeDto();
		employeeDto.setId(emp.getId());
		employeeDto.setName(emp.getName());
		employeeDto.setExperience(emp.getExperience());
		employeeDto.setSalary(emp.getSalary());
		employeeDto.setDepartmentName(emp.getDeptDetail().getDescription());
		employeeDto.setEmployeeType(emp.getEmpType().getDescription());
		List<AddressDto> addressLiDtos=buildaddressDeatils(emp.getAddressList());
		employeeDto.setAddressList(addressLiDtos);
		List<SkillSetDto> skillSetDtos=buildSkillSetDto(emp.getSkillSetList());
		employeeDto.setSkillSetList(skillSetDtos);
		return employeeDto;
	}

	private List<AddressDto>  buildaddressDeatils(List<AddressDetails> addressList) {
		List<AddressDto> addressLiDtos=new ArrayList<AddressDto>();
		addressList.forEach(add->{
			AddressDto addressDto=new AddressDto();
			addressDto.setAddress(add.getAddress());
			addressDto.setAddressType(add.getAddressType());
			addressLiDtos.add(addressDto);
		});
		return addressLiDtos;
	}
	
	private List<SkillSetDto> buildSkillSetDto(List<SkillSet> skiList){
		List<SkillSetDto> skillSetDtoList = new ArrayList<>();
		skiList.forEach(skill->{
			SkillSetDto skillSetDto = new SkillSetDto();
			skillSetDto.setSkillName(skill.getSkillName());
			skillSetDto.setSkillSetType(skill.getSkillType());
			skillSetDtoList.add(skillSetDto);
		});
		return skillSetDtoList;
	}
	
	
	public List<Employee> convertEmployee(List<EmployeeDto> employeeDtoList) {
		List<Employee> employeeList=new ArrayList<>();
		employeeDtoList.forEach(employeeDto->{
			Employee employee=new Employee();
			employee.setName(employeeDto.getName());
			employee.setId(employeeDto.getId());
			employee.setExperience(employeeDto.getExperience());
			employee.setSalary(employeeDto.getSalary());
			employee.setDeptDetail(deptRepository.findByDescription(employeeDto.getDepartmentName()));
			employee.setEmpType(typeRepository.findByDescription(employeeDto.getEmployeeType()));
			employee.setAddressList(buildaddressdtoDeatils(employeeDto.getAddressList(),employee));
			employee.setSkillSetList(buildSkillSet(employeeDto.getSkillSetList(),employee));
			employeeList.add(employee);
		});
		
		return employeeList;
	}
	
	private List<AddressDetails>  buildaddressdtoDeatils(List<AddressDto> addressDtoList, Employee employee) {
		 List<AddressDetails> addressDetailsList=new ArrayList<AddressDetails>();
		 addressDtoList.forEach(addDto->{
			 AddressDetails addressDetails=new AddressDetails();
			 addressDetails.setAddress(addDto.getAddress());
			 addressDetails.setAddressType(addDto.getAddressType());
			 addressDetails.setEmployee(employee);
			 addressDetailsList.add(addressDetails);
		});
		return addressDetailsList;
	}
	
	private List<SkillSet> buildSkillSet(List<SkillSetDto> skillSetDtoList, Employee employee){
		List<SkillSet> skillSetList = new ArrayList<>();
		skillSetDtoList.forEach(skillDto->{
			SkillSet skillSet = new SkillSet();
			skillSet.setEmployee(employee);
			skillSet.setSkillName(skillDto.getSkillName());
			skillSet.setSkillType(skillDto.getSkillSetType());
			skillSet.setReleventExperience(skillDto.getReleventExperience());
			skillSetList.add(skillSet);
		});
		return skillSetList;
	}

}
