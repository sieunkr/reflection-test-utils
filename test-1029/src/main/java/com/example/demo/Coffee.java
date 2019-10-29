package com.example.demo;

public class Coffee implements Drinkable{
    private String name;
    private int price;

    public Coffee(){

    }

    public Coffee(String name, int price){
        this.name = name;
        this.price = price;
    }

    @Override
    public void drink() {
        System.out.println("커피를 마시다.");
    }

    private void brew() {
        System.out.println("커피를 만들다.");
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}