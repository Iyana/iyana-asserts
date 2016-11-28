/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.iyana.asserts.java.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fgyara
 */
public class TestGeneric {
    
    private final String className;
    private final List<String> types = new ArrayList<>();
    
    private TestGeneric(String className) {
        this.className = className;
    }
    
    public static TestGeneric className(String className) {
        TestGeneric tg = new TestGeneric(className);
        return tg;
    }
    
    public TestGeneric type(String type) {
        this.getTypes().add(type);
        return this;
    }

    /**
     * @return the className
     */
    public String getClassName() {
        return className;
    }

    /**
     * @return the types
     */
    public List<String> getTypes() {
        return types;
    }
}
