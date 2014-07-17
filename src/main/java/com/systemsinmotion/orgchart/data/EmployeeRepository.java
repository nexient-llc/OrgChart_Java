package com.systemsinmotion.orgchart.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;

public interface EmployeeRepository  extends JpaRepository<Employee,Integer>, EmployeeRepositoryCustom {

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
	
	List<Employee> findByDepartmentIdAndIsActiveIsTrue(Integer deptId);
	
	@Query("SELECT e FROM Employee e WHERE (upper(e.firstName) like %:name% OR upper(e.lastName) like %:name%) "
			+ "AND e.department.id = :deptId AND e.isActive is true")
	List<Employee> findByUpperCaseNameAndDepartmentIdAndActive(@Param("name") String name,
			@Param("deptId") Integer deptId);
	
	List<Employee> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndDepartmentIdAndIsActiveIsTrue(
			String firstName, String lastName, Integer deptId);
	
	List<Employee> findByJobTitleIdAndIsActiveIsTrue(Integer jobId);
	
	@Query("SELECT e FROM Employee e WHERE (upper(e.firstName) like %:name% OR upper(e.lastName) like %:name%) "
			+ "AND e.jobTitle.id = :jobId AND e.isActive is true")
	List<Employee> findByUpperCaseNameAndJobTitleAndActive(@Param("name") String name,
			@Param("jobId")  Integer jobId);
	
	List<Employee> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndJobTitleIdAndIsActiveIsTrue(
			String firstName, String lastName, Integer jobId);
	
	@Query("SELECT e FROM Employee e WHERE (upper(e.firstName) like %:name% OR upper(e.lastName) like %:name%) "
			+ "AND e.department.id = :deptId AND e.jobTitle.id = :jobId AND e.isActive is true")
	List<Employee> findByUpperCaseNameAndDepartmentAndJobTitleAndActive(@Param ("name") String name,
			@Param("deptId") Integer deptId, @Param("jobId") Integer jobId);
	
	List<Employee> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndDepartmentIdAndJobTitleIdAndIsActiveIsTrue(
			String firstName, String lastName, Integer deptId,
			Integer jobId);
	
	List<Employee> findByDepartmentIdAndJobTitleIdAndIsActiveIsTrue(
			Integer deptId, Integer jobId);
	
}
