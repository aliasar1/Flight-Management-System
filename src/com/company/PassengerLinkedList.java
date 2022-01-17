package com.company;

import java.io.Serializable;

public class PassengerLinkedList implements Serializable {
    private NodePassenger head;

    public boolean isEmpty() {
        return (head == null);
    }

    public void displayAllPassengers() {
        if (isEmpty()) {
            System.out.println("Linked list is empty!");
            return;
        }
        System.out.println("Passenger Database ");
        NodePassenger current = head;
        while (current != null) {
            current.display();
            System.out.println();
            current = current.next;
        }
    }

    public void displayPassenger(int id) {
        if (isEmpty()) {
            System.out.println("Linked list is empty!");
            return;
        }
        System.out.println("Passenger profile: ");
        NodePassenger current = head;
        while (current != null) {
            if (id == current.getPassengerId()) {
                current.display();
                break;
            }
            current = current.next;
        }
    }

    public int listLength() {
        NodePassenger current = head;
        int count = 0;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public NodePassenger addPassenger(String name, int flightId,
            /* String gender, int passportNumber, String nationality, */String source, String destination) {
        NodePassenger newNode = new NodePassenger(name, flightId, /*gender,passportNumber,nationality,*/source,
                destination);
        newNode.next = head;
        head = newNode;
        return newNode;
    }

    public NodePassenger findPassenger(int passengerId) {
        NodePassenger current = head;
        while (current != null) {
            if (passengerId == current.getPassengerId()) {
                return current;
            }
        }
        System.out.println("No such passenger Id!");
        return null;
    }

    public void deleteFromPosition(int pos) {
        if (isEmpty()) {
            System.out.println("List is already empty.");
        } else if (pos > listLength() || pos < 1) {
            System.out.println("Invalid position!");
        } else if (head.next == null && pos == 1) {
            head = null;
        } else if (pos == 1) {
            NodePassenger temp = head;
            head = head.next;
            temp.next = null;
        } else {
            NodePassenger temp = head;
            int i = 1;
            while (i < pos - 1) {
                temp = temp.next;
                pos--;
            }
            temp.next = temp.next.next;
        }
    }

    //    public void addPassengerToFlight(NodePassenger passenger,int flightId,FlightRoutesLinkedList frl) {
    //        NodeFlightRoute current = frl.head; // flightLinkedList's head which is a NodeFlight node
    ////        int startConnectionNum;
    ////        int endConnectionNum;
    //
    //        //Getting connection numbers to add passenger between specific flights
    //        while (current != null) {
    //            if (current.getFlightId() == flightId) {
    //                //flight node (for from-to)
    //                NodeFlight flight = current.flights.head;
    //                while (flight != null) {
    //                    if (flight.source.name == passenger.sourceCity) {
    //                        //traverse from source city to destination city and set seats in between
    //                        while (flight.destination.name != passenger.destinationCity) {
    //                            int seatNum; //seat number
    //                            for (int i = 0; i < flight.seats.length; i++) {
    //                                if (flight.seats[i] == null) {
    //                                    flight.seats[i] = passenger;
    //                                    seatNum = i;
    //                                }
    //                            }
    //                            flight = flight.next;
    //                        }
    ////                        startConnectionNum = flight.getFlightConnectionsNum();
    //                    }
    ////                    if (flight.destination.name == passenger.destinationCity){
    ////                        endConnectionNum = flight.getFlightConnectionsNum();
    ////                    }
    //                    flight = flight.next;
    //                }
    //            }
    //            current = current.next;
    //        }

    //        //Setting current to frl head again
    //        current = frl.head;
    //
    //        //Adding passenger to the seats array between the connectionNums
    //        //otherwise setting seats array to null
    //        while(current!=null){
    //            NodeFlight flight  = current.flights.head;
    //            while (flight != null) {
    //                if (flight.source.name == passenger.sourceCity) {
    //                    startConnectionNum = flight.getFlightConnectionsNum();
    //                }
    //                if (flight.destination.name == passenger.destinationCity) {
    //                    endConnectionNum = flight.getFlightConnectionsNum();
    //                }
    //                flight = flight.next;
    //            }
    //        }
    //    }
}