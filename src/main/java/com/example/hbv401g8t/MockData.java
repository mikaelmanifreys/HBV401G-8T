package com.example.hbv401g8t;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class MockData {

    public static List<Flights> getMockFlights() {
        return Arrays.asList(
                new Flights("FI123", "Reykjavik", "New York", LocalDate.now(), 10, 30, 12, 45, 500),
                new Flights("FI124", "Reykjavik", "London", LocalDate.now(), 10, 30, 12, 45, 1500)
        );
    }

    public static List<Hotels> getMockHotels() {
        return Arrays.asList(
                new Hotels("Hótel New York", "New York", 1, 10000),
                new Hotels("Hótel London", "London", 2, 20000)
        );
    }

    public static List<DayTours> getMockDayTours() {
        return Arrays.asList(
                new DayTours("Hjólaferð", "New York", 1, LocalDate.now(), 1500),
                new DayTours("Þyrluflug", "London", 2, LocalDate.now(), 2000)
        );
    }
}
