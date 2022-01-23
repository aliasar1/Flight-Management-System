package com.company;

import java.io.Serializable;

public class StaticUtils implements Serializable {
    public int nextRouteId;
    public int nextFlightId;
    public int nextPassengerId;
    public int nextCityCode;

    public StaticUtils() {
        this.nextCityCode = 0;
        this.nextFlightId = 0;
        this.nextRouteId = 0;
        this.nextPassengerId = 0;
    }

    public StaticUtils(int nRI, int pID, int nCC, int nFI) {
        this.nextCityCode = nCC;
        this.nextRouteId = nRI;
        this.nextFlightId = nFI;
        this.nextPassengerId = pID;
    }

    public void loadClasses() {
        City.nextCityCode = this.nextCityCode;
        NodeFlight.nextFlightId = this.nextFlightId;
        NodeFlightRoute.nextRouteId = this.nextRouteId;
        NodePassenger.nextPassengerId = this.nextPassengerId;
    }

    public void loadFromClasses() {
        this.nextCityCode = City.nextCityCode;
        this.nextFlightId = NodeFlight.nextFlightId;
        this.nextRouteId = NodeFlightRoute.nextRouteId;
        this.nextPassengerId = NodePassenger.nextPassengerId;
    }
}
