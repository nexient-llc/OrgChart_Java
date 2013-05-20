package com.systemsinmotion.orgchart.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.systemsinmotion.orgchart.entity.Department;

/**
 * A data access object (DAO) providing persistence and search support for Employee entities. Transaction control of the
 * save(), update() and delete() operations must be handled externally by senders of these methods or must be manually
 * added to each of these methods for data to be persisted to the JPA datastore.
 * 
 * @see com.systemsinmotion.orgchart.entity.Department
 * @author Keith Skronek
 */
@Repository("departmentDAO")
public class DepartmentDAO implements IDepartmentDao {

	/**
	 * @see org.slf4j.Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(DepartmentDAO.class);

	/**
	 * @see org.springframework.orm.hibernate3.HibernateTemplate
	 */
	@Autowired
	HibernateTemplate hibernateTemplate;

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.DepartmentDAO#delete(com.systemsinmotion.orgchart.entity.Department)
	 */
	@Override
	public void delete(Department department) {
		LOG.debug("deleting Department instance with id:name = " + department.getId() + ":" + department.getName());
		try {
			this.hibernateTemplate.delete(department);
		} catch (RuntimeException re) {
			LOG.error("delete failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.DepartmentDAO#findAll()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Department> findAll() {
		LOG.debug("finding all Department instances");
		try {
			return this.hibernateTemplate.find("from " + Department.class.getName() + " order by name");
		} catch (RuntimeException re) {
			LOG.error("findAll() failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.DepartmentDAO#findById(java.lang.Integer )
	 */
	@Override
	public Department findById(Integer id) {
		LOG.debug("getting Department instance with id: " + id);
		try {
			return this.hibernateTemplate.get(Department.class, id);
		} catch (RuntimeException re) {
			LOG.error("get failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.DepartmentDAO#findByName(java.lang.String )
	 */
	@Override
	public Department findByName(String name) {
		LOG.debug("finding Department instance by name");
		Department dept = null;
		try {
			@SuppressWarnings("unchecked")
			List<Department> departments = this.hibernateTemplate.find("from Department where name=?", name);
			if (null != departments && !departments.isEmpty()) {
				dept = departments.get(0);
			}
		} catch (RuntimeException re) {
			LOG.error("lookup failed", re);
			throw re;
		}
		return dept;
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.DepartmentDAO#findByParentDepartment
	 * (com.systemsinmotion.orgchart.entity.Department)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Department> findByParentDepartment(Department department) {
		LOG.debug("finding child Departments for Department: " + department.getName());
		try {
			return this.hibernateTemplate.find("from Department where parentDepartment.id=?", department.getId());
		} catch (RuntimeException re) {
			LOG.error("lookup failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.DepartmentDAO#save(com.systemsinmotion.orgchart.entity.Department)
	 */
	@Override
	public Integer save(Department department) {
		LOG.debug("saving Department instance with name: " + department.getName());
		try {
			return (Integer) this.hibernateTemplate.save(department);
		} catch (RuntimeException re) {
			LOG.error("save failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.DepartmentDAO#toString()
	 */
	@Override
	public String toString() {
		String newLine = System.getProperty("line.separator");
		return getClass().getName() + " {" + newLine + "\thibernateTemplate : " + this.hibernateTemplate.toString()
				+ newLine + "}";
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.DepartmentDAO#update(com.systemsinmotion .orgchart.entity.Department)
	 */
	@Override
	public void update(Department department) {
		LOG.debug("updating Department instance with name: " + department.getName());
		try {
			this.hibernateTemplate.update(department);
		} catch (RuntimeException re) {
			LOG.error("update failed", re);
			throw re;
		}
	}
}