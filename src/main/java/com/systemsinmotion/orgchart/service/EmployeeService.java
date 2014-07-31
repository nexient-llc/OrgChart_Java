package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.systemsinmotion.orgchart.data.EmployeeRepository;
import com.systemsinmotion.orgchart.data.SimpleEmployeeRepository;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;
import com.systemsinmotion.orgchart.entity.SimpleEmployee;

@Service("employeeService")
@Transactional(readOnly = true)
public class EmployeeService {

	@Autowired
	EmployeeRepository repository;

	@Autowired
	SimpleEmployeeRepository simpleRepository;

	public List<Employee> findAllEmployees() {
		return this.repository.findAll();
	}

	public Employee findEmployeeByID(Integer employeeId) {
		return this.repository.findById(employeeId);
	}

	@Secured("ROLE_ADMIN")
	@Transactional
	public Employee storeEmployee(Employee employee) {
		if (employee == null) {
			throw new IllegalArgumentException();
		}
		if (employee.getIsActive() == null) {
			employee.setIsActive(true);
		}
		return this.repository.save(employee);
	}

	@Secured("ROLE_ADMIN")
	@Transactional
	public void removeEmployee(Employee employee) {
		if (employee == null) {
			throw new IllegalArgumentException();
		}
		employee.setIsActive(false);
		this.repository.save(employee);
	}

	public void setRepository(EmployeeRepository repository) {
		this.repository = repository;
	}

	public EmployeeRepository getRepository() {
		return this.repository;
	}

	@Secured("ROLE_ADMIN")
	@Transactional
	public void removeEmployeeById(Integer empId) {
		Employee employee = this.repository.findById(empId);
		removeEmployee(employee);
	}

	public List<Employee> findEmployeesByJobTitle(JobTitle jobTitle) {
		return this.repository.findByJobTitle(jobTitle);
	}

	public List<Employee> findEmployeesByFirstName(String firstName) {
		return this.repository.findByFirstNameContainingIgnoreCase(firstName);
	}

	public List<Employee> findEmployeesByLastName(String lastName) {
		return this.repository.findByLastNameContainingIgnoreCase(lastName);
	}

	public List<Employee> findEmployeesByDepartment(Department department) {
		return this.repository.findByDepartment(department);
	}

	public Page<Employee> findAllActiveEmployees(PageRequest pageRequest) {
		return this.repository.findByIsActiveIsTrue(pageRequest);
	}

	public List<Employee> findAllActiveEmployees() {
		return this.repository.findByIsActiveIsTrue();
	}

	public List<Employee> findEmployeesByFilter(String firstName,
			String lastName, Integer deptId, Integer jobId) {

		int bitVector = findBitVector(firstName, lastName, deptId, jobId);

		List<Employee> employees = null;
		switch (bitVector) {
		case 1: // First Name
			employees = repository
					.findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(
							firstName, firstName);
			break;
		case 2: // Last Name
			employees = repository
					.findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(
							lastName, lastName);
			break;
		case 3: // First Name, Last Name
			employees = repository
					.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndIsActiveIsTrue(
							firstName, lastName);
			break;
		case 4: // Department
			employees = repository.findByDepartmentIdAndIsActiveIsTrue(deptId);
			break;
		case 5: // First Name, Department
			employees = repository.findByUpperCaseNameAndDepartmentIdAndActive(
					firstName.toUpperCase(), deptId);
			break;
		case 6: // Last Name, Department
			employees = repository.findByUpperCaseNameAndDepartmentIdAndActive(
					lastName.toUpperCase(), deptId);
			break;
		case 7: // First Name, Last Name, Department
			employees = repository
					.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndDepartmentIdAndIsActiveIsTrue(
							firstName, lastName, deptId);
			break;
		case 8: // JobTitle
			employees = repository.findByJobTitleIdAndIsActiveIsTrue(jobId);
			break;
		case 9: // First Name JobTitle
			employees = repository.findByUpperCaseNameAndJobTitleAndActive(
					firstName.toUpperCase(), jobId);
			break;
		case 10: // Last Name, JobTitle
			employees = repository.findByUpperCaseNameAndJobTitleAndActive(
					lastName.toUpperCase(), jobId);
			break;
		case 11: // First Name, Last Name, JobTitle
			employees = repository
					.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndJobTitleIdAndIsActiveIsTrue(
							firstName, lastName, jobId);
			break;
		case 12: // Department, JobTitle
			employees = repository
					.findByDepartmentIdAndJobTitleIdAndIsActiveIsTrue(deptId,
							jobId);
			break;
		case 13: // First Name, Department, JobTitle
			employees = repository
					.findByUpperCaseNameAndDepartmentAndJobTitleAndActive(
							firstName.toUpperCase(), deptId, jobId);
			break;
		case 14: // Last Name, Department, JobTitle
			employees = repository
					.findByUpperCaseNameAndDepartmentAndJobTitleAndActive(
							lastName.toUpperCase(), deptId, jobId);
			break;
		case 15: // First Name, Last Name, Department, JobTitle
			employees = repository
					.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndDepartmentIdAndJobTitleIdAndIsActiveIsTrue(
							firstName, lastName, deptId, jobId);
			break;
		case 0:
		default:
			employees = findAllActiveEmployees();
			break;
		}
		return employees;
	}

	private int findBitVector(String firstName, String lastName,
			Integer deptId, Integer jobId) {
		int vector = 0;
		if (firstName != null) {
			vector |= 0x1; // First Bit
		}
		if (lastName != null) {
			vector |= 0x2; // Second Bit
		}
		if (deptId != null) {
			vector |= 0x4; // Third Bit
		}
		if (jobId != null) {
			vector |= 0x8; // Fourth Bit
		}
		return vector;
	}

	public List<SimpleEmployee> findEmployeesByNameOnlyFilter(String firstName,
			String lastName) {
		List<SimpleEmployee> employees = null;
		if (firstName != null && lastName != null) {
			employees = simpleRepository
					.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndIsActiveIsTrue(
							firstName, lastName);
		} else if (firstName != null) {
			employees = simpleRepository
					.findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(
							firstName, firstName);
		} else if (lastName != null) {
			employees = simpleRepository
					.findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(
							lastName, lastName);
		} else {
			employees = simpleRepository.findAll();
		}
		return employees;
	}

	public SimpleEmployee findSimpleEmployeeById(Integer id) {
		return simpleRepository.findById(id);
	}

	public List<Employee> findEmployeesByCriteriaFilter(String firstName,
			String lastName, Integer deptId, Integer jobId) {
		return repository.findActiveByUnknownInputs(firstName, lastName,
				deptId, jobId);
	}

	public List<Employee> findEmployeeBySkype(String skypeName) {
		return repository.findBySkypeNameIgnoreCase(skypeName);
	}

	public List<Employee> findEmployeeByEmail(String email) {
		return repository.findByEmailIgnoreCase(email);
	}
}