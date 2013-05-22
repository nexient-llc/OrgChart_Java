package com.systemsinmotion.orgchart.entity;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import org.junit.Test;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runner.RunWith;

@RunWith(Parameterized.class)
public class EmployeeParamsTest {
	
	// parameterized fields
	private Integer genericId;
	private String  genericName;
	
	// static data
	public static final String emptyName = "";
	public static final String oneCharName = "e";
	public static final String shortName = "stuff";
	public static final String longName = "asodijflaksjdflajsklf"; //21 chars long
	
	public static final Integer zeroId = 0;
	public static final Integer smallId = 1;
	public static final Integer negaId = -1;
	public static final Integer hugeId = 2147483647; //max Int size
	
	// for generating random data
	private static Random r = new Random();
	
	// used by the parameterized runner to load each set of data
	public EmployeeParamsTest(Integer genericId, String genericName) {
		this.genericId = genericId;
		this.genericName = genericName;
	}
	
	@Parameters
	public static Collection<Object[]> generateData()
	{
		// data
		return Arrays.asList(new Object[][] {
			{ zeroId,  emptyName   },
			{ smallId, oneCharName },
			{ negaId,  shortName   },
			{ hugeId,  longName    },
			{ r.nextInt(), emptyName+r.nextInt() }
		});
	}
	
	// finally the actually tests
	@Test
	public void set_get_id() {
		Employee emp = new Employee();
		emp.setId(genericId);
		assertTrue(emp.getId().equals(genericId));
	}
	
	@Test
	public void set_get_first_name() {
		Employee emp = new Employee();
		emp.setFirstName(genericName);
		assertTrue(emp.getFirstName().equals(genericName));
	}
	
	@Test
	public void set_get_last_name() {
		Employee emp = new Employee();
		emp.setLastName(genericName);
		assertTrue(emp.getLastName().equals(genericName));
	}
	
	@Test
	public void set_get_middle_initial() {
		Employee emp = new Employee();
		emp.setMiddleInitial(genericName);
		assertTrue(emp.getMiddleInitial().equals(genericName));
	}
	
	@Test
	public void set_get_email() {
		Employee emp = new Employee();
		emp.setEmail(genericName);
		assertTrue(emp.getEmail().equals(genericName));
	}
	
	@Test
	public void set_get_skype_name() {
		Employee emp = new Employee();
		emp.setSkypeName(genericName);
		assertTrue(emp.getSkypeName().equals(genericName));
	}
	
}
