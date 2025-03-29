package com.example.hbv401g8t;

public class Hotels {
    private int hotelId;
    private String name;
    private String location;
    private int availableRooms;
    private int bookedRooms;


    public Hotels(String name, String location, int hotelId, int availableRooms, int bookedRooms) {
        this.hotelId = hotelId;
        this.name = name;
        this.location = location;
        this.availableRooms = availableRooms;
        this.bookedRooms = bookedRooms;

    }

    public boolean checkAvailability() {
        if (availableRooms > 0) {
            return true;
        }
        return false;
    }

    public String getHotelDetails(){
        return "Hotel " + name + " ID: " + hotelId + " Location: " + location + " Rooms available? " + checkAvailability();
    }

}
