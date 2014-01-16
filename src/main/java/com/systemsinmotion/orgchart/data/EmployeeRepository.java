package com.systemsinmotion.orgchart.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	Employee findEmployeeByFirstName(String firstName);
	Employee findEmployeeByLastName(String lastName);
	Employee findEmployeeByEmail(String email);
	Employee findEmployeeBySkypeName(String skypeName);
	Employee findEmployeeById(Integer Id);
	
	List<Employee> findEmployeesByDepartment(Department department);
	List<Employee> findEmployeesByManagerId(Integer manager);
	
}
