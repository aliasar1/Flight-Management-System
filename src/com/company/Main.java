package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        FlightRoutesList fl = new FlightRoutesList();
        PassengerList passengersList = new PassengerList();
        Scanner scanner = new Scanner(System.in);
        StaticUtils staticUtils = new StaticUtils();
        ArrayList<City> citiesList = new ArrayList<City>();
        try {
            staticUtils = FileManager.loadStaticsSerialized();
            passengersList = FileManager.loadPassengersSerialized();
            citiesList = FileManager.loadCitySerialized();
            staticUtils.loadClasses();
            fl = FileManager.loadRouteSerialized(fl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                System.out.println("Main Menu");
                System.out.println("1. Add City");
                System.out.println("2. Create Route");
                System.out.println("3. Display Cities");
                System.out.println("4. Display All Flight");
                System.out.println("5. Add Passenger");
                System.out.println("6. Display Passenger");
                System.out.println("7. Display All Passenger");
                System.out.println("8. Find Flight");
                System.out.println("9. Book Flight");
                System.out.println("10. Print Flight Seats");
                System.out.println("11. Exit");
                System.out.println("Enter your choice: ");
                int choice = scanner.nextInt();
                //scanner.nextLine();
                if (choice < 11) {
                    if (choice == 1) {
                        boolean cityExist = false;
                        System.out.println("Enter city : ");
                        String cityName = scanner.next();
                        if (!cityName.matches("[a-zA-Z]+")){
                            System.out.println("City name must have only alphabets!");
                            continue;
                        }
                        for (City city : citiesList) {
                            if (String.valueOf(city.name).equals(cityName)) {
                                System.out.println("City already exists!\n");
                                cityExist = true;
                                break;
                            }
                        }
                        if (!cityExist) {
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
                        }
                    } else if (choice == 2) {
                        System.out.println("Enter number of cities : ");
                        int cityNum = scanner.nextInt();
                        if (cityNum > 1) {
                            System.out.println("Available Cities : ");
                            citiesList.forEach(city -> System.out.println(city.toString()));
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
                        }
                        else {
                            System.out.println("There must be minimum to cities in order to create a route!");
                        }
                    } else if (choice == 3) {
                        System.out.println("Cities : ");
                        citiesList.forEach(city -> System.out.println(city.toString()));
                        scanner.nextLine();
                        scanner.nextLine();
                    } else if (choice == 4) {
                        fl.display();
                        scanner.nextLine();
                        scanner.nextLine();
                    } else if (choice == 5) {
                        System.out.println("Enter name :");
                        scanner.nextLine();
                        String name = scanner.nextLine();
                        if (name.equals("") || !name.matches("[a-zA-Z]+")) {
                            System.out.println("Name cannot be empty and should contain only alphabets!");
                            continue;
                        }
                        System.out.println("Enter passport number: ");
                        String passNum = scanner.nextLine();
                        if (passNum.length() != 8){
                            System.out.println("Passport number length must be equal to 8!");
                            continue;
                        }
                        boolean isPassNumSame = false;
                        for (int i = 0; i<passengersList.listLength();i++){
                            if (passengersList.searchDuplicationPassport(passNum)){
                                System.out.println("Passenger already exists with same passport number!");
                                isPassNumSame = true;
                                break;
                            }
                        }
                        if (!isPassNumSame) {
                            NodePassenger passenger = passengersList.addPassenger(name, passNum);
                            System.out.println("Passenger added with id " + passenger.passengerId + "!");
                            FileManager.savePassengersSerialized(passengersList);
                            scanner.nextLine();
                            scanner.nextLine();
                        }
                    } else if (choice == 6) {
                        System.out.println("Enter passenger id : ");
                        int passengerId = scanner.nextInt();
                        passengersList.displayPassenger(passengerId);
                        scanner.nextLine();
                    } else if (choice == 7) {
                        passengersList.displayAllPassengers();
                        scanner.nextLine();
                    } else if (choice == 8) {
                        System.out.println("Enter Source : ");
                        String citySource = scanner.next();
                        System.out.println("Enter Destination : ");
                        String cityDestination = scanner.next();
                        fl.getRoutes(citySource, cityDestination);
                        scanner.nextLine();
                        scanner.nextLine();
                    } else if (choice == 9) {
                        System.out.println("Enter Passenger Id : ");
                        int passengerId = scanner.nextInt();
                        NodePassenger passenger = passengersList.findPassenger(passengerId);
                        if (passenger != null) {
                            System.out.println("Enter Source : ");
                            String citySource = scanner.next();
                            System.out.println("Enter Destination : ");
                            String cityDestination = scanner.next();
                            System.out.println("Available Routes: ");
                            fl.getRoutes(citySource, cityDestination);
                            System.out.println("Enter Selected Route Id : ");
                            int routeId = scanner.nextInt();
                            if (fl.verifyRoute(routeId, citySource, cityDestination)) {
                                fl.addPassengerToFlights(passenger, routeId, citySource, cityDestination);
                                System.out.println(
                                        "Flight Booked! " + passenger.passengerName + " is booked on Route "
                                                + routeId);
                            } else {
                                System.out.println("Incorrect information!");
                            }
                        } else {
                            System.out.println("Passenger not found!");
                        }
                        scanner.nextLine();
                        scanner.nextLine();
                    } else if (choice == 10) {
                        System.out.println("Enter Route Number");
                        int routeId = scanner.nextInt();
                        System.out.println("Printing All Flight Seats in Route" + routeId + ": ");
                        fl.printSeats(routeId, fl);
                        scanner.nextLine();
                        scanner.nextLine();
                    }
                } else {
                    System.out.println("Thank You!");
                    scanner.close();
                    return;
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                scanner.nextLine();
            }
        }
    }
}
