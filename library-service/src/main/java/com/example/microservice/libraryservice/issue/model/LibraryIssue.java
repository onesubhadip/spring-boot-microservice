package com.example.microservice.libraryservice.issue.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LibraryIssue {

    @Id
    private UUID id;

    private UUID userId;

    private UUID bookId;

    private IssueStatus status;
}
