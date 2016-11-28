/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.iyana.asserts.html.dto;

import uk.co.iyana.asserts.test.TestData;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author fgyara
 */
public class PageTestData implements TestData {
    private String testName;
    private Page page;
    private final List<AssertElement> assertElements = new ArrayList<>();
    private final List<AssertTable> assertTables = new ArrayList<>();
            
            
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
     * @return the page
     */
    public Page getPage() {
        return page;
    }

    /**
     * @param page the page to set
     */
    public void setPage(Page page) {
        this.page = page;
    }

    /**
     * @return the assertElements
     */
    public List<AssertElement> getAssertElements() {
        return assertElements;
    }

    /**
     * @return the assertTables
     */
    public List<AssertTable> getAssertTables() {
        return assertTables;
    }
    
    
}
