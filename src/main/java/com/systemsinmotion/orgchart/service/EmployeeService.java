package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.data.EmployeeRepository;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;

@Service("employeeService")
public class EmployeeService {
	
	@Autowired
	EmployeeRepository repository;
	

	public List<Employee> findAllEmployees() {
		return this.repository.findAllByIsActiveIsTrue();
	}

	
	public Employee findEmployeeByID(Integer employeeId) {
		return this.repository.findOne(employeeId);
	}
	
	
	public Employee findByEmail(String email) {
		return this.repository.findByEmail(email);
	}

	
	public Employee storeEmployee(Employee employee) {
		return this.repository.save(employee);
	}
	
	
	public List<Employee> findAllEmployeesByDepartment(Department dept) {
		return this.repository.findByDepartment(dept);
	}
	

	public void removeEmployee(Integer id) {
		this.repository.removeEmployee(id);
	}

	
	public List<Employee> getEmployeeSuggestions(String name) {
		String nameArr[] = name.split(" ");
		List<Employee> employees = null;
		
		if(nameArr.length == 1) {
			employees = this.repository.findAllByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(nameArr[0], nameArr[0]);
		}
		else if(nameArr.length == 2) {
			employees = this.repository.findAllByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(nameArr[0], nameArr[1]);
		}
		
		if(employees == null || employees.size() == 0) {
			employees = null;
		}
		
		return employees;
	}
	
	
	public List<Employee> findAllEmployeesByCriteria(String name, String deptId, String jobId) {
		List<Employee> employees = null;
		
		if(name.equals("") && deptId.equals("") && jobId.equals("")) {
			employees = this.repository.findAllByIsActiveIsTrue();
		}
		else if(name.equals("") && !deptId.equals("") && jobId.equals("")) {
			employees = this.repository.findAllByDepartmentId(Integer.parseInt(deptId));
		}
		else if(name.equals("") && deptId.equals("") && !jobId.equals("")) {
			employees = this.repository.findAllByJobTitleId(Integer.parseInt(jobId));
		}
		else if(name.equals("") && !deptId.equals("") && !jobId.equals("")) {
			employees = this.repository.findAllByDepartmentIdAndJobTitleId(Integer.parseInt(deptId), Integer.parseInt(jobId));
		}
		else if(!name.equals("") && deptId.equals("") && jobId.equals("")) {
			employees = nameNoDeptNoJob(name);
		}
		else if(!name.equals("") && !deptId.equals("") && jobId.equals("")) {
			employees = nameDeptNoJob(name, Integer.parseInt(deptId));
		}
		else if(!name.equals("") && deptId.equals("") && !jobId.equals("")) {
			employees = nameNoDeptJob(name, Integer.parseInt(jobId));
		}
		else if(!name.equals("") && !deptId.equals("") && !jobId.equals("")) {
			employees = nameDeptJob(name, Integer.parseInt(deptId), Integer.parseInt(jobId));
		}
		
		return employees;
	}
	
	
	private List<Employee> nameNoDeptNoJob(String name) {
		List<Employee> employees = null;
		String nameArr[] = name.split(" ");
		
		if(nameArr.length == 1) {
			employees = this.repository.findAllByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(nameArr[0], nameArr[0]);
		}
		else if(nameArr.length == 2) {
			employees = this.repository.findAllByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(nameArr[0], nameArr[1]);
		}
		
		return employees;
	}
	
	
	private List<Employee> nameDeptNoJob(String name, Integer deptId) {
		String nameArr[] = name.split(" ");
		List<Employee> employees = null;
		
		if(nameArr.length == 1) {
			employees = this.repository.findAllByFirstNameIgnoreCaseAndDepartmentId(nameArr[0], deptId);
		}
		else if(nameArr.length == 2) {
			employees = this.repository.findAllByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndDepartmentId(nameArr[0], nameArr[1], deptId);
		}
		
		return employees;
	}
	
	
	private List<Employee> nameNoDeptJob(String name, Integer jobId) {
		String nameArr[] = name.split(" ");
		List<Employee> employees = null;
		
		if(nameArr.length == 1) {
			employees = this.repository.findAllByFirstNameIgnoreCaseAndJobTitleId(nameArr[0], jobId);
		}
		else if(nameArr.length == 2) {
			employees = this.repository.findAllByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndJobTitleId(nameArr[0], nameArr[1], jobId);
		}
		
		return employees;
	}
	
	
	private List<Employee> nameDeptJob(String name,  Integer deptId, Integer jobId) {
		String nameArr[] = name.split(" ");
		List<Employee> employees = null;
		
		if(nameArr.length == 1) {
			employees = this.repository.findAllByFirstNameIgnoreCaseAndDepartmentIdAndJobTitleId(nameArr[0], deptId, jobId);
		}
		else if(nameArr.length == 2) {
			employees = this.repository.findAllByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndDepartmentIdAndJobTitleId(nameArr[0], nameArr[1], deptId, jobId);
		}
		
		return employees;
	}
	
	
//	public List<Employee> findEmployeesByFirstName(String firstName) {
//	return this.repository.findByFirstNameContainingIgnoreCase(firstName);
//}
//
//public List<Employee> findEmployeesByFirstNameAndLastName(String firstName, String lastName) {
//	return this.repository.findByFirstNameAndLastNameContainingIgnoreCase(firstName, lastName);
//}
//
//public List<Employee> findAllEmployeesByDepartmentId(Integer deptId) {
//	return this.repository.findAllByDepartmentId(deptId);
//}
//
//public List<Employee> findAllEmployeesByJobTitleId(Integer jobId) {
//	return this.repository.findAllByJobTitleId(jobId);
//}
//
//public List<Employee> findAllEmployeesByDepartmentIdAndJobTitleId(Integer deptId, Integer jobTitleId) {
//	return this.repository.findAllByDepartmentIdAndJobTitleId(deptId, jobTitleId);
//}

//public List<Employee> findAllEmployeesByFirstNameIgnoreCase(String firstName) {
//	return this.repository.findAllByFirstNameContainingIgnoreCase(firstName);
//}
//
//public List<Employee> findAllEmployeesByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String lastName) {
//	return this.repository.findAllByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(firstName, lastName);
//}
//
//
//public List<Employee> findAllEmployeesByFirstNameIgnoreCaseAndDepartmentId(	String firstName, Integer deptId) {
//	return this.repository.findAllByFirstNameIgnoreCaseAndDepartmentId(firstName, deptId);
//}
//
//public List<Employee> findAllEmployeesByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndDepartmentId(String firstName, String lastName, Integer deptId) {
//	return this.repository.findAllByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndDepartmentId(firstName, lastName, deptId);
//}
//
//public List<Employee> findAllEmployeesByFirstNameIgnoreCaseAndJobTitleId(String firstName, Integer jobId) {
//	return this.repository.findAllByFirstNameIgnoreCaseAndJobTitleId(firstName, jobId);
//}
//
//public List<Employee> findAllEmployeesByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndJobTitleId(String firstName, String lastName, Integer jobId) {
//	return this.repository.findAllByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndJobTitleId(firstName, lastName, jobId);
//}
//
//public List<Employee> findAllEmployeesByFirstNameIgnoreCaseAndDepartmentIdAndJobTitleId(String firstName, Integer deptId, Integer jobId) {
//	return this.repository.findAllByFirstNameIgnoreCaseAndDepartmentIdAndJobTitleId(firstName, deptId, jobId);
//}
//
//public List<Employee> findAllEmployeesByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndDepartmentIdAndJobTitleId(String firstName, String lastName, Integer deptId, Integer jobId) {
//	return this.repository.findAllByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndDepartmentIdAndJobTitleId(firstName, lastName, deptId, jobId);
//}
//public List<Employee> findAllEmployeesByFirstNameOrLastName(String string, String string2) {
//	return this.repository.findAllByFirstNameIgnoreCaseOrLastNameIgnoreCase(string, string2);
//}
	
	
}
