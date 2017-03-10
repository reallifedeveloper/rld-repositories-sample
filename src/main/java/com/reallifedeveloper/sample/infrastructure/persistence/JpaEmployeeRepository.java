package com.reallifedeveloper.sample.infrastructure.persistence;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.reallifedeveloper.sample.domain.Employee;
import com.reallifedeveloper.sample.domain.EmployeeRepository;

public interface JpaEmployeeRepository extends EmployeeRepository, JpaRepository<Employee, Long> {

    @Override
    @Query("select emp from Employee emp where emp.salary >= :salary")
    List<Employee> findEmployeesWithSalaryAtLeast(@Param("salary") BigDecimal salary);

}
