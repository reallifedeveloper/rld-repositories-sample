package com.reallifedeveloper.sample.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.hamcrest.Matchers;
import org.junit.Test;

public class EmployeeTest {

    private static final BigDecimal DELTA = new BigDecimal(0.0000001);

    @Test
    public void constructorWithoutDepartment() {
        Employee employee = new Employee(1L, "first", "last", new BigDecimal(1000), null);
        assertThat(employee.id(), is(1L));
        assertThat(employee.firstName(), is("first"));
        assertThat(employee.lastName(), is("last"));
        assertThat(employee.salary(), Matchers.closeTo(new BigDecimal(1000), DELTA));
        assertThat(employee.department(), nullValue());
    }

    @Test
    public void noArgsConstructor() {
        Employee employee = new Employee();
        assertThat(employee.id(), nullValue());
        assertThat(employee.firstName(), nullValue());
        assertThat(employee.lastName(), nullValue());
        assertThat(employee.salary(), nullValue());
        assertThat(employee.department(), nullValue());
    }

    @Test
    public void testToString() {
        Department department = new Department(1L, "dep");
        Employee employee = new Employee(1L, "first", "last", new BigDecimal(1000), department);
        assertThat(employee.toString(),
                is("Employee{id=1, firstName=first, lastName=last, salary=1000, department=dep}"));
    }
}
