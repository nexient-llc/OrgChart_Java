package com.systemsinmotion.orgchart.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "JOB_TITLE")
public class JobTitle {

    //@NotNull
    //@NotEmpty
    //@Size(min = 1, max = 45)
    private String name;

    private Integer Id;

    private boolean isActive;

    @Column(name = "NAME", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "IS_ACTIVE", nullable = true)
    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

}
