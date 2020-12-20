package com.example.microservice.bookservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@ToString
@Document("books")
public class Book {
    @Id
    private UUID id;
    private String title;
    private String author;
    private String publisher;
}
