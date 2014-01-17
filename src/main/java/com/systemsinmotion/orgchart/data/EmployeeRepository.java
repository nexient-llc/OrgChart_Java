package com.systemsinmotion.orgchart.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

	List<Employee> findByDepartment(Department department);
	List<Employee> findByDepartmentId(Integer departmentId);

	Employee findByEmail(String email);

	Employee findById(Integer id);

//	void update(Employee employee);

	List<Employee> findByManager(Employee employee);

	List<Employee> findByManagerId(Integer id);

	Employee findByFirstNameAndLastName(String firstName, String lastName);

	List<Employee> findByJobTitle(JobTitle jobTitle);
	
	List<Employee> findByJobTitleId(Integer jobTitleId);
	
	List<Employee> findByFirstNameStartingWithIgnoreCaseOrLastNameStartingWithIgnoreCaseOrderByFirstNameDesc(String firstName, String lastName);
}
