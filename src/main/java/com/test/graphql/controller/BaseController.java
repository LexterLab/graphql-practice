package com.test.graphql.controller;

import com.test.graphql.error.ErrorWrapper;
import com.test.graphql.operation.base.OperationOutput;
import io.vavr.control.Either;

public abstract class BaseController {

    protected <T extends OperationOutput> Object handleOutput(Either<ErrorWrapper, T> output) {
        return output.fold(
                errorOutput -> errorOutput,
                operationOutput -> operationOutput
        );
    }

}
