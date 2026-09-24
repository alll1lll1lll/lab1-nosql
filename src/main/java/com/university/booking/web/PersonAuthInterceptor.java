package com.university.booking.web;

import com.university.booking.repository.PersonRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class PersonAuthInterceptor implements HandlerInterceptor {

    static final String PERSON_ID_HEADER = "X-Person-Id";

    private final PersonRepository personRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String personId = request.getHeader(PERSON_ID_HEADER);
        if (personId == null || personId.isBlank() || !personRepository.existsById(personId)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Registered person required");
        }
        return true;
    }
}
