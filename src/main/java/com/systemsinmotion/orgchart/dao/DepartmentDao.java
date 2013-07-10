package com.systemsinmotion.orgchart.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import com.systemsinmotion.orgchart.entity.Department;

/**
 * A data access object (DAO) providing persistence and search support for Employee entities. Transaction control of the
 * save(), update() and delete() operations must be handled externally by senders of these methods or must be manually
 * added to each of these methods for data to be persisted to the JPA datastore.
 * 
 * @see com.systemsinmotion.orgchart.entity.Department
 * @author Keith Skronek
 */
@Repository("departmentDao")
public class DepartmentDao implements com.systemsinmotion.orgchart.dao.IDepartmentDao {

	/**
	 * @see org.slf4j.Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(DepartmentDao.class);

	/**
	 * @see org.springframework.orm.hibernate3.HibernateTemplate
	 */
	@Autowired
	HibernateTemplate hibernateTemplate;

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.DepartmentDAO#delete(com.systemsinmotion .orgchart.entity.Department)
	 */
	@Override
	public void delete(Department department) {
		LOG.debug("deleting Department instance with name: " + department.getName());
		this.hibernateTemplate.delete(department);
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.DepartmentDAO#findAll()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Department> findAll() {
		LOG.debug("finding all Department instances");
		return this.hibernateTemplate.find("from " + Department.class.getName() + " order by name");
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.DepartmentDAO#findById(java.lang.Integer )
	 */
	@Override
	public Department findById(Integer id) {
		LOG.debug("getting Department instance with id: " + id);
		Department dept = null;

		if (id != null) {
			dept = this.hibernateTemplate.get(Department.class, id);
		}
		return dept;
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.DepartmentDAO#findByName(java.lang.String )
	 */
	@Override
	public Department findByName(String name) {
		LOG.debug("finding Department instance by name: " + name);
		Department dept = null;

		if (StringUtils.hasText(name)) {
			DetachedCriteria criteria = DetachedCriteria.forClass(Department.class);
			criteria.add(Restrictions.eq("name", name));
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

			@SuppressWarnings("unchecked")
			List<Department> departments = this.hibernateTemplate.findByCriteria(criteria);

			if (null != departments && !departments.isEmpty()) {
				dept = departments.get(0);
			}
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
		List<Department> depts = Collections.EMPTY_LIST;

		if (department != null && department.getId() != null) {
			DetachedCriteria criteria = DetachedCriteria.forClass(Department.class);
			criteria.add(Restrictions.eq("parentDepartment.id", department.getId()));

			LOG.debug("finding child Departments for Department: " + department.getName());
			depts = this.hibernateTemplate.findByCriteria(criteria);
		}
		return depts;
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.DepartmentDAO#save(com.systemsinmotion .orgchart.entity.Department)
	 */
	@Override
	public Integer save(Department department) {
		LOG.debug("saving Department instance with name: " + department.getName());
		return (Integer) this.hibernateTemplate.save(department);
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
		this.hibernateTemplate.update(department);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> findAllParentDepartmentIds(){
		
		List<Integer> parentIds = new ArrayList<Integer>();
		List<Department> departments = hibernateTemplate.find("from " + Department.class.getName() + " order by name");
		
		for(int i=0; i<departments.size(); i++){
			if(departments.get(i).getParentDepartment() != null){
				parentIds.add(departments.get(i).getParentDepartment().getId());
			}
		}
		
		return parentIds;
	}
}