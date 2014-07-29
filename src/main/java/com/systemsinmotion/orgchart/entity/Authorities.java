package com.systemsinmotion.orgchart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "AUTHORITIES")
public class Authorities {

	@Id
	@Size(min = 4, max = 50)
	@JoinColumn(name = "USERNAME", referencedColumnName = "USERNAME", unique = true, updatable = false)
	private String userName;

	@Size(min = 3, max = 50)
	@Column(name = "AUTHORITY", unique = false, updatable = true, nullable = false)
	private String authority;

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getuserName() {
		return userName;
	}

	public String getAuthority() {
		return authority;
	}
}
