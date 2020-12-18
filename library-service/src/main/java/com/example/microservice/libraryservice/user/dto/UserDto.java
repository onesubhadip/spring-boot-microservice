package com.example.microservice.libraryservice.user.dto;

import com.example.microservice.libraryservice.book.dto.BookDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserDto {

    private UUID id;

    private String firstName;

    private String lastName;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate dob;

    private List<BookDto> issuedBooks;
}
