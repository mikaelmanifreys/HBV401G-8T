package com.example.hbv401g8t.hotel;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter hotel ID: ");
        int hotelId = scanner.nextInt();

        Hotel hotel = DatabaseConnection.getHotelFromDB(hotelId);

        if (hotel != null) {
            System.out.println("Hotel ID: " + hotel.getId());
            System.out.println("Name: " + hotel.getName());
            System.out.println("Region: " + hotel.getRegion());
            System.out.println("Address: " + hotel.getAddress());
            System.out.println("Available Rooms: " + hotel.getAvailableRooms());
            System.out.println("Ratings: " + hotel.getRatings());
        } else {
            System.out.println("No hotel found with ID " + hotelId);
        }


        scanner.close();
    }
}

