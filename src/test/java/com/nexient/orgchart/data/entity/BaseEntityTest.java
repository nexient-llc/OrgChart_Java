package com.nexient.orgchart.data.entity;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import static org.testng.Assert.assertEquals;

/**
 * Created by kskronek on 6/9/2016.
 */
public class BaseEntityTest {

	private BaseEntity baseEntity;

	private Set<Employee> employees = new TreeSet<Employee>();

	private static final Random RANDOM = new Random();

	@BeforeClass
	public void beforeClass() {
		baseEntity = new BaseEntity();
		employees = Entities.employees(2);
	}

	@Test
	public void setAndGetId() {
		final int id = RANDOM.nextInt();
		baseEntity.setId(id);
		assertEquals(id, Math.toIntExact(baseEntity.getId()));
	}
}
