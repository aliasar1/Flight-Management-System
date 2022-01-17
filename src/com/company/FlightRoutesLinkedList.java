package com.company;

import java.io.Serializable;

public class FlightRoutesLinkedList implements Serializable {
    //public static int FlightID = 0;
    NodeFlightRoute head;

    public FlightRoutesLinkedList() {

    }

    public boolean isEmpty() {
        return (head == null);
    }

    public int addRoute(City[] cities) {
        NodeFlightRoute route = new NodeFlightRoute();
        for (int i = 0; i < cities.length - 1; i++) {
            route.flights.addFlight(cities[i], cities[i + 1]);
        }
        //        FlightID++;
        insertLast(route);
        return route.getRouteId();
    }

    private void insertLast(NodeFlightRoute newNode) {

        if (isEmpty()) {
            newNode.next = head;
            head = newNode;
        } else {
            NodeFlightRoute currentNode = head;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = newNode;
            newNode.next = null;
        }
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Linked list is empty!");
            return;
        }
        System.out.println("FLight Routes Linked list: ");
        NodeFlightRoute current = head;
        while (current != null) {
            current.display();
            current = current.next;
        }
    }

    //get flight routes from all the flights available in flightRoutesLinkedList
    //with the user entered source and destination cities
    //uses getCities from NodeFlightRoute class
    public void getRoutes(String sourceCity, String destinationCity) {
        boolean routeFound = false;
        if (isEmpty()) {
            System.out.println("No flights Available! It's doomsday!");
        } else {
            NodeFlightRoute currentNode = head;
            while (currentNode != null) {
                City[] route = currentNode.getCities(sourceCity, destinationCity);
                if (route != null) {
                    routeFound = true;
                    System.out.println("Flight " + currentNode.getRouteId() + " ");
                    for (int i = 0; i < route.length; i++) {
                        if (i == route.length - 1) {
                            System.out.print(route[i].name);
                        } else {
                            System.out.print(route[i].name + "-->");
                        }
                    }
                }
                System.out.println();
                currentNode = currentNode.next;
            }
            if (!routeFound) {
                System.out.println("No flights available for this route");
            }
        }
    }

    public NodeFlightRoute addPassengerToFlight(NodePassenger passenger, int flightId, FlightRoutesLinkedList frl) {
        NodeFlightRoute current = frl.head; // flightLinkedList's head which is a NodeFlight node

        //Getting connection numbers to add passenger between specific flights
        while (current != null) {
            if (current.getRouteId() == flightId) {
                //flight node (for from-to)
                NodeFlight flight = current.flights.head;
                while (flight != null) {
                    int seatNum; //seat number
                    if (flight.source.name == passenger.sourceCity) {
                        for (int i = 0; i < flight.seats.length; i++) {
                            if (flight.seats[i] == null) {
                                flight.seats[i] = passenger;
                                seatNum = i;
                                //                                -------------
                                if (flight.destination.name != passenger.destinationCity) {
                                    flight = flight.next;
                                } else {
                                    break;
                                }
                                //traverse from source city to destination city and set seats in between
                                while (flight != null) {
                                    for (i = 0; i < flight.seats.length; i++) {
                                        if (flight.seats[i] == null) {
                                            flight.seats[i] = passenger;
                                            seatNum = i;
                                            break;
                                        }
                                    }
                                    if (flight.destination.name == passenger.destinationCity) {
                                        break;
                                    }
                                    flight = flight.next;
                                }
                                // ----------------------------
                                break;
                            }
                        }
                        //------------------------------------------------------------------------------
                        //--------------------------------------------------------------------------------
                    }
                    flight = flight.next;
                }
            }
            current = current.next;
        }
        return current;
    }

    // temporary print seats to check if its working
    public void printSeats(int flightId, FlightRoutesLinkedList frl) {
        NodeFlightRoute current = frl.head;
        NodeFlightRoute temp = frl.head;
        while (current != null) {
            if (current.routeId == flightId) {
                temp = current;
                break;
            }
            current = current.next;
        }
        NodeFlight nf = temp.flights.head;
        while (nf != null) {
            for (int i = 0; i < nf.seats.length; i++) {
                if (nf.seats[i] == null) {
                    System.out.println("Empty");
                } else {
                    System.out.println(nf.seats[i].passengerName);
                }
            }
            System.out.println();
            nf = nf.next;
        }
    }
}
