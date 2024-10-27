package com.test.graphql.controller;

import com.test.graphql.error.ErrorWrapper;
import com.test.graphql.operation.cartoon.add.AddCartoon;
import com.test.graphql.operation.cartoon.add.AddCartoonInput;
import com.test.graphql.operation.cartoon.add.AddCartoonOutput;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CartoonController {
    private final AddCartoon addCartoon;


    @MutationMapping
    public Either<ErrorWrapper, AddCartoonOutput> createBook(@Argument AddCartoonInput input) {
        Either<ErrorWrapper, AddCartoonOutput> output = addCartoon.process(input);
        log.info("output: {}", output);
        return addCartoon.process(input);
    }

}
