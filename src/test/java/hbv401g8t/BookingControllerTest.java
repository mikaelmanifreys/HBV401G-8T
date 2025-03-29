package hbv401g8t;

import com.example.hbv401g8t.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BookingControllerTest {

    private BookingController controller;
    private Customer testCustomer;

    Flights flight = new Flights("Flug123", "Icelandair", "Paris", "18:00", "21:00");


    @Before
    public void setUp() {
        controller = new BookingController(new TripPlanner());
        controller.getAvailableTripPackages().clear();
        controller.getAvailableTripPackages().add(new TripPackage("Paris", 1200));
        controller.getAvailableTripPackages().add(new TripPackage("New York", 1800));
        testCustomer = new Customer("Alice", 1, "alice@test.com");
    }

    @Test
    public void testAddFlightToPackage() {
        controller.addFlightToPackage(0, flight);
        TripPackage trip = controller.getAvailableTripPackages().get(0);
        assertEquals(1, trip.getFlights().size());
        assertEquals("Flug123", trip.getFlights().get(0).getFlightDetails().split(" ")[1]);
        assertEquals("Paris", trip.getDestination());
    }

    @Test
    public void testAddFlightToPackageFail() {
        controller.addFlightToPackage(1, flight);
        TripPackage trip = controller.getAvailableTripPackages().get(0);
        assertEquals("New York", trip.getDestination());
    }
}

