package com.example.hbv401g8t.hotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookingDB {

    private static final String URL = "jdbc:postgresql://ep-lingering-voice-a55okvue-pooler.us-east-2.aws.neon.tech/hotel?sslmode=require";
    private static final String USER = "hotel_owner";
    private static final String PASSWORD = "npg_nWObTCF4kms9";

    public void saveBooking(Booking booking) {
        String sql = "INSERT INTO Booking (hotelid, bookingid, fromdate, todate, guestname) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, booking.getHotelId());
            stmt.setInt(2, booking.getBookingId());
            stmt.setDate(3, new java.sql.Date(booking.getFrom().getTime()));
            stmt.setDate(4, new java.sql.Date(booking.getTo().getTime()));
            stmt.setString(5, booking.getGuestName());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeBooking(int bookingID) {
        String sql = "DELETE FROM Booking WHERE bookingid = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bookingID);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Booking getBooking(int bookingID) {
        String sql = "SELECT * FROM Booking WHERE bookingid = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bookingID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Booking(
                        rs.getInt("hotelid"),
                        rs.getInt("bookingid"),
                        rs.getDate("fromdate"),
                        rs.getDate("todate"),
                        rs.getString("guestname")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Booking> getBookingsByHotel(int hotelID) {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM Booking WHERE hotelid = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, hotelID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                bookings.add(new Booking(
                        rs.getInt("hotelid"),
                        rs.getInt("bookingid"),
                        rs.getDate("fromdate"),
                        rs.getDate("todate"),
                        rs.getString("guestname")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookings;
    }
}
