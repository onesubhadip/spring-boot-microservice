package com.example.microservice.bookservice;

import com.example.microservice.bookservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepo extends JpaRepository<Book, UUID> {

    Optional<Book> findById(UUID id);
}
