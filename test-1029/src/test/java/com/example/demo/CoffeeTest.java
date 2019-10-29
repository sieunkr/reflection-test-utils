package com.example.demo;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

public class CoffeeTest {

    @Test
    public void 필드명_테스트(){
        Object coffee = new Coffee();
        Field[] fields = coffee.getClass().getDeclaredFields();

        List<String> actualFieldNameList = getFieldNames(fields);

        assertTrue(Arrays.asList("name", "price").containsAll(actualFieldNameList));


        try {
            Field field = coffee.getClass().getDeclaredField("price");

            assertEquals("price", field.getName());

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void 필드명_테스트_02(){

        Class<?> clazz = null;
        try {
            clazz = Class.forName("com.example.demo.Coffee");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            Constructor<?> constructor = clazz.getConstructor(String.class, int.class);
            Coffee coffee = (Coffee)constructor.newInstance("라떼", 1200);
            try {
                Field field = clazz.getDeclaredField("price");

                //assertEquals(1200, field.get(coffee));   // can not access a member of class ... "private"
                field.setAccessible(true);
                assertEquals(1200, field.get(coffee));

            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void 클래스_이름(){

        Class<?> clazz = null;
        try {
            clazz = Class.forName("com.example.demo.Coffee");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        assertEquals("Coffee", clazz.getSimpleName());
        assertEquals("com.example.demo.Coffee", clazz.getName());
        assertEquals("com.example.demo.Coffee", clazz.getCanonicalName());

    }


    @Test
    public void 패키지(){

        Class<?> clazz = null;
        try {
            clazz = Class.forName("com.example.demo.Coffee");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        assertEquals("com.example.demo", clazz.getPackage().getName());
    }


    @Test
    public void 인터페이스(){

        Class<?> clazz = null;
        try {
            clazz = Class.forName("com.example.demo.Coffee");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Class<?>[] coffeeInterfaces = Objects.requireNonNull(clazz).getInterfaces();

        assertEquals(1, coffeeInterfaces.length);

    }



    @Test
    public void 생성자(){

        Class<?> clazz = null;
        try {
            clazz = Class.forName("com.example.demo.Coffee");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Constructor<?>[] constructors = clazz.getConstructors();

        assertEquals(2, constructors.length);
        assertEquals("com.example.demo.Coffee", constructors[1].getName());
        assertEquals("java.lang.String", constructors[1].getParameterTypes()[0].getName());
    }


    @Test
    public void 생성자_조회(){

        Class<?> clazz = null;
        try {
            clazz = Class.forName("com.example.demo.Coffee");
            Constructor<?> constructor = clazz.getConstructor(String.class, int.class);

            Coffee coffee = (Coffee)constructor.newInstance("라떼", 1200);

            assertEquals("라떼", coffee.getName());
            assertEquals(1200, coffee.getPrice());

        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void 메서드(){

        Class<?> clazz = null;
        try {
            clazz = Class.forName("com.example.demo.Coffee");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Method[] methods = clazz.getDeclaredMethods();
        List<String> actualMethods = getMethodNames(methods);

        assertEquals(4, actualMethods.size());
        assertTrue(actualMethods.containsAll(Arrays.asList("drink", "brew", "getName", "getPrice")));
    }


    @Test
    public void 메서드_private(){

        Class<?> clazz = null;
        try {
            clazz = Class.forName("com.example.demo.Coffee");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {

            Coffee coffee = new Coffee("latte", 1200);

            Method brewMethod = clazz.getDeclaredMethod("brew");

            assertFalse(brewMethod.isAccessible());

            brewMethod.setAccessible(true);

            assertTrue(brewMethod.isAccessible());

            brewMethod.invoke(coffee);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    private static List<String> getMethodNames(Method[] methods) {
        List<String> methodNames = new ArrayList<>();
        for (Method method : methods)
            methodNames.add(method.getName());
        return methodNames;
    }

    private static List<String> getFieldNames(Field[] fields) {
        List<String> fieldNames = new ArrayList<>();
        for (Field field : fields)
            fieldNames.add(field.getName());
        return fieldNames;
    }
}