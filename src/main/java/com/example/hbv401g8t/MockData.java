package com.example.hbv401g8t;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class MockData {

    public static List<Flights> getMockFlights() {
        return Arrays.asList(
                new Flights("FI123", "Reykjavik", "New York", LocalDate.now(), 10, 30, 12, 45, 5000, 30),
                new Flights("FI124", "Reykjavik", "London", LocalDate.now(), 10, 30, 12, 45, 1500, 60),
                new Flights("FI125", "Reykjavik", "Suðurnes", LocalDate.now(), 10, 30, 12, 45, 2000, 40),
                new Flights("FI126", "Reykjavik", "Austurland", LocalDate.now(), 10, 30, 12, 45, 2500, 50),
                new Flights("FI127", "Reykjavik", "Vesturland", LocalDate.now(), 10, 30, 12, 45, 3000, 30),
                new Flights("FI128", "Akureyri", "Höfuðborgarsvæði", LocalDate.now(), 10, 30, 12, 45, 3500, 20),
                new Flights("FI129", "Reykjavik", "Suðurland", LocalDate.now(), 10, 30, 12, 45, 4000, 30)
        );
    }

    public static List<Hotels> getMockHotels() {
        return Arrays.asList(
                new Hotels("Hótel New York", "New York", 1, 10000, 20),
                new Hotels("Hótel London", "London", 2, 20000, 30)
        );
    }

    public static List<DayTours> getMockDayTours() {
        return Arrays.asList(
                new DayTours("Hjólaferð", "New York", 1, LocalDate.now(), 1500),
                new DayTours("Þyrluflug", "London", 2, LocalDate.now(), 20000),
                new DayTours("Listasafn", "Suðurnes", 3, LocalDate.now(), 1500),
                new DayTours("Fjallganga", "Austurland", 4, LocalDate.now(), 2000),
                new DayTours("Bátsferð", "Vesturland", 5, LocalDate.now(), 1500),
                new DayTours("Hestaferð", "Höfuðborgarsvæði", 6, LocalDate.now(), 10000),
                new DayTours("Fossar", "Suðurland", 7, LocalDate.now(), 2000)
        );
    }
}
