/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.iyana.asserts.dao.test;

import uk.co.iyana.asserts.testdata.LinkTestData;

/**
 *
 * @author fgyara
 * @param <K>
 * @param <V>
 */
public interface IDaoLinkTestHarness<K, V> {
    
    public void doTest(LinkTestData<K, V> testData);
    
    public boolean checkExists(LinkTestData<K, V> testData);
}
