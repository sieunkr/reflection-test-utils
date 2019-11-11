package com.example.demo;

import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@Component
public class CoffeeComponent {


    public String findByName(String name){

        //getInstanceByName(name);

        Object b = getInstanceByName(name);
        //b.make();

        return null;
    }


    private Object getInstanceByName(String name){
        try {
            Class cls = Class.forName(name);

            Constructor constructor = cls.getConstructor();
            return constructor.newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}