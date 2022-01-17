package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        FlightRoutesLinkedList fl = new FlightRoutesLinkedList();
        PassengerLinkedList pl = new PassengerLinkedList();
        Scanner scanner = new Scanner(System.in);
        StaticUtils staticUtils = new StaticUtils();
        ArrayList<City> citiesList = new ArrayList<City>();
        try {
            staticUtils = FileManager.loadStaticsSerialized();
            citiesList = FileManager.loadCitySerialized(citiesList);
            staticUtils.loadFrom();
            fl = FileManager.loadRouteSerialized(fl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                System.out.println("Main Menu");
                System.out.println("1. Add City");
                System.out.println("2. Create Route");
                System.out.println("3. Display All Flight");
                System.out.println("4. Add Passenger");
                System.out.println("5. Display Passenger");
                System.out.println("6. Display All Passenger");
                System.out.println("7. Find Flight");
                System.out.println("8. Book Flight");
                System.out.println("9. Print Flight Seats");
                System.out.println("10. Exit");
                System.out.println("Enter your choice: ");
                int choice = scanner.nextInt();
                //scanner.nextLine();
                if (choice < 10) {
                    switch (choice) {
                        case 1:
                            System.out.println("Enter city : ");
                            String cityName = scanner.next();
                            City city = new City(cityName);
                            citiesList.add(city);
                            try {
                                FileManager.saveCitySerialized(citiesList);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            System.out.println("City added!");
                            scanner.nextLine();
                            scanner.nextLine();
                            break;
                        case 2:
                            System.out.println("Enter number of cities : ");
                            int cityNum = scanner.nextInt();
                            scanner.nextLine();
                            City[] cities = new City[cityNum];
                            boolean isFound = false;
                            for (int i = 0; (i < cityNum); i++) {
                                System.out.println("Enter city " + (i + 1) + " : ");
                                String cityEntered = scanner.next();
                                for (City city1 : citiesList) {
                                    if (city1.name.equals(cityEntered)) {
                                        cities[i] = city1;
                                        isFound = true;
                                    }
                                }
                                if (!isFound) {
                                    System.out.println("City not found!");
                                    break;
                                }
                            }
                            if (isFound) {
                                fl.addRoute(cities);
                                try {
                                    FileManager.saveRouteSerialized(fl);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                System.out.println("Route Added!");
                                scanner.nextLine();
                            }
                            break;
                        case 3:
                            fl.display();
                            scanner.nextLine();
                            scanner.nextLine();
                            break;
                        case 4:
                            System.out.println("Enter name : ");
                            String name = scanner.next();
                            System.out.println("Enter Flight Number : ");
                            int flightNumber = scanner.nextInt();
                            System.out.println("Enter Source : ");
                            String citySource = scanner.next();
                            System.out.println("Enter Destination : ");
                            String cityDestination = scanner.next();
                            NodePassenger passenger = pl.addPassenger(name, flightNumber, citySource, cityDestination);
                            System.out.println("Your Passenger ID is : " + passenger.getPassengerId());
                            scanner.nextLine();
                            scanner.nextLine();
                            break;
                        case 5:
                            System.out.println("Enter passenger id : ");
                            int passengerId = scanner.nextInt();
                            pl.displayPassenger(passengerId);
                            scanner.nextLine();
                            scanner.nextLine();
                            break;
                        case 6:
                            pl.displayAllPassengers();
                            scanner.nextLine();
                            scanner.nextLine();
                            break;
                        case 7:
                            System.out.println("Enter Source : ");
                            citySource = scanner.next();
                            System.out.println("Enter Destination : ");
                            cityDestination = scanner.next();
                            fl.getRoutes(citySource, cityDestination);
                            scanner.nextLine();
                            scanner.nextLine();
                            break;
                        case 8:
                            System.out.println("Enter Passenger Id : ");
                            passengerId = scanner.nextInt();
                            passenger = pl.findPassenger(passengerId);
                            fl.addPassengerToFlight(passenger, passenger.getFlightId(), fl);
                            System.out.println("Passenger Added To flight : " + passenger.getFlightId());
                            scanner.nextLine();
                            scanner.nextLine();
                            break;
                        case 9:
                            System.out.println("Enter Flight Number");
                            flightNumber = scanner.nextInt();
                            System.out.println("Printing All seats : ");
                            fl.printSeats(flightNumber, fl);
                            scanner.nextLine();
                            scanner.nextLine();
                            break;

                        default:
                            System.out.println("Wrong choice");
                            break;
                    }
                } else {
                    System.out.println("Thank You!");
                    scanner.close();
                    return;
                }
            }
            catch (Exception ex){
                System.out.println(ex.getMessage());
                scanner.nextLine();
            }
        }


        //        System.out.println("Thank you!");

        //        PassengerLinkedList pl = new PassengerLinkedList();
        //        NodePassenger passenger1 =pl.addPassenger("aleem","karachi","u.s.a");
        //        NodePassenger passenger2 =pl.addPassenger("bilal","islamabad","france");
        //        NodePassenger passenger3 =pl.addPassenger("aliasar","dubai","u.k");
        //        NodePassenger passenger4 =  pl.addPassenger("hammad","karachi","dubai");
        //        pl.displayAllPassengers();
        //        pl.displayPassenger(3);

        //        City karachi = new City("karachi");
        //        City islamabad = new City("islamabad");
        //        City dubai = new City("dubai");
        //        City Uk = new City("u.k");
        //        City france = new City("france");
        //        City usa = new City("u.s.a");
        //
        //        City[] cities1 = {karachi,islamabad,dubai,Uk,france,usa};
        //        City[] cities2 = {islamabad,dubai,Uk,france,usa,karachi};
        //        City[] cities3 = {islamabad,karachi};
        //        City[] cities4 = {karachi,islamabad,dubai};
        ////
        //        FlightRoutesLinkedList fl = new FlightRoutesLinkedList();
        //        fl.addRoute(cities1);
        //        fl.addRoute(cities2);
        //        fl.addRoute(cities3);
        //        fl.addRoute(cities4);
        //        fl.addPassengerToFlight(passenger2,1,fl);
        //        NodeFlightRoute flt = fl.addPassengerToFlight(passenger3,0,fl);
        //        flt = fl.addPassengerToFlight(passenger2,0,fl);
        //        flt = fl.addPassengerToFlight(passenger4,0,fl);
        //        System.out.println(flt.getRouteId());
        //        NodeFlight nf = flt.flights.head;
        //        while(nf != null){
        //            System.out.println(nf.source.name);
        //            System.out.println(Arrays.toString(nf.seats));
        //            nf = nf.next;
        //        }
        //        System.out.println(Arrays.toString(flt.flights.head.seats));
        //        fl.printSeats(0,fl);
        //        System.out.println(fl.head.flights.head.seats[0]);

        //        fl.display();
        //        System.out.println();
        //        fl.getRoutes("islamabad","karachi");
        //        System.out.println();
        //        fl.display();
    }
}
