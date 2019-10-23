package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { CoffeeService.class, CoffeeRepository.class})
public class CoffeeServiceTest {

    @Autowired
    private CoffeeService coffeeService;

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Test
    public void whenB() throws Exception{

        Integer expectedPrice = 1400;

        Method method = CoffeeService.class.getDeclaredMethod("getPriceWithDiscount", Coffee.class);
        method.setAccessible(true);

        Integer activePrice =
                (Integer)method.invoke(coffeeService, coffeeRepository.findByName("latte"));

        assertEquals(expectedPrice, activePrice);

    }


    @Test
    public void whenA(){

        Integer expectedPrice = 1400;

        Integer activePrice =
            ReflectionTestUtils.invokeMethod(coffeeService, "getPriceWithDiscount", coffeeRepository.findByName("latte"));

        assertEquals(expectedPrice, activePrice);
    }
}