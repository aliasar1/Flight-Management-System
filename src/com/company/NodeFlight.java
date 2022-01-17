package com.company;

import java.io.Serializable;

public class NodeFlight implements Serializable {
    //    static int counter = 0;
    private int flightConnectionsNum = 0;
    //    private int flightId;
    //    private int numSeats = 100;
    //    public LocalDateTime flightTime;
    public City source;
    public City destination;
    public NodePassenger[] seats;
    public NodeFlight next = null;

    public NodeFlight( /*LocalDateTime flightTime,*/ City source, City destination) {
        this.flightConnectionsNum++;
        this.source = source;
        this.destination = destination;
        this.seats = new NodePassenger[5]; //100 seats of NodePassenger class
        //        this.flightTime = flightTime;
        //        counter++;
    }

    public void display() {
        System.out.print(source.name + " --> " + destination.name + "\n");
    }

    public int getFlightConnectionsNum() {
        return flightConnectionsNum;
    }

}
