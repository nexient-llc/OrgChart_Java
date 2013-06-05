package com.systemsinmotion.orgchart.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Employee entity
 */
@Entity
@Table(name = "EMPLOYEE")
public class Employee implements java.io.Serializable {

	private static final long serialVersionUID = 6653979594581645307L;

	@NotNull
	private Integer id;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 20)
	private String firstName;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 45)
	private String lastName;

	@Size(min = 0, max = 1)
	private String middleInitial;

	@Size(min = 0, max = 100)
	private String email;

	@Size(min = 0, max = 100)
	private String skypeName;

	@NotNull
	private JobTitle jobTitle;

	@NotNull
	private Department department;

	// Constructors

	/** default constructor */
	public Employee() {

	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "FIRST_NAME", nullable = false, length = 20)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "LAST_NAME", nullable = false, length = 45)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "MIDDLE_INITIAL")
	public String getMiddleInitial() {
		return this.middleInitial;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	@Column(name = "EMAIL", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "SKYPE_NAME", length = 100)
	public String getSkypeName() {
		return this.skypeName;
	}

	public void setSkypeName(String skypeName) {
		this.skypeName = skypeName;
	}

	@OneToOne(/* cascade = CascadeType.ALL */)
	@JoinColumn(name = "JOB_TITLE_ID")
	public JobTitle getJobTitle() {
		return this.jobTitle;
	}

	public void setJobTitle(JobTitle jobTitle) {
		this.jobTitle = jobTitle;
	}

	@OneToOne(/* cascade = CascadeType.ALL */)
	@JoinColumn(name = "DEPARTMENT_ID")
	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	// Added for logging purposes
	public String PrintFullName() {
		return new StringBuilder(this.getFirstName()).append(" ").append(this.getMiddleInitial()).append(" ")
				.append(this.getLastName()).toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}