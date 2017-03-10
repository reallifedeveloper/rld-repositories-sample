package com.reallifedeveloper.sample.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "salary", nullable = false)
    private BigDecimal salary;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    public Employee(Long id, String firstName, String lastName, BigDecimal salary, Department department) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.department = department;
    }

    // Required by JPA.
    Employee() {
    }

    public Long id() {
        return id;
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public BigDecimal salary() {
        return salary;
    }

    public Department department() {
        return department;
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", salary=" + salary
                + ", department=" + department.name() + "}";
    }

}
