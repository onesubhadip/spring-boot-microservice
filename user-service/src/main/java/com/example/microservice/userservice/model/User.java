package com.example.microservice.userservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
public class User {

    @Id
    private UUID id;

    private String firstName;

    private String lastName;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate dob;
}
