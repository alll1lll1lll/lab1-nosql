package com.university.booking.dto;

import com.university.booking.model.Person.PersonRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonRequest {

    @NotBlank
    private String id;

    @NotBlank
    private String name;

    @NotNull
    private PersonRole role;
}
