package com.example.hbv401g8t;

import java.time.LocalDate;

public class Flights {
    private String flightId;
    private int fjoldiFarthega;
    private int departureTimeHour;
    private int departureTimeMin;
    private int arrivalTimeHour;
    private int arrivalTimeMin;
    private String departurePlace;
    private String destination;
    private LocalDate date;

    public Flights(String flightId, int fjoldiFarthega, String departurePlace, String destination, LocalDate date, int departureTimeHour, int departureTimeMin, int arrivalTimeHour, int arrivalTimeMin) {
        this.flightId = flightId;
        this.fjoldiFarthega = fjoldiFarthega;
        this.departureTimeHour = departureTimeHour;
        this.departureTimeMin = departureTimeMin;
        this.arrivalTimeHour = arrivalTimeHour;
        this.arrivalTimeMin = arrivalTimeMin;
        this.date = date;
        this.departurePlace = departurePlace;
        this.destination = destination;
    }

    public String getFlightDetails() {
        return "flight " + flightId + " with " + fjoldiFarthega + "from" + departurePlace + "to" + destination + " on " + date + departureTimeHour + ":" + departureTimeMin + " to " + arrivalTimeHour + ":" + arrivalTimeMin;
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
}
