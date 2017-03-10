package com.reallifedeveloper.sample.domain;

import java.util.List;

public interface DepartmentRepository {

    Department findByName(String name);

    List<Department> findAll();

    <T extends Department> T save(T department);

}
