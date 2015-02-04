package com.systemsinmotion.orgchart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "USERS")
public class Users {

	@Id
	@Column(name = "USERNAME", unique = true, nullable = false, length = 50)
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 45)
	private String userName;

	@Column(name = "PASSWORD", nullable = false, length = 50)
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 45)
	private String password;

	@Column(name = "ENABLED")
	private Boolean enabled;

	public void setUserName(String name) {
		this.userName = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

}