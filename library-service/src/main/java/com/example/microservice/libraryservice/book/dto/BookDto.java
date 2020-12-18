package com.example.microservice.libraryservice.book.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class BookDto {
    private UUID id;
    private String title;
    private String author;
    private String publisher;
}