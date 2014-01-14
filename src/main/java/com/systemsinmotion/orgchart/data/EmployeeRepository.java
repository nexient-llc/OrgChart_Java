package com.systemsinmotion.orgchart.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	List<Employee> findByDepartment(Department department);

	Employee findByEmail(String email);

	Employee findById(Integer id);

	Employee findByFirstName(String name);

	Employee findByLastName(String name);

	List<Employee> findByManagerId(Integer managerId);

	List<Employee> findByManager(Employee manager);

	List<Employee> findByIsManager(boolean isManager);

}
