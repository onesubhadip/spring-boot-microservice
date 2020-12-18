package com.example.microservice.libraryservice.user;

import com.example.microservice.libraryservice.issue.LibraryIssueService;
import com.example.microservice.libraryservice.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/library/users")
public class LibraryUserController {

    @Autowired
    private LibraryUserService libraryUserService;

    @Autowired
    private LibraryIssueService libraryIssueService;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return libraryUserService.getAllUsers();
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<UserDto> getUserInfo(@PathVariable("user_id") UUID id) {
        return ResponseEntity.of(libraryUserService.getUserInfo(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UUID registerNewUser(@RequestBody UserDto userDto) {
        return libraryUserService.addUser(userDto);
    }

    @DeleteMapping("/{user_id}")
    public void removeUser(@PathVariable("user_id") UUID id) {
        libraryUserService.removeUser(id);
    }

    @PutMapping("/{user_id}")
    public void removeUser(@PathVariable("user_id") UUID id, @RequestBody UserDto userDto) {
        libraryUserService.updateUserInfo(id, userDto);
    }

    @PostMapping("/{user_id}/books/{book_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public UUID issueBookToUser(@PathVariable("user_id") UUID userId,
                                @PathVariable("book_id") UUID bookId) {

        //TODO assert valid user
        //TODO assert valid book

        return libraryIssueService.issueBookForUser(userId, bookId);
    }

    @DeleteMapping("/{user_id}/books/{book_id}")
    public void releaseBookFromUser(@PathVariable("user_id") UUID userId,
                                @PathVariable("book_id") UUID bookId) {

        //TODO assert valid user

        libraryIssueService.releaseBookForUser(userId, bookId);
    }
}
