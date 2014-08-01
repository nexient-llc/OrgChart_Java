package com.systemsinmotion.orgchart.data;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	void delete(Employee emp);

	//List<Employee> findByDepartment(Department dept);

	Employee findByEmailIgnoreCase(String email);
	
	Employee findBySkypeNameIgnoreCase(String skype);

	Employee findById(Integer id);

	List<Employee> findByManager(Employee manager);

	List<Employee> findByFirstNameAndLastNameAndDepartmentIdAndJobTitleId(String firstName, String lastName, Integer deptId, Integer jobTitleId);
	
	// stuff I put here for user stories:	
	List<Employee> findAllByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(String name1, String name2);

	List<Employee> findAllByDepartmentIdAndIsActiveIsTrue(Integer deptId);

	List<Employee> findAllByJobTitleIdAndIsActiveIsTrue(Integer jobId);

	List<Employee> findAllByDepartmentIdAndJobTitleIdAndIsActiveIsTrue(Integer deptId, Integer jobTitleId);

	List<Employee> findAllByFirstNameContainingIgnoreCaseAndIsActiveIsTrue(String firstName);

	List<Employee> findAllByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndIsActiveIsTrue(String firstName, String lastName);

	List<Employee> findAllByFirstNameIgnoreCaseAndDepartmentIdAndIsActiveIsTrue(String firstName, Integer deptId);

	List<Employee> findAllByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndDepartmentIdAndIsActiveIsTrue(String firstName, String lastName, Integer deptId);

	List<Employee> findAllByFirstNameIgnoreCaseAndJobTitleIdAndIsActiveIsTrue(String firstName, Integer jobId);

	List<Employee> findAllByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndJobTitleIdAndIsActiveIsTrue(String firstName, String lastName, Integer jobId);

	List<Employee> findAllByIsActiveIsTrue(Pageable pageable);
	
	List<Employee> findAllByIsActiveIsTrue();
	
	List<Employee> findAllByIsActiveIsFalse();

	List<Employee> findAllByFirstNameIgnoreCaseAndDepartmentIdAndJobTitleIdAndIsActiveIsTrue(String firstName, Integer deptId, Integer jobId);

	List<Employee> findAllByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndDepartmentIdAndJobTitleIdAndIsActiveIsTrue(String firstName, String lastName, Integer deptId, Integer jobId);

	@Modifying
	@Transactional
	@Query("update Employee emp set emp.isActive = false where emp.id = :id")
	void removeEmployee(@Param("id") Integer id);

	@Modifying
	@Transactional
	@Query("update Employee emp set emp.isActive = true where emp.id = :id")
	void reenableEmployee(@Param("id") Integer id);
	
	List<Employee> findByFirstNameContainingIgnoreCase(String firstName);

	List<Employee> findAllByEmailIgnoreCaseOrSkypeNameIgnoreCase(String email, String skype);
	
	//List<Employee> findByFirstNameContainingIgnoreCase(String firstName);

	//List<Employee> findByFirstNameAndLastNameContainingIgnoreCase(String firstName, String lastName);
	
	//List<Employee> findByFirstNameAndMiddleInitialAndLastNameContainingIgnoreCase(String firstName, Character middleInitial, String lastName);
	
	//List<Employee> findAllByFirstNameIgnoreCaseAndMiddleInitialAndLastNameIgnoreCase(String firstName, Character mid, String lastName);
}
