package com.reallifedeveloper.sample.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "department")
    private Set<Employee> employees = new HashSet<>();

    public Department(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Required by JPA.
    Department() {
    }

    public Long id() {
        return id;
    }

    public String name() {
        return name;
    }

    public Set<Employee> employees() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    @Override
    public String toString() {
        return "Department{id=" + id + ", name=" + name + "}";
    }

}
