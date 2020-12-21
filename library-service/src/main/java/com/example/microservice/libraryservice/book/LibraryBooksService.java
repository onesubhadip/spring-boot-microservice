package com.example.microservice.libraryservice.book;

import com.example.microservice.libraryservice.ServiceName;
import com.example.microservice.libraryservice.book.dto.BookDto;
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
public class LibraryBooksService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private ServiceName serviceName;

    private String bookServiceUrl() {
        return discoveryClient.getInstances(serviceName.getBookService())
                .get(0)
                .getUri()
                .toString() + "/books/";
    }

    public List<BookDto> getAllBooks() {
        return restTemplate.exchange(bookServiceUrl(),
                HttpMethod.GET, null, new ParameterizedTypeReference<List<BookDto>>() {})
               .getBody();
    }

    public Optional<BookDto> getBookInfo(UUID id) {

        try{
            return Optional.ofNullable(restTemplate.exchange(bookServiceUrl() + id.toString(),
                    HttpMethod.GET, null, BookDto.class)
                    .getBody());
        }catch (HttpClientErrorException e) {
            log.error("Book for id {} not found", id, e);
            return Optional.empty();
        }
    }

    public UUID addNewBook(BookDto bookDto) {

        HttpEntity<BookDto> httpEntity = new HttpEntity<>(bookDto);
        return restTemplate.exchange(bookServiceUrl(),
                HttpMethod.POST, httpEntity, UUID.class)
                .getBody();
    }

    public void deleteBook(UUID id) {
        restTemplate.delete(bookServiceUrl() + id.toString());
    }
}
