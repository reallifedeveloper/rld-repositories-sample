package com.reallifedeveloper.sample.domain;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

public class DepartmentTest {

    @Test
    public void constructor() {
        Department department = new Department(1L, "dep");
        assertThat(department.id(), is(1L));
        assertThat(department.name(), is("dep"));
    }

    @Test
    public void noArgsConstructor() {
        Department department = new Department();
        assertThat(department.id(), nullValue());
        assertThat(department.name(), nullValue());
    }

    @Test
    public void addEmployee() {
        Department department = new Department(1L, "dep");
        assertThat(department.employees().size(), is(0));
        Employee employee = new Employee(1L, "first", "last", new BigDecimal(1000), department);
        department.addEmployee(employee);
        assertThat(department.employees().size(), is(1));
        assertThat(department.employees(), hasItem(employee));
    }

    @Test
    public void testToString() {
        Department department = new Department(1L, "dep");
        assertThat(department.toString(), is("Department{id=1, name=dep}"));
    }
}
