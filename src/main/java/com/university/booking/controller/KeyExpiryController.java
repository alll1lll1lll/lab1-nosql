package com.university.booking.controller;

import com.university.booking.listener.KeyExpiryListener;
import com.university.booking.web.AdminOnly;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/research")
@RequiredArgsConstructor
public class KeyExpiryController {

    private final KeyExpiryListener keyExpiryListener;

    @AdminOnly
    @GetMapping("/expired")
    public Map<String, Long> getExpiredStats() {
        return keyExpiryListener.getExpiredStats();
    }
}
