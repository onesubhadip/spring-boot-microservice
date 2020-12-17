package com.example.microservice.bookservice;

import com.example.microservice.bookservice.model.Book;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    public Optional<Book> getBookById(UUID id) {
        return bookRepo.findById(id);
    }

    public UUID saveBook(Book book) {
        book.setId(UUID.randomUUID());
        bookRepo.save(book);
        return book.getId();
    }

    public void deleteBook(UUID id) {
        bookRepo.deleteById(id);
    }

    public void updateBook(UUID id, Book newBook) {
        Book oldBook = bookRepo.findById(id).orElseThrow();
        if(StringUtils.isNotBlank(newBook.getAuthor())) {
            oldBook.setAuthor(newBook.getAuthor());
        }
        if(StringUtils.isNotBlank(newBook.getPublisher())) {
            oldBook.setAuthor(newBook.getPublisher());
        }
        if(StringUtils.isNotBlank(newBook.getTitle())) {
            oldBook.setAuthor(newBook.getTitle());
        }
        bookRepo.save(oldBook);
    }
}
