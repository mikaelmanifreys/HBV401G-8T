package com.example.hbv401g8t.hotel;

import java.util.ArrayList;
import java.util.List;

public class HotelController {

    public Hotel getHotelInfo(int hotelID) {
        return DatabaseConnection.getHotelFromDB(hotelID);
    }

    public List<Hotel> getAllHotels() {
        return DatabaseConnection.getAllHotelsFromDB();
    }

    public boolean updateAvailableRooms(int hotelID, int rooms) {
        Hotel hotel = DatabaseConnection.getHotelFromDB(hotelID);
        if (hotel != null) {
            DatabaseConnection.updateAvailableRooms(hotelID, rooms);
            return true;
        }
        return false;
    }


    public List<Hotel> searchHotelByName(List<Hotel> hotels, String name) {
        List<Hotel> result = new ArrayList<>();
        for (Hotel hotel : hotels) {
            if (hotel.getName() != null && hotel.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(hotel);
            }
        }
        return result;
    }


    public List<Hotel> searchHotelByRegion(List<Hotel> hotels, String region) {
        List<Hotel> result = new ArrayList<>();
        for (Hotel hotel : hotels) {
            if (hotel.getRegion() != null && hotel.getRegion().equalsIgnoreCase(region)) {
                result.add(hotel);
            }
        }
        return result;
    }


    public boolean addBooking(int hotelID, Booking booking) {
        if (checkRoomAvailability(hotelID)) {
            new BookingDB().saveBooking(booking);
            int newRoomCount = DatabaseConnection.getHotelFromDB(hotelID).getAvailableRooms() - 1;
            DatabaseConnection.updateAvailableRooms(hotelID, newRoomCount);
            return true;
        }
        return false;
    }

    public boolean cancelBooking(int bookingID) {
        Booking booking = new BookingDB().getBooking(bookingID);
        if (booking != null) {
            new BookingDB().removeBooking(bookingID);
            Hotel hotel = DatabaseConnection.getHotelFromDB(booking.getHotelId());
            if (hotel != null) {
                int newRoomCount = hotel.getAvailableRooms() + 1;
                DatabaseConnection.updateAvailableRooms(hotel.getId(), newRoomCount);
            }
            return true;
        }
        return false;
    }


    public boolean updateAmenities(int hotelID, Amenities amenities) {
        Hotel hotel = DatabaseConnection.getHotelFromDB(hotelID);
        if (hotel != null) {
            DatabaseConnection.updateAmenitiesInDB(hotelID, amenities);
            hotel.setAmenities(amenities);  // update in-memory object
            return true;
        }
        return false;
    }


    public boolean checkRoomAvailability(int hotelID) {
        Hotel hotel = DatabaseConnection.getHotelFromDB(hotelID);
        if (hotel != null) {
            return hotel.getAvailableRooms() > 0;
        }
        return false;
    }
}
