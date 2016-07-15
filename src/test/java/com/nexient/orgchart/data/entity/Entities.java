package com.nexient.orgchart.data.entity;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * Created by kskronek on 5/25/2016.
 */
public class Entities {

	public static Department department() {
		return new Department.Builder(RandomStringUtils.randomAlphabetic(10)).build();
	}

	public static Set<Employee> employees(int count) {
		Set<Employee> emps = new HashSet<>();
		IntStream.range(0, 10).forEach(n -> emps.add(employee()));
		return emps;
	}

	public static Employee employee() {
		return new Employee.Builder(RandomStringUtils.randomAlphabetic(5), RandomStringUtils.randomAlphabetic(8))
				.build();
	}
}

