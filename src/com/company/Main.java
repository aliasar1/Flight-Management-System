package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //regex data format
        String s = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";

//        if (!validateAdmin()){
//            System.out.println("Invalid username or password! System is shutting down...");
//            System.exit(0);
//        }

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
                primaryScreen();
                System.out.println();
                int choice = userInput();
                if (choice == 1) {
                    while (true){
                        try {
                            flightPanel();
                            int c1 = userInput();
                            System.out.println();
                            if (c1 == 1) {
                                boolean cityExist = false;
                                System.out.println("Enter city : ");
                                String cityName = scanner.next().toUpperCase();
                                if (!cityName.matches("[a-zA-Z]+")) {
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
                                }
                            } else if (c1 == 2) {
                                System.out.println("Enter date (DD/MM/YYYY): ");
                                String date = scanner.next();
                                if (!date.matches(s)){
                                    System.out.println("Date format not correct (DD/MM/YYYY)");
                                    continue;
                                }
                                System.out.println("Enter number of cities : ");
                                int cityNum = scanner.nextInt();
                                if (cityNum > 1) {
                                    System.out.println("Available Cities : ");
                                    citiesList.forEach(city -> System.out.println(city.toString()));
                                    City[] cities = new City[cityNum];
                                    boolean isFound = false;
                                    for (int i = 0; (i < cityNum); i++) {
                                        System.out.println("Enter city " + (i + 1) + " : ");
                                        String cityEntered = scanner.next().toUpperCase();
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
                                        fl.addRoute(cities,date);
                                        try {
                                            FileManager.saveRouteSerialized(fl);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        System.out.println("Route Added!");
                                    }
                                }
                                else {
                                    System.out.println("There must be minimum two cities in order to create a route!");
                                }
                            } else if (c1 == 3) {
                                System.out.println("Cities : ");
                                citiesList.forEach(city -> System.out.println(city.toString()));
                            }
                            else if (c1 == 4){
                                fl.display();
                            }
                            else if (c1 == 0){
                                break;
                            }
                            else {
                                System.out.println("INVALID CHOICE!");
                            }
                        }
                        catch (Exception ex){
                            System.out.println(ex.getMessage());
                        }
                    }
                }
                else if (choice == 2){
                    while (true) {
                        try {
                            passengerPanel();
                            int c2 = userInput();
                            scanner.nextLine();
                            if (c2 == 1) {
                                System.out.println("Enter name :");
                                String name = scanner.nextLine().toUpperCase();
                                if (name.equals("") || !name.strip().matches("^[A-Za-z\\s]*$")){
                                    System.out.println("Name must have only alphabets!");
                                    continue;
                                }
                                System.out.println("Enter passport number: ");
                                String passNum = scanner.nextLine().toUpperCase();
                                if (passNum.length() != 8) {
                                    System.out.println("Passport number length must be equal to 8!");
                                    continue;
                                }
                                boolean isPassNumSame = false;
                                for (int i = 0; i < passengersList.listLength(); i++) {
                                    if (passengersList.searchDuplicationPassport(passNum)) {
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
                            } else if (c2 == 2) {
                                System.out.println("Enter passenger id : ");
                                int passengerId = scanner.nextInt();
                                if(passengerId < 0){
                                    System.out.println("Enter correct ID");
                                    continue;
                                }
                                passengersList.displayPassenger(passengerId);
                            } else if (c2 == 3) {
                                passengersList.displayAllPassengers();
                            }else if (c2 == 0) {
                                break;
                            } else {
                                System.out.println("INVALID CHOICE!");
                            }
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                }
                else if (choice == 3){
                    while (true){
                        try {
                            bookingPanel();
                            int c3 = userInput();
                            System.out.println();
                            if (c3 == 1){
                                System.out.println("Enter Source : ");
                                String citySource = scanner.next().toUpperCase();
                                System.out.println("Enter Destination : ");
                                String cityDestination = scanner.next().toUpperCase();
                                fl.getRoutes(citySource, cityDestination);
                            }
                            else if (c3 == 2){
                                System.out.println("Enter Passenger Id : ");
                                int passengerId = scanner.nextInt();
                                NodePassenger passenger = passengersList.findPassenger(passengerId);
                                if (passenger != null) {
                                    System.out.println("Enter Source : ");
                                    String citySource = scanner.next().toUpperCase();
                                    System.out.println("Enter Destination : ");
                                    String cityDestination = scanner.next().toUpperCase();
                                    System.out.println("Available Routes: ");
                                    boolean isAvailable = fl.getRoutes(citySource, cityDestination);
                                    if (!isAvailable)
                                        break;
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
                            }
                            else if (c3 == 3){
                                System.out.println("Enter Route Number");
                                int routeId = scanner.nextInt();
                                System.out.println("Printing All Flight Seats in Route" + routeId + ": ");
                                fl.printSeats(routeId, fl);
                            }
                            else if(c3 == 4){
                                System.out.println("Enter passenger id :");
                                int passengerId = scanner.nextInt();
                                NodePassenger passenger = passengersList.findPassenger(passengerId);
                                if (passenger != null) {
                                    System.out.println("Enter Source : ");
                                    String citySource = scanner.next().toUpperCase();
                                    System.out.println("Enter Destination : ");
                                    String cityDestination = scanner.next().toUpperCase();
                                    System.out.println("Enter Selected Route Id : ");
                                    int routeId = scanner.nextInt();
                                    boolean isAvailable = fl.getRoutes(citySource, cityDestination);
                                    if(isAvailable)
                                        fl.removePassengerFromFlights(passenger,routeId,citySource,cityDestination);
                                }

                            }
                            else if (c3 == 0){
                                break;
                            }
                            else {
                                System.out.println("INVALID CHOICE!");
                            }
                        }
                        catch (Exception ex){
                            System.out.println(ex.getMessage());
                        }
                    }
                }
                else if (choice == 0){
                    System.out.println("Saving files! Thank You!");
                    FileManager.saveAll(passengersList, fl, staticUtils, citiesList);
                    scanner.close();
                    return;
                }
            } catch (Exception ex) {
                System.out.println("INVALID CHOICE!");
            }
        }
    }

    public static void title(){
        System.out.println();
        System.out.println("------------------------------");
        System.out.println("|                             |");
        System.out.println("|         ZAB AIRLINE         |");
        System.out.println("|                             |");
        System.out.println("------------------------------");
        System.out.println();
    }

    public static void primaryScreen(){
        title();
        System.out.println("Press 1: FLIGHTS PANEL");
        System.out.println("Press 2: PASSENGERS PANEL");
        System.out.println("Press 3: BOOKING PANEL");
        System.out.println("PRESS 0: TO EXIT");
    }

    public static int userInput(){
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter your choice: ");
            return scanner.nextInt();
        }
        catch (Exception ex) {
            System.out.println("INVALID INPUT!");
            userInput();
        }
        return -1;
    }

    public static void flightPanel(){
        System.out.println();
        System.out.println("Press 1: ADD CITY");
        System.out.println("Press 2: CREATE ROUTE");
        System.out.println("Press 3: DISPLAY CITIES");
        System.out.println("Press 4: DISPLAY ALL FLIGHTS");
        System.out.println("PRESS 0: TO RETURN BACK");
    }

    public static void passengerPanel(){
        System.out.println();
        System.out.println("Press 1: ADD PASSENGER");
        System.out.println("Press 2: DISPLAY PASSENGER DETAILS");
        System.out.println("Press 3: DISPLAY ALL PASSENGER DETAILS");
        System.out.println("PRESS 0: TO RETURN BACK");
    }

    public static void bookingPanel() {
        System.out.println();
        System.out.println("Press 1: FIND FLIGHT");
        System.out.println("Press 2: BOOK FLIGHT");
        System.out.println("Press 3: PRINT FLIGHT SEATS");
        System.out.println("Press 4: CANCEL FLIGHT");
        System.out.println("PRESS 0: TO RETURN BACK");
    }

    public static boolean validateAdmin(){
        System.out.println("**** ADMIN VERIFICATION ****");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter username: ");
        String uName = sc.nextLine();
        System.out.print("Enter password: ");
        String pass = sc.nextLine();
        return uName.equals("zabairline") && pass.equals("admin123");
    }

}
