package com.systemsinmotion.orgchart.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
	
	List<Employee> findDistinctEmployeeByDepartmentAndFirstNameOrDepartmentAndLastName(Department department, String firstName, Department department2, String lastName);

	List<Employee> findDistinctEmployeeByFirstNameStartingWithIgnoreCaseOrLastNameStartingWithIgnoreCase(String firstName, String lastName);

	List<Employee> findDistinctEmployeeByDepartmentAndJobTitle(Department department, JobTitle jobTitle);

	List<Employee> findDistinctEmployeeByJobTitle(JobTitle jobTitle);

	List<Employee> findDistinctEmployeeByDepartment(Department department);

	Employee findByEmail(String notPresentValue);

	List<Employee> findByManager(Employee manager);

	List<Employee> findByDepartment(Department department);

	List<Employee> findByFirstNameAndLastNameAndDepartmentIdAndJobTitleId(String firstName, String lastName, Integer id, Integer id2);

	List<Employee> findDistinctEmployeeByFirstNameAndLastName(String firstName,String lastName);

	List<Employee> findDistinctEmployeeByFirstNameAndLastNameAndJobTitle(String firstName, String lastName, JobTitle jobTitle);

	List<Employee> findDistinctEmployeeByFirstNameAndLastNameAndDepartment(String firstName, String lastName, Department department);

	List<Employee> findDistinctEmployeeByFirstNameAndLastNameAndDepartmentAndJobTitle(String firstName, String lastName, Department department,JobTitle jobTitle);

	List<Employee> findDistinctEmployeeByJobTitleAndFirstNameOrLastName(String lastName, String firstName, JobTitle jobTitle);

	@Query ("from Employee e where e.department.id = :deptId and e.jobTitle.id = :titleId and (e.firstName = :firstName or e.lastName = :lastName)")
	List<Employee> findByAllPartialName(@Param("deptId") Integer deptId, @Param("titleId") Integer titleId, @Param("firstName") String firstName, @Param("lastName") String lastName);
	
}