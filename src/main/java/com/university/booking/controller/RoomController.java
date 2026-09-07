package com.university.booking.controller;

import com.university.booking.dto.RoomRequest;
import com.university.booking.model.Room;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @PostMapping
    public ResponseEntity<Room> create(@Valid @RequestBody RoomRequest request) {
        return null;
    }

    @GetMapping
    public ResponseEntity<List<Room>> getAll() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getById(@PathVariable String id) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        return null;
    }
}
