/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.iyana.asserts.prettyprint;

import java.util.List;

/**
 *
 * @author fgyara
 */
public class PrettyPrint {
 
    public static void print(String s) {
        System.out.println(s);
    }
    
    public static void print(String s, int tabs) {
        for (int iCtr=0; iCtr<tabs; iCtr++) System.out.print("\t");
        System.out.println(s);
    }
    
    public static void print(Object o) {
        PrettyPrint.print(o,0);
    }

    public static void print(Object o, int tabs) {
        PrettyPrint.print(o.toString(), tabs);
    }
    
    public static void print(List list) {
        PrettyPrint.print(list,0);
    }
    
    public static void print(List list, int tabs) {
        PrettyPrint.print("List of " + list.size(), tabs);
        for (Object o : list) {
            PrettyPrint.print(o, tabs+1);
        }
    }
    
    public static void print(Object[] arrayOfO, int tabs) {
        PrettyPrint.print("Array of " + arrayOfO.length, tabs);
        for (Object o : arrayOfO) {
            PrettyPrint.print(o, tabs+1);
        }
    }
    
}
