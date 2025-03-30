package mocks;

import com.example.hbv401g8t.Flights;

/**
 * Mock implementation of the Flights class.
 */
public class MockFlights extends Flights {

    public MockFlights() {
        // Sendum fölsk gögn í constructor super-klasann
        super("MOCK123", "MockAir", "Testville", "00:00", "03:00");
    }

    @Override
    public String getFlightDetails() {
        // Skilum prófanlegu strengsgildi
        return "flight MOCK123 with MockAir on 00:00 to 03:00";
    }
}

