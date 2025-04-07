package com.example.hbv401g8t;

import java.time.LocalDate;

public class Flights {
    private String flightId;
    private int departureTimeHour;
    private int departureTimeMin;
    private int arrivalTimeHour;
    private int arrivalTimeMin;
    private String departurePlace;
    private String destination;
    private LocalDate date;
    private int highestPrice;

    public Flights(String flightId, String departurePlace, String destination, LocalDate date, int departureTimeHour, int departureTimeMin, int arrivalTimeHour, int arrivalTimeMin, int highestPrice) {
        this.flightId = flightId;
        this.departureTimeHour = departureTimeHour;
        this.departureTimeMin = departureTimeMin;
        this.arrivalTimeHour = arrivalTimeHour;
        this.arrivalTimeMin = arrivalTimeMin;
        this.date = date;
        this.departurePlace = departurePlace;
        this.destination = destination;
        this.highestPrice = highestPrice;
    }

    @Override
    public String toString() {
        return departurePlace + "-" + destination;
    }

    public String getFlightDetails() {
        return "flight " + flightId + "from" + departurePlace + "to" + destination + " on " + date + departureTimeHour + ":" + departureTimeMin + " to " + arrivalTimeHour + ":" + arrivalTimeMin;
    }

    public String getFlightPlaces() {
        return departurePlace + " - " + destination;
    }

    public String getDeparturePlace() {
        return departurePlace;
    }

    public String getDestination() {
        return destination;
    }

    public String getFlightId() {
        return flightId;
    }

    public String getArrivalTime() {
        return arrivalTimeHour + ":" + arrivalTimeMin;
    }

    public String getDepartureTime() {
        return departureTimeHour + ":" + departureTimeMin;
    }

    public Object getDate() {
        return date;
    }

    public int getPrice() {
        return highestPrice;
    }
}
