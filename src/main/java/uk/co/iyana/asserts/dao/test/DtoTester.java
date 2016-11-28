/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.iyana.asserts.dao.test;

import org.testng.Assert;
import uk.co.iyana.asserts.testdata.CreateTestData;
import uk.co.iyana.asserts.testdata.LinkTestData;
import uk.co.iyana.springdao.ConstraintType;
import uk.co.iyana.springdao.exception.StructuredDataIntegrityViolationException;

/**
 *
 * @author fgyara
 */
public class DtoTester {
    
    public static <T> T testCreate(CreateTestData<T> testData, IDaoCreateTestHarness<T> harness) {
        T result;
        try {
            result = harness.doTest(testData);
        } catch (Exception t) {
            if (testData.getExpectException() == null) {
                Assert.fail("Unexpected exception", t);
            } else {
                // exception was expected
                Assert.assertEquals(t.getClass().getName(), testData.getExpectException(), "Incorrect exception thrown");
                
                if (t.getClass().equals( StructuredDataIntegrityViolationException.class)) {
                    String violatedConstraint = ((StructuredDataIntegrityViolationException)t).getViolatedConstraintName();
                    ConstraintType violatedConstraintType = ((StructuredDataIntegrityViolationException)t).getViolatedConstraintType();
                    
                    Assert.assertEquals( violatedConstraintType, testData.getViolatedConstraintType(), "Constraint type incorrect");
                    Assert.assertEquals( violatedConstraint, testData.getViolatedConstraintName(), "Constraint name incorrect");
                }
            }
            
            return null;
        }
        
        if (testData.getExpectException() != null) {
            // was expecting an exception
            Assert.fail("Was expecting exception " + testData.getExpectException() + " to be thrown");
        }

        // ensure that result is not null
        Assert.assertNotNull(result, "Result was null");

        // ensure that the record was created in the db
        
        T requeriedResult = harness.find(result);
        
        Assert.assertNotNull(requeriedResult, "Create did not persist a record.");
        
        return requeriedResult;
    }

    public static <K, V> void testLink(LinkTestData<K, V> testData, IDaoLinkTestHarness<K, V> harness) {
        try {
            harness.doTest(testData);
        } catch (Exception t) {
            if (testData.getExpectException() == null) {
                Assert.fail("Unexpected exception", t);
            } else {
                // exception was expected
                Assert.assertEquals(t.getClass().getName(), testData.getExpectException(), "Incorrect exception thrown");
                
                if (t.getClass().equals( StructuredDataIntegrityViolationException.class)) {
                    String violatedConstraint = ((StructuredDataIntegrityViolationException)t).getViolatedConstraintName();
                    ConstraintType violatedConstraintType = ((StructuredDataIntegrityViolationException)t).getViolatedConstraintType();
                    
                    Assert.assertEquals( violatedConstraintType, testData.getViolatedConstraintType(), "Constraint type incorrect");
                    Assert.assertEquals( violatedConstraint, testData.getViolatedConstraintName(), "Constraint name incorrect");
                }
            }
            
            return;
        }
        
        if (testData.getExpectException() != null) {
            // was expecting an exception
            Assert.fail("Was expecting exception " + testData.getExpectException() + " to be thrown");
        }

        // ensure that the record was created in the db
        Assert.assertTrue( harness.checkExists(testData), "Link did not persist a record");
    }
    
    
}
