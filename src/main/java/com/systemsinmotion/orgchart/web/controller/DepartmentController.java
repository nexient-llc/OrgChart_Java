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
import com.systemsinmotion.orgchart.service.DepartmentService;
import com.systemsinmotion.orgchart.web.View;

@Controller
@RequestMapping(value="depts")
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;
	
	public void setDepartmentService(DepartmentService departmentService) 
	{
		
		this.departmentService = departmentService;
		
	}
	
	//initial page load
	@RequestMapping(method=RequestMethod.GET)
	public String doDepartments_GET(Model model) 
	{
			
		//simply return the view
		return View.DEPARTMENTS;
		 
	}
	
	//find by id
	@RequestMapping(params="deptID", method=RequestMethod.GET)
	public String findDeptByID(@RequestParam("deptID") Integer deptID
			,Model model)
	{

		//determine if there is a numeric id greater than 0, which there should be after JS validation
		if (deptID > 0)
		{
			//retrieve the individual department information
			Department dept = departmentService.findDepartmentByID(deptID);
			
			//verify that an object was returned
			if (dept != null)
			{
				//retrieve the entire dept list
				List<Department> depts = departmentService.findAllDepartments();
				
				//pass the info to the model
				model.addAttribute("editDept", dept);
				model.addAttribute("deptsList", depts);
			}
			
			//otherwise return a 'dept not found' message
			else
			{
				//create the message
				 String msg = "No department found. Please try again.";
				
				//pass the info to the model
				model.addAttribute("msg", msg);
			}
		}
		
		//if not, pass back a failure message
		else
		{
			//create the message
			 String msg = "No valid Department ID provided. Please try again.";
			
			//pass the info to the model
			model.addAttribute("msg", msg);
			
		}
		
		//finally, return the view
		return View.DEPARTMENTS;
		
	}
	
	//find by name
	@RequestMapping(params="deptName", method=RequestMethod.GET)
	public String findByDeptName(@RequestParam("deptName") String deptName
			,Model model)
	{
		//determine if a value other than null was passed
		if(deptName != null)
		{
			//retrieve the list of matching records
			List<Department> namedDepts = departmentService.findDepartmentByName(deptName);
			
			//verify that the returned list is not empty
			if(namedDepts.size() > 0)
			{
				//check which display is needed for the returned information
				if(namedDepts.size() == 1)
				{
					//the single record display will work here, so simply grab the object out of the list
					Department dept = namedDepts.get(0);
					
					//grab the whole department list, for the parent drop down
					List<Department> depts = departmentService.findAllDepartments();
					
					//pass the info to the model
					model.addAttribute("editDept", dept);
					model.addAttribute("deptsList", depts);
				}
				
				//multiple records returned, show in a list format instead
				else
				{
					//pass the list to the model
					model.addAttribute("depts", namedDepts);
				}
			}
			
			//otherwise let user know that no matching records where found
			else
			{
				//create the message string
				String msg = "No matching department names found. Please try again.";
				
				//pass it to the model for display
				model.addAttribute("msg", msg);
			}
		}
		
		//otherwise return a 'no dept found' message
		else
		{
			//create the message string
			String msg = "We're sorry, but no departments were found. Please try again.";
			
			//pass it to the model
			model.addAttribute("msg", msg);
		}
		
		//return the department view
		return View.DEPARTMENTS;
	}
	
	//view all departments
	@RequestMapping(params="viewAllDepts", method=RequestMethod.GET)
	public String viewAllDepts(Model model)
	{
		
		//retrieve the requested data from the database
		List<Department> departments = departmentService.findAllDepartments();
		
		//pass it to the model for display
		model.addAttribute("depts", departments);
		
		//and return the department view
		return View.DEPARTMENTS;
		
	}

	//fetch department list for parent drop down
	@RequestMapping(params="addDept", method=RequestMethod.GET)
	public String fetchDepartmentList(Model model)
	{
		//retrieve the requested data from the database
		List<Department> departments = departmentService.findAllDepartments();
		
		//pass it to the model for display
		model.addAttribute("addDept", departments);
		
		//and return the department view
		return View.DEPARTMENTS;
	}
	
	//update an existing department
	@RequestMapping(params="update", method=RequestMethod.POST)
	public String updateDepartment(@Valid Department updateDept
			,@RequestParam("editDeptID") Integer deptID
			,@RequestParam("editName") String name
			,@RequestParam("editParent_id") Integer parentID
			,Model model)
	{
		//verify that a dept ID was passed, otherise return an error message
		if( !(deptID > 0) )
		{
			//create the error message
			String msg = "An error has occured while attempting to process the requested record, please try again.";
			
			//pass the info to the model for display
			model.addAttribute("msg", msg);
		}
		
		//verify that the department name is not empty
		else if( !(name.length() > 0) )
		{
			//create the error message
			String msg = "The department name being edited cannot be blank.";
			
			//pass the info to the model for display
			model.addAttribute("msg", msg);
		}
		
		//verification passes, process the update
		else 
		{
			//set the id and name values
			updateDept.setDepartmentId(deptID);
			updateDept.setName(name);
			
			//set the parent dept
			if(parentID == 0)
			{
				updateDept.setParentDepartment(null);
			}
			else
			{
				updateDept.setParentDepartment(departmentService.findDepartmentByID(parentID));
			}
			
			//update the record
			departmentService.updateDepartment(updateDept);
			
			//verify the update was successful
			Department checkDept = departmentService.findDepartmentByID(deptID);
			
			if(checkDept != null && checkDept.getDepartmentId() == updateDept.getDepartmentId())
			{
				//success message
				String msg = "Update successful";
				
				//pass the info to the model for display
				model.addAttribute("msg", msg);
				
			}
			else
			{
				//fail message
				String msg = "An error has occured while updating your changes. Please try again.";
				
				//pass the info to the model for display
				model.addAttribute("msg", msg);
			}
		}
		
		//return the department view
		return View.DEPARTMENTS;
	}
	
	//add a new department
	@RequestMapping(params="addNew", method=RequestMethod.POST)
	public String addNewDepartment(@Valid Department newDept
			,@RequestParam("parent_id") Integer parentID
			,Model model)
	{
		//find the assigned parent department based on the ID passed in from the form
		if (parentID > 0 && parentID != null)
		{
			newDept.setParentDepartment(departmentService.findDepartmentByID(parentID));
		}
		else
		{
			newDept.setParentDepartment(null);
		}
		
		//save the new department object and populate its new ID
		newDept.setDepartmentId(departmentService.storeDepartment(newDept));
		
		//verify the new dept was added and an id supplied
		if(newDept.getDepartmentId() != null && newDept.getDepartmentId() > 0)
		{
			//tell the user the addition was successful
			String msg = "New department, " + newDept.getName() + ", added successfully";
			
			//pass the msg to the model for display
			model.addAttribute("msg", msg);
		}
		else
		{
			//tell the user the addition was successful
			String msg = "New department, " + newDept.getName() + ", was not added. Please try again.";
			
			//pass the msg to the model for display
			model.addAttribute("msg", msg);
		}
		
		//return the department view
		return View.DEPARTMENTS;
		
	}
	
	//delete a department record from the 'All Departments' list view
	@RequestMapping(params="deleteDeptID" , method=RequestMethod.POST)
	public String deleteDepartment(@RequestParam("deleteDeptID") Integer deptID
			,Model model)
	{
		//verify that an actual dept ID was passed in
		if(deptID > 0)
		{
			//retrieve the dept object based on the id passed in
			Department deptToDelete = departmentService.findDepartmentByID(deptID);
			
			//grab the department name before deleting
			String conf = deptToDelete.getName();
			
			//delete the selected record from the database
			departmentService.removeDepartment(deptToDelete);
			
			//finish the success message
			conf += " has been successfully deleted.";
			
			//pass it to the model
			model.addAttribute("msg", conf);
			
		}

		//otherwise tell the user there was an issue
		else
		{
			//generate the error message
			String msg = "An error has occurred. Department was not deleted. Please try again.";
			
			//pass it to the model
			model.addAttribute("msg", msg);
		}

		//return the department view
		return View.DEPARTMENTS;
	}
	
}
