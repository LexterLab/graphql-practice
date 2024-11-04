package com.test.graphql.processor.cartoon;

import com.test.graphql.error.ErrorHandler;
import com.test.graphql.error.ErrorWrapper;
import com.test.graphql.operation.cartoon.update.UpdateCartoon;
import com.test.graphql.operation.cartoon.update.UpdateCartoonInput;
import com.test.graphql.operation.cartoon.update.UpdateCartoonOutput;
import com.test.graphql.validator.input.ValidateInput;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateCartoonProcessor implements UpdateCartoon {
    private final ErrorHandler errorHandler;


    @Override
    @ValidateInput
    public Either<ErrorWrapper, UpdateCartoonOutput> process(UpdateCartoonInput input) {
        return Try.of(() -> UpdateCartoonOutput.builder().build())
                .toEither()
                .mapLeft(errorHandler::handle);
    }

}
