package com.example.hbv401g8t;

public class Hotels {
    private int hotelId;
    private String name;
    private String location;
    private int price;

    public Hotels(String name, String location, int hotelId, int price) {
        this.hotelId = hotelId;
        this.name = name;
        this.location = location;
        this.price = price;
    }

    @Override
    public String toString() {
        return name;
    }


    public String getHotelDetails() {
        return "Hotel " + name + " ID: " + hotelId + " Location: " + location;
    }

    public String getHotelName() {
        return name;
    }

    public String getHotelLocation() {
        return location;
    }

    public int getHotelId() {
        return hotelId;
    }
    

    public int getPrice() {
        return price;
    }

}
