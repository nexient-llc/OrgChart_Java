package com.nexient.orgchart.data.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by kskronek on 5/24/2016.
 */
public class BaseEntity {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "DEPARTMENT_ID", unique = true, nullable = false)
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
