package com.test.graphql.processor.cartoon;

import com.test.graphql.error.ErrorHandler;
import com.test.graphql.error.ErrorWrapper;
import com.test.graphql.operation.cartoon.add.AddCartoon;
import com.test.graphql.operation.cartoon.add.AddCartoonInput;
import com.test.graphql.operation.cartoon.add.AddCartoonOutput;
import com.test.graphql.validator.input.ValidateInput;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddCartoonProcessor implements AddCartoon {
    private final ErrorHandler errorHandler;

    @Override
    @ValidateInput
    public Either<ErrorWrapper, AddCartoonOutput> process(AddCartoonInput input) {
        log.info("Processing AddCartoon");
        return cartoonOutput(input)
                .toEither()
                .mapLeft(errorHandler::handle);
    }

    private Try<AddCartoonOutput> cartoonOutput(AddCartoonInput input) {
        log.info("input {}", input);
        errorHandler.validateInput(input);
        return Try.of(() -> AddCartoonOutput.builder()
                .name(input.name())
                .build());
    }
}
