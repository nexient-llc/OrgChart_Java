package com.systemsinmotion.test.Suite

import junit.framework.TestSuite

public class TestSuiteGroovy extends TestSuite {
     // Since Eclipse launches tests relative to the project root,
     // declare the relative path to the test scripts for convenience
     private static final String TEST_ROOT = "src/test/groovy/com/systemsinmotion/orgchart/";
     public static TestSuite suite() throws Exception {
         TestSuite suite = new TestSuite();
         GroovyTestSuite gsuite = new GroovyTestSuite();
         suite.addTestSuite(gsuite.compile(TEST_ROOT + "dao/DepartmentDaoTestGroovy.groovy"));
         return suite;
     }
}
