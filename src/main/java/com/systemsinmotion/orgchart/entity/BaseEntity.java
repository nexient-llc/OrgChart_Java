package com.systemsinmotion.orgchart.entity;

import java.io.IOException;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.google.gson.annotations.Expose;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {
	private static final long serialVersionUID = -7662658803273025261L;


	@Expose
	private Integer id;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer departmentId) {
		this.id = departmentId;
	}

	@Override
	public String toString() {
		String thisAsString = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			thisAsString = mapper.writeValueAsString(this);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return thisAsString;
	}
}
