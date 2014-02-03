package com.systemsinmotion.orgchart.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.entity.Department;

public class MockDepartmentRepository implements DepartmentRepository {

	private Department department;
	private List<Department> depts = new ArrayList<Department>();

	public MockDepartmentRepository() {
		this.department = Entities.department();
		this.department.setId(Entities.DEPT_ID);
		this.depts.add(department);
	}

	@Override
	public List<Department> findAll() {
		return depts;
	}

	@Override
	public List<Department> findAll(Sort sort) {
		return depts;
	}

	@Override
	public List<Department> findAll(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Department> List<S> save(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}

	@Override
	public Department saveAndFlush(Department entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<Department> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub

	}

	@Override
	public Department getOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Department> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <S extends Department> S save(S entity) {
		return (S) this.department;
	}

	@Override
	public Department findOne(Integer id) {
		return this.department;
	}

	@Override
	public boolean exists(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Department entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Iterable<? extends Department> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public Department findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Department> findByParentDepartmentId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
