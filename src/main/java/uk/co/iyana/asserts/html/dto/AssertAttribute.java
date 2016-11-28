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
public class AssertAttribute {
    private String name;
    private String value;
    private final List<String> values = new ArrayList<>();

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
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the values
     */
    public List<String> getValues() {
        return values;
    }
}
