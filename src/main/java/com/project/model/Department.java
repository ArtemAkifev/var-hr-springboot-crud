package com.project.model;

import javax.persistence.*;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long departmentId;
    private String name_departments;

    public long getDepartment_id() {
        return departmentId;
    }

    public String getName_departments() {
        return name_departments;
    }

    public void setName_departments(String name_departments) {
        this.name_departments = name_departments;
    }

    public Department(){

    }

    public Department(String name_departments) {
        this.name_departments = name_departments;
    }
}
