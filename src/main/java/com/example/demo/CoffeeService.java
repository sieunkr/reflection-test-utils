package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoffeeService {

    private final CoffeeRepository coffeeRepository;

    public Integer getPriceByName(String name){
        return getPriceWithDiscount(getCoffeeByName(name));
    }

    private Coffee getCoffeeByName(String name){
        return coffeeRepository.findByName(name);
    }

    private Integer getPriceWithDiscount(Coffee c){
        return c.getPrice() - 100;
    }
}
