/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.iyana.asserts.test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import uk.co.iyana.json.io.JsonHelper;

/**
 *
 * @author fgyara
 */
public class DataProviderHelper {

    public static Object[][] toArray(Type t, String... fileNames) {
        
        List list = null;
        for (String fileName: fileNames) {
            List newList = (List)JsonHelper.read(fileName).to(t);
            
            if (list == null) {
                list = newList;
            } else {
                list.addAll(newList);
            }
        }
        
        return DataProviderHelper.toArray(list);          
        
        // return DataProviderHelper.toArray((List)JsonHelper.read(fileName).to(t));          
    }
    
    
    public static Object[][] toArray(List list) {
        
        Object[][] emptyArray = {};
        Object[][] aToRet = {{}};
        
        if (list.isEmpty()) {
            return emptyArray;
        }

        List<Object[]> toRet = new ArrayList<>();
        for (Object obj : list) {
            Object[] row = { obj }; 
            toRet.add(row);
        }
        
        return toRet.toArray(aToRet);
    }
    
}
