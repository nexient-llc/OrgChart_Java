package com.systemsinmotion.orgchart.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;

public interface EmployeeRepository  extends JpaRepository<Employee,Integer> {

//	Employee findByFirstName(String name);

	List<Employee> findByDepartment(Department department);
	List<Employee> findByJobTitle(JobTitle jobtitle);

	Employee findByEmail(String email);

	Employee findById(Integer id);

	List<Employee> findByManager(Employee manager);

	List<Employee> findByFirstNameAndLastNameAndDepartmentIdAndJobTitleId(
			String firstName, String lastName, Integer deptId, Integer jobId);
	List<Employee> findByFirstNameContainingIgnoreCase(String firstName);
	List<Employee> findByLastNameContainingIgnoreCase(String lastName);
	
	List<Employee> findByIsActiveIsTrue();
}
