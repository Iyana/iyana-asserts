/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.iyana.asserts.rest.test;

import uk.co.iyana.asserts.test.FindTestData;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import uk.co.iyana.asserts.test.Assert2;
import uk.co.iyana.httpclient.IyanaHttpClient;
import uk.co.iyana.json.io.JsonHelper;

/**
 *
 * @author fgyara
 */
public class RestTestHelper {
    
    public static void testRest(FindTestData<?> testData, String url, boolean expectArray) {

        IyanaHttpClient client = IyanaHttpClient.get(url);
        
        // check status
        int statusCode = client.getStatusCode();
        int expectedStatusCode = testData.getExpectedHttpCode();
        if (expectedStatusCode == 0) expectedStatusCode = 200;
        
        // check result
        String resultJson = client.getResponseBodyAsString();
        
        Type t;
        if (expectArray) {
            t = new TypeToken<List<Map<String, ?>>>() {}.getType();
        } else {
            t = new TypeToken<Map<Object, ?>>() {}.getType();
        }   
        Object printResult =  JsonHelper.readString(resultJson).to(t);
        
        // print the json
        System.out.println("Test: " + testData.getTestName());
        System.out.println("\tUrl:" + url);
        System.out.println("\tExpected Status Code: " + expectedStatusCode);
        System.out.println("\tInput:" + testData.getKey());
        System.out.println("\tActual (json) " + resultJson);
        System.out.println("\tActual (list.toString) " + printResult.toString());
        
        // check that the maps are equal
        if (expectedStatusCode == 200) {
            if (expectArray) {
                System.out.println("\tExpected (Array)" + testData.getDataList());
            } else {
                System.out.println("\tExpected (Dto)" + testData.getData());
            }
        } else {
            System.out.println("\tExpected " + testData.getExpectedHttpError());
        }
        System.out.println();
        
        // check the return code
        Assert2.assertEquals(statusCode, expectedStatusCode, "Http return code incorrect");
        
        // check that the maps are equal
        if (expectedStatusCode == 200) {
            if (expectArray) {
                List<Map<String, ?>> result =  JsonHelper.readString(resultJson).to(t);
                Assert2.assertEquals(result, testData.getDataList(), "http body incorrect");
            } else {
                Map<String, ?> result =  JsonHelper.readString(resultJson).to(t);
                Assert2.assertEquals(result, testData.getData(), "http body incorrect");
            }
        } else {
            Type errorType = new TypeToken<Map<Object, ?>>() {}.getType();
            Map<String, ?> result =  JsonHelper.readString(resultJson).to(errorType);
            Assert2.assertEquals(result, testData.getExpectedHttpError(), "http body incorrect");
        }
        
    }
    
}
