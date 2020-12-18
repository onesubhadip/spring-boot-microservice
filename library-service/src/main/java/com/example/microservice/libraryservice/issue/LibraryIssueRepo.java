package com.example.microservice.libraryservice.issue;

import com.example.microservice.libraryservice.issue.model.LibraryIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface LibraryIssueRepo extends JpaRepository<LibraryIssue, UUID> {

    List<LibraryIssue> findByUserId(UUID userId);

    @Modifying(flushAutomatically = true)
    @Query("update LibraryIssue l " +
            "set l.status = com.example.microservice.libraryservice.issue.model.IssueStatus.RETURNED " +
            "where l.userId = :userId")
    void releaseAllBooksForUser(@Param("userId") UUID userId);

    @Modifying(flushAutomatically = true)
    @Query("update LibraryIssue l " +
            "set l.status = com.example.microservice.libraryservice.issue.model.IssueStatus.RETURNED " +
            "where l.userId = :userId and l.bookId = :bookId")
    void releaseBookForUser(@Param("userId") UUID userId, @Param("bookId") UUID bookId);
}
