package com.systemsinmotion.orgchart.data;

import java.util.List;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;


public interface EmployeeRepository extends BaseRepository<Employee, Integer> {

	List<Employee> findByDepartment(Department department);

	Employee findByEmail(String email);

	Employee findById(Integer id);

	List<Employee> findByManager(Employee manager);

	List<Employee> findByFirstNameAndLastNameAndDepartmentIdAndJobTitleId(
			String firstName, String lastName, Integer id, Integer id2);

	List<Employee> findByJobTitle(JobTitle jobTitle);

	List<Employee> findByDepartmentAndJobTitle(Department department,
			JobTitle jobTitle);

	List<Employee> findByDepartmentId(Integer id);

	List<Employee> findByDepartmentIdAndJobTitleId(Integer id, Integer id2);

	List<Employee> findByJobTitleId(Integer id);

	List<Employee> findByFirstName(String firstName);

	List<Employee> findByFirstNameOrLastName(String firstName, String lastName);

	List<Employee> findByFirstNameAndLastName(String firstName, String lastName);

	List<Employee> findByFirstNameStartingWithOrLastNameStartingWith(
			String firstName, String lastName);

	List<Employee> findByFirstNameAndLastNameStartingWith(String firstName,
			String lastName);
}
