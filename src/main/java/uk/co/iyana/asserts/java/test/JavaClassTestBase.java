/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.iyana.asserts.java.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import uk.co.iyana.asserts.java.dto.TestClass;
import uk.co.iyana.asserts.java.dto.TestConstant;
import uk.co.iyana.asserts.java.dto.TestConstructor;
import uk.co.iyana.asserts.java.dto.TestMethod;
import uk.co.iyana.asserts.test.TestBase;


/**
 *
 * @author fgyara
 */
public class JavaClassTestBase extends TestBase {
    
    protected final ArrayList<TestClass> classes = new ArrayList<>();
    
    public JavaClassTestBase(String prefix) {
        super(prefix);
    }
    
    @DataProvider(name = "classesParamsProvider")
    public Object[][] classesParamsProvider() {

        ArrayList<Object[]> toRet = new ArrayList<>();
        Object[][] aToRet = {{}};
        Object[][] emptyArray = {};

        for (TestClass tc : classes) {
            String name = "." + tc.getEntityName() + ".Class - " + tc.getClassName();
            Object[] row = { name, tc }; 
            toRet.add(row);
        }
        
        if (toRet.isEmpty()) {
            return emptyArray;
        }
                
        return toRet.toArray(aToRet);
    }   
    
    @Test(
        dataProvider = "classesParamsProvider",
        groups = {"testClasses"} 
    )
    public void testClasses(String errorLocation, TestClass tc) {
        // check if the class exists
        try {
            Class c = Class.forName( tc.getPkg() + "." + tc.getClassName());
            
            // check if it is the right variety
            switch (tc.getType()) {
                case ABSTRACT_CLASS:
                    Assert.assertTrue(Modifier.isAbstract( c.getModifiers()), errorLocation + ": Incorrect class type.");
                    break;
                    
                case CLASS:
                    Assert.assertFalse(Modifier.isAbstract( c.getModifiers()), errorLocation + ": Incorrect class type.");
                    Assert.assertFalse( c.isInterface(), ": Incorrect class type.");
                    break;
                    
                case INTERFACE:
                    Assert.assertTrue( c.isInterface(), ": Incorrect class type.");
                    break;
            }
            
            // ensure it extends the right thing
            if (c.isInterface()) {
                // do not check superclass
            } else {
                String expectedSuperclass = tc.getXtends();
                if (expectedSuperclass == null) expectedSuperclass = "java.lang.Object";
                
                Class sc = c.getSuperclass();
                Assert.assertEquals(sc.getName(), expectedSuperclass, errorLocation + ": Incorrect super-class");
            }
                
            // ensure that it implements the right interfaces
            List<String> actualInterfaces = new ArrayList<>();
            for (Class ifc : c.getInterfaces()) {
                actualInterfaces.add( ifc.getName());
            }
            Assert.assertEquals(actualInterfaces, tc.getInterfaces(), errorLocation + ": Incorrect interfaces implemented");
            
        } catch (Exception e) {
            Assert.fail(errorLocation + ": Class could not be validated (Exception)", e);
        }
    }
    
    @DataProvider(name = "ctorParamsProvider")
    public Object[][] ctorParamsProvider() {

        ArrayList<Object[]> toRet = new ArrayList<>();
        Object[][] aToRet = {{}};
        Object[][] emptyArray = {};

        for (TestClass tc : classes) {
            for (TestConstructor ctor : tc.getConstructors()) {
                String errorLocation = "." + tc.getEntityName() + ".Constructor - " + tc.getClassName();
                Object[] row = { errorLocation, tc, ctor }; 
                toRet.add(row);
            }
        }
        
        if (toRet.isEmpty()) {
            return emptyArray;
        }
        return toRet.toArray(aToRet);
    }   
    
    @Test(
        dataProvider = "ctorParamsProvider",
        groups = {"testConstructors"} 
    ) 
    public void testConstructor(String errorLocation, TestClass tc, TestConstructor expectedCtor) {

        try {
            Class c = Class.forName( tc.getPkg() + "." + tc.getClassName());
            
            // create ctor param list
            int size = expectedCtor.getParams().size();
            
            try {
                Class[] params = new Class[expectedCtor.getParams().size()];
                for (int iCtr=0; iCtr < params.length; iCtr++) {
                    params[iCtr] = ParamTypeHelper.getClass(expectedCtor.getParams().get(iCtr));
                }
                
                Constructor ctor = c.getConstructor(params);
                Assert.assertNotNull(ctor, errorLocation + " Constructor not found");
            } catch (NoSuchMethodException | SecurityException e) {
                Assert.fail(errorLocation + " Constructor not found");
            }            
        } catch (ClassNotFoundException e) {
            Assert.fail(errorLocation + ": Class could not be loaded (Exception)", e);
        }
    }    
    
    @DataProvider(name = "methodParamsProvider")
    public Object[][] methodParamsProvider() {

        ArrayList<Object[]> toRet = new ArrayList<>();
        Object[][] aToRet = {{}};
        Object[][] emptyArray = {};

        for (TestClass tc : classes) {
            for (TestMethod method : tc.getMethods()) {
                String errorLocation = "." + tc.getEntityName() + ".Method - " + tc.getClassName() + "." + method.getName();
                Object[] row = { errorLocation, tc, method }; 
                toRet.add(row);
            }
        }
        
        if (toRet.isEmpty()) {
            return emptyArray;
        }
        return toRet.toArray(aToRet);
    }   
    
