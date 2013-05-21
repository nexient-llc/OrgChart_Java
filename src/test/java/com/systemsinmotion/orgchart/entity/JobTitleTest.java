package com.systemsinmotion.orgchart.entity;

import static org.junit.Assert.*;
import java.util.Random;
import org.junit.Test;

public class JobTitleTest {
	public static final String nullName = null;
	public static final String emptyName = "";
	public static final String oneCharName = "e";
	public static final String shortName = "stuff";
	public static final String longName = "asodijflaksjdflajsklf"; //21 chars long
	
	public static final Integer nullId = null;
	public static final Integer zeroId = 0;
	public static final Integer smallId = 8;
	public static final Integer hugeId = 2147483647; //max Int size
	
	private static Random r = new Random();
	
	@Test
	public void empty_jobTitle_is_empty() {
		JobTitle job = new JobTitle();
		assertNull(job.getId());
		assertNull(job.getName());
	}
	
	// All these set_get_name_* test should be using @Parameters, or something similar.
	// However, as far as I can tell JUnit requires multiple test class to do
	// parameterized tests.
	@Test
	public void set_get_name_null() {
		JobTitle job = new JobTitle();
		job.setName(nullName);
		assertTrue(job.getName() == null);
	}

	@Test
	public void set_get_name_empty() {
		JobTitle job = new JobTitle();
		job.setName(emptyName);
		assertTrue(job.getName().equals(emptyName));
	}
	
	@Test
	public void set_get_name_onechar() {
		JobTitle job = new JobTitle();
		job.setName(oneCharName);
		assertTrue(job.getName().equals(oneCharName));
	}
	
	@Test
	public void set_get_name_short() {
		JobTitle job = new JobTitle();
		job.setName(shortName);
		assertTrue(job.getName().equals(shortName));
	}
	
	@Test
	public void set_get_name_long() {
		JobTitle job = new JobTitle();
		job.setName(longName);
		assertTrue(job.getName().equals(longName));
	}
	
	@Test
	public void set_get_name_random() {
		JobTitle job = new JobTitle();
		String randomName = emptyName + r.nextInt();
		job.setName(randomName);
		assertTrue(job.getName().equals(randomName));
	}
	
	// Ditto re: params for set_get_ids...
	@Test
	public void set_get_id_null() {
		JobTitle job = new JobTitle();
		job.setId(nullId);
		assertTrue(job.getId() == null);
	}
	
	@Test
	public void set_get_id_zero() {
		JobTitle job = new JobTitle();
		job.setId(zeroId);
		assertTrue(job.getId().equals(zeroId));
	}
	
	@Test
	public void set_get_id_small() {
		JobTitle job = new JobTitle();
		job.setId(smallId);
		assertTrue(job.getId().equals(smallId));
	}
	
	@Test
	public void set_get_id_huge() {
		JobTitle job = new JobTitle();
		job.setId(hugeId);
		assertTrue(job.getId().equals(hugeId));
	}
	
	@Test
	public void set_get_id_random() {
		JobTitle job = new JobTitle();
		Integer randomInt = r.nextInt();
		job.setId(randomInt);
		assertTrue(job.getId().equals(randomInt));
	}
	
}