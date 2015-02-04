package com.systemsinmotion.orgchart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "AUTHORITIES")
public class Authorities {

	@Id
	@Size(min = 1, max = 45)
	@PrimaryKeyJoinColumn(name = "USERNAME", referencedColumnName = "USERNAME")
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