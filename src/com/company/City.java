package com.company;

import java.io.Serializable;

public class City implements Serializable {
    public static int nextCityCode = 0;
    String name;
    int code;

    // Time complexity = O(1)
    public City(String name) {
        this.name = name;
        this.code = nextCityCode;
        nextCityCode++;
    }

    // Time complexity = O(1)
    @Override
    public String toString() {
        return "City{'" + name + '\'' + ", Code: " + code + '}';
    }
}
