package com.university.booking.service;

import com.university.booking.dto.BookingRequest;
import com.university.booking.model.Booking;
import com.university.booking.repository.BookingRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    public Booking createBooking(BookingRequest request) {
        return null;
    }

    public Booking getBooking(String id) {
        return null;
    }

    public List<Booking> getAllBookings() {
        return null;
    }

    public Booking submitBooking(String id) {
        return null;
    }

    public Booking approveBooking(String id, String adminId) {
        return null;
    }

    public Booking rejectBooking(String id, String adminId) {
        return null;
    }

    public void deleteBooking(String id) {
    }
}
