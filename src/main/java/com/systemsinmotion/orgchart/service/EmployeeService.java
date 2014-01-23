package com.systemsinmotion.orgchart.service;

import java.util.Collection;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.data.EmployeeRepository;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;

@Service("employeeService")
public class EmployeeService {

	@Autowired
	EmployeeRepository repository;
	
	public Employee storeEmployee(Employee employee){
		Department dept = employee.getDepartment();
		if(dept != null)
			employee.setDepartment(dept.getId() == null ? null : dept);
		JobTitle job = employee.getJobTitle();
		if(job != null)
			employee.setJobTitle(job.getId() == null ? null : job);
		
		try{
			return this.repository.save(employee);
		}
		catch(ConstraintViolationException e){
			return null;
		}
		catch(DataIntegrityViolationException e){
			return null;
		}
	}
	
	public Employee findEmployeeById(Integer id){
		return this.repository.findById(id);
	}
	
	public Employee findEmployeeByEmail(String email){
		return this.repository.findByEmail(email);
	}
	
	public List<Employee> findEmployeesByManagerId(Integer managerId){
		return this.repository.findByManagerId(managerId);
	}
	
	public List<Employee> findEmployeesByDepartmentId(Integer departmentId){
		return this.repository.findByDepartmentId(departmentId);
	}
	
	public List<Employee> findAllEmployees(){
		return this.repository.findAll();
	}

	public void setRepository(EmployeeRepository repository) {
		this.repository = repository;		
	}
	
	public Employee findEmployeeByFirstAndLastName(String firstName, String lastName){
		return this.repository.findByFirstNameAndLastName(firstName, lastName);
	}

	public void setEmployeeInactive(Employee employee) {
		employee.setIsActive(false);
		storeEmployee(employee);	
	}

	public Employee findEmployeeByFirstName(String firstName) {
		return this.repository.findByFirstName(firstName);
	}

	public List<Employee> findEmployeesByJobTitleId(Integer jobTitleId) {
		return this.repository.findByJobTitleId(jobTitleId);
	}

	public List<Employee> findEmployeesLikeFirstOrLastName(String firstName, String lastName) {
		return this.repository.findByFirstNameContainsOrLastNameContainsAllIgnoreCase(firstName, lastName);
	}

	public List<Employee> findActiveEmployees() {
		return this.repository.findByIsActiveTrue();
	}
	
	public String[] findActiveEmployeesNamesOnly(){
		List<Employee> emps = findActiveEmployees();
		String[] empNames = new String[emps.size()];
		for(int i=0;i<emps.size();i++)
			empNames[i] = emps.get(i).getFirstName()+" "+emps.get(i).getLastName();
		return empNames;
	}
	
	public List<Employee> findByNameAndOrDepartmentAndOrJob(String fullName, Integer deptId, Integer jobId){
		List<Employee> emps = null;
		if(!fullName.equals("")){
			String[] split = fullName.split(" ");
			String first = split[0];
			String last = null;
			if(split.length == 2)
				last = split[1];
			else if(split.length == 3)
				last = split[2];
			
			emps =  findEmployeesLikeFirstOrLastName(first, last == null ? first : last);
		}
		if(deptId != null){
			if(emps == null)
				emps =  findEmployeesByDepartmentId(deptId);
			else
				for(int i=0;i<emps.size();i++){
					if(emps.get(i).getDepartment() != null){
						if(!emps.get(i).getDepartment().getId().equals(deptId))
							emps.remove(i--);
					}else
						emps.remove(i--);
				}
		}
		if(jobId != null){
			if(emps == null)
				emps = findEmployeesByJobTitleId(jobId);
			else
				for(int i=0;i<emps.size();i++){
					if(emps.get(i).getJobTitle() != null){
						if(!emps.get(i).getJobTitle().getId().equals(jobId))
							emps.remove(i--);
					}else
						emps.remove(i--);
				}
		}
		
		return emps;
	}
}
