package com.example.hbv401g8t;

import java.time.LocalDate;

public class Flights {
    private String flightId;
    private String airline;
    private int departureTimeHour;
    private int departureTimeMin;
    private int arrivalTimeHour;
    private int arrivalTimeMin;
    private String departurePlace;
    private String destination;
    private LocalDate date;

    public Flights(String flightId, String airline, String departurePlace, String destination, LocalDate date, int departureTimeHour, int departureTimeMin, int arrivalTimeHour, int arrivalTimeMin) {
        this.flightId = flightId;
        this.airline = airline;
        this.departureTimeHour = departureTimeHour;
        this.departureTimeMin = departureTimeMin;
        this.arrivalTimeHour = arrivalTimeHour;
        this.arrivalTimeMin = arrivalTimeMin;
        this.date = date;
        this.departurePlace = departurePlace;
        this.destination = destination;
    }

    public String getFlightDetails() {
        return "flight " + flightId + " with " + airline + "from" + departurePlace + "to" + destination + " on " + date + departureTimeHour + ":" + departureTimeMin + " to " + arrivalTimeHour + ":" + arrivalTimeMin;
    }

    public String getFlightPlaces() {
        return departurePlace + " - " + destination;
    }
}
