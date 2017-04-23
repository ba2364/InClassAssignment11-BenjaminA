package com.ba2364.inclassassignment11_benjamina;

import java.io.Serializable;

public class City implements Serializable{
    public String id;
    public String name;
    public int pop;
    public boolean bigCity;


    public City() {}


    public City(String id, String name, int pop, boolean bigCity) {
        this.id = id;
        this.name = name;
        this.pop = pop;
        this.bigCity = bigCity;
    }


    @Override
    public String toString() {
        return "City{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", pop=" + pop +
                ", bigCity=" + bigCity +
                '}';
    }
}
