package com.example.microservice.libraryservice.book;

import com.example.microservice.libraryservice.book.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/library/books")
public class LibraryBooksController {

    @Autowired
    private LibraryBooksService libraryBooksService;

    @GetMapping
    public List<BookDto> listAllBooks() {
        return libraryBooksService.getAllBooks();
    }

    @GetMapping("/{book_id}")
    public ResponseEntity<BookDto> getBookInfo(@PathVariable("book_id") UUID id) {
        return ResponseEntity.of(libraryBooksService.getBookInfo(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UUID addNewBook(@RequestBody BookDto bookDto) {
        return libraryBooksService.addNewBook(bookDto);
    }

    @DeleteMapping("/{book_id}")
    public void deleteBook(@PathVariable("book_id") UUID id) {
        libraryBooksService.deleteBook(id);
    }
}
