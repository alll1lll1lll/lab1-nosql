package com.university.booking.service;

import com.university.booking.dto.PersonRequest;
import com.university.booking.exception.ResourceNotFoundException;
import com.university.booking.model.Person;
import com.university.booking.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@AllArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public Person createPerson(PersonRequest request) {
        Person person = Person.builder()
                .isuId(generateUniqueId())
                .lastName(request.getLastName())
                .firstName(request.getFirstName())
                .middleName(request.getMiddleName())
                .role(request.getRole())
                .build();

        return personRepository.save(person);
    }

    public Person getPerson(String id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public void deletePerson(String id) {
        personRepository.deleteById(id);
    }

    private String generateUniqueId() {
        String id;
        do {
            id = String.valueOf(ThreadLocalRandom.current().nextInt(100000, 1000000));
        } while (personRepository.existsById(id));
        return id;
    }
}
