package com.systemsinmotion.orgchart.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	List<Employee> findByDepartment(Department department);
	
	List<Employee> findByDepartmentId(Integer id);

	Employee findByEmail(String email);

	Employee findById(Integer id);

	List<Employee> findByManager(Employee manager);

	Employee findByFirstName(String firstName);

	Employee findByLastName(String lastName);
	
	Employee findByFirstNameAndLastName(String firstName, String lastName);
	
	List<Employee> findByJobTitle(JobTitle jobTitle);
	
	List<Employee> findByJobTitleId(Integer id);

	List<Employee> findByIsActiveTrue();

//	List<Employee> findByFirstNameContainingOrLastNameContaining(String name);
	
}
