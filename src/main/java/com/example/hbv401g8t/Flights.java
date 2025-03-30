package com.example.hbv401g8t;

public class Flights {
    private String flightId;
    private String airline;
    private String departureTime;
    private String arrivalTime;
    private String destination;

    public Flights (String flightId, String airline, String destination, String departureTime, String arrivalTime){
        this.flightId = flightId;
        this.airline = airline;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.destination = destination;
    }

    public String getFlightDetails() {
        return "flight " + flightId + " with " + airline + " on " + departureTime + " to " + arrivalTime;
    }
}
