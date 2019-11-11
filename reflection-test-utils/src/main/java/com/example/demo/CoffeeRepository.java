package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Repository
public class CoffeeRepository {

    private final HashMap<String, Coffee> coffeeHashMap = new HashMap<>();

    @PostConstruct
    public void init() {
        coffeeHashMap.put("mocha", Coffee.builder().name("mocha").milk(true).price(1700).build());
        coffeeHashMap.put("latte", Coffee.builder().name("latte").milk(true).price(1500).build());
        coffeeHashMap.put("americano", Coffee.builder().name("americano").milk(false).price(900).build());
        coffeeHashMap.put("cappuccino", Coffee.builder().name("cappuccino").milk(false).price(1800).build());
        coffeeHashMap.put("coldbrew", Coffee.builder().name("coldbrew").milk(false).price(2000).build());
        coffeeHashMap.put("affogato", Coffee.builder().name("affogato").milk(false).price(2300).build());
    }

    public List<Coffee> findAllOrderByPrice() {
        ArrayList<Coffee> coffeeArrayList = new ArrayList<>(coffeeHashMap.values());

        coffeeArrayList.sort(Comparator.comparing(Coffee::getPrice));

        return coffeeArrayList;
    }

    public Coffee findByName(String name) {

        log.info(Thread.currentThread().getName());

        return coffeeHashMap.get(name);
    }
}
