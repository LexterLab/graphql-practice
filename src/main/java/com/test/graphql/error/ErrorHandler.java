package com.test.graphql.error;

import com.test.graphql.operation.base.OperationInput;
import io.vavr.API;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.Predicates.instanceOf;

@Component
@RequiredArgsConstructor
public class ErrorHandler {
    protected final ConversionService conversionService;
    protected final Validator validator;

    protected API.Match.Case<Exception, ErrorWrapper> customCase(Throwable throwable, HttpStatus status,
                                                                 Class<? extends Exception> e) {
        return Case($(instanceOf(e)), () -> ErrorWrapper.builder()
                .errors(List.of(Error.builder()
                        .message(throwable.getMessage())
                        .build()))
                .status(status).build());
    }

    protected API.Match.Case<Exception, ErrorWrapper> defaultCase(Throwable throwable) {
        return Case($(),() -> ErrorWrapper.builder()
                .errors(List.of(Error.builder()
                        .message(throwable.getMessage())
                        .build()))
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build());
    }

    protected API.Match.Case<Exception, ErrorWrapper> validatorCase(Throwable throwable) {
        List<Error> errors = mapExceptionToErrors(throwable);
        return Case($(instanceOf(InputValidationException.class)), () -> ErrorWrapper.builder()
                .errors(errors)
                .status(HttpStatus.BAD_REQUEST)
                .build());
    }

    protected void validateInput(OperationInput input) {
        Set<ConstraintViolation<OperationInput>> violations = validator.validate(input);
        if (!violations.isEmpty()) {
            throw new InputValidationException(mapConstraintViolations(violations));
        }
    }

    private List<Error> mapConstraintViolations(Set<ConstraintViolation<OperationInput>> violations) {
        return  violations.stream()
                .map(violation -> Error.builder()
                        .message(violation.getMessage())
                        .field(violation.getPropertyPath().toString())
                        .build())
                .collect(Collectors.toList());
    }

    private List<Error> mapExceptionToErrors(Throwable throwable) {
        List<Error> errors = new ArrayList<>();
        if (throwable instanceof InputValidationException) {
            ((InputValidationException) throwable).getErrors()
                    .forEach(error -> errors.add(Error.builder()
                            .message(error.message())
                            .field(error.field()).build()));
        }
        return errors;
    }

}
