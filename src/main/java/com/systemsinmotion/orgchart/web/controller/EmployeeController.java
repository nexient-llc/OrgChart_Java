package com.systemsinmotion.orgchart.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;
import com.systemsinmotion.orgchart.service.DepartmentService;
import com.systemsinmotion.orgchart.service.EmployeeService;
import com.systemsinmotion.orgchart.service.JobTitleService;
import com.systemsinmotion.orgchart.web.View;

@Controller
@RequestMapping(value="emps")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	DepartmentService departmentService;

	@Autowired
	JobTitleService jobTitleService;
	
	public void setDepartmentService(DepartmentService departmentService) 
	{
		this.departmentService = departmentService;
	}
	
	public void setEmployeeService(EmployeeService employeeService)
	{
		this.employeeService = employeeService;
	}
	
	public void setJobTitleService(JobTitleService jobTitleService)
	{
		this.jobTitleService = jobTitleService;
	}
	
	//initial page load
	@RequestMapping(method = RequestMethod.GET)
	public String doEmployees_GET(Model model)
	{
		
		//retrieve the base data to be used in search drop downs
		List<Employee> empsList = employeeService.findAllEmployees();
		List<Department> deptsList = departmentService.findAllDepartments();
		List<JobTitle> jtList = jobTitleService.findAllJobTitles();
		
		//pass the data lists to the model for use
		model.addAttribute("emps", empsList);
		model.addAttribute("depts", deptsList);
		model.addAttribute("jobs", jtList);
		
		//return the employee view
		return View.EMPLOYEES;
		
	}
	
	//find by ID
	@RequestMapping(params="findEmpID", method=RequestMethod.GET)
	public String findEmpByID(@RequestParam("findEmpID") Integer empID
			,Model model)
	{
		//verify that we have an id greater than 0
		if(empID > 0)
		{
			//try retrieving the emp record
			Employee editEmp = employeeService.findEmployeeByID(empID);
			
			//verify that a record was returned
			if(editEmp != null)
			{
				//pass the information to the model for display upon page return
				model.addAttribute("editEmp", editEmp);
				
			}
			else
			{
				//pass a message that no employee record was found
				String msg = "No employee record was found for ID: " + empID;
				
				//pass the message to the model
				model.addAttribute("msg", msg);
			}
		}
		else
		{
			//generate an error message for the user
			String msg = "No valid employee ID supplied. Please enter a numeric ID greater than 0.";
			
			//pass the message to the model
			model.addAttribute("msg", msg);
		}
		
		//retrieve the base data to be used in search drop downs
		List<Employee> empsList = employeeService.findAllEmployees();
		List<Department> deptsList = departmentService.findAllDepartments();
		List<JobTitle> jtList = jobTitleService.findAllJobTitles();
		
		//pass the data lists to the model for use
		model.addAttribute("emps", empsList);
		model.addAttribute("depts", deptsList);
		model.addAttribute("jobs", jtList);
		
		//return the emp view
		return View.EMPLOYEES;
	}
	
	//find by Name
	@RequestMapping(params="searchName", method=RequestMethod.GET)
	public String findEmpByName(@RequestParam("findFirstName") String fName
			,@RequestParam("findLastName") String lName
			,Model model)
	{
		//check which fields are not {blank}
		if(fName.equals("{blank}"))
		{
			fName = "0";
		}
		
		if(lName.equals("{blank}"))
		{
			lName = "0";
		}
		
		//verify that both search strings are not 0
		if( !fName.equals("0") || !lName.equals("0") )
		{
			//have the service perform the necessary searches
			List<Employee> returnedEmps = employeeService.findByEmployeeName(fName, lName);
			
			//check if the employee list contains values
			if(returnedEmps.size() > 0)
			{
				//determine if only a single record, or multiple, were returned
				if(returnedEmps.size() == 1)
				{
					//use the edit layout to display the single record
					Employee editEmp = returnedEmps.get(0);
					
					//pass the record to the model
					model.addAttribute("editEmp", editEmp);
				}
				else
				{
					//use the list layout to display all the returned matches
					model.addAttribute("empList", returnedEmps);
				}
			}
			else
			{
				//tell the user there were no matches for their search
				String msg = "No employee records were returned for your search criteria.";
				
				//pass the message to the model
				model.addAttribute("msg", msg);	
			}
		}
		else
		{
			//tell the user there was an error with the input
			String msg = "No valid employee name supplied. Please enter a first name, last name, or combination of first and last name.";
			
			//pass the message to the model
			model.addAttribute("msg", msg);
		}
		
		//retrieve the base data to be used in drop downs
		List<Employee> empsList = employeeService.findAllEmployees();
		List<Department> deptsList = departmentService.findAllDepartments();
		List<JobTitle> jtList = jobTitleService.findAllJobTitles();
		
		//pass the data lists to the model for use
		model.addAttribute("emps", empsList);
		model.addAttribute("depts", deptsList);
		model.addAttribute("jobs", jtList);
		
		//return the emp view
		return View.EMPLOYEES;
	}
	
	//find by Email
	@RequestMapping(params="findEmail", method=RequestMethod.GET)
	public String findEmpByEmail(@RequestParam("findEmail") String email
			,Model model)
	{
		//verify that we did not get an empty string
		if(email.length() > 0)
		{
			//search
			List<Employee> returnedEmps = employeeService.findEmployeeByEmail(email);
			
			//verify that records were returned
			if(returnedEmps.size() > 0)
			{
				//check how many records where returned so we know how to display them
				if(returnedEmps.size() == 1)
				{
					//use the edit view for a single record
					Employee editEmp = returnedEmps.get(0);
					
					model.addAttribute("editEmp", editEmp);
				}
				else
				{
					//use the list view for multiple matches
					model.addAttribute("empList", returnedEmps);
				}
			}
			else
			{
				//otherwise tell the user that no records were found
				String msg = "No records where found for your search criteria.";
				
				//pass the message to the model
				model.addAttribute("msg", msg);
			}
		}
		else
		{
			//tell the user there was an error with the input
			String msg = "No email provided for search criteria. Please enter a whole or partial email and try again.";
			
			//pass the message to the model
			model.addAttribute("msg", msg);
		}
		
		//retrieve the base data to be used in drop downs
		List<Employee> empsList = employeeService.findAllEmployees();
		List<Department> deptsList = departmentService.findAllDepartments();
		List<JobTitle> jtList = jobTitleService.findAllJobTitles();
		
		//pass the data lists to the model for use
		model.addAttribute("emps", empsList);
		model.addAttribute("depts", deptsList);
		model.addAttribute("jobs", jtList);
		
		//return the emp view
		return View.EMPLOYEES;
	}
	
	//find by Dept
	@RequestMapping(params="deptSelection", method=RequestMethod.GET)
	public String findEmpsByDept(@RequestParam("deptSelection") Integer deptID
		,Model model)
	{
		//verify that deptID is an integer greater than 0
		if(deptID > 0)
		{
			//retrieve the department record for the id passed
			Department dept = departmentService.findDepartmentByID(deptID);
			
			//verify that a dept was returned
			//this shouldn't be an issue since the ID is passed from a select, but just in case
			if(dept != null)
			{
				//retrieve the employee records tied to that department
				List<Employee> returnedEmps = employeeService.findEmployeesByDepartment(dept);
				
				//verify that records were returned
				if(returnedEmps.size() > 0)
				{
					//pass the returned records to the model, using the list view for display
					model.addAttribute("empList", returnedEmps);
				}
				else
				{
					//otherwise tell the user that no records were returned
					String msg = "No employee records were returned for that department.";
					
					//pass the message to the model
					model.addAttribute("msg", msg);
				}
			}
			else
			{
				//otherwise tell the user that no records were returned
				String msg = "An error occurred during the lookup of the selected department. Please refresh and try again.";
				
				//pass the message to the model
				model.addAttribute("msg", msg);
			}
			
		}
		else
		{
			//tell the user there was an error
			String msg = "An error occurred during the lookup of the selected department. Please refresh and try again.";
			
			//pass the message to the model
			model.addAttribute("msg", msg);
		}
		
		//retrieve the base data to be used in drop downs
		List<Employee> empsList = employeeService.findAllEmployees();
		List<Department> deptsList = departmentService.findAllDepartments();
		List<JobTitle> jtList = jobTitleService.findAllJobTitles();
		
		//pass the data lists to the model for use
		model.addAttribute("emps", empsList);
		model.addAttribute("depts", deptsList);
		model.addAttribute("jobs", jtList);
		
		//return the emp view
		return View.EMPLOYEES;
	}
	
	//find by Title
	@RequestMapping(params="titleSelection", method=RequestMethod.GET)
	public String findEmpsByTitle(@RequestParam("titleSelection") Integer jtID
		,Model model)
	{
		//verify that jtID is an integer greater than 0
		if(jtID > 0)
		{
			//retrieve the record for the passed job title id
			JobTitle jt = jobTitleService.findByJobTitleID(jtID);
			
			//verify that a record was returned
			//again, shouldn't be an issue because we're using a select, however, just to be safe
			if(jt != null)
			{
				//retrieve employees with this job title
				List<Employee> returnedEmps = employeeService.findEmployeesByJobTitle(jt);
				
				//verify that there are record to show the user
				if(returnedEmps.size() > 0)
				{
					//pass the list to the model for display
					model.addAttribute("empList", returnedEmps);
				}
				else
				{
					//tell the user that no records were returned
					String msg = "No employee records where returned for the selected job title";
					
					//pass the message to the model
					model.addAttribute("msg", msg);
				}
			}
			else
			{
				//tell the user that there was an issue searching by that job title
				String msg = "An error occured while searching by the selected job title. Please refresh and try again.";
			
				//pass the message to the model for display
				model.addAttribute("msg", msg);		
			}
		}
		else
		{
			//tell the user there was an issue
			String msg = "An error occurred during the lookup of the selected job title. Please refresh and try again.";
			
			//pass the message to the model
			model.addAttribute("msg", msg);
			
		}
		
		//retrieve the base data to be used in drop downs
		List<Employee> empsList = employeeService.findAllEmployees();
		List<Department> deptsList = departmentService.findAllDepartments();
		List<JobTitle> jtList = jobTitleService.findAllJobTitles();
		
		//pass the data lists to the model for use
		model.addAttribute("emps", empsList);
		model.addAttribute("depts", deptsList);
		model.addAttribute("jobs", jtList);
		
		//return the emp view
		return View.EMPLOYEES;		
	}
	
	//find by Manager
	@RequestMapping(params="mgrSelection", method=RequestMethod.GET)
	public String findEmpsByManager(@RequestParam("mgrSelection") Integer mgrID
			,Model model)
	{
		//verify that mgrID is a integer larger than 0
		if(mgrID > 0)
		{
			//retrieve the manager's information
			Employee mgr = employeeService.findEmployeeByID(mgrID);
			
			//verify that a record has returns
			//using a select here as well, but just to be safe we'll verify
			if(mgr != null)
			{
				//fetch the employees tied to this manager
				List<Employee> returnedEmps = employeeService.findEmployeesByManager(mgr);
				
				//check that we have some returns
				if(returnedEmps.size() > 0)
				{
					//pass the list to model
					model.addAttribute("empList", returnedEmps);
				}
				else
				{
					//no records returned
					String msg = "No employee records were returned for this manager";
					
					//pass to the model
					model.addAttribute("msg", msg);
				}
			}
			else
			{
				//tell the user that no manager was found
				String msg = "An error occured while searching by the selected manager. Please try again.";
				
				//pass the msg to the model
				model.addAttribute("msg", msg);	
			}
		}
		else
		{
			//tell the user there was an issue
			String msg = "An error occurred during the lookup of the selected manager. Please refresh and try again.";
			
			//pass the message to the model
			model.addAttribute("msg", msg);
		}
		
		//retrieve the base data to be used in drop downs
		List<Employee> empsList = employeeService.findAllEmployees();
		List<Department> deptsList = departmentService.findAllDepartments();
		List<JobTitle> jtList = jobTitleService.findAllJobTitles();
		
		//pass the data lists to the model for use
		model.addAttribute("emps", empsList);
		model.addAttribute("depts", deptsList);
		model.addAttribute("jobs", jtList);
		
		//return the emp view
		return View.EMPLOYEES;	
	}
	
	//view all
	@RequestMapping(params="viewAllEmps", method=RequestMethod.GET)
	public String viewAllEmps(Model model)
	{
		//retrieve the base data to be used in drop downs
		List<Employee> empsList = employeeService.findAllEmployees();
		List<Department> deptsList = departmentService.findAllDepartments();
		List<JobTitle> jtList = jobTitleService.findAllJobTitles();
		
		//pass the data lists to the model for use
		model.addAttribute("emps", empsList);
		model.addAttribute("empList", empsList);
		model.addAttribute("depts", deptsList);
		model.addAttribute("jobs", jtList);
		
		//pass the data lists to the model for use
		model.addAttribute("emps", empsList);
		model.addAttribute("depts", deptsList);
		model.addAttribute("jobs", jtList);
		
		//return the view
		return View.EMPLOYEES;
	}
	
	//request add new
	@RequestMapping(params="addNew", method=RequestMethod.GET)
	public String fetchInfoForAdd(Model model)
	{
		//retrieve the base data to be used in drop downs
		List<Employee> empsList = employeeService.findAllEmployees();
		List<Department> deptsList = departmentService.findAllDepartments();
		List<JobTitle> jtList = jobTitleService.findAllJobTitles();
		
		//pass the data lists to the model for use
		model.addAttribute("addEmp", true);
		model.addAttribute("emps", empsList);
		model.addAttribute("depts", deptsList);
		model.addAttribute("jobs", jtList);
				
		//return the view		
		return View.EMPLOYEES;
	}
	
	//add new
	@RequestMapping(params="firstName, lastName", method=RequestMethod.POST)
	public String addNewEmployee(@Valid Employee newEmp
			,@RequestParam("departmentId") Integer deptID
			,@RequestParam("jobTitleID") Integer jtID
			,@RequestParam("managerID") Integer mgrID
			,Model model)
	{
		
		//if the 'is manager' field was not checked, change it from null to false 
		if(newEmp.getIsManager() == null)
		{
			newEmp.setIsManager(false);
		}
		
		//find the assigned department data based on the ID passed in
		if(deptID > 0)
		{
			newEmp.setDept(departmentService.findDepartmentByID(deptID));
		}
		else 
		{
			newEmp.setDept(null);
		}
		
		//find the employee's job title
		if(jtID > 0)
		{
			newEmp.setJobTitle(jobTitleService.findByJobTitleID(jtID));
		}
		else
		{
			newEmp.setJobTitle(null);
		}
		
		//find manager record
		if(mgrID > 0)
		{
			newEmp.setManager(employeeService.findEmployeeByID(mgrID));
		}
		else
		{
			newEmp.setManager(null);
		}
		
		//set blank emails to null to avoid contraint issues
		if(newEmp.getEmail().equals(""))
		{
			newEmp.setEmail(null);
		}
		
		//set blank skype names to null to avoid contraint issues
		if (newEmp.getSkypeName().equals(""))
		{
			newEmp.setSkypeName(null);
		}
	
		//insert a new record and populate the employee id
		newEmp.setEmpID(employeeService.createEmployeeRecord(newEmp));
		
		//verify the record was created, and tell the user
		if(newEmp.getEmpID() != null && newEmp.getEmpID() > 0)
		{
			String msg = "Employee record successfully created for: " + newEmp.getFirstName() + " " + newEmp.getLastName() + ".";
			
			model.addAttribute("msg", msg);
		}
		else
		{
			//otherwise tell them that the record was not added
			String msg = "Employee record was not created for: " + newEmp.getFirstName() + " " + newEmp.getLastName() + ". Please try again.";
			
			model.addAttribute("msg", msg);
		}
		
		//retrieve the data to be displayed on the page
		List<Employee> empsList = employeeService.findAllEmployees();
		List<Department> deptsList = departmentService.findAllDepartments();
		List<JobTitle> jtList = jobTitleService.findAllJobTitles();
		
		//pass the data lists to the model for display
		model.addAttribute("emps", empsList);
		model.addAttribute("depts", deptsList);
		model.addAttribute("jobs", jtList);
		
		//and finally, return the employee view
		return View.EMPLOYEES;
		
	}
	
	//update existing
	@RequestMapping(params="editEmpID", method=RequestMethod.POST)
	public String updateExistingEmployee(@Valid Employee editEmp
			,@RequestParam("editDepartmentId") Integer deptID
			,@RequestParam("editJobTitleID") Integer jtID
			,@RequestParam("editManagerID") Integer mgrID
			,@RequestParam("editEmpID") Integer empID
			,@RequestParam("editFirstName") String fName
			,@RequestParam("editLastName") String lName
			,@RequestParam("editEmail") String email
			,@RequestParam("editSkypeName") String skypeName
			,Model model)
	{
		//first set the id in the object so we dont get a null pointer exception
		editEmp.setEmpID(empID);
		
		//set the name 
		editEmp.setFirstName(fName);
		
		//set the last name
		editEmp.setLastName(lName);
		
		//find the assigned department data based on the ID passed in
		if(deptID > 0)
		{
			editEmp.setDept(departmentService.findDepartmentByID(deptID));
		}
		else 
		{
			editEmp.setDept(null);
		}
		
		//set blank emails to null to avoid contraint issues
		if(email.equals(""))
		{
			editEmp.setEmail(null);
		}
		else
		{
			editEmp.setEmail(email);
		}
		
		//set blank skype names to null to avoid contraint issues
		if (skypeName.equals(""))
		{
			editEmp.setSkypeName(null);
		}
		else
		{
			editEmp.setSkypeName(skypeName);
		}
	
		//find the employee's job title
		if(jtID > 0)
		{
			editEmp.setJobTitle(jobTitleService.findByJobTitleID(jtID));
		}
		else
		{
			editEmp.setJobTitle(null);
		}
		
		//find manager record
		if(mgrID > 0)
		{
			editEmp.setManager(employeeService.findEmployeeByID(mgrID));
		}
		else
		{
			editEmp.setManager(null);
		}
		
		//if the 'is manager' field was not checked, change it from null to false 
		if(editEmp.getIsManager() == null)
		{
			editEmp.setIsManager(false);
		}
		
		//now run the update
		employeeService.updateEmployeeRecord(editEmp);
				
		//verify the record returns, and tell the user
		if(editEmp.getEmpID() != null && editEmp.getEmpID() > 0)
		{
			String msg = "Employee record successfully updated for: " + editEmp.getFirstName() + " " + editEmp.getLastName() + ".";
			
			model.addAttribute("msg", msg);
		}
		else
		{
			//otherwise tell them that the record was not updated
			String msg = "Employee record was not updated for: " + editEmp.getFirstName() + " " + editEmp.getLastName() + ". Please try again.";
			
			model.addAttribute("msg", msg);
		}
		
		//retrieve the data to be displayed on the page
		List<Employee> empsList = employeeService.findAllEmployees();
		List<Department> deptsList = departmentService.findAllDepartments();
		List<JobTitle> jtList = jobTitleService.findAllJobTitles();
		
		//pass the data lists to the model for display
		model.addAttribute("emps", empsList);
		model.addAttribute("depts", deptsList);
		model.addAttribute("jobs", jtList);
		
		//and finally, return the employee view
		return View.EMPLOYEES;
		
	}
	
	//delete
	@RequestMapping(params="deleteEmpID", method = RequestMethod.POST)
	public String deleteEmployeeRecord(@RequestParam("deleteEmpID") Integer empID
			,Model model)
	{
		//verify that we have an id that is an integer greater than 0
		if(empID > 0)
		{
			//retrieve employee record based on passed ID
			Employee emp = employeeService.findEmployeeByID(empID);
			
			//verify that a record was returned
			if(emp != null)
			{
				//delete the selected employee record
			    employeeService.deleteEmployeeRecord(emp);
			    
			    //verify deletion
			    emp = employeeService.findEmployeeByID(empID);
			    
			    if(emp == null)
			    {
			    	//display success msg
			    	String msg = "Employee record deleted successfully.";
					
					//pass the msg to the model
					model.addAttribute("msg", msg);
			    }
			    else
			    {
			    	//show failure msg
			    	String msg = "An error occured while processing your delete request. Record was not deleted. Please try again.";
					
					//pass the msg to the model
					model.addAttribute("msg", msg);
			    }
			}
			else
			{
				//tell the user there was an error
				String msg = "An error occured while processing your delete request. Record was not deleted. Please try again.";
				
				//pass the msg to the model
				model.addAttribute("msg", msg);
			}
		}
		else
		{
			//tell the user there was an issue
			String msg = "An error occured while processing your delete request. Record was not deleted. Please try again.";
			
			//pass the msg to the model
			model.addAttribute("msg", msg);
		}
		
		//retrieve the data to be displayed on the page
		List<Employee> empsList = employeeService.findAllEmployees();
		List<Department> deptsList = departmentService.findAllDepartments();
		List<JobTitle> jtList = jobTitleService.findAllJobTitles();
		
		//pass the data lists to the model for display
		model.addAttribute("emps", empsList);
		model.addAttribute("depts", deptsList);
		model.addAttribute("jobs", jtList);
				
		//return employee view
		return View.EMPLOYEES;
	}
	
}
