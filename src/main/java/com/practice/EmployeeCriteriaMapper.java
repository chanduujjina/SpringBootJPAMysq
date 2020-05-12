package com.practice;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.util.MultiValueMap;

import com.practice.dto.EmployeeCriteria;



@Mapper(componentModel = "spring")
public interface EmployeeCriteriaMapper {
	@Mapping(target = "names",
	 expression = "java(!org.springframework.util.StringUtils.isEmpty( queryParamMap.getFirst(\"name\")) ? "
	 		+ "new java.util.HashSet<String>(java.util.Arrays.asList(queryParamMap.getFirst(\"name\").split(\",\"))): null)")
	@Mapping(target = "employeeTypes",
	 expression = "java(!org.springframework.util.StringUtils.isEmpty( queryParamMap.getFirst(\"employeeType\")) ? "
	 		+ "new java.util.HashSet<String>(java.util.Arrays.asList(queryParamMap.getFirst(\"employeeType\").split(\",\"))): null)")
	@Mapping(target = "departmentNames",
	 expression = "java(!org.springframework.util.StringUtils.isEmpty( queryParamMap.getFirst(\"departmentName\")) ? "
	 		+ "new java.util.HashSet<String>(java.util.Arrays.asList(queryParamMap.getFirst(\"departmentName\").split(\",\"))): null)")
	
	@Mapping(target = "salary",
	expression = "java(!org.springframework.util.StringUtils.isEmpty( queryParamMap.getFirst(\"salary\")) ? "
			+ "java.lang.Integer.parseInt(queryParamMap.getFirst(\"salary\")): 0 )")
	
	@Mapping(target = "experience",
	expression = "java( !org.springframework.util.StringUtils.isEmpty( queryParamMap.getFirst(\"experience\")) ? "
			+ "java.lang.Integer.parseInt(queryParamMap.getFirst(\"experience\")): 0 )")
	EmployeeCriteria convertToEmployeeCriteria(MultiValueMap<String, String> queryParamMap);

}
