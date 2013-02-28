package com.systemsinmotion.orgchart.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.systemsinmotion.orgchart.entity.JobTitle;
import com.systemsinmotion.orgchart.service.JobTitleService;
import com.systemsinmotion.orgchart.web.View;

@Controller
@RequestMapping(value="jobs")
public class JobTitleController {

	@Autowired
	JobTitleService jobTitleService;
	
	public void setJobTitleService(JobTitleService jobTitleService)
	{
		
		this.jobTitleService = jobTitleService;
		
	}
	
	//initial page load
	@RequestMapping(method=RequestMethod.GET)
	public String doJobTitles_GET(Model model)
	{	
		//return the job title view
		return View.JOB_TITLES;	
	}
	
	//find by ID
	@RequestMapping(params="jobTitleID", method=RequestMethod.GET)
	public String findTitleByID(@RequestParam("jobTitleID") Integer jtID
			,Model model)
	{
		//determine if there is a numeric id greater than 0, which there should be after JS validation
		if (jtID > 0)
		{
			//retrieve the individual department information
			JobTitle jobTitle = jobTitleService.findByJobTitleID(jtID);
			
			//verify that an object was returned
			if (jobTitle != null)
			{	
				//pass the info to the model
				model.addAttribute("editJob", jobTitle);
			}
			
			//otherwise return a 'title not found' message
			else
			{
				//create the message
				 String msg = "No job title found. Please try again.";
				
				//pass the info to the model
				model.addAttribute("msg", msg);
			}
		}
		
		//if not, pass back a failure message
		else
		{
			//create the message
			 String msg = "No valid Job Title ID provided. Please try again.";
			
			//pass the info to the model
			model.addAttribute("msg", msg);
			
		}
		
		//return the job title view
		return View.JOB_TITLES;
	}
	
	//find by Description
	@RequestMapping(params="jobTitleName", method=RequestMethod.GET)
	public String findJobTitleByDescription(@RequestParam("jobTitleName") String searchDesc
			,Model model)
	{
		//determine if a value other than null was passed
		if(searchDesc != null)
		{
			//retrieve the list of matching records
			List<JobTitle> namedJobTitles = jobTitleService.findByJobTitleDescription(searchDesc);
			
			//verify that the returned list is not empty
			if(namedJobTitles.size() > 0)
			{
				//check which display is needed for the returned information
				if(namedJobTitles.size() == 1)
				{
					//the single record display will work here, so simply grab the object out of the list
					JobTitle job = namedJobTitles.get(0);
									
					//pass the info to the model
					model.addAttribute("editJob", job);
				}
				
				//multiple records returned, show in a list format instead
				else
				{
					//pass the list to the model
					model.addAttribute("jobs", namedJobTitles);
				}
			}
			
			//otherwise let user know that no matching records where found
			else
			{
				//create the message string
				String msg = "No matching job titles found. Please try again.";
				
				//pass it to the model for display
				model.addAttribute("msg", msg);
			}
		}
		
		//otherwise return a 'no job title found' message
		else
		{
			//create the message string
			String msg = "We're sorry, but no job titles were found. Please try again.";
			
			//pass it to the model
			model.addAttribute("msg", msg);
		}
		
		//return the job title view
		return View.JOB_TITLES;
	}
	
	//view all
	@RequestMapping(params="viewAllJobs", method=RequestMethod.GET)
	public String viewAllJobTitles(Model model)
	{
		//fetch the desired data
		List<JobTitle> jobs = jobTitleService.findAllJobTitles();
		
		//pass it to the model
		model.addAttribute("jobs", jobs);
		
		//return the job title view
		return View.JOB_TITLES;
	}
	
	//add new
	@RequestMapping(params="addNewJob", method=RequestMethod.POST)
	public String doJobTitles_POST(@Valid JobTitle newJT
			,Model model)
	{
		
		//add the new job title to the datase, populating the object ID 
		newJT.setJobTitleID(jobTitleService.createJobTitle(newJT));
		
		//verify the new record
		if(newJT.getJobTitleID() != null && newJT.getJobTitleID() > 0)
		{
			//return a success message
			String msg = newJT.getDesc() + " was successfully added.";
			
			//pass to the model for display
			model.addAttribute("msg", msg);
		}
		else
		{
			//return an error message
			String msg = "An error has occured. New job title was not created, please try again.";
			
			//pass to the model for display
			model.addAttribute("msg", msg);
		}
		
		//and return the job title view
		return View.JOB_TITLES;
		
	}
	
	//update existing
	@RequestMapping(params="editJobID", method=RequestMethod.POST)
	public String updateJobTitle(@Valid JobTitle editJT
			,@RequestParam("editJobID") Integer jtID
			,@RequestParam("editDesc") String editDesc
			,Model model)
	{
		//verify that an id was passed
		if( !(jtID > 0 ) )
		{
			//tell the user that an error occurred
			String msg = "An error has occured while attempting to process the requested record, please try again.";
			
			//pass the info to the model for display
			model.addAttribute("msg", msg);
		}
		
		//verify that the description being updated is not blank
		else if ( !(editDesc.length() > 0) )
		{
			//tell the user that an error occurred
			String msg = "The job title description to be edited cannot be blank.";
			
			//pass the info to the model for display
			model.addAttribute("msg", msg);
		}
		
		//verification passes, process the update
		else 
		{
			//set the id and name values
			editJT.setJobTitleID(jtID);
			editJT.setDesc(editDesc);
			
			//update the record
			jobTitleService.updatejobTitle(editJT);
			
			//verify the update was successful
			JobTitle checkJT = jobTitleService.findByJobTitleID(jtID);
			
			if(checkJT != null && checkJT.getJobTitleID() == editJT.getJobTitleID())
			{
				//success message
				String msg = "Update successful";
				
				//pass the info to the model for display
				model.addAttribute("msg", msg);
				
			}
			else
			{
				//fail message
				String msg = "An error has occured while saving your changes. Please try again.";
				
				//pass the info to the model for display
				model.addAttribute("msg", msg);
			}
		}

		
		//return the job title view
		return View.JOB_TITLES;
	}
	
	//delete selected
	@RequestMapping(params="deleteJobID", method=RequestMethod.POST)
	public String deleteJobTitle(@RequestParam("deleteJobID") Integer jtID
			,Model model)
	{
		//verify that an actual dept ID was passed in
		if(jtID > 0)
		{
			//retrieve the job title object based on the id passed in
			JobTitle jobToDelete = jobTitleService.findByJobTitleID(jtID);
			
			//verify the object exists, before trying to delete it
			if(jobToDelete != null)
			{
				//grab the department name before deleting
				String conf = jobToDelete.getDesc();
				
				//delete the selected record from the database
				jobTitleService.deleteJobTitle(jobToDelete);
				
				//finish the success message
				conf += " has been successfully deleted.";
				
				//pass it to the model
				model.addAttribute("msg", conf);
			}
			else
			{
				//create an error message
				String conf = "An error has occurred. Job Title was not deleted. Please try again.";
				
				//pass it for display
				model.addAttribute("msg", conf);
			}
			
		}

		//otherwise tell the user there was an issue
		else
		{
			//generate the error message
			String msg = "An error has occurred. Job Title was not deleted. Please try again.";
			
			//pass it to the model
			model.addAttribute("msg", msg);
		}
		
		//return the job title view
		return View.JOB_TITLES;
	}
	
}
