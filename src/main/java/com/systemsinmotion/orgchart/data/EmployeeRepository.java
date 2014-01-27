package com.systemsinmotion.orgchart.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.systemsinmotion.orgchart.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	Employee findEmployeeById(Integer Id);
	/*
	Employee findEmployeeByFirstName(String firstName);
	Employee findEmployeeByLastName(String lastName);
	
	List<Employee> findEmployeesByDepartment(Department department);
	List<Employee> findEmployeesByManagerId(Integer manager);
	*/
	
	Employee findEmployeeByEmail(String email);
	Employee findEmployeeBySkypeName(String skypeName);
	
	List<Employee> findEmployeesByIsActiveTrue();
}
