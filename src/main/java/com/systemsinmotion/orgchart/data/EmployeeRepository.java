package com.systemsinmotion.orgchart.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;


public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

	@Query("from Employee where firstName in ?1")
	List<Employee> findByFirstNameIn(String... firstName);
	
	
	List<Employee> findByIsActiveTrue();
	List<Employee> findByDepartment(Department department);
	List<Employee> findByDepartmentId(Integer id);
	Employee findByEmail(String email);
	Employee findById(Integer id);
	List<Employee> findByManagerId(Integer id);
	List<Employee> findByFirstNameOrLastNameAllIgnoreCase(String firstName, String lastName);
	
	List<Employee> findByFirstNameStartingWithOrLastNameStartingWithAllIgnoreCaseOrderByFirstNameAsc(String firstName, String lastName);
	
	List<Employee> findByJobTitle(JobTitle jobTitle);
	List<Employee> findByJobTitleId(Integer id);

	List<Employee> findByJobTitleAndDepartment(JobTitle jobTitle, Department department);
	
	@Query("from Employee e where e.department = :department and (upper(e.firstName) = upper(:firstname) or upper(e.lastName) = upper(:lastname)) order by e.firstName asc")
	List<Employee> findByDepartmentAndFirstNameOrLastName(@Param("department") Department department, @Param("firstname") String firstName,
			@Param("lastname") String lastName);
	
	@Query("from Employee e where e.jobTitle = :jobTitle and (upper(e.firstName) = upper(:firstname) or upper(e.lastName) = upper(:lastname)) order by e.firstName asc")
	List<Employee> findByJobTitleAndFirstNameOrLastName(@Param("jobTitle") JobTitle jobTitle, @Param("firstname") String firstName,
			@Param("lastname") String lastName);
	
	@Query("from Employee e where e.department = :department and e.jobTitle = :jobTitle and (upper(e.firstName) = upper(:firstname) or upper(e.lastName) = upper(:lastname)) order by e.firstName asc")
	List<Employee> findByDepartmentAndJobTitleAndFirstNameOrLastName(@Param("department") Department department, @Param("jobTitle") JobTitle jobTitle, @Param("firstname") String firstName,
			@Param("lastname") String lastName);	
}
