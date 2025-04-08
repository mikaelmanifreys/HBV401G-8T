package com.example.hbv401g8t.hotel;

public class Amenities {
    private Boolean spa;
    private Boolean breakfast;
    private Boolean transport;
    private Boolean gym;
    private Boolean swimmingPool;
    private Boolean daycare;
    private Boolean restaurant;
    private Boolean miniBar;
    private Boolean roomService;
    private Boolean bar;
    private Boolean wifi;
    private Boolean washingMachine;

    public Amenities() {
        this(false, false, false, false, false, false, false, false, false, false, false, false);
    }

    public Amenities(
            boolean spa,
            boolean breakfast,
            boolean transport,
            boolean gym,
            boolean swimmingPool,
            boolean daycare,
            boolean restaurant,
            boolean miniBar,
            boolean roomService,
            boolean bar,
            boolean wifi,
            boolean washingMachine
    ) {
        this.spa = spa;
        this.breakfast = breakfast;
        this.transport = transport;
        this.gym = gym;
        this.swimmingPool = swimmingPool;
        this.daycare = daycare;
        this.restaurant = restaurant;
        this.miniBar = miniBar;
        this.roomService = roomService;
        this.bar = bar;
        this.wifi = wifi;
        this.washingMachine = washingMachine;
    }


    public void setProperty(String property, Boolean value) {
        switch (property.toLowerCase()) {
            case "spa":
                this.spa = value;
                break;
            case "breakfast":
                this.breakfast = value;
                break;
            case "transport":
                this.transport = value;
                break;
            case "gym":
                this.gym = value;
                break;
            case "swimmingpool":
                this.swimmingPool = value;
                break;
            case "daycare":
                this.daycare = value;
                break;
            case "restaurant":
                this.restaurant = value;
                break;
            case "minibar":
                this.miniBar = value;
                break;
            case "roomservice":
                this.roomService = value;
                break;
            case "bar":
                this.bar = value;
                break;
            case "wifi":
                this.wifi = value;
                break;
            case "washingmachine":
                this.washingMachine = value;
                break;
            default:
                throw new IllegalArgumentException("Unknown property: " + property);
        }
    }

    public Boolean getProperty(String property) {
        switch (property.toLowerCase()) {
            case "spa":
                return this.spa;
            case "breakfast":
                return this.breakfast;
            case "transport":
                return this.transport;
            case "gym":
                return this.gym;
            case "swimmingpool":
                return this.swimmingPool;
            case "daycare":
                return this.daycare;
            case "restaurant":
                return this.restaurant;
            case "minibar":
                return this.miniBar;
            case "roomservice":
                return this.roomService;
            case "bar":
                return this.bar;
            case "wifi":
                return this.wifi;
            case "washingmachine":
                return this.washingMachine;
            default:
                throw new IllegalArgumentException("Unknown property: " + property);
        }
    }
}
