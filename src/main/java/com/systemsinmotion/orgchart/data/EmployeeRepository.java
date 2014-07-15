package com.systemsinmotion.orgchart.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;


public interface EmployeeRepository extends JpaRepository<Employee,Integer>{

	public List<Employee> findByFirstName(String name);
	
	public List <Employee> findByDepartment(Department depart);
	
	public List <Employee> findByJobTitleName(String title);
	
	public List <Employee> findByDepartmentName(String name);
	
	public List <Employee> findByDepartmentNameAndJobTitleName(String departName,String jobTitle);
	
	public List <Employee> findByFirstNameAndLastName(String firstName, String LastName);
	
	public Employee findByEmail(String email);
	
	public Employee findById(Integer id);
	
	public List<Employee> findByManager(Employee manager);
	 
	public List<Employee> findByFirstNameAndLastNameAndDepartmentIdAndJobTitleId(
			String firstName, String lastName, Integer id, Integer id2);
}
