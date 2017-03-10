package com.reallifedeveloper.sample.domain;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeRepository {

    Employee findById(Long id);

    List<Employee> findByLastName(String lastName);

    List<Employee> findEmployeesWithSalaryAtLeast(BigDecimal salary);

    <T extends Employee> T save(T employee);

}
