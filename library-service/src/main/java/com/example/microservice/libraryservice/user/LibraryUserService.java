package com.example.microservice.libraryservice.user;

import com.example.microservice.libraryservice.ServiceName;
import com.example.microservice.libraryservice.book.dto.BookDto;
import com.example.microservice.libraryservice.issue.LibraryIssueService;
import com.example.microservice.libraryservice.user.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class LibraryUserService {

    @Autowired
    private RestTemplate lbRestTemplate;

    @Autowired
    private LibraryIssueService libraryIssueService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private ServiceName serviceName;

    private String userServiceUrl() {
        return "http://" + serviceName.getUserService() + "/users/";
    }

    public List<UserDto> getAllUsers() {
        return lbRestTemplate.exchange(userServiceUrl(),
                HttpMethod.GET, null, new ParameterizedTypeReference<List<UserDto>>() {})
                .getBody();
    }

    public Optional<UserDto> getUserInfo(UUID id) {
        try{
            UserDto user = lbRestTemplate.exchange(userServiceUrl() + id.toString(),
                    HttpMethod.GET, null, UserDto.class)
                    .getBody();
            assert user != null;

            List<BookDto> issuedBooks = libraryIssueService.getBooksForUser(user.getId());
            user.setIssuedBooks(issuedBooks);

            return Optional.of(user);
        }catch (HttpClientErrorException e) {
            return Optional.empty();
        }
    }

    public UUID addUser(UserDto userDto) {
        HttpEntity<UserDto> httpEntity = new HttpEntity<>(userDto);
        return lbRestTemplate.exchange(userServiceUrl(),
                HttpMethod.POST, httpEntity, UUID.class)
                .getBody();
    }

    public void removeUser(UUID id) {
        libraryIssueService.releaseAllBooksForUser(id);

        lbRestTemplate.delete(userServiceUrl() + id.toString());
    }

    public void updateUserInfo(UUID id, UserDto userDto) {
        try{
            lbRestTemplate.put(userServiceUrl() + id.toString(), userDto);
        }catch (HttpClientErrorException e) {
            log.error("Could not update as user is not found for id {}", id);
        }
    }
}
