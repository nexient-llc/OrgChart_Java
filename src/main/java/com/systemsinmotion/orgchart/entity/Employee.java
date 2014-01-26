package com.systemsinmotion.orgchart.entity;


import org.hibernate.validator.constraints.NotEmpty;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "EMPLOYEE")
public class Employee extends BaseEntity {

    private static final long serialVersionUID = 2786300800604338231L;

    private String firstName;
    private String lastName;
    private String email;
    private String skypeName;

    private Character middleInitial;

    private boolean isManager;
    private boolean isActive;

    private Department department;
    private Employee manager;
    private JobTitle jobTitle;


    @Column(name = "FIRST_NAME", nullable = false, length = 20)
    public String getFirstName() {
        return firstName;
    }

    @Column(name = "LAST_NAME", nullable = false, length = 50)
    public String getLastName() {
        return lastName;
    }

    @Column(name = "MIDDLE_INITIAL")
    public Character getMiddleInitial() {
        return middleInitial;
    }

    @Column(name = "EMAIL", unique = true)
    public String getEmail() {
        return email;
    }

    @Column(name = "SKYPE_NAME", unique = true)
    public String getSkypeName() {
        return skypeName;
    }

    @Column(name = "IS_MANAGER")
    public boolean getIsManager() {
        return isManager;
    }

    @Column(name = "IS_ACTIVE")
    public boolean getIsActive() {
        return isActive;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MANAGER_ID", referencedColumnName = "ID")
    public Employee getManager() {
        return this.manager;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "JOB_TITLE_ID", referencedColumnName = "ID")
    public JobTitle getJobTitle() {
        return this.jobTitle;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "ID")
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
    public void setManager(Employee manager) {
        this.manager = manager;
    }
    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    public void setIsManager(boolean isManager) {
        this.isManager = isManager;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setMiddleInitial(Character middleInitial) {
        this.middleInitial = middleInitial;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setSkypeName(String skypeName) {
        this.skypeName = skypeName;
    }
}
