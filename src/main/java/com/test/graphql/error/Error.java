package com.test.graphql.error;

import lombok.Builder;

@Builder
public record Error(
        String message,
        String field
) {
}
