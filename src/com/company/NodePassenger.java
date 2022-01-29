package com.company;

import java.io.Serializable;

public class NodePassenger implements Serializable {
    public static int nextPassengerId = 1;
    public int passengerId;
    public String passengerName;
    public String passNum;
    public NodePassenger next;

    // Time complexity = O(1)
    public int getPassengerId() {
        return this.passengerId;
    }

    // Time complexity = O(1)
    public NodePassenger(String name, String passNum) {
        this.passengerId = nextPassengerId;
        this.passNum = passNum;
        this.passengerName = name;
        nextPassengerId++;
    }

    // Time complexity = O(1)
    public void display() {
        System.out.println(toString());
    }

    // Time complexity = O(1)
    @Override
    public String toString() {
        return "Passenger ID : " + getPassengerId() + "\nName : " + passengerName + "\nPassport Number: " + passNum;
    }
}
