package com.systemsinmotion.orgchart.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.systemsinmotion.orgchart.entity.SimpleEmployee;

public interface SimpleEmployeeRepository extends JpaRepository<SimpleEmployee, Integer> {
	SimpleEmployee findById(Integer id);
	List<SimpleEmployee> findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(String firstName, String lastName);
	List<SimpleEmployee> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndIsActiveIsTrue(String firstName, String lastName);
}
