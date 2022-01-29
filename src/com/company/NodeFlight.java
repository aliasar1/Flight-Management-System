package com.company;

import java.io.Serializable;
import java.util.ArrayList;

public class NodeFlight implements Serializable {

    public static int nextFlightId = 1;
    public int flightId;
    public City source;
    public City destination;
    public NodePassenger[] seats;
    public NodeFlight next = null;

    // Time complexity = O(1)
    public NodeFlight(City source, City destination) {
        this.flightId = nextFlightId;
        this.source = source;
        this.destination = destination;
        this.seats = new NodePassenger[5]; //100 seats of NodePassenger class
        nextFlightId++;
    }

    // Time complexity = O(n)
    public int getVacantSeats() {
        int count = 0;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == null) {
                count++;
            }
        }
        return count;
    }

    // Time complexity = O(1)
    public void display() {
        System.out.print(source.name + " --> " + destination.name + "\n");
    }
}
