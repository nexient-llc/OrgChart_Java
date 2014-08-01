package com.systemsinmotion.orgchart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "EMPLOYEE")
public class SimpleEmployee extends BaseEntity {
	@Column(name = "FIRST_NAME", nullable = false, length = 20)
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 19)
	private String firstName;
	
	@Column(name = "LAST_NAME", nullable = false, length = 50)
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 45)
	private String lastName;
	
	@Column(name = "MIDDLE_INITIAL", length = 1)
	private Character midInitial;

	public void setFirstName(String string) {
		this.firstName = string;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setLastName(String string) {
		this.lastName = string;		
	}

	public String getLastName() {
		return lastName;
	}
	
	public void setMiddleInitial(Character mid) {
		this.midInitial = mid;		
	}

	public Character getMiddleInitial() {
		return midInitial;
	}

	public String getFullName() {
		String fullName = "";
		if (firstName != null)
		{
			fullName += firstName;
		}
		if (midInitial != null)
		{
			fullName += " " + midInitial;
		}
		if (lastName != null)
		{
			fullName += " " + lastName;
		}
		
		return fullName.trim();
	}

}
