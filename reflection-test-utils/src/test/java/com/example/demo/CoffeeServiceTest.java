package com.example.demo;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class CoffeeServiceTest {

    private CoffeeService coffeeService;

    private CoffeeRepository coffeeRepository;

    @Before
    public void init(){
        coffeeRepository = new CoffeeRepository();
        coffeeRepository.init();
        coffeeService = new CoffeeService(coffeeRepository);
    }

    @Test
    public void testGetPriceWithDiscountByMethodInvoke() throws Exception {

        //Given
        int expectedPrice = 1400;

        Method method = CoffeeService.class.getDeclaredMethod("getPriceWithDiscount", Coffee.class);
        method.setAccessible(true);

        //When
        int actualPrice = (int) method.invoke(coffeeService, coffeeRepository.findByName("latte"));

        //Then
        assertEquals(expectedPrice, actualPrice);

    }


    @Test
    public void testGetPriceWithDiscountByReflectionTestUtils() {

        //Given
        int expectedPrice = 1400;

        //When
        int actualPrice = ReflectionTestUtils.invokeMethod(
                coffeeService,
                "getPriceWithDiscount",
                coffeeRepository.findByName("latte"));

        //Then
        assertEquals(expectedPrice, actualPrice);
    }
}