    @Test(
        dataProvider = "methodParamsProvider",
        groups = {"testMethods"} 
    ) 
    public void testMethod(String errorLocation, TestClass tc, TestMethod expectedMethod) {

        try {
            Class c = Class.forName( tc.getPkg() + "." + tc.getClassName());
            
            // create method param list
            Class[] params = new Class[expectedMethod.getParams().size()];
            for (int iCtr=0; iCtr < params.length; iCtr++) {
                params[iCtr] = ParamTypeHelper.getClass(expectedMethod.getParams().get(iCtr).getClassName());
            }
            
            // get the method 
            try {
                Method method = c.getDeclaredMethod(expectedMethod.getName(), params);
                Assert.assertNotNull(method, errorLocation + " Method not found");
                
                // check that each of the parameters have the right generic type
                for (int iCtr=0; iCtr < expectedMethod.getParams().size(); iCtr++) {
                    ArrayList<String> typeArgumentList = new ArrayList<>();
                    Type genericParameterType = method.getGenericParameterTypes()[iCtr];
                    
                    if(genericParameterType instanceof ParameterizedType){
                        ParameterizedType pGenericParameterType = (ParameterizedType) genericParameterType;
                        Type[] typeArguments = pGenericParameterType.getActualTypeArguments();
                        for(Type typeArgument : typeArguments){
                            Class typeArgClass = (Class) typeArgument;
                            typeArgumentList.add(typeArgClass.getName());
                        }    
                    }
                    Assert.assertEquals(typeArgumentList, expectedMethod.getParams().get(iCtr).getTypes(), "Mismatch type arguments for param " + (iCtr+1));
                }
                
                
                // check that it returns what is expected
                if (expectedMethod.isReturnsVoid()) {
                    // ensure it returns void
                    Assert.assertEquals( method.getReturnType(), Void.TYPE, errorLocation + " Incorrect return type");
                } else {
                    // check that the classes are the same
                    String expectedReturnType = ParamTypeHelper.getAdjustedClassName(expectedMethod.getReturnType().getClassName());
                    Assert.assertEquals( method.getReturnType().getName(), expectedReturnType, " Incorrect return type");
                    
                    // get the parameterised types
                    ArrayList<String> returnTypes = new ArrayList<>();
                    Type returnType = method.getGenericReturnType();
                    if(returnType instanceof ParameterizedType){
                        ParameterizedType pReturnType = (ParameterizedType) returnType;
                        Type[] typeArguments = pReturnType.getActualTypeArguments();
                        for(Type typeArgument : typeArguments){
                            if (typeArgument instanceof Class) {
                                Class typeArgClass = (Class) typeArgument;
                                returnTypes.add(typeArgClass.getName());
                            } else if (typeArgument instanceof ParameterizedType) {
                                ParameterizedType r2 = (ParameterizedType)typeArgument;
                                returnTypes.add(((Class)r2.getRawType()).getName());
                            }
                        }    
                    }
                    Assert.assertEquals( returnTypes, expectedMethod.getReturnType().getTypes(), errorLocation + " Mismatched generic return type");
                    
                }                        
                
            } catch (NoSuchMethodException | SecurityException e) {
                Assert.fail(errorLocation + " Method not found");
            }            
        } catch (ClassNotFoundException e) {
            Assert.fail(errorLocation + ": Class could not be loaded (Exception)", e);
        }
    }    
    
    @DataProvider(name = "constantsParamProvider")
    public Object[][] constantsParamProvider() {

        ArrayList<Object[]> toRet = new ArrayList<>();
        Object[][] aToRet = {{}};
        Object[][] emptyArray = {};

        for (TestClass tc : classes) {
            for (TestConstant constant : tc.getConstants()) {
                String errorLocation = "." + tc.getEntityName() + ".Constant - " + constant.getFieldName();
                Object[] row = { errorLocation, tc, constant }; 
                toRet.add(row);
            }
        }
        
        if (toRet.isEmpty()) {
            return emptyArray;
        }
        return toRet.toArray(aToRet);
    }   
    
    @Test(
        dataProvider = "constantsParamProvider"
    ) 
    public void testConstants(String errorLocation, TestClass tc, TestConstant expectedConstant) {

        try {
            Class c = Class.forName( tc.getPkg() + "." + tc.getClassName());
            
            // get the field
            try {
                Field field = c.getDeclaredField(expectedConstant.getFieldName());
                field.setAccessible(true);
                Assert.assertEquals((String)field.get(null), expectedConstant.getFieldValue());
            } catch (NoSuchFieldException e) {
                Assert.fail(errorLocation + " Constant not found");
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Assert.fail(errorLocation + " Could not read constant value");
            }            
        } catch (ClassNotFoundException e) {
            Assert.fail(errorLocation + ": Class could not be loaded (Exception)", e);
        }
    }    
    
    
    
}