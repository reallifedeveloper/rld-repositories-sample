package com.reallifedeveloper.sample.infrastructure.persistence;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.sql.DataSource;

import org.dbunit.dataset.datatype.IDataTypeFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.reallifedeveloper.sample.domain.Department;
import com.reallifedeveloper.tools.test.database.dbunit.AbstractDbTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring-context-rld-repositories-sample-test.xml" })
public class JpaDepartmentRepositoryIT extends AbstractDbTest {

    @Autowired
    private JpaDepartmentRepository repository;

    @Autowired
    private DataSource ds;

    @Autowired
    private IDataTypeFactory dataTypeFactory;

    public JpaDepartmentRepositoryIT() {
        super(null, "/dbunit/rld-repositories-sample.dtd", "/dbunit/department.xml");
    }

    @Test
    public void findByExistingName() {
        Department department = repository().findByName("IT");
        assertThat(department, notNullValue());
        assertThat(department.name(), is("IT"));
        assertThat(department.id(), is(1001L));
    }

    @Test
    public void findByNonExistingName() {
        assertThat(repository().findByName("foo"), nullValue());
    }

    @Test
    public void findAll() {
        List<Department> departments = repository().findAll();
        assertThat(departments, notNullValue());
        assertThat(departments.size(), is(2));
    }

    @Test
    public void save() {
        assertThat(repository().findByName("R&D"), nullValue());
        assertThat(repository().findAll().size(), is(2));
        Department newDepartment = new Department(null, "R&D");
        ((JpaRepository<Department, Long>) repository()).save(newDepartment);
        repository().flush();
        assertThat(repository().findByName("R&D"), notNullValue());
        assertThat(repository().findAll().size(), is(3));
    }

    protected JpaDepartmentRepository repository() {
        return repository;
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
