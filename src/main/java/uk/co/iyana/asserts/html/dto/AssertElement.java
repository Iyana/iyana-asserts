/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.iyana.asserts.html.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fgyara
 */
public class AssertElement {
    private String xpath;
    private String name;
    private boolean multiValue;
    private Integer count;
    
    private final List<AssertAttribute> assertAttributes = new ArrayList<>();
    
    /**
     * @return the xpath
     */
    public String getXpath() {
        return xpath;
    }

    /**
     * @param xpath the xpath to set
     */
    public void setXpath(String xpath) {
        this.xpath = xpath;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the assertAttributes
     */
    public List<AssertAttribute> getAssertAttributes() {
        return assertAttributes;
    }

    /**
     * @return the multiValue
     */
    public boolean isMultiValue() {
        return multiValue;
    }

    /**
     * @param multiValue the multiValue to set
     */
    public void setMultiValue(boolean multiValue) {
        this.multiValue = multiValue;
    }

    /**
     * @return the count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(Integer count) {
        this.count = count;
    }

}
