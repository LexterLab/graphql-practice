package com.test.graphql.processor.cartoon;

import com.test.graphql.error.ErrorHandler;
import com.test.graphql.error.ErrorWrapper;
import com.test.graphql.operation.cartoon.get.GetCartoon;
import com.test.graphql.operation.cartoon.get.GetCartoonInput;
import com.test.graphql.operation.cartoon.get.GetCartoonOutput;
import com.test.graphql.validator.input.ValidateInput;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCartoonProcessor implements GetCartoon {
    private final ErrorHandler errorHandler;


    @Override
    @ValidateInput
    public Either<ErrorWrapper, GetCartoonOutput> process(GetCartoonInput input) {
        return result()
                .toEither()
                .mapLeft(errorHandler::handle);
    }


    public Try<GetCartoonOutput> result() {
        return Try.of(() -> GetCartoonOutput.builder().name("gg").build());
    }
}
