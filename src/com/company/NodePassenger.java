package com.company;

import java.io.Serializable;

public class NodePassenger implements Serializable {
    public NodePassenger next;
    public static int counter = 1;
    public String passengerName;
    public int passengerId; // generated automatically
    public String sourceCity;
    public String destinationCity;
    public int flightId;
    //    public String gender;
    //    public int passportNumber;
    //    public String nationality;

    public int getPassengerId() {
        return this.passengerId;
    }

    public int getFlightId() {
        return flightId;
    }

    public String getSourceCity() {
        return sourceCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public NodePassenger(String name, int flightId,
            /*String gender, int passportNumber, String nationality,*/ String source, String destination) {
        this.passengerName = name;
        //        this.gender = gender;
        //        this.passportNumber = passportNumber;
        //        this.nationality = nationality;
        this.sourceCity = source;
        this.destinationCity = destination;
        this.flightId = flightId;
        this.passengerId = counter;
        //        insertFirst(counter + "-" + name + "-" + gender + "-" + passportNumber + "-" + nationality + "-" + source + "-" + destination);
        counter++;
    }

    public void display() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "Passenger ID : " + getPassengerId() + "\nName : " + passengerName + "\nSource City : " + sourceCity
                + "\nDestination City : " + destinationCity;
    }
}
