package com.test.graphql.controller;

import com.test.graphql.error.ErrorWrapper;
import com.test.graphql.operation.base.OperationOutput;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BaseController {

    protected <T extends OperationOutput> Object handleOutput(Either<ErrorWrapper, T> output) {
        return output.fold(
                errorOutput -> errorOutput,
                operationOutput -> operationOutput
        );
    }

    protected ResponseEntity<?> handleOutput(Either<ErrorWrapper, ? extends OperationOutput> output, HttpStatus status) {
        return output.fold(
                errorOutput -> new ResponseEntity<>(errorOutput, errorOutput.status()),
                operationOutput -> new ResponseEntity<>(operationOutput, status)
        );
    }

}
