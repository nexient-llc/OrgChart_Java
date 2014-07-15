package com.systemsinmotion.orgchart.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
	List<Employee> findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(
			String firstName, String lastName);
	List<Employee> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndIsActiveIsTrue(
			String firstName, String lastName);
	List<Employee> findByDepartmentAndIsActiveIsTrue(Department department);
	
	@Query("SELECT e FROM Employee e WHERE (upper(e.firstName) like %:name% OR upper(e.lastName) like %:name%) "
			+ "AND e.department = :department AND e.isActive is true")
	List<Employee> findByUpperCaseNameAndDepartmentAndActive(@Param("name") String name,
			@Param("department") Department department);
	List<Employee> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndDepartmentAndIsActiveIsTrue(
			String firstName, String lastName, Department department);
	List<Employee> findByJobTitleAndIsActiveIsTrue(JobTitle jobTitle);
	@Query("SELECT e FROM Employee e WHERE (upper(e.firstName) like %:name% OR upper(e.lastName) like %:name%) "
			+ "AND e.jobTitle = :jobtitle AND e.isActive is true")
	List<Employee> findByNameAndJobTitleAndActive(@Param("name") String name,
			@Param("jobtitle")  JobTitle jobTitle);
	List<Employee> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndJobTitleAndIsActiveIsTrue(
			String firstName, String lastName, JobTitle jobTitle);
	@Query("SELECT e FROM Employee e WHERE (upper(e.firstName) like %:name% OR upper(e.lastName) like %:name%) "
			+ "AND e.department = :department AND e.jobTitle = :jobtitle AND e.isActive is true")
	List<Employee> findByUpperCaseNameAndDepartmentAndJobTitle(@Param ("name") String name,
			@Param("department") Department department, @Param("jobtitle") JobTitle jobTitle);
	List<Employee> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndDepartmentAndJobTitleAndIsActiveIsTrue(
			String firstName, String lastName, Department department,
			JobTitle jobTitle);
	List<Employee> findByDepartmentAndJobTitleAndIsActiveIsTrue(
			Department department, JobTitle jobTitle);
}
