/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.iyana.asserts.service.test;

import org.testng.Assert;
import uk.co.iyana.asserts.test.Assert2;
import uk.co.iyana.asserts.testdata.CreateTestData;
import uk.co.iyana.commons.usererror.UserErrorException;
import uk.co.iyana.commons.usererror.UserErrors;
import uk.co.iyana.json.io.JsonHelper;

/**
 *
 * @author fgyara
 */
public class ServiceTester {
    
    public static <T> T testCreate(CreateTestData<T> testData, IServiceCreateTestHarness<T> harness) {
        
        System.out.println( "Test " + testData.getTestName() + " started");
        T result;
        try {
            result = harness.doTest(testData);
            System.out.println( "Test " + testData.getTestName() + " completed without exception");
            System.out.println( "\tResult: " );
            JsonHelper.write(result).to(System.out);
        } catch (Exception t) {
            System.out.println( "Test " + testData.getTestName() + " completed with exception " + t.getClass() + "(" + t.getMessage() + ")");
            if (t.getClass().equals( UserErrorException.class)) {
                System.out.println( "\tUserException:");
                JsonHelper.write(t).to(System.out);
            }
            
            if (testData.getExpectServiceException() == null) {
                Assert.fail("Unexpected exception", t);
            } else {
                // exception was expected
                Assert.assertEquals(t.getClass().getName(), testData.getExpectServiceException(), "Incorrect exception thrown");
                
                if (t.getClass().equals( UserErrorException.class)) {
                    
                    UserErrors expectedUserErrors = testData.getExpectUserErrors();
                    UserErrors actualUserErrors = ((UserErrorException)t).getUserErrors();
                    Assert2.assertEquals( actualUserErrors, expectedUserErrors, "Incorrect user errors");
                }
            }
            
            return null;
        }
        
        if (testData.getExpectServiceException() != null) {
            // was expecting an exception
            Assert.fail("Was expecting exception " + testData.getExpectException() + " to be thrown");
        }

        // ensure that result is not null
        Assert.assertNotNull(result, "Result was null");

        // ensure that the record was created in the db
        T requeriedResult = harness.find(result, testData);
        
        Assert.assertNotNull(requeriedResult, "Create did not persist a record.");
        
        return requeriedResult;
    }

    
}
