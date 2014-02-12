package com.systemsinmotion.orgchart.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
	Employee findByEmail(String email);
	List<Employee> findByManager(Employee manager);
	List<Employee> findByDepartment(Department department);
	Employee findById(Integer id);
	List<Employee> findEmployeesByIsActiveTrue();
	List<Employee> findEmployeesByIsActiveFalse();
	//void update(Employee employee);
}
 