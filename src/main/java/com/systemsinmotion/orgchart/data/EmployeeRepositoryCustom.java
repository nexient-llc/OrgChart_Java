package com.systemsinmotion.orgchart.data;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

import com.systemsinmotion.orgchart.entity.Employee;

@NoRepositoryBean
public interface EmployeeRepositoryCustom {
	List<Employee> findActiveByFirstNameOrLastName(String name);
	List<Employee> findActiveByFirstNameOrLastNameAndDepartmentId(String name, Integer deptId);
	List<Employee> findActiveByFirstNameOrLastNameAndJobTitleId(String name, Integer jobId);
	List<Employee> findActiveByFirstNameOrLastNameAndDepartmentIdAndJobTitleId(String name, Integer deptId, Integer jobId);
	List<Employee> findActiveByUnknownInputs(String firstName, String lastName, Integer deptId, Integer jobId);
}
