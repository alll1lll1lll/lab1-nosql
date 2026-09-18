package com.university.booking.service;

import com.university.booking.dto.BookingRequest;
import com.university.booking.enums.BookingStatus;
import com.university.booking.model.Booking;
import com.university.booking.repository.BookingRepository;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    public Booking createBooking(BookingRequest request) {

        Booking booking = Booking.builder()
                .id(UUID.randomUUID().toString())
                .personId(request.getPersonId())
                .roomId(request.getRoomId())
                .categoryId(request.getCategoryId())
                .eventName(request.getEventName())
                .eventDate(request.getEventDate())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .participantCount(request.getParticipantCount())
                .contactPhone(request.getContactPhone())
                .status(BookingStatus.DRAFT)
                .createdAt(LocalDateTime.now())
                .build();

        return bookingRepository.save(booking);
    }

    public Booking getBooking(String id) {
        
        return bookingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Booking not found with id: " + id));
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking submitBooking(String id) {
        Booking booking = getBooking(id);
        booking.setStatus(BookingStatus.UNDER_REVIEW);

        return bookingRepository.save(booking);
    }

    public Booking approveBooking(String id, String adminId) {
        Booking booking = getBooking(id);
        booking.setStatus(BookingStatus.APPROVED);
        booking.setProcessedBy(adminId);
        booking.setProcessedAt(LocalDateTime.now());

        return bookingRepository.save(booking);
    }

    public Booking rejectBooking(String id, String adminId) {
        Booking booking = getBooking(id);
        booking.setStatus(BookingStatus.REJECTED);
        booking.setProcessedBy(adminId);
        booking.setProcessedAt(LocalDateTime.now());

        return bookingRepository.save(booking);
    }

    public void deleteBooking(String id) {
        bookingRepository.delete(id);
    }
}
