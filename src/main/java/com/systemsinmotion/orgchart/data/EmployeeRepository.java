package com.systemsinmotion.orgchart.data;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

	Employee findById(Integer id);

    Employee findByFirstName(String first);

    Employee findByLastName(String last);



}
