package com.test.graphql.exception;

import com.test.graphql.error.Error;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class InputValidationException extends RuntimeException {
        private List<Error> errors;
}
