package com.company;

import java.io.Serializable;

public class NodeFlight implements Serializable {

    public static int nextFlightId = 1;
    public int flightId;
    public City source;
    public City destination;
    public NodePassenger[] seats;
    public NodeFlight next = null;

    public NodeFlight(City source, City destination) {
        this.flightId = nextFlightId;
        this.source = source;
        this.destination = destination;
        this.seats = new NodePassenger[5]; //100 seats of NodePassenger class
        nextFlightId++;
    }

    public int getVacantSeats() {
        int count = 0;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == null) {
                count++;
            }
        }
        return count;
    }

    public void display() {
        System.out.print(source.name + " --> " + destination.name + "\n");
    }
}
