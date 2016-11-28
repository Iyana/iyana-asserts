/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.iyana.asserts.dao.test;

import uk.co.iyana.asserts.testdata.CreateTestData;

/**
 *
 * @author fgyara
 * @param <T>
 */
public interface IDaoCreateTestHarness<T> {
    
    public T doTest(CreateTestData<T> testData);
    
    public T find(T createdDto);
}
