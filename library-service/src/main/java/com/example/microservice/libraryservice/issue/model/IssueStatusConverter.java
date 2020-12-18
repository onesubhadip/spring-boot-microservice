package com.example.microservice.libraryservice.issue.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class IssueStatusConverter implements AttributeConverter<IssueStatus, String> {

    @Override
    public String convertToDatabaseColumn(IssueStatus status) {
        if(status == null) {
            return null;
        }
        return status.code();
    }

    @Override
    public IssueStatus convertToEntityAttribute(String statusCode) {
        if(statusCode == null) {
            return null;
        }
        return Stream.of(IssueStatus.values())
                .filter(c -> c.code().equals(statusCode))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
