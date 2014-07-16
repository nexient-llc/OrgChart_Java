package com.systemsinmotion.orgchart.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	void delete(Employee emp);

	List<Employee> findByDepartment(Department dept);

	Employee findByEmail(String email);

	Employee findById(Integer id);

	List<Employee> findByManager(Employee manager);

	List<Employee> findByFirstNameAndLastNameAndDepartmentIdAndJobTitleId(String firstName, String lastName, Integer deptId, Integer jobTitleId);
	
	// stuff I put here for user stories:	
	List<Employee> findAllByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String name1, String name2);

	List<Employee> findAllByDepartmentId(Integer deptId);

	List<Employee> findAllByJobTitleId(Integer jobId);

	List<Employee> findAllByDepartmentIdAndJobTitleId(Integer deptId,Integer jobTitleId);

	List<Employee> findAllByFirstNameContainingIgnoreCase(String firstName);

	List<Employee> findAllByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(String firstName, String lastName);

	List<Employee> findAllByFirstNameIgnoreCaseAndDepartmentId(String firstName, Integer deptId);

	List<Employee> findAllByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndDepartmentId(String firstName, String lastName, Integer deptId);

	List<Employee> findAllByFirstNameIgnoreCaseAndJobTitleId(String firstName,Integer jobId);

	List<Employee> findAllByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndJobTitleId(String firstName, String lastName, Integer jobId);

	List<Employee> findAllByIsActiveIsTrue();

	List<Employee> findAllByFirstNameIgnoreCaseAndDepartmentIdAndJobTitleId(String firstName, Integer deptId, Integer jobId);

	List<Employee> findAllByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndDepartmentIdAndJobTitleId(String firstName, String lastName, Integer deptId, Integer jobId);

	@Modifying
	@Transactional
	@Query("update Employee emp set emp.isActive = false where emp.id = :id")
	void removeEmployee(@Param("id") Integer id);
	
	
	//List<Employee> findByFirstNameContainingIgnoreCase(String firstName);

	//List<Employee> findByFirstNameAndLastNameContainingIgnoreCase(String firstName, String lastName);
	
	//List<Employee> findByFirstNameAndMiddleInitialAndLastNameContainingIgnoreCase(String firstName, Character middleInitial, String lastName);
	
	//List<Employee> findAllByFirstNameIgnoreCaseAndMiddleInitialAndLastNameIgnoreCase(String firstName, Character mid, String lastName);
}
