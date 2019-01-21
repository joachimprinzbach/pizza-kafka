package com.pizza.api;

public class PizzaDto {

    private String name;

    public PizzaDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
