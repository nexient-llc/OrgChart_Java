package com.systemsinmotion.orgchart.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;

public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Configure the entity manager to be used.
	 * 
	 * @param em
	 *            the {@link EntityManager} to set.
	 */
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@Override
	public List<Employee> findActiveByFirstNameOrLastName(String name) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		EntityType<Employee> type = em.getMetamodel().entity(Employee.class);
		CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
		Root<Employee> root = query.from(Employee.class);

		query.where(buildOrPredicate_firstNameLastName(builder, root, type, name), builder.equal(root.get("isActive"), true));

		return em.createQuery(query).getResultList();
	}

	@Override
	public List<Employee> findActiveByFirstNameOrLastNameAndDepartmentId(
			String name, Integer deptId) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		EntityType<Employee> type = em.getMetamodel().entity(Employee.class);
		CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
		Root<Employee> root = query.from(Employee.class);

		query.where(buildOrPredicate_firstNameLastName(builder, root, type, name),
				buildAndPredicate_department(builder, root, type, deptId),
				builder.equal(root.get("isActive"), true));

		return em.createQuery(query).getResultList();		
	}

	@Override
	public List<Employee> findActiveByFirstNameOrLastNameAndJobTitleId(
			String name, Integer jobId) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		EntityType<Employee> type = em.getMetamodel().entity(Employee.class);
		CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
		Root<Employee> root = query.from(Employee.class);

		query.where(buildOrPredicate_firstNameLastName(builder, root, type, name),
				buildAndPredicate_jobTitle(builder, root, type, jobId),
				builder.equal(root.get("isActive"), true));

		return em.createQuery(query).getResultList();		
	}
	
	@Override
	public List<Employee> findActiveByFirstNameOrLastNameAndDepartmentIdAndJobTitleId(
			String name, Integer deptId, Integer jobId) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		EntityType<Employee> type = em.getMetamodel().entity(Employee.class);
		CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
		Root<Employee> root = query.from(Employee.class);

		query.where(buildOrPredicate_firstNameLastName(builder, root, type, name),
				buildAndPredicate_department(builder, root, type, deptId),
				buildAndPredicate_jobTitle(builder, root, type, jobId),
				builder.equal(root.get("isActive"), true));

		return em.createQuery(query).getResultList();		
	}

	private static Predicate buildAndPredicate_department(CriteriaBuilder builder, Root<Employee> root, EntityType<Employee> type,
			Integer deptId) {
		return builder.and(builder.equal(root.get("department").get("id"), deptId));
	}

	private static Predicate buildAndPredicate_jobTitle(CriteriaBuilder builder, Root<Employee> root, EntityType<Employee> type,
			Integer jobId) {
		return builder.and(builder.equal(root.get("jobTitle").get("id"), jobId));
	}

	private static Predicate buildOrPredicate_firstNameLastName(CriteriaBuilder builder, Root<Employee> root, EntityType<Employee> type,
			String name) {
		return builder.or(buildCaseInsitiveLikePredicate(builder, root, type, "firstName", name),
				buildCaseInsitiveLikePredicate(builder, root, type, "lastName", name));
	}

	private static Predicate buildCaseInsitiveLikePredicate(CriteriaBuilder builder, Root<Employee> root,
			EntityType<Employee> type, String fieldName, String fieldValue) {

		SingularAttribute<Employee, String> attribute = type.getDeclaredSingularAttribute(fieldName, String.class);

		return builder.like(builder.lower(root.get(attribute)), "%" + fieldValue.toLowerCase() + "%");
	}

}
