package com.university.booking.controller;

import com.university.booking.dto.PersonRequest;
import com.university.booking.model.Person;
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
@RequestMapping("/api/persons")
public class PersonController {

    @PostMapping
    public ResponseEntity<Person> create(@Valid @RequestBody PersonRequest request) {
        return null;
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAll() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getById(@PathVariable String id) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        return null;
    }
}
