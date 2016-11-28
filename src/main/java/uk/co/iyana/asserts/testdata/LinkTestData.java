/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.iyana.asserts.testdata;

import java.util.Map;
import uk.co.iyana.asserts.test.TestData;
import uk.co.iyana.springdao.ConstraintType;

/**
 *
 * @author fgyara
 * @param <K>
 */
public class LinkTestData<K, V> implements TestData {
    
    private String testName;
    private K key;
    private V linkedValue;
    
    private String expectException;
    private String violatedConstraintName;
    private ConstraintType violatedConstraintType;
    
    private int expectedHttpCode;
    private Map expectedHttpError;
    
    /**
     * @return the testName
     */
    @Override
    public String getTestName() {
        return testName;
    }

    /**
     * @param testName the testName to set
     */
    public void setTestName(String testName) {
        this.testName = testName;
    }

    /**
     * @return the key
     */
    public K getKey() {
        return key;
    }

    /**
     * @param key the key set
     */
    public void setKey(K key) {
        this.key = key;
    }

    /**
     * @return the expectException
     */
    public String getExpectException() {
        return expectException;
    }

    /**
     * @param expectException the expectException to set
     */
    public void setExpectException(String expectException) {
        this.expectException = expectException;
    }


    /**
     * @return the expectedHttpCode
     */
    public int getExpectedHttpCode() {
        return expectedHttpCode;
    }

    /**
     * @param expectedHttpCode the expectedHttpCode to set
     */
    public void setExpectedHttpCode(int expectedHttpCode) {
        this.expectedHttpCode = expectedHttpCode;
    }

    /**
     * @return the expectedHttpError
     */
    public Map getExpectedHttpError() {
        return expectedHttpError;
    }

    /**
     * @param expectedHttpError the expectedHttpError to set
     */
    public void setExpectedHttpError(Map expectedHttpError) {
        this.expectedHttpError = expectedHttpError;
    }

    /**
     * @return the violatedConstraintName
     */
    public String getViolatedConstraintName() {
        return violatedConstraintName;
    }

    /**
     * @param violatedConstraintName the violatedConstraintName to set
     */
    public void setViolatedConstraintName(String violatedConstraintName) {
        this.violatedConstraintName = violatedConstraintName;
    }

    /**
     * @return the violatedConstraintType
     */
    public ConstraintType getViolatedConstraintType() {
        return violatedConstraintType;
    }

    /**
     * @param violatedConstraintType the violatedConstraintType to set
     */
    public void setViolatedConstraintType(ConstraintType violatedConstraintType) {
        this.violatedConstraintType = violatedConstraintType;
    }

    /**
     * @return the linkedValue
     */
    public V getLinkedValue() {
        return linkedValue;
    }

    /**
     * @param linkedValue the linkedValue to set
     */
    public void setLinkedValue(V linkedValue) {
        this.linkedValue = linkedValue;
    }
}
