package com.example.microservice.libraryservice.issue;

import com.example.microservice.libraryservice.book.LibraryBooksService;
import com.example.microservice.libraryservice.book.dto.BookDto;
import com.example.microservice.libraryservice.issue.model.IssueStatus;
import com.example.microservice.libraryservice.issue.model.LibraryIssue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LibraryIssueService {

    @Autowired
    private LibraryIssueRepo libraryIssueRepo;

    @Autowired
    private LibraryBooksService libraryBooksService;

    public List<BookDto> getBooksForUser(UUID id) {

        List<LibraryIssue> issues = libraryIssueRepo.findByUserId(id);
        return issues.stream()
                .filter(issue-> IssueStatus.ISSUED.equals(issue.getStatus()))
                .map(LibraryIssue::getBookId)
                .map(libraryBooksService::getBookInfo)
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
    }

    public void releaseAllBooksForUser(UUID id) {
        libraryIssueRepo.releaseAllBooksForUser(id);
    }

    public void releaseBookForUser(UUID userId, UUID bookId) {
        libraryIssueRepo.releaseBookForUser(userId, bookId);
    }

    public UUID issueBookForUser(UUID userId, UUID bookId) {

        LibraryIssue newIssue = LibraryIssue.builder()
                .id(UUID.randomUUID())
                .userId(userId)
                .bookId(bookId)
                .status(IssueStatus.ISSUED)
                .build();

        libraryIssueRepo.save(newIssue);
        return newIssue.getId();
    }
}
