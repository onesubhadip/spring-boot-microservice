package com.example.microservice.bookservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
public class Book {
    @Id
    private UUID id;
    private String title;
    private String author;
    private String publisher;
}
