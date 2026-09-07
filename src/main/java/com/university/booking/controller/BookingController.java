package com.university.booking.controller;

import com.university.booking.dto.BookingRequest;
import com.university.booking.model.Booking;
import com.university.booking.service.BookingService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookings")
@AllArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> create(@Valid @RequestBody BookingRequest request) {
        return null;
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getAll() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getById(@PathVariable String id) {
        return null;
    }

    @PutMapping("/{id}/submit")
    public ResponseEntity<Booking> submit(@PathVariable String id) {
        return null;
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<Booking> approve(@PathVariable String id) {
        return null;
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<Booking> reject(@PathVariable String id) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        return null;
    }
}
