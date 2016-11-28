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
public class TestMethod {

    private final String name;
    private boolean returnsVoid = false;
    private TestGeneric returnType;
    private final List<TestGeneric> params = new ArrayList<>();
    
    private TestMethod(String name) {
        this.name = name;
    }
    
    public static TestMethod name(String name) {
        TestMethod tm = new TestMethod(name);
        return tm;
    }
    
    public TestMethod returnsVoid() {
        this.returnsVoid = true;
        return this;
    }
    
    public TestMethod returns(String returns) {
        this.returnType = TestGeneric.className(returns);
        return this;
    }
    
    public TestMethod returns(TestGeneric returns)  {
        this.returnType = returns;
        return this;
    }
    
    public TestMethod param(String paramType)  {
        this.params.add( TestGeneric.className(paramType));
        return this;
    }

    public TestMethod param(TestGeneric paramType) {
        this.params.add(paramType);
        return this;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the returnsVoid
     */
    public boolean isReturnsVoid() {
        return returnsVoid;
    }

    /**
     * @return the returnType
     */
    public TestGeneric getReturnType() {
        return returnType;
    }

    /**
     * @return the params
     */
    public List<TestGeneric> getParams() {
        return params;
    }
    
}
