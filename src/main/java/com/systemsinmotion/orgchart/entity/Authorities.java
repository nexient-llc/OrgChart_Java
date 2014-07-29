package com.systemsinmotion.orgchart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.codehaus.jackson.map.ObjectMapper;

@Entity
@Table(name = "AUTHORITIES")
public class Authorities {

	@Id
	@PrimaryKeyJoinColumn(name = "USERNAME", referencedColumnName = "USERNAME")
	private String userName;
	
	@Column(name = "AUTHORITY", nullable = false, length = 45)
	@Size(min = 1, max = 44)
	private String authority;

	public String getUserName() {
		return userName;
	}
	
	public String getAuthority() {
		return authority;
	}
	
	public void setUserName(String user) {
		this.userName = user;
	}
	
	public void setAuthority(String authority) {
		this.authority = authority;
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
