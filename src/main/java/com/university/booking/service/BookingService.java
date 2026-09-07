package com.university.booking.service;

import com.university.booking.repository.BookingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
}