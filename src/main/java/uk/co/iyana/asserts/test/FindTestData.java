/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.iyana.asserts.test;

import java.util.List;
import java.util.Map;
import uk.co.iyana.asserts.test.TestData;

/**
 *
 * @author fgyara
 * @param <K>
 */
public class FindTestData<K> implements TestData {
    
    private String testName;
    private K key;
    private Map<String, ?> data;
    private List<Map<String,?>> dataList;
    
    private Map<String, String> dataClassNames;
    private String expectException;
    
    private int expectedHttpCode;
    
    private Map expectedHttpError;
    
    /**
     * @return the testName
     */
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
     * @param key the key to set
     */
    public void setKey(K key) {
        this.key = key;
    }

    /**
     * @return the data
     */
    public Map<String, ?> getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Map<String, ?> data) {
        this.data = data;
    }

    /**
     * @return the dataClassNames
     */
    public Map<String, String> getDataClassNames() {
        return dataClassNames;
    }

    /**
     * @param dataClassNames the dataClassNames to set
     */
    public void setDataClassNames(Map<String, String> dataClassNames) {
        this.dataClassNames = dataClassNames;
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
     * @return the dataList
     */
    public List<Map<String,?>> getDataList() {
        return dataList;
    }

    /**
     * @param dataList the dataList to set
     */
    public void setDataList(List<Map<String,?>> dataList) {
        this.dataList = dataList;
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
}
