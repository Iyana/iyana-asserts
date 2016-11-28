/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.iyana.asserts.test;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author fgyara
 */
public class MapTestHelper {
    
    public static Map<String,String> getClassNames(Map<String, Object> in) {
        if (in == null) return null;
        
        Map<String, String> out = new HashMap<>();
        
        for (String key: in.keySet()) {
            Object obj = in.get(key);
            
            if (obj == null) {
                out.put(key, "null");
            } else {
                out.put(key, obj.getClass().getName());
            }
        }
        
        return out;
    }
}
