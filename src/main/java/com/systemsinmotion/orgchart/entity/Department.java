package com.systemsinmotion.orgchart.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.persister.walking.internal.FetchStrategyHelper;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.jdbc.support.lob.TemporaryLobCreator;

@Entity
@Table(name = "DEPARTMENT")
public class Department extends BaseEntity {

    private static final long serialVersionUID = -5379179412533671591L;

    private Set<Department> childDepartments = new HashSet<Department>(0);
    private Set<Employee> employees = new HashSet<Employee>();

    private String name;

    private Department parentDepartment;
    private Employee manager;

    private boolean isActive;

    @Column(name = "IS_ACTIVE", nullable = true)
    public boolean getIsActive() {
        return isActive;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parentDepartment")
    public Set<Department> getChildDepartments() {
        return this.childDepartments;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "department")
    public Set<Employee> getEmployees() {
        return this.employees;
    }

    @Column(name = "NAME", nullable = false, length = 50)
    public String getName() {
        return this.name;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PARENT_DEPARTMENT_ID", referencedColumnName = "ID")
    public Department getParentDepartment() {
        return this.parentDepartment;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MANAGER_ID", referencedColumnName = "ID")
    public Employee getManager() {
        return this.manager;
    }

    public void setManager(Employee deptManager) {
        this.manager = deptManager;
    }
    public void setChildDepartments(Set<Department> departments) {
        this.childDepartments = departments;
    }
    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setParentDepartment(Department department) {
        this.parentDepartment = department;
    }
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }


}