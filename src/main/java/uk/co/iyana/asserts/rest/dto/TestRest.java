/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.iyana.asserts.rest.dto;

/**
 *
 * @author fgyara
 */
public class TestRest {
    private String url;
    private int expectedResponseCode;
    private Object expectedBody;
 

//    TestRest.get(xxx)
//            .hasResponseCode(yy)
//            .hasBody(zz)
    
    
    private TestRest() {
        
    }
    
    
    public static TestRest get(String url) {
        TestRest toRet = new TestRest();
        toRet.url = url;
        
        return toRet;
    }
    
    public TestRest hasResponseCode(int expectedResponseCode) {
        this.expectedResponseCode = expectedResponseCode;
        return this;
    }
    
    public TestRest hasBody(Object expectedBody) {
        this.expectedBody = expectedBody;
        return this;
    }
    
}
