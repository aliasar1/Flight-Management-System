package com.company;

import java.io.Serializable;

public class StaticUtils implements Serializable {
    public int nextFlightId;
    public int counter;
    public int nextCityCode;

    public StaticUtils(){
        this.nextFlightId = 0;
        this.counter = 0;
        this.nextCityCode = 0;
    }

    public StaticUtils(int nFI, int c, int nCC){
        this.nextFlightId = nFI;
        this.counter = c;
        this.nextCityCode = nCC;
    }

    public void loadFrom(){
        City.nextCityCode = this.nextCityCode;
        NodeFlightRoute.nextFlightId = this.nextFlightId;
        NodePassenger.counter = this.counter;
    }

    public void loadTo(){
        this.nextCityCode = City.nextCityCode;
        this.nextFlightId = NodeFlightRoute.nextFlightId;
        this.counter = NodePassenger.counter;
    }
}
