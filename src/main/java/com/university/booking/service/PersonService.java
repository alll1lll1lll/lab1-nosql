package com.university.booking.service;

import com.university.booking.dto.PersonRequest;
import com.university.booking.model.Person;
import com.university.booking.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public Person createPerson(PersonRequest request) {
        Person person = Person.builder()
                .id(request.getId())
                .name(request.getName())
                .role(request.getRole())
                .build();

        return personRepository.save(person);
    }

    public Person getPerson(String id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Person not found with id: " + id));
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public void deletePerson(String id) {
        personRepository.delete(id);
    }
}