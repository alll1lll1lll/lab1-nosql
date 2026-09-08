package com.university.booking.service;

import com.university.booking.dto.PersonRequest;
import com.university.booking.model.Person;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonService {

    public Person createPerson(PersonRequest request) {
        return null;
    }

    public Person getPerson(String id) {
        return null;
    }

    public List<Person> getAllPersons() {
        return null;
    }

    public void deletePerson(String id) {
    }
}
