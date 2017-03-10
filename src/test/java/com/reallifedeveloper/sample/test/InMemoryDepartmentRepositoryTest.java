package com.reallifedeveloper.sample.test;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.reallifedeveloper.sample.domain.Department;
import com.reallifedeveloper.sample.infrastructure.persistence.JpaDepartmentRepository;
import com.reallifedeveloper.sample.infrastructure.persistence.JpaDepartmentRepositoryIT;
import com.reallifedeveloper.tools.test.database.dbunit.DbUnitFlatXmlReader;

@RunWith(JUnit4.class)
public class InMemoryDepartmentRepositoryTest extends JpaDepartmentRepositoryIT {

    private InMemoryDepartmentRepository repository = new InMemoryDepartmentRepository();

    @Override
    public void setUpDatabase() throws Exception {
        DbUnitFlatXmlReader xmlReader = new DbUnitFlatXmlReader();
        xmlReader.read("/dbunit/department.xml", repository, Department.class, Long.class);
    }

    @Override
    public void tearDownDatabase() throws Exception {
        // Do nothing
    }

    @Override
    protected JpaDepartmentRepository repository() {
        return repository;
    }

}
