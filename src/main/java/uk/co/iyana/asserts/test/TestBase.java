/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.iyana.asserts.test;

import java.lang.reflect.Method;
import org.testng.ITest;
import org.testng.annotations.BeforeMethod;

/**
 *
 * @author fgyara
 */
public class TestBase implements ITest {

    private String testName = "";
    private final String prefix;
    
    public TestBase(String prefix) {
        this.prefix = prefix;
    }
    
   @BeforeMethod(alwaysRun = true)
    public void testData(Method method, Object[] testData) {
        if (testData == null) {
            throw new IllegalArgumentException("Got null testData on method " + method.getName());
        } 
        
        if (testData.length == 0) {
            throw new IllegalArgumentException("Got empty testData on method " + method.getName());
        }
        
        if (testData[0] instanceof String) {
            this.testName = this.prefix + (String) testData[0];
            return;
        }
        
        if (testData[0] instanceof TestData) {
            this.testName = this.prefix + ((TestData) testData[0]).getTestName();
            return;
        }
        
        throw new IllegalArgumentException("testData type not supported " + method.getName());
    }

    @Override
    public String getTestName() {
        return this.testName;
    }    
    
}
