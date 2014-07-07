package com.systemsinmotion.orgchart.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class BaseEntityTest {
	Integer id;
	BaseEntity baseEntity = new TestingBaseEntity();
	Random random = new Random();

	@Before
	public void before() {
		id = random.nextInt();
		baseEntity.setId(id);
	}

	@Test
	public void setAndGetId() {
		assertNotNull(baseEntity.getId());
		assertEquals(id, baseEntity.getId());
	}

	@Test
	public void toStringTest() {
		assertNotNull(baseEntity.toString());
	}

	class TestingBaseEntity extends BaseEntity {
	}
	
	
	@Test
	public void setAndGetIsActive(){
		this.baseEntity.setIsActive(false);
		assertNotNull(this.baseEntity.getIsActive());
		assertTrue(baseEntity.getIsActive()==false);
	}
}
