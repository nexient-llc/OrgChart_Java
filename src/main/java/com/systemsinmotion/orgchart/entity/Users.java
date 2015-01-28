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
	@Column(name = "USERNAME", unique = true, nullable =false, length = 50)
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 45)
	private String username;
	
	@Column(name = "PASSWORD", nullable =false, length = 50)
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 45)
	private String password;
	
	@Column(name = "enabled", nullable =false)
	@NotNull
	@NotEmpty
	private boolean enabled;
	
	
}