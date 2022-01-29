package com.company;

import java.io.Serializable;

public class FlightsList implements Serializable {
    private int count = 0;
    public NodeFlight head;

    // Time complexity = O(1)
    public FlightsList() {
        head = null;
    }

    // Time complexity = O(1)
    public int getCount() {
        return count;
    }

    // Time complexity = O(1)
    public boolean isEmpty() {
        return (head == null);
    }

    // Time complexity = O(1)  - For inserting at last Time complexity = O(n)
    public void addFlight(City source, City destination) {
        NodeFlight flight = new NodeFlight(source, destination);
        insertLast(flight);
    }

    // Time complexity = O(n)
    private void insertLast(NodeFlight newNode) {
        if (isEmpty()) {
            newNode.next = head;
            head = newNode;
        } else {
            NodeFlight currentNode = head;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = newNode;
            newNode.next = null;
        }
        this.count++;
    }

    // Time complexity = O(n)
    public void display() {
        if (isEmpty()) {
            System.out.println("Linked list is empty!");
            return;
        }
        System.out.println("Flights Linked list: ");
        NodeFlight current = head;
        while (current != null) {
            current.display();
            current = current.next;
        }
    }

}
