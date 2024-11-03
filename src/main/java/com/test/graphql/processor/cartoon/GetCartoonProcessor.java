package com.test.graphql.processor.cartoon;

import com.test.graphql.error.ErrorHandler;
import com.test.graphql.error.ErrorWrapper;
import com.test.graphql.exception.GeneralException;
import com.test.graphql.operation.cartoon.get.GetCartoon;
import com.test.graphql.operation.cartoon.get.GetCartoonInput;
import com.test.graphql.operation.cartoon.get.GetCartoonOutput;
import com.test.graphql.validator.input.ValidateInput;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCartoonProcessor implements GetCartoon {
    private final ErrorHandler errorHandler;


    @Override
    @ValidateInput
    public Either<ErrorWrapper, GetCartoonOutput> process(GetCartoonInput input) {
        return exception(input)
                .toEither()
                .mapLeft(errorHandler::handle);
    }


    public Try<GetCartoonOutput> result(GetCartoonInput input) {
        return Try.of(() -> GetCartoonOutput.builder().name("gg").build());
    }

    public Try<GetCartoonOutput> exception(GetCartoonInput input) {
        return Try.of(() -> {
            throw new GeneralException("dada", HttpStatus.FORBIDDEN);
        });
    }
}
