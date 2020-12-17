package com.example.microservice.userservice;

import com.example.microservice.userservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<User> getUser(@PathVariable("user_id") UUID id) {
        return ResponseEntity.of(userService.getUser(id));
    }

    @PostMapping("/")
    public UUID createUser(@RequestBody User userDto) {
        return userService.createUser(userDto);
    }

    @DeleteMapping("/{user_id}")
    public void removeUser(@PathVariable("user_id") UUID id) {
        userService.removeUser(id);
    }

    @PutMapping("/{user_id}")
    public void updateUserInfo(@PathVariable("user_id") UUID id, @RequestBody User user) {
        userService.updateUser(id, user);
    }

}
