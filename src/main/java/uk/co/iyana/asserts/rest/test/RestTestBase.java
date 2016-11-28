/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.iyana.asserts.rest.test;

import java.util.ArrayList;
import java.util.List;
import uk.co.iyana.asserts.rest.dto.TestRest;
import uk.co.iyana.asserts.test.TestBase;

/**
 *
 * @author fgyara
 */
public class RestTestBase extends TestBase {
    
    protected List<TestRest> tests = new ArrayList<>();
    
    public RestTestBase() {
        super("REST");
    }
    
}
