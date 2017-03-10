package com.reallifedeveloper.sample.test;

import com.reallifedeveloper.sample.domain.Department;
import com.reallifedeveloper.sample.infrastructure.persistence.JpaDepartmentRepository;
import com.reallifedeveloper.tools.test.database.inmemory.InMemoryJpaRepository;
import com.reallifedeveloper.tools.test.database.inmemory.LongPrimaryKeyGenerator;

public class InMemoryDepartmentRepository extends InMemoryJpaRepository<Department, Long>
        implements JpaDepartmentRepository {

    public InMemoryDepartmentRepository() {
        super(new LongPrimaryKeyGenerator());
    }

    @Override
    public Department findByName(String name) {
        return findByUniqueField("name", name);
    }

}
