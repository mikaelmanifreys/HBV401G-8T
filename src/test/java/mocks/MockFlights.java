package mocks;

import com.example.hbv401g8t.Flights;

/**
 * Mock implementation of the Flights class.
 * This simulates an external component (another team's Flights module).
 */
public class MockFlights extends Flights {

    public MockFlights() {
        // Sendum falsk gögn í constructor super-klasans
        super("MOCK123", "MockAir", "Testville", "00:00", "03:00");
    }

    @Override
    public String getFlightDetails() {
        // Skilum stöðugu, prófanlegu strengsgildi
        return "flight MOCK123 with MockAir on 00:00 to 03:00";
    }
}

