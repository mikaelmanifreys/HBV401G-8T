package hbv401g8t;

import com.example.hbv401g8t.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BookingControllerTest {

    private BookingController controller;
    private Customer testCustomer;

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
        Flights flight = new Flights("XY123", "Icelandair", "Paris", "18:00", "21:00");
        controller.addFlightToPackage(0, flight);
        TripPackage trip = controller.getAvailableTripPackages().get(0);
        assertEquals(1, trip.getFlights().size());
        assertEquals("XY123", trip.getFlights().get(0).getFlightDetails().split(" ")[1]);
    }

    @Test
    public void testAddHotelToPackage() {
        Hotels hotel = new Hotels("Test Hotel", "Paris", 1, 10, 30);
        controller.addHotelToPackage(0, hotel);
        TripPackage trip = controller.getAvailableTripPackages().get(0);
        assertEquals(1, trip.getHotel().size());
        assertEquals(1, );
    }

}
