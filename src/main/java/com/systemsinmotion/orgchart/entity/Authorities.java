package com.systemsinmotion.orgchart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "AUTHORITIES")
public class Authorities {

	@Id
	@Column(name = "USERNAME", unique = true, nullable =false, length = 50)
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 45)
	private String username;
	
	@Column(name = "AUTHORITY", unique = true, nullable =false, length = 45)
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 40)
	private String authority;
	
}