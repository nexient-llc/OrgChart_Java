package com.systemsinmotion.orgchart.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	List<Employee> findByDepartment(Department department);
	
	List<Employee> findByDepartmentId(Integer departmentId);

	Employee findByEmail(String email);

	Employee findById(Integer id);

	List<Employee> findByManagerId(Integer id);
	
	List<Employee> findByManager(Employee manager);

	Employee findByFirstNameAndLastName(String firstName, String lastName);

	Employee findByFirstName(String firstName);

	List<Employee> findByJobTitleId(Integer jobTitleId);

	List<Employee> findByIsActiveTrue();
	
	List<Employee> findByFirstNameContainsOrLastNameContainsAllIgnoreCase(String firstName, String lastName);
	
}
