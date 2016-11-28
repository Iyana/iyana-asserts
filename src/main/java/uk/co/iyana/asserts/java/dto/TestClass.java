/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.iyana.asserts.java.dto;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import uk.co.iyana.asserts.java.test.ParamTypeHelper;

/**
 *
 * @author fgyara
 */
public class TestClass {
    
    private final String entityName;
    private String className;
    private String pkg;
    private TestClassType type;
    private String xtends;
    private final List<TestMethod> methods = new ArrayList<>();
    private final List<TestConstructor> constructors = new ArrayList<>();
    private final List<String> interfaces = new ArrayList<>();
    private final List<TestConstant> constants = new ArrayList<>();
    
    private TestClass(String entityName) {
        this.entityName = entityName;
    }
    
    public static TestClass forEntity(String entityName) {
        TestClass tc = new TestClass(entityName);
        return tc;
    }
    
    public TestClass name(String className) {
        this.className = className;
        return this;
    }
    
    public TestClass pkg(String pkg) {
        this.pkg = pkg;
        return this;
    }
    
    public TestClass xtends(String xtends) {
        this.xtends = xtends;
        return this;
    }

    public TestClass type(TestClassType type) {
        this.type = type;
        return this;
    }
    
    public TestClass accessorAndModifier(String fieldName, String type)  {
        TestMethod accessor = 
                TestMethod.name("get" + capitalize(fieldName))
                    .returns(type);

        TestMethod modifier = 
                TestMethod.name("set" + capitalize(fieldName))
                    .returnsVoid()
                    .param(type);
        
        this.getMethods().add(accessor);
        this.getMethods().add(modifier);
                    
        return this;
    }
    
    public TestClass mvAccessorAndModifier(String fieldName, String type) {
        TestMethod accessor = 
            TestMethod.name("get" + capitalize(fieldName) + "List")
                .returns(TestGeneric.className("java.util.List")
                        .type(ParamTypeHelper.getAdjustedClassName(type))
                );

        TestMethod modifier = 
            TestMethod.name("set" + capitalize(fieldName) + "List")
                .returnsVoid()
                .param(TestGeneric.className("java.util.List")
                        .type(ParamTypeHelper.getAdjustedClassName(type))
                );
        
        this.getMethods().add(accessor);
        this.getMethods().add(modifier);
                    
        return this;
    }
    
    public TestClass constructor(TestConstructor constructor) {
        this.getConstructors().add(constructor);
        return this;
    }
    
    public TestClass defaultConstructor() {
        this.getConstructors().add(new TestConstructor());
        return this;
    }

    public TestClass impl(String ifc) {
        this.interfaces.add(ifc);
        return this;
    }
    
    public TestClass method(TestMethod method) {
        this.methods.add(method);
        return this;
    }
    
    
    /**
     * @return the entityName
     */
    public String getEntityName() {
        return entityName;
    }

    /**
     * @return the className
     */
    public String getClassName() {
        return className;
    }

    /**
     * @return the pkg
     */
    public String getPkg() {
        return pkg;
    }

    /**
     * @return the type
     */
    public TestClassType getType() {
        return type;
    }

    /**
     * @return the xtends
     */
    public String getXtends() {
        return xtends;
    }

    /**
     * @return the methods
     */
    public List<TestMethod> getMethods() {
        return methods;
    }

    /**
     * @return the constructors
     */
    public List<TestConstructor> getConstructors() {
        return constructors;
    }

    /**
     * @return the interfaces
     */
    public List<String> getInterfaces() {
        return interfaces;
    }
    
    private String capitalize(final String line) {
       return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }  
    
    public List<String> foo(ArrayList<String> x) {
        return null;
    }
    
    
    public List<Map<String, Object>>  foo2 () {
        return null;
    }
    
    public static void main(String args[]) throws Exception {
        
        Class[] cs = { };
        Method m1 = TestClass.class.getMethod("foo2", cs );
        System.out.println( m1.getName());
        
        // get the parameterised types
        Type returnType = m1.getGenericReturnType();
        if(returnType instanceof ParameterizedType){
            ParameterizedType pReturnType = (ParameterizedType) returnType;
            Type[] typeArguments = pReturnType.getActualTypeArguments();
            for(Type typeArgument : typeArguments){
                System.out.println("Type argument:" + typeArgument.getClass().getName());
                
                ParameterizedType pta = (ParameterizedType)typeArgument;
                System.out.println("PTA: " + pta.getRawType().getClass().getName());
                System.out.println("PTA: " + ((Class)pta.getRawType()).getName());
            }    
        }
        
        
        
        
//        Method ms[] = TestClass.class.getDeclaredMethods();
//        for (Method m : ms) {
//            System.out.println( m.getName());
//            Class[] params = m.getParameterTypes();
//            for (Class param: params) {
//                System.out.println( param.getName());
//            }
//        }
//        System.out.println();
        
//        ParameterizedType paramType = (ParameterizedType) m.getGenericParameterTypes()[0];
//        System.out.println( paramType.getClass().getName());
//        System.out.println(paramType.getActualTypeArguments()[0]);
        
        
        
        
//        
//if(ret instanceof ParameterizedType){
//    ParameterizedType type = (ParameterizedType) ret;
//    Type[] typeArguments = type.getActualTypeArguments();
//    for(Type typeArgument : typeArguments){
//        Class typeArgClass = (Class) typeArgument;
//        System.out.println("typeArgClass = " + typeArgClass);
//    }

    }

    public TestClass constant(String fieldName, String fieldValue) {
        this.getConstants().add(new TestConstant(fieldName, fieldValue));
        return this;
    }

    /**
     * @return the constants
     */
    public List<TestConstant> getConstants() {
        return constants;
    }
}
