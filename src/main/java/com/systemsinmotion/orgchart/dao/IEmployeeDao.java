package com.systemsinmotion.orgchart.dao;

import java.util.List;

import com.systemsinmotion.orgchart.entity.Employee;

public interface IEmployeeDao {
	
	/**
	 * Deletes the <code>Employee</code> instance from the database.
	 * 
	 * @param employee
	 *            Employee instance to delete
	 */
	public abstract void delete(Employee employee);
	
	/**
	 * Returns all <code>Employee</code> instances as a <code>List</code>
	 * 
	 * @return List of Employees
	 */
	public abstract List<Employee> findAll();
	
	/**
	 * Returns a <code>Employee</code> instance having a given <code>id</code>
	 * value.
	 * 
	 * @param id
	 *            Identifier of <code>Employee</code> instance to find
	 * @return Single <code>Employee</code> instance. Null if not found.
	 */
	public abstract Employee findById(Integer id);

	/**
	 * Returns all <code>Employee</code> instances having a given <code>first_name</code>
	 * value.
	 * 
	 * @param first_name
	 *            first_name value of <code>Employee</code> instances to find
	 * @return All <code>Employee</code> instances with the corresponding first_name value.
	 *			  Null if not found.
	 */
	public abstract List<Employee> findByFirstName(String first_name);
	
	/**
	 * Returns all <code>Employee</code> instances having a given <code>last_name</code>
	 * value.
	 * 
	 * @param last_name
	 *            last_name value of <code>Employee</code> instances to find
	 * @return All <code>Employee</code> instances with the corresponding last_name value.
	 *			  Null if not found.
	 */
	public abstract List<Employee> findByLastName(String last_name);
	
	/**
	 * Returns a <code>Employee</code> instance having a given <code>email</code>
	 * value.
	 * 
	 * @param email
	 *            email value of a <code>Employee</code> instance to find
	 * @return A <code>Employee</code> instance with the corresponding email value.
	 *			  Null if not found.
	 */
	public abstract Employee findByEmail(String email);
	
	/**
	 * Returns a <code>Employee</code> instance having a given <code>skype_name</code>
	 * value.
	 * 
	 * @param skype_name
	 *            skype_name value of a<code>Employee</code> instance to find
	 * @return A <code>Employee</code> instance with the corresponding skype_name value.
	 *			  Null if not found.
	 */
	public abstract Employee findBySkypeName(String skype_name);
	
	/**
	 * Returns all <code>Employee</code> instances having a given <code>is_manager</code>
	 * value.
	 * 
	 * @param is_manager
	 *            is_manager value of <code>Employee</code> instances to find
	 * @return All <code>Employee</code> instances with the corresponding is_manager value.
	 *			  Null if not found.
	 */
	public abstract List<Employee> findByManagerStatus(boolean is_Manager);
	
	/**
	 * Returns all <code>Employee</code> instances having a given <code>ManagerId</code>
	 * value.
	 * 
	 * @param managerId
	 *            Contains value of a manager's id which <code>Employee</code> instances are associated with
	 *            
	 * @return All <code>Employee</code> instances with the corresponding ManagerId.
	 *			  Null if not found.
	 */
	public abstract List<Employee> findByManager(int managerId);
	
	/**
	 * Returns all <code>Employee</code> instances having a given <code>Manager</code>
	 * <code>Employee</code> instance.
	 * 
	 * @param manager
	 *            Contains value of a <code>Employee</code> instance corresponding to a manager
	 *            which <code>Employee</code> instances are associated with.
	 *            
	 * @return All <code>Employee</code> instances with the corresponding <code>Employee</code> instance
	 * 			as manager. Null if not found.
	 *
	 */
	public abstract List<Employee> findByManager(Employee manager);
	/**
	 * Returns all <code>Employee</code> instances having a given <code>Job_Title_Id</code>
	 * value.
	 * 
	 * @param employee
	 *            Contains value of a Job Title's id which <code>Employee</code> instances are associated with
	 *            
	 * @return All <code>Employee</code> instances with the corresponding Job_Title_Id.
	 *			  Null if not found.
	 */
	public abstract List<Employee> findByJobTitle(Employee employee);
	
	/**
	 * Returns all <code>Employee</code> instances having a given <code>Department_id</code>
	 * value.
	 * 
	 * @param departmentId
	 *            Contains value of a department's id which <code>Employee</code> instances are associated with
	 *            
	 * @return All <code>Employee</code> instances with the corresponding department ID.
	 *			  Null if not found.
	 */
	public abstract List<Employee> findByDepartment(Integer departmentId);
	
	/**
	 * Saves a give <code>Employee</code instance and returns its generated
	 * id.
	 * 
	 * @param employee
	 *            The <code>Employee</code> instance to be saved
	 * @return The identifier (id) of the new <code>Employee</code> instance.
	 */
	public abstract int save(Employee employee);

	/**
	 * Updates an existing <code>Employee</code> instance with new values.
	 * 
	 * @param employee
	 *            The <code>Employee</code> instance to be updated
	 */
	public abstract void update(Employee employee);
}
