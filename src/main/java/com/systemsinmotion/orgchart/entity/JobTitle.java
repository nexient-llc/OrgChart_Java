package com.systemsinmotion.orgchart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "JOB_TITLE")
public class JobTitle extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6905723931037952963L;
	
	private String name;
	private String description;
	
	public void setName( String newName){
		this.name = newName;
	}

	@Column(name = "NAME", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}
	
	@Column(name = "DESCRIPTION", length = 120)
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription( String descript ){
//		if(descript != null){
//			//DO NOTHING
//		}
//		else if(descript.length() > 120) {
//			descript = descript.substring(0, 120);
//		}
		this.description = descript;
	}
	
	

}
