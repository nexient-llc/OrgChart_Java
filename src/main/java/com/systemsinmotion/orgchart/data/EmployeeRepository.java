package com.systemsinmotion.orgchart.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	Employee findByFirstName(String firstName);
	Employee findByLastName(String lastName);
	Employee findByEmail(String email);
	Employee findBySkypeName(String skypeName);
	Employee findById(Integer Id);
	
	List<Employee> findByDepartment(Department department);
	List<Employee> findByManagerId(Integer manager);
	
}
