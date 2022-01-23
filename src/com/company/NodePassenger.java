package com.company;

import java.io.Serializable;

public class NodePassenger implements Serializable {
    public static int nextPassengerId = 1;
    public int passengerId;
    public String passengerName;
    public NodePassenger next;

    public int getPassengerId() {
        return this.passengerId;
    }

    public NodePassenger(String name) {
        this.passengerId = nextPassengerId;
        this.passengerName = name;
        nextPassengerId++;
    }

    public void display() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "Passenger ID : " + getPassengerId() + "\nName : " + passengerName;
    }
}
