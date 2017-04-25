package com.ba2364.inclassassignment11_benjamina;

import java.io.Serializable;

public class City implements Serializable {

    public String id;
    public String cityName;
    public int cityPopulation;
    public boolean cityBig;

    public City() {
    }

    public City(String id, String cityName, int cityPopulation, boolean cityBig) {
        this.id = id;
        this.cityName = cityName;
        this.cityPopulation = cityPopulation;
        this.cityBig = cityBig;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=' " + id + '\'' +
                ", name='" + cityName + '\'' +
                ", population=" + cityPopulation +
                ", big city=" + cityBig +
                '}';
    }
}

