package com.reallifedeveloper.sample.infrastructure.persistence;

import org.junit.Test;

import com.reallifedeveloper.tools.test.database.dbunit.DbUnitDtdGenerator;

/**
 * A simple way to generate tht DbUnit DTD file describing the contents of the DbUnit XML files.
 * <p>
 * This is not a testcase, but is written as one to make it easy to run. Just right-click on the test
 * and run it as a Junit test, and the DTD is written on stdout.
 *
 * @author RealLifeDeveloper
 */
public class DbUnitDtdGeneratorIT {

    @Test
    public void generateDtd() throws Exception {
        DbUnitDtdGenerator dtdGenerator =
                new DbUnitDtdGenerator("/META-INF/spring-context-rld-repositories-sample.xml");
        // CHECKSTYLE:OFF
        System.out.println(dtdGenerator.generateDtd());
        // CHECKSTYLE:ON
    }
}
