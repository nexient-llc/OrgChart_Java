package com.systemsinmotion.orgchart.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
	
	@Test
	public void equals_sameObj() {
		assertTrue(baseEntity.equals(baseEntity));
	}
	
	@Test
	public void equals_sameId() {
		BaseEntity newBaseEntity = new TestingBaseEntity();
		assertNotNull(newBaseEntity);
		
		newBaseEntity.setId(baseEntity.getId());
		assertTrue(baseEntity.equals(newBaseEntity));
	}
	
	@Test
	public void equals_differentId() {
		BaseEntity newBaseEntity = new TestingBaseEntity();
		assertNotNull(newBaseEntity);
		
		newBaseEntity.setId(baseEntity.getId() + 1);
		assertFalse(baseEntity.equals(newBaseEntity));
	}
	
	@Test
	public void equals_bothIdNull() {
		BaseEntity newBaseEntity = new TestingBaseEntity();
		assertNotNull(newBaseEntity);
		baseEntity.setId(null);
		newBaseEntity.setId(null);
		assertTrue(baseEntity.equals(newBaseEntity));
	}

	@Test
	public void equals_FirstIdNull() {
		BaseEntity newBaseEntity = new TestingBaseEntity();
		assertNotNull(newBaseEntity);
		baseEntity.setId(null);
		newBaseEntity.setId(random.nextInt());
		assertFalse(baseEntity.equals(newBaseEntity));
	}

	@Test
	public void equals_SecondIdNull() {
		BaseEntity newBaseEntity = new TestingBaseEntity();
		assertNotNull(newBaseEntity);
		newBaseEntity.setId(null);
		assertFalse(baseEntity.equals(newBaseEntity));
	}

	@Test
	public void equals_notAnEmployee() {
		String str = new String();
		assertNotNull(str);
		assertFalse(baseEntity.equals(str));
	}
}
