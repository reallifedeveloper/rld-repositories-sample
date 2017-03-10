package com.reallifedeveloper.sample.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reallifedeveloper.sample.domain.Department;
import com.reallifedeveloper.sample.domain.DepartmentRepository;

public interface JpaDepartmentRepository extends DepartmentRepository, JpaRepository<Department, Long> {

}
