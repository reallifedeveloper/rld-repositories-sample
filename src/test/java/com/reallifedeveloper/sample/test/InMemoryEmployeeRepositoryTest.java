package com.reallifedeveloper.sample.test;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.reallifedeveloper.sample.domain.Department;
import com.reallifedeveloper.sample.domain.Employee;
import com.reallifedeveloper.sample.infrastructure.persistence.JpaDepartmentRepository;
import com.reallifedeveloper.sample.infrastructure.persistence.JpaEmployeeRepository;
import com.reallifedeveloper.sample.infrastructure.persistence.JpaEmployeeRepositoryIT;
import com.reallifedeveloper.tools.test.database.dbunit.DbUnitFlatXmlReader;

@RunWith(JUnit4.class)
public class InMemoryEmployeeRepositoryTest extends JpaEmployeeRepositoryIT {

    private InMemoryEmployeeRepository repository = new InMemoryEmployeeRepository();
    private InMemoryDepartmentRepository departmentRepository = new InMemoryDepartmentRepository();

    @Override
    public void setUpDatabase() throws Exception {
        DbUnitFlatXmlReader xmlReader = new DbUnitFlatXmlReader();
        xmlReader.read("/dbunit/department.xml", departmentRepository, Department.class, Long.class);
        xmlReader.read("/dbunit/employee.xml", repository, Employee.class, Long.class);
    }

    @Override
    public void tearDownDatabase() throws Exception {
        // Do nothing
    }

    @Override
    protected JpaEmployeeRepository repository() {
        return repository;
    }

    @Override
    protected JpaDepartmentRepository departmentRepository() {
        return departmentRepository;
    }
}
