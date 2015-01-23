package com.systemsinmotion.orgchart.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class JobTitleTest {
	private static final String NEW_NAME = "new name";
	JobTitle jobTitle;

	@Before
	public void before() {
		this.jobTitle = new JobTitle();
	}

	@Test
	public void instantiated() {
		assertNotNull(jobTitle);
	}
	
	@Test
	public void setAndGetName() {
		jobTitle.setName(NEW_NAME);
		String name = jobTitle.getName();
		assertNotNull(name);
		assertEquals(NEW_NAME, name);
	}
}
