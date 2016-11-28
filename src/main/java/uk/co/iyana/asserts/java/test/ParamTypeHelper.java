/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.iyana.asserts.java.test;

import java.util.List;
import java.util.Arrays;

/**
 *
 * @author fgyara
 */
public class ParamTypeHelper {
    
    private final static String[] primitiveTypes = { "byte", "short", "int", "long", "float", "double", "char", "boolean"};
    
    private final static List<String> primitives = Arrays.asList(primitiveTypes);
    
    public static Class getClass(String type) throws ClassNotFoundException {
        if (type.contains(".")) {
            // fully qualified name
            return Class.forName(type);
        }
            
        if (primitives.contains(type)) {
            switch (type) {
                case "byte": return byte.class;
                case "short": return short.class;
                case "int": return int.class;
                case "long": return long.class;
                case "float": return float.class;
                case "double": return double.class;
                case "char": return char.class;
                case "boolean": return boolean.class;
            }
        }
        
        return Class.forName("java.lang." + type);
    }
    
    public static String getAdjustedClassName(String className) {
        if (className.contains(".")) {
            return className;
        }
        
        if (primitives.contains(className)) {
            return className;
        }
        
        return "java.lang." + className;
        
    }
}
