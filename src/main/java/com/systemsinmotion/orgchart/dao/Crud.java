/*
// Eventually this will be a generic Entity to handle CRUD instead of 
// repeating the code in DepartmentDao, EmployeeDao, and JobTitleDao.

import org.springframework.beans.factory.annotation.Autowired;
 

import org.springframework.orm.hibernate3.HibernateTemplate;


public class Crud (CrudEntity crudentity) {
	
	@Autowired
	HibernateTemplate hibernateTemplate;

	@Override
	public Integer save(CrudEntity crudentity) {
		return (Integer) this.hibernateTemplate.save(department);
	}

	@Override
	public void update(CrudEntity crudentity) {
		this.hibernateTemplate.update(crudentity);
	}
	
	@Override
	public void delete(CrudEntity crudentity) {
		LOG.debug("deleting Department instance with name: " + department.getName());
		this.hibernateTemplate.delete(department);
	}
}*/