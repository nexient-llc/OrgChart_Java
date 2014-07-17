package com.systemsinmotion.orgchart.data;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

import com.systemsinmotion.orgchart.entity.Employee;

@NoRepositoryBean
public interface EmployeeRepositoryCustom {
	List<Employee> findActiveByFirstNameOrLastName(String name);
}
