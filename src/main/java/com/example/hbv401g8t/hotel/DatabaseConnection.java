package com.example.hbv401g8t.hotel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {

    public static Hotel getHotelFromDB(int hotelId) {
        String url = "jdbc:postgresql://ep-lingering-voice-a55okvue-pooler.us-east-2.aws.neon.tech/hotel?sslmode=require";
        String user = "hotel_owner";
        String password = "npg_nWObTCF4kms9";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Hotel WHERE id = " + hotelId + ";");

            if (rs.next()) {
                Hotel hotel = new Hotel();
                hotel.setId(rs.getInt("id"));
                hotel.setName(rs.getString("name"));
                hotel.setAddress(rs.getString("address"));
                hotel.setRegion(rs.getString("region"));
                hotel.setAvailableRooms(rs.getInt("availablerooms"));
                hotel.setRatings(rs.getInt("ratings"));
                hotel.setPrice(rs.getInt("price"));
                // amenities yrði sótt sérstaklega ef það er í annarri töflu
                return hotel;
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void updateAvailableRooms(int hotelId, int newRoomCount) {
        String url = "jdbc:postgresql://ep-lingering-voice-a55okvue-pooler.us-east-2.aws.neon.tech/hotel?sslmode=require";
        String user = "hotel_owner";
        String password = "npg_nWObTCF4kms9";

        String updateSQL = "UPDATE Hotel SET availablerooms = ? WHERE id = ?";

        try (
                Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement stmt = conn.prepareStatement(updateSQL)
        ) {
            stmt.setInt(1, newRoomCount);
            stmt.setInt(2, hotelId);
            stmt.executeUpdate();
            System.out.println("Updated hotel " + hotelId + " with " + newRoomCount + " available rooms.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Hotel> getAllHotelsFromDB() {
        List<Hotel> hotels = new ArrayList<>();
        String url = "jdbc:postgresql://ep-lingering-voice-a55okvue-pooler.us-east-2.aws.neon.tech/hotel?sslmode=require";
        String user = "hotel_owner";
        String password = "npg_nWObTCF4kms9";

        String query = "SELECT * FROM Hotel";

        try (
                Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                Hotel hotel = new Hotel();
                hotel.setId(rs.getInt("id"));
                hotel.setName(rs.getString("name"));
                hotel.setAddress(rs.getString("address"));
                hotel.setRegion(rs.getString("region"));
                hotel.setAvailableRooms(rs.getInt("availablerooms"));
                hotel.setRatings(rs.getInt("ratings"));
                hotel.setPrice(rs.getInt("price"));
                hotels.add(hotel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return hotels;
    }

    public static void updateAmenitiesInDB(int hotelId, Amenities amenities) {
        String url = "jdbc:postgresql://ep-lingering-voice-a55okvue-pooler.us-east-2.aws.neon.tech/hotel?sslmode=require";
        String user = "hotel_owner";
        String password = "npg_nWObTCF4kms9";

        String sql = "UPDATE Amenities SET spa = ?, breakfast = ?, transport = ?, gym = ?, swimmingpool = ?, daycare = ?, " +
                "restaurant = ?, minibar = ?, roomservice = ?, bar = ?, wifi = ?, washingmachine = ? WHERE hotelid = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, amenities.getProperty("spa"));
            stmt.setBoolean(2, amenities.getProperty("breakfast"));
            stmt.setBoolean(3, amenities.getProperty("transport"));
            stmt.setBoolean(4, amenities.getProperty("gym"));
            stmt.setBoolean(5, amenities.getProperty("swimmingpool"));
            stmt.setBoolean(6, amenities.getProperty("daycare"));
            stmt.setBoolean(7, amenities.getProperty("restaurant"));
            stmt.setBoolean(8, amenities.getProperty("minibar"));
            stmt.setBoolean(9, amenities.getProperty("roomservice"));
            stmt.setBoolean(10, amenities.getProperty("bar"));
            stmt.setBoolean(11, amenities.getProperty("wifi"));
            stmt.setBoolean(12, amenities.getProperty("washingmachine"));
            stmt.setInt(13, hotelId);

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Amenities getAmenitiesFromDB(int hotelId) {
        String url = "jdbc:postgresql://ep-lingering-voice-a55okvue-pooler.us-east-2.aws.neon.tech/hotel?sslmode=require";
        String user = "hotel_owner";
        String password = "npg_nWObTCF4kms9";
        String sql = "SELECT * FROM Amenities WHERE hotelid = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, hotelId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Amenities(
                        rs.getBoolean("spa"),
                        rs.getBoolean("breakfast"),
                        rs.getBoolean("transport"),
                        rs.getBoolean("gym"),
                        rs.getBoolean("swimmingpool"),
                        rs.getBoolean("daycare"),
                        rs.getBoolean("restaurant"),
                        rs.getBoolean("minibar"),
                        rs.getBoolean("roomservice"),
                        rs.getBoolean("bar"),
                        rs.getBoolean("wifi"),
                        rs.getBoolean("washingmachine")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
