package com.systemsinmotion.orgchart.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.systemsinmotion.orgchart.entity.Department;

@Repository("departmentDAO")
public class DepartmentDAOImpl implements DepartmentDAO {

	@Autowired
	HibernateTemplate hibernateTemplate;

	//save a new department record, return its generated ID	
	public Integer save(Department department) 
	{
		try {
			return (Integer) this.hibernateTemplate.save(department);
		} 
		catch (RuntimeException re) 
		{
			throw re;
		}
	}
	
	//update an existing record
	public void update(Department department) 
	{
		try {
			this.hibernateTemplate.update(department);
		} 
		catch (RuntimeException re) 
		{
			throw re;
		}
	}
	
	//delete a selected record
	public void delete(Department department) 
	{
		try {
			this.hibernateTemplate.delete(department);
		}
		catch (RuntimeException re) 
		{
			throw re;
		}
	}

	//find all departments
	@SuppressWarnings("unchecked")
	public List<Department> findAll() 
	{
		try {
			return this.hibernateTemplate.find(" FROM "
					+ Department.class.getName() 
					+ " ORDER BY name");
		} 
		catch (RuntimeException re) 
		{
			throw re;
		}
	}

	//find a specific department by ID
	public Department findById(Integer id) 
	{
		try {
			return this.hibernateTemplate.get(Department.class, id);
		} 
		catch (RuntimeException re) 
		{
			throw re;
		}
	}

	//find a list of departments by whole or partial name
	@SuppressWarnings("unchecked")
	public List<Department> findByName(String name) 
	{
		try {
			return this.hibernateTemplate.find(
					" FROM "
					+ Department.class.getName()
					+ " WHERE name LIKE ? "
					, "%" + name + "%");
		} 
		catch (RuntimeException re) 
		{
			throw re;
		}
	}

	//find a list of departments based on parent dept
	@SuppressWarnings("unchecked")
	public List<Department> findByParentDepartment(Department department) 
	{
		try {
			return this.hibernateTemplate.find(
					"from Department where parentDepartment.departmentId=?",
					department.getDepartmentId());
		} 
		catch (RuntimeException re) 
		{
			throw re;
		}
	}

}