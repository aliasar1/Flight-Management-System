package com.company;

import java.io.Serializable;

public class NodeFlightRoute implements Serializable {
    public static int nextRouteId = 0;
    int routeId;

    public String getDate() {
        return date;
    }

    public String date;
    FlightsList flights;

    public NodeFlightRoute next = null;

    public NodeFlightRoute(String date) {
        this.date = date;
        this.routeId = nextRouteId;
        nextRouteId++;
        flights = new FlightsList();
    }

    public int getRouteId() {
        return routeId;
    }

    public NodeFlight[] getFlights(String citySource, String cityDestination) {
        int j = 0; //count for cities nodes between source city and destination city

        // to check if the source and destination match in the flight
        boolean source = false;
        boolean destination = false;

        NodeFlight temp = null; // to preserve the original flights head
        NodeFlight current = flights.head;
        while (current != null) {
            if (citySource.equals(current.source.name)) {
                source = true;
                // set the head where the source city is
                temp = current;
            }
            // count cities from the source if true
            if (source)
                j += 1;
            if (cityDestination.equals(current.destination.name)) {
                destination = true;
                //break if destination is found
                break;
            }
            current = current.next;
        }

        //setting current to newly set flights head
        int i = 0; //index for cities array
        if (source && destination) {
            NodeFlight[] flights = new NodeFlight[j];
            while (temp != null && j != 0) {
                flights[i] = temp;
                temp = temp.next;
                j--;
                i++;
            }
            return flights;
        }
        return null;

    }

    // helper function to find the best route
    public City[] getCities(String citySource, String cityDestination) {

        int j = 0; //count for cities nodes between source city and destination city

        // to check if the source and destination match in the flight
        boolean source = false;
        boolean destination = false;

        NodeFlight temp = null; // to preserve the original flights head
        NodeFlight current = flights.head;
        while (current != null) {
            if (citySource.equals(current.source.name)) {
                source = true;
                // set the head where the source city is
                temp = current;
            }
            // count cities from the source if true
            if (source)
                j += 1;
            if (cityDestination.equals(current.destination.name)) {
                destination = true;
                //break if destination is found
                break;
            }
            current = current.next;
        }
        //setting current to newly set flights head
        int i = 0; //index for cities array
        if (source && destination) {
            City[] cities = new City[j + 1];
            while (temp != null) {
                cities[i] = temp.source;
                i += 1;
                if (temp.destination.name.equals(cityDestination)) {
                    cities[i] = temp.destination;
                    break;
                }
                temp = temp.next;
            }
            return cities;
        }
        return null;
    }

    public void display() {
        System.out.println("Route ID: " + this.routeId);
        System.out.println("Date    : " + getDate());
        flights.display();
        System.out.println();
    }
}
