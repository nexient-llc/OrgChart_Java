package com.systemsinmotion.orgchart.entity;

import static org.junit.Assert.*;
import java.util.Random;
import org.junit.Test;

public class DepartmentTest {

	public static final String nullName = null;
	public static final String emptyName = "";
	public static final String oneCharName = "e";
	public static final String shortName = "stuff";
	public static final String longName = "asodijflaksjdflajsklfjasjfksjkdfjskjdfkjsdfsd"; //46chars long
	
	public static final Integer nullId = null;
	public static final Integer zeroId = 0;
	public static final Integer smallId = 8;
	public static final Integer hugeId = 2147483647;
	
	private static Random r = new Random();
	
	@Test
	public void empty_department_is_empty() {
		Department dep = new Department();
		assertNull(dep.getId());
		assertNull(dep.getParentDepartment());
		assertNull(dep.getName());
	}
	
	// All these set_get_name_* test should be using @Parameters, or something similar.
	// However, as far as I can tell JUnit requires multiple test class to do
	// parameterized tests.
	@Test
	public void set_get_name_null() {
		Department dep = new Department();
		dep.setName(nullName);
		assertTrue(dep.getName() == null);
	}

	@Test
	public void set_get_name_empty() {
		Department dep = new Department();
		dep.setName(emptyName);
		assertTrue(dep.getName().equals(emptyName));
	}
	
	@Test
	public void set_get_name_onechar() {
		Department dep = new Department();
		dep.setName(oneCharName);
		assertTrue(dep.getName().equals(oneCharName));
	}
	
	@Test
	public void set_get_name_short() {
		Department dep = new Department();
		dep.setName(shortName);
		assertTrue(dep.getName().equals(shortName));
	}
	
	@Test
	public void set_get_name_long() {
		Department dep = new Department();
		dep.setName(longName);
		assertTrue(dep.getName().equals(longName));
	}
	
	@Test
	public void set_get_name_random() {
		Department dep = new Department();
		String randomName = emptyName + r.nextInt();
		dep.setName(randomName);
		assertTrue(dep.getName().equals(randomName));
	}
	
	// Ditto re: params for set_get_ids...
	@Test
	public void set_get_id_null() {
		Department dep = new Department();
		dep.setId(nullId);
		assertTrue(dep.getId() == null);
	}
	
	@Test
	public void set_get_id_zero() {
		Department dep = new Department();
		dep.setId(zeroId);
		assertTrue(dep.getId().equals(zeroId));
	}
	
	@Test
	public void set_get_id_small() {
		Department dep = new Department();
		dep.setId(smallId);
		assertTrue(dep.getId().equals(smallId));
	}
	
	@Test
	public void set_get_id_huge() {
		Department dep = new Department();
		dep.setId(hugeId);
		assertTrue(dep.getId().equals(hugeId));
	}
	
	@Test
	public void set_get_id_random() {
		Department dep = new Department();
		Integer randomInt = r.nextInt();
		dep.setId(randomInt);
		assertTrue(dep.getId().equals(randomInt));
	}
	
	@Test
	public void set_get_parent_null() {
		Department dep = new Department();
		dep.setParentDepartment(null);
		assertTrue(dep.getParentDepartment() == null);
	}
	
	@Test
	public void set_get_parent_empty() {
		Department dep = new Department();
		dep.setParentDepartment(new Department());
		assertTrue(dep.getParentDepartment().getId() == null);
		assertTrue(dep.getParentDepartment().getName() == null);
	}
	
	@Test
	public void set_get_parent_random() {
		Department depParent = new Department();
		Integer randomDepId = r.nextInt();
		depParent.setId(randomDepId);
		String randomDepName = emptyName + r.nextInt();
		depParent.setName(randomDepName);
		
		Department depChild = new Department();
		depChild.setParentDepartment(depParent);
		assertTrue(depChild.getParentDepartment().getId().equals(randomDepId));
		assertTrue(depChild.getParentDepartment().getName().equals(randomDepName));
	}
	
}
