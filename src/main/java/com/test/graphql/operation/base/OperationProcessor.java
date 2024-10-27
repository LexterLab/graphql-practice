package com.test.graphql.operation.base;

import com.test.graphql.error.ErrorWrapper;
import io.vavr.control.Either;

public interface OperationProcessor<I extends OperationInput, O extends OperationOutput> {
    Either<ErrorWrapper, O> process(I input);
}
