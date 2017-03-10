package com.reallifedeveloper.sample.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.reallifedeveloper.sample.domain.Employee;
import com.reallifedeveloper.sample.infrastructure.persistence.JpaEmployeeRepository;
import com.reallifedeveloper.tools.test.database.inmemory.InMemoryJpaRepository;
import com.reallifedeveloper.tools.test.database.inmemory.LongPrimaryKeyGenerator;

public class InMemoryEmployeeRepository extends InMemoryJpaRepository<Employee, Long>
        implements JpaEmployeeRepository {

    public InMemoryEmployeeRepository() {
        super(new LongPrimaryKeyGenerator());
    }

    @Override
    public Employee findById(Long id) {
        return findByUniqueField("id", id);
    }

    @Override
    public List<Employee> findByLastName(String lastName) {
        return findByField("lastName", lastName);
    }

    @Override
    public List<Employee> findEmployeesWithSalaryAtLeast(BigDecimal salary) {
        List<Employee> employeesWithSalaryAtLeast = new ArrayList<>();
        for (Employee employee : findAll()) {
            if (employee.salary().compareTo(salary) >= 0) {
                employeesWithSalaryAtLeast.add(employee);
            }
        }
        return employeesWithSalaryAtLeast;
    }

}
