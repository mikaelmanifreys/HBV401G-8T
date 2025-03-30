package com.example.hbv401g8t;


// mock class
public class DayTours {
    private int tourId;
    private String name;
    private String location;
    //private List<TimeSlot>;

    public DayTours(String name, String location, int tourId) {
        this.tourId = tourId;
        this.name = name;
        this.location = location;
    }

    public int getTourId() {
        return tourId;
    }

    public String getTourName() {
        return name;
    }

    public String getTourLocation() {
        return location;
    }


}
