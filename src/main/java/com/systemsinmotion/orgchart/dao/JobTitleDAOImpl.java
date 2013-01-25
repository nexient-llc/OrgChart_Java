package com.systemsinmotion.orgchart.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;

@Repository("jobTitleDAO")
public class JobTitleDAOImpl implements JobTitleDAO {

	@Autowired
	HibernateTemplate hibernateTemplate;

	public void findByJobTitleID(int id) {
		try {
			this.hibernateTemplate.get(JobTitle.class, id);
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw re;
		}

	}

	@SuppressWarnings("unchecked")
	public List<JobTitle> findByDescription(JobTitle jobTitle) {
		try {
			return this.hibernateTemplate
					.find("from JOBTITLE  where description =?"
							+ jobTitle.getDesc());

		}

		catch (RuntimeException re) {
			re.printStackTrace();
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Employee> findByEmployee(Employee employee) {
		try {

			return this.hibernateTemplate.find("FROM EMPLOYEE WHERE"
					+ "EMPLOYEE=" + employee.getEmployees());

		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<JobTitle> findAllJobTitles(JobTitle jobTitle) {
		try {
			return this.hibernateTemplate.find("From JOB_TITLE"
					+ "Get all JOB_TITLES" + jobTitle.getDesc());

		}

		catch (RuntimeException re) {
			throw re;
		}
	}

	public void deleteJobTItle(JobTitle jobTitle_delete) {

		try {
			this.hibernateTemplate.delete("Delete JOB_TITLE"
					+ jobTitle_delete.getDesc());
		} catch (RuntimeException re) {
			throw re;
		}
	}

	// How do I add unless I'm writing a SQL Query String in order to add to the
	// DB?????
	public Integer addJobTitle(JobTitle jobTitle_add) {
		try {
			return (Integer) this.hibernateTemplate.save(jobTitle_add);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/*
	 * The page has a list of all Job Titles: The list need only display the Job
	 * Title Description The page has an Add button to add a new Job Title. The
	 * Add Job Title section has a field to enter a new Job Title description.
	 * The Add Job Title section has a Save button to save the new Job Title.
	 * The list of all Job Titles has an Edit button on each row that brings up
	 * an edit Job Title section. The Edit Job Title section has a field
	 * containing the Job Title description which can be changed. The Edit Job
	 * Title section has a Save button to save the Job Title. The Edit Job Title
	 * section has an option to delete a Job Title.
	 */

}
