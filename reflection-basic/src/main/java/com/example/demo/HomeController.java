package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/coffee")
public class HomeController {

    private final CoffeeComponent coffeeComponent;

    public HomeController(CoffeeComponent coffeeComponent) {
        this.coffeeComponent = coffeeComponent;
    }

    @GetMapping
    public String findOne(@RequestParam(name = "name") String name){
        return coffeeComponent.findByName(name);
    }

}
