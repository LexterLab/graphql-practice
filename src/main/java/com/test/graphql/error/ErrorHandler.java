package com.test.graphql.error;

import com.test.graphql.exception.GeneralException;
import com.test.graphql.exception.InputValidationException;
import com.test.graphql.operation.base.OperationInput;
import io.vavr.API;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ErrorHandler {
    protected final ConversionService conversionService;
    protected final Validator validator;


    public ErrorWrapper handle(Throwable throwable) {
        System.out.println("Error: " + throwable.getMessage());
        return API.Match(throwable).of(
                Case($(instanceOf(GeneralException.class)), () -> ErrorWrapper.builder()
                        .errors(List.of(Error.builder()
                                .message(throwable.getMessage())
                                .build()))
                        .status(((GeneralException) throwable).getStatus())
                        .build()),
                validatorCase(throwable),

                defaultCase(throwable)
        );
    }


    private API.Match.Case<Exception, ErrorWrapper> defaultCase(Throwable throwable) {
        log.info("Error def: {}", throwable.getMessage());
        return Case($(),() -> ErrorWrapper.builder()
                .errors(List.of(Error.builder()
                        .message(throwable.getMessage())
                        .build()))
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build());
    }

    private API.Match.Case<Exception, ErrorWrapper> validatorCase(Throwable throwable) {
        List<Error> errors = mapExceptionToErrors(throwable);
        log.info("Errors: {}", errors);
        return Case($(instanceOf(InputValidationException.class)), () -> ErrorWrapper.builder()
                .errors(errors)
                .status(HttpStatus.BAD_REQUEST)
                .build());
    }

    public void validateInput(OperationInput input) {
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
        if (throwable instanceof InputValidationException ex) {
                ex.getErrors()
                    .forEach(error -> errors.add(Error.builder()
                            .message(error.message())
                            .field(error.field()).build()));
        }
        return errors;
    }

}
