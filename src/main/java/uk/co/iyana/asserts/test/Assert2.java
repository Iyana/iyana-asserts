/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.iyana.asserts.test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.testng.Assert;

/**
 *
 * @author fgyara
 */
public class Assert2 extends Assert {

    public static void assertEquals(List<Map<String, Object>> actual, List<Map<String, ?>> expected) {
        Assert2.assertEquals(actual, expected, null);
    }

    public static void assertEquals(List<Map<String, Object>> actual, List<Map<String, ?>> expected, String mesg) {
        
        System.out.println("ASSERTING THRU2");

        if (actual == null || expected == null) {
            fail("Lists not equal: expected: " + expected + " and actual: " + actual);
        }

        if (actual.size() != expected.size()) {
            fail("Lists do not have the same size:" + actual.size() + " != " + expected.size());
        }

        // loop through
        for (int iCtr=0; iCtr < actual.size(); iCtr++) {
            Map<?,?> expectedMap = expected.get(iCtr);
            Map<?,?> actualMap = actual.get(iCtr);
            Assert2.compareMap(actualMap, expectedMap);
        }
    }

    public static void assertEquals(Map<?, ?> actual, Map<?, ?> expected) {
        Assert2.assertEquals(actual, expected, null);
    }

    public static void assertEquals(Map<?, ?> actual, Map<?, ?> expected, String mesg) {
        if (actual == expected) {
            return;
        }

        if (actual == null || expected == null) {
            fail("Maps not equal: expected: " + expected + " and actual: " + actual);
        }

        if (actual.size() != expected.size()) {
            fail("Maps do not have the same size:" + actual.size() + " != " + expected.size());
        }

        compareMap(actual, expected);

    }

    private static void compareMap(Map<?, ?> actual, Map<?, ?> expected) {
        Set<?> entrySet = actual.entrySet();
        for (Object anEntrySet : entrySet) {
            Map.Entry<?, ?> entry = (Map.Entry<?, ?>) anEntrySet;
            Object key = entry.getKey();
            Object value = entry.getValue();
            Object expectedValue = expected.get(key);
            // System.out.println("key " + key + "v=" + value.getClass().getName() + " eV=>" + expectedValue.getClass().getName());

            if (value instanceof BigDecimal) {
                double dValue = ((BigDecimal) value).doubleValue();
                assertEquals(dValue, expectedValue, "Maps do not match for key:" + key + " actual:" + dValue
                        + " expected:" + expectedValue);
            } else {
                assertEquals(value, expectedValue, "Maps do not match for key:" + key + " actual:" + value
                        + " expected:" + expectedValue);
            }
        }
    }

    public static void assertEqualOrNullSortedList(List actual, List expected, String mesg) {
        if ((expected == null)||(expected.isEmpty())) {
            Assert.assertTrue(((actual == null)||(actual.isEmpty())), mesg);
            return;
        }
        
        // sort the lists
        Collections.sort(actual);
        Collections.sort(expected);

        Assert.assertEquals(actual, expected, mesg);
        
    }

}
