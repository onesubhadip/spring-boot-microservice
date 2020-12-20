package com.example.microservice.bookservice;

import com.example.microservice.bookservice.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepo extends MongoRepository<Book, UUID> {

    Optional<Book> findById(UUID id);
}
