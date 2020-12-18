package com.example.microservice.userservice;

import com.example.microservice.userservice.model.User;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public Optional<User> getUser(UUID id) {
        return userRepo.findById(id);
    }

    public UUID createUser(User userDto) {
        userDto.setId(UUID.randomUUID());
        userRepo.saveAndFlush(userDto);
        return userDto.getId();
    }

    public void removeUser(UUID id) {
        userRepo.deleteById(id);
    }

    public void updateUser(UUID id, User userInfo) {
        User userFormDB = userRepo.findById(id).orElseThrow();
        if(StringUtils.isNotBlank(userInfo.getFirstName())) {
            userFormDB.setFirstName(userInfo.getFirstName());
        }
        if(StringUtils.isNotBlank(userInfo.getLastName())) {
            userFormDB.setLastName(userInfo.getLastName());
        }
        if(userInfo.getDob() != null) {
            userFormDB.setDob(userInfo.getDob());
        }
        userRepo.save(userFormDB);
    }
}
