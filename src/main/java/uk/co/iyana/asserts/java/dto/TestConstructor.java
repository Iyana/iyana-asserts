
 /* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.iyana.asserts.java.dto;

import java.util.ArrayList;
import java.util.List;
import uk.co.iyana.asserts.java.test.ParamTypeHelper;
/**
 *
 * @author fgyara
 */
public class TestConstructor {

    private final List<String> params = new ArrayList<>();
    
    public static TestConstructor param(String type) {
        TestConstructor tc = new TestConstructor();
        tc.and(type);
        return tc;
    }
    
    /**
     * @return the params
     */
    public List<String> getParams() {
        return params;
    }
    
    public TestConstructor and(String type) {
        this.getParams().add(type);
        return this;
    }
    
    
}
