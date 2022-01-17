package com.company;

import java.io.Serializable;

public class City implements Serializable {
    String name;
    int code;
    public static int nextCityCode = 0;

    public City(String name) {
        this.name = name;
        this.code = nextCityCode;
        nextCityCode++;
    }

    @Override
    public String toString() {
        return "City{ '"+ name + '\'' + " Code " + code +
                '}';
    }
}
