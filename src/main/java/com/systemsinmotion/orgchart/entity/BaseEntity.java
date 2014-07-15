package com.systemsinmotion.orgchart.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.codehaus.jackson.map.ObjectMapper;

@MappedSuperclass
public abstract class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false)
	private Integer id;

	@Column(name="IS_ACTIVE", columnDefinition = "boolean default true")
	private Boolean isActive = true;

	public Integer getId() {
		return this.id;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
    public boolean isActive() {
		return isActive;
	}
	@Override
	public String toString() {
		String thisAsString = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			thisAsString = mapper.writeValueAsString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return thisAsString;
	}
}
