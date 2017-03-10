package com.reallifedeveloper.sample.infrastructure.persistence;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.List;

import javax.sql.DataSource;

import org.dbunit.dataset.datatype.IDataTypeFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.reallifedeveloper.sample.domain.Department;
import com.reallifedeveloper.sample.domain.Employee;
import com.reallifedeveloper.tools.test.database.dbunit.AbstractDbTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring-context-rld-repositories-sample-test.xml" })
public class JpaEmployeeRepositoryIT extends AbstractDbTest {

    private static final BigDecimal DELTA = new BigDecimal(0.0000001);

    @Autowired
    private JpaEmployeeRepository repository;

    @Autowired
    private JpaDepartmentRepository departmentRepository;

    @Autowired
    private DataSource ds;

    @Autowired
    private IDataTypeFactory dataTypeFactory;

    private Department departmentSales;

    public JpaEmployeeRepositoryIT() {
        super(null, "/dbunit/rld-repositories-sample.dtd", "/dbunit/department.xml", "/dbunit/employee.xml");
    }

    @Before
    public void init() {
        departmentSales = departmentRepository().findByName("Sales");
        if (departmentSales == null) {
            fail("Error in test data: department 'Sales' not found");
        }
    }

    @Test
    public void findByExistingId() {
        Employee employee = repository().findById(1001L);
        assertThat(employee, notNullValue());
        assertThat(employee.firstName(), is("Jack"));
        assertThat(employee.lastName(), is("Bauer"));
        assertThat(employee.salary(), closeTo(new BigDecimal(100000), DELTA));
        assertThat(employee.department().name(), is("IT"));
    }

    @Test
    public void findByNonExistingId() {
        assertThat(repository().findById(-1L), nullValue());
    }

    @Test
    public void findByExistingLastName() {
        List<Employee> employees = repository().findByLastName("Bauer");
        assertThat(employees, notNullValue());
        assertThat(employees.size(), is(2));
    }

    @Test
    public void findByNonExistingLastName() {
        List<Employee> employees = repository().findByLastName("Kennedy");
        assertThat(employees, notNullValue());
        assertThat(employees.size(), is(0));
    }

    @Test
    public void findEmployeesWithSalariesAtLeast() {
        List<Employee> employees = repository().findEmployeesWithSalaryAtLeast(new BigDecimal(120000));
        assertThat(employees, notNullValue());
        assertThat(employees.size(), is(2));
    }

    @Test
    public void save() {
        assertThat(repository().findAll().size(), is(5));
        assertThat(repository().findByLastName("Kennedey").size(), is(0));
        Employee newEmployee = new Employee(null, "John F.", "Kennedy", new BigDecimal(200000), departmentSales);
        ((JpaRepository<Employee, Long>) repository()).save(newEmployee);
        repository().flush();
        assertThat(repository().findAll().size(), is(6));
        assertThat(repository().findByLastName("Kennedy").size(), is(1));
    }

    protected JpaEmployeeRepository repository() {
        return repository;
    }

    protected JpaDepartmentRepository departmentRepository() {
        return departmentRepository;
    }

    @Override
    protected DataSource getDataSource() {
        return ds;
    }

    @Override
    protected IDataTypeFactory getDataTypeFactory() {
        return dataTypeFactory;
    }

}
