package com.systemsinmotion.orgchart.data;

import java.util.List;

import org.mockito.stubbing.OngoingStubbing;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;

public interface EmployeeRepository extends BaseRepository<Employee,Integer>{

	List<Employee> findByDepartment(Department dept);

	Employee findByEmail(String email);

	Employee findById(Integer id);

	List<Employee> findByManager(Employee manager);

	List<Employee> findByFirstNameAndLastNameAndDepartmentIdAndJobTitleId(
			String firstName, String lastName, Integer id, Integer id2);
	
	List<Employee> findByIsManager(boolean isManager);
	
}
