package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoffeeService {

    private final CoffeeRepository coffeeRepository;

    public int getPriceByName(String name) {
        //TODO: 구현 예정
        return 0;
    }

    private Coffee getCoffeeByName(String name) {
        return coffeeRepository.findByName(name);
    }

    private int getPriceWithDiscount(Coffee c) {
        return c.getPrice() - 100;
    }
}