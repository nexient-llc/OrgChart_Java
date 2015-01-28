package com.systemsinmotion.orgchart.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;

public interface EmployeeRepository extends BaseRepository<Employee,Integer> {
	
	Employee findById(Integer id);
	
	List<Employee> findByFirstName(String firstName);
	
	List<Employee> findByLastName(String lastName);

	List<Employee> findByFirstNameAndLastNameAndDepartmentIdAndJobTitleId(String firstName, String lastName, Department department, JobTitle jobTitle);

	List<Employee> findByLastNameAndDepartmentIdAndJobTitleId(String firstName, Department department, JobTitle jobTitle);

	List<Employee> findByFirstNameAndDepartmentIdAndJobTitleId(String firstName, Department department, JobTitle jobTitle);
	
	List<Employee> findDistinctEmployeeByFirstNameOrLastNameAndDepartmentAndJobTitle(String firstName, String lastName, Department department, JobTitle jobTitle);

	List<Employee> findDistinctEmployeeByFirstNameOrLastNameAndDepartment(String firstName, String lastName, Department department);

	List<Employee> findDistinctEmployeeByFirstNameOrLastNameAndJobTitle(String firstName, String lastName, JobTitle jobTitle);

	List<Employee> findDistinctEmployeeByFirstNameStartingWithIgnoreCaseOrLastNameStartingWithIgnoreCase(String firstName, String lastName);

	List<Employee> findDistinctEmployeeByDepartmentAndJobTitle(Department department, JobTitle jobTitle);

	List<Employee> findDistinctEmployeeByJobTitle(JobTitle jobTitle);

	List<Employee> findDistinctEmployeeByDepartment(Department department);

	Employee findByEmail(String notPresentValue);

	List<Employee> findByManager(Employee manager);

	List<Employee> findByDepartment(Department department);

	List<Employee> findByFirstNameAndLastNameAndDepartmentIdAndJobTitleId(
			String firstName, String lastName, Integer id, Integer id2);
}