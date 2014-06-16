package com.systemsinmotion.orgchart.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

	List<Employee> findByDepartment(Department department);
	Employee findByEmail(String email);
	Employee findById(Integer id);
	List<Employee> findByManager(Employee employee);
}
