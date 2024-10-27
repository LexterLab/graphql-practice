package com.test.graphql.error;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.util.List;

@Builder
public record ErrorWrapper(
        List<Error> errors,
        HttpStatus status
) {
}
